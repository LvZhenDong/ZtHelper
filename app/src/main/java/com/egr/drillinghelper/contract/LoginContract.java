package com.egr.drillinghelper.contract;

import com.egr.drillinghelper.mvp.IModel;
import com.egr.drillinghelper.mvp.IPresenter;
import com.egr.drillinghelper.mvp.IView;

/**
 * author lzd
 * date 2017/9/26 10:05
 * 类描述：
 */

public interface LoginContract {
    interface Model extends IModel {
        void login(String phone, String password);

        void readCache();
    }

    interface View extends IView {
        void inputError(int e);

        void loginSuccess();

        void loginFail(String message);
    }

    interface Presenter extends IPresenter<View> {
        void login(String phone, String password);

        void readCache();
    }
}
