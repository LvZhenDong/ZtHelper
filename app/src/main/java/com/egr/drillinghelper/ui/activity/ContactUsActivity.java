package com.egr.drillinghelper.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.bean.response.ContactUs;
import com.egr.drillinghelper.contract.ContactUsContract;
import com.egr.drillinghelper.presenter.ContactUsPresenterImpl;
import com.egr.drillinghelper.ui.adapter.ContactUsAdapter;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;
import com.egr.drillinghelper.ui.widgets.DialogHelper;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import cc.cloudist.acplibrary.ACProgressFlower;

/**
 * author lzd
 * date 2017/9/28 17:08
 * 类描述：
 */

public class ContactUsActivity extends BaseMVPActivity<ContactUsContract.View,
        ContactUsPresenterImpl> implements ContactUsContract.View {
    @BindView(R.id.tv_service_phone)
    TextView tvServicePhone;
    @BindView(R.id.tv_sales_phone)
    TextView tvSalesPhone;
    private ACProgressFlower mDialog;

    @BindView(R.id.rv_contact)
    LRecyclerView rvMessage;
    ContactUsAdapter mAdapter;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;

    @Override
    public int returnLayoutID() {
        return R.layout.activity_contact_us;
    }

    @Override
    public void TODO(Bundle savedInstanceState) {
        setupActionBar(R.string.contact_us, true);
        setActionbarBackground(R.color.white);

        mDialog = DialogHelper.openiOSPbDialog(this, getString(R.string.waiting));
        initRv();
    }

    private void initRv() {
        mAdapter = new ContactUsAdapter(getActivity());
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mAdapter);
        rvMessage.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvMessage.setAdapter(mLRecyclerViewAdapter);
        rvMessage.setLoadMoreEnabled(false);
        rvMessage.setPullRefreshEnabled(false);
        mLRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });

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
        mAdapter.setDataList(contactUs.getContactList());
        tvSalesPhone.setText(contactUs.getAboutUs().getSalesTel());
        tvServicePhone.setText(contactUs.getAboutUs().getServiceTel());
    }

}
