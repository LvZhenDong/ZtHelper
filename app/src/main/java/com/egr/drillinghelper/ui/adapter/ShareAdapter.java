package com.egr.drillinghelper.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.bean.ShareIcon;
import com.egr.drillinghelper.interfaces.OnItemClickListener;
import com.egr.drillinghelper.ui.base.BaseListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.pgyersdk.c.a.l;
import static com.pgyersdk.c.a.n;

/**
 * author lzd
 * date 2017/10/9 14:37
 * 类描述：
 */

public class ShareAdapter extends BaseListAdapter<ShareIcon,ShareAdapter.ViewHolder> {

    public ShareAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder onLCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_share,
                parent,false));
    }

    @Override
    public void onBindItemHolder(ViewHolder holder, int position) {
        ShareIcon item=getDataList().get(position);
        holder.tvShare.setText(item.getName());
        Drawable top= ContextCompat.getDrawable(mContext,item.getIconId());
        holder.tvShare.setCompoundDrawablesWithIntrinsicBounds(null,top,null,null);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.tv_share)
        TextView tvShare;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            tvShare.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(listener != null)
                listener.onItemClick(getAdapterPosition());
        }
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    OnItemClickListener listener;
}
