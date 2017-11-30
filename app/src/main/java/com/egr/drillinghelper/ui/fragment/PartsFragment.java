package com.egr.drillinghelper.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.bean.ShareIcon;
import com.egr.drillinghelper.bean.base.BasePage;
import com.egr.drillinghelper.bean.response.Store;
import com.egr.drillinghelper.bean.response.StoreMore;
import com.egr.drillinghelper.contract.PartsContract;
import com.egr.drillinghelper.interfaces.OnItemClickListener;
import com.egr.drillinghelper.mvp.BaseMVPFragment;
import com.egr.drillinghelper.presenter.PartsListPresenterImpl;
import com.egr.drillinghelper.presenter.PartsPresenterImpl;
import com.egr.drillinghelper.ui.activity.PartsListActivity;
import com.egr.drillinghelper.ui.activity.SearchActivity;
import com.egr.drillinghelper.ui.adapter.ChoosePartsAdapter;
import com.egr.drillinghelper.ui.adapter.MallAdapter;
import com.egr.drillinghelper.ui.widgets.RvInScrollView;
import com.egr.drillinghelper.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author lzd
 * date 2017/9/26 18:10
 * 类描述：配件/钻探
 */

public class PartsFragment extends BaseMVPFragment<PartsContract.View, PartsPresenterImpl>
        implements PartsContract.View {
    @BindView(R.id.rv_parts)
    RvInScrollView rvParts;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.tv_mall)
    TextView tvMall;
    @BindView(R.id.ll_search)
    LinearLayout llSearch;
    @BindView(R.id.rl_mall)
    RecyclerView rvMall;
    @BindView(R.id.rl_choose_parts)
    RelativeLayout rlChooseParts;

    String keyword;
    private ChoosePartsAdapter mAdapter;
    private MallAdapter mallAdapter;

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
        setUmengAnalyze(R.string.home_store);
        initSearchEt();
        initRv();
        initMall();
        presenter.getPartsCache();
    }

    private void initMall(){
        mallAdapter=new MallAdapter(getActivity());
        rvMall.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        rvMall.setAdapter(mallAdapter);
    }

    @Override
    public void onVisibleToUserChanged(boolean isVisibleToUser, boolean invokeInResumeOrPause) {
        super.onVisibleToUserChanged(isVisibleToUser, invokeInResumeOrPause);
        if (isFirstVisiableToUser && isVisibleToUser) {
            presenter.getPartsList("");
            isFirstVisiableToUser = false;
        }
    }

    private void initRv() {
        List<ShareIcon> list = new ArrayList<>();
        final int[] resId=new int[]{R.string.choose_parts1,R.string.choose_parts2,R.string.choose_parts3,
                R.string.choose_parts4,R.string.choose_parts5,R.string.choose_parts6};
        list.add(new ShareIcon(R.drawable.ic_parts1, resId[0]));
        list.add(new ShareIcon(R.drawable.ic_parts2, resId[1]));
        list.add(new ShareIcon(R.drawable.ic_parts3, resId[2]));
        list.add(new ShareIcon(R.drawable.ic_parts4, resId[3]));
        list.add(new ShareIcon(R.drawable.ic_parts5, resId[4]));
        list.add(new ShareIcon(R.drawable.ic_parts6, resId[5]));


        mAdapter = new ChoosePartsAdapter(getActivity());
        mAdapter.setListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                PartsListActivity.start(getActivity(),position,resId[position]);
            }
        });

//        rvParts.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        rvParts.setNestedScrollingEnabled(false);
        rvParts.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        rvParts.setAdapter(mAdapter);  //LZD
        mAdapter.setDataList(list);
    }

    private void initSearchEt() {
//        etSearch.setOnEnterListener(new LvEditText.OnEnterListener() {
//            @Override
//            public void onEnterClick(String text) {
//                keyword=text;
//                rvParts.forceToRefresh();
//            }
//        });
    }

    @OnClick({R.id.ll_search,R.id.rl_choose_parts})
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.tv_search:
//                keyword = etSearch.getText().toString().trim();
//                rvParts.forceToRefresh();
//                break;
            case R.id.ll_search:
                onSearchClick(2);
                break;
            case R.id.rl_choose_parts:
                PartsListActivity.start(getActivity(), PartsListPresenterImpl.KEY_ALL,R.string.all);
                break;
        }
    }

    private void onSearchClick(int type) {
        Intent intent = new Intent(getActivity(), SearchActivity.class);
        intent.putExtra(KEY_INTENT, type);
        startActivity(intent);
    }

    @Override
    public void getPastsFail(String msg) {
        tvMall.setVisibility(View.GONE);
        ToastUtils.show(getActivity(), msg);
    }

    @Override
    public void getPartsListSuccess(BasePage<Store> store) {

//        if (store.getCurrent() > 1) {
//            mAdapter.addAll(store.getRecords());
//        } else if (store.getCurrent() == 1) {
//            mAdapter.setDataList(store.getRecords());
//        }
    }

    @Override
    public void noMoreData() {
        ToastUtils.show(getActivity(), R.string.no_more_data);
    }

    @Override
    public void showParts(List<Store> list) {

//        mAdapter.setDataList(list);

    }

    @Override
    public void getMallSuc(List<StoreMore> list) {
        mallAdapter.setDataList(list);
    }
}
