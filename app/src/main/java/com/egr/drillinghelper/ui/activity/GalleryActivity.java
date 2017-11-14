package com.egr.drillinghelper.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.ui.base.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
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
        initViewPager();
    }

    private void initViewPager() {
//        PagerAdapter adapter = new PagerAdapter() {
//
//            @Override
//            public boolean isViewFromObject(View arg0, Object arg1) {
//                return arg0 == arg1;
//            }
//
//            @Override
//            public void destroyItem(ViewGroup container, int position,
//                                    Object obj) {
//                container.removeView((View) obj);
//            }
//
//            @Override
//            public Object instantiateItem(ViewGroup container, int position) {
//                View view = View.inflate(container.getContext(), R.layout.item_pager, null);
//                ImageView imageView = (ImageView) view.findViewById(R.id.item_img);
//                TextView tv = (TextView) view.findViewById(R.id.tv_number);
//                tv.setText((position + 1) + "/" + getCount());
//                GlideUtils.load(images.get(position), imageView);
//                container.addView(view, ViewGroup.LayoutParams.MATCH_PARENT,
//                        ViewGroup.LayoutParams.MATCH_PARENT);
//                return view;
//            }
//
//            @Override
//            public int getCount() {
//                return images.size();
//            }
//        };
        UrlPagerAdapter pagerAdapter=new UrlPagerAdapter(this,images);
        viewpager.setAdapter(pagerAdapter);
    }
}
