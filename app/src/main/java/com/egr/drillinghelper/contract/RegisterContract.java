package com.egr.drillinghelper.contract;

import com.egr.drillinghelper.mvp.IModel;
import com.egr.drillinghelper.mvp.IPresenter;
import com.egr.drillinghelper.mvp.IView;

/**
 * author lzd
 * date 2017/9/26 15:09
 * 类描述：
 */

public interface RegisterContract {
    interface Model extends IModel {
        void register(String name,String company,String phone,
                      String verCode,String pswd);

        void getVerCode(String phone);
    }

    interface View extends IView {

        void inputError(int e);

        void registerSuccess();

        void registerFail(String message);

        void getVerCodeSuccess(String code);

        void getVerCodeFail(String msg);

    }

    interface Presenter extends IPresenter<View> {
        void register(String name,String company,String phone,
                      String verCode,String pswd);

        void getVerCode(String phone);
    }
}
