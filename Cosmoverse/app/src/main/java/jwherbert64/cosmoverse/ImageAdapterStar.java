package jwherbert64.cosmoverse;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by James on 16/02/2017.
 */

public class ImageAdapterStar extends BaseAdapter {
    private Context context;

    public String[] title = {
            "The Sun",
            "OGLE TR-122b",
            "R136a1",
            "PSR J1719-1438",
            "LP867-10",
            "HE 2359-2844",
            "Kepler-47",
            "La Superba",
            "PSR J1841-0500",
            "Swift J1644+57"
    };

    public String[] images = {
            "@drawable/star_1",
            "@drawable/star_2",
            "@drawable/star_3",
            "@drawable/star_4",
            "@drawable/star_5",
            "@drawable/star_6",
            "@drawable/star_7",
            "@drawable/star_8",
            "@drawable/star_9",
            "@drawable/star_10"
    };

    public String[] description = {
            "Our Sun is 4.6 billion years old, has a diameter of 1.4 million km, temperatures of up to 15.6 million °C and a core consisting mainly of hydrogen and helium. Our Sun has significant spots with lower temperatures, it is also responsible for solar winds and magnetic storms on Earth.",
            "OGLE TR-122b is a brown dwarf, meaning it never gained enough size, heat or nuclear energy to be effective as a star. It is the smallest star ever recorded and is roughly the size of Jupiter. It's only able to remain active due to the protection provided by a much larger parent star.",
            "R136a1 is the largest and heaviest known star in the universe, weighing 265 times more than the Sun. What's fascinating about R136a1 is that it's lost weight over time spewing out matter and energy. It's expected to explode in a few million years.",
            "PSR J1719-1438 is a large star with a white dwarf orbiting around it. The gravity from it's so strong that it's stripped the white dwarf of everything but its core. The white dwarf shines bright like a diamond and is incredibly dense and heavy.",
            "Some stars are so massive, that planets close to them are destroyed by their gravity. LP867-10 is a large star with a gas giant on a close elliptical orbit to it. Satellite moons attach to the planet on trajectory and get torn apart and engulfed by the star.",
            "Most stars will only release gas materials leaving heavier elements like lead to reside in their cores. HE 2359-2844 releases 10'000 times more lead than the Sun, categorized as a heavy metal sub-dwarf. The surface metal of this star resides in a huge cloud layer over 100 km thick.",
            "The Kepler-47 system has two stars, one roughly the size of the Sun and another three times as small, both affected by the other's gravity. Orbiting those two are a couple of planets, both gas giants.",
            "La Superba is a dying red giant about 710 light-years away from Earth. It burns at around 2,400°C, which is frigidly cold for a star, although it's more than 4'400 times brighter than our Sun, due to a massive amount of infrared radiation.",
            "A pulsar is an ultra-light, ultra-speedy star that can rotate in half a millisecond. This causes immensely bright rays of light to shoot at regular intervals. PSR J1841-0500 is a pulsar which stopped rotating for 580 days. There is no known reason for why it stopped or started up again.",
            "Black holes are so massive their gravity has a huge effect on other objects in the universe. Swift J1644+57 is a star which lies extremely close to a black hole which is a rarity. It is slowly being torn apart and devoured by the black hole observed through bright gamma rays contact causes."
    };

    public ImageAdapterStar(Context c) {
        context = c;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int position) {
        return images[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Typeface typeFaceBold = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Bold.ttf");
        final float scale = context.getResources().getDisplayMetrics().density;

        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        int la = (int) (150 * scale + 0.5f);
        int lb = (int) (50 * scale + 0.5f);
        int m = (int) (1 * scale + 0.5f);

        ViewGroup.LayoutParams llp = new ViewGroup.LayoutParams(new GridView.LayoutParams(la, la + lb));
        linearLayout.setLayoutParams(llp);
        linearLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.black));

        ImageView imageView = new ImageView(context);

        String uri = images[position];
        int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
        Drawable res = context.getResources().getDrawable(imageResource);
        imageView.setImageDrawable(res);

        ViewGroup.LayoutParams ilp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, la);
        imageView.setLayoutParams(ilp);

        TextView textView = new TextView(context);
        LinearLayout.LayoutParams tlp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        tlp.leftMargin = m;
        tlp.rightMargin = m;
        tlp.bottomMargin = m;
        textView.setLayoutParams(tlp);
        textView.setTextSize(16);
        textView.setTextColor(ContextCompat.getColor(context, R.color.white));
        textView.setBackgroundResource(R.drawable.button);
        textView.setPadding(5, 0, 5, 0);
        textView.setGravity(Gravity.CENTER);
        textView.setTypeface(typeFaceBold);
        textView.setText(title[position]);

        linearLayout.addView(textView, 0);
        linearLayout.addView(imageView, 0);

        return linearLayout;
    }
}