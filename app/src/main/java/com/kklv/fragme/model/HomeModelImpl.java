package com.kklv.fragme.model;

import com.kklv.fragme.mvp.BaseModel;
import com.kklv.fragme.presenter.HomePresenterImpl;

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
