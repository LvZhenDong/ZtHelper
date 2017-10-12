package com.egr.drillinghelper.model;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.api.NetApi;
import com.egr.drillinghelper.api.error.EObserver;
import com.egr.drillinghelper.api.error.ResponseThrowable;
import com.egr.drillinghelper.bean.base.BasePage;
import com.egr.drillinghelper.bean.response.Explain;
import com.egr.drillinghelper.contract.ExplainContract;
import com.egr.drillinghelper.factory.APIServiceFactory;
import com.egr.drillinghelper.factory.TransformersFactory;
import com.egr.drillinghelper.mvp.BaseMVPFragment;
import com.egr.drillinghelper.mvp.BaseModel;
import com.egr.drillinghelper.presenter.ExplainPresenterImpl;
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

public class ExplainModelImpl extends BaseModel<ExplainPresenterImpl> implements ExplainContract.Model {

    private NetApi api;

    public ExplainModelImpl(ExplainPresenterImpl presenter) {
        super(presenter);
        api = APIServiceFactory.getInstance().createService();
    }

    @Override
    public void getExplainList(int current) {
        if (NetworkUtils.isNetworkConnected(getContext())) {
            api.explainList(current + "")
                    .compose(TransformersFactory.<BasePage<Explain>>commonTransformer((BaseMVPFragment) presenter.getView()))
                    .subscribe(new EObserver<BasePage<Explain>>() {
                        @Override
                        public void onError(ResponseThrowable e, String eMsg) {
                            if (e.code == TIMEOUT_ERROR)
                                showCache();
                            else
                                presenter.getView().getExplainFail(eMsg);
                        }

                        @Override
                        public void onComplete(@NonNull BasePage<Explain> data) {
                            presenter.getExplainListSuccess(data);
                        }
                    });
        } else {
            showCache();
        }

    }

    private void showCache() {
        try {
            presenter.getView().showExplainCache(CacheUtils.getExplains());
        } catch (Exception e) {
            presenter.getView().getExplainFail(getContext().getString(R.string.net_error));
        }
    }

    @Override
    public void getExplainCache() {
        api.getExplainCache()
                .compose(TransformersFactory.<List<Explain>>commonTransformer((BaseMVPFragment) presenter.getView()))
                .subscribe(new EObserver<List<Explain>>() {
                    @Override
                    public void onError(ResponseThrowable e, String eMsg) {

                    }

                    @Override
                    public void onComplete(@NonNull List<Explain> data) {
                        CacheUtils.saveExplains(data);
                    }
                });
    }
}
