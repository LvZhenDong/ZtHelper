package com.egr.drillinghelper.ui.widgets;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.utils.WindowUtils;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;


public class DialogHelper {

    /**
     * 开启类似iOS HDPB dialog
     *
     * @param context
     * @param title
     * @return
     */
    public static ACProgressFlower openiOSPbDialog(Context context, String title) {
        ACProgressFlower dialog = new ACProgressFlower.Builder(context)
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                .text(title)
                .fadeColor(Color.DKGRAY).build();
        return dialog;
    }

//    /**
//     * open a loading dialog
//     *
//     * @param context
//     * @param content
//     * @return
//     */
//    public static Dialog openLoadingDialog(Context context, String content) {
//        View layout = View.inflate(context, R.layout.dialog_loading, null);
//        Dialog dialog = new Dialog(context, R.style.LLDialog);
//        dialog.setContentView(layout);
//        TextView titleTv = (TextView) layout.findViewById(R.id.dialog_loading_content);
//        if (content != null)
//            titleTv.setText(content);
//        dialog.setCancelable(false);
//        return dialog;
//    }
//

    /**
     * open a confirm and cancel dialog
     *
     * @param context
     * @param title
     * @param content
     * @return
     */
    public static Dialog openConfirmDialog(Context context, String title, String content,boolean singleBtn,
                                           final OnDialogClickListener listener) {
        String left=context.getString(R.string.cancel);
        String right=context.getString(R.string.sure);
        View layout = View.inflate(context, R.layout.dialog_confirm, null);
        final Dialog dialog = new Dialog(context, R.style.LLDialog);
        dialog.setContentView(layout);
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.width = WindowUtils.dpToPx(context, 260);
        dialog.getWindow().setAttributes(lp);
        dialog.setCancelable(true);
        TextView titleTv = (TextView) layout.findViewById(R.id.dialog_confirm_title);
        TextView contentTv = (TextView) layout.findViewById(R.id.dialog_confirm_content);
        TextView leftTv = (TextView) layout.findViewById(R.id.dialog_confirm_left_bt);
        TextView rightTv = (TextView) layout.findViewById(R.id.dialog_confirm_right_bt);
        View divider = layout.findViewById(R.id.dialog_confirm_divider);
        titleTv.setText(title);
        contentTv.setText(content);
        if (singleBtn) {//隐藏left
            leftTv.setVisibility(View.GONE);
            divider.setVisibility(View.GONE);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
        } else {
            leftTv.setText(left);
                leftTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        if(listener != null)
                            listener.onCancelClick();
                    }
                });
        }
        rightTv.setText(right);
        rightTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    if(listener != null)
                        listener.onEnsureClick();
                }
            });

        return dialog;
    }

    public interface OnDialogClickListener{
        void onEnsureClick();

        void onCancelClick();
    }

    public static Dialog openConfirmDialog(Context context, int title, int content,boolean singleBtn,
                                           OnDialogClickListener listener) {
        return openConfirmDialog(context,
                context.getResources().getString(title),
                context.getResources().getString(content),singleBtn,
                listener);
    }
