package com.egr.drillinghelper.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.bean.response.Explain;
import com.egr.drillinghelper.contract.KnowContract;
import com.egr.drillinghelper.mvp.BaseMVPFragment;
import com.egr.drillinghelper.presenter.KnowPresenterImpl;
import com.egr.drillinghelper.ui.adapter.KnowledgeAdapter;
import com.egr.drillinghelper.utils.ToastUtils;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;

import butterknife.BindView;

/**
 * author lzd
 * date 2017/9/27 10:19
 * 类描述：知识问答
 */

public class KnowledgeFragment extends BaseMVPFragment<KnowContract.View, KnowPresenterImpl>
        implements KnowContract.View {
    @BindView(R.id.rv_knowledge)
    LRecyclerView rvKnowledge;

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
        initRv();
    }


    private void initRv() {
        mAdapter = new KnowledgeAdapter(getActivity());
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mAdapter);
        rvKnowledge.setAdapter(mLRecyclerViewAdapter);

        rvKnowledge.setRefreshProgressStyle(ProgressStyle.TriangleSkewSpin);
        rvKnowledge.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvKnowledge.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getKnowList();
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
                //TODO 知识问答目录
            }
        });
        presenter.getKnowList();

    }

    @Override
    public void getKnowFail(String msg) {
        rvKnowledge.refreshComplete(10);
        ToastUtils.show(getActivity(), msg);
    }

    @Override
    public void getKnowListSuccess(Explain explain) {
        rvKnowledge.refreshComplete(10);

        if (explain.getCurrent() > 1) {
            mAdapter.addAll(explain.getRecords());
        } else if (explain.getCurrent() == 1) {
            mAdapter.setDataList(explain.getRecords());
        }
    }

    @Override
    public void noMoreData() {
        rvKnowledge.refreshComplete(10);
        ToastUtils.show(getActivity(), R.string.no_more_data);
    }
}
