package com.kklv.fragme.presenter;

import com.kklv.fragme.contract.TextContract;
import com.kklv.fragme.model.TestModelImpl;
import com.kklv.fragme.mvp.BasePresenter;
import com.kklv.fragme.mvp.IModel;

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
