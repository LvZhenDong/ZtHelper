package com.egr.drillinghelper.model;

import com.egr.drillinghelper.api.NetApi;
import com.egr.drillinghelper.api.error.EObserver;
import com.egr.drillinghelper.api.error.ResponseThrowable;
import com.egr.drillinghelper.bean.response.ExplainCatalog;
import com.egr.drillinghelper.bean.response.KnowCatalog;
import com.egr.drillinghelper.contract.KnowCatalogContract;
import com.egr.drillinghelper.factory.APIServiceFactory;
import com.egr.drillinghelper.factory.TransformersFactory;
import com.egr.drillinghelper.mvp.BaseModel;
import com.egr.drillinghelper.presenter.KnowCatalogPresenterImpl;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;

import java.util.List;

import io.reactivex.annotations.NonNull;

/**
 * author lzd
 * date 2017/9/26 16:40
 * 类描述：
 */

public class KnowCatalogModelImpl extends BaseModel<KnowCatalogPresenterImpl>
        implements KnowCatalogContract.Model{
    private NetApi api;
    public KnowCatalogModelImpl(KnowCatalogPresenterImpl presenter) {
        super(presenter);
        api = APIServiceFactory.getInstance().createService();
    }

    @Override
    public void knowCatalog(String id) {
        api.knowCatalog(id)
                .compose(TransformersFactory.<List<KnowCatalog>>commonTransformer((BaseMVPActivity) presenter.getView()))
                .subscribe(new EObserver<List<KnowCatalog>>() {
                    @Override
                    public void onError(ResponseThrowable e, String eMsg) {
                        presenter.getView().getCatalogFail(eMsg);
                    }

                    @Override
                    public void onComplete(@NonNull List<KnowCatalog> data) {
                        presenter.getCatalogSuccess(data);
                    }
                });
    }
}
