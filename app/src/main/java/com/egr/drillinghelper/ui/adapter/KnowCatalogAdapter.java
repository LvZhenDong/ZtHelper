package com.egr.drillinghelper.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.bean.response.KnowCatalog;
import com.egr.drillinghelper.ui.activity.KnowArticleActivity;
import com.egr.drillinghelper.ui.base.BaseActivity;
import com.egr.drillinghelper.ui.base.BaseListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author lzd
 * date 2017/10/10 9:53
 * 类描述：
 */

public class KnowCatalogAdapter extends BaseListAdapter<KnowCatalog,
        KnowCatalogAdapter.ViewHolder> {
    private boolean isCache;

    public KnowCatalogAdapter(Context context) {
        super(context);
    }

    public void setCache(boolean cache) {
        isCache = cache;
    }

    @Override
    public ViewHolder onLCreateViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
        return new ViewHolder(inflater
                .inflate(R.layout.item_explain_catalog, parent, false));
    }

    @Override
    public void onBindItemHolder(ViewHolder holder, int position) {
        KnowCatalog item = getDataList().get(position);
        holder.tvTitle.setText(item.getTitle());

    }

    void getDetail(int position) {
        KnowCatalog item = getDataList().get(position);
        if (!TextUtils.isEmpty(item.getContent())) {
            Intent intent = new Intent(mContext, KnowArticleActivity.class);
            intent.putExtra(BaseActivity.KEY_INTENT, item.getContent());
            intent.putExtra(BaseActivity.KEY_INTENT_BOOLEAN, isCache);
            intent.putExtra("id",item.getId());
            mContext.startActivity(intent);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.rl_catalog)
        RelativeLayout rlItem;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.line)
        View line;
        @BindView(R.id.iv_play)
        ImageView ivPlay;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            rlItem.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            getDetail(getAdapterPosition());
        }
    }
}
