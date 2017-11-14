package com.egr.drillinghelper.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.bean.response.StoreDetail;
import com.egr.drillinghelper.hybrid.CommBrowserActivity;
import com.egr.drillinghelper.ui.base.BaseListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author lzd
 * date 2017/9/27 14:58
 * 类描述：
 */

public class PartsDetailAdapter extends BaseListAdapter<StoreDetail.Parts, PartsDetailAdapter.ViewHolder> {

    public PartsDetailAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder onLCreateViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
        return new ViewHolder(inflater
                .inflate(R.layout.item_parts_detail, parent, false));
    }

    @Override
    public void onBindItemHolder(ViewHolder holder, int position) {
        StoreDetail.Parts item = getDataList().get(position);
        holder.tvItemQuestion.setText(item.getName());
    }

    private void getDetail(int position) {
        StoreDetail.Parts item = getDataList().get(position);
         CommBrowserActivity.start(mContext, item.getUrl(), item.getName());
         if(mListener != null)
             mListener.onGetParts(item.getId());
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tv)
        TextView tvItemQuestion;
        @BindView(R.id.ll_question)
        LinearLayout llContent;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            llContent.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            getDetail(getAdapterPosition());
        }
    }

    OnGetPartsListener mListener;
    public void setOnGetPartsListener(OnGetPartsListener listener){
        mListener=listener;
    }
    public interface OnGetPartsListener{
        void onGetParts(String id);
    }
}
