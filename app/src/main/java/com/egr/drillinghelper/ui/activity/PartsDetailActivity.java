package com.egr.drillinghelper.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.bean.response.Store;
import com.egr.drillinghelper.bean.response.StoreDetail;
import com.egr.drillinghelper.contract.PartsDetailContract;
import com.egr.drillinghelper.hybrid.CommBrowserActivity;
import com.egr.drillinghelper.presenter.PartsDetailPresenterImpl;
import com.egr.drillinghelper.ui.adapter.PartsDetailAdapter;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;
import com.egr.drillinghelper.ui.widgets.DialogHelper;
import com.egr.drillinghelper.utils.CollectionUtil;
import com.egr.drillinghelper.utils.GlideUtils;
import com.egr.drillinghelper.utils.ToastUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import cc.cloudist.acplibrary.ACProgressFlower;

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

    Store store;
    PartsDetailAdapter mAdapter;
    private ACProgressFlower mDialog;

    @Override
    public int returnLayoutID() {
        return R.layout.activity_parts_detail;
    }

    @Override
    public void TODO(Bundle savedInstanceState) {
        setUmengAnalyze(R.string.parts_detail);
        setupActionBar(R.string.parts_detail, true);
        setActionbarBackground(R.color.white);

        store = (Store) getIntent().getSerializableExtra(KEY_INTENT);
        mAdapter = new PartsDetailAdapter(this);
        rv.setNestedScrollingEnabled(false);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(mAdapter);
        if (store != null) {
            mDialog = DialogHelper.openiOSPbDialog(this, getString(R.string.waiting));
            mDialog.show();
//            presenter.getContent("12q6c5acaa8841b381bb4a111310a166");
            presenter.getContent(store.getId());
        }
    }

    @OnClick({R.id.tv_buy, R.id.iv})
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
            GlideUtils.load(data.getPicture(), iv);
            tvTitle.setText(data.getName());
            tvInfo.setText(data.getInformation());

            if(!CollectionUtil.isListEmpty(data.getParts())){
                llParts.setVisibility(View.VISIBLE);
                mAdapter.setDataList(data.getParts());
            }
            if(!TextUtils.isEmpty(data.getPartsPicture())){
                llParts.setVisibility(View.VISIBLE);
                ivParts.setVisibility(View.VISIBLE);
                GlideUtils.load(data.getPartsPicture(),ivParts);
            }

        }
    }
}
