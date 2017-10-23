package com.egr.drillinghelper.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.bean.base.BasePage;
import com.egr.drillinghelper.bean.response.Explain;
import com.egr.drillinghelper.bean.rxbus.SearchKey;
import com.egr.drillinghelper.common.MyConstants;
import com.egr.drillinghelper.contract.KnowContract;
import com.egr.drillinghelper.mvp.BaseMVPFragment;
import com.egr.drillinghelper.presenter.KnowPresenterImpl;
import com.egr.drillinghelper.ui.activity.KnowCatalogActivity;
import com.egr.drillinghelper.ui.adapter.KnowledgeAdapter;
import com.egr.drillinghelper.utils.EgrRxBus;
import com.egr.drillinghelper.utils.ToastUtils;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;

import java.util.List;

import butterknife.BindView;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

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

        rvKnowledge.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
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

                String id = mAdapter.getDataList().get(position).getId();
                Intent intent = new Intent(getActivity(), KnowCatalogActivity.class);
                intent.putExtra(KEY_INTENT, id);
                startActivity(intent);
            }
        });
        EgrRxBus.subscribe(this, SearchKey.class, new Consumer<SearchKey>() {
            @Override
            public void accept(@NonNull SearchKey searchKey) throws Exception {
                if(searchKey.getType() == MyConstants.SEARCH_TYPE_KNOWLEDGE){

                }
            }
        });
        presenter.getKnowList();
        presenter.getKnowCache();
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
