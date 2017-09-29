package com.egr.drillinghelper.presenter;

import com.egr.drillinghelper.contract.PartsContract;
import com.egr.drillinghelper.model.PartsModelImpl;
import com.egr.drillinghelper.mvp.BasePresenter;
import com.egr.drillinghelper.mvp.IModel;

/**
 * author lzd
 * date 2017/9/26 16:41
 * 类描述：
 */

public class PartsPresenterImpl extends BasePresenter<PartsContract.View,
        PartsModelImpl> implements PartsContract.Presenter {
    int pageNum = 1;

    @Override
    protected IModel createModel() {
        return new PartsModelImpl(this);
    }

    @Override
    public void getPartsList() {
        pageNum = 1;
        mModel.getPartsList(1);
    }

    @Override
    public void loadMore() {
        mModel.getPartsList(pageNum++);
    }

    @Override
    public boolean isLoadMore() {
        return pageNum != 1;
    }
}
