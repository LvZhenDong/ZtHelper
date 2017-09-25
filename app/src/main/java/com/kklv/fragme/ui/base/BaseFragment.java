package com.kklv.fragme.ui.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.michaelflisar.rxbus2.rx.RxDisposableManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Ymmmsick on 17/5/9.
 */
public abstract class BaseFragment extends Fragment implements FragmentUserVisibleController.UserVisibleCallback {

    protected ViewGroup view;
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

    @Override
    public void onVisibleToUserChanged(boolean isVisibleToUser, boolean invokeInResumeOrPause) {

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
        view = (ViewGroup) inflater.inflate(layoutID, container, false);
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
//        getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public void baseStartActivity(Class cls, Bundle data) {
        Intent intent = new Intent(getActivity(), cls);
        intent.putExtras(data);
        startActivity(intent);
//        getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public void baseStartActivityForResult(Class cls, int requestCode) {
        Intent intent = new Intent(getActivity(), cls);
        startActivityForResult(intent, requestCode);
//        getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public void baseStartActivityForResult(Class cls, Bundle data, int requestCode) {
        Intent intent = new Intent(getActivity(), cls);
        intent.putExtras(data);
        startActivityForResult(intent, requestCode);
//        getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public void finish() {
        getActivity().finish();
//        getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
