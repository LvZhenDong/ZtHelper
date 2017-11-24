package com.egr.drillinghelper.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.widget.GridLayoutManager;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.bean.base.BasePage;
import com.egr.drillinghelper.bean.response.Store;
import com.egr.drillinghelper.contract.PartsListContract;
import com.egr.drillinghelper.presenter.PartsListPresenterImpl;
import com.egr.drillinghelper.ui.adapter.PartsAdapter;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;
import com.egr.drillinghelper.utils.CollectionUtil;
import com.egr.drillinghelper.utils.ToastUtils;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;

import butterknife.BindView;

public class PartsListActivity extends BaseMVPActivity<PartsListContract.View,
        PartsListPresenterImpl> implements PartsListContract.View {

    @BindView(R.id.rv_parts)
    LRecyclerView mRvParts;
    
    PartsAdapter mAdapter;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;

    int type;

    public static void start(Context context, int type, @StringRes int titleId) {
        Intent starter = new Intent(context, PartsListActivity.class);
        starter.putExtra(KEY_INTENT, type);
        starter.putExtra("title", titleId);
        context.startActivity(starter);
    }

    @Override
    public int returnLayoutID() {
        return R.layout.activity_parts_list;
    }

    @Override
    public PartsListPresenterImpl createPresenter() {
        return new PartsListPresenterImpl();
    }

    @Override
    public void TODO(Bundle savedInstanceState) {
        int titleId = getIntent().getIntExtra("title", R.string.all);
        setupActionBar(titleId, true);
        setActionbarBackground(R.color.white);
        type = getIntent().getIntExtra(KEY_INTENT, 0);

        initRv();
        mDialog.show();
        presenter.getPartsList(type);
    }
    
    private void initRv(){
        mAdapter = new PartsAdapter(getActivity());
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mAdapter);

        mRvParts.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRvParts.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mRvParts.setAdapter(mLRecyclerViewAdapter);  //LZD
        mRvParts.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getPartsList(type);
            }
        });
        mRvParts.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                presenter.loadMore();
            }
        });
    }

    @Override
    public void getPastsFail(String msg) {
        mDialog.dismiss();
        mRvParts.refreshComplete(10);
        ToastUtils.show(getActivity(), msg);
    }

    @Override
    public void getPartsListSuccess(BasePage<Store> data) {
        mDialog.dismiss();
        mRvParts.refreshComplete(10);

        if (data.getCurrent() > 1) {
            mAdapter.addAll(data.getRecords());
        } else if (data.getCurrent() == 1) {
            if(CollectionUtil.isListEmpty(data.getRecords()))
                ToastUtils.show(this,R.string.no_data);
            mAdapter.setDataList(data.getRecords());
        }
    }

    @Override
    public void noMoreData() {
        mDialog.dismiss();
        mRvParts.refreshComplete(10);
        ToastUtils.show(getActivity(), R.string.no_more_data);
    }
}
