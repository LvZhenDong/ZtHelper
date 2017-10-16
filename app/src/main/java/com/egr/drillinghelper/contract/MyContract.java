package com.egr.drillinghelper.contract;

import com.egr.drillinghelper.bean.response.Share;
import com.egr.drillinghelper.bean.response.UserInfo;
import com.egr.drillinghelper.mvp.IModel;
import com.egr.drillinghelper.mvp.IPresenter;
import com.egr.drillinghelper.mvp.IView;

/**
 * author lzd
 * date 2017/9/26 10:05
 * 类描述：
 */

public interface MyContract {
    interface Model extends IModel {
        void userInfo();

        void logout();

        void getShareContent();
    }

    interface View extends IView {
        void getUserInfoSuccess(UserInfo userInfo);

        void logoutSuccess();

        void logoutFail(String msg);

        void getShareContentSuccess(Share share);
    }

    interface Presenter extends IPresenter<View> {
        void userInfo();

        void quit();

        void getShareContent();
    }
}
