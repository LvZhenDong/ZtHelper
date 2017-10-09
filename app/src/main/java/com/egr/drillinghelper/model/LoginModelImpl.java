package com.egr.drillinghelper.model;

import com.egr.drillinghelper.api.error.EObserver;
import com.egr.drillinghelper.api.error.ResponseThrowable;
import com.egr.drillinghelper.bean.response.LoginResponse;
import com.egr.drillinghelper.contract.LoginContract;
import com.egr.drillinghelper.factory.APIServiceFactory;
import com.egr.drillinghelper.factory.TransformersFactory;
import com.egr.drillinghelper.mvp.BaseModel;
import com.egr.drillinghelper.presenter.LoginPresenterImpl;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;

import java.util.HashMap;

import io.reactivex.annotations.NonNull;

/**
 * author lzd
 * date 2017/9/26 10:07
 * 类描述：
 */

public class LoginModelImpl extends BaseModel<LoginPresenterImpl> implements LoginContract.Model{
    public LoginModelImpl(LoginPresenterImpl loginPresenter) {
        super(loginPresenter);
    }

    @Override
    public void login(String phone, String password) {
        HashMap<String,Object> options=new HashMap<>();
        options.put("phone",phone);
        options.put("password",password);

        APIServiceFactory.getInstance().createService()
                .login(options)
                .compose(TransformersFactory.<LoginResponse>commonTransformer((BaseMVPActivity) presenter.getView()))
                .subscribe(new EObserver<LoginResponse>() {
                    @Override
                    public void onError(ResponseThrowable e,String eMsg) {
                        presenter.getView().loginFail(eMsg);
                    }

                    @Override
                    public void onComplete(@NonNull LoginResponse loginResponse) {

                        if(loginResponse == null){
                            onError(null,"登录失败");
                            return;
                        }
                        APIServiceFactory.setTOKEN(loginResponse.getToken());
                        presenter.getView().loginSuccess();
                    }
                });
    }
}
