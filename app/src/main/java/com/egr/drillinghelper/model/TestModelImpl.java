package com.egr.drillinghelper.model;

import com.egr.drillinghelper.contract.TextContract;
import com.egr.drillinghelper.mvp.BaseModel;
import com.egr.drillinghelper.presenter.TestPresenterImpl;

/**
 * Created by lvzhendong on 2017/9/25.
 */

public class TestModelImpl extends BaseModel<TestPresenterImpl>
    implements TextContract.Model{
    public TestModelImpl(TestPresenterImpl testPresenter) {
        super(testPresenter);
    }
}
