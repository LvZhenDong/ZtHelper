package com.egr.drillinghelper.presenter;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.bean.response.Store;
import com.egr.drillinghelper.bean.response.StoreMore;
import com.egr.drillinghelper.common.UserManager;
import com.egr.drillinghelper.contract.PartsContract;
import com.egr.drillinghelper.model.PartsModelImpl;
import com.egr.drillinghelper.mvp.BasePresenter;
import com.egr.drillinghelper.mvp.IModel;
import com.egr.drillinghelper.ui.adapter.PartsAdapter;
import com.egr.drillinghelper.utils.CollectionUtil;

import java.util.List;

import static android.R.attr.data;
import static com.pgyersdk.c.a.m;

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
        mModel.getMall();
    }

    @Override
    public void loadMore() {
        mModel.getPartsList(current + 1);
    }

    @Override
    public void getPartsListSuccess(Store store) {
        if (CollectionUtil.isListEmpty(store.getRecords()) && store.getCurrent() != 1) {
            getView().noMoreData();
        } else {
            current = store.getCurrent();
            getView().getPartsListSuccess(store);
        }
    }

    @Override
    public void getPastsFail(String msg) {
        if(UserManager.isLogined())
            getView().getPastsFail(msg);
    }


    @Override
    public void getMallSuccess() {

        mModel.getPartsList(current);
    }


}
