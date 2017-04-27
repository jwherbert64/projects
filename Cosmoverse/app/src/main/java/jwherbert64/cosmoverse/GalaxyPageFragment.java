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

public class GalaxyPageFragment extends Fragment {
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

        String p_a_str_a = "A galaxy is a collection of billions or trillions of stars among gas, dust and dark matter bound by gravitational attraction accelerating through the universe. Galaxies vary greatly in shape and size and there is no definitive model of how galaxies form. It is estimated there are between 200 billion to 2 trillion galaxies in the observable universe. Galaxies are classified based on their shape as either elliptical, spiral or irregular. Many galaxies are thought to have black holes at their active centers.";

        String p_a_str_b = "Galaxies are extremely far away from each other, so much that the unit of measurement to measure a galaxy compared to the empty space between is to the millionth. This space is filled";

        String p_b_str_a = "with a tenuous gas that has a density less than one atom per cubic meter. The majority of galaxies are gravitationally organized into groups and clusters surrounded by immense voids.";

        String p_b_str_b = "Our galaxy, the Milky Way is 100'000 light-years in diameter and approximately 100 billion stars. The Milky Way is a relatively small galaxy, but it would take the sun 226 million years to fully orbit it. Aside from dark matter, which allows galaxies to defy gravity and hold together. Their is another phenomenon applied to our universe; Dark energy is an unknown form of energy which is hypothesized to permeate all of the space, accelerating the expansion of the universe and isolating the galaxies.";

        if(page == 0) {
            tv_info_a.setText(p_a_str_a);
            tv_info_b.setText(p_a_str_b);

            String uri = "@drawable/shapes";
            int imageResource = getResources().getIdentifier(uri, null, getContext().getPackageName());
            Drawable res = getResources().getDrawable(imageResource);
            iv_b.setImageDrawable(res);

            tv_a.setText("");
            tv_b.setText("Diagram of the possible shapes of a galaxy");
            ll_a.setVisibility(LinearLayout.GONE);

            ll_p1.setBackgroundResource(R.drawable.page_active);
        }

        if(page == 1) {
            tv_info_a.setText(p_b_str_a);
            tv_info_b.setText(p_b_str_b);

            String uri = "@drawable/milky_way";
            int imageResource = getResources().getIdentifier(uri, null, getContext().getPackageName());
            Drawable res = getResources().getDrawable(imageResource);
            iv_a.setImageDrawable(res);

            tv_a.setText("Representation of how our Milky Way galaxy would look");
            tv_b.setText("");
            ll_b.setVisibility(LinearLayout.GONE);

            ll_p2.setBackgroundResource(R.drawable.page_active);
        }

        return view;
    }
}
