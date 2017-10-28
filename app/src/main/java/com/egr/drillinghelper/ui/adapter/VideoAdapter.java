package com.egr.drillinghelper.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.bean.response.Video;
import com.egr.drillinghelper.ui.base.BaseListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author lzd
 * date 2017/10/10 9:53
 * 类描述：
 */

public class VideoAdapter extends BaseListAdapter<Video,
        VideoAdapter.ViewHolder> {

    public VideoAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder onLCreateViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
        return new ViewHolder(inflater
                .inflate(R.layout.item_explain_catalog, parent, false));
    }

    @Override
    public void onBindItemHolder(ViewHolder holder, int position) {
        Video item = getDataList().get(position);
        holder.tvTitle.setText(item.getDesc());
    }

    class ViewHolder extends RecyclerView.ViewHolder {
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
        }

    }

}
