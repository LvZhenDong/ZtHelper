package com.egr.drillinghelper.ui.fragment;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.bean.response.Explain;
import com.egr.drillinghelper.bean.response.KnowCatalog;
import com.egr.drillinghelper.bean.response.Parts;
import com.egr.drillinghelper.bean.rxbus.SearchKey;
import com.egr.drillinghelper.contract.SearchContract;
import com.egr.drillinghelper.mvp.BaseMVPFragment;
import com.egr.drillinghelper.presenter.SearchPresenterImpl;
import com.egr.drillinghelper.ui.adapter.HomeFragmentAdapter;
import com.egr.drillinghelper.ui.base.BaseFragment;
import com.egr.drillinghelper.ui.widgets.BanSlideViewPager;
import com.egr.drillinghelper.utils.AnimViewWrapper;
import com.egr.drillinghelper.utils.DensityUtils;
import com.egr.drillinghelper.utils.EgrRxBus;
import com.egr.drillinghelper.utils.ToastUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


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
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.iv_cancel)
    ImageView ivCancel;
    @BindView(R.id.ll_search)
    LinearLayout llSearch;
    @BindView(R.id.rl_search)
    RelativeLayout rlSearch;

    HomeFragmentAdapter mAdapter;

    /**
     * 动画时间
     */
    int mSearchAnimTime = 300;
    int searchWidth;
    String keyword;

    @Override
    public int returnLayoutID() {
        return R.layout.fragment_home;
    }

    @Override
    public void TODO(View view, Bundle savedInstanceState) {
        initSearchRl();
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
                        break;
                    case R.id.rb_knows:
                        vpHome.setCurrentItem(1);
                        break;
                    case R.id.rb_parts:
                        vpHome.setCurrentItem(2);
                        break;
                }
            }
        });
    }

    @OnClick({R.id.tv_search, R.id.ll_search, R.id.rl_search, R.id.iv_cancel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_search:
                keyword = etSearch.getText().toString().trim();
                if(!TextUtils.isEmpty(keyword)){
                    EgrRxBus.post(new SearchKey(keyword,vpHome.getCurrentItem()));
                }
                break;
            case R.id.ll_search:
                etSearch.requestFocus();
                break;
            case R.id.iv_cancel:
                cancelSearch();
                break;
            case R.id.rl_search:
                break;
        }
    }

    private void initSearchRl() {

//        ivSearch.post(new Runnable() {
//            @Override
//            public void run() {
//                searchWidth = (int) DensityUtils.px2dp(getActivity(), ivSearch.getWidth());
//
//            }
//        });

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
     * 点击取消
     */
    private void cancelSearch() {
        etSearch.clearFocus();
        etSearch.setText("");
        cancelAnim();
    }

    /**
     * 点击搜索动画
     */
    private void searchAnim() {
        searchWidth = (int) DensityUtils.px2dp(getActivity(), ivSearch.getWidth());
        ObjectAnimator.ofInt(new AnimViewWrapper.MarginLeft(ivSearch, getActivity()),
                "marginLeft", 0).setDuration(mSearchAnimTime).start();
        ObjectAnimator.ofInt(new AnimViewWrapper.Width(ivSearch, getActivity()), "width",
                0).setDuration(mSearchAnimTime).start();
        ObjectAnimator.ofInt(new AnimViewWrapper.Width(ivCancel, getActivity()), "width",
                40).setDuration(mSearchAnimTime).start();
    }

    /**
     * 取消搜索的动画
     */
    private void cancelAnim() {
        ObjectAnimator.ofInt(new AnimViewWrapper.MarginLeft(ivSearch, getActivity()),
                "marginLeft", 50).setDuration(mSearchAnimTime).start();
        ObjectAnimator.ofInt(new AnimViewWrapper.Width(ivSearch,
                getActivity()), "width", searchWidth).setDuration(mSearchAnimTime).start();
        ObjectAnimator.ofInt(new AnimViewWrapper.Width(ivCancel,
                getActivity()), "width", 0).setDuration(mSearchAnimTime).start();
    }


    public boolean isExplain() {
        return vpHome.getCurrentItem() == 0;
    }
}
