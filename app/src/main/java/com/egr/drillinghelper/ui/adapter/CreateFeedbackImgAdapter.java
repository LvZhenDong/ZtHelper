package com.egr.drillinghelper.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.ui.base.BaseListAdapter;
import com.egr.drillinghelper.utils.GlideUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author lzd
 * date 2017/10/10 14:53
 * 类描述：
 */

public class CreateFeedbackImgAdapter extends BaseListAdapter<String,
        CreateFeedbackImgAdapter.ViewHolder> {

    public final static int IMG_NUMBER = 6;

    public final static String ITEM_ADD = "item add";
    private CallBack mListener;

    public CreateFeedbackImgAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder onLCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_img,
                parent, false));
    }

    @Override
    public void onBindItemHolder(ViewHolder holder, int position) {
        if (ITEM_ADD.equals(getDataList().get(position)))
            GlideUtils.loadLocalImg(R.drawable.ic_add_img, holder.ivImg);
        else
            GlideUtils.loadLocalImg(getDataList().get(position), holder.ivImg);
    }

    private void onImgClick(int position) {
        if (ITEM_ADD.equals(getDataList().get(position))) {
            if (mListener != null)
                mListener.onAddClick();
        } else {
            remove(position);
            int length=getDataList().size();
            if (length == IMG_NUMBER - 1 &&
                    !ITEM_ADD.equals(getDataList().get(length-1)))
                getDataList().add(ITEM_ADD);
            mListener.removeItem(position);
        }
    }

    @Override
    public void setDataList(List<String> list) {
        if (list == null) {
            list = new ArrayList<>();
        }
        if (list.size() < IMG_NUMBER) {
            list.add(ITEM_ADD);
        }

        super.setDataList(list);
    }

    public void setListener(CallBack listener) {
        this.mListener = listener;
    }

    public interface CallBack {
        void onAddClick();

        void removeItem(int position);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.iv_img)
        ImageView ivImg;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            ivImg.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onImgClick(getAdapterPosition());


        }
    }
}
