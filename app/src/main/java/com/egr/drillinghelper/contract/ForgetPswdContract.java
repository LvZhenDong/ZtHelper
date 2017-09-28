package com.egr.drillinghelper.contract;

import com.egr.drillinghelper.mvp.IModel;
import com.egr.drillinghelper.mvp.IPresenter;
import com.egr.drillinghelper.mvp.IView;

/**
 * author lzd
 * date 2017/9/26 15:09
 * 类描述：
 */

public interface ForgetPswdContract {
    interface Model extends IModel {
        void forgetPswd(String phone,String code,String pswd);

        void getVerCode(String phone);
    }

    interface View extends IView {
        void inputError(int e);

        void forgetPswdFail(String msg);

        void forgetPswdSuccess();

        void getVerCodeSuccess(String code);

        void getVerCodeFail(String msg);
    }

    interface Presenter extends IPresenter<View> {
        void forgetPswd(String phone,String code,String pswd,String ensurePswd);

        void getVerCode(String phone);
    }
}
