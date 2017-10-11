package com.egr.drillinghelper.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.bean.response.Feedback;
import com.egr.drillinghelper.ui.adapter.QuestionAdapter;
import com.egr.drillinghelper.ui.base.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * author lzd
 * date 2017/10/11 15:23
 * 类描述：
 */

public class CreateFeedbackSuccessActivity extends BaseActivity {
    ArrayList<Feedback> list;
    @BindView(R.id.tv_hint)
    TextView tvHint;
    @BindView(R.id.rv_feedback)
    RecyclerView rvFeedback;

    QuestionAdapter mAdapter;

    @Override
    public int returnLayoutID() {
        return R.layout.activity_create_feedback_success;
    }

    @Override
    public void TODO(Bundle savedInstanceState) {
        setupActionBar(R.string.feedback, true);
        setActionbarBackground(R.color.white);

        Bundle bundle = getIntent().getExtras();
        list = (ArrayList<Feedback>) bundle.getSerializable(KEY_INTENT);


        if (list != null && list.size() > 0) {
            mAdapter = new QuestionAdapter(this);
            rvFeedback.setLayoutManager(new LinearLayoutManager(this));
            rvFeedback.setAdapter(mAdapter);

            mAdapter.setDataList(list);
        }
    }

}
