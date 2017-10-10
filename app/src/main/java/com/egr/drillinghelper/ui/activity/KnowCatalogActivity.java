package com.egr.drillinghelper.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.bean.response.KnowCatalog;
import com.egr.drillinghelper.contract.KnowCatalogContract;
import com.egr.drillinghelper.presenter.KnowCatalogPresenterImpl;
import com.egr.drillinghelper.ui.adapter.KnowCatalogAdapter;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;
import com.egr.drillinghelper.ui.widgets.DialogHelper;
import com.egr.drillinghelper.utils.CollectionUtil;
import com.egr.drillinghelper.utils.ToastUtils;

import java.util.List;

import butterknife.BindView;
import cc.cloudist.acplibrary.ACProgressFlower;

/**
 * author lzd
 * date 2017/10/10 9:18
 * 类描述：知识问答目录
 */

public class KnowCatalogActivity extends BaseMVPActivity<KnowCatalogContract.View,
        KnowCatalogPresenterImpl> implements KnowCatalogContract.View {
    @BindView(R.id.rv_catalog)
    RecyclerView rvCatalog;

    private ACProgressFlower mDialog;
    private KnowCatalogAdapter mAdapter;

    @Override
    public KnowCatalogPresenterImpl createPresenter() {
        return new KnowCatalogPresenterImpl();
    }
    @Override
    public int returnLayoutID() {
        return R.layout.activity_explain_catalog;
    }

    @Override
    public void TODO(Bundle savedInstanceState) {
        setupActionBar(R.string.ask_knowledge, true);
        setActionbarBackground(R.color.white);

        mDialog = DialogHelper.openiOSPbDialog(this, getString(R.string.waiting));
        String id = getIntent().getStringExtra(KEY_INTENT);

        mAdapter=new KnowCatalogAdapter(this);
        rvCatalog.setAdapter(mAdapter);
        rvCatalog.setLayoutManager(new LinearLayoutManager(this));

        if (!mDialog.isShowing())
            mDialog.show();

        presenter.getKnowCatalog(id);
    }


    @Override
    public void getCatalogSuccess(List<KnowCatalog> catalogList) {
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
