package com.egr.drillinghelper.presenter;

import com.egr.drillinghelper.bean.response.ExplainCatalog;
import com.egr.drillinghelper.contract.ExplainCatalogContract;
import com.egr.drillinghelper.model.ExplainCatalogModelImpl;
import com.egr.drillinghelper.mvp.BasePresenter;
import com.egr.drillinghelper.mvp.IModel;

import java.util.ArrayList;
import java.util.List;

/**
 * author lzd
 * date 2017/9/26 16:41
 * 类描述：
 */

public class ExplainCatalogPresenterImpl extends BasePresenter<ExplainCatalogContract.View,
        ExplainCatalogModelImpl> implements ExplainCatalogContract.Presenter {
    List<ExplainCatalog> list = new ArrayList<>();
    int deep;

    @Override
    protected IModel createModel() {
        return new ExplainCatalogModelImpl(this);
    }

    @Override
    public void getExplainCatalog(String id) {
        mModel.explainCatalog(id);
    }

    @Override
    public void getCatalogSuccess(List<ExplainCatalog> catalogList) {
        getList(catalogList);
        getView().getCatalogSuccess(list);
    }

    private void getList(List<ExplainCatalog> catalogList) {
        deep++;
        for (ExplainCatalog item : catalogList) {
            item.setDeep(deep);
            list.add(item);
            if (item.getChilds() != null && item.getChilds().size() != 0)
                getList(item.getChilds());

        }
        deep--;
    }
}
