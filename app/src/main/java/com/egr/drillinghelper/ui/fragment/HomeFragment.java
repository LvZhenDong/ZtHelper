package com.egr.drillinghelper.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.bean.rxbus.HomeCurrent;
import com.egr.drillinghelper.ui.adapter.HomeFragmentAdapter;
import com.egr.drillinghelper.ui.base.BaseFragment;
import com.egr.drillinghelper.ui.widgets.BanSlideViewPager;
import com.egr.drillinghelper.ui.widgets.TabEntity;
import com.egr.drillinghelper.utils.EgrRxBus;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * author lzd
 * date 2017/9/26 18:10
 * 类描述：
 */

public class HomeFragment extends BaseFragment {

    @BindView(R.id.vp_home)
    BanSlideViewPager vpHome;
    @BindView(R.id.tl)
    CommonTabLayout mTl;

    HomeFragmentAdapter mAdapter;

    @Override
    public int returnLayoutID() {
        return R.layout.fragment_home;
    }

    @Override
    public void TODO(View view, Bundle savedInstanceState) {
        mAdapter = new HomeFragmentAdapter(getChildFragmentManager());
        vpHome.setOffscreenPageLimit(mAdapter.getCount());
        vpHome.setScrollEnable(false);
        vpHome.setAdapter(mAdapter);

        ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
        mTabEntities.add(new TabEntity(getString(R.string.explain)));
        mTabEntities.add(new TabEntity(getString(R.string.ask_knowledge)));
        mTabEntities.add(new TabEntity(getString(R.string.parts)));
        mTl.setTabData(mTabEntities);
        mTl.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                vpHome.setCurrentItem(position);
                EgrRxBus.post(new HomeCurrent(position));
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

}
