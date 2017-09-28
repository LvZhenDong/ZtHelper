package com.egr.drillinghelper.presenter;

import com.egr.drillinghelper.contract.HomeContract;
import com.egr.drillinghelper.contract.SearchContract;
import com.egr.drillinghelper.model.HomeModelImpl;
import com.egr.drillinghelper.model.SearchModelImpl;
import com.egr.drillinghelper.mvp.BasePresenter;
import com.egr.drillinghelper.mvp.IModel;

/**
 * author lzd
 * date 2017/9/26 16:41
 * 类描述：
 */

public class SearchPresenterImpl extends BasePresenter<SearchContract.View,
        SearchModelImpl> implements SearchContract.Presenter{
    @Override
    protected IModel createModel() {
        return new SearchModelImpl(this);
    }
}
