package com.egr.drillinghelper.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.bean.response.Explain;
import com.egr.drillinghelper.ui.base.BaseListAdapter;
import com.egr.drillinghelper.utils.GlideUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author lzd
 * date 2017/9/27 15:36
 * 类描述：
 */

public class ExplainAdapter extends BaseListAdapter<Explain,
        ExplainAdapter.ViewHolder> {

    public ExplainAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder onLCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_explian,
                parent, false));
    }

    @Override
    public void onBindItemHolder(ViewHolder holder, int position) {
        Explain item = getDataList().get(position);
        holder.tvTitle.setText(item.getTitle());
        holder.tvInfo.setText(item.getDescription());
        GlideUtils.load(item.getPhoto(),holder.ivImg);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_img)
        ImageView ivImg;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_info)
        TextView tvInfo;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
