package com.egr.drillinghelper.contract;

import com.egr.drillinghelper.bean.response.Feedback;
import com.egr.drillinghelper.mvp.IModel;
import com.egr.drillinghelper.mvp.IPresenter;
import com.egr.drillinghelper.mvp.IView;
import com.lzy.imagepicker.bean.ImageItem;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;

/**
 * author lzd
 * date 2017/9/26 15:09
 * 类描述：
 */

public interface CreateFeedbackContract {
    interface Model extends IModel {
        void createFeedback(String question);

        void createFeedback(String question, Map<String, RequestBody> photos);
    }

    interface View extends IView {
        void commitFail(String msg);

        void commitSuccess(List<Feedback> list);
    }

    interface Presenter extends IPresenter<View> {
        void createFeedback(String question, List<ImageItem> imageItems);
    }
}
