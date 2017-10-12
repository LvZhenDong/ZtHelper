package com.egr.drillinghelper.presenter;


import com.egr.drillinghelper.bean.base.BasePage;
import com.egr.drillinghelper.bean.response.Explain;
import com.egr.drillinghelper.contract.KnowContract;
import com.egr.drillinghelper.model.KnowModelImpl;
import com.egr.drillinghelper.mvp.BasePresenter;
import com.egr.drillinghelper.mvp.IModel;
import com.egr.drillinghelper.utils.CollectionUtil;

/**
 * author lzd
 * date 2017/9/26 16:41
 * 类描述：
 */

public class KnowPresenterImpl extends BasePresenter<KnowContract.View,
        KnowModelImpl> implements KnowContract.Presenter {
    int current;

    @Override
    protected IModel createModel() {
        return new KnowModelImpl(this);
    }

    @Override
    public void getKnowCache() {
        mModel.getKnowCache();
    }

    @Override
    public void getKnowList() {
        current = 1;
        mModel.getKnowList(current);
    }

    @Override
    public void loadMore() {
        mModel.getKnowList(current + 1);
    }

    @Override
    public void getKnowListSuccess(BasePage<Explain> data) {
        if (data == null || CollectionUtil.isListEmpty(data.getRecords())){
            getView().noMoreData();
        } else{
            current = data.getCurrent();
            getView().getKnowListSuccess(data);
        }

    }
}
