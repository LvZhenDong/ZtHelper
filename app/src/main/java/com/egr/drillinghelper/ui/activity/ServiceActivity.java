package com.egr.drillinghelper.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.bean.base.BasePage;
import com.egr.drillinghelper.bean.response.Message;
import com.egr.drillinghelper.bean.response.ServiceMsg;
import com.egr.drillinghelper.contract.ServiceContract;
import com.egr.drillinghelper.presenter.ServicePresenterImpl;
import com.egr.drillinghelper.ui.adapter.ServiceAdapter;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;
import com.egr.drillinghelper.ui.widgets.DialogHelper;
import com.egr.drillinghelper.utils.CollectionUtil;
import com.egr.drillinghelper.utils.EgrRxBus;
import com.egr.drillinghelper.utils.TimeUtils;
import com.egr.drillinghelper.utils.ToastUtils;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;
import cc.cloudist.acplibrary.ACProgressFlower;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

import static com.egr.drillinghelper.R.id.tv_add_img;
import static com.egr.drillinghelper.utils.TimeUtils.getCurrentTime;

/**
 * author lzd
 * date 2017/10/24 10:46
 * 类描述：
 */

public class ServiceActivity extends BaseMVPActivity<ServiceContract.View,
        ServicePresenterImpl> implements ServiceContract.View {

    public final static int IMAGE_PICKER = 24;
    @BindView(R.id.tv_send)
    TextView tvSend;
    @BindView(tv_add_img)
    TextView tvAddImg;
    @BindView(R.id.et_msg)
    EditText etMsg;
    @BindView(R.id.rl_send_msg)
    RelativeLayout rlSendMsg;
    @BindView(R.id.rv_service_msg)
    LRecyclerView rvMsg;
    @BindView(R.id.ll_add_img)
    LinearLayout llAddImg;
    ServiceAdapter mAdapter;
    LinearLayoutManager linearLayoutManager;
    private ACProgressFlower mDialog;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;

    @Override
    public ServicePresenterImpl createPresenter() {
        return new ServicePresenterImpl();
    }

    @Override
    public int returnLayoutID() {
        return R.layout.activity_service;
    }

    @Override
    public void TODO(Bundle savedInstanceState) {
        setupActionBar(R.string.go_feedback, true);
        setActionbarBackground(R.color.white);

        mDialog = DialogHelper.openiOSPbDialog(this, getString(R.string.waiting));

        mAdapter = new ServiceAdapter(this);
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mAdapter);
        rvMsg.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        linearLayoutManager = new LinearLayoutManager(this);
        rvMsg.setLayoutManager(linearLayoutManager);
        rvMsg.setAdapter(mLRecyclerViewAdapter);
        rvMsg.setLoadMoreEnabled(false);
        rvMsg.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadMore();
            }
        });
        mLRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ServiceMsg item=mAdapter.getDataList().get(position);
                if(!CollectionUtil.isListEmpty(item.getPictureList())){
                    GalleryActivity.start(getActivity(), (ArrayList<String>) item.getPictureList());
                }
            }
        });

        initEt();

        //接收到消息
        EgrRxBus.subscribe(this, Message.class, new Consumer<Message>() {
            @Override
            public void accept(@NonNull Message message) throws Exception {
//                mAdapter.add(ServiceMsg.createRecText(message));
//                rvMsg.smoothScrollToPosition(mAdapter.getDataList().size());
                presenter.getLatestMsg();
            }
        });

        mDialog.show();
        presenter.getMsg();

        mAdapter.setReSendListener(new ServiceAdapter.ReSendListener() {
            @Override
            public void onReSend(int pos) {
                ServiceMsg serviceMsg=mAdapter.getDataList().get(pos);
                presenter.sendMsg(serviceMsg.getMsg());
                serviceMsg.setSendState(2);
                mAdapter.notifyItemChanged(pos);
            }
        });
    }

    private void initEt() {
        etMsg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {   //显示发送按钮
                    tvAddImg.setVisibility(View.INVISIBLE);
                    tvSend.setVisibility(View.VISIBLE);
                } else {    //显示发送图片按钮
                    tvAddImg.setVisibility(View.VISIBLE);
                    tvSend.setVisibility(View.INVISIBLE);
                }
            }
        });
        InputFilter emojiFilter = new InputFilter() {

            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest,
                                       int dstart, int dend) {
                Pattern emoji = Pattern.compile(
                        "[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
                        Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
                Matcher emojiMatcher = emoji.matcher(source);
                if (emojiMatcher.find()) {
                    return "";
                }
                return null;
            }
        };
        etMsg.setFilters(new InputFilter[]{emojiFilter});
        etMsg.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    llAddImg.setVisibility(View.GONE);
                }
            }
        });
    }

    @OnClick({R.id.tv_send, R.id.tv_add_img, R.id.ll_add_img})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_send:
                String msg = etMsg.getText().toString().trim();
                if(TextUtils.isEmpty(msg))return;
                etMsg.setText("");
                mAdapter.add(ServiceMsg.createSendText(TimeUtils.getCurrentTime(),msg));
                rvMsg.smoothScrollToPosition(mAdapter.getDataList().size());
                presenter.sendMsg(msg);
                break;
            case R.id.tv_add_img:
                //收起keyboard
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(etMsg.getWindowToken(), 0);
//                llAddImg.setVisibility(View.VISIBLE);
                etMsg.clearFocus();
                Intent imgIntent = new Intent(getActivity(), ImageGridActivity.class);
                startActivityForResult(imgIntent, IMAGE_PICKER);
                break;
            case R.id.ll_add_img:
                llAddImg.setVisibility(View.GONE);
                Intent intent = new Intent(getActivity(), ImageGridActivity.class);
                startActivityForResult(intent, IMAGE_PICKER);
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
                String imgStr = images.get(0).path;
                mAdapter.add(ServiceMsg.createSendImg(getCurrentTime(),imgStr));
                presenter.sendPhoto(imgStr);
                rvMsg.smoothScrollToPosition(mAdapter.getDataList().size());
            }
        }
    }

    @Override
    public void sendMsgSuc() {
        // TODO 更新item
        mAdapter.getLastItem().setSendState(0);
        mAdapter.notifyItemChanged(mAdapter.getDataList().size()-1);
    }

    @Override
    public void sendMsgFail() {
        //TODO
        mAdapter.getLastItem().setSendState(1);
        mAdapter.notifyItemChanged(mAdapter.getDataList().size()-1);
    }

    @Override
    public void getMsgSuc(BasePage<ServiceMsg> data) {
        mDialog.dismiss();
        rvMsg.refreshComplete(20);
        if (data.getCurrent() > 1) {    //加载更早的历史消息
            mAdapter.addAll(0, data.getRecords());
            linearLayoutManager.scrollToPositionWithOffset(data.getRecords().size(), 0);
        } else if (data.getCurrent() == 1) {    //加载消息list
            mAdapter.setDataList(data.getRecords());
            rvMsg.scrollToPosition(mAdapter.getDataList().size());
        }
    }

    @Override
    public void getMsgFail(String msg) {
        mDialog.dismiss();
        rvMsg.refreshComplete(20);
        ToastUtils.show(this, msg);
    }

    @Override
    public void noMoreData() {
        mDialog.dismiss();
        rvMsg.refreshComplete(20);
        ToastUtils.show(getActivity(), R.string.no_more_data);
    }

    @Override
    public void getLatestSuc(BasePage<ServiceMsg> data) {

        mAdapter.add(data.getRecords().get(0));
        rvMsg.smoothScrollToPosition(mAdapter.getDataList().size());
    }
}
