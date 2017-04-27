package jwherbert64.cosmoverse;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by James on 16/02/2017.
 */

public class ImageActivity  extends AppCompatActivity {

    TextView tv_title, tv_desc;
    ImageView iv_image;

    String str_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_activity_layout);

        getSupportActionBar().hide();

        tv_title = (TextView) findViewById(R.id.tv_title);;
        tv_desc = (TextView) findViewById(R.id.tv_desc);
        iv_image = (ImageView) findViewById(R.id.iv_image);

        Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
        Typeface typeFaceBold = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Bold.ttf");

        tv_title.setTypeface(typeFaceBold);
        tv_desc.setTypeface(typeFace);

        Intent i = getIntent();

        str_title = i.getExtras().getString("topic");
        int position = i.getExtras().getInt("id");

        if(str_title.equals("The Stars")) {
            ImageAdapterStar adapter = new ImageAdapterStar(this);

            tv_title.setText(adapter.title[position]);
            tv_desc.setText(adapter.description[position]);

            String uri = adapter.images[position];
            int imageResource = getResources().getIdentifier(uri, null, getPackageName());
            Drawable res = getResources().getDrawable(imageResource);
            iv_image.setImageDrawable(res);
        }

        if(str_title.equals("The Planets")) {
            ImageAdapterPlanet adapter = new ImageAdapterPlanet(this);

            tv_title.setText(adapter.title[position]);
            tv_desc.setText(adapter.description[position]);

            String uri = adapter.images[position];
            int imageResource = getResources().getIdentifier(uri, null, getPackageName());
            Drawable res = getResources().getDrawable(imageResource);
            iv_image.setImageDrawable(res);
        }

        if(str_title.equals("The Galaxies")) {
            ImageAdapterGalaxy adapter = new ImageAdapterGalaxy(this);

            tv_title.setText(adapter.title[position]);
            tv_desc.setText(adapter.description[position]);

            String uri = adapter.images[position];
            int imageResource = getResources().getIdentifier(uri, null, getPackageName());
            Drawable res = getResources().getDrawable(imageResource);
            iv_image.setImageDrawable(res);
        }
    }
}
