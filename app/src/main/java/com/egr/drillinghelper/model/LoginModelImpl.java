package com.egr.drillinghelper.model;

import com.egr.drillinghelper.mvp.BaseModel;
import com.egr.drillinghelper.presenter.LoginPresenterImpl;

/**
 * author 边凌
 * date 2017/9/26 10:07
 * 类描述：
 */

public class LoginModelImpl extends BaseModel<LoginPresenterImpl> {
    public LoginModelImpl(LoginPresenterImpl loginPresenter) {
        super(loginPresenter);
    }
}
