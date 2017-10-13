package com.egr.drillinghelper.model;

import com.egr.drillinghelper.api.NetApi;
import com.egr.drillinghelper.api.error.EObserver;
import com.egr.drillinghelper.api.error.ResponseThrowable;
import com.egr.drillinghelper.bean.base.BasePage;
import com.egr.drillinghelper.bean.response.Message;
import com.egr.drillinghelper.contract.MessageDetailContract;
import com.egr.drillinghelper.factory.APIServiceFactory;
import com.egr.drillinghelper.factory.TransformersFactory;
import com.egr.drillinghelper.mvp.BaseModel;
import com.egr.drillinghelper.presenter.MessageDetailPresenterImpl;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;

import io.reactivex.annotations.NonNull;

import static android.R.attr.data;
import static com.egr.drillinghelper.factory.TransformersFactory.commonTransformer;
import static com.pgyersdk.c.a.e;

/**
 * author lzd
 * date 2017/9/26 16:40
 * 类描述：
 */

public class MessageDetailModelImpl extends BaseModel<MessageDetailPresenterImpl>
        implements MessageDetailContract.Model {
    NetApi api;
    public MessageDetailModelImpl(MessageDetailPresenterImpl MessageDetailPresenter) {
        super(MessageDetailPresenter);
        api = APIServiceFactory.getInstance().createService();
    }

    @Override
    public void getMsgDetail(String id) {
        api.getMsgDetail(id)
                .compose(TransformersFactory.<Message>commonTransformer((BaseMVPActivity) presenter.getView()))
                .subscribe(new EObserver<Message>() {
                    @Override
                    public void onError(ResponseThrowable e, String eMsg) {
                        presenter.getView().getMsgFail(eMsg);
                    }

                    @Override
                    public void onComplete(@NonNull Message data) {
                        presenter.getView().getMsgSuccess(data);
                    }
                });
    }
}
