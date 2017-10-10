package com.egr.drillinghelper.presenter;

import com.egr.drillinghelper.contract.FeedbackContract;
import com.egr.drillinghelper.model.FeedbackModelImpl;
import com.egr.drillinghelper.mvp.BasePresenter;
import com.egr.drillinghelper.mvp.IModel;

/**
 * author lzd
 * date 2017/9/26 16:41
 * 类描述：
 */

public class FeedbackPresenterImpl extends BasePresenter<FeedbackContract.View,
        FeedbackModelImpl> implements FeedbackContract.Presenter{
    @Override
    protected IModel createModel() {
        return new FeedbackModelImpl(this);
    }

    @Override
    public void getFeedbackList() {
        mModel.getFeedbackList();
    }
}
