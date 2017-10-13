package com.egr.drillinghelper.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.bean.response.Store;
import com.egr.drillinghelper.bean.response.StoreMore;
import com.egr.drillinghelper.contract.PartsContract;
import com.egr.drillinghelper.mvp.BaseMVPFragment;
import com.egr.drillinghelper.presenter.PartsPresenterImpl;
import com.egr.drillinghelper.ui.adapter.PartsAdapter;
import com.egr.drillinghelper.utils.ToastUtils;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;

import java.util.List;

import butterknife.BindView;

import static android.R.id.list;

/**
 * author lzd
 * date 2017/9/26 18:10
 * 类描述：配件/钻探
 */

public class PartsFragment extends BaseMVPFragment<PartsContract.View,PartsPresenterImpl>
    implements PartsContract.View{
    @BindView(R.id.rv_parts)
    LRecyclerView rvParts;

    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private PartsAdapter mAdapter;

    @Override
    protected PartsPresenterImpl createPresenter() {
        return new PartsPresenterImpl();
    }

    @Override
    public int returnLayoutID() {
        return R.layout.fragment_parts;
    }

    @Override
    public void TODO(View view, Bundle savedInstanceState) {
        initRv();
    }

    private void initRv(){
        mAdapter=new PartsAdapter(getActivity());
        mLRecyclerViewAdapter=new LRecyclerViewAdapter(mAdapter);

        rvParts.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        rvParts.setLayoutManager(new GridLayoutManager(getActivity(),2));
        rvParts.setAdapter(mLRecyclerViewAdapter);  //LZD
        rvParts.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getPartsList();
            }
        });
        rvParts.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                presenter.loadMore();
            }
        });

        presenter.getPartsList();
    }

    @Override
    public void getPastsFail(String msg) {
        rvParts.refreshComplete(10);
        ToastUtils.show(getActivity(),msg);
    }

    @Override
    public void getPartsListSuccess(Store store) {
        rvParts.refreshComplete(10);

        if(store.getCurrent() > 1){
            mAdapter.addAll(store.getRecords());
        }else if(store.getCurrent() == 1){
            mAdapter.setDataList(store.getRecords());
        }
    }

    @Override
    public void noMoreData() {
        rvParts.refreshComplete(10);
        ToastUtils.show(getActivity(), R.string.no_more_data);
    }
}
