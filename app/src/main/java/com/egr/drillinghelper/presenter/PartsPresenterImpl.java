package com.egr.drillinghelper.presenter;

import com.egr.drillinghelper.bean.base.BasePage;
import com.egr.drillinghelper.bean.response.Store;
import com.egr.drillinghelper.common.UserManager;
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
    String keyword;

    @Override
    protected IModel createModel() {
        return new PartsModelImpl(this);
    }

    @Override
    public void getPartsList(String keyword) {
        this.keyword = keyword;
        current = 1;
        mModel.getMall();
    }

    @Override
    public void loadMore() {
        mModel.getPartsList(keyword, current + 1);
    }

    @Override
    public void getPartsListSuccess(BasePage<Store> store) {
        if (CollectionUtil.isListEmpty(store.getRecords()) && store.getCurrent() != 1) {
            getView().noMoreData();
        } else {
            current = store.getCurrent();
            getView().getPartsListSuccess(store);
        }
    }

    @Override
    public void getPastsFail(String msg) {
        if (UserManager.isLogined())
            getView().getPastsFail(msg);
    }


    @Override
    public void getMallSuccess() {

        mModel.getPartsList(keyword, current);
    }

    @Override
    public void getPartsCache() {
        mModel.getPartsCache();
    }


}
