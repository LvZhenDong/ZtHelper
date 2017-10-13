package com.egr.drillinghelper.model;

import com.egr.drillinghelper.api.NetApi;
import com.egr.drillinghelper.api.error.EObserver;
import com.egr.drillinghelper.api.error.ResponseThrowable;
import com.egr.drillinghelper.bean.response.NullBodyResponse;
import com.egr.drillinghelper.contract.HomeContract;
import com.egr.drillinghelper.factory.APIServiceFactory;
import com.egr.drillinghelper.factory.TransformersFactory;
import com.egr.drillinghelper.mvp.BaseModel;
import com.egr.drillinghelper.presenter.HomePresenterImpl;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;

import io.reactivex.annotations.NonNull;

/**
 * author lzd
 * date 2017/9/26 16:40
 * 类描述：
 */

public class HomeModelImpl extends BaseModel<HomePresenterImpl> implements HomeContract.Model{
    public HomeModelImpl(HomePresenterImpl homePresenter) {
        super(homePresenter);
        api = APIServiceFactory.getInstance().createService();
    }
    private NetApi api;
    @Override
    public void getNoReadMsg() {
        api.getNoReadMsg()
                .compose(TransformersFactory.<Integer>commonTransformer((BaseMVPActivity) presenter.getView()))
                .subscribe(new EObserver<Integer>() {
                    @Override
                    public void onError(ResponseThrowable e, String eMsg) {
                    }

                    @Override
                    public void onComplete(@NonNull Integer response) {
                        presenter.getView().getNoReadMsgSuccess(response);
                    }
                });
    }
}
