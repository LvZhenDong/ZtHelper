package com.egr.drillinghelper.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.bean.base.BasePage;
import com.egr.drillinghelper.bean.response.Store;
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
import butterknife.OnClick;

/**
 * author lzd
 * date 2017/9/26 18:10
 * 类描述：配件/钻探
 */

public class PartsFragment extends BaseMVPFragment<PartsContract.View,PartsPresenterImpl>
    implements PartsContract.View{
    @BindView(R.id.rv_parts)
    LRecyclerView rvParts;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.et_search)
    EditText etSearch;

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
        initSearchEt();
        initRv();
        presenter.getPartsCache();
    }

    @Override
    public void onVisibleToUserChanged(boolean isVisibleToUser, boolean invokeInResumeOrPause) {
        super.onVisibleToUserChanged(isVisibleToUser, invokeInResumeOrPause);
        if (isFirstVisiableToUser && isVisibleToUser) {
            rvParts.forceToRefresh();
            isFirstVisiableToUser = false;
        }
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
                presenter.getPartsList(keyword);
            }
        });
        rvParts.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                presenter.loadMore();
            }
        });


    }

    String keyword;

    private void initSearchEt() {
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEND || actionId == EditorInfo.IME_ACTION_DONE
                        || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                    //处理事件
                    keyword = etSearch.getText().toString().trim();
                    rvParts.forceToRefresh();
                }
                return false;
            }
        });
    }

    @OnClick({R.id.tv_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_search:
                keyword = etSearch.getText().toString().trim();
                rvParts.forceToRefresh();
                break;
        }
    }

    @Override
    public void getPastsFail(String msg) {
        rvParts.refreshComplete(10);
        ToastUtils.show(getActivity(),msg);
    }

    @Override
    public void getPartsListSuccess(BasePage<Store> store) {
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

    @Override
    public void showParts(List<Store> list) {
        rvParts.refreshComplete(10);

        mAdapter.setDataList(list);

    }
}
