package com.egr.drillinghelper.ui.activity;

import android.os.Bundle;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.api.NetApi;
import com.egr.drillinghelper.api.error.LObserver;
import com.egr.drillinghelper.api.error.ResponeThrowable;
import com.egr.drillinghelper.bean.request.LoginRequest;
import com.egr.drillinghelper.bean.response.Resident;
import com.egr.drillinghelper.contract.TextContract;
import com.egr.drillinghelper.factory.APIServiceFactory;
import com.egr.drillinghelper.factory.TransformersFactory;
import com.egr.drillinghelper.presenter.TestPresenterImpl;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;
import com.orhanobut.logger.Logger;

import java.util.HashMap;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by lvzhendong on 2017/9/24.
 */

public class TestActivity extends BaseMVPActivity<TextContract.View,TestPresenterImpl>
    implements TextContract.View{

    @Override
    public int returnLayoutID() {
        return R.layout.activity_test;
    }

    @Override
    public void TODO(Bundle savedInstanceState) {
        LoginRequest request = new LoginRequest();
        request.setLoginName("111865");
        request.setPassword("e10adc3949ba59abbe56e057f20f883e");
        HashMap<String,Object> options=new HashMap<>();
        options.put("phone","15208460574");
        options.put("password","e10adc3949ba59abbe56e057f20f883e");
        options.put("loginType","0");
        options.put("deviceCode","1104a8979293d98dcde");
        options.put("deviceType","0");
        APIServiceFactory.getInstance().createService(NetApi.class)
                .login(options)
                .compose(TransformersFactory.<Resident>commonTransformer(this))
                .subscribe(new LObserver<Resident>() {
                    @Override
                    public void onError(ResponeThrowable e) {
                        Logger.e(e.getLMessage());
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Resident Resident) {
                        Logger.i(Resident.getAccount());
                    }
                });
    }

    @Override
    public TestPresenterImpl createPresenter() {
        return new TestPresenterImpl();
    }
}
