package app.integro.dioceseofbangalore.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import app.integro.dioceseofbangalore.fragments.FragmentHome;
import app.integro.dioceseofbangalore.fragments.NewsFragment;
import app.integro.dioceseofbangalore.fragments.NotificationsFragment;
import app.integro.dioceseofbangalore.fragments.WebSiteFragment;

public class SliderAdapter extends FragmentPagerAdapter {

    private boolean flag = false;
    public SliderAdapter(FragmentManager fm, boolean flag) {
        super(fm);
        this.flag = flag;
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        if (position == 0) {
            fragment = new FragmentHome();
        }
        if (position == 1) {
            fragment = new NewsFragment();
        }
        if (position == 2) {
            fragment = new NotificationsFragment();
        }
        if (position == 3) {
            fragment = new WebSiteFragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
