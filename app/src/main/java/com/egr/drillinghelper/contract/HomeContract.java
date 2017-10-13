package com.egr.drillinghelper.contract;

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
    }

    interface View extends IView {
        void getNoReadMsgSuccess(int counts);
    }

    interface Presenter extends IPresenter<View> {
        void getNoReadMsg();
    }
}
