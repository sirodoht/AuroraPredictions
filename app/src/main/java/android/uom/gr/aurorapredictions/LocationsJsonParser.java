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
            JSONArray locationsJsonArray = new JSONArray(jsonString);

            Log.i("locationsList:", locationsJsonArray.toString());

            for (int i = 0; i < locationsJsonArray.length(); i++) {
                JSONObject locationsItemJsonObject = locationsJsonArray.getJSONObject(i);
                String locationName = locationsItemJsonObject.getString("description");
                Log.i("locationName:", locationName);
                results.add(locationName);
            }

        } catch (JSONException e) {
            Log.e("LocationsJsonParser", e.getMessage());
        }

        return results;
    }
}
