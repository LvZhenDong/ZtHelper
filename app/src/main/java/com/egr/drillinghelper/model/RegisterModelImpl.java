package com.egr.drillinghelper.model;

import com.egr.drillinghelper.api.NetApi;
import com.egr.drillinghelper.api.error.EObserver;
import com.egr.drillinghelper.api.error.ResponseThrowable;
import com.egr.drillinghelper.bean.response.NullBodyResponse;
import com.egr.drillinghelper.contract.RegisterContract;
import com.egr.drillinghelper.factory.APIServiceFactory;
import com.egr.drillinghelper.factory.TransformersFactory;
import com.egr.drillinghelper.mvp.BaseModel;
import com.egr.drillinghelper.presenter.RegisterPresenterImpl;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;

import java.util.HashMap;

import io.reactivex.annotations.NonNull;


/**
 * author lzd
 * date 2017/9/27 11:55
 * 类描述：
 */

public class RegisterModelImpl extends BaseModel<RegisterPresenterImpl>
        implements RegisterContract.Model {

    private NetApi api;

    public RegisterModelImpl(RegisterPresenterImpl registerPresenter) {
        super(registerPresenter);
        api = APIServiceFactory.getInstance().createService(NetApi.class);
    }

    @Override
    public void register(String name, String company, String phone, String verCode, String pswd) {
        HashMap<String, Object> options = new HashMap<>();
        options.put("name", name);
        options.put("company", company);
        options.put("phone", phone);
        options.put("code", verCode);
        options.put("password", pswd);
        api.register(options)
                .compose(TransformersFactory.nullBodyTransformer((BaseMVPActivity) presenter.getView()))
                .subscribe(new EObserver<NullBodyResponse>() {
                    @Override
                    public void onError(ResponseThrowable e, String eMsg) {
                        presenter.getView().registerFail(eMsg);
                    }

                    @Override
                    public void onComplete(@NonNull NullBodyResponse response) {
                        presenter.getView().registerSuccess();
                    }
                });

    }

    @Override
    public void getVerCode(String phone) {
        api.getVerCode("register", phone)
                .compose(TransformersFactory.<String>commonTransformer((BaseMVPActivity) presenter.getView()))
                .subscribe(new EObserver<String>() {
                    @Override
                    public void onError(ResponseThrowable e, String eMsg) {
                        presenter.getView().getVerCodeFail(eMsg);
                    }

                    @Override
                    public void onComplete(@NonNull String s) {
                        presenter.getView().getVerCodeSuccess(s);
                    }
                });

    }
}
