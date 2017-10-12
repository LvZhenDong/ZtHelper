package com.egr.drillinghelper.contract;

import com.egr.drillinghelper.bean.base.BasePage;
import com.egr.drillinghelper.bean.response.Reply;
import com.egr.drillinghelper.mvp.IModel;
import com.egr.drillinghelper.mvp.IPresenter;
import com.egr.drillinghelper.mvp.IView;

/**
 * author lzd
 * date 2017/9/26 15:09
 * 类描述：
 */

public interface ReplyContract {
    interface Model extends IModel {
        void getReply(String status,int current);
    }

    interface View extends IView {
        void getReplyFail(String msg);

        void getReplySuccess(BasePage<Reply> data);

        void noMoreData();
    }

    interface Presenter extends IPresenter<View> {
        void getReply(String status);

        void loadMore(String status);

        void getReplySuccess(BasePage<Reply> data);
    }
}
