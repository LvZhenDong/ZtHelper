package com.egr.drillinghelper.presenter;

import com.egr.drillinghelper.contract.AboutEgrContract;
import com.egr.drillinghelper.model.AboutEgrModelImpl;
import com.egr.drillinghelper.mvp.BasePresenter;
import com.egr.drillinghelper.mvp.IModel;

/**
 * author lzd
 * date 2017/9/26 16:41
 * 类描述：
 */

public class AboutEgrPresenterImpl extends BasePresenter<AboutEgrContract.View,
        AboutEgrModelImpl> implements AboutEgrContract.Presenter{
    @Override
    protected IModel createModel() {
        return new AboutEgrModelImpl(this);
    }

    @Override
    public void getAbout() {
        mModel.getAbout();
    }
}
