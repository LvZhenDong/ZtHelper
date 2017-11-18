package com.egr.drillinghelper.ui.base;

import android.os.Bundle;

import com.egr.drillinghelper.mvp.IMvpBase;
import com.egr.drillinghelper.mvp.IPresenter;
import com.egr.drillinghelper.mvp.IView;
import com.egr.drillinghelper.ui.widgets.DialogHelper;

import cc.cloudist.acplibrary.ACProgressFlower;


public abstract class BaseMVPActivity<V extends IView, P extends IPresenter<V>> extends
        BaseActivity implements IMvpBase<V> {

    protected P presenter;

    public abstract P createPresenter();

    protected ACProgressFlower mDialog;

    @Override
    protected void setOthers(Bundle savedInstanceState) {
        presenter = createPresenter();
        presenter.attachView(getMvpView());
        mDialog = DialogHelper.openiOSPbDialog(this);
        TODO(savedInstanceState);
    }

    @Override
    public V getMvpView() {
        return (V) this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView(false);
    }
}
