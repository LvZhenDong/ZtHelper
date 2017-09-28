package com.egr.drillinghelper.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.bean.response.Instruction;
import com.egr.drillinghelper.bean.response.Store;
import com.egr.drillinghelper.contract.PartsContract;
import com.egr.drillinghelper.mvp.BaseMVPFragment;
import com.egr.drillinghelper.presenter.PartsPresenterImpl;
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

public class PartsFragment extends BaseMVPFragment<PartsContract.View,PartsPresenterImpl>
    implements PartsContract.View{
    @BindView(R.id.rv_parts)
    RecyclerView rvParts;

    private PartsAdapter mAdapter;

    @Override
    protected PartsPresenterImpl createPresenter() {
        return new PartsPresenterImpl();
    }

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

        presenter.getPartsList();
    }

    @Override
    public void getPartsListSuccess(List<Store> list) {
        mAdapter.setDataList(list);
    }
}
