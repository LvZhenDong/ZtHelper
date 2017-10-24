package com.egr.drillinghelper.ui.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.bean.response.Store;
import com.egr.drillinghelper.hybrid.CommBrowserActivity;
import com.egr.drillinghelper.ui.base.BaseActivity;
import com.egr.drillinghelper.utils.GlideUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author lzd
 * date 2017/10/23 17:40
 * 类描述：
 */

public class PartsDetailActivity extends BaseActivity {
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_info)
    TextView tvInfo;
    @BindView(R.id.tv_buy)
    TextView tvBuy;

    Store store;

    @Override
    public int returnLayoutID() {
        return R.layout.activity_parts_detail;
    }

    @Override
    public void TODO(Bundle savedInstanceState) {
        setupActionBar(R.string.parts_detail, true);
        setActionbarBackground(R.color.white);

        store = (Store) getIntent().getSerializableExtra(KEY_INTENT);

        if (store != null) {
            GlideUtils.load(store.getPicture(), iv);
            tvTitle.setText(store.getName());
            tvInfo.setText(store.getInformation());
        }
    }

    @OnClick(R.id.tv_buy)
    public void onClick() {
        if (store != null)
            CommBrowserActivity.start(this, store.getUrl(), store.getName());
    }
}