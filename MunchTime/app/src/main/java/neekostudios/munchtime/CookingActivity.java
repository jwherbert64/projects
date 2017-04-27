package neekostudios.munchtime;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class CookingActivity extends AppCompatActivity {

    Button b_cooking, b_shopping;
    Button b_search;
    Button b_popular, b_trending;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.cooking_activity_layout);

        Typeface regular = Typeface.createFromAsset(getAssets(), "fonts/Hind-Regular.ttf");
        Typeface light = Typeface.createFromAsset(getAssets(), "fonts/Hind-Light.ttf");
        Typeface medium = Typeface.createFromAsset(getAssets(), "fonts/Hind-Medium.ttf");
        Typeface bold = Typeface.createFromAsset(getAssets(), "fonts/Hind-Bold.ttf");
        Typeface semiBold = Typeface.createFromAsset(getAssets(), "fonts/Hind-SemiBold.ttf");

        b_cooking = (Button) findViewById(R.id.b_cooking);
        b_shopping = (Button) findViewById(R.id.b_shopping);
        b_search = (Button) findViewById(R.id.b_search);
        b_popular = (Button) findViewById(R.id.b_popular);
        b_trending = (Button) findViewById(R.id.b_trending);

        b_cooking.setTypeface(semiBold);
        b_shopping.setTypeface(semiBold);
        b_search.setTypeface(bold);
        b_popular.setTypeface(medium);
        b_trending.setTypeface(medium);
    }
}