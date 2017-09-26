package com.egr.drillinghelper.mvp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.CheckResult;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.egr.drillinghelper.ui.base.FragmentUserVisibleController;
import com.michaelflisar.rxbus2.rx.RxDisposableManager;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by Ymmmsick on 17/5/9.
 */
public abstract class BaseMVPFragment<V extends IView, P extends IPresenter<V>> extends Fragment
        implements LifecycleProvider<FragmentEvent>, IView, IMvpBase<V>, FragmentUserVisibleController.UserVisibleCallback {

    protected ViewGroup view;
    Unbinder unbinder;
    protected P presenter;
    protected boolean isFirstVisiableToUser = true;
    private final BehaviorSubject<FragmentEvent> lifecycleSubject = BehaviorSubject.create();
    private FragmentUserVisibleController userVisibleController;

    public BaseMVPFragment() {
        userVisibleController = new FragmentUserVisibleController(this, this);
    }

    @Override
    @android.support.annotation.NonNull
    @CheckResult
    public final Observable<FragmentEvent> lifecycle() {
        return lifecycleSubject.hide();
    }

    @Override
    @android.support.annotation.NonNull
    @CheckResult
    public final <T> LifecycleTransformer<T> bindUntilEvent(@android.support.annotation.NonNull FragmentEvent event) {
        return RxLifecycle.bindUntilEvent(lifecycleSubject, event);
    }

    @Override
    @android.support.annotation.NonNull
    @CheckResult
    public final <T> LifecycleTransformer<T> bindToLifecycle() {
        return RxLifecycleAndroid.bindFragment(lifecycleSubject);
    }

    @Override
    @CallSuper
    public void onAttach(android.app.Activity activity) {
        super.onAttach(activity);
        lifecycleSubject.onNext(FragmentEvent.ATTACH);
    }

    @Override
    @CallSuper
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lifecycleSubject.onNext(FragmentEvent.CREATE);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        userVisibleController.activityCreated();
    }

    @Override
    @CallSuper
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lifecycleSubject.onNext(FragmentEvent.CREATE_VIEW);
        if (presenter == null) {
            presenter = createPresenter();
        }
        presenter.attachView(getMvpView());
        TODO(view, savedInstanceState);
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
    public void onStart() {
        super.onStart();
        lifecycleSubject.onNext(FragmentEvent.START);
    }

    @Override
    @CallSuper
    public void onResume() {
        super.onResume();
        lifecycleSubject.onNext(FragmentEvent.RESUME);
        userVisibleController.resume();
    }

    @Override
    @CallSuper
    public void onPause() {
        lifecycleSubject.onNext(FragmentEvent.PAUSE);
        userVisibleController.pause();
        super.onPause();
    }

    @Override
    @CallSuper
    public void onStop() {
        lifecycleSubject.onNext(FragmentEvent.STOP);
        super.onStop();
    }

    @Override
    @CallSuper
    public void onDestroyView() {
        lifecycleSubject.onNext(FragmentEvent.DESTROY_VIEW);
        presenter.detachView(getRetainInstance());
        unbinder.unbind();
        RxDisposableManager.unsubscribe(this);
        super.onDestroyView();
    }

    @Override
    @CallSuper
    public void onDestroy() {
        lifecycleSubject.onNext(FragmentEvent.DESTROY);
        super.onDestroy();
    }

    @Override
    @CallSuper
    public void onDetach() {
        lifecycleSubject.onNext(FragmentEvent.DETACH);
        super.onDetach();
    }

    protected abstract P createPresenter();

    @Nullable
    @Override
    public V getMvpView() {
        return (V) this;
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
