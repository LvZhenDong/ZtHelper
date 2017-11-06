package com.egr.drillinghelper.model;

import com.egr.drillinghelper.api.NetApi;
import com.egr.drillinghelper.api.error.EObserver;
import com.egr.drillinghelper.api.error.ERROR;
import com.egr.drillinghelper.api.error.ResponseThrowable;
import com.egr.drillinghelper.bean.base.BasePage;
import com.egr.drillinghelper.bean.base.BaseResponseBean;
import com.egr.drillinghelper.bean.response.KnowCatalog;
import com.egr.drillinghelper.bean.response.ServiceMsg;
import com.egr.drillinghelper.contract.ServiceContract;
import com.egr.drillinghelper.factory.APIServiceFactory;
import com.egr.drillinghelper.factory.TransformersFactory;
import com.egr.drillinghelper.mvp.BaseModel;
import com.egr.drillinghelper.presenter.ServicePresenterImpl;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;

import java.util.List;
import java.util.Map;

import io.reactivex.annotations.NonNull;
import okhttp3.RequestBody;

/**
 * author lzd
 * date 2017/9/26 16:40
 * 类描述：
 */

public class ServiceModelImpl extends BaseModel<ServicePresenterImpl> implements ServiceContract.Model {
    private NetApi api;

    public ServiceModelImpl(ServicePresenterImpl ServicePresenter) {
        super(ServicePresenter);
        api = APIServiceFactory.getInstance().createService();
    }

    @Override
    public void sendMsg(String msg) {
        api.sendServiceMsg(msg)
                .compose(TransformersFactory.<List<KnowCatalog>>commonTransformer((BaseMVPActivity) presenter.getView()))
                .subscribe(new EObserver<List<KnowCatalog>>() {
                    @Override
                    public void onError(ResponseThrowable e, String eMsg) {
                        if (e.code == ERROR.UNKNOWN)
                            presenter.getView().sendMsgSuc(null);
                        else
                            presenter.getView().sendMsgFail();
                    }

                    @Override
                    public void onComplete(@NonNull List<KnowCatalog> response) {
                        getLatest();        //返回body不为null,去拿最新的一条数据就是匹配数据
                        presenter.getView().sendMsgSuc(response);
                    }
                });
    }


    @Override
    public void sendPhoto(Map<String, RequestBody> photo) {
        api.sendServiceMsg(photo)
                .compose(TransformersFactory.emptyTrans((BaseMVPActivity) presenter.getView()))
                .subscribe(new EObserver<BaseResponseBean>() {
                    @Override
                    public void onError(ResponseThrowable e, String eMsg) {
                        presenter.getView().sendMsgFail();
                    }

                    @Override
                    public void onComplete(@NonNull BaseResponseBean response) {
                        presenter.getView().sendMsgSuc(null);
                    }
                });
    }

    @Override
    public void getLatest() {
        api.getServiceMsg("1", "1")
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
    public void resolved(String id) {
        api.resolved(id)
                .compose(TransformersFactory.emptyTrans((BaseMVPActivity) presenter.getView()))
                .subscribe(new EObserver<BaseResponseBean>() {
                    @Override
                    public void onError(ResponseThrowable e, String eMsg) {
                        presenter.getView().getMsgFail(eMsg);
                    }

                    @Override
                    public void onComplete(@NonNull BaseResponseBean response) {
                        presenter.getView().noNeed();
                    }
                });
    }

    @Override
    public void unsolved(String id) {
        api.unsolved(id)
                .compose(TransformersFactory.<String>commonTransformer((BaseMVPActivity) presenter.getView()))
                .subscribe(new EObserver<String>() {
                    @Override
                    public void onError(ResponseThrowable e, String eMsg) {
                        presenter.getView().getMsgFail(eMsg);
                    }

                    @Override
                    public void onComplete(@NonNull String response) {
                        presenter.getView().resolvedSuc();
                        getLatest();
                    }
                });
    }

    @Override
    public void getMsg(int current, int size) {
        api.getServiceMsg(current + "", size + "")
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
