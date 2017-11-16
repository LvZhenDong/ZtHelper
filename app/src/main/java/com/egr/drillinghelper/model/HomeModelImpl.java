package com.egr.drillinghelper.model;

import com.egr.drillinghelper.api.NetApi;
import com.egr.drillinghelper.api.error.EObserver;
import com.egr.drillinghelper.api.error.ResponseThrowable;
import com.egr.drillinghelper.bean.response.ContactUs;
import com.egr.drillinghelper.contract.HomeContract;
import com.egr.drillinghelper.factory.APIServiceFactory;
import com.egr.drillinghelper.factory.TransformersFactory;
import com.egr.drillinghelper.mvp.BaseModel;
import com.egr.drillinghelper.presenter.HomePresenterImpl;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;
import com.egr.drillinghelper.utils.ApkUtils;
import com.shelwee.update.UpdateHelper;

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

    @Override
    public void getContact() {
        api.contactList()
                .compose(TransformersFactory.<ContactUs>commonTransformer((BaseMVPActivity) presenter.getView()))
                .subscribe(new EObserver<ContactUs>() {
                    @Override
                    public void onError(ResponseThrowable e, String eMsg) {
                        presenter.getView().getContactError(eMsg);
                    }

                    @Override
                    public void onComplete(@NonNull ContactUs contactUs) {
                        presenter.getContactSuccess(contactUs);
                    }
                });
    }

    @Override
    public void checkRead() {
        api.checkRead()
                .compose(TransformersFactory.<Boolean>commonTransformer((BaseMVPActivity) presenter.getView()))
                .subscribe(new EObserver<Boolean>() {
                    @Override
                    public void onError(ResponseThrowable e, String eMsg) {

                    }

                    @Override
                    public void onComplete(@NonNull Boolean response) {
                        presenter.getView().checkReadSuc(response);
                    }
                });
    }

    @Override
    public void checkVersion() {
        String url = APIServiceFactory.getBaseUrl() + NetApi.Version + ApkUtils.getVersionCode(getContext());
        UpdateHelper updateHelper = new UpdateHelper.Builder(getContext())
                .checkUrl(url)
                .isHintNewVersion(false)
                .build();
        updateHelper.check();
    }
}
