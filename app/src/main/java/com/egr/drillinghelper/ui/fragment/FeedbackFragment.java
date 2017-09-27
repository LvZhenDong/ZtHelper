package com.egr.drillinghelper.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.ui.adapter.QuestionAdapter;
import com.egr.drillinghelper.ui.base.BaseFragment;
import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author lzd
 * date 2017/9/26 18:10
 * 类描述：
 */

public class FeedbackFragment extends BaseFragment {
    @BindView(R.id.ll_go_feedback)
    LinearLayout llGoFeedback;
    @BindView(R.id.rv_question)
    LRecyclerView rvQuestion;

    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private QuestionAdapter mAdapter;


    @Override
    public int returnLayoutID() {
        return R.layout.fragment_feedback;
    }

    @Override
    public void TODO(View view, Bundle savedInstanceState) {
        initRv();
    }

    private void initRv(){
        mAdapter=new QuestionAdapter(getActivity());
        mLRecyclerViewAdapter=new LRecyclerViewAdapter(mAdapter);
        rvQuestion.setAdapter(mLRecyclerViewAdapter);
        DividerDecoration divider = new DividerDecoration.Builder(getActivity())
                .setHeight(R.dimen.line_height)
                .setPadding(R.dimen.padding_vertical)
                .setColorResource(R.color.bg_line)
                .build();
        rvQuestion.addItemDecoration(divider);
        rvQuestion.setRefreshProgressStyle(ProgressStyle.TriangleSkewSpin);
        rvQuestion.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvQuestion.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });
        rvQuestion.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {

            }
        });
        mLRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });

        List<String> list=new ArrayList<>();
        list.add("钻机在钻孔过程中为什么会偏孔？");
        list.add("如何使用密封件维修包？");
        list.add("如何更换发动机机油？");
        list.add("如果调节钻机的转速与扭力？");
        list.add("如何更换卡瓦？");
        list.add("钻探相关常规问题？");
        mAdapter.setDataList(list);
    }

    @OnClick(R.id.ll_go_feedback)
    public void onClick() {
        rvQuestion.refreshComplete(1);
    }
}
