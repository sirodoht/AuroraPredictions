package layout;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.uom.gr.aurorapredictions.LocationsJsonParser;
import android.uom.gr.aurorapredictions.R;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LocationsFragment extends Fragment {

    ArrayAdapter<String> locationsListAdapter;

    private String[] data = {
            "Athabasca, Alberta, Canada",
            "Cape Liptrap Lighthouse",
            "Cherry Springs State Park",
            "Cressy, Tasmania, Australia",
            "Fairbanks, Alaska, United States",
            "Flinders, Victoria, Australia",
            "Eagle's Nest, Inverloch, Victoria, Australia",
            "Iqaluit, Nunavut, Canada",
            "Longyearbyen, Svalbard, Norway",
            "Melfort, Saskatchewan, Canada",
            "Mount Tassie, Victoria, Australia",
            "Point Addis, Victoria, Australia",
            "Portland, Victoria, AUstralia",
            "Split Point Lighthouse at Aireys Inlet, Victoria, Australia",
            "Troms, Troms County, Norway",
            "Whitehorse, Yukon Territory, Canada",
            "Yellowknife, NWT, Canada",
    };

    public LocationsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.locationsfragment, menu);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        List<String> dummyList = new ArrayList<>(Arrays.asList(data));

        locationsListAdapter = new ArrayAdapter<>(
                getActivity(),
                R.layout.location_list_item,
                R.id.location_list_item_textview,
                dummyList);

        View rootView = inflater.inflate(R.layout.fragment_locations, container, false);

        ListView locationsListView = (ListView) rootView.findViewById(R.id.listview_locations);
        locationsListView.setAdapter(locationsListAdapter);

        return rootView;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_refresh) {
            FetchLocationsTask task = new FetchLocationsTask();
            task.execute();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class FetchLocationsTask extends AsyncTask<String, Void, String[]> {

        @Override
        protected String[] doInBackground(String... params) {
            return fetchLocationsData();
        }

        @Override
        protected void onPostExecute(String[] strings) {
            if (strings != null) {
                locationsListAdapter.clear();
                for (String location : strings) {
                    locationsListAdapter.add(location);
                }
            }
        }

        private String[] fetchLocationsData() {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            String locationsJsonStr = null;

            try {
                URL url = new URL("https://api.auroras.live/v1/?type=locations");

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                reader = new BufferedReader(new InputStreamReader(inputStream));

                // Add \n for debugging purposes
                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }

                locationsJsonStr = buffer.toString();

                Log.i("LocationsFragment", locationsJsonStr);

                List<String> locationsList = LocationsJsonParser.getLocationsFromJson(locationsJsonStr);
                String[] locationsArray = new String[7];
                return locationsList.toArray(locationsArray);

            } catch (IOException e) {
                Log.e("LocationsFragment", "Error on API call:", e);
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("LocationsFragment", "Error closing stream:", e);
                    }
                }
            }

            return null;
        }
    }

}
