package com.egr.drillinghelper.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.bean.response.Feedback;
import com.egr.drillinghelper.contract.FeedbackContract;
import com.egr.drillinghelper.mvp.BaseMVPFragment;
import com.egr.drillinghelper.presenter.FeedbackPresenterImpl;
import com.egr.drillinghelper.ui.activity.CreateFeedbackActivity;
import com.egr.drillinghelper.ui.adapter.ExplainAdapter;
import com.egr.drillinghelper.ui.adapter.QuestionAdapter;
import com.egr.drillinghelper.ui.base.BaseFragment;
import com.egr.drillinghelper.utils.ToastUtils;
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
 * 类描述：信息反馈
 */

public class FeedbackFragment extends BaseMVPFragment<FeedbackContract.View,
        FeedbackPresenterImpl> implements FeedbackContract.View{
    @BindView(R.id.ll_go_feedback)
    LinearLayout llGoFeedback;
    @BindView(R.id.rv_question)
    LRecyclerView rvQuestion;

    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private QuestionAdapter mAdapter;

    @Override
    protected FeedbackPresenterImpl createPresenter() {
        return new FeedbackPresenterImpl();
    }

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
        rvQuestion.setLoadMoreEnabled(false);
        rvQuestion.setPullRefreshEnabled(false);
        mLRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });

        presenter.getFeedbackList();
    }

    @OnClick(R.id.ll_go_feedback)
    public void onClick() {
        baseStartActivity(CreateFeedbackActivity.class);
    }

    @Override
    public void getFeedbackListSuccess(List<Feedback> list) {
        mAdapter.setDataList(list);
    }

    @Override
    public void getFeedbackFail(String msg) {
        ToastUtils.show(getActivity(),msg);
    }
}
