package com.egr.drillinghelper.model;

import com.egr.drillinghelper.api.NetApi;
import com.egr.drillinghelper.api.error.EObserver;
import com.egr.drillinghelper.api.error.ResponseThrowable;
import com.egr.drillinghelper.bean.response.FeedbackDetail;
import com.egr.drillinghelper.contract.FeedbackDetailContract;
import com.egr.drillinghelper.factory.APIServiceFactory;
import com.egr.drillinghelper.factory.TransformersFactory;
import com.egr.drillinghelper.mvp.BaseModel;
import com.egr.drillinghelper.presenter.FeedbackDetailPresenterImpl;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;

import io.reactivex.annotations.NonNull;

/**
 * author lzd
 * date 2017/9/26 16:40
 * 类描述：
 */

public class FeedbackDetailModelImpl extends BaseModel<FeedbackDetailPresenterImpl> implements FeedbackDetailContract.Model{
    public FeedbackDetailModelImpl(FeedbackDetailPresenterImpl presenter) {
        super(presenter);
        api = APIServiceFactory.getInstance().createService();
    }
    private NetApi api;
    @Override
    public void getDetail(String id) {
        api.getFeedbackDetail(id)
                .compose(TransformersFactory.<FeedbackDetail>commonTransformer((BaseMVPActivity) presenter.getView()))
                .subscribe(new EObserver<FeedbackDetail>() {
                    @Override
                    public void onError(ResponseThrowable e, String eMsg) {
                        presenter.getView().getDetailFail(eMsg);
                    }

                    @Override
                    public void onComplete(@NonNull FeedbackDetail detail) {
                        presenter.getDetailSuccess(detail);
                    }
                });
    }
}
