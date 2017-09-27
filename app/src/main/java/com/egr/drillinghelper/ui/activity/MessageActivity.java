package com.egr.drillinghelper.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.bean.response.Instruction;
import com.egr.drillinghelper.contract.MessageContract;
import com.egr.drillinghelper.presenter.MessagePresenterImpl;
import com.egr.drillinghelper.ui.adapter.InstructionAdapter;
import com.egr.drillinghelper.ui.adapter.MessageAdapter;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;
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

/**
 * author lzd
 * date 2017/9/27 18:13
 * 类描述：消息
 */

public class MessageActivity extends BaseMVPActivity<MessageContract.View,
        MessagePresenterImpl> implements MessageContract.View {


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
        setupActionBar(R.string.my_message, true);
        setActionbarBackground(R.color.white);

        initRv();
    }

    @Override
    public MessagePresenterImpl createPresenter() {
        return new MessagePresenterImpl();
    }

    private void initRv(){
        mAdapter=new MessageAdapter(getActivity());
        mLRecyclerViewAdapter=new LRecyclerViewAdapter(mAdapter);
        rvMessage.setAdapter(mLRecyclerViewAdapter);

        rvMessage.setRefreshProgressStyle(ProgressStyle.TriangleSkewSpin);
        rvMessage.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvMessage.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });
        rvMessage.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {

            }
        });
        mLRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });

        List<Instruction> list=new ArrayList<>();
        Instruction item1=new Instruction();
        list.add(item1);
        Instruction item2=new Instruction();
        list.add(item2);
        Instruction item3=new Instruction();
        list.add(item3);
        mAdapter.setDataList(list);
    }

}
