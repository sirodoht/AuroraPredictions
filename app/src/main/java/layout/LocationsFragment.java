package layout;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.uom.gr.aurorapredictions.R;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

}
