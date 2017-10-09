package com.egr.drillinghelper.model;

import com.egr.drillinghelper.api.NetApi;
import com.egr.drillinghelper.api.error.EObserver;
import com.egr.drillinghelper.api.error.ResponseThrowable;
import com.egr.drillinghelper.bean.response.ContactUs;
import com.egr.drillinghelper.bean.response.UserInfo;
import com.egr.drillinghelper.contract.ContactUsContract;
import com.egr.drillinghelper.factory.APIServiceFactory;
import com.egr.drillinghelper.factory.TransformersFactory;
import com.egr.drillinghelper.mvp.BaseMVPFragment;
import com.egr.drillinghelper.mvp.BaseModel;
import com.egr.drillinghelper.presenter.ContactUsPresenterImpl;
import com.egr.drillinghelper.presenter.SearchPresenterImpl;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;

import io.reactivex.annotations.NonNull;

/**
 * author lzd
 * date 2017/9/26 16:40
 * 类描述：
 */

public class ContactUsModelImpl extends BaseModel<ContactUsPresenterImpl>
        implements ContactUsContract.Model{
    private NetApi api;

    public ContactUsModelImpl(ContactUsPresenterImpl presenter) {
        super(presenter);
        api = APIServiceFactory.getInstance().createService();
    }

    @Override
    public void getContactList() {
        api.contactList()
                .compose(TransformersFactory.<ContactUs>commonTransformer((BaseMVPActivity) presenter.getView()))
                .subscribe(new EObserver<ContactUs>() {
                    @Override
                    public void onError(ResponseThrowable e, String eMsg) {

                    }

                    @Override
                    public void onComplete(@NonNull ContactUs contactUs) {
                        presenter.getView().getListSuccess(contactUs);
                    }
                });
    }
}
