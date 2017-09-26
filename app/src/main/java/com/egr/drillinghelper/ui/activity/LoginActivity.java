package com.egr.drillinghelper.ui.activity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.egr.drillinghelper.R;
import com.egr.drillinghelper.contract.LoginContract;
import com.egr.drillinghelper.presenter.LoginPresenterImpl;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author lzd
 * date 2017/9/26 10:05
 * 类描述：登录
 */

public class LoginActivity extends BaseMVPActivity<LoginContract.View, LoginPresenterImpl>
        implements LoginContract.View {
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.iv_logo)
    ImageView ivLogo;
    @BindView(R.id.et_phoneNum)
    EditText etPhoneNum;
    @BindView(R.id.et_pasw)
    EditText etPasw;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.tv_forget_pswd)
    TextView tvForgetPswd;

    @Override
    public int returnLayoutID() {
        return R.layout.activity_login;
    }

    @Override
    public void TODO(Bundle savedInstanceState) {
        setSwipeBackEnabled(false);//设置不可右滑关闭

        Glide.with(this).load(R.drawable.logo).into(ivLogo);
    }

    @Override
    public LoginPresenterImpl createPresenter() {
        return new LoginPresenterImpl();
    }

    @OnClick(R.id.tv_login)
    public void login() {
        Logger.i(etPhoneNum.getText().toString().trim()+"  "+etPasw.getText().toString().trim());

        baseStartActivity(HomeActivity.class);
    }

    @OnClick(R.id.tv_register)
    public void register() {
        baseStartActivity(ForgetPswdActivity.class);
    }
}
