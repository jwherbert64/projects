package jwherbert64.cosmoverse;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by James on 14/02/2017.
 */

public class PlanetPageFragment extends Fragment {
    TextView tv_info_a, tv_info_b;

    LinearLayout ll_a, ll_b;
    ImageView iv_a, iv_b;
    TextView tv_a, tv_b;

    LinearLayout ll_p1, ll_p2, ll_p3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.page_fragment_layout, container, false);

        tv_info_a = (TextView) view.findViewById(R.id.tv_info_a);
        tv_info_b = (TextView) view.findViewById(R.id.tv_info_b);

        ll_a = (LinearLayout) view.findViewById(R.id.ll_a);
        iv_a = (ImageView) view.findViewById(R.id.iv_a);
        tv_a = (TextView) view.findViewById(R.id.tv_a);
        ll_b = (LinearLayout) view.findViewById(R.id.ll_b);
        iv_b = (ImageView) view.findViewById(R.id.iv_b);
        tv_b = (TextView) view.findViewById(R.id.tv_b);

        Typeface typeFace = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Regular.ttf");
        Typeface typeFaceBold = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Bold.ttf");

        tv_info_a.setTypeface(typeFace);
        tv_info_b.setTypeface(typeFace);
        tv_a.setTypeface(typeFaceBold);
        tv_b.setTypeface(typeFaceBold);

        ll_p1 = (LinearLayout) view.findViewById(R.id.ll_p1);
        ll_p2 = (LinearLayout) view.findViewById(R.id.ll_p2);
        ll_p3 = (LinearLayout) view.findViewById(R.id.ll_p3);

        Bundle bundle = getArguments();
        int page = bundle.getInt("count");

        String p_a_str_a = "A planets a celestial object which orbits a star made from the debris of the solar nebula. The heavy weight of a star gives it a gravitational pull on the matter, grouping together mass until it's rounded by its own gravity to form planets. This causes planets to form their own celestial bodies orbiting them called moons. During a planet's birth, it's beaten by comets and asteroids, battering it with matter such as rock, metal, ice and dust adding to the mass of the planet. This process can often annihilate";

        String p_a_str_b = "the planet due to the size of these objects. When the planet is large enough, it's iron core creates a magnetic field around the planet, pushing these objects away. A planet's gravity and temperature";

        String p_b_str_a = "is determined by its size and distance from the sun. These factors alter a planet's atmosphere, which is responsible for absorbing and scattering ultraviolet radiation and burning up meteors. When a star forms it spreads a huge amount of matter which planets are made up of. Heavier materials remain closer due to the star's gravity thus planets further out are lighter and made more of gaseous materials then solids. Similarly, the closer a planet is to its star the higher";

        String p_b_str_b = "it's temperature, varying them from volcanic molten planets to drown frozen planets. Water is brought to planets by comets, mostly made of ice, which determines the planets dryness.";

        String p_c_str_a = "Planets can slowly move inwards or outwards on their orbit around a star, meaning rock planets can be found further out and gas planets can be found at closer in often mutating their attributes.";

        String p_c_str_b = "Moons have similar attributes then planets and are formed from broken up objects that orbit a planet clumping together. A planet can have many moons and the size and distance of a moon from its planet can alter dramatically. Earth is the only known planet with life so far, and the formulae for a planet to generate life is infinitely complex. The drastic changes in gravity, temperature, radiation, materials and atmosphere of a planet make it almost impossible.";

        if(page == 0) {
            tv_info_a.setText(p_a_str_a);
            tv_info_b.setText(p_a_str_b);

            String uri = "@drawable/planet_core";
            int imageResource = getResources().getIdentifier(uri, null, getContext().getPackageName());
            Drawable res = getResources().getDrawable(imageResource);
            iv_b.setImageDrawable(res);

            tv_a.setText("");
            tv_b.setText("Diagram of a planet from its core to its shell");
            ll_a.setVisibility(LinearLayout.GONE);

            ll_p1.setBackgroundResource(R.drawable.page_active);
        }

        if(page == 1) {
            tv_info_a.setText(p_b_str_a);
            tv_info_b.setText(p_b_str_b);

            String uri = "@drawable/comet";
            int imageResource = getResources().getIdentifier(uri, null, getContext().getPackageName());
            Drawable res = getResources().getDrawable(imageResource);
            iv_b.setImageDrawable(res);

            tv_a.setText("");
            tv_b.setText("Photograph of a comet made up of ice in space");
            ll_a.setVisibility(LinearLayout.GONE);

            ll_p2.setBackgroundResource(R.drawable.page_active);
        }

        if(page == 2) {
            tv_info_a.setText(p_c_str_a);
            tv_info_b.setText(p_c_str_b);

            String uri = "@drawable/jupiter";
            int imageResource = getResources().getIdentifier(uri, null, getContext().getPackageName());
            Drawable res = getResources().getDrawable(imageResource);
            iv_a.setImageDrawable(res);

            tv_a.setText("Photograph of the gas giant Jupiter in space");
            tv_b.setText("");
            ll_b.setVisibility(LinearLayout.GONE);

            ll_p3.setBackgroundResource(R.drawable.page_active);
        }

        return view;
    }
}
