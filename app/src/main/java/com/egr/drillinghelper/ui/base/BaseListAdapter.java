package com.egr.drillinghelper.ui.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * base list recyclerview adapter
 *
 * @param <T>
 */
public abstract class BaseListAdapter<T, R extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<R> {
    protected Context mContext;

    protected List<T> mDataList = new ArrayList<>();

    public BaseListAdapter(Context context) {
        mContext = context;
    }

    @Override
    public R onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mContext);
        return onLCreateViewHolder(inflater,parent, viewType);
    }

    @Override
    public void onBindViewHolder(R holder, int position) {
        onBindItemHolder(holder, position);
    }

    //局部刷新关键：带payload的这个onBindViewHolder方法必须实现
    @Override
    public void onBindViewHolder(R holder, int position, List<Object> payloads) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position);
        } else {
            onBindItemHolder(holder, position, payloads);
        }

    }

    public abstract R onLCreateViewHolder(LayoutInflater inflater,ViewGroup parent, int viewType);

    public abstract void onBindItemHolder(R holder, int position);

    public void onBindItemHolder(R holder, int position, List<Object> payloads) {

    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public List<T> getDataList() {
        return mDataList;
    }

    public T getLastItem(){
        return mDataList.get(mDataList.size()-1);
    }

    public void setDataList(List<T> list) {
        this.mDataList.clear();
        this.mDataList.addAll(list);
        notifyDataSetChanged();
    }

    public void add(T t) {
        if (this.mDataList.add(t)) {
            notifyItemInserted(mDataList.size());
        }
    }

    public void addAll(Collection<T> list) {
        int lastIndex = this.mDataList.size();
        if (this.mDataList.addAll(list)) {
            notifyItemRangeInserted(lastIndex, list.size());
        }
    }

    public void addAll(int index, Collection<T> list){
        if(this.mDataList.addAll(index,list)){
            notifyItemRangeInserted(index, list.size());
        }
    }

    public void remove(int position) {
        this.mDataList.remove(position);
        notifyItemRemoved(position);

        if (position != (getDataList().size())) { // 如果移除的是最后一个，忽略
            notifyItemRangeChanged(position, this.mDataList.size() - position);
        }
    }

    public void remove(T t) {
        this.mDataList.remove(t);
        notifyDataSetChanged();
    }

    public void clear() {
        mDataList.clear();
        notifyDataSetChanged();
    }
}