package com.egr.drillinghelper.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.bean.response.StoreMore;
import com.egr.drillinghelper.hybrid.CommBrowserActivity;
import com.egr.drillinghelper.ui.base.BaseListAdapter;
import com.egr.drillinghelper.utils.GlideUtils;
import com.makeramen.roundedimageview.RoundedImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/11/23.
 */

public class MallAdapter extends BaseListAdapter<StoreMore, MallAdapter.ViewHolder> {

    public MallAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder onLCreateViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_mall,
                parent, false));
    }

    @Override
    public void onBindItemHolder(ViewHolder holder, int position) {
        StoreMore item=getDataList().get(position);
        holder.mTvName.setText(item.getName());
        GlideUtils.load(item.getPicture(),holder.mIvImg);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.iv_img)
        RoundedImageView mIvImg;
        @BindView(R.id.tv_name)
        TextView mTvName;
        @BindView(R.id.ll_content)
        LinearLayout mLL;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            mLL.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            StoreMore item=getDataList().get(getAdapterPosition());
            CommBrowserActivity.start(mContext, item.getUrl(), item.getName());
        }
    }
}
