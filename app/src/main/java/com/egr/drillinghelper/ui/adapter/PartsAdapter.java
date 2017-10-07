package com.egr.drillinghelper.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.bean.response.Store;
import com.egr.drillinghelper.ui.base.BaseListAdapter;
import com.egr.drillinghelper.utils.GlideUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author lzd
 * date 2017/9/27 15:36
 * 类描述：
 */

public class PartsAdapter extends BaseListAdapter<Store.RecordsBean,
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
    public void setDataList(List<Store.RecordsBean> list) {
        //添加进入商城的item
        Store.RecordsBean bean=new Store.RecordsBean();
        bean.setInformation("进入商城");
        list.add(0, bean);
        super.setDataList(list);
    }

    @Override
    public void onBindItemHolder(ViewHolder holder, int position) {
        Store.RecordsBean item = getDataList().get(position);
        if ("进入商城".equals(item.getInformation())) { //进入商城的处理
            holder.rlMall.setVisibility(View.VISIBLE);
            holder.llParts.setVisibility(View.GONE);
        } else {
            holder.rlMall.setVisibility(View.GONE);
            holder.llParts.setVisibility(View.VISIBLE);
            holder.tvTitle.setText(item.getName());
            holder.tvInfo.setText(item.getInformation());
            GlideUtils.load(item.getPicture(),holder.ivImg);
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
