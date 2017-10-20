package com.egr.drillinghelper.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.bean.response.Reply;
import com.egr.drillinghelper.ui.activity.GalleryActivity;
import com.egr.drillinghelper.ui.base.BaseActivity;
import com.egr.drillinghelper.ui.base.BaseListAdapter;
import com.egr.drillinghelper.utils.CollectionUtil;
import com.egr.drillinghelper.utils.GlideUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author lzd
 * date 2017/9/27 15:36
 * 类描述：
 */

public class ReplyAdapter extends BaseListAdapter<Reply,
        ReplyAdapter.ViewHolder> {

    OnReplyClickListener mListener;

    public ReplyAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder onLCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_feedback_history,
                parent, false));
    }

    @Override
    public void onBindItemHolder(ViewHolder holder, int position) {
        Reply item = getDataList().get(position);
        holder.tvTime.setText(item.getUpdatetime());
        holder.tvInfo.setText(item.getQuestion());
        if (CollectionUtil.isListEmpty(item.getAttachments())) {
            holder.ivImg.setVisibility(View.GONE);
        } else {
            holder.ivImg.setVisibility(View.VISIBLE);
            GlideUtils.load(item.getAttachments().get(0), holder.ivImg);
        }
        holder.tvReadReply.setVisibility(TextUtils.isEmpty(item.getAnswer()) ? View.INVISIBLE : View.VISIBLE);
    }

    void showReply(int position) {
        if (mListener != null)
            mListener.onReplyClick(getDataList().get(position).getAnswer());
    }

    void showImgs(int position) {
        Intent intent=new Intent(mContext, GalleryActivity.class);
        intent.putExtra(BaseActivity.KEY_INTENT,getDataList().get(position).getAttachments());
        mContext.startActivity(intent);
    }

    public void setOnReplyClickListener(OnReplyClickListener listener) {
        mListener = listener;
    }

    public interface OnReplyClickListener {
        void onReplyClick(String reply);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.iv_img)
        ImageView ivImg;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_info)
        TextView tvInfo;
        @BindView(R.id.tv_read_reply)
        TextView tvReadReply;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            tvReadReply.setOnClickListener(this);
            ivImg.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_read_reply:
                    showReply(getAdapterPosition() - 1);  //有下拉刷新，所以-1
                    break;
                case R.id.iv_img:
                    showImgs(getAdapterPosition() - 1);
                    break;
            }

        }
    }

}
