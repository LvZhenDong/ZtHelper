package com.egr.drillinghelper.presenter;

import com.egr.drillinghelper.contract.MessageContract;
import com.egr.drillinghelper.model.MessageModelImpl;
import com.egr.drillinghelper.mvp.BasePresenter;
import com.egr.drillinghelper.mvp.IModel;

/**
 * author lzd
 * date 2017/9/26 16:41
 * 类描述：
 */

public class MessagePresenterImpl extends BasePresenter<MessageContract.View,
        MessageModelImpl> implements MessageContract.Presenter{
    @Override
    protected IModel createModel() {
        return new MessageModelImpl(this);
    }
}
