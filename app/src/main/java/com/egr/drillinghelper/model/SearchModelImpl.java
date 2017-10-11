package com.egr.drillinghelper.model;

import com.egr.drillinghelper.api.NetApi;
import com.egr.drillinghelper.api.error.EObserver;
import com.egr.drillinghelper.api.error.ResponseThrowable;
import com.egr.drillinghelper.bean.base.BasePageResponse;
import com.egr.drillinghelper.bean.response.ExplainCatalog;
import com.egr.drillinghelper.bean.response.KnowCatalog;
import com.egr.drillinghelper.bean.response.Parts;
import com.egr.drillinghelper.bean.response.SearchResult;
import com.egr.drillinghelper.contract.SearchContract;
import com.egr.drillinghelper.factory.APIServiceFactory;
import com.egr.drillinghelper.factory.TransformersFactory;
import com.egr.drillinghelper.mvp.BaseModel;
import com.egr.drillinghelper.presenter.SearchPresenterImpl;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;

import io.reactivex.annotations.NonNull;

/**
 * author lzd
 * date 2017/9/26 16:40
 * 类描述：
 */

public class SearchModelImpl extends BaseModel<SearchPresenterImpl> implements SearchContract.Model {
    NetApi api;

    public SearchModelImpl(SearchPresenterImpl presenter) {
        super(presenter);
        api = APIServiceFactory.getInstance().createService();
    }

    @Override
    public void searchKnow(String keyword,int current) {
        api.searchKnow(keyword,current+"")
                .compose(TransformersFactory.<BasePageResponse<KnowCatalog>>commonTransformer((BaseMVPActivity) presenter.getView()))
                .subscribe(new EObserver<BasePageResponse<KnowCatalog>>() {
                    @Override
                    public void onError(ResponseThrowable e, String eMsg) {
                        presenter.getView().searchFail(eMsg);
                    }

                    @Override
                    public void onComplete(@NonNull BasePageResponse<KnowCatalog> data) {
                        presenter.searchKnowSuccess(data);

                    }
                });
    }

    @Override
    public void searchParts(String keyword,int current) {
        api.searchProduct(keyword,current+"")
                .compose(TransformersFactory.<BasePageResponse<Parts>>commonTransformer((BaseMVPActivity) presenter.getView()))
                .subscribe(new EObserver<BasePageResponse<Parts>>() {
                    @Override
                    public void onError(ResponseThrowable e, String eMsg) {
                        presenter.getView().searchFail(eMsg);
                    }

                    @Override
                    public void onComplete(@NonNull BasePageResponse<Parts> data) {
                        presenter.searchPartsSuccess(data);

                    }
                });
    }

    @Override
    public void searchExplain(String keyword,int current) {
        api.searchExplain(keyword,current+"")
                .compose(TransformersFactory.<BasePageResponse<ExplainCatalog>>commonTransformer((BaseMVPActivity) presenter.getView()))
                .subscribe(new EObserver<BasePageResponse<ExplainCatalog>>() {
                    @Override
                    public void onError(ResponseThrowable e, String eMsg) {
                        presenter.getView().searchFail(eMsg);
                    }

                    @Override
                    public void onComplete(@NonNull BasePageResponse<ExplainCatalog> data) {
                        presenter.searchExplainCatalogSuccess(data);
                    }
                });
    }
}
