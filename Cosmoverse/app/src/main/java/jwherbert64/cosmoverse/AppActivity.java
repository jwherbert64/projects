package jwherbert64.cosmoverse;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AppActivity extends AppCompatActivity {

    TextView tv_u_title, tv_u_desc;
    Button b_u_info;

    TextView tv_s_title, tv_s_desc;
    Button b_s_info, b_s_gallery;

    TextView tv_p_title, tv_p_desc;
    Button b_p_info, b_p_gallery;

    TextView tv_g_title, tv_g_desc;
    Button b_g_info, b_g_gallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_activity_layout);

        tv_u_title = (TextView) findViewById(R.id.tv_u_title);
        tv_u_desc = (TextView) findViewById(R.id.tv_u_desc);
        b_u_info = (Button) findViewById(R.id.b_u_info);

        tv_s_title = (TextView) findViewById(R.id.tv_s_title);
        tv_s_desc = (TextView) findViewById(R.id.tv_s_desc);
        b_s_info = (Button) findViewById(R.id.b_s_info);
        b_s_gallery = (Button) findViewById(R.id.b_s_gallery);

        tv_p_title = (TextView) findViewById(R.id.tv_p_title);
        tv_p_desc = (TextView) findViewById(R.id.tv_p_desc);
        b_p_info = (Button) findViewById(R.id.b_p_info);
        b_p_gallery = (Button) findViewById(R.id.b_p_gallery);

        tv_g_title = (TextView) findViewById(R.id.tv_g_title);
        tv_g_desc = (TextView) findViewById(R.id.tv_g_desc);
        b_g_info = (Button) findViewById(R.id.b_g_info);
        b_g_gallery = (Button) findViewById(R.id.b_g_gallery);

        Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
        Typeface typeFaceBold = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Bold.ttf");

        tv_u_title.setTypeface(typeFaceBold);
        tv_u_desc.setTypeface(typeFace);
        b_u_info.setTypeface(typeFace);

        tv_s_title.setTypeface(typeFaceBold);
        tv_s_desc.setTypeface(typeFace);
        b_s_info.setTypeface(typeFace);
        b_s_gallery.setTypeface(typeFace);

        tv_p_title.setTypeface(typeFaceBold);
        tv_p_desc.setTypeface(typeFace);
        b_p_info.setTypeface(typeFace);
        b_p_gallery.setTypeface(typeFace);

        tv_g_title.setTypeface(typeFaceBold);
        tv_g_desc.setTypeface(typeFace);
        b_g_info.setTypeface(typeFace);
        b_g_gallery.setTypeface(typeFace);

        String str_u_desc = "The observable universe is an infinite expansion of space and time created by the Big Bang 13 billion years ago. It is believed to be at least 10 billion light-years in diameter and includes all kinds of physical matter and energy, the planets, stars, galaxies, and all the contents of space.";

        String str_s_desc = "A star is a luminous ball of gas made up of mostly hydrogen and helium, held together by its own gravity. Nuclear fusion reactions occur within its core producing light and heat. A star with a large enough mass collapses in upon itself then explodes in a supernova. It's believed this death of a star is the cause of black holes.";

        String str_p_desc = "A planets a celestial object which orbits a star made from the debris of the solar nebula. The heavy weight of a star gives it a gravitational pull on the matter, grouping together mass until it's rounded by its own gravity to form planets. This causes planets to form their own celestial bodies orbiting them called moons.";

        String str_g_desc = "A galaxy is a collection of millions or billions of stars among gas and dust, bound together by gravitational attraction accelerating through the universe. Galaxies vary greatly in shape and size and there is no definitive model of how galaxies form. There are at least one hundred billion galaxies in the observable universe.";

        tv_u_desc.setText(str_u_desc);

        tv_s_desc.setText(str_s_desc);

        tv_p_desc.setText(str_p_desc);

        tv_g_desc.setText(str_g_desc);

        b_u_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), UniverseInfoActivity.class);
                startActivity(i);
            }
        });

        b_s_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), StarInfoActivity.class);
                startActivity(i);
            }
        });

        b_s_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), GalleryActivity.class);
                i.putExtra("title", "The Stars");
                startActivity(i);
            }
        });

        b_p_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), PlanetInfoActivity.class);
                startActivity(i);
            }
        });

        b_p_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), GalleryActivity.class);
                i.putExtra("title", "The Planets");
                startActivity(i);
            }
        });

        b_g_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), GalaxyInfoActivity.class);
                startActivity(i);
            }
        });

        b_g_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), GalleryActivity.class);
                i.putExtra("title", "The Galaxies");
                startActivity(i);
            }
        });

    }
}
