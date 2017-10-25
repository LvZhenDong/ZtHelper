package com.egr.drillinghelper.contract;

import com.egr.drillinghelper.bean.base.BasePage;
import com.egr.drillinghelper.bean.response.ContactUs;
import com.egr.drillinghelper.bean.response.Video;
import com.egr.drillinghelper.mvp.IModel;
import com.egr.drillinghelper.mvp.IPresenter;
import com.egr.drillinghelper.mvp.IView;

/**
 * author lzd
 * date 2017/9/26 16:39
 * 类描述：
 */

public interface VideoPartContract {
    interface Model extends IModel {
        void getVideoList(String keyword,int current);
    }

    interface View extends IView {
        void noMoreData();

        void getVideoListSuc(BasePage<Video> data);

        void getVideoListFail(String msg);
    }

    interface Presenter extends IPresenter<View> {
        void getVideoList(String keyword);

        void loadMore();

        void getVideoListSuc(BasePage<Video> data);
    }
}
