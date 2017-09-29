package com.egr.drillinghelper.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.egr.drillinghelper.R;
import com.egr.drillinghelper.bean.response.UserInfo;
import com.egr.drillinghelper.contract.PersonalContract;
import com.egr.drillinghelper.presenter.PersonalPresenterImpl;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;
import com.egr.drillinghelper.ui.widgets.DialogHelper;
import com.egr.drillinghelper.utils.ToastUtils;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import cc.cloudist.acplibrary.ACProgressFlower;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * author lzd
 * date 2017/9/29 16:41
 * 类描述：
 */

public class PersonalActivity extends BaseMVPActivity<PersonalContract.View,
        PersonalPresenterImpl> implements PersonalContract.View {
    public final static int IMAGE_PICKER = 22;
    @BindView(R.id.iv_head)
    CircleImageView ivHead;
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
    private ACProgressFlower mDialog;

    @Override
    public int returnLayoutID() {
        return R.layout.activity_personal;
    }

    @Override
    public void TODO(Bundle savedInstanceState) {
        setupActionBar(R.string.personal, true);
        setActionbarBackground(R.color.white);

        mUserInfo = (UserInfo) getIntent().getSerializableExtra(KEY_INTENT);
        if (mUserInfo != null) {
            tvName.setText(mUserInfo.getName());
            tvCompany.setText(mUserInfo.getCompany());
            tvPhone.setText(mUserInfo.getPhone());
            Glide.with(getActivity()).load(mUserInfo.getPhoto()).centerCrop().into(ivHead);
        }

        mDialog = DialogHelper.openiOSPbDialog(this, getString(R.string.waiting));
    }

    @Override
    public PersonalPresenterImpl createPresenter() {
        return new PersonalPresenterImpl();
    }

    @OnClick({R.id.rl_head, R.id.tv_complete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_head:
                Intent intent = new Intent(getActivity(), ImageGridActivity.class);
                startActivityForResult(intent, IMAGE_PICKER);
                break;
            case R.id.tv_complete:
                if (TextUtils.isEmpty(mHeadPath))
                    return;
                if(!mDialog.isShowing())
                    mDialog.show();
                presenter.changeHead(mHeadPath);
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
                Glide.with(this).
                        load(mHeadPath)
                        .centerCrop().into(ivHead);
            }
        }
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
