package com.egr.drillinghelper.presenter;

import android.text.TextUtils;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.contract.RegisterContract;
import com.egr.drillinghelper.model.RegisterModelImpl;
import com.egr.drillinghelper.mvp.BasePresenter;
import com.egr.drillinghelper.mvp.IModel;
import com.egr.drillinghelper.utils.MD5Util;
import com.egr.drillinghelper.utils.StringUtils;

/**
 * author lzd
 * date 2017/9/27 11:55
 * 类描述：
 */

public class RegisterPresenterImpl extends BasePresenter<RegisterContract.View,
        RegisterModelImpl> implements RegisterContract.Presenter {

    @Override
    protected IModel createModel() {
        return new RegisterModelImpl(this);
    }

    @Override
    public void register(String name, String company, String phone, String verCode, String pswd) {
        if (isInputFormal(name, company, phone, verCode, pswd)) {
            pswd= MD5Util.encodeByLower(pswd);
            mModel.register(name, company, phone, verCode, pswd);
        }
    }

    @Override
    public void getVerCode(String phone) {
        if(TextUtils.isEmpty(phone)){
            getView().inputError(R.string.phone_empty);
            return;
        }
        if(!StringUtils.isMobileNO(phone)){
            getView().inputError(R.string.is_not_phone_num);
            return;
        }

        mModel.getVerCode(phone);
    }

    private boolean isInputFormal(String name, String company,
                                  String phone, String verCode, String pswd) {
        if (TextUtils.isEmpty(name)) {
            getView().inputError(R.string.name_empty);
        } else if (TextUtils.isEmpty(company)) {
            getView().inputError(R.string.company_empty);
        } else if (TextUtils.isEmpty(phone)) {
            getView().inputError(R.string.phone_empty);
        } else if(!StringUtils.isMobileNO(phone)){
            getView().inputError(R.string.is_not_phone_num);
        } else if (TextUtils.isEmpty(verCode)) {
            getView().inputError(R.string.ver_code_empty);
        } else if (TextUtils.isEmpty(pswd)) {
            getView().inputError(R.string.pswd_empty);
        } else {
            return true;
        }
        return false;
    }
}
