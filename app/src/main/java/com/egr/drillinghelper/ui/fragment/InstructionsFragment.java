package com.egr.drillinghelper.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.bean.response.Instruction;
import com.egr.drillinghelper.ui.adapter.InstructionAdapter;
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

/**
 * author lzd
 * date 2017/9/27 10:17
 * 类描述：
 */

public class InstructionsFragment extends BaseFragment {
    @BindView(R.id.rv_instruction)
    LRecyclerView rvInstruction;

    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private InstructionAdapter mAdapter;
    @Override
    public int returnLayoutID() {
        return R.layout.fragment_instructions;
    }

    @Override
    public void TODO(View view, Bundle savedInstanceState) {
        initRv();
    }

    private void initRv(){
        mAdapter=new InstructionAdapter(getActivity());
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
        item1.setImgId(R.drawable.test);
        item1.setTitle("EP200使用说明");
        item1.setContent("英格尔最新研发的EP200便携式铝合金全液钻机，通过严格的现场测试，性能优越。");
        list.add(item1);
        Instruction item2=new Instruction();
        item2.setImgId(R.drawable.test);
        item2.setTitle("EP200使用说明");
        item2.setContent("英格尔最新研发的EP200便携式铝合金全液钻机，通过严格的现场测试，性能优越。");
        list.add(item2);
        Instruction item3=new Instruction();
        item3.setImgId(R.drawable.test);
        item3.setTitle("EP200使用说明");
        item3.setContent("英格尔最新研发的EP200便携式铝合金全液钻机，通过严格的现场测试，性能优越。");
        list.add(item3);
        mAdapter.setDataList(list);
    }
}
