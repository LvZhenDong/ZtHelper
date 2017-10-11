package com.egr.drillinghelper.contract;

import com.egr.drillinghelper.bean.response.FeedbackDetail;
import com.egr.drillinghelper.mvp.IModel;
import com.egr.drillinghelper.mvp.IPresenter;
import com.egr.drillinghelper.mvp.IView;

/**
 * author lzd
 * date 2017/9/26 15:09
 * 类描述：
 */

public interface FeedbackDetailContract {
    interface Model extends IModel {
        void getDetail(String id);
    }

    interface View extends IView {
        void getDetailSuccess(String detail);

        void getDetailFail(String msg);
    }

    interface Presenter extends IPresenter<View> {
        void getDetail(String id);

        void getDetailSuccess(FeedbackDetail detail);
    }
}
