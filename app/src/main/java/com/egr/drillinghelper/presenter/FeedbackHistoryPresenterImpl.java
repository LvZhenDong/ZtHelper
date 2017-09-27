package com.egr.drillinghelper.presenter;

import com.egr.drillinghelper.contract.FeedbackHistoryContract;
import com.egr.drillinghelper.model.FeedbackHistoryModelImpl;
import com.egr.drillinghelper.mvp.BasePresenter;
import com.egr.drillinghelper.mvp.IModel;

/**
 * author lzd
 * date 2017/9/26 16:41
 * 类描述：
 */

public class FeedbackHistoryPresenterImpl extends BasePresenter<FeedbackHistoryContract.View,
        FeedbackHistoryModelImpl> implements FeedbackHistoryContract.Presenter{
    @Override
    protected IModel createModel() {
        return new FeedbackHistoryModelImpl(this);
    }
}
