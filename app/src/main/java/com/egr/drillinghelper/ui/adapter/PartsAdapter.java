package com.egr.drillinghelper.ui.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.egr.drillinghelper.hybrid.CommBrowserActivity;
import com.egr.drillinghelper.ui.activity.PartsDetailActivity;
import com.egr.drillinghelper.ui.base.BaseActivity;
import com.egr.drillinghelper.ui.base.BaseListAdapter;
import com.egr.drillinghelper.utils.GlideUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author lzd
 * date 2017/9/27 15:36
 * 类描述：
 */

public class PartsAdapter extends BaseListAdapter<Store,
        PartsAdapter.ViewHolder> {

    public final static String INTO_MALL = "-1";

    public PartsAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder onLCreateViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_parts,
                parent, false));
    }

    @Override
    public void onBindItemHolder(ViewHolder holder, int position) {
        Store item = getDataList().get(position);
        holder.rlMall.setVisibility(View.GONE);
        holder.llParts.setVisibility(View.VISIBLE);
        holder.tvTitle.setText(item.getName());
        holder.tvInfo.setText(item.getInformation());
        GlideUtils.load(item.getPicture(), holder.ivImg);


    }

    void goPartsDetail(int position) {
        Store item = getDataList().get(position);

        if (INTO_MALL.equals(item.getId())) { //商城
            CommBrowserActivity.start(mContext, item.getUrl(), item.getName());
        } else {
            Intent intent = new Intent(mContext, PartsDetailActivity.class);
            intent.putExtra(BaseActivity.KEY_INTENT, item);
            mContext.startActivity(intent);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

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

            llParts.setOnClickListener(this);
            rlMall.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            goPartsDetail(getAdapterPosition() - 1);
        }
    }
}
