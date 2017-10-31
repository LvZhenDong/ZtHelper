package com.egr.drillinghelper.ui.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.bean.response.KnowCatalog;
import com.egr.drillinghelper.bean.response.ServiceMsg;
import com.egr.drillinghelper.common.UserManager;
import com.egr.drillinghelper.ui.base.BaseListAdapter;
import com.egr.drillinghelper.ui.widgets.SendStateView;
import com.egr.drillinghelper.utils.GlideUtils;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author lzd
 * date 2017/10/24 11:31
 * 类描述：
 */

public class ServiceAdapter extends BaseListAdapter<ServiceMsg, RecyclerView.ViewHolder> {

    Gson gson = new Gson();
    ReSendListener reSendListener;
    ClickMatchListener matchListener;

    public ServiceAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onLCreateViewHolder(LayoutInflater inflater, ViewGroup parent,
                                                       int viewType) {
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
            case ServiceMsg.TYPE_REC_MATCH:
                view = inflater.inflate(R.layout.item_service_msg_match_text_rec, parent, false);
                return new MatchViewHolder(view);
            case ServiceMsg.TYPE_REC_MATCH_EMPTY:
                view = inflater.inflate(R.layout.item_service_msg_match_empty_text_rec, parent, false);
                return new MatchEmptyViewHolder(view);
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

    public void setMatchListener(ClickMatchListener listener) {
        this.matchListener = listener;
    }

    public interface ReSendListener {
        void onReSend(int pos);
    }

    public interface ClickMatchListener {
        void resolved(String id);

        void unResolved(String id);

        void showContent(String content);
    }

    interface ShowMsg {
        void showMsg(ServiceMsg item);
    }

    class SendTextViewHolder extends RecyclerView.ViewHolder implements ShowMsg, View.OnClickListener {
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
            mIvSendState.setOnClickListener(this);
        }

        @Override
        public void showMsg(ServiceMsg item) {
            mTvTime.setText(item.getCreateTime());
            mIvSendState.setSendState(item.getSendState());
            GlideUtils.loadCircleImg(UserManager.getInstance().getUserPhoto(), mIvSendHead);
            ServiceMsg.ChatBean chatBean = gson.fromJson(item.getMsg(), ServiceMsg.ChatBean.class);
            mTvMsg.setText(chatBean.getMessage());
        }

        @Override
        public void onClick(View v) {
            if (reSendListener != null) reSendListener.onReSend(getAdapterPosition() - 1);
        }
    }

    class SendImgViewHolder extends RecyclerView.ViewHolder implements ShowMsg, View.OnClickListener {
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
            mIvSendState.setOnClickListener(this);
        }

        @Override
        public void showMsg(ServiceMsg item) {
            mTvTime.setText(item.getCreateTime());
            mIvSendState.setSendState(item.getSendState());
            GlideUtils.loadCircleImg(UserManager.getInstance().getUserPhoto(), mIvSendHead);
            GlideUtils.load(item.getPictureList().get(0), mIvImg);
        }

        @Override
        public void onClick(View v) {
            if (reSendListener != null) reSendListener.onReSend(getAdapterPosition() - 1);
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
            ServiceMsg.ChatBean chatBean = gson.fromJson(item.getMsg(), ServiceMsg.ChatBean.class);
            mTvMsg.setText(chatBean.getMessage());
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

    class MatchViewHolder extends RecyclerView.ViewHolder implements ShowMsg, View.OnClickListener {
        @BindView(R.id.tv_time)
        TextView mTvTime;
        @BindView(R.id.tv_hint1)
        TextView mTvHint1;
        @BindView(R.id.rv_match)
        RecyclerView mRvMatch;
        @BindView(R.id.tv_hint2)
        TextView mTvHint2;
        @BindView(R.id.tv_resolved)
        TextView mTvResolved;
        @BindView(R.id.tv_unsolved)
        TextView mTvUnsolved;

        MatchListAdapter adapter;

        public MatchViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            adapter = new MatchListAdapter(mContext);
            mRvMatch.setLayoutManager(new LinearLayoutManager(mContext));
            mRvMatch.setAdapter(adapter);

            mTvResolved.setOnClickListener(this);
            mTvUnsolved.setOnClickListener(this);
        }

        @Override
        public void showMsg(ServiceMsg item) {
            String msgStr = item.getMsg();
            ServiceMsg.KnowBean knowBean = gson.fromJson(msgStr, ServiceMsg.KnowBean.class);
            mTvTime.setText(item.getCreateTime());
            adapter.setDataList(knowBean.getMessage());
        }

        @Override
        public void onClick(View v) {
            ServiceMsg serviceMsg = getDataList().get(getAdapterPosition() - 1);
            if(serviceMsg.getStatus() != 0)return;
            switch (v.getId()) {
                case R.id.tv_resolved:
                    if (matchListener != null)
                        matchListener.resolved(serviceMsg.getSupportId());
                    break;
                case R.id.tv_unsolved:
                    if (matchListener != null)
                        matchListener.unResolved(serviceMsg.getSupportId());
                    break;
            }
        }
    }

    class MatchEmptyViewHolder extends RecyclerView.ViewHolder implements ShowMsg, View.OnClickListener {
        @BindView(R.id.tv_time)
        TextView mTvTime;
        @BindView(R.id.tv_resolved)
        TextView mTvNotNeed;
        @BindView(R.id.tv_unsolved)
        TextView mTvNeed;

        public MatchEmptyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            mTvNotNeed.setOnClickListener(this);
            mTvNeed.setOnClickListener(this);
        }

        @Override
        public void showMsg(ServiceMsg item) {
            mTvTime.setText(item.getCreateTime());
        }

        @Override
        public void onClick(View v) {
            ServiceMsg serviceMsg = getDataList().get(getAdapterPosition() - 1);
            if(serviceMsg.getStatus() != 0)return;
            switch (v.getId()) {
                case R.id.tv_resolved:
                    if (matchListener != null)
                        matchListener.resolved(serviceMsg.getSupportId());
                    break;
                case R.id.tv_unsolved:
                    if (matchListener != null)
                        matchListener.unResolved(serviceMsg.getSupportId());
                    break;
            }
        }
    }

    class MatchListAdapter extends BaseListAdapter<KnowCatalog, MatchListAdapter.TitleViewHolder> {
        public MatchListAdapter(Context context) {
            super(context);
        }

        @Override
        public TitleViewHolder onLCreateViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.item_match, parent, false);
            return new TitleViewHolder(view);
        }

        @Override
        public void onBindItemHolder(TitleViewHolder holder, int position) {
            KnowCatalog item = getDataList().get(position);
            holder.mTvTitle.setText(item.getTitle());
        }

        class TitleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            @BindView(R.id.tv_title)
            TextView mTvTitle;
            @BindView(R.id.ll_content)
            LinearLayout mLlContent;

            public TitleViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);

                mLlContent.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                if (matchListener != null)
                    matchListener.showContent(getDataList().get(getAdapterPosition()).getContent());
            }
        }
    }
}
