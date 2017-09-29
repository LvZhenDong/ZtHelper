package com.egr.drillinghelper.contract;

import com.egr.drillinghelper.bean.response.AboutEgr;
import com.egr.drillinghelper.mvp.IModel;
import com.egr.drillinghelper.mvp.IPresenter;
import com.egr.drillinghelper.mvp.IView;

/**
 * author lzd
 * date 2017/9/26 15:09
 * 类描述：
 */

public interface AboutEgrContract {
    interface Model extends IModel {
        void getAbout();
    }

    interface View extends IView {
        void getAboutSuccess(String aboutEgr);
        void getAboutFail(String msg);
    }

    interface Presenter extends IPresenter<View> {
        void getAbout();
    }
}
