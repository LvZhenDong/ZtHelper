package com.egr.drillinghelper.ui.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.bean.response.Message;
import com.egr.drillinghelper.ui.base.BaseListAdapter;
import com.egr.drillinghelper.ui.widgets.SwipeMenuView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author lzd
 * date 2017/9/27 15:36
 * 类描述：
 */

public class MessageAdapter extends BaseListAdapter<Message,
        MessageAdapter.ViewHolder> {

    private SwipeListener listener;

    public MessageAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder onLCreateViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_message,
                parent, false));
    }

    @Override
    public void onBindItemHolder(ViewHolder holder, int position) {
        Message item = getDataList().get(position);
        holder.tvInfo.setText(item.getMsg());
        holder.tvTitle.setText(item.getTitle());
        holder.tvTime.setText(item.getUpdatetime());
        holder.tvTitle.setTextColor(ContextCompat.getColor(mContext,
                item.isIsRead() ? R.color.tv999 : R.color.tv333));
        holder.tvInfo.setTextColor(ContextCompat.getColor(mContext,
                item.isIsRead() ? R.color.tv999 : R.color.tv666));

    }

    public void setListener(SwipeListener listener) {
        this.listener = listener;
    }

    public interface SwipeListener {
        void onDelete(int position);

        void onItemClick(int position);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_info)
        TextView tvInfo;
        @BindView(R.id.sw_content)
        SwipeMenuView swContent;
        @BindView(R.id.tv_delete)
        TextView tvDelete;
        @BindView(R.id.cv_msg)
        CardView cvMsg;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            cvMsg.setOnClickListener(this);
            tvDelete.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.cv_msg:
                    if (listener != null)
                        listener.onItemClick(getAdapterPosition() - 1);
                    break;
                case R.id.tv_delete:
                    if (listener != null)
                        listener.onDelete(getAdapterPosition() - 1);
                    break;
            }
        }
    }
}
