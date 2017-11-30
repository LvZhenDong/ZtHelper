package com.egr.drillinghelper.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.ui.base.BaseActivity;
import com.egr.drillinghelper.utils.GlideUtils;

import java.util.ArrayList;

import butterknife.BindView;
import ru.truba.touchgallery.GalleryWidget.BasePagerAdapter;
import ru.truba.touchgallery.GalleryWidget.GalleryViewPager;
import ru.truba.touchgallery.GalleryWidget.UrlPagerAdapter;

/**
 * author lzd
 * date 2017/10/17 11:53
 * 类描述：
 */

public class GalleryActivity extends BaseActivity {
    @BindView(R.id.viewpager)
    GalleryViewPager viewpager;
    @BindView(R.id.tv_number)
    TextView tvNum;
    @BindView(R.id.iv_gif)
    ImageView mIvGif;
    private ArrayList<String> images;

    public static void start(Context context, ArrayList<String> imgList) {
        Intent intent = new Intent(context, GalleryActivity.class);
        intent.putExtra(KEY_INTENT, imgList);
        context.startActivity(intent);
    }

    @Override
    public int returnLayoutID() {
        return R.layout.activity_gallery;
    }

    @Override
    public void TODO(Bundle savedInstanceState) {
        setUmengAnalyze(R.string.gallery);
        images = (ArrayList<String>) getIntent().getSerializableExtra(KEY_INTENT);
        if(images.get(0).endsWith("gif")){
            viewpager.setVisibility(View.GONE);
            tvNum.setVisibility(View.GONE);
            mIvGif.setVisibility(View.VISIBLE);
            GlideUtils.load(images.get(0),mIvGif);
        }else {
            initViewPager();
        }

    }

    private void initViewPager() {
        UrlPagerAdapter pagerAdapter = new UrlPagerAdapter(this, images);
        viewpager.setAdapter(pagerAdapter);
        pagerAdapter.setOnItemChangeListener(new BasePagerAdapter.OnItemChangeListener() {
            @Override
            public void onItemChange(int currentPosition) {
                tvNum.setText((currentPosition + 1) + "/" + images.size());
            }
        });
    }
}
