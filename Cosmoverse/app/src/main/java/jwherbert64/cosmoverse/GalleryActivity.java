package jwherbert64.cosmoverse;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

public class GalleryActivity extends AppCompatActivity {

    TextView tv_title;

    GridView gv_gallery;

    String str_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_activity_layout);

        getSupportActionBar().hide();

        tv_title = (TextView) findViewById(R.id.tv_title);
        gv_gallery = (GridView) findViewById(R.id.gv_gallery);

        Intent i = getIntent();

        str_title = i.getExtras().getString("title");

        Typeface typeFaceBold = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Bold.ttf");

        tv_title.setTypeface(typeFaceBold);

        tv_title.setText(str_title);

        if(str_title.equals("The Stars")) {
            gv_gallery.setAdapter(new ImageAdapterStar(this));

            gv_gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent i = new Intent(getApplicationContext(), ImageActivity.class);
                    i.putExtra("topic", str_title);
                    i.putExtra("id", position);
                    startActivity(i);
                }
            });
        }

        if(str_title.equals("The Planets")) {
            gv_gallery.setAdapter(new ImageAdapterPlanet(this));

            gv_gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent i = new Intent(getApplicationContext(), ImageActivity.class);
                    i.putExtra("topic", str_title);
                    i.putExtra("id", position);
                    startActivity(i);
                }
            });
        }

        if(str_title.equals("The Galaxies")) {
            gv_gallery.setAdapter(new ImageAdapterGalaxy(this));

            gv_gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent i = new Intent(getApplicationContext(), ImageActivity.class);
                    i.putExtra("topic", str_title);
                    i.putExtra("id", position);
                    startActivity(i);
                }
            });
        }
    }
}
