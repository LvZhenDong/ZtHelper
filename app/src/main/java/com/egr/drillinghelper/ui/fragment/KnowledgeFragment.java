package com.egr.drillinghelper.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.bean.response.Instruction;
import com.egr.drillinghelper.ui.adapter.KnowledgeAdapter;
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
 * date 2017/9/27 10:19
 * 类描述：知识问答
 */

public class KnowledgeFragment extends BaseFragment {
    @BindView(R.id.rv_knowledge)
    LRecyclerView rvKnowledge;

    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private KnowledgeAdapter mAdapter;

    @Override
    public int returnLayoutID() {
        return R.layout.fragment_knowledge;
    }

    @Override
    public void TODO(View view, Bundle savedInstanceState) {
        initRv();
    }


    private void initRv(){
        mAdapter=new KnowledgeAdapter(getActivity());
        mLRecyclerViewAdapter=new LRecyclerViewAdapter(mAdapter);
        rvKnowledge.setAdapter(mLRecyclerViewAdapter);

        rvKnowledge.setRefreshProgressStyle(ProgressStyle.TriangleSkewSpin);
        rvKnowledge.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvKnowledge.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });
        rvKnowledge.setOnLoadMoreListener(new OnLoadMoreListener() {
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
        item1.setImgId(R.drawable.test);
        item1.setTitle("钻探设备知识问答");
        list.add(item1);
        Instruction item2=new Instruction();
        item2.setImgId(R.drawable.test);
        item2.setTitle("钻探技术知识问答");
        list.add(item2);
        Instruction item3=new Instruction();
        item3.setImgId(R.drawable.test);
        item3.setTitle("其他知识问答");
        list.add(item3);
        mAdapter.setDataList(list);
    }
}
