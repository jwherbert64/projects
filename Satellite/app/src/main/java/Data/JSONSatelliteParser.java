package Data;

import org.json.JSONException;
import org.json.JSONObject;

import Model.Satellite;
import Util.Utils;

/**
 * Created by James on 1/02/2017.
 */

public class JSONSatelliteParser {

    public static Satellite getSatellite(String data) {

        Satellite satellite = new Satellite();

        try {
            JSONObject jsonObject = new JSONObject(data);

            satellite.setDate(Utils.getString("date", jsonObject));
            satellite.setUrl(Utils.getString("url", jsonObject));

            return satellite;
        }
        catch(JSONException e) {
            e.printStackTrace();

            return null;
        }
    }
}
