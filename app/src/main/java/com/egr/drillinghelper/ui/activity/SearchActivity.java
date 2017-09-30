package com.egr.drillinghelper.ui.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.contract.SearchContract;
import com.egr.drillinghelper.presenter.SearchPresenterImpl;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;
import com.egr.drillinghelper.utils.AnimViewWrapper;
import com.egr.drillinghelper.utils.DensityUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author lzd
 * date 2017/9/28 9:45
 * 类描述：搜索
 */

public class SearchActivity extends BaseMVPActivity<SearchContract.View,
        SearchPresenterImpl> implements SearchContract.View {

    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.ll_search)
    LinearLayout llSearch;
    @BindView(R.id.rl_fast_register_search)
    RelativeLayout rlFastRegisterSearch;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_cancel)
    ImageView ivCancel;
    /**
     * 搜索图表动画前的marginLeft
     */
    int mOriginalMarginLeft;
    /**
     * 动画时间
     */
    int mSearchAnimTime = 300;
    /**
     * 搜索结果的左边距，用于与搜索框对齐
     */
    int mResultLvPaddingLeft;

    int backWidth, searchWidth;

    @Override
    public int returnLayoutID() {
        return R.layout.activity_search;
    }

    @Override
    public void TODO(Bundle savedInstanceState) {
        init();
    }

    @Override
    public SearchPresenterImpl createPresenter() {
        return new SearchPresenterImpl();
    }

    private void init() {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) ivSearch.getLayoutParams();

        mOriginalMarginLeft = (int) DensityUtils.px2dp(getActivity(), params.leftMargin);
        ivBack.post(new Runnable() {
            @Override
            public void run() {
                backWidth = (int) DensityUtils.px2dp(SearchActivity.this, ivBack.getWidth());
                searchWidth = (int) DensityUtils.px2dp(SearchActivity.this, ivSearch.getWidth());

            }
        });

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)) {
//                    getProductList(s.toString());
                } else {
//                    dismissPW();
                }
            }
        });
        etSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    searchAnim();
                }
            }
        });
    }

    /**
     * 点击搜索动画
     */
    private void searchAnim() {
        ObjectAnimator animatorMarginLeft = ObjectAnimator.ofInt(new AnimViewWrapper.MarginLeft
                (ivSearch, getActivity()), "marginLeft", 10);
        animatorMarginLeft.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                //设置搜索结果ListView的左边距
                mResultLvPaddingLeft = (int) (etSearch.getX() - llSearch.getX());
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animatorMarginLeft.setDuration(mSearchAnimTime).start();

        ObjectAnimator.ofInt(new AnimViewWrapper.Width(ivBack, getActivity()), "width",
                0).setDuration(mSearchAnimTime).start();
        ObjectAnimator.ofInt(new AnimViewWrapper.Width(ivSearch, getActivity()), "width",
                0).setDuration(mSearchAnimTime).start();
        ObjectAnimator.ofInt(new AnimViewWrapper.Width(ivCancel, getActivity()), "width",
                40).setDuration(mSearchAnimTime).start();
    }


    @OnClick({R.id.tv_search, R.id.iv_search, R.id.et_search, R.id.iv_back,
            R.id.ll_search, R.id.rl_fast_register_search, R.id.iv_cancel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_search:

                break;
            case R.id.iv_search:
                break;
            case R.id.et_search:
                break;
            case R.id.ll_search:
                etSearch.requestFocus();
                break;
            case R.id.iv_cancel:
                cancelSearch();
                break;
            case R.id.rl_fast_register_search:
                break;
        }
    }

    /**
     * 点击取消
     */
    private void cancelSearch() {
//        dismissPW();
        etSearch.clearFocus();
        etSearch.setText("");
        cancelAnim();
    }

    /**
     * 取消搜索的动画
     */
    private void cancelAnim() {
        ObjectAnimator.ofInt(new AnimViewWrapper.MarginLeft(ivSearch, getActivity()),
                "marginLeft", mOriginalMarginLeft).setDuration(mSearchAnimTime).start();
        ObjectAnimator.ofInt(new AnimViewWrapper.Width(ivBack,
                getActivity()), "width", backWidth).setDuration(mSearchAnimTime).start();
        ObjectAnimator.ofInt(new AnimViewWrapper.Width(ivSearch,
                getActivity()), "width", searchWidth).setDuration(mSearchAnimTime).start();
        ObjectAnimator.ofInt(new AnimViewWrapper.Width(ivCancel,
                getActivity()), "width", 0).setDuration(mSearchAnimTime).start();

    }
}
