package com.egr.drillinghelper.receiver;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.egr.drillinghelper.app.EgrAppManager;
import com.egr.drillinghelper.bean.response.Message;
import com.egr.drillinghelper.common.RxBusConstant;
import com.egr.drillinghelper.common.UserManager;
import com.egr.drillinghelper.ui.activity.LoginActivity;
import com.egr.drillinghelper.ui.activity.MessageDetailActivity;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;
import com.egr.drillinghelper.ui.widgets.DialogHelper;
import com.egr.drillinghelper.utils.EgrRxBus;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.data.JPushLocalNotification;

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
            Logger.i("极光注册成功 code:" + id);
            UserManager.setJPushId(id);
        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            sendToNotification(context, bundle);
        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            openNotification(context, bundle);
        }
    }

    private void sendToNotification(Context context, Bundle bundle) {
        String string = bundle.getString(JPushInterface.EXTRA_MESSAGE);
        Logger.i("接受到推送消息:" + string);
        Message message = new Gson().fromJson(string, Message.class);
        Logger.i("userId:"+UserManager.getUserId());
        if(TextUtils.isEmpty(UserManager.getUserId()) ||
                !TextUtils.equals(UserManager.getUserId(),message.getUserId()))return;
        if(message.isLoginConflict()){
            showLoginConflictDialog(message);
        }else {
            showMessage(context,message,string);
        }
    }

    /**
     * 弹出登录冲突dialog
     * @param message
     */
    private void showLoginConflictDialog(Message message){
        final Activity activity= EgrAppManager.getInstance().currentActivity();
        Dialog dialog= DialogHelper.openConfirmDialog(activity, message.getTitle(), message.getMsg(),
                true, new DialogHelper.OnDialogClickListener() {
                    @Override
                    public void onEnsureClick() {
                        UserManager.quit();
                        EgrAppManager.getInstance().finishAllActivity();
                        Intent intent = new Intent(activity, LoginActivity.class);
                        intent.putExtra(BaseMVPActivity.KEY_INTENT_BOOLEAN, true);
                        activity.startActivity(intent);
                    }

                    @Override
                    public void onCancelClick() {

                    }
                });

        dialog.show();
    }

    private void showMessage(Context context,Message message,String string){
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
        if(TextUtils.isEmpty(UserManager.getUserId()) || !message.getUserId().equals(UserManager.getUserId())){
            Intent mIntent = new Intent(context, LoginActivity.class);
            mIntent.putExtra(BaseMVPActivity.KEY_INTENT_BOOLEAN, true);
            context.startActivity(mIntent);

            return;
        }


        Intent mIntent = new Intent(context, MessageDetailActivity.class);
        mIntent.putExtra(BaseMVPActivity.KEY_INTENT, message.getId());
        context.startActivity(mIntent);
    }

}