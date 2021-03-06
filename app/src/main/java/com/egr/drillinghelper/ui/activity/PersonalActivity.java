package com.egr.drillinghelper.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.bean.response.UserInfo;
import com.egr.drillinghelper.common.UserManager;
import com.egr.drillinghelper.contract.PersonalContract;
import com.egr.drillinghelper.presenter.PersonalPresenterImpl;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;
import com.egr.drillinghelper.utils.GlideUtils;
import com.egr.drillinghelper.utils.ToastUtils;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author lzd
 * date 2017/9/29 16:41
 * 类描述：个人信息
 */

public class PersonalActivity extends BaseMVPActivity<PersonalContract.View,
        PersonalPresenterImpl> implements PersonalContract.View {
    public final static int IMAGE_PICKER = 22;
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.rl_head)
    RelativeLayout rlHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_company)
    TextView tvCompany;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_complete)
    TextView tvComplete;
    UserInfo mUserInfo;
    String mHeadPath;

    @Override
    public int returnLayoutID() {
        return R.layout.activity_personal;
    }

    @Override
    public void TODO(Bundle savedInstanceState) {
        setUmengAnalyze(R.string.personal);
        setupActionBar(R.string.personal, true);
        setActionbarBackground(R.color.white);

        mUserInfo = UserManager.getInstance().getUserInfo();
        if (mUserInfo != null) {
            tvName.setText(mUserInfo.getName());
            tvCompany.setText(mUserInfo.getCompany());
            tvPhone.setText(mUserInfo.getPhone());
            GlideUtils.loadCircleImg(mUserInfo.getPhoto(),ivHead);
        }

    }

    @Override
    public PersonalPresenterImpl createPresenter() {
        return new PersonalPresenterImpl();
    }

    @OnClick({R.id.rl_head, R.id.tv_complete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_head:
                ImagePicker imagePicker=ImagePicker.getInstance();
                imagePicker.setMultiMode(false);
                imagePicker.setCrop(true);
                imagePicker.setStyle(CropImageView.Style.CIRCLE);
                Intent intent = new Intent(getActivity(), ImageGridActivity.class);
                startActivityForResult(intent, IMAGE_PICKER);
                break;
            case R.id.tv_complete:

                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == IMAGE_PICKER) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra
                        (ImagePicker.EXTRA_RESULT_ITEMS);
                mHeadPath = images.get(0).path;
                GlideUtils.loadCircleImg(mHeadPath,ivHead);
                commit();
            }
        }
    }

    private void commit(){
        if (TextUtils.isEmpty(mHeadPath))
            return;
        if(!mDialog.isShowing())
            mDialog.show();
        presenter.changeHead(mHeadPath);
    }

    @Override
    public void changeHeadSuccess() {
        mDialog.dismiss();
        ToastUtils.show(this,R.string.change_success);
    }

    @Override
    public void changeHeadFail(String msg) {
        mDialog.dismiss();
        ToastUtils.show(this,msg);
    }
}
