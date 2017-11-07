package com.egr.drillinghelper.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.bean.base.BasePage;
import com.egr.drillinghelper.bean.response.Video;
import com.egr.drillinghelper.contract.VideoPartContract;
import com.egr.drillinghelper.hybrid.CommBrowserActivity;
import com.egr.drillinghelper.mvp.BaseMVPFragment;
import com.egr.drillinghelper.presenter.VideoPartPresenterImpl;
import com.egr.drillinghelper.ui.adapter.VideoAdapter;
import com.egr.drillinghelper.ui.widgets.LvEditText;
import com.egr.drillinghelper.utils.ToastUtils;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author lzd
 * date 2017/10/25 15:02
 * 类描述：视频区
 */

public class VideoPartFragment extends BaseMVPFragment<VideoPartContract.View,
        VideoPartPresenterImpl> implements VideoPartContract.View {
    @BindView(R.id.rv)
    LRecyclerView rv;
    @BindView(R.id.et_search)
    LvEditText etSearch;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    VideoAdapter mAdapter;
    String keyword;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;

    @Override
    protected VideoPartPresenterImpl createPresenter() {
        return new VideoPartPresenterImpl();
    }

    @Override
    public int returnLayoutID() {
        return R.layout.fragment_video;
    }

    @Override
    public void TODO(View view, Bundle savedInstanceState) {
        setUmengAnalyze(R.string.home_video);
        initSearchEt();
        mAdapter = new VideoAdapter(getActivity());
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mAdapter);
        rv.setAdapter(mLRecyclerViewAdapter);

        rv.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getVideoList(keyword);
            }
        });
        rv.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                presenter.loadMore();
            }
        });
        mLRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Video item = mAdapter.getDataList().get(position);
                CommBrowserActivity.start(getActivity(), item.getUrl(), item.getDesc());
            }
        });
    }

    private void initSearchEt() {
        etSearch.setOnEnterListener(new LvEditText.OnEnterListener() {
            @Override
            public void onEnterClick(String text) {
                keyword=text;
                rv.forceToRefresh();
            }
        });
    }

    @Override
    public void onVisibleToUserChanged(boolean isVisibleToUser, boolean invokeInResumeOrPause) {
        super.onVisibleToUserChanged(isVisibleToUser, invokeInResumeOrPause);
        if (isFirstVisiableToUser && isVisibleToUser) {
            rv.forceToRefresh();
            isFirstVisiableToUser = false;
        }
    }

    @Override
    public void noMoreData() {
        rv.refreshComplete(10);
        ToastUtils.show(getActivity(), R.string.no_more_data);
    }

    @Override
    public void getVideoListSuc(BasePage<Video> data) {
        rv.refreshComplete(10);

        if (data.getCurrent() > 1) {
            mAdapter.addAll(data.getRecords());
        } else if (data.getCurrent() == 1) {
            mAdapter.setDataList(data.getRecords());
        }
    }

    @Override
    public void getVideoListFail(String msg) {
        rv.refreshComplete(10);
        ToastUtils.show(getActivity(), msg);
    }



    @OnClick(R.id.tv_search)
    public void onClick() {
        keyword = etSearch.getText().toString().trim();
        rv.forceToRefresh();
    }
}
