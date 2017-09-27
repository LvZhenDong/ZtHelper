package com.egr.drillinghelper.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.bean.response.Instruction;
import com.egr.drillinghelper.ui.adapter.InstructionAdapter;
import com.egr.drillinghelper.ui.adapter.PartsAdapter;
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
 * date 2017/9/26 18:10
 * 类描述：
 */

public class PartsFragment extends BaseFragment{
    @BindView(R.id.rv_parts)
    RecyclerView rvParts;

    private PartsAdapter mAdapter;
    @Override
    public int returnLayoutID() {
        return R.layout.fragment_parts;
    }

    @Override
    public void TODO(View view, Bundle savedInstanceState) {
        initRv();
    }

    private void initRv(){
        mAdapter=new PartsAdapter(getActivity());
        rvParts.setAdapter(mAdapter);

        rvParts.setLayoutManager(new GridLayoutManager(getActivity(),2));

        List<Instruction> list=new ArrayList<>();
        Instruction mall=new Instruction();
        list.add(mall);
        Instruction item1=new Instruction();
        item1.setImgId(R.drawable.test);
        item1.setTitle("钻杆油");
        item1.setContent("英格尔钻具油脂");
        list.add(item1);
        Instruction item2=new Instruction();
        item2.setImgId(R.drawable.test);
        item2.setTitle("薄壁钻头");
        item2.setContent("英格尔薄壁钻头");
        list.add(item2);
        Instruction item3=new Instruction();
        item3.setImgId(R.drawable.test);
        item3.setTitle("钻具总成");
        item3.setContent("英格尔薄壁钻具系列钻");
        list.add(item3);
        Instruction item4=new Instruction();
        item4.setImgId(R.drawable.test);
        item4.setTitle("钻具总成");
        item4.setContent("英格尔薄壁钻具系列钻");
        list.add(item4);
        Instruction item5=new Instruction();
        item5.setImgId(R.drawable.test);
        item5.setTitle("钻具总成");
        item5.setContent("英格尔薄壁钻具系列钻");
        list.add(item5);
        Instruction item6=new Instruction();
        item6.setImgId(R.drawable.test);
        item6.setTitle("钻具总成");
        item6.setContent("英格尔薄壁钻具系列钻");
        list.add(item6);
        mAdapter.setDataList(list);
    }
}
