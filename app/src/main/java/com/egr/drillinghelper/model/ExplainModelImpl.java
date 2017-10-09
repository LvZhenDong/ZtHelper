package com.egr.drillinghelper.model;

import com.egr.drillinghelper.api.NetApi;
import com.egr.drillinghelper.api.error.EObserver;
import com.egr.drillinghelper.api.error.ResponseThrowable;
import com.egr.drillinghelper.bean.response.Explain;
import com.egr.drillinghelper.contract.ExplainContract;
import com.egr.drillinghelper.factory.APIServiceFactory;
import com.egr.drillinghelper.factory.TransformersFactory;
import com.egr.drillinghelper.mvp.BaseMVPFragment;
import com.egr.drillinghelper.mvp.BaseModel;
import com.egr.drillinghelper.presenter.ExplainPresenterImpl;

import io.reactivex.annotations.NonNull;

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
        api.explainList(current + "")
                .compose(TransformersFactory.<Explain>commonTransformer((BaseMVPFragment) presenter.getView()))
                .subscribe(new EObserver<Explain>() {
                    @Override
                    public void onError(ResponseThrowable e, String eMsg) {
                        presenter.getView().getExplainFail(eMsg);
                    }

                    @Override
                    public void onComplete(@NonNull Explain explain) {
                        presenter.getExplainListSuccess(explain);
                    }
                });
    }
}
