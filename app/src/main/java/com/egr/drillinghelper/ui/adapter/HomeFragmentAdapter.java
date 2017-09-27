package com.egr.drillinghelper.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.egr.drillinghelper.ui.fragment.FeedbackFragment;
import com.egr.drillinghelper.ui.fragment.HomeFragment;
import com.egr.drillinghelper.ui.fragment.InstructionsFragment;
import com.egr.drillinghelper.ui.fragment.KonwledgeFragment;
import com.egr.drillinghelper.ui.fragment.MyFragment;
import com.egr.drillinghelper.ui.fragment.PartsFragment;

/**
 * author lzd
 * date 2017/9/26 17:51
 * 类描述：
 */

public class HomeFragmentAdapter extends FragmentPagerAdapter {
    private Class[] fragments = {InstructionsFragment.class, KonwledgeFragment.class};

    public HomeFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        try {
            return (Fragment) fragments[position].newInstance();
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
