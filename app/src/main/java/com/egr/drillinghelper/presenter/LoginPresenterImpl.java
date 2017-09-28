package com.egr.drillinghelper.presenter;

import android.text.TextUtils;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.contract.LoginContract;
import com.egr.drillinghelper.model.LoginModelImpl;
import com.egr.drillinghelper.mvp.BasePresenter;
import com.egr.drillinghelper.mvp.IModel;
import com.egr.drillinghelper.utils.MD5Util;
import com.egr.drillinghelper.utils.StringUtils;

/**
 * author lzd
 * date 2017/9/26 10:08
 * 类描述：
 */

public class LoginPresenterImpl extends BasePresenter<LoginContract.View,LoginModelImpl>
    implements LoginContract.Presenter{
    @Override
    protected IModel createModel() {
        return new LoginModelImpl(this);
    }

    @Override
    public void login(String phone, String pswd) {
        if(TextUtils.isEmpty(phone)){
            getView().inputError(R.string.phone_empty);
            return;
        }
        if(!StringUtils.isMobileNO(phone)){
            getView().inputError(R.string.is_not_phone_num);
            return;
        }

        if(TextUtils.isEmpty(pswd)){
            getView().inputError(R.string.pswd_empty);
            return;
        }
        pswd= MD5Util.encodeByLower(pswd);
        mModel.login(phone,pswd);
    }
}
