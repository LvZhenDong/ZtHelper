package com.egr.drillinghelper.presenter;

import com.egr.drillinghelper.bean.response.Explain;
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
    public void getExplainList() {
        current = 1;
        mModel.getExplainList(current);
    }

    @Override
    public void loadMore() {
        mModel.getExplainList(current + 1);
    }

    @Override
    public void getExplainListSuccess(Explain explain) {
        if (explain == null || CollectionUtil.isListEmpty(explain.getRecords())){
            getView().noMoreData();
        } else{
            current = explain.getCurrent();
            getView().getExplainListSuccess(explain);
        }

    }
}
