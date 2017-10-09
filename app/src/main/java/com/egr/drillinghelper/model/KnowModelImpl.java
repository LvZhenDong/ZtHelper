package com.egr.drillinghelper.model;

import com.egr.drillinghelper.api.NetApi;
import com.egr.drillinghelper.api.error.EObserver;
import com.egr.drillinghelper.api.error.ResponseThrowable;
import com.egr.drillinghelper.bean.response.Explain;
import com.egr.drillinghelper.contract.KnowContract;
import com.egr.drillinghelper.factory.APIServiceFactory;
import com.egr.drillinghelper.factory.TransformersFactory;
import com.egr.drillinghelper.mvp.BaseMVPFragment;
import com.egr.drillinghelper.mvp.BaseModel;
import com.egr.drillinghelper.presenter.KnowPresenterImpl;

import io.reactivex.annotations.NonNull;

/**
 * author lzd
 * date 2017/9/26 16:40
 * 类描述：
 */

public class KnowModelImpl extends BaseModel<KnowPresenterImpl> implements KnowContract.Model {
    private NetApi api;

    public KnowModelImpl(KnowPresenterImpl presenter) {
        super(presenter);
        api = APIServiceFactory.getInstance().createService();
    }

    @Override
    public void getKnowList(int current) {
        api.knowList(current + "")
                .compose(TransformersFactory.<Explain>commonTransformer((BaseMVPFragment) presenter.getView()))
                .subscribe(new EObserver<Explain>() {
                    @Override
                    public void onError(ResponseThrowable e, String eMsg) {
                        presenter.getView().getKnowFail(eMsg);
                    }

                    @Override
                    public void onComplete(@NonNull Explain Know) {
                        presenter.getKnowListSuccess(Know);
                    }
                });
    }
}
