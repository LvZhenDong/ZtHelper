package com.egr.drillinghelper.ui.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.michaelflisar.rxbus2.rx.RxDisposableManager;
import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Ymmmsick on 17/5/9.
 */
public abstract class BaseFragment extends Fragment implements FragmentUserVisibleController.UserVisibleCallback {
    protected static final String KEY_INTENT = BaseActivity.KEY_INTENT;
    protected static final String KEY_INTENT_BOOLEAN = BaseActivity.KEY_INTENT_BOOLEAN;

    Unbinder unbinder;
    protected boolean isFirstVisiableToUser = true;
    private FragmentUserVisibleController userVisibleController;

    public BaseFragment() {
        userVisibleController = new FragmentUserVisibleController(this, this);
    }

    @Override
    @CallSuper
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setOthers(view, savedInstanceState);
    }

    protected void setOthers(View view, Bundle savedInstanceState) {
        TODO(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        userVisibleController.activityCreated();
    }

    @Override
    public void onResume() {
        super.onResume();
        userVisibleController.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        userVisibleController.pause();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        userVisibleController.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void setWaitingShowToUser(boolean waitingShowToUser) {
        userVisibleController.setWaitingShowToUser(waitingShowToUser);
    }

    @Override
    public boolean isWaitingShowToUser() {
        return userVisibleController.isWaitingShowToUser();
    }

    @Override
    public boolean isVisibleToUser() {
        return userVisibleController.isVisibleToUser();
    }

    @Override
    public void callSuperSetUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    String mUmengAnalyze;

    public void setUmengAnalyze(String str) {
        this.mUmengAnalyze = str;
    }

    public void setUmengAnalyze(int strId) {
        this.mUmengAnalyze = getString(strId);
    }

    @Override
    public void onVisibleToUserChanged(boolean isVisibleToUser, boolean invokeInResumeOrPause) {
        if (TextUtils.isEmpty(mUmengAnalyze)) return;
        if (isVisibleToUser) {
            MobclickAgent.onPageStart(mUmengAnalyze);
        } else {
            MobclickAgent.onPageEnd(mUmengAnalyze);
        }
    }

    @Override
    @CallSuper
    public void onDestroyView() {
        unbinder.unbind();
        RxDisposableManager.unsubscribe(this);
        super.onDestroyView();
    }

    /**
     * use in oncreate
     * such as <i><b>return inflate(inflater,container)<b/><i/>
     *
     * @param inflater
     * @param container
     * @param layoutID  the view to be layout
     * @return
     */
    protected View inflate(LayoutInflater inflater, ViewGroup container, int layoutID) {
        View view = inflater.inflate(layoutID, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflate(inflater, container, returnLayoutID());
    }

    public abstract int returnLayoutID();

    /**
     * 相当于 onCreate
     *
     * @param savedInstanceState
     */
    public abstract void TODO(View view, Bundle savedInstanceState);

    /**
     * get context
     *
     * @return
     */
    public Context getContext() {
        return getActivity();
    }

    public void baseStartActivity(Class cls) {
        Intent intent = new Intent(getActivity(), cls);
        startActivity(intent);
    }

    public void finish() {
        getActivity().finish();
    }
}
