package com.egr.drillinghelper.ui.widgets;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.bean.ShareIcon;
import com.egr.drillinghelper.bean.response.Share;
import com.egr.drillinghelper.interfaces.OnItemClickListener;
import com.egr.drillinghelper.ui.adapter.ShareAdapter;
import com.egr.drillinghelper.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;
import me.shaohui.bottomdialog.BaseBottomDialog;

/**
 * author lzd
 * date 2017/10/9 14:17
 * 类描述：分享dialog
 */

public class ShareDialog extends BaseBottomDialog implements OnItemClickListener {
    @BindView(R.id.rv_share)
    RecyclerView rvShare;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;

    ShareAdapter adapter;
    Context mContent;
    Share share;
    String text, url;

    public void setContent(Context mContent, Share share) {
        this.mContent = mContent;
        this.share = share;
        url = share.getQrcode();
        text = TextUtils.isEmpty(share.getContent()) ? getString(R.string.app_name) : share.getContent();
    }

    @Override
    public int getLayoutRes() {
        return R.layout.dialog_share;
    }

    @Override
    public void bindView(View v) {
        ButterKnife.bind(this, v);

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        adapter = new ShareAdapter(getContext());
        List<ShareIcon> list = new ArrayList<>();
        list.add(new ShareIcon(R.drawable.ic_weixin, R.string.weixin));
        list.add(new ShareIcon(R.drawable.ic_weixin_moments, R.string.weixin_moments));
        list.add(new ShareIcon(R.drawable.ic_qq, R.string.QQ));
//        list.add(new ShareIcon(R.drawable.ic_qzone, R.string.Qzone));
        //        list.add(new ShareIcon(R.drawable.ic_weibo, R.string.weibo));
        rvShare.setLayoutManager(new GridLayoutManager(getContext(), list.size()));
        rvShare.setAdapter(adapter);
        adapter.setDataList(list);
        adapter.notifyDataSetChanged();
        adapter.setListener(this);
    }

    private void showShare(final String type) {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        oks.setPlatform(type);

        // 启动分享GUI
        oks.setTitle(getString(R.string.app_name));
        oks.setTitleUrl(url);
        oks.setText(text);
        oks.setImageUrl(url);      //如果图片不能加载出来，则微博分享会失败
        oks.setUrl(url);
        oks.setSite(getString(R.string.app_name));
        oks.setSiteUrl(url);

        oks.setCallback(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                ToastUtils.show(mContent, R.string.share_suc);
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                ToastUtils.show(mContent, R.string.share_error);
            }

            @Override
            public void onCancel(Platform platform, int i) {
                if (!TextUtils.equals(type, QQ.NAME) && !TextUtils.equals(type, QZone.NAME))    //因为QQ和Qzone即使分享成功也会回调cancel，所以这里特殊处理
                    ToastUtils.show(mContent, R.string.share_cancel);
            }
        });

        // 启动分享
        oks.show(mContent);
    }

    @Override
    public void onItemClick(int position) {
        String type = "";
        switch (position) {
            case 0:
                type = Wechat.NAME;
                break;
            case 1:
                type = WechatMoments.NAME;
                break;
            case 2:
                type = QQ.NAME;
                break;
            case 3:
                type = QZone.NAME;
                break;
            case 4:
                type = SinaWeibo.NAME;
                break;
        }
        showShare(type);
        dismiss();
    }
}
