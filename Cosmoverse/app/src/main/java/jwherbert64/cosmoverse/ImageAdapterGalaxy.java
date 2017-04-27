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

public class ImageAdapterGalaxy extends BaseAdapter {
    private Context context;

    public String[] title = {
            "Milky Way",
            "Andromeda",
            "Triangulum",
            "Messier 64",
            "Messier 81",
            "Messier 82",
            "Cosmos Redshift 7",
            "Large Magellanic Cloud",
    };

    public String[] images = {
            "@drawable/galaxy_1",
            "@drawable/galaxy_2",
            "@drawable/galaxy_3",
            "@drawable/galaxy_4",
            "@drawable/galaxy_5",
            "@drawable/galaxy_6",
            "@drawable/galaxy_7",
            "@drawable/galaxy_8"
    };

    public String[] description = {
            "The Milky Way is the galaxy that contains our Solar System. The Milky Way is a barred spiral galaxy with a diameter between 100,000 light-years and 180,000 light-years. The Milky Way is estimated to contain 100–400 billion stars. There are probably at least 100 billion planets in the Milky Way. The Solar System is located within the disk, about 26,000 light-years from the Galactic Center, on the inner edge of one of the spiral-shaped concentrations of gas and dust called the Orion Arm. The stars in the inner 10,000 light-years form a bulge and one or more bars that radiate from the bulge. The very center is marked by an intense radio source, named Sagittarius A*, which is likely to be a supermassive black hole. Stars and gases at a wide range of distances from the Galactic Center orbit at approximately 220 kilometers per second. The constant rotation speed contradicts the laws of Keplerian dynamics and suggests that much of the mass of the Milky Way does not emit or absorb electromagnetic radiation. This mass has been termed \"dark matter\". The rotational period is about 240 million years at the position of the Sun. The Milky Way as a whole is moving at a velocity of approximately 600 km per second with respect to extragalactic frames of reference. The oldest stars in the Milky Way are nearly as old as the Universe itself and thus probably formed shortly after the Dark Ages of the Big Bang. The Milky Way has several satellite galaxies and is part of the Local Group of galaxies, which is a component of the Virgo Supercluster, which is itself a component of the Laniakea Supercluster.",
            "The Andromeda Galaxy is a spiral galaxy approximately 2.5 million light-years from Earth and is the nearest major galaxy to the Milky Way. Andromeda is approximately 220,000 light years across, and it is the largest galaxy of the Local Group. Despite earlier findings that suggested that the Milky Way contains more dark matter and could be the largest in the grouping, it was revealed that Andromeda contains one trillion stars, at least twice the number of stars in the Milky Way, which is estimated to be 200–400 billion. The mass of the Andromeda Galaxy is estimated to be 1.5×1012 solar masses, while the Milky Way is estimated to be 8.5×1011 solar masses. The Milky Way and Andromeda galaxies are expected to collide in 4.5 billion years, eventually merging to form a giant elliptical galaxy or perhaps a large disc galaxy.",
            "The Triangulum Galaxy is a spiral galaxy approximately 3 million light-years from Earth in the constellation Triangulum. The Triangulum Galaxy is the third-largest member of the Local Group of galaxies, behind the Milky Way and the Andromeda Galaxy. It is one of the most distant permanent objects that can be viewed with the naked eye. It is believed to be a satellite of the Andromeda Galaxy due to their interactions, velocities and proximity to one another in the night sky. ",
            "The Messier 64 Galaxy has a spectacular dark band of absorbing dust in front of the galaxy's bright nucleus. It is a spiral galaxy in the Coma Berenices constellation. The interstellar medium of Messier 64 comprises two counter-rotating disks which are approximately equal in mass. The inner disk contains the prominent dust lanes of the galaxy. The stellar population of the galaxy exhibits no measurable counter-rotation. Possible formation scenarios include a merger with a gas-rich satellite galaxy in a retrograde orbit, or the continued accretion of gas clouds from the intergalactic medium.",
            "Messier 81 is a spiral galaxy about 12 million light-years away in the constellation Ursa Major. Most of it's emission at infrared wavelengths originates from interstellar dust. This interstellar dust is found primarily within the galaxy's spiral arms, and it has been shown to be associated with star formation regions. The general explanation is that the hot, short-lived blue stars that are found within star formation regions are very effective at heating the dust and thus enhancing the infrared dust emission from these regions. Messier 81 has an active galactic nucleus; a compact region at the center of a galaxy that has a much higher than normal luminosity over at least some portion of the electromagnetic spectrum, with characteristics indicating that the excess luminosity is not produced by stars. A galaxy hosting an active galactic nucleus is called an active galaxy. The radiation from an active galactic nucleus is believed to be a result of accretion of matter by a supermassive black hole at the center of its host galaxy. active galactic nucleus's are the most luminous persistent sources of electromagnetic radiation in the Universe.",
            "Messier 82 is a starburst galaxy about 12 million light-years away in the constellation Ursa Major. A member of the M81 Group, it is about five times more luminous than the whole Milky Way and has a center one hundred times more luminous than our galaxy's center. The starburst activity is thought to have been triggered by interaction with neighboring galaxy M81. Studying M82, scientists discovered the brightest pulsar yet known, designated M82 X-2.",
            "Cosmos Redshift 7 is a high-redshift Lyman-alpha emitter galaxy (meaning CR7 is one of the oldest, most distant galaxies), in the constellation Sextans, about 12.9 billion light-years from Earth, reported to contain the first stars when the Universe was about 800 million years old. These stars to provided the chemical elements like oxygen, nitrogen, carbon, calcium and iron needed for the later formation of planets and life as it is known.",
            "The Large Magellanic Cloud is a satellite galaxy of the Milky Way. At a distance of 163,000 light-years, the LMC is the third-closest galaxy to the Milky Way, lying closer to the center. The LMC has a diameter of about 14,000 light-years and a mass of approximately 10 billion Sun masses, making it roughly 1/100 as massive as the Milky Way. The LMC is the fourth-largest galaxy in the Local Group, after the Andromeda Galaxy, the Milky Way, and the Triangulum Galaxy. The LMC contains a very prominent bar in its center, suggesting that it may have been a barred dwarf spiral galaxy before its spiral arms were disrupted, likely by the Milky Way's gravity."
    };

    public ImageAdapterGalaxy(Context c) {
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