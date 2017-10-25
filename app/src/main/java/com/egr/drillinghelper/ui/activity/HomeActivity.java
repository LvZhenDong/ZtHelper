package com.egr.drillinghelper.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.common.MyConstants;
import com.egr.drillinghelper.common.RxBusConstant;
import com.egr.drillinghelper.common.UserManager;
import com.egr.drillinghelper.contract.HomeContract;
import com.egr.drillinghelper.factory.APIServiceFactory;
import com.egr.drillinghelper.presenter.HomePresenterImpl;
import com.egr.drillinghelper.ui.adapter.HomeActivityAdapter;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;
import com.egr.drillinghelper.ui.widgets.BanSlideViewPager;
import com.egr.drillinghelper.ui.widgets.DialogHelper;
import com.egr.drillinghelper.utils.ApkUtils;
import com.egr.drillinghelper.utils.EgrRxBus;
import com.egr.drillinghelper.utils.PhoneUtils;
import com.egr.drillinghelper.utils.ToastUtils;
import com.shelwee.update.UpdateHelper;

import butterknife.BindView;
import butterknife.OnClick;
import cc.cloudist.acplibrary.ACProgressFlower;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

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
    private ACProgressFlower mDialog;

    View.OnClickListener homeMsgListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //点击消息按钮
            if (!isLogin()) return;
            baseStartActivity(MessageActivity.class);
        }
    };

    View.OnClickListener searchListener= new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            onSearchClick(homeAdapter.getHomeFragment().getCurrentItem());
        }
    };

    View.OnClickListener phoneListener=new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            if(TextUtils.isEmpty(phone)){
                mDialog.show();
                presenter.getContact();
            }else {
                PhoneUtils.callPhone(getActivity(), phone);
            }

        }
    };



    @Override
    public int returnLayoutID() {
        return R.layout.activity_home;
    }

    @Override
    public void TODO(Bundle savedInstanceState) {
        setSwipeBackEnabled(false);//设置不可右滑关闭
        setupActionBar(ContextCompat.getDrawable(this, R.drawable.bg_home_logo), false);
        setActionbarBackground(R.color.white);
        setActionBarLeftIcon(R.drawable.ic_home_msg_gray, homeMsgListener);
        setActionBarRightIcon(R.drawable.ic_home_search_gray, searchListener);

        mDialog = DialogHelper.openiOSPbDialog(this, getString(R.string.waiting));

        homeAdapter = new HomeActivityAdapter(getSupportFragmentManager());
        vpHome.setOffscreenPageLimit(homeAdapter.getCount());
        vpHome.setScrollEnable(false);
        vpHome.setAdapter(homeAdapter);
        vpHome.setCurrentItem(0, false);

        EgrRxBus.subscribe(this, String.class, new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                if (s.equals(RxBusConstant.UPDATE_MSG))
                    presenter.getNoReadMsg();
            }
        });

        checkVersion();
        presenter.getNoReadMsg();

    }

    private void checkVersion() {
        String url = APIServiceFactory.getBaseUrl() + MyConstants.API.Version + ApkUtils.getVersionCode(this);
        UpdateHelper updateHelper = new UpdateHelper.Builder(this)
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
        if (view.getId() != R.id.rb_home && !isLogin())return;
        switch (view.getId()) {
            case R.id.rb_home:
                vpHome.setCurrentItem(0);
                setActionBarTitleDrawable(R.drawable.bg_home_logo);
                setActionBarRightIcon(R.drawable.ic_home_search_gray, searchListener);
                setActionBarRightTextGone();
                setActionbarBackground(R.color.white);
                setActionBarTitleColor(R.color.black);
                changeLeftIcon(R.drawable.ic_home_msg_gray);
                break;
            case R.id.rb_parts:
                vpHome.setCurrentItem(1);
                setActionBarTitle(R.string.video_part);
//                setActionBarRightIcon(R.drawable.ic_home_search, searchListener);
                setActionBarRightTextGone();
                setActionBarRightIvGone();
                setActionbarBackground(R.color.white);
                setActionBarTitleColor(R.color.black);
                changeLeftIcon(R.drawable.ic_home_msg_gray);
                break;
            case R.id.rb_feedback:
                vpHome.setCurrentItem(2);
                setActionBarTitle(R.string.feedback);
//                setActionBarRightText(R.string.feedback_histroy, new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        //历史反馈
//                        baseStartActivity(FeedbackHistoryActivity.class);
//                    }
//                });
                setActionBarRightIcon(R.drawable.ic_phone_gray,phoneListener);
                setActionbarBackground(R.color.white);
                setActionBarTitleColor(R.color.black);
                changeLeftIcon(R.drawable.ic_home_msg_gray);
                break;
            case R.id.rb_my:
                vpHome.setCurrentItem(3);
                setActionBarTitle(R.string.my);
                setActionBarRightTextGone();
                setActionBarRightIvGone();
                setActionbarBackground(ContextCompat.getDrawable(this, R.drawable.bg_actionbar));
                setActionBarTitleColor(R.color.white);
                changeLeftIcon(R.drawable.ic_home_msg_white);
                break;
        }
    }

    private void onSearchClick(int type) {
        if(!isLogin())return;

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
        if (counts > 0)
            showRedDot();
        else
            hideRedDot();
    }

    @Override
    public void getContactSuccess(String phoneNum) {
        mDialog.dismiss();
        phone=phoneNum;
        PhoneUtils.callPhone(this, phoneNum);
    }

    @Override
    public void getContactError(String msg) {
        mDialog.dismiss();
    }

    String phone;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PhoneUtils.MY_PERMISSIONS_REQUEST_CALL_PHONE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the phone call
                    PhoneUtils.startPhoneIntent(this, phone);
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                break;
        }
    }

    private boolean isLogin() {
        if (!UserManager.getInstance().isLogined()) {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.putExtra(KEY_INTENT_BOOLEAN, true);
            startActivity(intent);
            finish();

            return false;
        }

        return true;
    }
    /**
     * 实现点击空白处，软键盘消失事件
     *
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            // 获得当前得到焦点的View，一般情况下就是EditText（特殊情况就是轨迹求或者实体案件会移动焦点）
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {
                hideSoftInput(v.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时没必要隐藏
     *
     * @param v
     * @param event
     * @return
     */
    private boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left
                    + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击EditText的事件，忽略它。
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditView上，和用户用轨迹球选择其他的焦点
        return false;
    }

    /**
     * 多种隐藏软件盘方法的其中一种
     *
     * @param token
     */
    private void hideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token,
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
