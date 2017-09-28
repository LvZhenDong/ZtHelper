package com.egr.drillinghelper.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.bean.response.UserInfo;
import com.egr.drillinghelper.contract.MyContract;
import com.egr.drillinghelper.mvp.BaseMVPFragment;
import com.egr.drillinghelper.presenter.MyPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.egr.drillinghelper.R.id.tv;

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

        presenter.userInfo();
    }

    @OnClick({R.id.ll_contact_us, R.id.ll_about_egr, R.id.ll_share, R.id.ll_quit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_contact_us:
                break;
            case R.id.ll_about_egr:
                break;
            case R.id.ll_share:
                break;
            case R.id.ll_quit:
                break;
        }
    }

    @Override
    public void getUserInfoSuccess(UserInfo userInfo) {
        tvName.setText(userInfo.getName());
        tvCompany.setText(userInfo.getCompany());
    }
}
