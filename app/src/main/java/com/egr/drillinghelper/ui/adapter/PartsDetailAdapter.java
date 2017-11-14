package com.egr.drillinghelper.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.bean.response.Feedback;
import com.egr.drillinghelper.ui.activity.FeedbackDetailActivity;
import com.egr.drillinghelper.ui.base.BaseActivity;
import com.egr.drillinghelper.ui.base.BaseListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author lzd
 * date 2017/9/27 14:58
 * 类描述：
 */

public class PartsDetailAdapter extends BaseListAdapter<Feedback, PartsDetailAdapter.ViewHolder> {

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
        holder.tvItemQuestion.setText(getDataList().get(position).getQuestion());
    }

    private void getDetail(int position) {
        Intent intent = new Intent(mContext, FeedbackDetailActivity.class);
        intent.putExtra(BaseActivity.KEY_INTENT, getDataList().get(position).getId());
        mContext.startActivity(intent);
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
}
