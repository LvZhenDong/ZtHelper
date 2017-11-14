package com.egr.drillinghelper.contract;

import com.egr.drillinghelper.bean.response.StoreDetail;
import com.egr.drillinghelper.mvp.IModel;
import com.egr.drillinghelper.mvp.IPresenter;
import com.egr.drillinghelper.mvp.IView;

/**
 * author lzd
 * date 2017/9/26 15:09
 * 类描述：
 */

public interface PartsDetailContract {
    interface Model extends IModel {
        void getContent(String id);

        void getParts(String id);
    }

    interface View extends IView {
        void getFail(String msg);
        void getSuc(StoreDetail data);
    }

    interface Presenter extends IPresenter<View> {
        void getContent(String id);

        void getParts(String id);
    }
}
