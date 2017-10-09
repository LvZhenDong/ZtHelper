package com.egr.drillinghelper.utils;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.ui.widgets.DialogHelper;

/**
 * author lzd
 * date 2017/10/9 9:58
 * 类描述：
 */

public class PhoneUtils {
    public final static int MY_PERMISSIONS_REQUEST_CALL_PHONE = 0X99;

    /**
     * 拨打电话
     *
     * @param activity
     * @param phone
     */
    public static void callPhone(final Activity activity, final String phone) {
        if (activity == null) return;

        if (!TextUtils.isEmpty(phone)) {
            Dialog dialog = DialogHelper.openConfirmDialog(activity, activity.getString(R.string.confirm_dialog_title_phone),
                    activity.getString(R.string.confirm_dialog_content_phone) + phone + activity.getString(R.string.confirm_dialog_content_phone2),
                    new DialogHelper.OnDialogClickListener() {
                        @Override
                        public void onEnsureClick() {
                            startPhoneIntent(activity, phone);
                        }

                        @Override
                        public void onCancelClick() {

                        }
                    });
            dialog.show();
        }
    }

    public static void startPhoneIntent(Activity activity, String phone) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + phone);
        intent.setData(data);
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.CALL_PHONE},
                    MY_PERMISSIONS_REQUEST_CALL_PHONE);
            return;
        } else {
            //You already have permission
            try {
                activity.startActivity(intent);
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }
    }
}
