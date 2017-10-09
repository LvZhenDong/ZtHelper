package com.egr.drillinghelper.presenter;

import com.egr.drillinghelper.bean.response.Store;
import com.egr.drillinghelper.contract.PartsContract;
import com.egr.drillinghelper.model.PartsModelImpl;
import com.egr.drillinghelper.mvp.BasePresenter;
import com.egr.drillinghelper.mvp.IModel;
import com.egr.drillinghelper.utils.CollectionUtil;

/**
 * author lzd
 * date 2017/9/26 16:41
 * 类描述：
 */

public class PartsPresenterImpl extends BasePresenter<PartsContract.View,
        PartsModelImpl> implements PartsContract.Presenter {
    int current;

    @Override
    protected IModel createModel() {
        return new PartsModelImpl(this);
    }

    @Override
    public void getPartsList() {
        current = 1;
        mModel.getPartsList(current);
    }

    @Override
    public void loadMore() {
        mModel.getPartsList(current + 1);
    }

    @Override
    public void getPartsListSuccess(Store store) {
        if (store == null || CollectionUtil.isListEmpty(store.getRecords())) {
            getView().noMoreData();
        } else {
            current = store.getCurrent();
            getView().getPartsListSuccess(store);
        }

    }


}
