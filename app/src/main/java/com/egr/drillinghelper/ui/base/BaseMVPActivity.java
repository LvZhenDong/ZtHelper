package com.egr.drillinghelper.ui.base;

import android.os.Bundle;

import com.egr.drillinghelper.mvp.IMvpBase;
import com.egr.drillinghelper.mvp.IPresenter;
import com.egr.drillinghelper.mvp.IView;
import com.michaelflisar.rxbus2.rx.RxDisposableManager;


public abstract class BaseMVPActivity<V extends IView, P extends IPresenter<V>> extends
        BaseActivity implements IMvpBase<V> {

    protected P presenter;
    public abstract P createPresenter();

    @Override
    protected void setOthers(Bundle savedInstanceState) {
        presenter = createPresenter();
        presenter.attachView(getMvpView());
        TODO(savedInstanceState);
    }

    @Override
    public V getMvpView() {
        return (V) this;
    }

    @Override
    protected void afterDestroy() {
        presenter.detachView(false);
        unbinder.unbind();
        RxDisposableManager.unsubscribe(this);
    }
}
