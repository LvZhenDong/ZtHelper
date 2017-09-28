package com.egr.drillinghelper.presenter;

import com.egr.drillinghelper.contract.PartsContract;
import com.egr.drillinghelper.contract.SearchContract;
import com.egr.drillinghelper.model.PartsModelImpl;
import com.egr.drillinghelper.model.SearchModelImpl;
import com.egr.drillinghelper.mvp.BasePresenter;
import com.egr.drillinghelper.mvp.IModel;

/**
 * author lzd
 * date 2017/9/26 16:41
 * 类描述：
 */

public class PartsPresenterImpl extends BasePresenter<PartsContract.View,
        PartsModelImpl> implements PartsContract.Presenter{
    @Override
    protected IModel createModel() {
        return new PartsModelImpl(this);
    }

    @Override
    public void getPartsList() {
        mModel.getPartsList();
    }
}
