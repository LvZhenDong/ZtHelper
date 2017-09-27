package com.egr.drillinghelper.ui.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.mvp.IMvpBase;
import com.egr.drillinghelper.mvp.IPresenter;
import com.egr.drillinghelper.mvp.IView;
import com.egr.drillinghelper.ui.widgets.swipeback.SwipeBackActivity;
import com.michaelflisar.rxbus2.rx.RxDisposableManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseMVPActivity<V extends IView, P extends IPresenter<V>> extends
        SwipeBackActivity implements IMvpBase<V> {

    protected ActionBar actionBar;
    protected TextView title, rightTv;
    protected ImageView leftIv, rightIv, rightIv2;
    private Unbinder unbinder;
    protected P presenter;

    public abstract int returnLayoutID();

    public abstract void TODO(Bundle savedInstanceState);

    public abstract P createPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(returnLayoutID());
        actionBar = getSupportActionBar();
        unbinder = ButterKnife.bind(this);
        presenter = createPresenter();
        presenter.attachView(getMvpView());
        TODO(savedInstanceState);
    }

    @Override
    public V getMvpView() {
        return (V) this;
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
        title.setText(getString(actionBarTitleResId));
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
    protected void setupActionBar(int actionBarTitleResId, boolean displayHomeAsUpEnabled, View.OnClickListener listener) {
        if (actionBar == null) {
            return;
        }
        loadView();
        title.setText(getString(actionBarTitleResId));
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
    protected void setupActionBar(String actionBarTitle, int rightRes, boolean displayHomeAsUpEnabled, View.OnClickListener onClickListener) {
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
    protected void setupActionBar(int actionBarTitleResId) {
        if (actionBar == null) {
            return;
        }
        loadView();
        title.setText(actionBarTitleResId);
        rightIv.setVisibility(View.GONE);
        leftIv.setVisibility(View.GONE);
    }

    private void loadView() {
        actionBar.setElevation(0);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.MATCH_PARENT);
        layoutParams.gravity = Gravity.CENTER_HORIZONTAL | Gravity.CENTER_HORIZONTAL;
        View actionbarLayout = LayoutInflater.from(this).inflate(R.layout.view_cusactionbar, null);
        actionBar.setCustomView(actionbarLayout, layoutParams);
        Toolbar parent = (Toolbar) actionbarLayout.getParent();
        parent.setContentInsetsAbsolute(0, 0);
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));
        title = (TextView) actionbarLayout.findViewById(R.id.anctionbar_title);
        rightTv = (TextView) actionbarLayout.findViewById(R.id.anctionbar_right_tv);
        rightIv = (ImageView) actionbarLayout.findViewById(R.id.anctionbar_right_ic);
        rightIv2 = (ImageView) actionbarLayout.findViewById(R.id.anctionbar_right_ic2);
        leftIv = (ImageView) actionbarLayout.findViewById(R.id.anctionbar_left_arrow);
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

    /**
     * set actionbar right text resource and listener
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
    }

    public void changeActionBarRightText(int rightResId){
        if (rightTv == null)
            throw new RuntimeException("setupAction must first call!!!");
        rightTv.setText(rightResId);
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
     * set actionbar right text invisibile
     */
    public void setActionBarRightTextGone() {
        if (rightTv == null)
            throw new RuntimeException("setupAction must first call!!!");
        rightTv.setVisibility(View.GONE);
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
        presenter.detachView(false);
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
//        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public void baseStartActivity(Class cls, Bundle data) {
        Intent intent = new Intent(this, cls);
        intent.putExtras(data);
        startActivity(intent);
//        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public void baseStartActivityForResult(Class cls, int requestCode) {
        Intent intent = new Intent(this, cls);
        startActivityForResult(intent, requestCode);
//        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public void baseStartActivityForResult(Class cls, Bundle data, int requestCode) {
        Intent intent = new Intent(this, cls);
        intent.putExtras(data);
        startActivityForResult(intent, requestCode);
//        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public void finish() {
        super.finish();
//        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
