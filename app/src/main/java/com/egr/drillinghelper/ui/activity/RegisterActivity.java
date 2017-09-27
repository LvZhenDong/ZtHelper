package com.egr.drillinghelper.ui.activity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.contract.RegisterContract;
import com.egr.drillinghelper.presenter.RegisterPresenterImpl;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    TextView tvGetVerCode;
    @BindView(R.id.et_ver_code)
    EditText etVerCode;
    @BindView(R.id.et_pswd)
    EditText etPswd;
    @BindView(R.id.tv_commit)
    TextView tvCommit;

    @Override
    public int returnLayoutID() {
        return R.layout.activity_register;
    }

    @Override
    public void TODO(Bundle savedInstanceState) {
        setupActionBar(R.string.register, true);
        setActionbarBackground(R.color.white);
    }

    @Override
    public RegisterPresenterImpl createPresenter() {
        return new RegisterPresenterImpl();
    }


    @OnClick(R.id.tv_commit)
    public void onClick() {
    }
}
