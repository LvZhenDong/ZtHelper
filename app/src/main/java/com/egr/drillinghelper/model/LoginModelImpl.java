package com.egr.drillinghelper.model;

import android.text.TextUtils;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.api.error.EObserver;
import com.egr.drillinghelper.api.error.ResponseThrowable;
import com.egr.drillinghelper.bean.response.Explain;
import com.egr.drillinghelper.bean.response.UserInfo;
import com.egr.drillinghelper.common.UserManager;
import com.egr.drillinghelper.contract.LoginContract;
import com.egr.drillinghelper.factory.APIServiceFactory;
import com.egr.drillinghelper.factory.TransformersFactory;
import com.egr.drillinghelper.mvp.BaseModel;
import com.egr.drillinghelper.presenter.LoginPresenterImpl;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;
import com.egr.drillinghelper.utils.CacheUtils;
import com.egr.drillinghelper.utils.CollectionUtil;
import com.egr.drillinghelper.utils.NetworkUtils;

import java.util.HashMap;
import java.util.List;

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
            String deviceCode= UserManager.getInstance().getJPushId(getContext());
            if(!TextUtils.isEmpty(deviceCode))
                options.put("deviceCode", deviceCode);
            options.put("deviceType",0);    //0-android 1-iOS

            APIServiceFactory.getInstance().createService()
                    .login(options)
                    .compose(TransformersFactory.<UserInfo>commonTransformer((BaseMVPActivity) presenter.getView()))
                    .subscribe(new EObserver<UserInfo>() {
                        @Override
                        public void onError(ResponseThrowable e,String eMsg) {
                            presenter.getView().loginFail(eMsg);
                        }

                        @Override
                        public void onComplete(@NonNull UserInfo userInfo) {

                            if(userInfo == null){
                                onError(null,"登录失败");
                                return;
                            }
                            UserManager.getInstance().saveUserInfo(userInfo);
                            presenter.getView().loginSuccess();
                        }
                    });


    }

    @Override
    public void readCache() {
        if(!NetworkUtils.isNetworkConnected(getContext())){
            showCache();
        }
    }

    private void showCache(){
        try {
            List<Explain> explain= CacheUtils.getExplains();
            List<Explain> know=CacheUtils.getKnows();
            if(!CollectionUtil.isListEmpty(explain) || !CollectionUtil.isListEmpty(know))
                presenter.getView().loginSuccess();
            else
                presenter.getView().loginFail(getContext().getString(R.string.net_error));
        } catch (Exception e) {
            presenter.getView().loginFail(getContext().getString(R.string.net_error));
        }
    }
}
