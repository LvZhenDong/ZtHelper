package com.egr.drillinghelper.presenter;

import com.egr.drillinghelper.contract.TextContract;
import com.egr.drillinghelper.model.TestModelImpl;
import com.egr.drillinghelper.mvp.BasePresenter;
import com.egr.drillinghelper.mvp.IModel;

/**
 * Created by lvzhendong on 2017/9/25.
 */

public class TestPresenterImpl extends BasePresenter<TextContract.View,TestModelImpl> implements
        TextContract
        .Presendter{
    @Override
    protected IModel createModel() {
        return new TestModelImpl(this);
    }
}
