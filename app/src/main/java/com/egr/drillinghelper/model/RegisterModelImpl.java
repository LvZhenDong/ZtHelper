package com.egr.drillinghelper.model;

import com.egr.drillinghelper.mvp.BaseModel;
import com.egr.drillinghelper.presenter.RegisterPresenterImpl;



/**
 * author lzd
 * date 2017/9/27 11:55
 * 类描述：
 */

public class RegisterModelImpl extends BaseModel<RegisterPresenterImpl> {
    public RegisterModelImpl(RegisterPresenterImpl registerPresenter) {
        super(registerPresenter);
    }
}
