package com.egr.drillinghelper.ui.widgets;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.bean.ShareIcon;
import com.egr.drillinghelper.bean.response.Share;
import com.egr.drillinghelper.interfaces.OnItemClickListener;
import com.egr.drillinghelper.ui.adapter.ShareAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
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

    public void setContent(Context mContent,Share share) {
        this.mContent = mContent;
        this.share=share;
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

        adapter=new ShareAdapter(getContext());
        rvShare.setLayoutManager(new GridLayoutManager(getContext(),5));
        rvShare.setAdapter(adapter);

        List<ShareIcon> list=new ArrayList<>();
        list.add(new ShareIcon(R.drawable.ic_weixin,R.string.weixin));
        list.add(new ShareIcon(R.drawable.ic_weixin_moments,R.string.weixin_moments));
        list.add(new ShareIcon(R.drawable.ic_qq,R.string.QQ));
        list.add(new ShareIcon(R.drawable.ic_qzone,R.string.Qzone));
        list.add(new ShareIcon(R.drawable.ic_weibo,R.string.weibo));
        adapter.setDataList(list);
        adapter.notifyDataSetChanged();
        adapter.setListener(this);
    }

    private void showShare(Share share,String type) {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        oks.setPlatform(type);

        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(getString(R.string.share));
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
//        oks.setTitleUrl(share.getQrcode());
        // text是分享文本，所有平台都需要这个字段
        oks.setText(share.getContent());
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl(share.getQrcode());
        // url仅在微信（包括好友和朋友圈）中使用
//        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
//        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
//        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
//        oks.setSiteUrl(share.getQrcode());

        // 启动分享GUI
        oks.show(mContent);
    }

    @Override
    public void onItemClick(int position) {
        String type="";
        switch (position){
            case 0:
                type= Wechat.NAME;
                break;
            case 1:
                type= WechatMoments.NAME;
                break;
            case 2:
                type=QQ.NAME;
                break;
            case 3:
                type= QZone.NAME;
                break;
            case 4:
                type= SinaWeibo.NAME;
                break;
        }
        showShare(share,type);
        dismiss();
    }
}
