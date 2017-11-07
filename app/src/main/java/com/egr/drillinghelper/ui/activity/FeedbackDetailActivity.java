package com.egr.drillinghelper.ui.activity;

import android.os.Bundle;
import android.webkit.WebView;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.contract.FeedbackDetailContract;
import com.egr.drillinghelper.presenter.FeedbackDetailPresenterImpl;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;
import com.egr.drillinghelper.ui.widgets.DialogHelper;
import com.egr.drillinghelper.utils.ToastUtils;

import butterknife.BindView;
import cc.cloudist.acplibrary.ACProgressFlower;

/**
 * author lzd
 * date 2017/9/29 21:31
 * 类描述：
 */

public class FeedbackDetailActivity extends BaseMVPActivity<FeedbackDetailContract.View,
        FeedbackDetailPresenterImpl> implements FeedbackDetailContract.View {
    @BindView(R.id.wv)
    WebView webView;
    private ACProgressFlower mDialog;

    @Override
    public int returnLayoutID() {
        return R.layout.activity_feedback_detail;
    }

    @Override
    public void TODO(Bundle savedInstanceState) {
        setUmengAnalyze(R.string.feedback_detail);
        setupActionBar(R.string.feedback_detail, true);
        setActionbarBackground(R.color.white);

        String id=getIntent().getStringExtra(KEY_INTENT);

        mDialog = DialogHelper.openiOSPbDialog(this, getString(R.string.waiting));

        if (!mDialog.isShowing())
            mDialog.show();
        presenter.getDetail(id);
    }

    @Override
    public FeedbackDetailPresenterImpl createPresenter() {
        return new FeedbackDetailPresenterImpl();
    }

    @Override
    public void getDetailSuccess(String detail) {
        mDialog.dismiss();
        if (detail != null)
            webView.loadDataWithBaseURL(null, detail, null, "utf-8", null);
    }

    @Override
    public void getDetailFail(String msg) {
        mDialog.dismiss();
        ToastUtils.show(this, msg);
    }
}
