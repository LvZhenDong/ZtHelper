package com.egr.drillinghelper.presenter;

import com.egr.drillinghelper.bean.response.KnowCatalog;
import com.egr.drillinghelper.contract.KnowCatalogContract;
import com.egr.drillinghelper.model.KnowCatalogModelImpl;
import com.egr.drillinghelper.mvp.BasePresenter;
import com.egr.drillinghelper.mvp.IModel;

import java.util.List;

/**
 * author lzd
 * date 2017/9/26 16:41
 * 类描述：
 */

public class KnowCatalogPresenterImpl extends BasePresenter<KnowCatalogContract.View,
        KnowCatalogModelImpl> implements KnowCatalogContract.Presenter {
    @Override
    protected IModel createModel() {
        return new KnowCatalogModelImpl(this);
    }

    @Override
    public void getKnowCatalog(String id) {
        mModel.knowCatalog(id);
    }

    @Override
    public void getCatalogSuccess(List<KnowCatalog> catalogList) {
        getView().getCatalogSuccess(catalogList);
    }
}
