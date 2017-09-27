package com.egr.drillinghelper.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.egr.drillinghelper.ui.fragment.FeedbackHistoryRepliedFragment;
import com.egr.drillinghelper.ui.fragment.FeedbackHistoryWaitFragment;
import com.egr.drillinghelper.ui.fragment.InstructionsFragment;
import com.egr.drillinghelper.ui.fragment.KonwledgeFragment;

/**
 * author lzd
 * date 2017/9/26 17:51
 * 类描述：
 */

public class FeedbackHistoryFragmentAdapter extends FragmentPagerAdapter {
    private Class[] fragments = {FeedbackHistoryWaitFragment.class, FeedbackHistoryRepliedFragment.class};

    public FeedbackHistoryFragmentAdapter(FragmentManager fm) {
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
