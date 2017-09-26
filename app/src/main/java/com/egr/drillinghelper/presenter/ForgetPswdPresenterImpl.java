package com.egr.drillinghelper.presenter;

import com.egr.drillinghelper.contract.ForgetPswdContract;
import com.egr.drillinghelper.model.ForgetPswdModelImpl;
import com.egr.drillinghelper.model.LoginModelImpl;
import com.egr.drillinghelper.mvp.BasePresenter;
import com.egr.drillinghelper.mvp.IModel;

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
