package com.egr.drillinghelper.contract;

import com.egr.drillinghelper.bean.response.ExplainCatalog;
import com.egr.drillinghelper.mvp.IModel;
import com.egr.drillinghelper.mvp.IPresenter;
import com.egr.drillinghelper.mvp.IView;

import java.util.List;

/**
 * author lzd
 * date 2017/9/26 15:09
 * 类描述：
 */

public interface ExplainCatalogContract {
    interface Model extends IModel {
        void explainCatalog(String id);
    }

    interface View extends IView {
        void getCatalogSuccess(List<ExplainCatalog> catalogList);

        void getCatalogFail(String msg);
    }

    interface Presenter extends IPresenter<View> {
        void getExplainCatalog(String id);

        void getCatalogSuccess(List<ExplainCatalog> catalogList);
    }
}
