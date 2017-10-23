package com.egr.drillinghelper.contract;

import com.egr.drillinghelper.bean.base.BasePage;
import com.egr.drillinghelper.bean.response.Store;
import com.egr.drillinghelper.bean.response.StoreMore;
import com.egr.drillinghelper.mvp.IModel;
import com.egr.drillinghelper.mvp.IPresenter;
import com.egr.drillinghelper.mvp.IView;

import java.util.List;

import static android.R.id.list;

/**
 * author lzd
 * date 2017/9/26 15:09
 * 类描述：
 */

public interface PartsContract {
    interface Model extends IModel {
        void getPartsList(String keyword,int pageNum);

        void getMall();
    }

    interface View extends IView {
        void getPastsFail(String msg);

        void getPartsListSuccess(BasePage<Store> data);

        void noMoreData();
    }

    interface Presenter extends IPresenter<View> {
        void getPartsList(String keyword);

        void loadMore();

        void getPartsListSuccess(BasePage<Store> data);

        void getPastsFail(String msg);

        void getMallSuccess();
    }
}
