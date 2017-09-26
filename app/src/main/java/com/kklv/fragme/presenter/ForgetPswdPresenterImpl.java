package com.kklv.fragme.presenter;

import com.kklv.fragme.contract.ForgetPswdContract;
import com.kklv.fragme.contract.LoginContract;
import com.kklv.fragme.model.ForgetPswdModelImpl;
import com.kklv.fragme.model.LoginModelImpl;
import com.kklv.fragme.mvp.BasePresenter;
import com.kklv.fragme.mvp.IModel;

/**
 * author lzd
 * date 2017/9/26 15:09
 * 类描述：
 */

public class ForgetPswdPresenterImpl extends BasePresenter<ForgetPswdContract.View,LoginModelImpl>
implements ForgetPswdContract.Presenter{
    @Override
    protected IModel createModel() {
        return new ForgetPswdModelImpl(this);
    }
}
