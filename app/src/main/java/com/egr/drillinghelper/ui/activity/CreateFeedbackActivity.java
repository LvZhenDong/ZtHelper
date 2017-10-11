package com.egr.drillinghelper.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.contract.CreateFeedbackContract;
import com.egr.drillinghelper.presenter.CreateFeedbackPresenterImpl;
import com.egr.drillinghelper.ui.adapter.CreateFeedbackImgAdapter;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;
import com.egr.drillinghelper.ui.widgets.DialogHelper;
import com.egr.drillinghelper.ui.widgets.NumberLimitEditText;
import com.egr.drillinghelper.utils.ToastUtils;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cc.cloudist.acplibrary.ACProgressFlower;

import static com.lzy.imagepicker.ui.ImageGridActivity.EXTRAS_IMAGES;

/**
 * author lzd
 * date 2017/9/27 16:38
 * 类描述：
 */

public class CreateFeedbackActivity extends BaseMVPActivity<CreateFeedbackContract.View,
        CreateFeedbackPresenterImpl> implements CreateFeedbackContract.View, CreateFeedbackImgAdapter.CallBack {
    public final static int IMAGE_PICKER = 22;

    @BindView(R.id.et_create_feedback)
    NumberLimitEditText etCreateFeedback;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    @BindView(R.id.rv_img)
    RecyclerView rvImg;

    CreateFeedbackImgAdapter mAdapter;
    private ACProgressFlower mDialog;

    @Override
    public int returnLayoutID() {
        return R.layout.activity_create_feedback;
    }

    @Override
    public void TODO(Bundle savedInstanceState) {
        setupActionBar(R.string.feedback, true);
        setActionbarBackground(R.color.white);

        mDialog = DialogHelper.openiOSPbDialog(this, getString(R.string.commiting));

        mAdapter = new CreateFeedbackImgAdapter(this);
        rvImg.setLayoutManager(new GridLayoutManager(this, 4));
        rvImg.setAdapter(mAdapter);
        mAdapter.setDataList(null);
        mAdapter.setListener(this);

    }

    @Override
    public CreateFeedbackPresenterImpl createPresenter() {
        return new CreateFeedbackPresenterImpl();
    }

    @OnClick(R.id.tv_commit)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_commit:
                onCommit();
                break;
        }
    }

    private void onCommit(){
        String question=etCreateFeedback.getText().toString().trim();
        if(TextUtils.isEmpty(question)){
            ToastUtils.show(this,R.string.create_input_empty);
        }else {
            mDialog.show();
            presenter.createFeedback(question,images);
        }
    }

    ArrayList<ImageItem> images;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == IMAGE_PICKER) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra
                        (ImagePicker.EXTRA_RESULT_ITEMS);
                List<String> mImgList = new ArrayList<>();
                for (ImageItem item : images) {
                    mImgList.add(item.path);
                }
                mAdapter.setDataList(mImgList);
            }
        }
    }

    @Override
    public void onAddClick() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setSelectLimit(CreateFeedbackImgAdapter.IMG_NUMBER);
        Intent intent = new Intent(getActivity(), ImageGridActivity.class);
        intent.putExtra(EXTRAS_IMAGES,images);
        startActivityForResult(intent, IMAGE_PICKER);
    }

    @Override
    public void removeItem(int position) {
        if(images != null)
            images.remove(position);
    }

    @Override
    public void commitFail(String msg) {
        mDialog.dismiss();
        ToastUtils.show(this,msg);
    }

    @Override
    public void commitSuccess() {
        mDialog.dismiss();
        ToastUtils.show(this,R.string.commit_success);
        finish();
    }
}
