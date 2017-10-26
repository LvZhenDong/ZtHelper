package com.egr.drillinghelper.ui.fragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.bean.rxbus.HomeCurrent;
import com.egr.drillinghelper.common.RxBusConstant;
import com.egr.drillinghelper.ui.adapter.HomeFragmentAdapter;
import com.egr.drillinghelper.ui.base.BaseFragment;
import com.egr.drillinghelper.ui.widgets.BanSlideViewPager;
import com.egr.drillinghelper.utils.EgrRxBus;

import butterknife.BindView;


/**
 * author lzd
 * date 2017/9/26 18:10
 * 类描述：
 */

public class HomeFragment extends BaseFragment {

    @BindView(R.id.vp_home)
    BanSlideViewPager vpHome;
    @BindView(R.id.rb_explain)
    RadioButton rbExplain;
    @BindView(R.id.rb_knows)
    RadioButton rbKnows;
    @BindView(R.id.rb_parts)
    RadioButton rbParts;
    @BindView(R.id.rg_top)
    RadioGroup rgTop;

    HomeFragmentAdapter mAdapter;

    @Override
    public int returnLayoutID() {
        return R.layout.fragment_home;
    }

    @Override
    public void TODO(View view, Bundle savedInstanceState) {
        rbExplain.setChecked(true);
        mAdapter = new HomeFragmentAdapter(getChildFragmentManager());
        vpHome.setOffscreenPageLimit(mAdapter.getCount());
        vpHome.setScrollEnable(false);
        vpHome.setAdapter(mAdapter);
        vpHome.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        rgTop.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_explain:
                        vpHome.setCurrentItem(0);
                        EgrRxBus.post(new HomeCurrent(0));
                        break;
                    case R.id.rb_knows:
                        vpHome.setCurrentItem(1);
                        EgrRxBus.post(new HomeCurrent(1));
                        break;
                    case R.id.rb_parts:
                        vpHome.setCurrentItem(2);
                        EgrRxBus.post(new HomeCurrent(2));
                        break;
                }
            }
        });
    }
}
