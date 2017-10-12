package com.egr.drillinghelper.presenter;

import com.egr.drillinghelper.bean.base.BasePage;
import com.egr.drillinghelper.bean.response.Reply;
import com.egr.drillinghelper.contract.ReplyContract;
import com.egr.drillinghelper.model.ReplyModelImpl;
import com.egr.drillinghelper.mvp.BasePresenter;
import com.egr.drillinghelper.mvp.IModel;
import com.egr.drillinghelper.utils.CollectionUtil;

import static com.egr.drillinghelper.R.string.explain;

/**
 * author lzd
 * date 2017/9/26 16:41
 * 类描述：
 */

public class ReplyPresenterImpl extends BasePresenter<ReplyContract.View,
        ReplyModelImpl> implements ReplyContract.Presenter{
    int current;
    @Override
    protected IModel createModel() {
        return new ReplyModelImpl(this);
    }

    @Override
    public void getReply(String status) {
        current=1;
        mModel.getReply(status,current);
    }

    @Override
    public void loadMore(String status) {
        mModel.getReply(status,current+1);
    }

    @Override
    public void getReplySuccess(BasePage<Reply> data) {
        if (data == null || CollectionUtil.isListEmpty(data.getRecords())){
            getView().noMoreData();
        } else{
            current = data.getCurrent();
            getView().getReplySuccess(data);
        }
    }
}
