package com.egr.drillinghelper.contract;

import com.egr.drillinghelper.bean.base.BasePage;
import com.egr.drillinghelper.bean.response.ContactUs;
import com.egr.drillinghelper.bean.response.ServiceMsg;
import com.egr.drillinghelper.mvp.IModel;
import com.egr.drillinghelper.mvp.IPresenter;
import com.egr.drillinghelper.mvp.IView;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;

/**
 * author lzd
 * date 2017/9/26 16:39
 * 类描述：
 */

public interface ServiceContract {
    interface Model extends IModel {
        void sendMsg(String msg);

        void getMsg(int current);

        void sendPhoto(Map<String, RequestBody> photo);
    }

    interface View extends IView {
        void sendMsgSuc();

        void sendMsgFail();

        void getMsgSuc(BasePage<ServiceMsg> data);

        void getMsgFail(String msg);

        void noMoreData();
    }

    interface Presenter extends IPresenter<View> {
        void sendMsg(String msg);

        void getMsgSuc(BasePage<ServiceMsg> data);

        void sendPhoto(String path);

        void getMsg();

        void loadMore();
    }
}