//
//    /**
//     * open a update and cancel dialog
//     *
//     * @param context
//     * @param title
//     * @param content
//     * @param left
//     * @param right
//     * @param leftOnClick
//     * @param rightOnClick
//     * @return
//     */
//    public static Dialog openUpdateDialog(Context context, String title, String content, String left, String right,
//                                          View.OnClickListener leftOnClick,
//                                          View.OnClickListener rightOnClick) {
//        View layout = View.inflate(context, R.layout.dialog_update, null);
//        final Dialog dialog = new Dialog(context, R.style.LLDialog);
//        dialog.setContentView(layout);
//        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
//        lp.width = WindowUtils.dpToPx(context, 260);
//        dialog.getWindow().setAttributes(lp);
//        TextView titleTv = (TextView) layout.findViewById(R.id.dialog_update_title);
//        TextView contentTv = (TextView) layout.findViewById(R.id.dialog_update_content);
//        TextView leftTv = (TextView) layout.findViewById(R.id.dialog_update_left_bt);
//        TextView rightTv = (TextView) layout.findViewById(R.id.dialog_update_right_bt);
//        View divider = layout.findViewById(R.id.dialog_update_divider);
//        titleTv.setText(title);
//        contentTv.setText(content);
//        if (left == null) {//隐藏left
//            leftTv.setVisibility(View.GONE);
//            divider.setVisibility(View.GONE);
//        } else {
//            leftTv.setText(left);
//            if (leftOnClick != null)
//                leftTv.setOnClickListener(leftOnClick);
//            else
//                leftTv.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        dialog.dismiss();
//                    }
//                });
//        }
//        rightTv.setText(right);
//        if (rightOnClick != null)
//            rightTv.setOnClickListener(rightOnClick);
//        else
//            rightTv.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    dialog.dismiss();
//                }
//            });
//        dialog.setCancelable(true);
//        return dialog;
//    }
//
//    /**
//     * 开启登录注册dialog
//     *
//     * @param context
//     * @param registerOnclick
//     * @param loginOnclick
//     * @return
//     */
//    public static Dialog openLoginAndRegisterDialog(Context context, View.OnClickListener registerOnclick, View.OnClickListener loginOnclick) {
//        View layout = View.inflate(context, R.layout.dialog_login, null);
//        Dialog dialog = new Dialog(context, R.style.LLDialog);
//        dialog.setContentView(layout);
//        dialog.setCancelable(true);
//        TextView register = (TextView) layout.findViewById(R.id.dialog_login_register);
//        TextView login = (TextView) layout.findViewById(R.id.dialog_login_login);
//        if (registerOnclick != null)
//            register.setOnClickListener(registerOnclick);
//        if (loginOnclick != null)
//            login.setOnClickListener(loginOnclick);
//        return dialog;
//    }
//
//    /**
//     * 开启重新登录dialog
//     *
//     * @param context
//     */
//    public static void openReloginDialog(final Context context) {
//        ThreadUtil.getMainThreadHandler().post(new Runnable() {
//            @Override
//            public void run() {
//                View layout = View.inflate(context, R.layout.dialog_confirm, null);
//                final Dialog dialog = new Dialog(context, R.style.LLDialog);
//                dialog.setContentView(layout);
//                WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
//                lp.width = WindowUtils.dpToPx(context, 260);
//                dialog.getWindow().setAttributes(lp);
//                TextView titleTv = (TextView) layout.findViewById(R.id.dialog_confirm_title);
//                TextView contentTv = (TextView) layout.findViewById(R.id.dialog_confirm_content);
//                TextView leftTv = (TextView) layout.findViewById(R.id.dialog_confirm_left_bt);
//                TextView rightTv = (TextView) layout.findViewById(R.id.dialog_confirm_right_bt);
//                View divider = layout.findViewById(R.id.dialog_confirm_divider);
//                titleTv.setText(context.getString(R.string.dialog_title_hint));
//                contentTv.setText(context.getString(R.string.user_login_expire_or_otherlogin));
//                leftTv.setVisibility(View.GONE);
//                divider.setVisibility(View.GONE);
//                rightTv.setText(context.getString(R.string.confirm));
//                rightTv.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        LLUserManager.getInstance().logout(context);
//                        LLAppManager.getInstance().finishOtherActivity(HomeActivity.class);
//                        context.startActivity(new Intent(context, LoginActivity.class));
//                        dialog.dismiss();
//                    }
//                });
//                dialog.setCancelable(false);
//                dialog.show();
//            }
//        });
//    }
//
//    /**
//     * 开启一个底部提示dialog
//     *
//     * @param context
//     * @param title
//     * @param cancle
//     * @param content
//     * @param onItemClickListener
//     * @return
//     */
//    public static void openBottomHintDialog(Context context, String title, String cancle,
//                                            String[] content, OnItemClickListener onItemClickListener) {
//        new AlertView.Builder().setContext(context)
//                .setStyle(AlertView.Style.ActionSheet)
//                .setTitle(title)
//                .setMessage(null)
//                .setCancelText(cancle)
//                .setOthers(content)
//                .setOnItemClickListener(onItemClickListener)
//                .build()
//                .setCancelable(true)
//                .show();
//    }
//
//    /**
//     * 红包点击弹出框
//     *
//     * @param title   发红包的名字
//     * @param messege 当不能领红包时候显示的话
//     * @param type    是否显示烟花爆炸效果  0显示  1不显示
//     */
//    public static RedpkgDialog showRedpkgDialog(Context context, String title, String messege, int type, DialogInterface.OnClickListener listener) {
//        RedpkgDialog.Builder builder = new RedpkgDialog.Builder(context);
//        builder.setTitle(title);
//        builder.setMessage(messege);
//        builder.setType(type);
//        if (listener == null)
//            builder.setPositiveButton(new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//                    dialogInterface.dismiss();
//                }
//            });
//        else
//            builder.setPositiveButton(listener);
//        RedpkgDialog redpkgDialog = builder.create();
//        redpkgDialog.show();
//        return redpkgDialog;
//    }
//
//    /**
//     * 开启支付dialog
//     *
//     * @param context
//     * @param amountStr
//     * @param onPayConfirmListener
//     */
//    public static void openPayDialog(Context context, final String amountStr, final OnPayConfirmListener onPayConfirmListener) {
//        View layout = View.inflate(context, R.layout.dialog_pay, null);
//        final Dialog dialog = new Dialog(context, R.style.LLDialog);
//        dialog.setContentView(layout);
//        dialog.setCancelable(false);
//        TextView amount = (TextView) layout.findViewById(R.id.pay_amount);
//        TextView cancle = (TextView) layout.findViewById(R.id.pay_cancle);
//        TextView confirm = (TextView) layout.findViewById(R.id.pay_confirm);
//        final EditText password = (EditText) layout.findViewById(R.id.pay_password);
//        amount.setText("¥ " + amountStr);
//        cancle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
//        confirm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//                if (onPayConfirmListener != null)
//                    onPayConfirmListener.onPayConfirm(amountStr, password.getText().toString().trim());
//
//            }
//        });
//        dialog.show();
//    }
//
//    /**
//     * 开启一级轮子view
//     *
//     * @param context
//     * @param title
//     * @param list
//     * @param listener
//     * @return
//     */
//    public static <T> OptionsPickerView openPickDialog(Context context, String title, List<T> list, OptionsPickerView.OnOptionsSelectListener listener) {
//        OptionsPickerView optionsPickerView = new OptionsPickerView.Builder(context, listener)
//                .setTitleText(title)
//                .build();
//        optionsPickerView.setPicker(list);
//        return optionsPickerView;
//    }
//
//    /**
//     * 开启时间选择器
//     *
//     * @param context
//     * @param listener
//     * @return
//     */
//    public static TimePickerView openTimePickDialog(Context context, TimePickerView.OnTimeSelectListener listener) {
//        TimePickerView timePickerView = new TimePickerView.Builder(context, listener)
//                .setType(new boolean[]{true, true, true, false, false, false})
//                .build();
//        return timePickerView;
//    }
//
//    /**
//     * 开启一个带edittext的对话框
//     *
//     * @param context
//     * @param title
//     * @param subTitle
//     * @param cancle
//     * @param content
//     * @param listener
//     * @return
//     */
//    public static AlertView openEditDialog(Context context, String title, String subTitle, String cancle, String[] content, OnItemClickListener listener) {
//        AlertView alertView = new AlertView(title, subTitle, cancle,
//                null, content, context, AlertView.Style.Alert, listener);
//        return alertView;
//    }
//
//    /**
//     * 开启发送验证码dialog
//     *
//     * @param context
//     * @param phone
//     * @param sendCodeCallback
//     */
//    public static void openSendCodeDialog(Context context, String phone, SendCodeCallback sendCodeCallback) {
//        SendCodeDialog dialog = new SendCodeDialog(context, R.style.LLDialog);
//        dialog.setPhone(phone);
//        dialog.setCancelable(false);
//        dialog.setSendCodeCallback(sendCodeCallback);
//        dialog.show();
//        Window window = dialog.getWindow();
//        WindowManager.LayoutParams lp = window.getAttributes();
//        lp.gravity = Gravity.CENTER;
//        lp.width = WindowUtils.dpToPx(context, 260);//宽高可设置具体大小
//        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
//        dialog.getWindow().setAttributes(lp);
//    }
//
//    /**
//     * 开启领取补贴dialog
//     *
//     * @param context
//     * @param money
//     */
//    public static void openGetSubsidyDialog(Context context, Double money) {
//        SubsidyGetDialog dialog = new SubsidyGetDialog(context, R.style.LLDialog);
//        dialog.setSubsidyMoney(money);
//        dialog.setCancelable(true);
//        dialog.show();
//        Window window = dialog.getWindow();
//        WindowManager.LayoutParams lp = window.getAttributes();
//        lp.gravity = Gravity.CENTER;
//        lp.width = WindowUtils.dpToPx(context, 320);//宽高可设置具体大小
//        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
//        dialog.getWindow().setAttributes(lp);
//    }
}
