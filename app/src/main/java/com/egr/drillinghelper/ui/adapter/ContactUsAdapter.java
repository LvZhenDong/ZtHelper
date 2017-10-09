package com.egr.drillinghelper.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.bean.response.ContactUs;
import com.egr.drillinghelper.bean.response.Instruction;
import com.egr.drillinghelper.ui.base.BaseListAdapter;
import com.egr.drillinghelper.utils.TextViewUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author lzd
 * date 2017/9/27 15:36
 * 类描述：
 */

public class ContactUsAdapter extends BaseListAdapter<ContactUs.ContactListBean,
        ContactUsAdapter.ViewHolder> {

    public ContactUsAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder onLCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_contact_us,
                parent, false));
    }

    @Override
    public void onBindItemHolder(ViewHolder holder, int position) {
        ContactUs.ContactListBean item = getDataList().get(position);
        holder.tvTitle.setText(item.getName());
        TextViewUtil.setText(holder.tvAddress,mContext.getString(R.string.tag_address),item.getAddress());
        TextViewUtil.setText(holder.tvPhone,mContext.getString(R.string.tag_tel),item.getTel());
        TextViewUtil.setText(holder.tvWeb,mContext.getString(R.string.tag_website),item.getWebsite());
        TextViewUtil.setText(holder.tvEmail,mContext.getString(R.string.tag_email),item.getEmail());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_address)
        TextView tvAddress;
        @BindView(R.id.tv_phone)
        TextView tvPhone;
        @BindView(R.id.tv_web)
        TextView tvWeb;
        @BindView(R.id.tv_email)
        TextView tvEmail;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
