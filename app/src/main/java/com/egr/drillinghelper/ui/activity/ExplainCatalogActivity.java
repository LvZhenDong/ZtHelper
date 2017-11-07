package com.egr.drillinghelper.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.bean.response.ExplainCatalog;
import com.egr.drillinghelper.contract.ExplainCatalogContract;
import com.egr.drillinghelper.presenter.ExplainCatalogPresenterImpl;
import com.egr.drillinghelper.ui.adapter.ExplainCatalogAdapter;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;
import com.egr.drillinghelper.ui.widgets.DialogHelper;
import com.egr.drillinghelper.utils.CollectionUtil;
import com.egr.drillinghelper.utils.ToastUtils;

import java.util.List;

import butterknife.BindView;
import cc.cloudist.acplibrary.ACProgressFlower;

/**
 * author lzd
 * date 2017/9/29 18:19
 * 类描述：使用说明目录
 */

public class ExplainCatalogActivity extends BaseMVPActivity<ExplainCatalogContract.View,
        ExplainCatalogPresenterImpl> implements ExplainCatalogContract.View {
    @BindView(R.id.rv_catalog)
    RecyclerView rvCatalog;
    private ACProgressFlower mDialog;
    private ExplainCatalogAdapter mAdapter;

    @Override
    public int returnLayoutID() {
        return R.layout.activity_explain_catalog;
    }

    @Override
    public void TODO(Bundle savedInstanceState) {
        setUmengAnalyze(R.string.explain);
        setupActionBar(R.string.explain, true);
        setActionbarBackground(R.color.white);

        mDialog = DialogHelper.openiOSPbDialog(this, getString(R.string.waiting));
        String id = getIntent().getStringExtra(KEY_INTENT);

        mAdapter = new ExplainCatalogAdapter(this,id);
        rvCatalog.setAdapter(mAdapter);
        rvCatalog.setLayoutManager(new LinearLayoutManager(this));

        if (!mDialog.isShowing())
            mDialog.show();
        presenter.getExplainCatalog(id);
    }

    @Override
    public ExplainCatalogPresenterImpl createPresenter() {
        return new ExplainCatalogPresenterImpl();
    }

    @Override
    public void getCatalogSuccess(List<ExplainCatalog> catalogList) {
        mDialog.dismiss();
        if (CollectionUtil.isListEmpty(catalogList)){
            ToastUtils.show(this,R.string.no_data);
        } else {
            mAdapter.setDataList(catalogList);
        }
    }

    @Override
    public void getCatalogFail(String msg) {
        mDialog.dismiss();
        ToastUtils.show(this, msg);
    }

}
