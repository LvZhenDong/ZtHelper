package com.egr.drillinghelper.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.app.EgrAppManager;
import com.egr.drillinghelper.bean.response.StoreDetail;
import com.egr.drillinghelper.contract.PartsDetailContract;
import com.egr.drillinghelper.hybrid.CommBrowserActivity;
import com.egr.drillinghelper.presenter.PartsDetailPresenterImpl;
import com.egr.drillinghelper.ui.adapter.PartsDetailAdapter;
import com.egr.drillinghelper.ui.base.BaseActivity;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;
import com.egr.drillinghelper.utils.CollectionUtil;
import com.egr.drillinghelper.utils.GlideUtils;
import com.egr.drillinghelper.utils.ToastUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author lzd
 * date 2017/10/23 17:40
 * 类描述：
 */

public class PartsDetailActivity extends BaseMVPActivity<PartsDetailContract.View, PartsDetailPresenterImpl>
        implements PartsDetailContract.View {
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_info)
    TextView tvInfo;
    @BindView(R.id.tv_buy)
    TextView tvBuy;
    @BindView(R.id.iv_parts)
    ImageView ivParts;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.ll_parts)
    LinearLayout llParts;

    StoreDetail store;
    PartsDetailAdapter mAdapter;

    public static void start(Context context, String id) {
        Intent intentPart = new Intent(context, PartsDetailActivity.class);
        intentPart.putExtra(BaseActivity.KEY_INTENT, id);
        context.startActivity(intentPart);
    }

    @Override
    public int returnLayoutID() {
        return R.layout.activity_parts_detail;
    }

    @Override
    public void TODO(Bundle savedInstanceState) {
        setUmengAnalyze(R.string.parts_detail);
        setupActionBar(R.string.parts_detail, true);
        setActionbarBackground(R.color.white);
        setActionBarRightText(R.string.back_home, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EgrAppManager.getInstance().finishOtherActivity(HomeActivity.class);
            }
        });

        String id = getIntent().getStringExtra(KEY_INTENT);
        mAdapter = new PartsDetailAdapter(this);
        mAdapter.setOnGetPartsListener(new PartsDetailAdapter.OnGetPartsListener() {
            @Override
            public void onGetParts(String id) {
                presenter.getParts(id);
            }
        });
        rv.setNestedScrollingEnabled(false);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(mAdapter);
        mDialog.show();
        presenter.getContent(id);
    }

    @OnClick({R.id.tv_buy, R.id.iv, R.id.iv_parts})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_buy:
                if (store != null)
                    CommBrowserActivity.start(this, store.getUrl(), store.getName());
                break;
            case R.id.iv:
                if (store != null) {
                    ArrayList<String> imgs = new ArrayList<>();
                    imgs.add(store.getPicture());
                    GalleryActivity.start(this, imgs);
                }
                break;
            case R.id.iv_parts:
                if (store != null) {
                    ArrayList<String> imgs = new ArrayList<>();
                    imgs.add(store.getPartsPicture());
                    GalleryActivity.start(this, imgs);
                }
                break;

        }

    }

    @Override
    public PartsDetailPresenterImpl createPresenter() {
        return new PartsDetailPresenterImpl();
    }

    @Override
    public void getFail(String msg) {
        mDialog.dismiss();
        ToastUtils.show(this, msg);
    }

    @Override
    public void getSuc(StoreDetail data) {
        mDialog.dismiss();
        if (data != null) {
            store = data;
            GlideUtils.load(data.getPicture(), iv);
            tvTitle.setText(data.getName());
            tvInfo.setText(data.getInformation());

            if (!CollectionUtil.isListEmpty(data.getParts())) {
                llParts.setVisibility(View.VISIBLE);
                mAdapter.setDataList(data.getParts());
            }
            if (!TextUtils.isEmpty(data.getPartsPicture())) {
                ivParts.setVisibility(View.VISIBLE);
                GlideUtils.load(data.getPartsPicture(), ivParts);
            }

        }
    }
}
