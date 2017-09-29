package com.egr.drillinghelper.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.bean.response.ExplainCatalog;
import com.egr.drillinghelper.ui.base.BaseListAdapter;
import com.egr.drillinghelper.utils.DensityUtils;
import com.egr.drillinghelper.utils.video.VideoUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author lzd
 * date 2017/9/29 19:24
 * 类描述：
 */

public class ExplainCatalogAdapter extends BaseListAdapter<ExplainCatalog,
        ExplainCatalogAdapter.ViewHolder> {

    private OnArticleChooseListener mListener;

    public ExplainCatalogAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder onLCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.item_explain_catalog, parent, false));
    }

    @Override
    public void onBindItemHolder(ViewHolder holder, int position) {
        ExplainCatalog item = getDataList().get(position);
        holder.tvTitle.setText(item.getTitle());
        holder.ivPlay.setVisibility(TextUtils.isEmpty(item.getUrl()) ? View.GONE : View.VISIBLE);

        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) holder.tvTitle.getLayoutParams();
        params.leftMargin = DensityUtils.dp2px(mContext, item.getDeep() * 20);
        holder.tvTitle.setLayoutParams(params);

        ViewGroup.MarginLayoutParams paramsLine = (ViewGroup.MarginLayoutParams) holder.line.getLayoutParams();
        paramsLine.leftMargin = DensityUtils.dp2px(mContext, item.getDeep() * 20);
        holder.line.setLayoutParams(paramsLine);
    }

    void getDetail(int position) {
        ExplainCatalog item = getDataList().get(position);
        if (!TextUtils.isEmpty(item.getArticleId()) && !item.getArticleId().equals("0")) {
            if (mListener != null)
                mListener.onArticleChoose(item.getArticleId());
        }
    }

    public void setOnArticleChooseListener(OnArticleChooseListener listener) {
        this.mListener = listener;
    }

    public interface OnArticleChooseListener {
        void onArticleChoose(String id);
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
            ivPlay.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.rl_catalog:
                    getDetail(getAdapterPosition());
                    break;
                case R.id.iv_play:
                    VideoUtil.playByOtherApp(mContext, getDataList().get(getAdapterPosition()).getUrl());
                    break;
            }

        }
    }


}
