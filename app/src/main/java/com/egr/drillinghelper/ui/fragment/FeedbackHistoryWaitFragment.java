package com.egr.drillinghelper.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.bean.response.Instruction;
import com.egr.drillinghelper.ui.adapter.InstructionAdapter;
import com.egr.drillinghelper.ui.adapter.WaitForReplyAdapter;
import com.egr.drillinghelper.ui.base.BaseFragment;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author lzd
 * date 2017/9/27 10:17
 * 类描述：历史反馈待回复
 */

public class FeedbackHistoryWaitFragment extends BaseFragment {
    @BindView(R.id.rv_instruction)
    LRecyclerView rvInstruction;

    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private WaitForReplyAdapter mAdapter;
    @Override
    public int returnLayoutID() {
        return R.layout.fragment_instructions;
    }

    @Override
    public void TODO(View view, Bundle savedInstanceState) {
        initRv();
    }

    private void initRv(){
        mAdapter=new WaitForReplyAdapter(getActivity());
        mLRecyclerViewAdapter=new LRecyclerViewAdapter(mAdapter);
        rvInstruction.setAdapter(mLRecyclerViewAdapter);

        rvInstruction.setRefreshProgressStyle(ProgressStyle.TriangleSkewSpin);
        rvInstruction.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvInstruction.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });
        rvInstruction.setOnLoadMoreListener(new OnLoadMoreListener() {
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
