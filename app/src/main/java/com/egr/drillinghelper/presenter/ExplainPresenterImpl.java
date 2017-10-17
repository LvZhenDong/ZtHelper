package com.egr.drillinghelper.presenter;

import com.egr.drillinghelper.bean.base.BasePage;
import com.egr.drillinghelper.bean.response.Explain;
import com.egr.drillinghelper.common.UserManager;
import com.egr.drillinghelper.contract.ExplainContract;
import com.egr.drillinghelper.model.ExplainModelImpl;
import com.egr.drillinghelper.mvp.BasePresenter;
import com.egr.drillinghelper.mvp.IModel;
import com.egr.drillinghelper.utils.CollectionUtil;

/**
 * author lzd
 * date 2017/9/26 16:41
 * 类描述：
 */

public class ExplainPresenterImpl extends BasePresenter<ExplainContract.View,
        ExplainModelImpl> implements ExplainContract.Presenter {
    int current;

    @Override
    protected IModel createModel() {
        return new ExplainModelImpl(this);
    }

    @Override
    public void getExplainCache() {
        mModel.getExplainCache();
    }

    @Override
    public void getExplainList() {
        current = 1;
        mModel.getExplainList(current);
    }

    @Override
    public void loadMore() {
        mModel.getExplainList(current + 1);
    }

    @Override
    public void getExplainListSuccess(BasePage<Explain> data) {
        if (data == null || CollectionUtil.isListEmpty(data.getRecords())){
            getView().noMoreData();
        } else{
            current = data.getCurrent();
            getView().getExplainListSuccess(data);
        }

    }

    @Override
    public void getExplainFail(String msg) {
        if(UserManager.isLogined())
            getView().getExplainFail(msg);
    }
}
