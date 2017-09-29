package com.egr.drillinghelper.presenter;

import com.egr.drillinghelper.contract.MyContract;
import com.egr.drillinghelper.contract.SearchContract;
import com.egr.drillinghelper.model.MyModelImpl;
import com.egr.drillinghelper.model.SearchModelImpl;
import com.egr.drillinghelper.mvp.BasePresenter;
import com.egr.drillinghelper.mvp.IModel;

/**
 * author lzd
 * date 2017/9/26 16:41
 * 类描述：
 */

public class MyPresenterImpl extends BasePresenter<MyContract.View,
        MyModelImpl> implements MyContract.Presenter{
    @Override
    protected IModel createModel() {
        return new MyModelImpl(this);
    }

    @Override
    public void userInfo() {
        mModel.userInfo();
    }

    @Override
    public void quit() {
        mModel.logout();
    }
}
