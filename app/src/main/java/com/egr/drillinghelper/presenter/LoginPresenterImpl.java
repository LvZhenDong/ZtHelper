package com.egr.drillinghelper.presenter;

import com.egr.drillinghelper.contract.LoginContract;
import com.egr.drillinghelper.model.LoginModelImpl;
import com.egr.drillinghelper.mvp.BasePresenter;
import com.egr.drillinghelper.mvp.IModel;

/**
 * author 边凌
 * date 2017/9/26 10:08
 * 类描述：
 */

public class LoginPresenterImpl extends BasePresenter<LoginContract.View,LoginModelImpl>
    implements LoginContract.Presenter{
    @Override
    protected IModel createModel() {
        return new LoginModelImpl(this);
    }
}
