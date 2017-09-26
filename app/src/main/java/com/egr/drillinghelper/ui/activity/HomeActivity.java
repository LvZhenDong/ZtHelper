package com.egr.drillinghelper.ui.activity;

import android.os.Bundle;
import android.widget.RadioGroup;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.contract.HomeContract;
import com.egr.drillinghelper.presenter.HomePresenterImpl;
import com.egr.drillinghelper.ui.adapter.HomeAdapter;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;
import com.egr.drillinghelper.ui.widgets.BanSlideViewPager;

import butterknife.BindView;

/**
 * author lzd
 * date 2017/9/26 16:39
 * 类描述：首页
 */

public class HomeActivity extends BaseMVPActivity<HomeContract.View,
        HomePresenterImpl> implements HomeContract.View {

    @BindView(R.id.rg_home)
    RadioGroup rgHome;
    @BindView(R.id.vp_home)
    BanSlideViewPager vpHome;
    private HomeAdapter homeAdapter;

    @Override
    public int returnLayoutID() {
        return R.layout.activity_home;
    }

    @Override
    public void TODO(Bundle savedInstanceState) {
        setSwipeBackEnabled(false);//设置不可右滑关闭
        setupActionBar("首页", false);
        homeAdapter = new HomeAdapter(getSupportFragmentManager());
        vpHome.setOffscreenPageLimit(homeAdapter.getCount());
        vpHome.setScrollEnable(false);
        vpHome.setAdapter(homeAdapter);
        vpHome.setCurrentItem(0,false);
    }

    @Override
    public HomePresenterImpl createPresenter() {
        return new HomePresenterImpl();
    }

}
