package com.egr.drillinghelper.model;

import com.egr.drillinghelper.api.NetApi;
import com.egr.drillinghelper.api.error.EObserver;
import com.egr.drillinghelper.api.error.ResponseThrowable;
import com.egr.drillinghelper.contract.PersonalContract;
import com.egr.drillinghelper.factory.APIServiceFactory;
import com.egr.drillinghelper.factory.TransformersFactory;
import com.egr.drillinghelper.mvp.BaseModel;
import com.egr.drillinghelper.presenter.PersonalPresenterImpl;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;

import java.util.Map;

import okhttp3.RequestBody;

/**
 * author lzd
 * date 2017/9/26 16:40
 * 类描述：
 */

public class PersonalModelImpl extends BaseModel<PersonalPresenterImpl> implements PersonalContract.Model{
    private NetApi api;
    public PersonalModelImpl(PersonalPresenterImpl presenter) {
        super(presenter);
        api = APIServiceFactory.getInstance().createService();
    }

    @Override
    public void userPhoto(Map<String, RequestBody> photo) {
//        api.userPhoto(photo)
//                .compose(TransformersFactory.nullBodyTransformer((BaseMVPActivity) presenter.getView()))
//                .subscribe(new EObserver<NullBodyResponse>() {
//                    @Override
//                    public void onError(ResponseThrowable e, String eMsg) {
//                        presenter.getView().changeHeadFail(eMsg);
//                    }
//
//                    @Override
//                    public void onComplete(@NonNull NullBodyResponse response) {
//                        presenter.changeHeadSuccess();
//                    }
//                });

        api.userPhoto(photo).compose(TransformersFactory.emptyTrans((BaseMVPActivity) presenter.getView()))
                .subscribe(new EObserver<Boolean>() {
                    @Override
                    public void onError(ResponseThrowable e, String eMsg) {
                        presenter.getView().changeHeadFail(eMsg);
                    }

                    @Override
                    public void onComplete(Boolean aBoolean) {
                        presenter.changeHeadSuccess();
                    }
                });
    }
}
