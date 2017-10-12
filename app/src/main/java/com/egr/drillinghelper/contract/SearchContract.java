package com.egr.drillinghelper.contract;

import com.egr.drillinghelper.bean.base.BasePage;
import com.egr.drillinghelper.bean.response.Explain;
import com.egr.drillinghelper.bean.response.KnowCatalog;
import com.egr.drillinghelper.bean.response.Parts;
import com.egr.drillinghelper.mvp.IModel;
import com.egr.drillinghelper.mvp.IPresenter;
import com.egr.drillinghelper.mvp.IView;

import java.util.List;

/**
 * author lzd
 * date 2017/9/26 15:09
 * 类描述：
 */

public interface SearchContract {
    interface Model extends IModel {
        void searchKnow(String keyword,int current);

        void searchParts(String keyword,int current);

        void searchExplain(String keyword,int current);
    }

    interface View extends IView {
        void searchFail(String msg);

        void noMoreData();

        void searchKnowSuccess(List<KnowCatalog> knowCatalogs,List<String> titles);

        void searchParts(List<Parts> parts,List<String> titles);

        void searchExplainCatalog(List<Explain> explainCatalogs, List<String> titles);
    }

    interface Presenter extends IPresenter<View> {
        void search(String keyword,int type);

        void loadMore();

        void searchKnowSuccess(BasePage<KnowCatalog> data);

        void searchPartsSuccess(BasePage<Parts> data);

        void searchExplainCatalogSuccess(BasePage<Explain> data);

    }
}
