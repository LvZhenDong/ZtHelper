package com.egr.drillinghelper.ui.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.egr.drillinghelper.R;
import com.egr.drillinghelper.bean.response.Instruction;
import com.egr.drillinghelper.ui.base.BaseListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author lzd
 * date 2017/9/27 15:36
 * 类描述：
 */

public class PartsAdapter extends BaseListAdapter<Instruction,
        PartsAdapter.ViewHolder> {

    public PartsAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder onLCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_parts,
                parent, false));
    }

    @Override
    public void onBindItemHolder(ViewHolder holder, int position) {
        Instruction item=getDataList().get(position);
        if(item.getImgId() == 0){
            holder.rlMall.setVisibility(View.VISIBLE);
            holder.llParts.setVisibility(View.GONE);
        }else {
            holder.rlMall.setVisibility(View.GONE);
            holder.llParts.setVisibility(View.VISIBLE);
            holder.tvTitle.setText(item.getTitle());
            holder.tvInfo.setText(item.getContent());
            Glide.with(mContext).load(item.getImgId()).into(holder.ivImg);
        }

    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ll_parts)
        LinearLayout llParts;
        @BindView(R.id.rl_mall)
        RelativeLayout rlMall;
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
