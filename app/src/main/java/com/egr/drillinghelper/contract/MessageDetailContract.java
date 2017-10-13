package com.egr.drillinghelper.contract;

import com.egr.drillinghelper.bean.response.Message;
import com.egr.drillinghelper.mvp.IModel;
import com.egr.drillinghelper.mvp.IPresenter;
import com.egr.drillinghelper.mvp.IView;

/**
 * author lzd
 * date 2017/9/26 16:39
 * 类描述：
 */

public interface MessageDetailContract {
    interface Model extends IModel {
        void getMsgDetail(String id);
    }

    interface View extends IView {
        void getMsgSuccess(Message msg);

        void getMsgFail(String msg);
    }

    interface Presenter extends IPresenter<View> {
        void getMsgDetail(String id);
    }
}
