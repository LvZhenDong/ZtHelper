package com.egr.drillinghelper.presenter;

import android.text.TextUtils;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.contract.ForgetPswdContract;
import com.egr.drillinghelper.model.ForgetPswdModelImpl;
import com.egr.drillinghelper.model.LoginModelImpl;
import com.egr.drillinghelper.mvp.BasePresenter;
import com.egr.drillinghelper.mvp.IModel;
import com.egr.drillinghelper.utils.MD5Util;
import com.egr.drillinghelper.utils.StringUtils;

/**
 * author lzd
 * date 2017/9/26 15:09
 * 类描述：
 */

public class ForgetPswdPresenterImpl extends BasePresenter<ForgetPswdContract.View,ForgetPswdModelImpl>
implements ForgetPswdContract.Presenter{
    @Override
    protected IModel createModel() {
        return new ForgetPswdModelImpl(this);
    }

    @Override
    public void forgetPswd(String phone, String code, String pswd, String ensurePswd) {
        if(isInputFormal(phone,code,pswd,ensurePswd)){
            pswd = MD5Util.encodeByLower(pswd);
            mModel.forgetPswd(phone, code, pswd);
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

    private boolean isInputFormal(String phone, String verCode, String pswd,String ensurePswd) {
        if (TextUtils.isEmpty(phone)) {
            getView().inputError(R.string.phone_empty);
        } else if(!StringUtils.isMobileNO(phone)){
            getView().inputError(R.string.is_not_phone_num);
        } else if (TextUtils.isEmpty(verCode)) {
            getView().inputError(R.string.ver_code_empty);
        } else if (TextUtils.isEmpty(pswd)) {
            getView().inputError(R.string.pswd_empty);
        } else if(!pswd.equals(ensurePswd)){
            getView().inputError(R.string.pswd_unlike);
        }else {
            return true;
        }
        return false;
    }
}
