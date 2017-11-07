package com.egr.drillinghelper.ui.activity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.contract.FeedbackHistoryContract;
import com.egr.drillinghelper.presenter.FeedbackHistoryPresenterImpl;
import com.egr.drillinghelper.ui.adapter.FeedbackHistoryFragmentAdapter;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author lzd
 * date 2017/9/27 17:13
 * 类描述：
 */

public class FeedbackHistoryActivity extends BaseMVPActivity<FeedbackHistoryContract.View,
        FeedbackHistoryPresenterImpl> implements FeedbackHistoryContract.View {
    @BindView(R.id.tv_wait_for_reply)
    TextView tvWaitForReply;
    @BindView(R.id.tv_replied)
    TextView tvReplied;
    @BindView(R.id.message_triangle)
    RelativeLayout messageTriangle;
    @BindView(R.id.vp_feedback_history)
    ViewPager vpFeedbackHistory;

    private int currentPosition;
    FeedbackHistoryFragmentAdapter mAdapter;

    @Override
    public int returnLayoutID() {
        return R.layout.activity_feedback_history;
    }

    @Override
    public void TODO(Bundle savedInstanceState) {
        setUmengAnalyze(R.string.feedback_histroy);
        setupActionBar(R.string.feedback_histroy, true);
        setActionbarBackground(R.color.white);

        mAdapter = new FeedbackHistoryFragmentAdapter(getSupportFragmentManager());
        vpFeedbackHistory.setOffscreenPageLimit(mAdapter.getCount());
        vpFeedbackHistory.setAdapter(mAdapter);
        vpFeedbackHistory.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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

    @Override
    public FeedbackHistoryPresenterImpl createPresenter() {
        return new FeedbackHistoryPresenterImpl();
    }

    @OnClick({R.id.tv_wait_for_reply, R.id.tv_replied})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_wait_for_reply:
                startAnimation(0, false);
                break;
            case R.id.tv_replied:
                startAnimation(1, false);
                break;
        }
    }

    private void startAnimation(int position, boolean isScroll) {
        int length = tvWaitForReply.getMeasuredWidth();
        int x = (int) messageTriangle.getX();
        switch (position) {
            case 0:
                if (currentPosition == 1) {
                    startAnimator(x - length);
                }
                currentPosition = 0;
                break;
            case 1:
                if (currentPosition == 0) {
                    startAnimator(length + x);
                }
                currentPosition = 1;
                break;
        }
        if (!isScroll)
            vpFeedbackHistory.setCurrentItem(position);
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
}
