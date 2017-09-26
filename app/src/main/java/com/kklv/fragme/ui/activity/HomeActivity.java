package com.kklv.fragme.ui.activity;

import android.os.Bundle;
import android.widget.RadioGroup;

import com.kklv.fragme.R;
import com.kklv.fragme.contract.HomeContract;
import com.kklv.fragme.presenter.HomePresenterImpl;
import com.kklv.fragme.ui.adapter.HomeAdapter;
import com.kklv.fragme.ui.base.BaseMVPActivity;
import com.kklv.fragme.ui.widgets.BanSlideViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;

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
