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

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

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
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.et_search)
    EditText etSearch;
    String keyword;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private ExplainAdapter mAdapter;

    @Override
    protected ExplainPresenterImpl createPresenter() {
        return new ExplainPresenterImpl();
    }

    @Override
    public int returnLayoutID() {
        return R.layout.fragment_explain;
    }

    @Override
    public void TODO(View view, Bundle savedInstanceState) {
        initSearchEt();
        initRv();
        presenter.getExplainList("");
        presenter.getExplainCache();
    }

    private void initRv() {
        mAdapter = new ExplainAdapter(getActivity());
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mAdapter);
        rvInstruction.setAdapter(mLRecyclerViewAdapter);

        rvInstruction.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        rvInstruction.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvInstruction.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getExplainList(keyword);
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

    }

    private void initSearchEt() {
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEND || actionId == EditorInfo.IME_ACTION_DONE
                        || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                    //处理事件
                    keyword = etSearch.getText().toString().trim();
                    presenter.getExplainList(keyword);
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
                presenter.getExplainList(keyword);
                break;
        }
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

    @Override
    public void showExplainCache(List<Explain> explains) {
        rvInstruction.refreshComplete(10);

        mAdapter.setDataList(explains);
    }
}
