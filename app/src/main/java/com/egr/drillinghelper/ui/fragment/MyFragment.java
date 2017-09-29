package com.egr.drillinghelper.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.egr.drillinghelper.R;
import com.egr.drillinghelper.bean.response.UserInfo;
import com.egr.drillinghelper.common.RxBusConstant;
import com.egr.drillinghelper.contract.MyContract;
import com.egr.drillinghelper.mvp.BaseMVPFragment;
import com.egr.drillinghelper.presenter.MyPresenterImpl;
import com.egr.drillinghelper.ui.activity.AboutEgrActivity;
import com.egr.drillinghelper.ui.activity.ContactUsActivity;
import com.egr.drillinghelper.ui.activity.LoginActivity;
import com.egr.drillinghelper.ui.activity.PersonalActivity;
import com.egr.drillinghelper.ui.widgets.DialogHelper;
import com.egr.drillinghelper.utils.EgrRxBus;
import com.egr.drillinghelper.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;
import cc.cloudist.acplibrary.ACProgressFlower;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * author lzd
 * date 2017/9/26 18:10
 * 类描述：个人中心
 */

public class MyFragment extends BaseMVPFragment<MyContract.View, MyPresenterImpl>
        implements MyContract.View {
    @BindView(R.id.ll_contact_us)
    LinearLayout llContactUs;
    @BindView(R.id.ll_about_egr)
    LinearLayout llAboutEgr;
    @BindView(R.id.ll_share)
    LinearLayout llShare;
    @BindView(R.id.ll_quit)
    LinearLayout llQuit;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_company)
    TextView tvCompany;
    @BindView(R.id.iv_head)
    CircleImageView ivHead;
    UserInfo mUserInfo;
    private ACProgressFlower mDialog;

    @Override
    protected MyPresenterImpl createPresenter() {
        return new MyPresenterImpl();
    }

    @Override
    public int returnLayoutID() {
        return R.layout.fragment_my;
    }

    @Override
    public void TODO(View view, Bundle savedInstanceState) {
        mDialog = DialogHelper.openiOSPbDialog(getActivity(), getString(R.string.waiting));

        EgrRxBus.subscribe(this, String.class, new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                if (s.equals(RxBusConstant.UPDATE_MY_USER_INFO))
                    presenter.userInfo();
            }
        });

        presenter.userInfo();
    }

    @OnClick({R.id.ll_contact_us, R.id.ll_about_egr, R.id.ll_share,
            R.id.ll_quit, R.id.tv_name, R.id.tv_company, R.id.iv_head})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_contact_us:
                baseStartActivity(ContactUsActivity.class);
                break;
            case R.id.ll_about_egr:
                baseStartActivity(AboutEgrActivity.class);
                break;
            case R.id.ll_share:
                break;
            case R.id.ll_quit:
                if (!mDialog.isShowing())
                    mDialog.show();
                presenter.quit();
                break;
            case R.id.iv_head:
                goToPersonal();
                break;
            case R.id.tv_name:
                goToPersonal();
                break;
            case R.id.tv_company:
                goToPersonal();
                break;
        }
    }

    /**
     * 跳转个人信息界面
     */
    private void goToPersonal() {
        Intent intent = new Intent(getActivity(), PersonalActivity.class);
        intent.putExtra(KEY_INTENT, mUserInfo);
        startActivity(intent);
    }

    @Override
    public void getUserInfoSuccess(UserInfo userInfo) {
        mUserInfo = userInfo;
        tvName.setText(userInfo.getName());
        tvCompany.setText(userInfo.getCompany());
        Glide.with(getActivity()).load(userInfo.getPhoto()).centerCrop().into(ivHead);
    }

    @Override
    public void logoutSuccess() {
        mDialog.dismiss();
        finish();
        baseStartActivity(LoginActivity.class);
    }

    @Override
    public void logoutFail(String msg) {
        mDialog.dismiss();
        ToastUtils.show(getActivity(), msg);
    }

}
