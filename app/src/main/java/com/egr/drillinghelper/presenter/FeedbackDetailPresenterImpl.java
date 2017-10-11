package com.egr.drillinghelper.presenter;

import com.egr.drillinghelper.bean.response.FeedbackDetail;
import com.egr.drillinghelper.contract.FeedbackDetailContract;
import com.egr.drillinghelper.model.FeedbackDetailModelImpl;
import com.egr.drillinghelper.mvp.BasePresenter;
import com.egr.drillinghelper.mvp.IModel;

/**
 * author lzd
 * date 2017/9/26 16:41
 * 类描述：
 */

public class FeedbackDetailPresenterImpl extends BasePresenter<FeedbackDetailContract.View,
        FeedbackDetailModelImpl> implements FeedbackDetailContract.Presenter{
    @Override
    protected IModel createModel() {
        return new FeedbackDetailModelImpl(this);
    }

    @Override
    public void getDetail(String id) {
        mModel.getDetail(id);
    }

    @Override
    public void getDetailSuccess(FeedbackDetail detail) {
        getView().getDetailSuccess(detail.getAnswer());
    }
}
