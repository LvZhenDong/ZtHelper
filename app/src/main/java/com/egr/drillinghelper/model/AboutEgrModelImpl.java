package com.egr.drillinghelper.model;

import com.egr.drillinghelper.api.NetApi;
import com.egr.drillinghelper.api.error.EObserver;
import com.egr.drillinghelper.api.error.ResponseThrowable;
import com.egr.drillinghelper.contract.AboutEgrContract;
import com.egr.drillinghelper.factory.APIServiceFactory;
import com.egr.drillinghelper.factory.TransformersFactory;
import com.egr.drillinghelper.mvp.BaseModel;
import com.egr.drillinghelper.presenter.AboutEgrPresenterImpl;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;

import io.reactivex.annotations.NonNull;

/**
 * author lzd
 * date 2017/9/26 16:40
 * 类描述：
 */

public class AboutEgrModelImpl extends BaseModel<AboutEgrPresenterImpl> implements AboutEgrContract.Model{
    public AboutEgrModelImpl(AboutEgrPresenterImpl presenter) {
        super(presenter);
        api = APIServiceFactory.getInstance().createService();
    }
    private NetApi api;
    @Override
    public void getAbout() {
        api.getAbout()
                .compose(TransformersFactory.<String>commonTransformer((BaseMVPActivity) presenter.getView()))
                .subscribe(new EObserver<String>() {
                    @Override
                    public void onError(ResponseThrowable e, String eMsg) {
                        presenter.getView().getAboutFail(eMsg);
                    }

                    @Override
                    public void onComplete(@NonNull String aboutEgr) {
                        presenter.getView().getAboutSuccess(aboutEgr);
                    }
                });
    }
}
