package com.egr.drillinghelper.contract;

import com.egr.drillinghelper.bean.response.ExplainCatalog;
import com.egr.drillinghelper.bean.response.KnowCatalog;
import com.egr.drillinghelper.mvp.IModel;
import com.egr.drillinghelper.mvp.IPresenter;
import com.egr.drillinghelper.mvp.IView;

import java.util.List;

/**
 * author lzd
 * date 2017/9/26 15:09
 * 类描述：
 */

public interface KnowCatalogContract {
    interface Model extends IModel {
        void knowCatalog(String id);
    }

    interface View extends IView {
        void getCatalogSuccess(List<KnowCatalog> catalogList);

        void getCatalogFail(String msg);

        void getCatalogCache(List<KnowCatalog> catalogList);
    }

    interface Presenter extends IPresenter<View> {
        void getKnowCatalog(String id);

        void getCatalogSuccess(List<KnowCatalog> catalogList);
    }
}
