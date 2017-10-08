package com.egr.drillinghelper.model;

import com.egr.drillinghelper.api.NetApi;
import com.egr.drillinghelper.api.error.EObserver;
import com.egr.drillinghelper.api.error.ResponseThrowable;
import com.egr.drillinghelper.bean.response.ExplainCatalog;
import com.egr.drillinghelper.contract.ExplainCatalogContract;
import com.egr.drillinghelper.factory.APIServiceFactory;
import com.egr.drillinghelper.factory.TransformersFactory;
import com.egr.drillinghelper.mvp.BaseModel;
import com.egr.drillinghelper.presenter.ExplainCatalogPresenterImpl;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;

import java.util.List;

import io.reactivex.annotations.NonNull;

/**
 * author lzd
 * date 2017/9/26 16:40
 * 类描述：
 */

public class ExplainCatalogModelImpl extends BaseModel<ExplainCatalogPresenterImpl>
        implements ExplainCatalogContract.Model{
    private NetApi api;
    public ExplainCatalogModelImpl(ExplainCatalogPresenterImpl presenter) {
        super(presenter);
        api = APIServiceFactory.getInstance().createService(NetApi.class);
    }

    @Override
    public void explainCatalog(String id) {
        api.explainCatalog(id)
                .compose(TransformersFactory.<List<ExplainCatalog>>commonTransformer((BaseMVPActivity) presenter.getView()))
                .subscribe(new EObserver<List<ExplainCatalog>>() {
                    @Override
                    public void onError(ResponseThrowable e, String eMsg) {
                        presenter.getView().getCatalogFail(eMsg);
                    }

                    @Override
                    public void onComplete(@NonNull List<ExplainCatalog> data) {
                        presenter.getCatalogSuccess(data);
                    }
                });
    }
}
