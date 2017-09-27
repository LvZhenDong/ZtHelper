package com.egr.drillinghelper.presenter;

import com.egr.drillinghelper.contract.CreateFeedbackContract;
import com.egr.drillinghelper.model.CreateFeedbackModelImpl;
import com.egr.drillinghelper.mvp.BasePresenter;
import com.egr.drillinghelper.mvp.IModel;

/**
 * author lzd
 * date 2017/9/26 16:41
 * 类描述：
 */

public class CreateFeedbackPresenterImpl extends BasePresenter<CreateFeedbackContract.View,
        CreateFeedbackModelImpl> implements CreateFeedbackContract.Presenter{
    @Override
    protected IModel createModel() {
        return new CreateFeedbackModelImpl(this);
    }
}
