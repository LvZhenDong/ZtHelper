package com.egr.drillinghelper.contract;

import com.egr.drillinghelper.bean.base.BasePage;
import com.egr.drillinghelper.bean.response.Explain;
import com.egr.drillinghelper.mvp.IModel;
import com.egr.drillinghelper.mvp.IPresenter;
import com.egr.drillinghelper.mvp.IView;

/**
 * author lzd
 * date 2017/9/26 16:39
 * 类描述：
 */

public interface KnowContract {
    interface Model extends IModel {
        void getKnowList(int current);
    }

    interface View extends IView {
        void getKnowFail(String msg);

        void getKnowListSuccess(BasePage<Explain> data);

        void noMoreData();
    }

    interface Presenter extends IPresenter<View> {
        void getKnowList();

        void loadMore();

        void getKnowListSuccess(BasePage<Explain> data);
    }
}
