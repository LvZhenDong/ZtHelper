package com.egr.drillinghelper.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.common.MyConstants;
import com.egr.drillinghelper.common.RxBusConstant;
import com.egr.drillinghelper.contract.HomeContract;
import com.egr.drillinghelper.factory.APIServiceFactory;
import com.egr.drillinghelper.presenter.HomePresenterImpl;
import com.egr.drillinghelper.ui.adapter.HomeActivityAdapter;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;
import com.egr.drillinghelper.ui.widgets.BanSlideViewPager;
import com.egr.drillinghelper.utils.ApkUtils;
import com.egr.drillinghelper.utils.EgrRxBus;
import com.egr.drillinghelper.utils.ToastUtils;
import com.shelwee.update.UpdateHelper;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

import static com.egr.drillinghelper.ui.activity.SearchActivity.SEARCH_TYPE_EXPLAIN;
import static com.egr.drillinghelper.ui.activity.SearchActivity.SEARCH_TYPE_KNOWLEDGE;
import static com.egr.drillinghelper.ui.activity.SearchActivity.SEARCH_TYPE_PARTS;

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
    private long mExitTime = 0;

    @Override
    public int returnLayoutID() {
        return R.layout.activity_home;
    }

    @Override
    public void TODO(Bundle savedInstanceState) {
        setSwipeBackEnabled(false);//设置不可右滑关闭
        setupActionBar(ContextCompat.getDrawable(this, R.drawable.bg_home_logo), false);
        setActionBarTitleColor(R.color.white);
        setActionBarLeftIcon(R.drawable.ic_home_msg, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击消息按钮
                baseStartActivity(MessageActivity.class);
            }
        });
        setActionbarBackground(ContextCompat.getDrawable(this, R.drawable.bg_actionbar));
        setActionBarRightIcon(R.drawable.ic_home_search, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击搜索按钮
                onSearchClick(homeAdapter.getHomeFragment().isExplain()
                        ? SEARCH_TYPE_EXPLAIN : SEARCH_TYPE_KNOWLEDGE);
            }
        });
        homeAdapter = new HomeActivityAdapter(getSupportFragmentManager());
        vpHome.setOffscreenPageLimit(homeAdapter.getCount());
        vpHome.setScrollEnable(false);
        vpHome.setAdapter(homeAdapter);
        vpHome.setCurrentItem(0, false);

        EgrRxBus.subscribe(this, String.class, new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                if (s.equals(RxBusConstant.UPDATE_MSG_NO_READ))
                    presenter.getNoReadMsg();
            }
        });

        checkVersion();
        presenter.getNoReadMsg();

    }

    private void checkVersion(){
        String url = APIServiceFactory.getBaseUrl()+ MyConstants.API.Version+ ApkUtils.getVersionCode(this);
        UpdateHelper updateHelper=new UpdateHelper.Builder(this)
                .checkUrl(url)
                .isHintNewVersion(false)
                .build();
        updateHelper.check();
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

                        onSearchClick(homeAdapter.getHomeFragment().isExplain()
                                ? SEARCH_TYPE_EXPLAIN : SEARCH_TYPE_KNOWLEDGE);
                    }
                });
                break;
            case R.id.rb_parts:
                vpHome.setCurrentItem(1);
                setActionBarTitle(R.string.parts);
                setActionBarRightIcon(R.drawable.ic_home_search, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onSearchClick(SEARCH_TYPE_PARTS);
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

    private void onSearchClick(int type) {
        Intent intent = new Intent(this, SearchActivity.class);
        intent.putExtra(KEY_INTENT, type);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {//如果两次按键时间间隔大于2000毫秒，则不退出
            ToastUtils.show(this, getString(R.string.exit_app_pressback_again));
            mExitTime = System.currentTimeMillis();// 更新mExitTime
        } else {// 否则退出程序
            finish();
            System.exit(0);
        }
    }

    @Override
    public void getNoReadMsgSuccess(int counts) {
        if(counts>0)
            showRedDot();
        else
            hideRedDot();
    }

}
