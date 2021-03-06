package com.egr.drillinghelper.presenter;

import com.egr.drillinghelper.contract.ContactUsContract;
import com.egr.drillinghelper.contract.SearchContract;
import com.egr.drillinghelper.model.ContactUsModelImpl;
import com.egr.drillinghelper.model.SearchModelImpl;
import com.egr.drillinghelper.mvp.BasePresenter;
import com.egr.drillinghelper.mvp.IModel;

/**
 * author lzd
 * date 2017/9/26 16:41
 * 类描述：
 */

public class ContactUsPresenterImpl extends BasePresenter<ContactUsContract.View,
        ContactUsModelImpl> implements ContactUsContract.Presenter{
    @Override
    protected IModel createModel() {
        return new ContactUsModelImpl(this);
    }

    @Override
    public void getContactList() {
        mModel.getContactList();
    }
}
