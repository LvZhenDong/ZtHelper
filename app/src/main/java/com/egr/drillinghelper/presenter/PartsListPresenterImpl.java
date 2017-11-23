package com.egr.drillinghelper.presenter;

import com.egr.drillinghelper.bean.base.BasePage;
import com.egr.drillinghelper.bean.response.Store;
import com.egr.drillinghelper.contract.PartsListContract;
import com.egr.drillinghelper.model.PartsListModelImpl;
import com.egr.drillinghelper.mvp.BasePresenter;
import com.egr.drillinghelper.mvp.IModel;
import com.egr.drillinghelper.utils.CollectionUtil;


public class PartsListPresenterImpl extends BasePresenter<PartsListContract.View,
        PartsListModelImpl> implements PartsListContract.Presenter {
    public static final int KEY_ALL = -1;

    int current;
    String mType;

    @Override
    protected IModel createModel() {
        return new PartsListModelImpl(this);
    }

    @Override
    public void getPartsList(int type) {
        current = 1;
        this.mType = type + "";
        if (type == KEY_ALL)
            mType = null;
        mModel.getPartsList(mType, "", current);
    }

    @Override
    public void getPastsFail(String msg) {

    }

    @Override
    public void getPartsListSuccess(BasePage<Store> data) {
        if (CollectionUtil.isListEmpty(data.getRecords()) && data.getCurrent() != 1) {
            getView().noMoreData();
        } else {
            current = data.getCurrent();
            getView().getPartsListSuccess(data);
        }
    }

    @Override
    public void loadMore() {
        mModel.getPartsList(mType, "", current + 1);
    }
}

