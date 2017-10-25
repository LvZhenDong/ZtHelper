package com.egr.drillinghelper.presenter;

import com.egr.drillinghelper.bean.base.BasePage;
import com.egr.drillinghelper.bean.response.ContactUs;
import com.egr.drillinghelper.bean.response.Video;
import com.egr.drillinghelper.contract.VideoPartContract;
import com.egr.drillinghelper.model.VideoPartModelImpl;
import com.egr.drillinghelper.mvp.BasePresenter;
import com.egr.drillinghelper.mvp.IModel;
import com.egr.drillinghelper.utils.CollectionUtil;

/**
 * author lzd
 * date 2017/9/26 16:41
 * 类描述：
 */

public class VideoPartPresenterImpl extends BasePresenter<VideoPartContract.View,
        VideoPartModelImpl> implements VideoPartContract.Presenter{
    int current;
    String keyword;
    @Override
    protected IModel createModel() {
        return new VideoPartModelImpl(this);
    }


    @Override
    public void getVideoList(String keyword) {
        current = 1;
        this.keyword=keyword;
        mModel.getVideoList(keyword,current);
    }

    @Override
    public void loadMore() {
        mModel.getVideoList(keyword,current+1);
    }

    @Override
    public void getVideoListSuc(BasePage<Video> data) {
        if (CollectionUtil.isListEmpty(data.getRecords()) && data.getCurrent() != 1){
            getView().noMoreData();
        } else{
            current = data.getCurrent();
            getView().getVideoListSuc(data);
        }
    }
}
