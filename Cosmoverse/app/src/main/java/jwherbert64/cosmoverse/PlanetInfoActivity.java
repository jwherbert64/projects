package jwherbert64.cosmoverse;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

/**
 * Created by James on 14/02/2017.
 */

public class PlanetInfoActivity extends FragmentActivity {

    TextView tv_title;

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_activity_layout);

        tv_title = (TextView) findViewById(R.id.tv_title);

        Typeface typeFaceBold = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Bold.ttf");

        tv_title.setTypeface(typeFaceBold);

        tv_title.setText("The Planets");

        viewPager = (ViewPager) findViewById(R.id.view_pager);

        PlanetSwipeAdapter swipeAdapter = new PlanetSwipeAdapter(getSupportFragmentManager());
        viewPager.setAdapter(swipeAdapter);
    }
}