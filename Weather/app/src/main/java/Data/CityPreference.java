package Data;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;

/**
 * Created by James on 1/02/2017.
 */

public class CityPreference {
    SharedPreferences prefs;

    public CityPreference(Activity activity) {
        prefs = activity.getPreferences(Activity.MODE_PRIVATE);
    }

    public String getCity() {
        return prefs.getString("city", "Sydney,AU");
    }

    public void setCity(String city) {
        prefs.edit().putString("city", city).commit();
    }
}
