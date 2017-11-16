package com.egr.drillinghelper.ui.activity;

import android.os.Bundle;
import android.webkit.WebView;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.contract.AboutEgrContract;
import com.egr.drillinghelper.presenter.AboutEgrPresenterImpl;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;
import com.egr.drillinghelper.utils.ToastUtils;

import butterknife.BindView;

/**
 * author lzd
 * date 2017/9/29 21:31
 * 类描述：
 */

public class AboutEgrActivity extends BaseMVPActivity<AboutEgrContract.View,
        AboutEgrPresenterImpl> implements AboutEgrContract.View {
    @BindView(R.id.wv)
    WebView webView;

    @Override
    public int returnLayoutID() {
        return R.layout.activity_about_egr;
    }

    @Override
    public void TODO(Bundle savedInstanceState) {
        setUmengAnalyze(R.string.about_egr);
        setupActionBar(R.string.about_egr, true);
        setActionbarBackground(R.color.white);

        if (!mDialog.isShowing())
            mDialog.show();
        presenter.getAbout();
    }

    @Override
    public AboutEgrPresenterImpl createPresenter() {
        return new AboutEgrPresenterImpl();
    }

    @Override
    public void getAboutSuccess(String aboutEgr) {
        mDialog.dismiss();
        if (aboutEgr != null)
            webView.loadDataWithBaseURL(null, aboutEgr, null, "utf-8", null);
    }

    @Override
    public void getAboutFail(String msg) {
        mDialog.dismiss();
        ToastUtils.show(this, msg);
    }
}
