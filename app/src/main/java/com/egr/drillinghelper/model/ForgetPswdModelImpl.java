package com.egr.drillinghelper.model;

import com.egr.drillinghelper.api.NetApi;
import com.egr.drillinghelper.api.error.EObserver;
import com.egr.drillinghelper.api.error.ResponseThrowable;
import com.egr.drillinghelper.bean.base.BaseResponseBean;
import com.egr.drillinghelper.contract.ForgetPswdContract;
import com.egr.drillinghelper.factory.APIServiceFactory;
import com.egr.drillinghelper.factory.TransformersFactory;
import com.egr.drillinghelper.mvp.BaseModel;
import com.egr.drillinghelper.presenter.ForgetPswdPresenterImpl;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;

import java.util.HashMap;

import io.reactivex.annotations.NonNull;

/**
 * author lzd
 * date 2017/9/26 15:09
 * 类描述：
 */

public class ForgetPswdModelImpl extends BaseModel<ForgetPswdPresenterImpl>
        implements ForgetPswdContract.Model {

    private NetApi api;

    public ForgetPswdModelImpl(ForgetPswdPresenterImpl forgetPswdPresenter) {
        super(forgetPswdPresenter);
        api = APIServiceFactory.getInstance().createService();
    }

    @Override
    public void forgetPswd(String phone, String code, String pswd) {
        HashMap<String, Object> options = new HashMap<>();

        options.put("phone", phone);
        options.put("code", code);
        options.put("password", pswd);
        api.forget(options)
                .compose(TransformersFactory.emptyTrans((BaseMVPActivity) presenter.getView()))
                .subscribe(new EObserver<BaseResponseBean>() {
                    @Override
                    public void onError(ResponseThrowable e, String eMsg) {
                        presenter.getView().forgetPswdFail(eMsg);
                    }

                    @Override
                    public void onComplete(@NonNull BaseResponseBean response) {
                        presenter.getView().forgetPswdSuccess();
                    }
                });
    }

    @Override
    public void getVerCode(String phone) {
        api.getVerCode("forget", phone)
                .compose(TransformersFactory.<String>commonTransformer((BaseMVPActivity)
                        presenter.getView()))
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
