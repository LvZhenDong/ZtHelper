package com.egr.drillinghelper.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.egr.drillinghelper.bean.response.Message;
import com.egr.drillinghelper.common.UserManager;
import com.egr.drillinghelper.ui.activity.MessageDetailActivity;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;
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

        Intent mIntent = new Intent(context, MessageDetailActivity.class);
        mIntent.putExtra(BaseMVPActivity.KEY_INTENT, message.getId());
        context.startActivity(mIntent);
    }

}
