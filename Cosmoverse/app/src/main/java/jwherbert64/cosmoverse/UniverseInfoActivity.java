package jwherbert64.cosmoverse;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

/**
 * Created by James on 14/02/2017.
 */

public class UniverseInfoActivity extends FragmentActivity {

    TextView tv_title;

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_activity_layout);

        tv_title = (TextView) findViewById(R.id.tv_title);

        Typeface typeFaceBold = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Bold.ttf");

        tv_title.setTypeface(typeFaceBold);

        tv_title.setText("The Universe");

        viewPager = (ViewPager) findViewById(R.id.view_pager);

        UniverseSwipeAdapter swipeAdapter = new UniverseSwipeAdapter(getSupportFragmentManager());
        viewPager.setAdapter(swipeAdapter);
    }
}
