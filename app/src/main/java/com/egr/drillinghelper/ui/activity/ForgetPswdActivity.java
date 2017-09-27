package com.egr.drillinghelper.ui.activity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.contract.ForgetPswdContract;
import com.egr.drillinghelper.presenter.ForgetPswdPresenterImpl;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author lzd
 * date 2017/9/26 15:08
 * 类描述：忘记密码
 */

public class ForgetPswdActivity extends BaseMVPActivity<ForgetPswdContract.View,
        ForgetPswdPresenterImpl> implements ForgetPswdContract.View {
    @BindView(R.id.et_phoneNum)
    EditText etPhoneNum;
    @BindView(R.id.et_ver_code)
    EditText etVerCode;
    @BindView(R.id.et_new_pswd)
    EditText etNewPswd;
    @BindView(R.id.et_ensure_pswd)
    EditText etEnsurePswd;
    @BindView(R.id.tv_complete)
    TextView tvComplete;

    @Override
    public int returnLayoutID() {
        return R.layout.activity_forget_pswd;
    }

    @Override
    public void TODO(Bundle savedInstanceState) {
        setupActionBar(R.string.forget_password, true);
        setActionbarBackground(R.color.white);
    }

    @Override
    public ForgetPswdPresenterImpl createPresenter() {
        return new ForgetPswdPresenterImpl();
    }


    @OnClick(R.id.tv_complete)
    public void onClick() {
        Logger.i("完成");
    }
}
