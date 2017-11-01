package com.egr.drillinghelper.model;

import android.text.TextUtils;

import com.bumptech.glide.Glide;
import com.egr.drillinghelper.R;
import com.egr.drillinghelper.api.NetApi;
import com.egr.drillinghelper.api.error.EObserver;
import com.egr.drillinghelper.api.error.ResponseThrowable;
import com.egr.drillinghelper.bean.base.BasePage;
import com.egr.drillinghelper.bean.response.Explain;
import com.egr.drillinghelper.bean.response.KnowCatalog;
import com.egr.drillinghelper.common.MyConstants;
import com.egr.drillinghelper.contract.KnowContract;
import com.egr.drillinghelper.factory.APIServiceFactory;
import com.egr.drillinghelper.factory.TransformersFactory;
import com.egr.drillinghelper.mvp.BaseMVPFragment;
import com.egr.drillinghelper.mvp.BaseModel;
import com.egr.drillinghelper.presenter.KnowPresenterImpl;
import com.egr.drillinghelper.utils.CacheUtils;
import com.egr.drillinghelper.utils.CollectionUtil;
import com.egr.drillinghelper.utils.EgrTarget;
import com.egr.drillinghelper.utils.FileUtils;
import com.egr.drillinghelper.utils.GlideUtils;
import com.egr.drillinghelper.utils.NetworkUtils;
import com.egr.drillinghelper.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;

import static com.egr.drillinghelper.api.error.ERROR.TIMEOUT_ERROR;

/**
 * author lzd
 * date 2017/9/26 16:40
 * 类描述：
 */

public class KnowModelImpl extends BaseModel<KnowPresenterImpl> implements KnowContract.Model {
    private NetApi api;

    public KnowModelImpl(KnowPresenterImpl presenter) {
        super(presenter);
        api = APIServiceFactory.getInstance().createService();
    }

    @Override
    public void getKnowList(final String keyword, int current) {
        if (NetworkUtils.isNetworkConnected(getContext())) {
            api.knowList(keyword,current + "")
                    .compose(TransformersFactory.<BasePage<Explain>>commonTransformer((BaseMVPFragment) presenter.getView()))
                    .subscribe(new EObserver<BasePage<Explain>>() {
                        @Override
                        public void onError(ResponseThrowable e, String eMsg) {
                            if (e.code == TIMEOUT_ERROR)
                                showCache(keyword);
                            else
                                presenter.getView().getKnowFail(eMsg);
                        }

                        @Override
                        public void onComplete(@NonNull BasePage<Explain> data) {
                            presenter.getKnowListSuccess(data);
                        }
                    });
        }else {
            showCache(keyword);
        }
    }
    private void showCache(String keyword) {
        try {
            presenter.getView().showKnowCache(searchExplainsInCache(keyword));
        } catch (Exception e) {
            presenter.getView().getKnowFail(getContext().getString(R.string.net_error));
        }
    }

    private List<Explain> searchExplainsInCache(String keyword) throws Exception {
        List<Explain> searchResult = new ArrayList<>();
        List<Explain> cacheList = CacheUtils.getKnows();
        if (TextUtils.isEmpty(keyword) || CollectionUtil.isListEmpty(cacheList)) return cacheList;
        for (Explain item : cacheList) {
            if (item.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                searchResult.add(item);
            }
        }

        return searchResult;
    }

    @Override
    public void getKnowCache() {
        api.getKnowCache()
                .compose(TransformersFactory.<List<Explain>>commonTransformer((BaseMVPFragment) presenter.getView()))
                .subscribe(new EObserver<List<Explain>>() {
                    @Override
                    public void onError(ResponseThrowable e, String eMsg) {

                    }

                    @Override
                    public void onComplete(@NonNull List<Explain> data) {
                        CacheUtils.saveKnows(data);
                        saveImg();
                    }
                });
    }

    private void saveImg(){
        try {
            List<Explain> knows=CacheUtils.getKnows();
            for (Explain item:knows) {
                if(!TextUtils.isEmpty(item.getPhoto())){
                    GlideUtils.perLoadImg(getContext(),item.getPhoto());
                }
                List<KnowCatalog> knowCatalogs=item.getKnows();
                for(KnowCatalog knowCatalog:knowCatalogs){
                    String content=knowCatalog.getContent();
                    List<String> imgs= StringUtils.match(content,"img","src");
                    if(!CollectionUtil.isListEmpty(imgs)){  //下载图片
                        for (String path:imgs) {
                            String[] strs=path.split("/");
                            String name=strs[strs.length-1];
                            if (!TextUtils.isEmpty(name) && !FileUtils.fileExists(MyConstants.PATH + name))
                                Glide.with(getContext()).load(path).into(new EgrTarget(name));
                        }

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
