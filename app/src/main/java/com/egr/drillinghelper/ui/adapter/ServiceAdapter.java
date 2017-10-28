package com.egr.drillinghelper.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.bean.response.ServiceMsg;
import com.egr.drillinghelper.common.UserManager;
import com.egr.drillinghelper.ui.base.BaseListAdapter;
import com.egr.drillinghelper.ui.widgets.SendStateView;
import com.egr.drillinghelper.utils.GlideUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author lzd
 * date 2017/10/24 11:31
 * 类描述：
 */

public class ServiceAdapter extends BaseListAdapter<ServiceMsg, RecyclerView.ViewHolder> {
    LayoutInflater inflater;

    public ServiceAdapter(Context context) {
        super(context);
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onLCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case ServiceMsg.TYPE_SEND_TEXT:
                view = inflater.inflate(R.layout.item_service_msg_text_send, parent, false);
                return new SendTextViewHolder(view);
            case ServiceMsg.TYPE_SEND_IMG:
                view = inflater.inflate(R.layout.item_service_msg_img_send, parent, false);
                return new SendImgViewHolder(view);
            case ServiceMsg.TYPE_REC_TEXT:
                view = inflater.inflate(R.layout.item_service_msg_text_rec, parent, false);
                return new RecTextViewHolder(view);
            case ServiceMsg.TYPE_REC_IMG:
                view = inflater.inflate(R.layout.item_service_msg_img_rec, parent, false);
                return new RecImgViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindItemHolder(RecyclerView.ViewHolder holder, int position) {
        ServiceMsg item = getDataList().get(position);
        ((ShowMsg) holder).showMsg(item);
    }

    @Override
    public int getItemViewType(int position) {
        return getDataList().get(position).getType();
    }

    public void setReSendListener(ReSendListener listener) {
        reSendListener = listener;
    }

    ReSendListener reSendListener;

    public interface ReSendListener {
        void onReSend(int pos);
    }

    class SendTextViewHolder extends RecyclerView.ViewHolder implements ShowMsg {
        @BindView(R.id.tv_time)
        TextView mTvTime;
        @BindView(R.id.iv_send_head)
        ImageView mIvSendHead;
        @BindView(R.id.tv_msg)
        TextView mTvMsg;
        @BindView(R.id.iv_send_state)
        SendStateView mIvSendState;

        public SendTextViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void showMsg(ServiceMsg item) {
            mTvTime.setText(item.getCreateTime());
            mIvSendState.setSendState(item.getSendState());
            GlideUtils.loadCircleImg(UserManager.getInstance().getUserPhoto(), mIvSendHead);
            mTvMsg.setText(item.getMsg());
        }
    }

    class SendImgViewHolder extends RecyclerView.ViewHolder implements ShowMsg {
        @BindView(R.id.tv_time)
        TextView mTvTime;
        @BindView(R.id.iv_send_head)
        ImageView mIvSendHead;
        @BindView(R.id.iv_img)
        ImageView mIvImg;
        @BindView(R.id.iv_send_state)
        SendStateView mIvSendState;

        public SendImgViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void showMsg(ServiceMsg item) {
            mTvTime.setText(item.getCreateTime());
            mIvSendState.setSendState(item.getSendState());
            GlideUtils.loadCircleImg(UserManager.getInstance().getUserPhoto(), mIvSendHead);
            GlideUtils.load(item.getPictureList().get(0), mIvImg);
        }
    }

    class RecTextViewHolder extends RecyclerView.ViewHolder implements ShowMsg {
        @BindView(R.id.tv_time)
        TextView mTvTime;
        @BindView(R.id.iv_receive_head)
        ImageView mIvReceiveHead;
        @BindView(R.id.tv_msg)
        TextView mTvMsg;

        public RecTextViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void showMsg(ServiceMsg item) {
            mTvMsg.setText(item.getMsg());
            mTvTime.setText(item.getCreateTime());
        }
    }

    class RecImgViewHolder extends RecyclerView.ViewHolder implements ShowMsg {
        @BindView(R.id.tv_time)
        TextView mTvTime;
        @BindView(R.id.iv_receive_head)
        ImageView mIvReceiveHead;
        @BindView(R.id.iv_img)
        ImageView mIvImg;

        public RecImgViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void showMsg(ServiceMsg item) {
            mTvTime.setText(item.getCreateTime());
            GlideUtils.load(item.getPictureList().get(0), mIvImg);
        }
    }

    interface ShowMsg {
        void showMsg(ServiceMsg item);
    }
}
