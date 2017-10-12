package com.egr.drillinghelper.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.bean.base.BasePage;
import com.egr.drillinghelper.bean.response.Instruction;
import com.egr.drillinghelper.bean.response.Message;
import com.egr.drillinghelper.contract.MessageContract;
import com.egr.drillinghelper.presenter.MessagePresenterImpl;
import com.egr.drillinghelper.ui.adapter.MessageAdapter;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;
import com.egr.drillinghelper.ui.widgets.DialogHelper;
import com.egr.drillinghelper.utils.ToastUtils;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cc.cloudist.acplibrary.ACProgressFlower;

/**
 * author lzd
 * date 2017/9/27 18:13
 * 类描述：消息
 */

public class MessageActivity extends BaseMVPActivity<MessageContract.View,
        MessagePresenterImpl> implements MessageContract.View, MessageAdapter.SwipeListener {


    @BindView(R.id.rv_message)
    LRecyclerView rvMessage;

    MessageAdapter mAdapter;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private ACProgressFlower mDialog;


    @Override
    public int returnLayoutID() {
        return R.layout.activity_message;
    }

    @Override
    public void TODO(Bundle savedInstanceState) {
        setupActionBar(R.string.my_message, true);
        setActionbarBackground(R.color.white);

        mDialog = DialogHelper.openiOSPbDialog(this, getString(R.string.waiting));
        initRv();

        mDialog.show();
        presenter.getMsgList();
    }

    @Override
    public MessagePresenterImpl createPresenter() {
        return new MessagePresenterImpl();
    }

    private void initRv(){
        mAdapter=new MessageAdapter(getActivity());
        mLRecyclerViewAdapter=new LRecyclerViewAdapter(mAdapter);
        rvMessage.setAdapter(mLRecyclerViewAdapter);

        rvMessage.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        rvMessage.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvMessage.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getMsgList();
            }
        });
        rvMessage.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                presenter.loadMore();
            }
        });

        mAdapter.setListener(this);
    }

    @Override
    public void getMsgListSuccess(BasePage<Message> data) {
        mDialog.dismiss();
        rvMessage.refreshComplete(10);

        if (data.getCurrent() > 1) {
            mAdapter.addAll(data.getRecords());
        } else if (data.getCurrent() == 1) {
            mAdapter.setDataList(data.getRecords());
        }
    }

    @Override
    public void getMsgListFail(String msg) {
        mDialog.dismiss();
        rvMessage.refreshComplete(10);
        ToastUtils.show(getActivity(), msg);
    }

    @Override
    public void noMoreData() {
        mDialog.dismiss();
        rvMessage.refreshComplete(10);
        ToastUtils.show(this, R.string.no_more_data);
    }

    @Override
    public void onDelete(int position) {
        mAdapter.remove(position);
    }

    @Override
    public void onItemClick(int position) {

    }
}
