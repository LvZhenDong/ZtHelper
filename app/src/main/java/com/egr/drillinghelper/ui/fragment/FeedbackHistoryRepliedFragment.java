package com.egr.drillinghelper.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.bean.base.BasePage;
import com.egr.drillinghelper.bean.response.Reply;
import com.egr.drillinghelper.contract.ReplyContract;
import com.egr.drillinghelper.mvp.BaseMVPFragment;
import com.egr.drillinghelper.presenter.ReplyPresenterImpl;
import com.egr.drillinghelper.ui.adapter.WaitForReplyAdapter;
import com.egr.drillinghelper.ui.widgets.ReadReplyDialog;
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
 * 类描述：历史反馈已回复
 */

public class FeedbackHistoryRepliedFragment extends BaseMVPFragment<ReplyContract.View,
        ReplyPresenterImpl> implements ReplyContract.View,
        WaitForReplyAdapter.OnReplyClickListener {
    @BindView(R.id.rv_instruction)
    LRecyclerView rvInstruction;

    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private WaitForReplyAdapter mAdapter;

    @Override
    protected ReplyPresenterImpl createPresenter() {
        return new ReplyPresenterImpl();
    }

    @Override
    public int returnLayoutID() {
        return R.layout.fragment_list;
    }

    @Override
    public void TODO(View view, Bundle savedInstanceState) {
        initRv();
    }

    private void initRv(){
        mAdapter=new WaitForReplyAdapter(getActivity());
        mLRecyclerViewAdapter=new LRecyclerViewAdapter(mAdapter);
        mAdapter.setOnReplyClickListener(this);
        rvInstruction.setAdapter(mLRecyclerViewAdapter);

        rvInstruction.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        rvInstruction.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvInstruction.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getReply("1");
            }
        });
        rvInstruction.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                presenter.loadMore("1");
            }
        });
        mLRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });

        presenter.getReply("1");
    }

    @Override
    public void onReplyClick(String reply) {
        ReadReplyDialog replyDialog=new ReadReplyDialog();
        replyDialog.showReply(getFragmentManager(),reply);
    }

    @Override
    public void getReplyFail(String msg) {
        rvInstruction.refreshComplete(10);
        ToastUtils.show(getActivity(), msg);
    }

    @Override
    public void getReplySuccess(BasePage<Reply> reply) {
        rvInstruction.refreshComplete(10);

        if (reply.getCurrent() > 1) {
            mAdapter.addAll(reply.getRecords());
        } else if (reply.getCurrent() == 1) {
            mAdapter.setDataList(reply.getRecords());
        }
    }

    @Override
    public void noMoreData() {
        rvInstruction.refreshComplete(10);
        ToastUtils.show(getActivity(), R.string.no_more_data);
    }
}
