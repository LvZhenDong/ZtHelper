package com.kklv.fragme.ui.activity;

import android.os.Bundle;

import com.kklv.fragme.R;
import com.kklv.fragme.api.NetApi;
import com.kklv.fragme.api.error.LObserver;
import com.kklv.fragme.api.error.ResponeThrowable;
import com.kklv.fragme.bean.request.LoginRequest;
import com.kklv.fragme.bean.response.Resident;
import com.kklv.fragme.contract.TextContract;
import com.kklv.fragme.factory.APIServiceFactory;
import com.kklv.fragme.factory.TransformersFactory;
import com.kklv.fragme.presenter.TestPresenterImpl;
import com.kklv.fragme.ui.base.BaseMVPActivity;
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
