package com.egr.drillinghelper.ui.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.utils.DensityUtils;
import com.michaelflisar.rxbus2.rx.RxDisposableManager;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends RxAppCompatActivity {
    public final static String KEY_INTENT = "intent data";
    public final static String KEY_INTENT_BOOLEAN = "intent data boolean";

    protected ActionBar actionBar;
    protected TextView title, rightTv;
    protected ImageView leftIv, rightIv, rightIv2, leftIv2;
    ImageView mRedDotIV;
    ViewGroup actionbarLayout;
    protected Unbinder unbinder;

    public abstract int returnLayoutID();

    public abstract void TODO(Bundle savedInstanceState);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);  //强制竖屏
        setContentView(returnLayoutID());
        actionBar = getSupportActionBar();
        unbinder = ButterKnife.bind(this);
        setOthers(savedInstanceState);
    }

    String mUmengAnalyze;

    public void setUmengAnalyze(String str) {
        this.mUmengAnalyze = str;
    }

    public void setUmengAnalyze(int strId){
        this.mUmengAnalyze=getString(strId);
    }



    @Override
    protected void onResume() {
        super.onResume();
        if(TextUtils.isEmpty(mUmengAnalyze))return;
        MobclickAgent.onPageStart(mUmengAnalyze);
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(TextUtils.isEmpty(mUmengAnalyze))return;
        MobclickAgent.onPageEnd(mUmengAnalyze);
        MobclickAgent.onPause(this);
    }

    protected void setOthers(Bundle savedInstanceState) {
        TODO(savedInstanceState);
    }

    /**
     * set actionbar title user resid
     *
     * @param actionBarTitleResId
     */
    protected void setupActionBar(int actionBarTitleResId, boolean displayHomeAsUpEnabled) {
        if (actionBar == null) {
            return;
        }
        loadView();
        title.setText(actionBarTitleResId);
        rightIv.setVisibility(View.GONE);
        if (displayHomeAsUpEnabled) {
            leftIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        } else {
            leftIv.setVisibility(View.GONE);
        }
    }

    /**
     * set actionbar title user resid
     *
     * @param actionBarTitleResId
     */
    protected void setupActionBar(int actionBarTitleResId, boolean displayHomeAsUpEnabled, View
            .OnClickListener listener) {
        if (actionBar == null) {
            return;
        }
        loadView();
        title.setText(actionBarTitleResId);
        rightIv.setVisibility(View.GONE);
        if (displayHomeAsUpEnabled) {
            leftIv.setOnClickListener(listener);
        } else {
            leftIv.setVisibility(View.GONE);
        }
    }

    /**
     * set actionbar background
     *
     * @param bgResID
     */
    protected void setActionbarBackground(int bgResID) {
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(bgResID)));
    }

    protected void setActionbarBackground(Drawable drawable) {
        actionBar.setBackgroundDrawable(drawable);
    }

    protected void setupActionBar(Drawable drawable, boolean displayHomeAsUpEnabled) {
        if (actionBar == null) {
            return;
        }
        loadView();
        title.setBackground(drawable);
        rightIv.setVisibility(View.GONE);
        if (displayHomeAsUpEnabled) {
            leftIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        } else {
            leftIv.setVisibility(View.GONE);
        }
    }

    /**
     * set actionbar title user resid
     *
     * @param actionBarTitle
     */
    protected void setupActionBar(String actionBarTitle, boolean displayHomeAsUpEnabled) {
        if (actionBar == null) {
            return;
        }
        loadView();
        title.setText(actionBarTitle);
        rightIv.setVisibility(View.GONE);
        if (displayHomeAsUpEnabled) {
            leftIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        } else {
            leftIv.setVisibility(View.GONE);
        }
    }

    /**
     * set actionbar title user resid
     *
     * @param actionBarTitle
     */
    protected void setupActionBar(String actionBarTitle, int rightRes, boolean
            displayHomeAsUpEnabled, View.OnClickListener onClickListener) {
        if (actionBar == null) {
            return;
        }
        loadView();
        title.setText(actionBarTitle);
        rightIv.setImageResource(rightRes);
        rightIv.setVisibility(View.VISIBLE);
        rightIv.setOnClickListener(onClickListener);
        if (displayHomeAsUpEnabled) {
            leftIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        } else {
            leftIv.setVisibility(View.GONE);
        }
    }

    /**
     * set actionbar title user resid
     *
     * @param actionBarTitleResId
     */
    protected void setupActionBar(int actionBarTitleResId, int Color, boolean
            displayHomeAsUpEnabled) {
        if (actionBar == null) {
            return;
        }
        loadView();
        title.setText(actionBarTitleResId);
        rightIv.setVisibility(View.GONE);
        if (displayHomeAsUpEnabled) {
            leftIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        } else {
            leftIv.setVisibility(View.GONE);
        }
    }

    /**
     * set actionbar title user resid
     *
     * @param actionBarTitleResId
     */
    protected void setupActionBar(int actionBarTitleResId) {
        String text = getString(actionBarTitleResId);
        setupActionBar(text);
    }

    protected void setupActionBar(String text){
        if (actionBar == null) {
            return;
        }
        loadView();
        title.setText(text);
        rightIv.setVisibility(View.GONE);
        leftIv.setVisibility(View.GONE);
    }

    private void loadView() {
        actionBar.setElevation(0);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(ActionBar.LayoutParams
                .MATCH_PARENT,
                ActionBar.LayoutParams.MATCH_PARENT);
        layoutParams.gravity = Gravity.CENTER_HORIZONTAL | Gravity.CENTER_HORIZONTAL;
        actionbarLayout = (ViewGroup) LayoutInflater.from(this).inflate(R.layout
                .view_cusactionbar, null);
        actionBar.setCustomView(actionbarLayout, layoutParams);
        Toolbar parent = (Toolbar) actionbarLayout.getParent();
        parent.setContentInsetsAbsolute(0, 0);
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color
                .colorPrimary)));
        title = (TextView) actionbarLayout.findViewById(R.id.anctionbar_title);
        rightTv = (TextView) actionbarLayout.findViewById(R.id.anctionbar_right_tv);
        rightIv = (ImageView) actionbarLayout.findViewById(R.id.anctionbar_right_ic);
        rightIv2 = (ImageView) actionbarLayout.findViewById(R.id.anctionbar_right_ic2);
        leftIv = (ImageView) actionbarLayout.findViewById(R.id.anctionbar_left_arrow);
        leftIv2 = (ImageView) actionbarLayout.findViewById(R.id.anctionbar_left_close);
    }

    /**
     * set actionbar leftIv iamge resource
     *
     * @param leftResId
     * @param listener
     */
    public void setActionBarLeftIcon(int leftResId, View.OnClickListener listener) {
        if (leftIv == null)
            throw new RuntimeException("setupAction must first call!!!");
        leftIv.setVisibility(View.VISIBLE);
        leftIv.setImageResource(leftResId);
        if (listener != null)
            leftIv.setOnClickListener(listener);
    }

    public void changeLeftIcon(int resId){
        if (leftIv == null)
            throw new RuntimeException("setupAction must first call!!!");
        leftIv.setVisibility(View.VISIBLE);
        leftIv.setImageResource(resId);
    }

    public void setActionBarLeft2Icon(View.OnClickListener listener) {
        setActionBarLeft2Icon(0, listener);
    }

    public void setActionBarLeft2Icon(int resId, View.OnClickListener listener) {
        if (leftIv2 == null)
            throw new RuntimeException("setupAction must first call!!!");
        leftIv2.setVisibility(View.VISIBLE);
        if (resId != 0)
            leftIv2.setImageResource(resId);
        if (listener != null)
            leftIv2.setOnClickListener(listener);
    }

    /**
     * set actionbar rightIv text resource and listener
     *
     * @param rightResId
     * @param listener
     */
    public void setActionBarRightText(int rightResId, View.OnClickListener listener) {
        if (rightTv == null)
            throw new RuntimeException("setupAction must first call!!!");
        rightTv.setVisibility(View.VISIBLE);
        rightTv.setText(rightResId);
        if (listener != null)
            rightTv.setOnClickListener(listener);
        setActionBarRightIvGone();
    }

    /**
     * set actionbar right text invisibile
     */
    public void setActionBarRightTextGone() {
        if (rightTv == null)
            throw new RuntimeException("setupAction must first call!!!");
        rightTv.setVisibility(View.GONE);
    }

    public void setActionBarRightIvGone() {
        if (rightTv == null)
            throw new RuntimeException("setupAction must first call!!!");
        rightIv.setVisibility(View.GONE);
    }

    /**
     * set actionbar second right img resource and listener
     *
     * @param rightResId
     * @param listener
     */
    public void setActionBarRightIcon(int rightResId, View.OnClickListener listener) {
        if (rightIv == null)
            throw new RuntimeException("setupAction must first call!!!");
        rightIv.setVisibility(View.VISIBLE);
        rightIv.setImageResource(rightResId);
        if (listener != null)
            rightIv.setOnClickListener(listener);
        setActionBarRightTextGone();
    }

    public void setActionBarRightIconHint() {
        if (rightIv == null)
            throw new RuntimeException("setupAction must first call!!!");
        rightIv.setVisibility(View.GONE);
    }

    /**
     * set actionbar right icon enable
     *
     * @param enable
     */
    public void setActionBarRightIconEnable(boolean enable) {
        if (rightIv == null)
            throw new RuntimeException("setupAction must first call!!!");
        rightIv.setEnabled(enable);
    }

    /**
     * set actionbar second right img resource and listener
     *
     * @param rightResId
     * @param listener
     */
    public void setActionBarRightIcon2(int rightResId, View.OnClickListener listener) {
        if (rightIv2 == null)
            throw new RuntimeException("setupAction must first call!!!");
        rightIv2.setVisibility(View.VISIBLE);
        rightIv2.setImageResource(rightResId);
        if (listener != null)
            rightIv2.setOnClickListener(listener);
    }

    /**
     * set actionbar title
     *
     * @param titleStr
     */
    protected void setActionBarTitle(String titleStr) {
        if (title == null)
            throw new RuntimeException("setupAction must first call!!!");
        title.setText(titleStr);
        title.setBackground(null);
    }

    protected void setActionBarTitle(int strId) {
        setActionBarTitle(getString(strId));
    }


    protected void setActionBarTitleDrawable(int drawableId) {
        if (title == null)
            throw new RuntimeException("setupAction must first call!!!");
        title.setBackground(ContextCompat.getDrawable(this, drawableId));
        title.setText("");
    }

    /**
     * set actionbar title
     *
     * @param colorResId
     */
    protected void setActionBarTitleColor(int colorResId) {
        if (title == null)
            throw new RuntimeException("setupAction must first call!!!");
        title.setTextColor(getResources().getColor(colorResId));
    }

    /**
     * get context
     *
     * @return
     */
    public Context getContext() {
        return this;
    }

    /**
     * get activity
     *
     * @return
     */
    public Activity getActivity() {
        return this;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        RxDisposableManager.unsubscribe(this);
    }

    /**
     * show SnackBar
     *
     * @param view
     * @param text
     */
    public void showSnackBar(View view, String text) {
        Snackbar.make(view, text, Snackbar.LENGTH_SHORT).show();
    }

    public void baseStartActivity(Class cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

    public void baseStartActivity(Class cls, Bundle data) {
        Intent intent = new Intent(this, cls);
        intent.putExtras(data);
        startActivity(intent);
    }

    /**
     * 设置消息红点提示的位置
     */
    private void setRedDotLocation() {
        int width = leftIv.getWidth();
        int height = leftIv.getHeight();

        mRedDotIV.setX(width / 2 + DensityUtils.dp2px(this, 2));
        mRedDotIV.setY(height / 4);
    }


    public void showRedDot() {
        if (mRedDotIV == null) {
            mRedDotIV = new ImageView(this);
            actionbarLayout.addView(mRedDotIV);
        }
        mRedDotIV.setVisibility(View.VISIBLE);
        setRedDotLocation();
        mRedDotIV.setImageResource(R.drawable.shape_oval_red);
    }


    public void hideRedDot() {
        if (mRedDotIV != null) {
            mRedDotIV.setVisibility(View.GONE);
        }
    }

}
