package jwherbert64.cosmoverse;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by James on 14/02/2017.
 */

public class StarSwipeAdapter extends FragmentStatePagerAdapter {
    public StarSwipeAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = new StarPageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("count", i);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
