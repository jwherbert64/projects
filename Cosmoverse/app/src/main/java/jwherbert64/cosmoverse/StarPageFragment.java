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

public class StarPageFragment extends Fragment {
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

        String p_a_str_a = "A star is a luminous ball of gas made up of mostly hydrogen and helium, held together by its own gravity. Nuclear fusion reactions accurate within its core producing light and heat. A star with a large enough mass collapses in upon itself then explodes in a supernova. It's believed this death of a star is the cause of black holes. Stars shine by burning hydrogen into helium in their cores, and later in their lives create heavier elements up until it has an iron core. Initially, clouds of gas and dust are disturbed by the";

        String p_a_str_b = "gravity of a nearby phenomena. The disturbance causes clumps to form and draw gas inwards. The collapsing clump begins to rotate and flatten into a disc of gas and dust. The disc rotates faster";

        String p_b_str_a = "and faster pulling more material inwards creating a hot, dense core called a protostar. When the protostar becomes hot enough hydrogen atoms begin to fuse, producing helium and energy. After millions of";

        String p_b_str_b = "years a bipolar flow erupts from the protostar and blasts away remaining gas and dust. These clouds of gas and dust in outer space responsible for the birth of stars are called nebulae. A nebula is formed when portions of the interstellar medium undergo gravitational collapse. Mutual gravitational attraction causes matter to clump together forming regions of great density. Stars form in this collapsing material and it's ultraviolet ionizing radiation causes the surrounding gas to become visible.";

        String p_c_str_a = "A black hole is a place in space where gravity pulls so much that even light can not get out. The gravity is so strong because the matter has been squeezed into a tiny point, distorting space itself.";

        String p_c_str_b = "Special technology have recorded stars around black holes acting strange, that's how we know of their existence. Black holes are formed when the center of a very large star collapses, causing a supernova and blasting parts of the star into space. Gamma ray bursts are extremely energetic explosions that have been observed in distant galaxies. They are the brightest electromagnetic events known to occur in the universe. They are theorized to be created when a black hole forms.";

        if(page == 0) {
            tv_info_a.setText(p_a_str_a);
            tv_info_b.setText(p_a_str_b);

            String uri = "@drawable/star_core";
            int imageResource = getResources().getIdentifier(uri, null, getContext().getPackageName());
            Drawable res = getResources().getDrawable(imageResource);
            iv_b.setImageDrawable(res);

            tv_a.setText("");
            tv_b.setText("Diagram of a star from its core to its shell");
            ll_a.setVisibility(LinearLayout.GONE);

            ll_p1.setBackgroundResource(R.drawable.page_active);
        }

        if(page == 1) {
            tv_info_a.setText(p_b_str_a);
            tv_info_b.setText(p_b_str_b);

            String uri = "@drawable/nebula";
            int imageResource = getResources().getIdentifier(uri, null, getContext().getPackageName());
            Drawable res = getResources().getDrawable(imageResource);
            iv_a.setImageDrawable(res);

            tv_a.setText("Photograph of a gas and dust nebula cloud");
            tv_b.setText("");
            ll_b.setVisibility(LinearLayout.GONE);

            ll_p2.setBackgroundResource(R.drawable.page_active);
        }

        if(page == 2) {
            tv_info_a.setText(p_c_str_a);
            tv_info_b.setText(p_c_str_b);

            String uri = "@drawable/black_hole";
            int imageResource = getResources().getIdentifier(uri, null, getContext().getPackageName());
            Drawable res = getResources().getDrawable(imageResource);
            iv_a.setImageDrawable(res);

            tv_a.setText("Representation of what a black hole might look like");
            tv_b.setText("");
            ll_b.setVisibility(LinearLayout.GONE);

            ll_p3.setBackgroundResource(R.drawable.page_active);
        }

        return view;
    }
}
