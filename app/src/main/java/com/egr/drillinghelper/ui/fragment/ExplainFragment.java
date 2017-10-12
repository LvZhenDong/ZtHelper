package com.egr.drillinghelper.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.bean.base.BasePage;
import com.egr.drillinghelper.bean.response.Explain;
import com.egr.drillinghelper.contract.ExplainContract;
import com.egr.drillinghelper.mvp.BaseMVPFragment;
import com.egr.drillinghelper.presenter.ExplainPresenterImpl;
import com.egr.drillinghelper.ui.activity.ExplainCatalogActivity;
import com.egr.drillinghelper.ui.adapter.ExplainAdapter;
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
 * date 2017/9/27 10:17
 * 类描述：使用说明
 */

public class ExplainFragment extends BaseMVPFragment<ExplainContract.View,
        ExplainPresenterImpl>
        implements ExplainContract.View {
    @BindView(R.id.rv_instruction)
    LRecyclerView rvInstruction;

    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private ExplainAdapter mAdapter;

    @Override
    protected ExplainPresenterImpl createPresenter() {
        return new ExplainPresenterImpl();
    }

    @Override
    public int returnLayoutID() {
        return R.layout.fragment_list;
    }

    @Override
    public void TODO(View view, Bundle savedInstanceState) {
        initRv();
    }

    private void initRv() {
        mAdapter = new ExplainAdapter(getActivity());
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mAdapter);
        rvInstruction.setAdapter(mLRecyclerViewAdapter);

        rvInstruction.setRefreshProgressStyle(ProgressStyle.TriangleSkewSpin);
        rvInstruction.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvInstruction.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getExplainList();
            }
        });
        rvInstruction.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                presenter.loadMore();
            }
        });
        mLRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String id = mAdapter.getDataList().get(position).getId();
                Intent intent = new Intent(getActivity(), ExplainCatalogActivity.class);
                intent.putExtra(KEY_INTENT, id);
                startActivity(intent);
            }
        });
        presenter.getExplainList();
    }


    @Override
    public void getExplainFail(String msg) {
        rvInstruction.refreshComplete(10);
        ToastUtils.show(getActivity(), msg);
    }

    @Override
    public void getExplainListSuccess(BasePage<Explain> data) {
        rvInstruction.refreshComplete(10);

        if (data.getCurrent() > 1) {
            mAdapter.addAll(data.getRecords());
        } else if (data.getCurrent() == 1) {
            mAdapter.setDataList(data.getRecords());
        }
    }

    @Override
    public void noMoreData() {
        rvInstruction.refreshComplete(10);
        ToastUtils.show(getActivity(), R.string.no_more_data);
    }
}
