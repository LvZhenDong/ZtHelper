package com.egr.drillinghelper.ui.activity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.contract.ForgetPswdContract;
import com.egr.drillinghelper.presenter.ForgetPswdPresenterImpl;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;
import com.egr.drillinghelper.ui.widgets.CountDownTimerButton;
import com.egr.drillinghelper.ui.widgets.LvEditText;
import com.egr.drillinghelper.utils.ToastUtils;

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
    LvEditText etEnsurePswd;
    @BindView(R.id.tv_complete)
    TextView tvComplete;
    @BindView(R.id.tv_get_ver_code)
    CountDownTimerButton btnGetVerCode;

    @Override
    public int returnLayoutID() {
        return R.layout.activity_forget_pswd;
    }

    @Override
    public void TODO(Bundle savedInstanceState) {
        setUmengAnalyze(R.string.forget_password);
        setupActionBar(R.string.forget_password, true);
        setActionbarBackground(R.color.white);

        etEnsurePswd.setOnEnterListener(new LvEditText.OnEnterListener() {
            @Override
            public void onEnterClick(String text) {
                complete();
            }
        });
    }

    @Override
    public ForgetPswdPresenterImpl createPresenter() {
        return new ForgetPswdPresenterImpl();
    }


    @OnClick(R.id.tv_complete)
    public void complete() {
        if (!mDialog.isShowing())
            mDialog.show();
        presenter.forgetPswd(etPhoneNum.getText().toString().trim(), etVerCode.getText().toString().trim(),
                etNewPswd.getText().toString().trim(), etEnsurePswd.getTrimText());
    }

    @OnClick(R.id.tv_get_ver_code)
    public void getVerCode() {
        if (!mDialog.isShowing())
            mDialog.show();
        presenter.getVerCode(etPhoneNum.getText().toString().trim());
    }

    @Override
    public void inputError(int e) {
        mDialog.dismiss();
        ToastUtils.show(this, e);
    }

    @Override
    public void forgetPswdFail(String msg) {
        mDialog.dismiss();
        ToastUtils.show(this, msg);
    }

    @Override
    public void forgetPswdSuccess() {
        mDialog.dismiss();
        ToastUtils.show(this, R.string.reset_pswd_success);
        finish();
    }

    @Override
    public void getVerCodeSuccess(String code) {
        btnGetVerCode.startCountDownTimer();
        mDialog.dismiss();
//        etVerCode.setText(code);
        ToastUtils.show(this,R.string.ver_code_send_suc);
    }

    @Override
    public void getVerCodeFail(String msg) {
        mDialog.dismiss();
        ToastUtils.show(this, msg);
    }

    @Override
    protected void onDestroy() {
        btnGetVerCode.stopCountDownTimer();
        super.onDestroy();
    }
}
