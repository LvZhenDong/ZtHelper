package com.egr.drillinghelper.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.bean.ShareIcon;
import com.egr.drillinghelper.interfaces.OnItemClickListener;
import com.egr.drillinghelper.ui.base.BaseListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author lzd
 * date 2017/10/9 14:37
 * 类描述：
 */

public class ChoosePartsAdapter extends BaseListAdapter<ShareIcon, ChoosePartsAdapter.ViewHolder> {

    OnItemClickListener listener;

    public ChoosePartsAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder onLCreateViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_choose_parts,
                parent, false));
    }

    @Override
    public void onBindItemHolder(ViewHolder holder, int position) {
        ShareIcon item = getDataList().get(position);
        holder.tvShare.setText(item.getName());
        Drawable top = ContextCompat.getDrawable(mContext, item.getIconId());
        holder.tvShare.setCompoundDrawablesWithIntrinsicBounds(null, top, null, null);
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tv_share)
        TextView tvShare;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            tvShare.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (listener != null)
                listener.onItemClick(getAdapterPosition()-1);
        }
    }
}
