package com.egr.drillinghelper.presenter;

import com.egr.drillinghelper.contract.PartsDetailContract;
import com.egr.drillinghelper.model.PartsDetailModelImpl;
import com.egr.drillinghelper.mvp.BasePresenter;
import com.egr.drillinghelper.mvp.IModel;

/**
 * author lzd
 * date 2017/9/26 16:41
 * 类描述：
 */

public class PartsDetailPresenterImpl extends BasePresenter<PartsDetailContract.View,
        PartsDetailModelImpl> implements PartsDetailContract.Presenter{
    @Override
    protected IModel createModel() {
        return new PartsDetailModelImpl(this);
    }

    @Override
    public void getContent(String id) {
        mModel.getContent(id);
    }
}
