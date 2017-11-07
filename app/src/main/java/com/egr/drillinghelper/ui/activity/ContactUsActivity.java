package com.egr.drillinghelper.ui.activity;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.bean.response.ContactUs;
import com.egr.drillinghelper.contract.ContactUsContract;
import com.egr.drillinghelper.presenter.ContactUsPresenterImpl;
import com.egr.drillinghelper.ui.adapter.ContactUsAdapter;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;
import com.egr.drillinghelper.ui.widgets.DialogHelper;
import com.egr.drillinghelper.ui.widgets.RvInScrollView;
import com.egr.drillinghelper.utils.GlideUtils;
import com.egr.drillinghelper.utils.PhoneUtils;

import butterknife.BindView;
import cc.cloudist.acplibrary.ACProgressFlower;

/**
 * author lzd
 * date 2017/9/28 17:08
 * 类描述：联系我们
 */

public class ContactUsActivity extends BaseMVPActivity<ContactUsContract.View,
        ContactUsPresenterImpl> implements ContactUsContract.View {
    @BindView(R.id.tv_service_phone)
    TextView tvServicePhone;
    @BindView(R.id.tv_sales_phone)
    TextView tvSalesPhone;
    @BindView(R.id.rv_contact)
    RvInScrollView rvMessage;
    ContactUsAdapter mAdapter;
    ContactUs mContactUs;
    @BindView(R.id.ll_service)
    LinearLayout llService;
    @BindView(R.id.ll_sales)
    LinearLayout llSales;
    @BindView(R.id.iv_qr)
    ImageView ivQr;
    @BindView(R.id.sv)
    ScrollView mSv;
    private ACProgressFlower mDialog;
    String phone;
    private View.OnClickListener onPhoneClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ll_sales:
                    phone = mContactUs.getAboutUs().getSalesTel();
                    break;
                case R.id.ll_service:
                    phone = mContactUs.getAboutUs().getServiceTel();
                    break;
            }
            PhoneUtils.callPhone(ContactUsActivity.this, phone);
        }
    };

    @Override
    public int returnLayoutID() {
        return R.layout.activity_contact_us;
    }

    @Override
    public void TODO(Bundle savedInstanceState) {
        setUmengAnalyze(R.string.contact_us);
        setupActionBar(R.string.contact_us, true);
        setActionbarBackground(R.color.white);

        mDialog = DialogHelper.openiOSPbDialog(this, getString(R.string.waiting));
        initRv();
    }

    private void initRv() {
        mAdapter = new ContactUsAdapter(getActivity());
        rvMessage.setNestedScrollingEnabled(false);
        rvMessage.setLayoutManager(new LinearLayoutManager(this));
        rvMessage.setAdapter(mAdapter);

        if (!mDialog.isShowing())
            mDialog.show();
        presenter.getContactList();
    }

    @Override
    public ContactUsPresenterImpl createPresenter() {
        return new ContactUsPresenterImpl();
    }

    @Override
    public void getListFail(String msg) {
        mDialog.dismiss();
    }

    @Override
    public void getListSuccess(ContactUs contactUs) {
        mDialog.dismiss();
        if (contactUs != null) {
            mSv.setVisibility(View.VISIBLE);
            mContactUs = contactUs;

            mAdapter.setDataList(contactUs.getContactList());
            if (contactUs.getAboutUs() != null) {
                tvSalesPhone.setText(contactUs.getAboutUs().getSalesTel());
                tvServicePhone.setText(contactUs.getAboutUs().getServiceTel());

                llSales.setOnClickListener(onPhoneClickListener);
                llService.setOnClickListener(onPhoneClickListener);

                GlideUtils.load(contactUs.getAboutUs().getQrcode(),ivQr);
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PhoneUtils.MY_PERMISSIONS_REQUEST_CALL_PHONE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the phone call
                    PhoneUtils.startPhoneIntent(this, phone);
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                break;
        }
    }
}
