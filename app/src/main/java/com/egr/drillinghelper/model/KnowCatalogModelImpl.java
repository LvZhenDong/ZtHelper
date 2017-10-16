package com.egr.drillinghelper.model;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.api.NetApi;
import com.egr.drillinghelper.api.error.EObserver;
import com.egr.drillinghelper.api.error.ResponseThrowable;
import com.egr.drillinghelper.bean.response.Explain;
import com.egr.drillinghelper.bean.response.ExplainCatalog;
import com.egr.drillinghelper.bean.response.KnowCatalog;
import com.egr.drillinghelper.contract.KnowCatalogContract;
import com.egr.drillinghelper.factory.APIServiceFactory;
import com.egr.drillinghelper.factory.TransformersFactory;
import com.egr.drillinghelper.mvp.BaseModel;
import com.egr.drillinghelper.presenter.KnowCatalogPresenterImpl;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;
import com.egr.drillinghelper.utils.CacheUtils;
import com.egr.drillinghelper.utils.NetworkUtils;

import java.util.List;

import io.reactivex.annotations.NonNull;

import static com.egr.drillinghelper.api.error.ERROR.TIMEOUT_ERROR;

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
    public void knowCatalog(final String id) {
        if(NetworkUtils.isNetworkConnected(getContext())) {
            api.knowCatalog(id)
                    .compose(TransformersFactory.<List<KnowCatalog>>commonTransformer((BaseMVPActivity) presenter.getView()))
                    .subscribe(new EObserver<List<KnowCatalog>>() {
                        @Override
                        public void onError(ResponseThrowable e, String eMsg) {
                            if (e.code == TIMEOUT_ERROR)
                                showCache(id);
                            else
                                presenter.getView().getCatalogFail(eMsg);
                        }

                        @Override
                        public void onComplete(@NonNull List<KnowCatalog> data) {
                            addNumber(data);
                            presenter.getCatalogSuccess(data);
                        }
                    });
        }else {
            showCache(id);
        }
    }

    private void showCache(String id){
        try {
            Explain explain= CacheUtils.getKnow(id);
            if(explain != null){
                List<KnowCatalog> list=explain.getKnows();
                addNumber(list);

                presenter.getView().getCatalogCache(list);
            } else
                presenter.getView().getCatalogFail(getContext().getString(R.string.no_data));
        } catch (Exception e) {
            presenter.getView().getCatalogFail(getContext().getString(R.string.net_error));
        }
    }

    private void addNumber(List<KnowCatalog> list){
        for(int i=1;i<list.size()+1;i++){
            KnowCatalog item=list.get(i-1);
            item.setTitle((i+"."+item.getTitle()));
        }
    }
}
