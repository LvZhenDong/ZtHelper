package com.egr.drillinghelper.mvp;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.CheckResult;
import android.support.annotation.Nullable;
import android.view.View;

import com.egr.drillinghelper.ui.base.BaseFragment;
import com.egr.drillinghelper.ui.base.FragmentUserVisibleController;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by Ymmmsick on 17/5/9.
 */
public abstract class BaseMVPFragment<V extends IView, P extends IPresenter<V>> extends BaseFragment
        implements LifecycleProvider<FragmentEvent>, IView, IMvpBase<V>, FragmentUserVisibleController.UserVisibleCallback {


    protected P presenter;
    private final BehaviorSubject<FragmentEvent> lifecycleSubject = BehaviorSubject.create();

    public BaseMVPFragment() {
        super();
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
    protected void setOthers(View view, Bundle savedInstanceState) {
        lifecycleSubject.onNext(FragmentEvent.CREATE_VIEW);
        if (presenter == null) {
            presenter = createPresenter();
        }
        presenter.attachView(getMvpView());
        TODO(view, savedInstanceState);
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
    }

    @Override
    @CallSuper
    public void onPause() {
        lifecycleSubject.onNext(FragmentEvent.PAUSE);
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
}
