package com.egr.drillinghelper.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.egr.drillinghelper.BuildConfig;
import com.egr.drillinghelper.R;
import com.egr.drillinghelper.contract.LoginContract;
import com.egr.drillinghelper.factory.APIServiceFactory;
import com.egr.drillinghelper.presenter.LoginPresenterImpl;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;
import com.egr.drillinghelper.ui.widgets.DialogHelper;
import com.egr.drillinghelper.utils.PhoneUtils;
import com.egr.drillinghelper.utils.ToastUtils;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.OnClick;
import cc.cloudist.acplibrary.ACProgressFlower;
import cn.magicbeans.android.ipmanager.module.MBIPInfo;
import cn.magicbeans.android.ipmanager.utils.FloatWindowUtils;
import cn.magicbeans.android.ipmanager.utils.MBIPContant;

import static com.egr.drillinghelper.utils.PhoneUtils.MY_PERMISSIONS_REQUEST_CALL_PHONE;

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

    private ACProgressFlower mDialog;

    @Override
    public int returnLayoutID() {
        return R.layout.activity_login;
    }

    @Override
    public void TODO(Bundle savedInstanceState) {
        if (!this.isTaskRoot()) {
            Intent mainIntent = getIntent();
            String action = mainIntent.getAction();
            if (mainIntent.hasCategory(Intent.CATEGORY_LAUNCHER) && action.equals(Intent
                    .ACTION_MAIN)) {
                finish();
                return;
            }
        }
        setSwipeBackEnabled(false);//设置不可右滑关闭

        Glide.with(this).load(R.drawable.logo).into(ivLogo);
        mDialog = DialogHelper.openiOSPbDialog(this, getString(R.string.logining));

        etPhoneNum.setText("18202806302");
        etPasw.setText("12345678");

        if(BuildConfig.DEBUG){
            FloatWindowUtils floatWindowUtils = new FloatWindowUtils();
            floatWindowUtils.init(this);
        }

        getWritePermission();
    }
    public final static int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 0X98;
    private void getWritePermission(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
            return;
        }else {
            presenter.readCache();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    presenter.readCache();
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                break;
        }
    }

    @Override
    public LoginPresenterImpl createPresenter() {
        return new LoginPresenterImpl();
    }

    @OnClick(R.id.tv_login)
    public void login() {
        if (!mDialog.isShowing())
            mDialog.show();
        presenter.login(etPhoneNum.getText().toString().trim(), etPasw.getText().toString().trim());
    }

    @OnClick(R.id.tv_register)
    public void register() {
        baseStartActivity(RegisterActivity.class);
    }

    @OnClick(R.id.tv_forget_pswd)
    public void forgetPswd() {
        baseStartActivity(ForgetPswdActivity.class);
    }


    @Override
    public void inputError(int e) {
        mDialog.dismiss();
        ToastUtils.show(this, e);
    }

    @Override
    public void loginSuccess() {
        mDialog.dismiss();
        baseStartActivity(HomeActivity.class);
        finish();
    }

    @Override
    public void loginFail(String message) {
        mDialog.dismiss();
        ToastUtils.show(this, message);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == MBIPContant.RESULT_CODE && requestCode == MBIPContant.REQUEST_CODE) {
            MBIPInfo info = (MBIPInfo) data.getSerializableExtra(MBIPContant.IP);
            APIServiceFactory.setBaseUrl(info.getIp()+":"+info.getPort());
        }

    }
}
