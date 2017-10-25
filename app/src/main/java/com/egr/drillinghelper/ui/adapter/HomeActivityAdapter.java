package com.egr.drillinghelper.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.egr.drillinghelper.ui.fragment.FeedbackFragment;
import com.egr.drillinghelper.ui.fragment.HomeFragment;
import com.egr.drillinghelper.ui.fragment.MyFragment;
import com.egr.drillinghelper.ui.fragment.PartsFragment;
import com.egr.drillinghelper.ui.fragment.VideoPartFragment;

/**
 * author lzd
 * date 2017/9/26 17:51
 * 类描述：
 */

public class HomeActivityAdapter extends FragmentPagerAdapter {
    private Class[] fragments = {HomeFragment.class, VideoPartFragment.class, FeedbackFragment.class,
            MyFragment.class};

    public HomeActivityAdapter(FragmentManager fm) {
        super(fm);
    }

    HomeFragment homeFragment;

    public HomeFragment getHomeFragment(){
        return homeFragment;
    }
    @Override
    public Fragment getItem(int position) {
        try {
            if(position == 0){
                homeFragment= (HomeFragment) fragments[position].newInstance();
                return homeFragment;
            }else {
                return (Fragment) fragments[position].newInstance();
            }

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getCount() {
        return fragments.length;
    }
}
