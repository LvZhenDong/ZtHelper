package com.egr.drillinghelper.ui.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.contract.HomeContract;
import com.egr.drillinghelper.presenter.HomePresenterImpl;
import com.egr.drillinghelper.ui.adapter.HomeActivityAdapter;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;
import com.egr.drillinghelper.ui.widgets.BanSlideViewPager;

import butterknife.BindView;
import butterknife.OnClick;

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
    @BindView(R.id.rb_home)
    RadioButton rbHome;
    @BindView(R.id.rb_parts)
    RadioButton rbParts;
    @BindView(R.id.rb_feedback)
    RadioButton rbFeedback;
    @BindView(R.id.rb_my)
    RadioButton rbMy;
    private HomeActivityAdapter homeAdapter;

    @Override
    public int returnLayoutID() {
        return R.layout.activity_home;
    }

    @Override
    public void TODO(Bundle savedInstanceState) {
        setSwipeBackEnabled(false);//设置不可右滑关闭
        setupActionBar(ContextCompat.getDrawable(this,R.drawable.bg_home_logo), false);
        setActionBarTitleColor(R.color.white);
        setActionBarLeftIcon(R.drawable.ic_home_msg, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击消息按钮
                baseStartActivity(MessageActivity.class);
            }
        });
        setActionbarBackground(ContextCompat.getDrawable(this,R.drawable.bg_actionbar));
        setActionBarRightIcon(R.drawable.ic_home_search, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击搜索按钮
            }
        });
        homeAdapter = new HomeActivityAdapter(getSupportFragmentManager());
        vpHome.setOffscreenPageLimit(homeAdapter.getCount());
        vpHome.setScrollEnable(false);
        vpHome.setAdapter(homeAdapter);
        vpHome.setCurrentItem(0, false);
    }

    @Override
    public HomePresenterImpl createPresenter() {
        return new HomePresenterImpl();
    }

    @OnClick({R.id.rb_home, R.id.rb_parts, R.id.rb_feedback, R.id.rb_my})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rb_home:
                vpHome.setCurrentItem(0);
                setActionBarTitleDrawable(R.drawable.bg_home_logo);
                setActionBarRightIcon(R.drawable.ic_home_search, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                break;
            case R.id.rb_parts:
                vpHome.setCurrentItem(1);
                setActionBarTitle(R.string.parts);
                setActionBarRightIcon(R.drawable.ic_home_search, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                break;
            case R.id.rb_feedback:
                vpHome.setCurrentItem(2);
                setActionBarTitle(R.string.feedback);
                setActionBarRightText(R.string.feedback_histroy, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //历史反馈
                        baseStartActivity(FeedbackHistoryActivity.class);
                    }
                });
                break;
            case R.id.rb_my:
                vpHome.setCurrentItem(3);
                setActionBarTitle(R.string.my);
                setActionBarRightTextGone();
                setActionBarRightIvGone();
                break;
        }
    }
}
