package jwherbert64.weather;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.text.DateFormat;
import java.util.Date;

import Data.CityPreference;
import Data.JSONWeatherParser;
import Data.WeatherHttpClient;
import Model.Weather;
import Util.Utils;

public class AppActivity extends AppCompatActivity {

    private TextView tv_city;
    private TextView tv_temp;
    private ImageView iv_thumb;
    private TextView tv_con;
    private TextView tv_wind;
    private TextView tv_clouds;
    private TextView tv_pressure;
    private TextView tv_humid;
    private TextView tv_rise;
    private TextView tv_set;
    private TextView tv_date;

    Weather weather = new Weather();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_activity_layout);

        tv_city = (TextView) findViewById(R.id.tv_city);
        tv_temp = (TextView) findViewById(R.id.tv_temp);
        iv_thumb = (ImageView) findViewById(R.id.iv_thumb);
        tv_con = (TextView) findViewById(R.id.tv_con);
        tv_wind = (TextView) findViewById(R.id.tv_wind);
        tv_clouds = (TextView) findViewById(R.id.tv_clouds);
        tv_pressure = (TextView) findViewById(R.id.tv_pressure);
        tv_humid = (TextView) findViewById(R.id.tv_humid);
        tv_rise = (TextView) findViewById(R.id.tv_rise);
        tv_set = (TextView) findViewById(R.id.tv_set);
        tv_date = (TextView) findViewById(R.id.tv_date);

        Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
        Typeface typeFaceBold = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Bold.ttf");

        tv_city.setTypeface(typeFaceBold);
        tv_temp.setTypeface(typeFaceBold);
        tv_con.setTypeface(typeFace);
        tv_wind.setTypeface(typeFace);
        tv_clouds.setTypeface(typeFace);
        tv_pressure.setTypeface(typeFace);
        tv_humid.setTypeface(typeFace);
        tv_date.setTypeface(typeFaceBold);


        CityPreference cityPreference = new CityPreference(AppActivity.this);

        renderWeatherData(cityPreference.getCity());
    }

    private void showInputDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AppActivity.this);
        builder.setTitle("Change City");

        final EditText cityInput = new EditText(AppActivity.this);
        cityInput.setInputType(InputType.TYPE_CLASS_TEXT);
        cityInput.setHint("Format: Sydney,AU");
        builder.setView(cityInput);
        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                CityPreference cityPreference = new CityPreference(AppActivity.this);
                cityPreference.setCity(cityInput.getText().toString());

                String newCity = cityPreference.getCity();

                renderWeatherData(newCity);
            }
        });
        builder.show();
    }

    private class DownloadImageAsyncTask extends AsyncTask<String, Void, Bitmap> {

        protected Bitmap doInBackground(String... params) {
            String urlDisplay = Utils.ICON_URL + params[0] + Utils.FILE_TYPE;

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
            iv_thumb.setImageBitmap(bitmap);
        }
    }

    public void renderWeatherData(String city) {
        WeatherTask weatherTask = new WeatherTask();
        weatherTask.execute(new String[]{city + "&units=metric"});
    }

    private class WeatherTask extends AsyncTask<String, Void, Weather> {
        @Override
        protected Weather doInBackground(String... params) {

            String data = ((new WeatherHttpClient()).getWeatherData(params[0]));

            weather.iconData = weather.currentCondition.getIcon();

            weather = JSONWeatherParser.getWeather(data);

            return weather;
        }

        @Override
        protected void onPostExecute(Weather weather) {
            super.onPostExecute(weather);

            DateFormat df = DateFormat.getTimeInstance(DateFormat.SHORT);

            String sunriseDate = df.format(new Date(weather.place.getSunrise()));
            String sunsetDate = df.format(new Date(weather.place.getSunset()));
            String date = df.format(new Date(weather.place.getLastUpdate()));

            tv_city.setText(weather.place.getCity() + "," + weather.place.getCountry());
            tv_temp.setText("" + String.format("%.1f", weather.currentCondition.getTemperature()) + "°C");
            tv_con.setText("Condition: " + weather.currentCondition.getCondition());
            tv_wind.setText("Wind: " + String.format("%.1f", weather.wind.getSpeed()) + "mps, " +
                    String.format("%.1f", weather.wind.getDeg()) + "°C");
            tv_clouds.setText("Precipitation: " + weather.clouds.getPrecipitation() + "mm");
            tv_pressure.setText("Pressure: " + (int) (weather.currentCondition.getPressure()) + "hpa");
            tv_humid.setText("Humidity: " + (int) (weather.currentCondition.getHumidity()) + "%");
            //tv_rise.setText("Sunrise: " + sunriseDate);
            //tv_set.setText("Sunset: " + sunsetDate);
            tv_date.setText("Last Update: " + date);

            new DownloadImageAsyncTask().execute(weather.currentCondition.getIcon());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.i_city) {
            showInputDialog();
        }

        return super.onOptionsItemSelected(item);
    }
}
