package com.egr.drillinghelper.ui.activity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.contract.RegisterContract;
import com.egr.drillinghelper.presenter.RegisterPresenterImpl;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;
import com.egr.drillinghelper.ui.widgets.CountDownTimerButton;
import com.egr.drillinghelper.ui.widgets.DialogHelper;
import com.egr.drillinghelper.ui.widgets.LvEditText;
import com.egr.drillinghelper.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cc.cloudist.acplibrary.ACProgressFlower;

/**
 * author lzd
 * date 2017/9/27 11:54
 * 类描述：
 */

public class RegisterActivity extends BaseMVPActivity<RegisterContract.View,
        RegisterPresenterImpl> implements RegisterContract.View {
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_company)
    EditText etCompany;
    @BindView(R.id.et_phoneNum)
    EditText etPhoneNum;
    @BindView(R.id.tv_get_ver_code)
    CountDownTimerButton btnGetVerCode;
    @BindView(R.id.et_ver_code)
    EditText etVerCode;
    @BindView(R.id.et_pswd)
    LvEditText etPswd;
    @BindView(R.id.tv_commit)
    TextView tvCommit;

    private ACProgressFlower mDialog;

    @Override
    public int returnLayoutID() {
        return R.layout.activity_register;
    }

    @Override
    public void TODO(Bundle savedInstanceState) {
        setupActionBar(R.string.register, true);
        setActionbarBackground(R.color.white);

        mDialog = DialogHelper.openiOSPbDialog(this, getString(R.string.waiting));

        etPswd.setOnEnterListener(new LvEditText.OnEnterListener() {
            @Override
            public void onEnterClick(String text) {
                commit();
            }
        });
    }

    @Override
    public RegisterPresenterImpl createPresenter() {
        return new RegisterPresenterImpl();
    }


    @OnClick(R.id.tv_commit)
    public void commit() {
        if(!mDialog.isShowing())
            mDialog.show();
        presenter.register(etName.getText().toString().trim(),etCompany.getText().toString().trim(),
                etPhoneNum.getText().toString().trim(),etVerCode.getText().toString().trim(),
                etPswd.getText().toString().trim());
    }

    @OnClick(R.id.tv_get_ver_code)
    public void getVerCode(){
        if(!mDialog.isShowing())
            mDialog.show();
        presenter.getVerCode(etPhoneNum.getText().toString().trim());
    }

    @Override
    public void inputError(int e) {
        mDialog.dismiss();
        ToastUtils.show(this,e);
    }

    @Override
    public void registerSuccess() {
        mDialog.dismiss();
        ToastUtils.show(this,R.string.register_success);
        finish();
    }

    @Override
    public void registerFail(String message) {
        mDialog.dismiss();
        ToastUtils.show(this,message);
    }

    @Override
    public void getVerCodeSuccess(String code) {
        btnGetVerCode.startCountDownTimer();
        mDialog.dismiss();
        etVerCode.setText(code);
    }

    @Override
    public void getVerCodeFail(String msg) {
        mDialog.dismiss();
        ToastUtils.show(this,msg);
    }

    @Override
    protected void onDestroy() {
        btnGetVerCode.stopCountDownTimer();
        super.onDestroy();
    }
}
