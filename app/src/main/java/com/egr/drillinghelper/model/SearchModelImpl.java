package com.egr.drillinghelper.model;

import com.egr.drillinghelper.mvp.BaseModel;
import com.egr.drillinghelper.presenter.HomePresenterImpl;
import com.egr.drillinghelper.presenter.SearchPresenterImpl;

/**
 * author lzd
 * date 2017/9/26 16:40
 * 类描述：
 */

public class SearchModelImpl extends BaseModel<SearchPresenterImpl> {
    public SearchModelImpl(SearchPresenterImpl presenter) {
        super(presenter);
    }
}
