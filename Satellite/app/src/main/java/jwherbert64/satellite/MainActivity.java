package jwherbert64.satellite;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.InputStream;

import Data.JSONSatelliteParser;
import Data.SatelliteHttpClient;
import Model.Satellite;

public class MainActivity extends AppCompatActivity {

    private EditText et_lon;
    private EditText et_lat;
    private Button b_submit;
    private ImageView iv_sat;
    private TextView tv_date;
    private LinearLayout ll_sat;

    Satellite satellite = new Satellite();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_lon = (EditText) findViewById(R.id.et_lon);
        et_lat = (EditText) findViewById(R.id.et_lat);
        b_submit = (Button) findViewById(R.id.b_submit);
        iv_sat = (ImageView) findViewById(R.id.iv_sat);
        tv_date = (TextView) findViewById(R.id.tv_date);
        ll_sat = (LinearLayout) findViewById(R.id.ll_sat);

        /*Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        int width = size.x;
        int height = size.y*/

        Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
        Typeface typeFaceBold = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Bold.ttf");

        et_lon.setTypeface(typeFace);
        et_lat.setTypeface(typeFace);
        tv_date.setTypeface(typeFaceBold);
        b_submit.setTypeface(typeFaceBold);

        b_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                renderSatelliteData(et_lon.getText().toString(), et_lat.getText().toString());
            }
        });
    }

    private class DownloadImageAsyncTask extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... params) {
            String urlDisplay = params[0];

            Bitmap icon = null;

            try {
                InputStream in = new java.net.URL(urlDisplay).openStream();
                icon = BitmapFactory.decodeStream(in);
            }
            catch(Exception e) {
                Log.e("Error: ", e.getMessage());
                e.printStackTrace();
            }
            return icon;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            iv_sat.setImageBitmap(bitmap);
        }
    }

    public void renderSatelliteData(String lon, String lat) {
        SatelliteTask satelliteTask = new SatelliteTask();
        satelliteTask.execute(new String[]{"" + lon, "" + lat});
    }

    private class SatelliteTask extends AsyncTask<String, Void, Satellite> {
        @Override
        protected Satellite doInBackground(String... params) {
            String data = ((new SatelliteHttpClient()).getSatelliteData(params[0], params[1]));

            satellite = JSONSatelliteParser.getSatellite(data);

            return satellite;
        }

        @Override
        protected void onPostExecute(Satellite satellite) {
            super.onPostExecute(satellite);

            String strDate = satellite.getDate().substring(0, 10);

            tv_date.setText("Last Updated: " + strDate);

            new DownloadImageAsyncTask().execute(satellite.getUrl());
        }
    }
}