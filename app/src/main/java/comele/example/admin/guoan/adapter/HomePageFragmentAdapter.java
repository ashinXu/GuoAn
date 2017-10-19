package comele.example.admin.guoan.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import comele.example.admin.guoan.fragment.FeedFragment;
import comele.example.admin.guoan.fragment.LifeFragment;
import comele.example.admin.guoan.fragment.MergeFragment;
import comele.example.admin.guoan.fragment.TopLineFragment;

/**
 * Created by admin on 2017/9/25.
 */
public class HomePageFragmentAdapter extends FragmentPagerAdapter {

    private List<String> mTitles;

    public HomePageFragmentAdapter(FragmentManager fm, List<String> titles) {
        super(fm);
        this.mTitles = titles;
    }


    @Override
    public int getCount() {
        return mTitles.size();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return TopLineFragment.newInstance(0);

            case 1:
                return MergeFragment.newInstance(1);

            case 2:
                return LifeFragment.newInstance(2);

            case 3:
                return FeedFragment.newInstance(3);
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
}
