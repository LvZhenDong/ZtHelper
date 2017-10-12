package com.egr.drillinghelper.presenter;

import com.egr.drillinghelper.bean.base.BasePage;
import com.egr.drillinghelper.bean.response.Message;
import com.egr.drillinghelper.contract.MessageContract;
import com.egr.drillinghelper.model.MessageModelImpl;
import com.egr.drillinghelper.mvp.BasePresenter;
import com.egr.drillinghelper.mvp.IModel;
import com.egr.drillinghelper.utils.CollectionUtil;

/**
 * author lzd
 * date 2017/9/26 16:41
 * 类描述：
 */

public class MessagePresenterImpl extends BasePresenter<MessageContract.View,
        MessageModelImpl> implements MessageContract.Presenter {
    int current;

    @Override
    protected IModel createModel() {
        return new MessageModelImpl(this);
    }

    @Override
    public void getMsgList() {
        current = 1;
        mModel.getMsgList(current);
    }

    @Override
    public void getMsgListSuccess(BasePage<Message> data) {
        if (data == null || CollectionUtil.isListEmpty(data.getRecords())) {
            getView().noMoreData();
        } else {
            current = data.getCurrent();
            getView().getMsgListSuccess(data);
        }
    }

    @Override
    public void loadMore() {
        mModel.getMsgList(current + 1);
    }
}
