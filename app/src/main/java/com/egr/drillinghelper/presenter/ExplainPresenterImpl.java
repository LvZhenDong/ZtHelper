package com.egr.drillinghelper.presenter;

import com.egr.drillinghelper.bean.response.Explain;
import com.egr.drillinghelper.bean.response.Store;
import com.egr.drillinghelper.contract.ExplainContract;
import com.egr.drillinghelper.model.ExplainModelImpl;
import com.egr.drillinghelper.mvp.BasePresenter;
import com.egr.drillinghelper.mvp.IModel;

import static com.pgyersdk.c.a.g;

/**
 * author lzd
 * date 2017/9/26 16:41
 * 类描述：
 */

public class ExplainPresenterImpl extends BasePresenter<ExplainContract.View,
        ExplainModelImpl> implements ExplainContract.Presenter{
    int current;
    @Override
    protected IModel createModel() {
        return new ExplainModelImpl(this);
    }

    @Override
    public void getExplainList() {
        current = 1;
        mModel.getExplainList(current);
    }

    @Override
    public void loadMore() {
        mModel.getExplainList(current+1);
    }

    @Override
    public void getExplainListSuccess(Explain explain) {
        current = explain.getCurrent();
        getView().getExplainListSuccess(explain);
    }
}
