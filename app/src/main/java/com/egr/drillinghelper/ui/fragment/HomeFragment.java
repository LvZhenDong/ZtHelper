package com.egr.drillinghelper.ui.fragment;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.ui.adapter.HomeFragmentAdapter;
import com.egr.drillinghelper.ui.base.BaseFragment;
import com.egr.drillinghelper.ui.widgets.BanSlideViewPager;
import com.egr.drillinghelper.utils.WindowUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author lzd
 * date 2017/9/26 18:10
 * 类描述：
 */

public class HomeFragment extends BaseFragment {
    @BindView(R.id.tv_instructions)
    TextView tvInstructions;
    @BindView(R.id.tv_knowledge)
    TextView tvKnowledge;
    @BindView(R.id.message_triangle)
    RelativeLayout messageTriangle;
    @BindView(R.id.vp_home)
    ViewPager vpHome;
    HomeFragmentAdapter mAdapter;
    private int currentPosition;

    @Override
    public int returnLayoutID() {
        return R.layout.fragment_home;
    }

    @Override
    public void TODO(View view, Bundle savedInstanceState) {
        mAdapter = new HomeFragmentAdapter(getChildFragmentManager());
        vpHome.setOffscreenPageLimit(mAdapter.getCount());
        vpHome.setAdapter(mAdapter);
        vpHome.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                startAnimation(position, true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    private void startAnimation(int position, boolean isScroll) {
        int length = tvInstructions.getMeasuredWidth();
        int x = (int) messageTriangle.getX();
        switch (position) {
            case 0://消息
                if (currentPosition == 1) {
                    startAnimator(x - length);
                }
                currentPosition = 0;
                break;
            case 1://群组
                if (currentPosition == 0) {
                    startAnimator(length + x);
                }
                currentPosition = 1;
                break;
        }
        if (!isScroll)
            vpHome.setCurrentItem(position);
    }

    /**
     * 动画滑动
     *
     * @param newX
     */
    private void startAnimator(int newX) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(messageTriangle, "X", messageTriangle.getX(), newX);
        animator.setDuration(100);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.start();
    }

    @OnClick({R.id.tv_instructions, R.id.tv_knowledge})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_instructions:
                startAnimation(0,false);
                break;
            case R.id.tv_knowledge:
                startAnimation(1,false);
                break;
        }
    }
}
