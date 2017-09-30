package com.egr.drillinghelper.ui.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.contract.AboutEgrContract;
import com.egr.drillinghelper.presenter.AboutEgrPresenterImpl;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;
import com.egr.drillinghelper.ui.widgets.DialogHelper;
import com.egr.drillinghelper.utils.ToastUtils;
import com.zzhoujay.richtext.RichText;

import butterknife.BindView;
import cc.cloudist.acplibrary.ACProgressFlower;

/**
 * author lzd
 * date 2017/9/29 21:31
 * 类描述：
 */

public class AboutEgrActivity extends BaseMVPActivity<AboutEgrContract.View,
        AboutEgrPresenterImpl> implements AboutEgrContract.View {
    @BindView(R.id.tv_content)
    TextView tvContent;
    private ACProgressFlower mDialog;

    @Override
    public int returnLayoutID() {
        return R.layout.activity_about_egr;
    }

    @Override
    public void TODO(Bundle savedInstanceState) {
        setupActionBar(R.string.about_egr, true);
        setActionbarBackground(R.color.white);

        mDialog = DialogHelper.openiOSPbDialog(this, getString(R.string.waiting));

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
            RichText.fromHtml(aboutEgr).into(tvContent);
    }

    @Override
    public void getAboutFail(String msg) {
        mDialog.dismiss();
        ToastUtils.show(this, msg);
    }
}
