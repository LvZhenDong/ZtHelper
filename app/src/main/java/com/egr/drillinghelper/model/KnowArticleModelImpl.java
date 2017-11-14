package com.egr.drillinghelper.model;

import com.egr.drillinghelper.api.NetApi;
import com.egr.drillinghelper.api.error.EObserver;
import com.egr.drillinghelper.api.error.ResponseThrowable;
import com.egr.drillinghelper.bean.base.BaseResponseBean;
import com.egr.drillinghelper.contract.KnowArticleContract;
import com.egr.drillinghelper.factory.APIServiceFactory;
import com.egr.drillinghelper.factory.TransformersFactory;
import com.egr.drillinghelper.mvp.BaseModel;
import com.egr.drillinghelper.presenter.KnowArticlePresenterImpl;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;

import io.reactivex.annotations.NonNull;

/**
 * author lzd
 * date 2017/9/26 16:40
 * 类描述：
 */

public class KnowArticleModelImpl extends BaseModel<KnowArticlePresenterImpl> implements KnowArticleContract.Model{
    public KnowArticleModelImpl(KnowArticlePresenterImpl presenter) {
        super(presenter);
        api = APIServiceFactory.getInstance().createService();
    }
    private NetApi api;
    @Override
    public void getContent(String id) {
        api.getKnowContent(id)
                .compose(TransformersFactory.emptyTrans((BaseMVPActivity) presenter.getView()))
                .subscribe(new EObserver<BaseResponseBean>() {
                    @Override
                    public void onError(ResponseThrowable e, String eMsg) {
                    }

                    @Override
                    public void onComplete(@NonNull BaseResponseBean data) {
                    }
                });
    }
}
