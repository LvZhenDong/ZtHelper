package com.egr.drillinghelper.ui.activity;

import android.os.Bundle;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.contract.SearchContract;
import com.egr.drillinghelper.presenter.SearchPresenterImpl;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;

/**
 * author lzd
 * date 2017/9/28 9:45
 * 类描述：搜索
 */

public class SearchActivity extends BaseMVPActivity <SearchContract.View,
        SearchPresenterImpl> implements SearchContract.View{

    @Override
    public int returnLayoutID() {
        return R.layout.activity_search;
    }

    @Override
    public void TODO(Bundle savedInstanceState) {

    }

    @Override
    public SearchPresenterImpl createPresenter() {
        return new SearchPresenterImpl();
    }
}
