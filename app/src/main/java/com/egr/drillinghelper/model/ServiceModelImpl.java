package com.egr.drillinghelper.model;

import com.egr.drillinghelper.api.NetApi;
import com.egr.drillinghelper.api.error.EObserver;
import com.egr.drillinghelper.api.error.ERROR;
import com.egr.drillinghelper.api.error.ResponseThrowable;
import com.egr.drillinghelper.bean.base.BasePage;
import com.egr.drillinghelper.bean.response.ContactUs;
import com.egr.drillinghelper.bean.response.KnowCatalog;
import com.egr.drillinghelper.bean.response.NullBodyResponse;
import com.egr.drillinghelper.bean.response.Reply;
import com.egr.drillinghelper.bean.response.ServiceMsg;
import com.egr.drillinghelper.contract.ServiceContract;
import com.egr.drillinghelper.factory.APIServiceFactory;
import com.egr.drillinghelper.factory.TransformersFactory;
import com.egr.drillinghelper.mvp.BaseMVPFragment;
import com.egr.drillinghelper.mvp.BaseModel;
import com.egr.drillinghelper.presenter.ServicePresenterImpl;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;

import java.util.Map;

import io.reactivex.annotations.NonNull;
import okhttp3.RequestBody;

/**
 * author lzd
 * date 2017/9/26 16:40
 * 类描述：
 */

public class ServiceModelImpl extends BaseModel<ServicePresenterImpl> implements ServiceContract.Model{
    public ServiceModelImpl(ServicePresenterImpl ServicePresenter) {
        super(ServicePresenter);
        api = APIServiceFactory.getInstance().createService();
    }
    private NetApi api;

    @Override
    public void sendMsg(String msg) {
        api.sendServiceMsg(msg)
                .compose(TransformersFactory.nullBodyTransformer((BaseMVPActivity) presenter.getView()))
                .subscribe(new EObserver<NullBodyResponse>() {
                    @Override
                    public void onError(ResponseThrowable e, String eMsg) {
                        presenter.getView().sendMsgFail();
                    }

                    @Override
                    public void onComplete(@NonNull NullBodyResponse response) {
                        presenter.getView().sendMsgSuc();
                    }
                });
    }


    @Override
    public void sendPhoto(Map<String, RequestBody> photo) {
        api.sendServiceMsg(photo)
                .compose(TransformersFactory.nullBodyTransformer((BaseMVPActivity) presenter.getView()))
                .subscribe(new EObserver<NullBodyResponse>() {
                    @Override
                    public void onError(ResponseThrowable e, String eMsg) {
                        presenter.getView().sendMsgFail();
                    }

                    @Override
                    public void onComplete(@NonNull NullBodyResponse response) {
                        presenter.getView().sendMsgSuc();
                    }
                });
    }

    @Override
    public void getLatest() {
        api.getServiceMsg("1","1")
                .compose(TransformersFactory.<BasePage<ServiceMsg>>commonTransformer((BaseMVPActivity) presenter.getView()))
                .subscribe(new EObserver<BasePage<ServiceMsg>>() {
                    @Override
                    public void onError(ResponseThrowable e, String eMsg) {
                        presenter.getView().getMsgFail(eMsg);
                    }

                    @Override
                    public void onComplete(@NonNull BasePage<ServiceMsg> data) {
                        presenter.getView().getLatestSuc(data);
                    }
                });
    }

    @Override
    public void getMsg(int current,int size) {
        api.getServiceMsg(current+"",size+"")
                .compose(TransformersFactory.<BasePage<ServiceMsg>>commonTransformer((BaseMVPActivity) presenter.getView()))
                .subscribe(new EObserver<BasePage<ServiceMsg>>() {
                    @Override
                    public void onError(ResponseThrowable e, String eMsg) {
                            presenter.getView().getMsgFail(eMsg);
                    }

                    @Override
                    public void onComplete(@NonNull BasePage<ServiceMsg> data) {
                        presenter.getMsgSuc(data);
                    }
                });
    }



}
