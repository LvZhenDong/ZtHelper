package com.egr.drillinghelper.presenter;

import com.egr.drillinghelper.contract.RegisterContract;
import com.egr.drillinghelper.model.RegisterModelImpl;
import com.egr.drillinghelper.mvp.BasePresenter;
import com.egr.drillinghelper.mvp.IModel;

/**
 * author lzd
 * date 2017/9/27 11:55
 * 类描述：
 */

public class RegisterPresenterImpl extends BasePresenter<RegisterContract.View,
        RegisterModelImpl> implements RegisterContract.Presenter{

    @Override
    protected IModel createModel() {
        return new RegisterModelImpl(this);
    }
}
