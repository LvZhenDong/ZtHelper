package com.egr.drillinghelper.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.bean.base.BasePage;
import com.egr.drillinghelper.bean.response.Explain;
import com.egr.drillinghelper.contract.KnowContract;
import com.egr.drillinghelper.mvp.BaseMVPFragment;
import com.egr.drillinghelper.presenter.KnowPresenterImpl;
import com.egr.drillinghelper.ui.activity.KnowCatalogActivity;
import com.egr.drillinghelper.ui.adapter.KnowledgeAdapter;
import com.egr.drillinghelper.ui.widgets.LvEditText;
import com.egr.drillinghelper.utils.ToastUtils;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
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
 * date 2017/9/27 10:19
 * 类描述：知识问答
 */

public class KnowledgeFragment extends BaseMVPFragment<KnowContract.View, KnowPresenterImpl>
        implements KnowContract.View {
    @BindView(R.id.rv_knowledge)
    LRecyclerView rvKnowledge;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.et_search)
    LvEditText etSearch;
    String keyword;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private KnowledgeAdapter mAdapter;

    @Override
    protected KnowPresenterImpl createPresenter() {
        return new KnowPresenterImpl();
    }

    @Override
    public int returnLayoutID() {
        return R.layout.fragment_knowledge;
    }

    @Override
    public void TODO(View view, Bundle savedInstanceState) {
        initSearchEt();
        initRv();
        presenter.getKnowCache();
    }

    @Override
    public void onVisibleToUserChanged(boolean isVisibleToUser, boolean invokeInResumeOrPause) {
        super.onVisibleToUserChanged(isVisibleToUser, invokeInResumeOrPause);
        if (isFirstVisiableToUser && isVisibleToUser) {
            rvKnowledge.forceToRefresh();
            isFirstVisiableToUser = false;
        }
    }

    private void initRv() {
        mAdapter = new KnowledgeAdapter(getActivity());
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mAdapter);
        rvKnowledge.setAdapter(mLRecyclerViewAdapter);

        rvKnowledge.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        rvKnowledge.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvKnowledge.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getKnowList(keyword);
            }
        });
        rvKnowledge.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                presenter.loadMore();
            }
        });
        mLRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                String id = mAdapter.getDataList().get(position).getId();
                Intent intent = new Intent(getActivity(), KnowCatalogActivity.class);
                intent.putExtra(KEY_INTENT, id);
                startActivity(intent);
            }
        });


    }

    private void initSearchEt() {
        etSearch.setOnEnterListener(new LvEditText.OnEnterListener() {
            @Override
            public void onEnterClick(String text) {
                keyword=text;
                rvKnowledge.forceToRefresh();
            }
        });
    }

    @OnClick({R.id.tv_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_search:
                keyword = etSearch.getText().toString().trim();
                rvKnowledge.forceToRefresh();
                break;
        }
    }

    @Override
    public void getKnowFail(String msg) {
        rvKnowledge.refreshComplete(10);
        ToastUtils.show(getActivity(), msg);
    }

    @Override
    public void getKnowListSuccess(BasePage<Explain> data) {
        rvKnowledge.refreshComplete(10);

        if (data.getCurrent() > 1) {
            mAdapter.addAll(data.getRecords());
        } else if (data.getCurrent() == 1) {
            mAdapter.setDataList(data.getRecords());
        }
    }

    @Override
    public void noMoreData() {
        rvKnowledge.refreshComplete(10);
        ToastUtils.show(getActivity(), R.string.no_more_data);
    }

    @Override
    public void showKnowCache(List<Explain> knows) {
        rvKnowledge.refreshComplete(10);
        mAdapter.setDataList(knows);
    }
}
