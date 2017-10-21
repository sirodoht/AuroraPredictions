package android.uom.gr.aurorapredictions;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sirodoht on 10/21/17.
 */

public class LocationsJsonParser {
    public static List<String> getLocationsFromJson(String jsonString) {
        List<String> results = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray locationsList = jsonObject.getJSONArray("list");

            Log.i("locationsList:", locationsList.toString());

            for (int i = 0; i < locationsList.length(); i++) {
                String locationName = locationsList.getJSONObject(i).getString("name");
                Log.i("locationName:", locationName);
                results.add(locationName);
            }

        } catch (JSONException e) {
            Log.e("LocationsJsonParser", e.getMessage());
        }

        return results;
    }
}
