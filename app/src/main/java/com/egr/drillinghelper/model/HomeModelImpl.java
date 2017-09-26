package com.egr.drillinghelper.model;

import com.egr.drillinghelper.mvp.BaseModel;
import com.egr.drillinghelper.presenter.HomePresenterImpl;

/**
 * author lzd
 * date 2017/9/26 16:40
 * 类描述：
 */

public class HomeModelImpl extends BaseModel<HomePresenterImpl> {
    public HomeModelImpl(HomePresenterImpl homePresenter) {
        super(homePresenter);
    }
}
