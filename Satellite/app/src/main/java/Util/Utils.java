package Util;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by James on 1/02/2017.
 */

public class Utils {
    public static final String BASE_URL = "https://api.nasa.gov/planetary/earth/imagery?";
    public static final String API_KEY = "&api_key=6Hv8cbs9nvNYsUAPN6qjhXfXgeuIhEeapNu6XrCp";

    public static JSONObject getObject(String tagName, JSONObject jsonObject)
            throws JSONException {
        JSONObject jObj = jsonObject.getJSONObject(tagName);
        return jObj;
    }

    public static String getString(String tagName, JSONObject jsonObject)
            throws JSONException {
        return jsonObject.getString(tagName);
    }

    public static float getFloat(String tagName, JSONObject jsonObject)
            throws JSONException {
        return (float) jsonObject.getDouble(tagName);
    }

    public static int getInt(String tagName, JSONObject jsonObject)
            throws JSONException {
        return jsonObject.getInt(tagName);
    }

}
