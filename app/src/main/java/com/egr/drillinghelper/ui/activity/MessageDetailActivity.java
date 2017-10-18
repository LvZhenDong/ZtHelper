package com.egr.drillinghelper.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.bean.response.Message;
import com.egr.drillinghelper.common.RxBusConstant;
import com.egr.drillinghelper.contract.MessageDetailContract;
import com.egr.drillinghelper.presenter.MessageDetailPresenterImpl;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;
import com.egr.drillinghelper.ui.widgets.DialogHelper;
import com.egr.drillinghelper.utils.EgrRxBus;
import com.egr.drillinghelper.utils.ToastUtils;

import butterknife.BindView;
import cc.cloudist.acplibrary.ACProgressFlower;

/**
 * author lzd
 * date 2017/10/13 9:40
 * 类描述：
 */

public class MessageDetailActivity extends BaseMVPActivity<MessageDetailContract.View,
        MessageDetailPresenterImpl> implements MessageDetailContract.View {
    @BindView(R.id.tv_msg)
    TextView tvMsg;
    private ACProgressFlower mDialog;

    @Override
    public int returnLayoutID() {
        return R.layout.activity_message_detail;
    }

    @Override
    public void TODO(Bundle savedInstanceState) {
        setupActionBar(R.string.my_message, true);
        setActionbarBackground(R.color.white);

        Intent intent = getIntent();
        String id = intent.getStringExtra(KEY_INTENT);

        mDialog = DialogHelper.openiOSPbDialog(this, getString(R.string.waiting));
        mDialog.show();
        presenter.getMsgDetail(id);
    }

    @Override
    public MessageDetailPresenterImpl createPresenter() {
        return new MessageDetailPresenterImpl();
    }

    @Override
    public void getMsgSuccess(Message msg) {
        mDialog.dismiss();
        tvMsg.setText(msg.getMsg());
        setActionBarTitle(msg.getTitle());
        EgrRxBus.post(RxBusConstant.UPDATE_MSG);
    }

    @Override
    public void getMsgFail(String msg) {
        mDialog.dismiss();
        ToastUtils.show(this, msg);
    }

}
