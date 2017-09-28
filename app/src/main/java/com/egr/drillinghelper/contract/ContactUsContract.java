package com.egr.drillinghelper.contract;

import com.egr.drillinghelper.bean.response.ContactUs;
import com.egr.drillinghelper.mvp.IModel;
import com.egr.drillinghelper.mvp.IPresenter;
import com.egr.drillinghelper.mvp.IView;

/**
 * author lzd
 * date 2017/9/26 15:09
 * 类描述：
 */

public interface ContactUsContract {
    interface Model extends IModel {
        void getContactList();
    }

    interface View extends IView {

        void getListFail(String msg);

        void getListSuccess(ContactUs contactUs);
    }

    interface Presenter extends IPresenter<View> {
        void getContactList();
    }
}
