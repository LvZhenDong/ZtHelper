package com.kklv.fragme.presenter;

import com.kklv.fragme.contract.LoginContract;
import com.kklv.fragme.model.LoginModelImpl;
import com.kklv.fragme.mvp.BasePresenter;
import com.kklv.fragme.mvp.IModel;

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
