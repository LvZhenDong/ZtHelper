package com.egr.drillinghelper.ui.widgets;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.bean.Share;
import com.egr.drillinghelper.ui.adapter.ShareAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.shaohui.bottomdialog.BaseBottomDialog;

/**
 * author lzd
 * date 2017/10/9 14:17
 * 类描述：分享dialog
 */

public class ShareDialog extends BaseBottomDialog {
    @BindView(R.id.rv_share)
    RecyclerView rvShare;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;

    ShareAdapter adapter;
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

        List<Share> list=new ArrayList<>();
        list.add(new Share(R.drawable.ic_weixin,R.string.weixin));
        list.add(new Share(R.drawable.ic_weixin_moments,R.string.weixin_moments));
        list.add(new Share(R.drawable.ic_qq,R.string.QQ));
        list.add(new Share(R.drawable.ic_qzone,R.string.Qzone));
        list.add(new Share(R.drawable.ic_weibo,R.string.weibo));
        adapter.setDataList(list);
        adapter.notifyDataSetChanged();
    }

}
