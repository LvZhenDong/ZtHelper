package com.egr.drillinghelper.contract;

import com.egr.drillinghelper.bean.base.BasePage;
import com.egr.drillinghelper.bean.response.Explain;
import com.egr.drillinghelper.mvp.IModel;
import com.egr.drillinghelper.mvp.IPresenter;
import com.egr.drillinghelper.mvp.IView;

import java.util.List;

/**
 * author lzd
 * date 2017/9/26 16:39
 * 类描述：
 */

public interface ExplainContract {
    interface Model extends IModel {
        void getExplainList(String keyword,int current);

        void getExplainCache();
    }

    interface View extends IView {
        void getExplainFail(String msg);

        void getExplainListSuccess(BasePage<Explain> data);

        void noMoreData();

        void showExplainCache(List<Explain> explain);
    }

    interface Presenter extends IPresenter<View> {
        void getExplainCache();

        void getExplainList(String keyword);

        void loadMore();

        void getExplainListSuccess(BasePage<Explain> data);

        void getExplainFail(String msg);
    }
}
