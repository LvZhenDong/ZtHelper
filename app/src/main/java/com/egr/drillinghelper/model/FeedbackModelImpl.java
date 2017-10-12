package com.egr.drillinghelper.model;

import com.egr.drillinghelper.api.NetApi;
import com.egr.drillinghelper.api.error.EObserver;
import com.egr.drillinghelper.api.error.ResponseThrowable;
import com.egr.drillinghelper.bean.response.Feedback;
import com.egr.drillinghelper.contract.FeedbackContract;
import com.egr.drillinghelper.factory.APIServiceFactory;
import com.egr.drillinghelper.factory.TransformersFactory;
import com.egr.drillinghelper.mvp.BaseMVPFragment;
import com.egr.drillinghelper.mvp.BaseModel;
import com.egr.drillinghelper.presenter.FeedbackPresenterImpl;

import java.util.List;

import io.reactivex.annotations.NonNull;

import static com.egr.drillinghelper.factory.TransformersFactory.commonTransformer;

/**
 * author lzd
 * date 2017/9/26 16:40
 * 类描述：
 */

public class FeedbackModelImpl extends BaseModel<FeedbackPresenterImpl> implements FeedbackContract.Model{
    private NetApi api;

    public FeedbackModelImpl(FeedbackPresenterImpl presenter) {
        super(presenter);
        api = APIServiceFactory.getInstance().createService();
    }

    @Override
    public void getFeedbackList() {
        api.getFeedbackList()
                .compose(TransformersFactory.<List<Feedback>>commonTransformer((BaseMVPFragment) presenter.getView()))
                .subscribe(new EObserver<List<Feedback>>() {
                    @Override
                    public void onError(ResponseThrowable e, String eMsg) {
                        presenter.getView().getFeedbackFail(eMsg);
                    }

                    @Override
                    public void onComplete(@NonNull List<Feedback> list) {
                        presenter.getView().getFeedbackListSuccess(list);
                    }
                });
    }
}
