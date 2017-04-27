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

public class UniversePageFragment extends Fragment {
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

        String p_a_str_a = "The observable universe is an infinite expansion of space and time created by the Big Bang 13 billion years ago. It is believed to be at least 10 billion light-years in diameter and includes all kinds";

        String p_a_str_b = "of physical matter and energy, the planets, stars, galaxies, and all the contents of space. Initially, all the universe's matter was concentrated in a very small point and permeated only by energy. The universe expanded outwards rapidly and some of this energy congealed into particles, which assembled into light atoms like hydrogen and helium. These atoms clumped first into galaxies, then stars, inside whose fiery furnaces all the other elements were forged. The expansion of the universe isn't";

        String p_b_str_a = "an explosion into empty space, rather the expansion of space itself. The force of the big bang's explosion should theoretically decrease and then eventually become an imploding force, but the universe's expansion seems";

        String p_b_str_b = "to be accelerating. This revelation means the matter of the universe defies the laws of physics, and galaxies shouldn't have enough gravity to hold. Something else is affecting the universe's structure, an unidentified type of matter, dark matter. Dark matter does not emit or interact with electromagnetic radiation such as light and is thus invisible to the entire electromagnetic spectrum. It's only directly observed by its effects on matter and through gravitational lensing on other objects.";

        if(page == 0) {
            tv_info_a.setText(p_a_str_a);
            tv_info_b.setText(p_a_str_b);

            String uri = "@drawable/expand";
            int imageResource = getResources().getIdentifier(uri, null, getContext().getPackageName());
            Drawable res = getResources().getDrawable(imageResource);
            iv_a.setImageDrawable(res);

            tv_a.setText("Depiction of the universes expansion after the big bang");
            tv_b.setText("");
            ll_b.setVisibility(LinearLayout.GONE);

            ll_p1.setBackgroundResource(R.drawable.page_active);
        }

        if(page == 1) {
            tv_info_a.setText(p_b_str_a);
            tv_info_b.setText(p_b_str_b);

            String uri = "@drawable/light";
            int imageResource = getResources().getIdentifier(uri, null, getContext().getPackageName());
            Drawable res = getResources().getDrawable(imageResource);
            iv_a.setImageDrawable(res);

            tv_a.setText("Light left over from the big bang 13 billion years ago");
            tv_b.setText("");
            ll_b.setVisibility(LinearLayout.GONE);

            ll_p2.setBackgroundResource(R.drawable.page_active);
        }

        return view;
    }
}
