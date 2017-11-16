package com.egr.drillinghelper.presenter;

import com.egr.drillinghelper.bean.response.ContactUs;
import com.egr.drillinghelper.contract.HomeContract;
import com.egr.drillinghelper.model.HomeModelImpl;
import com.egr.drillinghelper.mvp.BasePresenter;
import com.egr.drillinghelper.mvp.IModel;

/**
 * author lzd
 * date 2017/9/26 16:41
 * 类描述：
 */

public class HomePresenterImpl extends BasePresenter<HomeContract.View,
        HomeModelImpl> implements HomeContract.Presenter{
    @Override
    protected IModel createModel() {
        return new HomeModelImpl(this);
    }

    @Override
    public void getNoReadMsg() {
        mModel.getNoReadMsg();
    }

    @Override
    public void getContactSuccess(ContactUs contactUs) {
        getView().getContactSuccess(contactUs.getAboutUs().getServiceTel());
    }

    @Override
    public void getContact() {
        mModel.getContact();
    }

    @Override
    public void checkRead() {
        mModel.checkRead();
    }

    @Override
    public void checkVersion() {
        mModel.checkVersion();
    }
}
