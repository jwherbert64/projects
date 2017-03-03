package Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import Util.Utils;

/**
 * Created by James on 1/02/2017.
 */

public class SatelliteHttpClient {

    public String getSatelliteData(String lon, String lat) {
        HttpURLConnection connection = null;
        InputStream inputStream = null;

        try {
            //lon=100.75&lat=1.5&date=2016-12-12&cloud_score=True
            connection = (HttpURLConnection) (new URL(Utils.BASE_URL + "lon=" + lon + "&lat=" + lat +
            "&date=2016-12-12&cloud_score=True" + Utils.API_KEY)).openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();

            //Read response
            StringBuffer stringBuffer = new StringBuffer();
            inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;

            while((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line + "\r\n");
            }

            inputStream.close();
            connection.disconnect();

            return stringBuffer.toString();
        }
        catch(IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
