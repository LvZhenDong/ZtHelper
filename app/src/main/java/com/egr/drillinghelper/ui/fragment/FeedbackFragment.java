package com.egr.drillinghelper.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.bean.response.Feedback;
import com.egr.drillinghelper.contract.FeedbackContract;
import com.egr.drillinghelper.mvp.BaseMVPFragment;
import com.egr.drillinghelper.presenter.FeedbackPresenterImpl;
import com.egr.drillinghelper.ui.activity.ServiceActivity;
import com.egr.drillinghelper.ui.adapter.QuestionAdapter;
import com.egr.drillinghelper.utils.ToastUtils;

import java.util.List;

import butterknife.BindView;
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
    RecyclerView rvQuestion;

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
        setUmengAnalyze(R.string.home_service);
        initRv();
    }

    @Override
    public void onVisibleToUserChanged(boolean isVisibleToUser, boolean invokeInResumeOrPause) {
        super.onVisibleToUserChanged(isVisibleToUser, invokeInResumeOrPause);
        if (isFirstVisiableToUser && isVisibleToUser) {
            presenter.getFeedbackList();
            isFirstVisiableToUser = false;
        }
    }

    private void initRv(){
        mAdapter=new QuestionAdapter(getActivity());
        rvQuestion.setAdapter(mAdapter);
        rvQuestion.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @OnClick(R.id.ll_go_feedback)
    public void onClick() {
        baseStartActivity(ServiceActivity.class);
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
