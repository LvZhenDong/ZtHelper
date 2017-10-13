package com.egr.drillinghelper.contract;

import com.egr.drillinghelper.bean.base.BasePage;
import com.egr.drillinghelper.bean.response.Message;
import com.egr.drillinghelper.mvp.IModel;
import com.egr.drillinghelper.mvp.IPresenter;
import com.egr.drillinghelper.mvp.IView;

/**
 * author lzd
 * date 2017/9/26 10:05
 * 类描述：
 */

public interface MessageContract {
    interface Model extends IModel {
        void getMsgList(int current);

        void deleteMsg(String id);
    }

    interface View extends IView {
        void getMsgListSuccess(BasePage<Message> data);

        void getMsgListFail(String msg);

        void noMoreData();

        void deleteMsgSuccess();

        void deleteMsgFail(String msg);
    }

    interface Presenter extends IPresenter<View> {
        void getMsgList();

        void getMsgListSuccess(BasePage<Message> data);

        void loadMore();

        void deleteMsg(String id);
    }
}
