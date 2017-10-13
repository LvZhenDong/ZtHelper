package com.egr.drillinghelper.presenter;

import com.egr.drillinghelper.contract.MessageDetailContract;
import com.egr.drillinghelper.model.MessageDetailModelImpl;
import com.egr.drillinghelper.mvp.BasePresenter;
import com.egr.drillinghelper.mvp.IModel;

/**
 * author lzd
 * date 2017/9/26 16:41
 * 类描述：
 */

public class MessageDetailPresenterImpl extends BasePresenter<MessageDetailContract.View,
        MessageDetailModelImpl> implements MessageDetailContract.Presenter{
    @Override
    protected IModel createModel() {
        return new MessageDetailModelImpl(this);
    }

    @Override
    public void getMsgDetail(String id) {
        mModel.getMsgDetail(id);
    }
}
