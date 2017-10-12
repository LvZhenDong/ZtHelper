package com.egr.drillinghelper.model;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.api.NetApi;
import com.egr.drillinghelper.api.error.EObserver;
import com.egr.drillinghelper.api.error.ResponseThrowable;
import com.egr.drillinghelper.bean.response.Explain;
import com.egr.drillinghelper.bean.response.ExplainCatalog;
import com.egr.drillinghelper.contract.ExplainCatalogContract;
import com.egr.drillinghelper.factory.APIServiceFactory;
import com.egr.drillinghelper.factory.TransformersFactory;
import com.egr.drillinghelper.mvp.BaseModel;
import com.egr.drillinghelper.presenter.ExplainCatalogPresenterImpl;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;
import com.egr.drillinghelper.utils.CacheUtils;
import com.egr.drillinghelper.utils.CollectionUtil;
import com.egr.drillinghelper.utils.NetworkUtils;

import java.util.List;

import io.reactivex.annotations.NonNull;

import static com.egr.drillinghelper.api.error.ERROR.TIMEOUT_ERROR;

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
        api = APIServiceFactory.getInstance().createService();
    }

    @Override
    public void explainCatalog(final String id) {
        if(NetworkUtils.isNetworkConnected(getContext())){
            api.explainCatalog(id)
                    .compose(TransformersFactory.<List<ExplainCatalog>>commonTransformer((BaseMVPActivity) presenter.getView()))
                    .subscribe(new EObserver<List<ExplainCatalog>>() {
                        @Override
                        public void onError(ResponseThrowable e, String eMsg) {
                            if (e.code == TIMEOUT_ERROR)
                                showCache(id);
                            else
                                presenter.getView().getCatalogFail(eMsg);
                        }

                        @Override
                        public void onComplete(@NonNull List<ExplainCatalog> data) {
                            presenter.getCatalogSuccess(data);
                        }
                    });
        }else {
            showCache(id);
        }

    }

    private void showCache(String id){
        try {
            Explain explain=CacheUtils.getExplain(id);
            if(explain != null)
                presenter.getCatalogSuccess(explain.getCatalogs());
            else
                presenter.getView().getCatalogFail(getContext().getString(R.string.no_data));
        } catch (Exception e) {
            presenter.getView().getCatalogFail(getContext().getString(R.string.net_error));
        }
    }
}
