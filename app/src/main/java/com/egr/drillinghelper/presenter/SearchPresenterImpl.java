package com.egr.drillinghelper.presenter;

import com.egr.drillinghelper.bean.base.BasePage;
import com.egr.drillinghelper.bean.response.Explain;
import com.egr.drillinghelper.bean.response.KnowCatalog;
import com.egr.drillinghelper.bean.response.Parts;
import com.egr.drillinghelper.contract.SearchContract;
import com.egr.drillinghelper.model.SearchModelImpl;
import com.egr.drillinghelper.mvp.BasePresenter;
import com.egr.drillinghelper.mvp.IModel;
import com.egr.drillinghelper.utils.CollectionUtil;

import java.util.ArrayList;
import java.util.List;

import static com.egr.drillinghelper.ui.activity.SearchActivity.SEARCH_TYPE_EXPLAIN;
import static com.egr.drillinghelper.ui.activity.SearchActivity.SEARCH_TYPE_KNOWLEDGE;
import static com.egr.drillinghelper.ui.activity.SearchActivity.SEARCH_TYPE_PARTS;

/**
 * author lzd
 * date 2017/9/26 16:41
 * 类描述：
 */

public class SearchPresenterImpl extends BasePresenter<SearchContract.View,
        SearchModelImpl> implements SearchContract.Presenter {
    int current;
    String keyword;
    int type;
    @Override
    protected IModel createModel() {
        return new SearchModelImpl(this);
    }

    @Override
    public void search(String keyword, int type) {
        current=1;
        this.keyword=keyword;
        this.type=type;
        startSearch(keyword,type,current);
    }

    void startSearch(String keyword,int type,int current){
        switch (type) {
            case SEARCH_TYPE_EXPLAIN:
                mModel.searchExplain(keyword,current);
                break;
            case SEARCH_TYPE_KNOWLEDGE:
                mModel.searchKnow(keyword,current);
                break;
            case SEARCH_TYPE_PARTS:
                mModel.searchParts(keyword,current);
                break;
        }
    }

    @Override
    public void loadMore() {
        startSearch(keyword,type,current+1);
    }

    @Override
    public void searchKnowSuccess(BasePage<KnowCatalog> data) {
        if(CollectionUtil.isListEmpty(data.getRecords())){
            getView().noMoreData();
        }else {
            current=data.getCurrent();
            getView().searchKnowSuccess(data.getRecords(),
                    getKnowTitles(data.getRecords()));
        }

    }

    @Override
    public void searchPartsSuccess(BasePage<Parts> data) {
        if(CollectionUtil.isListEmpty(data.getRecords())){
            getView().noMoreData();
        }else {
            current=data.getCurrent();
            getView().searchParts(data.getRecords(),
                    getPartsTitles(data.getRecords()));
        }

    }

    @Override
    public void searchExplainCatalogSuccess(BasePage<Explain> data) {
        if(CollectionUtil.isListEmpty(data.getRecords())){
            getView().noMoreData();
        }else {
            current=data.getCurrent();
            getView().searchExplainCatalog(data.getRecords(),
                    getExplainTitles(data.getRecords()));
        }

    }

    private List<String> getKnowTitles(List<KnowCatalog> list) {
        List<String> titles = new ArrayList<>();
        for (KnowCatalog item : list) {
            titles.add(item.getTitle());
        }

        return titles;
    }

    private List<String> getPartsTitles(List<Parts> list){
        List<String> titles = new ArrayList<>();
        for (Parts item : list) {
            titles.add(item.getName());
        }

        return titles;
    }

    private List<String> getExplainTitles(List<Explain> list){
        List<String> titles = new ArrayList<>();
        for (Explain item : list) {
            titles.add(item.getTitle());
        }

        return titles;
    }


}
