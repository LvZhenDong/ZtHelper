package com.kklv.fragme.model;

import com.kklv.fragme.mvp.BaseModel;
import com.kklv.fragme.presenter.LoginPresenterImpl;

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
