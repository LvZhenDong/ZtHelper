package com.egr.drillinghelper.ui.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.bean.response.Explain;
import com.egr.drillinghelper.bean.response.KnowCatalog;
import com.egr.drillinghelper.bean.response.Parts;
import com.egr.drillinghelper.common.MyConstants;
import com.egr.drillinghelper.contract.SearchContract;
import com.egr.drillinghelper.hybrid.CommBrowserActivity;
import com.egr.drillinghelper.presenter.SearchPresenterImpl;
import com.egr.drillinghelper.ui.adapter.SearchResultAdapter;
import com.egr.drillinghelper.ui.base.BaseActivity;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;
import com.egr.drillinghelper.ui.widgets.DialogHelper;
import com.egr.drillinghelper.utils.AnimViewWrapper;
import com.egr.drillinghelper.utils.DensityUtils;
import com.egr.drillinghelper.utils.ToastUtils;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cc.cloudist.acplibrary.ACProgressFlower;

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
    @BindView(R.id.rl_search)
    RelativeLayout rlSearch;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_cancel)
    ImageView ivCancel;
    @BindView(R.id.rv_result)
    LRecyclerView rvResult;
    /**
     * 搜索图标动画前的marginLeft
     */
    int mOriginalMarginLeft;
    /**
     * 动画时间
     */
    int mSearchAnimTime = 300;
    int backWidth, searchWidth;
    int type;
    SearchResultAdapter mAdapter;
    List<String> mList = new ArrayList<>();
    List<KnowCatalog> mKnowCatalogs;
    List<Parts> mParts;
    List<Explain> mExplainCatalogs;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;

    private ACProgressFlower mDialog;
    private String keyword;

    @Override
    public int returnLayoutID() {
        return R.layout.activity_search;
    }

    @Override
    public void TODO(Bundle savedInstanceState) {
        initSearchRl();
        mDialog = DialogHelper.openiOSPbDialog(this, getString(R.string.waiting));
        type = getIntent().getIntExtra(KEY_INTENT, MyConstants.SEARCH_TYPE_EXPLAIN);

        mAdapter = new SearchResultAdapter(this);
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mAdapter);

        rvResult.setAdapter(mLRecyclerViewAdapter);
        rvResult.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        rvResult.setLayoutManager(new LinearLayoutManager(this));
        rvResult.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(!TextUtils.isEmpty(keyword)){
                    presenter.search(keyword,type);
                }
            }
        });
        rvResult.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                presenter.loadMore();
            }
        });
        mLRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                showDetail(position);
            }
        });

        mAdapter.setDataList(mList);
    }

    @Override
    public SearchPresenterImpl createPresenter() {
        return new SearchPresenterImpl();
    }

    private void initSearchRl() {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) ivSearch.getLayoutParams();

        mOriginalMarginLeft = (int) DensityUtils.px2dp(getActivity(), params.leftMargin);
        ivBack.post(new Runnable() {
            @Override
            public void run() {
                backWidth = (int) DensityUtils.px2dp(SearchActivity.this, ivBack.getWidth());
                searchWidth = (int) DensityUtils.px2dp(SearchActivity.this, ivSearch.getWidth());

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

    @OnClick({R.id.tv_search, R.id.iv_search, R.id.et_search, R.id.iv_back,
            R.id.ll_search, R.id.rl_search, R.id.iv_cancel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_search:
                keyword=etSearch.getText().toString().trim();
                if(!TextUtils.isEmpty(keyword)){
                    mDialog.show();
                    presenter.search(keyword,type);
                }
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
            case R.id.rl_search:
                break;
        }
    }

    private void showDetail(int position) {
        switch (type) {
            case MyConstants.SEARCH_TYPE_EXPLAIN:   //使用说明
                Explain explainOut=mExplainCatalogs.get(position);
                String id = explainOut.getId();
                Intent intent = new Intent(getActivity(), ExplainCatalogActivity.class);
                intent.putExtra(KEY_INTENT, id);
                startActivity(intent);
                break;
            case MyConstants.SEARCH_TYPE_KNOWLEDGE: //知识问答
                Intent intentKnow = new Intent(this, KnowArticleActivity.class);
                intentKnow.putExtra(BaseActivity.KEY_INTENT, mKnowCatalogs.get(position).getContent());
                startActivity(intentKnow);
                break;
            case MyConstants.SEARCH_TYPE_PARTS: //配件
                Parts part = mParts.get(position);
                CommBrowserActivity.start(this,part.getUrl(),part.getName());
                break;
        }
    }

    @Override
    public void searchFail(String msg) {
        mDialog.dismiss();
        rvResult.refreshComplete(10);
        ToastUtils.show(this, msg);
    }

    @Override
    public void noMoreData() {
        rvResult.refreshComplete(10);
        mDialog.dismiss();
        ToastUtils.show(this, R.string.no_more_data);
    }

    @Override
    public void searchKnowSuccess(List<KnowCatalog> knowCatalogs,
                                  List<String> titles) {
        int[] pos = new int[2];
        etSearch.getLocationInWindow(pos);
        if (mAdapter != null)
            mAdapter.setPaddingLeft(pos[0] + DensityUtils.dp2px(SearchActivity.this, 10));

        rvResult.refreshComplete(10);
        mDialog.dismiss();
        mAdapter.setDataList(titles);
        mKnowCatalogs = knowCatalogs;
    }

    @Override
    public void searchParts(List<Parts> parts, List<String> titles) {
        int[] pos = new int[2];
        etSearch.getLocationInWindow(pos);
        if (mAdapter != null)
            mAdapter.setPaddingLeft(pos[0] + DensityUtils.dp2px(SearchActivity.this, 10));

        rvResult.refreshComplete(10);
        mDialog.dismiss();
        mAdapter.setDataList(titles);
        mParts = parts;
    }

    @Override
    public void searchExplainCatalog(List<Explain> explainCatalogs, List<String> titles) {
        int[] pos = new int[2];
        etSearch.getLocationInWindow(pos);
        if (mAdapter != null)
            mAdapter.setPaddingLeft(pos[0] + DensityUtils.dp2px(SearchActivity.this, 10));

        rvResult.refreshComplete(10);
        mDialog.dismiss();
        mAdapter.setDataList(titles);
        mExplainCatalogs = explainCatalogs;
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
        ObjectAnimator animatorMarginLeft = ObjectAnimator.ofInt(new AnimViewWrapper.MarginLeft
                (ivSearch, getActivity()), "marginLeft", 10);
        animatorMarginLeft.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                //设置搜索结果ListView的paddingLeft
                int[] pos = new int[2];
                etSearch.getLocationInWindow(pos);

                if (mAdapter != null)
                    mAdapter.setPaddingLeft(pos[0] + DensityUtils.dp2px(SearchActivity.this, 10));
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

    /**
     * 实现点击空白处，软键盘消失事件
     *
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            // 获得当前得到焦点的View，一般情况下就是EditText（特殊情况就是轨迹求或者实体案件会移动焦点）
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {
                hideSoftInput(v.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时没必要隐藏
     *
     * @param v
     * @param event
     * @return
     */
    private boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left
                    + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击EditText的事件，忽略它。
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditView上，和用户用轨迹球选择其他的焦点
        return false;
    }

    /**
     * 多种隐藏软件盘方法的其中一种
     *
     * @param token
     */
    private void hideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token,
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


}
