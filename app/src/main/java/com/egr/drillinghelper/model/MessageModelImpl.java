package com.egr.drillinghelper.model;

import com.egr.drillinghelper.api.NetApi;
import com.egr.drillinghelper.api.error.EObserver;
import com.egr.drillinghelper.api.error.ResponseThrowable;
import com.egr.drillinghelper.bean.base.BasePage;
import com.egr.drillinghelper.bean.response.KnowCatalog;
import com.egr.drillinghelper.bean.response.Message;
import com.egr.drillinghelper.contract.MessageContract;
import com.egr.drillinghelper.factory.APIServiceFactory;
import com.egr.drillinghelper.factory.TransformersFactory;
import com.egr.drillinghelper.mvp.BaseModel;
import com.egr.drillinghelper.presenter.MessagePresenterImpl;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;

import io.reactivex.annotations.NonNull;

import static com.egr.drillinghelper.factory.TransformersFactory.commonTransformer;

/**
 * author lzd
 * date 2017/9/26 16:40
 * 类描述：
 */

public class MessageModelImpl extends BaseModel<MessagePresenterImpl> implements MessageContract.Model {
    NetApi api;

    public MessageModelImpl(MessagePresenterImpl presenter) {
        super(presenter);
        api = APIServiceFactory.getInstance().createService();
    }

    @Override
    public void getMsgList(int current) {
        api.getMsgList(""+current)
                .compose(TransformersFactory.<BasePage<Message>>commonTransformer((BaseMVPActivity) presenter.getView()))
                .subscribe(new EObserver<BasePage<Message>>() {
                    @Override
                    public void onError(ResponseThrowable e, String eMsg) {
                        presenter.getView().getMsgListFail(eMsg);
                    }

                    @Override
                    public void onComplete(@NonNull BasePage<Message> data) {
                        presenter.getMsgListSuccess(data);
                    }
                });
    }
}
