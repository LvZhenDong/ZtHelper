package com.egr.drillinghelper.contract;

import com.egr.drillinghelper.bean.response.Store;
import com.egr.drillinghelper.mvp.IModel;
import com.egr.drillinghelper.mvp.IPresenter;
import com.egr.drillinghelper.mvp.IView;

import java.util.List;

/**
 * author lzd
 * date 2017/9/26 15:09
 * 类描述：
 */

public interface PartsContract {
    interface Model extends IModel {
        void getPartsList(int pageNum);
    }

    interface View extends IView {
        void getPastsFail(String msg);

        void getPartsListSuccess(List<Store> list);
    }

    interface Presenter extends IPresenter<View> {
        void getPartsList();

        void loadMore();

        boolean isLoadMore();
    }
}
