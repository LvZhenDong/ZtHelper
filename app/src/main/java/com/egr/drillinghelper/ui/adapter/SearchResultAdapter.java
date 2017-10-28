package com.egr.drillinghelper.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.ui.base.BaseListAdapter;
import com.egr.drillinghelper.utils.DensityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author lzd
 * date 2017/10/10 11:09
 * 类描述：搜索结果
 */

public class SearchResultAdapter extends BaseListAdapter<String,
        SearchResultAdapter.ViewHolder> {

    private int paddingLeft;

    public SearchResultAdapter(Context context) {
        super(context);
    }

    public void setPaddingLeft(int paddingLeft) {
        this.paddingLeft = paddingLeft;
    }

    @Override
    public ViewHolder onLCreateViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
        return new ViewHolder(inflater
                .inflate(R.layout.item_search_result, parent, false));
    }

    @Override
    public void onBindItemHolder(ViewHolder holder, int position) {
        String item = getDataList().get(position);
        holder.tvTitle.setPadding(paddingLeft, holder.tvTitle.getPaddingTop(),
                DensityUtils.dp2px(mContext, 10), holder.tvTitle.getPaddingBottom());
        holder.tvTitle.setText(item);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tv_title)
        TextView tvTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
