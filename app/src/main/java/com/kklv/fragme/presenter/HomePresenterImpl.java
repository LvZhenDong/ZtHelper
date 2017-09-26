package com.kklv.fragme.presenter;

import com.kklv.fragme.contract.HomeContract;
import com.kklv.fragme.model.HomeModelImpl;
import com.kklv.fragme.mvp.BasePresenter;
import com.kklv.fragme.mvp.IModel;

/**
 * author lzd
 * date 2017/9/26 16:41
 * 类描述：
 */

public class HomePresenterImpl extends BasePresenter<HomeContract.View,
        HomeModelImpl> implements HomeContract.Presenter{
    @Override
    protected IModel createModel() {
        return new HomeModelImpl(this);
    }
}
