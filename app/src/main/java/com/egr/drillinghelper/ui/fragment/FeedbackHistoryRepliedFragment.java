package com.egr.drillinghelper.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.bean.response.Instruction;
import com.egr.drillinghelper.ui.adapter.WaitForReplyAdapter;
import com.egr.drillinghelper.ui.base.BaseFragment;
import com.egr.drillinghelper.ui.widgets.ReadReplyDialog;
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
 * 类描述：历史反馈已回复
 */

public class FeedbackHistoryRepliedFragment extends BaseFragment implements WaitForReplyAdapter.OnReplyClickListener {
    @BindView(R.id.rv_instruction)
    LRecyclerView rvInstruction;

    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private WaitForReplyAdapter mAdapter;
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
        item1.setContent("1支持Base64编码、本地图片和Assets目录图片,Copyright 2016 shaohui10086\n" +
                "\n" +
                "Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
                "you may not use this file except in compliance with the License.\n" +
                "You may obtain a copy of the License at\n" +
                "\n" +
                "   http://www.apache.org/licenses/LICENSE-2.0\n" +
                "\n" +
                "Unless required by applicable law or agreed to in writing, software\n" +
                "distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
                "WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
                "See the License for the specific language支持Base64编码、本地图片和Assets目录图片,Copyright 2016 shaohui10086\n" +
                "\" +\n" +
                "                \"\\n\" +\n" +
                "                \"Licensed under the Apache License, Version 2.0 (the \\\"License\\\");\\n\" +\n" +
                "\" +\n" +
                "                \"                \\\"you may not use this file except in compliance with the License.\\\\n\\\" +\\n\" +\n" +
                "                \"                \\\"You may obtain a copy of the License at\\\\n\\\" +\\n\" +\n" +
                "                \"                \\\"\\\\n\\\" +\\n\" +\n" +
                "                \"                \\\"   http://www.apache.org/licenses/LICENSE-2.0\\\\n\\\" +\\n\" +\n" +
                "                \"                \\\"\\\\n\\\" +\\n\" +\n" +
                "                \"                \\\"Unless required by applicable law or agreed to in writing, software\\\\n\\\" +\\n\" +\n" +
                "                \"                \\\"distributed under the License is distributed on an \\\\\\\"AS IS\\\\\\\" BASIS,\\\\n\\\" +\\n\" +\n" +
                "                \"                \\\"WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\\\\n\\\" +\\n\" +\n" +
                "                \"                \"See the License for the specific langunsed under the Apache License, Version 2.0 (the \\\"License\\\");\\n\" +\n" +
                "                \"you may not use this file except in compliance with the License.\\n\" +\n" +
                "                \"You may obtain a copy of the License at\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"   http://www.apache.org/licenses/LICENSE-2.0\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"Unless required by applicable law or agreed to in writing, software\\n\" +\n" +
                "                \"distributed under the License is distributed on an \\\"AS IS\\\" BASIS,\\n\" +\n" +
                "                \"WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\\n\" +\n" +
                "                \"See the License for the specific language\"");
        list.add(item1);

        Instruction item2=new Instruction();
        item2.setContent("2");
        list.add(item2);
        Instruction item3=new Instruction();
        item3.setContent("3");
        list.add(item3);
        mAdapter.setDataList(list);
    }

    @Override
    public void onReplyClick(String reply) {
        ReadReplyDialog replyDialog=new ReadReplyDialog();
        replyDialog.showReply(getFragmentManager(),reply);
    }
}
