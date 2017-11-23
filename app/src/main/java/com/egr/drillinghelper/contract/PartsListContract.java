package com.egr.drillinghelper.contract;

import com.egr.drillinghelper.bean.base.BasePage;
import com.egr.drillinghelper.bean.response.Store;
import com.egr.drillinghelper.mvp.IModel;
import com.egr.drillinghelper.mvp.IPresenter;
import com.egr.drillinghelper.mvp.IView;


public interface PartsListContract {

    interface Model extends IModel {
        void getPartsList(String type,String keyword,int pageNum);
    }

    interface View extends IView {
        void getPastsFail(String msg);

        void getPartsListSuccess(BasePage<Store> data);

        void noMoreData();
    }

    interface Presenter extends IPresenter<View> {
        void getPartsList(int type);

        void getPastsFail(String msg);

        void getPartsListSuccess(BasePage<Store> data);

        void loadMore();
    }
}
