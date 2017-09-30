package com.egr.drillinghelper.ui.activity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.contract.CreateFeedbackContract;
import com.egr.drillinghelper.presenter.CreateFeedbackPresenterImpl;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;
import com.egr.drillinghelper.ui.widgets.NumberLimitEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author lzd
 * date 2017/9/27 16:38
 * 类描述：
 */

public class CreateFeedbackActivity extends BaseMVPActivity<CreateFeedbackContract.View,
        CreateFeedbackPresenterImpl> implements CreateFeedbackContract.View {
    @BindView(R.id.et_create_feedback)
    NumberLimitEditText etCreateFeedback;
    @BindView(R.id.iv_add_photo)
    ImageView ivAddPhoto;
    @BindView(R.id.tv_commit)
    TextView tvCommit;

    @Override
    public int returnLayoutID() {
        return R.layout.activity_create_feedback;
    }

    @Override
    public void TODO(Bundle savedInstanceState) {
        setupActionBar(R.string.feedback, true);
        setActionbarBackground(R.color.white);
    }

    @Override
    public CreateFeedbackPresenterImpl createPresenter() {
        return new CreateFeedbackPresenterImpl();
    }

    @OnClick(R.id.tv_commit)
    public void onClick() {

    }
}
