package com.egr.drillinghelper.receiver;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.app.EgrAppManager;
import com.egr.drillinghelper.bean.base.BaseResponseBean;
import com.egr.drillinghelper.bean.response.Message;
import com.egr.drillinghelper.common.RxBusConstant;
import com.egr.drillinghelper.common.UserManager;
import com.egr.drillinghelper.factory.APIServiceFactory;
import com.egr.drillinghelper.factory.TransformersFactory;
import com.egr.drillinghelper.ui.activity.LoginActivity;
import com.egr.drillinghelper.ui.activity.MessageDetailActivity;
import com.egr.drillinghelper.ui.activity.ServiceActivity;
import com.egr.drillinghelper.ui.base.BaseActivity;
import com.egr.drillinghelper.ui.widgets.DialogHelper;
import com.egr.drillinghelper.utils.EgrRxBus;
import com.egr.drillinghelper.utils.L;
import com.google.gson.Gson;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.data.JPushLocalNotification;
import io.reactivex.functions.Consumer;

/**
 * author lzd
 * date 2017/10/16 16:32
 * 类描述：
 */

public class AppReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            String id = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
            L.i("极光注册成功 code:" + id);
            UserManager.getInstance().setJPushId(id);
        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            sendToNotification(context, bundle);
        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            openNotification(context, bundle);
        }
    }

    private void sendToNotification(Context context, Bundle bundle) {
        String string = bundle.getString(JPushInterface.EXTRA_MESSAGE);
        L.i("接受到推送消息:" + string);
        Message message = new Gson().fromJson(string, Message.class);
        if (!UserManager.getInstance().isLogined() ||
                !TextUtils.equals(UserManager.getInstance().getUserId(), message.getUserId()))
            return;
        if (message.isLoginConflict()) {
            showLoginConflictDialog(context, message);
        } else if (message.isMessage()) {
            showMessage(context, message, string);
        } else if (message.isServiceMsg()) {
            //如果当前是在ServiceActivity界面就直接显示回复
            if (EgrAppManager.getInstance().
                    isTopActivity(context, "com.egr.drillinghelper.ui.activity.ServiceActivity")) {
                EgrRxBus.post(message);
            } else {     //显示顶部通知
                EgrRxBus.post(RxBusConstant.UPDATE_MSG);
                showServiceMessage(context, message, string);
                EgrRxBus.post(RxBusConstant.NEW_SERVICE_MSG);
            }
        }
    }

    private void showServiceMessage(Context context, Message message, String string) {
        JPushLocalNotification ln = new JPushLocalNotification();
        ln.setBuilderId(0);
        ln.setContent(message.getMsg());
        ln.setTitle(message.getTitle());
        ln.setBroadcastTime(System.currentTimeMillis() + 500);
        ln.setExtras(string);
        JPushInterface.addLocalNotification(context, ln);
    }

    /**
     * 弹出登录冲突dialog
     *
     * @param message
     */
    private void showLoginConflictDialog(Context context, Message message) {
        final Activity activity = EgrAppManager.getInstance().currentActivity();
        String ensure = context.getString(R.string.quit_login);
        Dialog dialog = DialogHelper.openConfirmDialog(activity, message.getTitle(), message.getMsg(),
                true, ensure, new DialogHelper.OnDialogClickListener() {
                    @Override
                    public void onEnsureClick() {
                        UserManager.getInstance().quit();
                        EgrAppManager.getInstance().finishAllActivity();
                        Intent intent = new Intent(activity, LoginActivity.class);
                        intent.putExtra(BaseActivity.KEY_INTENT_BOOLEAN, true);
                        activity.startActivity(intent);
                    }

                    @Override
                    public void onCancelClick() {

                    }
                });

        dialog.show();
        readLoginConflictMsg(message.getId());
    }

    /**
     * 将登录冲突消息标记为已读
     */
    private void readLoginConflictMsg(String messageId) {
        APIServiceFactory.getInstance().createService()
                .readMsg(messageId)
                .compose(TransformersFactory.emptyTrans())
                .subscribe(new Consumer<BaseResponseBean>() {
                    @Override
                    public void accept(BaseResponseBean baseResponseBean) throws Exception {

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    private void showMessage(Context context, Message message, String string) {
        EgrRxBus.post(RxBusConstant.UPDATE_MSG);
        JPushLocalNotification ln = new JPushLocalNotification();
        ln.setBuilderId(0);
        ln.setContent(message.getMsg());
        ln.setTitle(message.getTitle());
        ln.setBroadcastTime(System.currentTimeMillis() + 500);
        ln.setExtras(string);
        JPushInterface.addLocalNotification(context, ln);
    }

    private void openNotification(Context context, Bundle bundle) {
        String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
        Message message = new Gson().fromJson(extras, Message.class);
        if (message == null || TextUtils.isEmpty(message.getId())) return;
        if (!UserManager.getInstance().isLogined() || !message.getUserId().equals(UserManager.getInstance().getUserId())) {
            Intent mIntent = new Intent(context, LoginActivity.class);
            mIntent.putExtra(BaseActivity.KEY_INTENT_BOOLEAN, true);
            context.startActivity(mIntent);

            return;
        } else if (message.isMessage()) {
            Intent mIntent = new Intent(context, MessageDetailActivity.class);
            mIntent.putExtra(BaseActivity.KEY_INTENT, message.getId());
            context.startActivity(mIntent);
        } else if (message.isServiceMsg()) {
            Intent mIntent = new Intent(context, ServiceActivity.class);
            context.startActivity(mIntent);
        }


    }

}
