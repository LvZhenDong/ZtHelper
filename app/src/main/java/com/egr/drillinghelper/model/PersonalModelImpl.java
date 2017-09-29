package com.egr.drillinghelper.model;

import com.egr.drillinghelper.api.NetApi;
import com.egr.drillinghelper.api.error.EObserver;
import com.egr.drillinghelper.api.error.ResponseThrowable;
import com.egr.drillinghelper.bean.response.Explain;
import com.egr.drillinghelper.bean.response.RegisterResponse;
import com.egr.drillinghelper.contract.PersonalContract;
import com.egr.drillinghelper.factory.APIServiceFactory;
import com.egr.drillinghelper.factory.TransformersFactory;
import com.egr.drillinghelper.mvp.BaseMVPFragment;
import com.egr.drillinghelper.mvp.BaseModel;
import com.egr.drillinghelper.presenter.PersonalPresenterImpl;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;

import java.util.Map;

import io.reactivex.annotations.NonNull;
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
        api = APIServiceFactory.getInstance().createService(NetApi.class);
    }

    @Override
    public void userPhoto(Map<String, RequestBody> photo) {
        api.userPhoto(photo)
                .compose(TransformersFactory.<RegisterResponse>commonTransformer((BaseMVPActivity) presenter.getView()))
                .subscribe(new EObserver<RegisterResponse>() {
                    @Override
                    public void onError(ResponseThrowable e, String eMsg) {
                        if(eMsg.equals("未知错误")){
                            presenter.changeHeadSuccess();
                        }else {
                            presenter.getView().changeHeadFail(eMsg);
                        }
                    }

                    @Override
                    public void onComplete(@NonNull RegisterResponse response) {
                        presenter.changeHeadSuccess();
                    }
                });
    }
}
