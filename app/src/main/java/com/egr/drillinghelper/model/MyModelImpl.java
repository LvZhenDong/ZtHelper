package com.egr.drillinghelper.model;

import com.egr.drillinghelper.api.NetApi;
import com.egr.drillinghelper.api.error.EObserver;
import com.egr.drillinghelper.api.error.ResponseThrowable;
import com.egr.drillinghelper.bean.response.NullBodyResponse;
import com.egr.drillinghelper.bean.response.Share;
import com.egr.drillinghelper.bean.response.UserInfo;
import com.egr.drillinghelper.contract.MyContract;
import com.egr.drillinghelper.factory.APIServiceFactory;
import com.egr.drillinghelper.factory.TransformersFactory;
import com.egr.drillinghelper.mvp.BaseMVPFragment;
import com.egr.drillinghelper.mvp.BaseModel;
import com.egr.drillinghelper.presenter.MyPresenterImpl;

import io.reactivex.annotations.NonNull;

/**
 * author lzd
 * date 2017/9/26 16:40
 * 类描述：
 */

public class MyModelImpl extends BaseModel<MyPresenterImpl> implements MyContract.Model {

    private NetApi api;

    public MyModelImpl(MyPresenterImpl presenter) {
        super(presenter);
        api = APIServiceFactory.getInstance().createService();
    }

    @Override
    public void userInfo() {
        api.userInfo()
                .compose(TransformersFactory.<UserInfo>commonTransformer((BaseMVPFragment) presenter.getView()))
                .subscribe(new EObserver<UserInfo>() {
                    @Override
                    public void onError(ResponseThrowable e, String eMsg) {

                    }

                    @Override
                    public void onComplete(@NonNull UserInfo userInfo) {
                        presenter.getView().getUserInfoSuccess(userInfo);
                    }
                });
    }

    @Override
    public void logout() {
        api.logout()
                .compose(TransformersFactory.nullBodyTransformer((BaseMVPFragment) presenter.getView()))
                .subscribe(new EObserver<NullBodyResponse>() {
                    @Override
                    public void onError(ResponseThrowable e, String eMsg) {
                        presenter.getView().logoutFail(eMsg);
                    }

                    @Override
                    public void onComplete(@NonNull NullBodyResponse data) {
                        presenter.getView().logoutSuccess();
                    }
                });
    }

    @Override
    public void getShareContent() {
        api.share()
                .compose(TransformersFactory.<Share>commonTransformer((BaseMVPFragment) presenter.getView()))
                .subscribe(new EObserver<Share>() {
                    @Override
                    public void onError(ResponseThrowable e, String eMsg) {
                        presenter.getView().logoutFail(eMsg);
                    }

                    @Override
                    public void onComplete(@NonNull Share share) {
                        presenter.getView().getShareContentSuccess(share);
                    }
                });
    }
}
