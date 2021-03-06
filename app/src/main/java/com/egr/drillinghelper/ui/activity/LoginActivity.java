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
import com.egr.drillinghelper.common.MySharePreferencesManager;
import com.egr.drillinghelper.common.UserManager;
import com.egr.drillinghelper.contract.LoginContract;
import com.egr.drillinghelper.factory.APIServiceFactory;
import com.egr.drillinghelper.presenter.LoginPresenterImpl;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;
import com.egr.drillinghelper.ui.widgets.DialogHelper;
import com.egr.drillinghelper.ui.widgets.LvEditText;
import com.egr.drillinghelper.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;
import cn.magicbeans.android.ipmanager.module.MBIPInfo;
import cn.magicbeans.android.ipmanager.utils.FloatWindowUtils;
import cn.magicbeans.android.ipmanager.utils.MBIPContant;

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
    LvEditText etPasw;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.tv_forget_pswd)
    TextView tvForgetPswd;

    private boolean notReadCache;

    @Override
    public int returnLayoutID() {
        return R.layout.activity_login;
    }

    @Override
    public void TODO(Bundle savedInstanceState) {
        //防止再多个栈里重复启动
        if (!this.isTaskRoot()) {
            Intent mainIntent = getIntent();
            String action = mainIntent.getAction();
            if (mainIntent.hasCategory(Intent.CATEGORY_LAUNCHER) && action.equals(Intent
                    .ACTION_MAIN)) {
                finish();
                return;
            }
        }
        setUmengAnalyze(R.string.login);
        Glide.with(this).load(R.drawable.logo).into(ivLogo);
        mDialog = DialogHelper.openiOSPbDialog(this, getString(R.string.logining));

        //自动填充密码和用户名
        etPhoneNum.setText(MySharePreferencesManager.getInstance().getString(MySharePreferencesManager.USER_NAME, ""));
        etPasw.setText(MySharePreferencesManager.getInstance().getString(MySharePreferencesManager.USER_PSWD, ""));

        etPhoneNum.setSelection(etPhoneNum.length());

        etPasw.setOnEnterListener(new LvEditText.OnEnterListener() {
            @Override
            public void onEnterClick(String text) {
                login();
            }
        });

        //修改IP工具
        if (BuildConfig.DEBUG) {
            FloatWindowUtils floatWindowUtils = new FloatWindowUtils();
            floatWindowUtils.init(this);
        }

        //如果是从“我的”点击退出到此界面，则不用读缓存等操作
        notReadCache = getIntent().getBooleanExtra(KEY_INTENT_BOOLEAN, false);
        if (!notReadCache)
            getWritePermission();
    }

    public final static int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 0X98;

    private void getWritePermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
            return;
        } else {
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
        presenter.login(etPhoneNum.getText().toString().trim(), etPasw.getTrimText());
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

        MySharePreferencesManager.getInstance().putString(MySharePreferencesManager.USER_NAME, etPhoneNum.getText().toString().trim());
        MySharePreferencesManager.getInstance().putString(MySharePreferencesManager.USER_PSWD, etPasw.getTrimText());
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
            APIServiceFactory.setBaseUrl(info.getIp() + ":" + info.getPort());
        }

    }

    @Override
    public void onBackPressed() {
        UserManager.getInstance().quit();
        super.onBackPressed();
    }
}
