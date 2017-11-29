package com.egr.drillinghelper.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.bean.base.BasePage;
import com.egr.drillinghelper.bean.response.Message;
import com.egr.drillinghelper.common.RxBusConstant;
import com.egr.drillinghelper.contract.MessageContract;
import com.egr.drillinghelper.presenter.MessagePresenterImpl;
import com.egr.drillinghelper.ui.adapter.MessageAdapter;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;
import com.egr.drillinghelper.utils.EgrRxBus;
import com.egr.drillinghelper.utils.ToastUtils;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;

import butterknife.BindView;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

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

    @Override
    public int returnLayoutID() {
        return R.layout.activity_message;
    }

    @Override
    public void TODO(Bundle savedInstanceState) {
        setUmengAnalyze(R.string.my_message);
        setupActionBar(R.string.my_message, true);
        setActionbarBackground(R.color.white);

        initRv();

        mDialog.show();
        presenter.getMsgList();
        EgrRxBus.subscribe(this, String.class, new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                if (s.equals(RxBusConstant.UPDATE_MSG))
                    presenter.getMsgList();
            }
        });
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
    public void deleteMsgSuccess() {
        mDialog.dismiss();
        if(deletePos != -1)
            mAdapter.remove(deletePos);
        EgrRxBus.post(RxBusConstant.UPDATE_MSG);
    }

    @Override
    public void deleteMsgFail(String msg) {
        deletePos=-1;
        mDialog.dismiss();
        ToastUtils.show(getActivity(), msg);
    }

    private int deletePos=-1;
    @Override
    public void onDelete(int position) {
        mDialog.show();
        deletePos=position;
        presenter.deleteMsg(mAdapter.getDataList().get(position).getId());
    }

    @Override
    public void onItemClick(int position) {
        Intent intent=new Intent(this,MessageDetailActivity.class);
        intent.putExtra(KEY_INTENT,mAdapter.getDataList().get(position).getId());
        startActivity(intent);
    }
}
