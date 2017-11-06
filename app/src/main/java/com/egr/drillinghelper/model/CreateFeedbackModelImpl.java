package com.egr.drillinghelper.model;

import com.egr.drillinghelper.api.NetApi;
import com.egr.drillinghelper.api.error.EObserver;
import com.egr.drillinghelper.api.error.ResponseThrowable;
import com.egr.drillinghelper.bean.response.Feedback;
import com.egr.drillinghelper.contract.CreateFeedbackContract;
import com.egr.drillinghelper.factory.APIServiceFactory;
import com.egr.drillinghelper.factory.TransformersFactory;
import com.egr.drillinghelper.mvp.BaseModel;
import com.egr.drillinghelper.presenter.CreateFeedbackPresenterImpl;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;

import java.util.List;
import java.util.Map;

import io.reactivex.annotations.NonNull;
import okhttp3.RequestBody;

/**
 * author lzd
 * date 2017/9/26 16:40
 * 类描述：
 */

public class CreateFeedbackModelImpl extends BaseModel<CreateFeedbackPresenterImpl> implements CreateFeedbackContract.Model{

    private NetApi api;

    public CreateFeedbackModelImpl(CreateFeedbackPresenterImpl presenter) {
        super(presenter);
        api = APIServiceFactory.getInstance().createService();
    }

    @Override
    public void createFeedback(String question) {
        api.createFeedback(question)
                .compose(TransformersFactory.<List<Feedback>>commonTransformer((BaseMVPActivity) presenter.getView()))
                .subscribe(new EObserver<List<Feedback>>() {
                    @Override
                    public void onError(ResponseThrowable e, String eMsg) {
                        presenter.getView().commitFail(eMsg);
                    }

                    @Override
                    public void onComplete(@NonNull List<Feedback> userInfo) {
                        presenter.getView().commitSuccess(userInfo);
                    }
                });
    }

    @Override
    public void createFeedback(String question, Map<String, RequestBody> photos) {
        api.createFeedback(question,photos)
                .compose(TransformersFactory.<List<Feedback>>commonTransformer((BaseMVPActivity) presenter.getView()))
                .subscribe(new EObserver<List<Feedback>>() {
                    @Override
                    public void onError(ResponseThrowable e, String eMsg) {
                        presenter.getView().commitFail(eMsg);
                    }

                    @Override
                    public void onComplete(@NonNull List<Feedback> userInfo) {
                        presenter.getView().commitSuccess(userInfo);
                    }
                });
    }
}
