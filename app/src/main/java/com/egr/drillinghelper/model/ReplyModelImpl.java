package com.egr.drillinghelper.model;

import com.egr.drillinghelper.api.NetApi;
import com.egr.drillinghelper.api.error.EObserver;
import com.egr.drillinghelper.api.error.ResponseThrowable;
import com.egr.drillinghelper.bean.response.Explain;
import com.egr.drillinghelper.bean.response.Reply;
import com.egr.drillinghelper.contract.ReplyContract;
import com.egr.drillinghelper.factory.APIServiceFactory;
import com.egr.drillinghelper.factory.TransformersFactory;
import com.egr.drillinghelper.mvp.BaseMVPFragment;
import com.egr.drillinghelper.mvp.BaseModel;
import com.egr.drillinghelper.presenter.ReplyPresenterImpl;

import io.reactivex.annotations.NonNull;

/**
 * author lzd
 * date 2017/9/26 16:40
 * 类描述：
 */

public class ReplyModelImpl extends BaseModel<ReplyPresenterImpl>
        implements ReplyContract.Model{
    private NetApi api;
    public ReplyModelImpl(ReplyPresenterImpl presenter) {
        super(presenter);
        api = APIServiceFactory.getInstance().createService();
    }

    @Override
    public void getReply(String status,int current) {
        api.getReplyList(status,current+"")
                .compose(TransformersFactory.<Reply>commonTransformer((BaseMVPFragment) presenter.getView()))
                .subscribe(new EObserver<Reply>() {
                    @Override
                    public void onError(ResponseThrowable e, String eMsg) {
                        presenter.getView().getReplyFail(eMsg);
                    }

                    @Override
                    public void onComplete(@NonNull Reply reply) {
                        presenter.getReplySuccess(reply);
                    }
                });
    }
}
