package com.egr.drillinghelper.contract;

import com.egr.drillinghelper.bean.response.Feedback;
import com.egr.drillinghelper.mvp.IModel;
import com.egr.drillinghelper.mvp.IPresenter;
import com.egr.drillinghelper.mvp.IView;

import java.util.List;

/**
 * author lzd
 * date 2017/9/26 15:09
 * 类描述：
 */

public interface FeedbackContract {
    interface Model extends IModel {
        void getFeedbackList();
    }

    interface View extends IView {
        void getFeedbackListSuccess(List<Feedback> list);

        void getFeedbackFail(String msg);
    }

    interface Presenter extends IPresenter<View> {
        void getFeedbackList();
    }
}
