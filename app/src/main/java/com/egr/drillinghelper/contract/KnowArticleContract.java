package com.egr.drillinghelper.contract;

import com.egr.drillinghelper.mvp.IModel;
import com.egr.drillinghelper.mvp.IPresenter;
import com.egr.drillinghelper.mvp.IView;

/**
 * author lzd
 * date 2017/9/26 15:09
 * 类描述：
 */

public interface KnowArticleContract {
    interface Model extends IModel {
        void getContent(String id);
    }

    interface View extends IView {
    }

    interface Presenter extends IPresenter<View> {
        void getContent(String id);
    }
}
