package com.egr.drillinghelper.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.bean.response.ServiceMsg;
import com.egr.drillinghelper.common.UserManager;
import com.egr.drillinghelper.ui.base.BaseListAdapter;
import com.egr.drillinghelper.utils.CollectionUtil;
import com.egr.drillinghelper.utils.GlideUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author lzd
 * date 2017/10/24 11:31
 * 类描述：
 */

public class ServiceAdapter extends BaseListAdapter<ServiceMsg, ServiceAdapter.ViewHolder> {

    public ServiceAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder onLCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_service_msg,
                parent, false));
    }

    @Override
    public void onBindItemHolder(ViewHolder holder, int position) {
        ServiceMsg item = getDataList().get(position);
        holder.show(item);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_receive_head)
        ImageView ivReceiveHead;
        @BindView(R.id.tv_receive_msg)
        TextView tvReceiveMsg;
        @BindView(R.id.rl_receive)
        RelativeLayout rlReceive;
        @BindView(R.id.iv_send_head)
        ImageView ivSendHead;
        @BindView(R.id.tv_send_msg)
        TextView tvSendMsg;
        @BindView(R.id.rl_send)
        RelativeLayout rlSend;
        @BindView(R.id.iv_receive_img)
        ImageView ivReceiveImg;
        @BindView(R.id.iv_send_img)
        ImageView ivSendImg;
        @BindView(R.id.tv_time)
        TextView tvTime;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void show(ServiceMsg item) {
            tvTime.setText(item.getCreateTime());
            if (item.isSend()) {
                GlideUtils.loadCircleImg(UserManager.getInstance().getUserPhoto(),ivSendHead);
                rlSend.setVisibility(View.VISIBLE);
                rlReceive.setVisibility(View.GONE);
                if (CollectionUtil.isListEmpty(item.getPictureList())) {
                    tvSendMsg.setText(item.getMsg());
                    tvSendMsg.setVisibility(View.VISIBLE);
                    ivSendImg.setVisibility(View.GONE);
                } else {
                    GlideUtils.load(item.getPictureList().get(0), ivSendImg);
                    tvSendMsg.setVisibility(View.GONE);
                    ivSendImg.setVisibility(View.VISIBLE);
                }
            } else {
                rlSend.setVisibility(View.GONE);
                rlReceive.setVisibility(View.VISIBLE);
                tvReceiveMsg.setText(item.getMsg());
                if (CollectionUtil.isListEmpty(item.getPictureList())) {
                    tvReceiveMsg.setText(item.getMsg());
                    tvReceiveMsg.setVisibility(View.VISIBLE);
                    ivReceiveImg.setVisibility(View.GONE);
                } else {
                    GlideUtils.load(item.getPictureList().get(0), ivReceiveImg);
                    tvReceiveMsg.setVisibility(View.GONE);
                    ivReceiveImg.setVisibility(View.VISIBLE);
                }
            }
        }
    }
}
