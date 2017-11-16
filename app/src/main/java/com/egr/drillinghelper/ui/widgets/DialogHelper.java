package com.egr.drillinghelper.ui.widgets;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.utils.WindowUtils;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;


public class DialogHelper {

    public static ACProgressFlower openiOSPbDialog(Context context) {
        return openiOSPbDialog(context, context.getString(R.string.waiting));
    }
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

    public static Dialog openConfirmDialog(Context context, String title, String content,boolean singleBtn,
                                           final OnDialogClickListener listener) {
        return openConfirmDialog(context, title, content, singleBtn,null, listener);
    }
    /**
     * open a confirm and cancel dialog
     *
     * @param context
     * @param title
     * @param content
     * @return
     */
    public static Dialog openConfirmDialog(Context context, String title, String content,boolean singleBtn,String ensure,
                                           final OnDialogClickListener listener) {
        String left=context.getString(R.string.cancel);
        String right= TextUtils.isEmpty(ensure)?context.getString(R.string.sure):ensure;
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
}
