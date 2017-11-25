package com.egr.drillinghelper.contract;

import com.egr.drillinghelper.bean.response.ContactUs;
import com.egr.drillinghelper.mvp.IModel;
import com.egr.drillinghelper.mvp.IPresenter;
import com.egr.drillinghelper.mvp.IView;

/**
 * author lzd
 * date 2017/9/26 16:39
 * 类描述：
 */

public interface HomeContract {
    interface Model extends IModel {
        void getNoReadMsg();

        void getContact();

        void checkRead();

        void checkVersion();

        void unRegisterCheck();
    }

    interface View extends IView {
        void getNoReadMsgSuccess(int counts);

        void getContactSuccess(String phoneNum);

        void getContactError(String msg);

        void checkReadSuc(boolean read);
    }

    interface Presenter extends IPresenter<View> {
        void getNoReadMsg();

        void getContactSuccess(ContactUs contactUs);

        void getContact();

        void checkRead();

        void checkVersion();

        void unRegisterCheck();
    }
}
