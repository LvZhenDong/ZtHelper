package com.egr.drillinghelper.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.ui.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author lzd
 * date 2017/9/26 18:10
 * 类描述：个人中心
 */

public class MyFragment extends BaseFragment {
    @BindView(R.id.ll_contact_us)
    LinearLayout llContactUs;
    @BindView(R.id.ll_about_egr)
    LinearLayout llAboutEgr;
    @BindView(R.id.ll_share)
    LinearLayout llShare;
    @BindView(R.id.ll_quit)
    LinearLayout llQuit;

    @Override
    public int returnLayoutID() {
        return R.layout.fragment_my;
    }

    @Override
    public void TODO(View view, Bundle savedInstanceState) {

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
}
