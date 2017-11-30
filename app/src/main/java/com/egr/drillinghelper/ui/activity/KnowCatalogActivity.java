package com.egr.drillinghelper.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.util.TypedValue;
import android.widget.TextView;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.bean.response.KnowCatalog;
import com.egr.drillinghelper.contract.KnowCatalogContract;
import com.egr.drillinghelper.presenter.KnowCatalogPresenterImpl;
import com.egr.drillinghelper.ui.adapter.KnowCatalogAdapter;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;
import com.egr.drillinghelper.utils.CollectionUtil;
import com.egr.drillinghelper.utils.ToastUtils;

import java.util.List;

import butterknife.BindView;

/**
 * author lzd
 * date 2017/10/10 9:18
 * 类描述：知识问答目录
 */

public class KnowCatalogActivity extends BaseMVPActivity<KnowCatalogContract.View,
        KnowCatalogPresenterImpl> implements KnowCatalogContract.View {
    @BindView(R.id.rv_catalog)
    RecyclerView rvCatalog;

    private KnowCatalogAdapter mAdapter;

    public static void start(Context context, String id, String title) {
        Intent starter = new Intent(context, KnowCatalogActivity.class);
        starter.putExtra(KEY_INTENT,id);
        starter.putExtra("title",title);
        context.startActivity(starter);
    }

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
        String id = getIntent().getStringExtra(KEY_INTENT);
        String title=getIntent().getStringExtra("title");
        setUmengAnalyze(R.string.ask_knowledge);
        setupActionBar(title, true);
        adjustTvTextSize(getTitleTv(),500,title);
        setActionbarBackground(R.color.white);

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

    @Override
    public void getCatalogCache(List<KnowCatalog> catalogList) {
        getCatalogSuccess(catalogList);
        mAdapter.setCache(true);
    }

    private void adjustTvTextSize(TextView tv, int maxWidth, String text) {
        int avaiWidth = maxWidth - tv.getPaddingLeft() - tv.getPaddingRight() - 10;

        if (avaiWidth <= 0) {
            return;
        }

        TextPaint textPaintClone = new TextPaint(tv.getPaint());
        // note that Paint text size works in px not sp
        float trySize = textPaintClone.getTextSize();

        while (textPaintClone.measureText(text) > avaiWidth) {
            trySize--;
            textPaintClone.setTextSize(trySize);
        }

        tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, trySize);
    }
}
