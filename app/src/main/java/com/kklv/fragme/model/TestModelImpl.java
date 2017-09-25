package com.kklv.fragme.model;

import com.kklv.fragme.contract.TextContract;
import com.kklv.fragme.mvp.BaseModel;
import com.kklv.fragme.presenter.TestPresenterImpl;

/**
 * Created by lvzhendong on 2017/9/25.
 */

public class TestModelImpl extends BaseModel<TestPresenterImpl>
    implements TextContract.Model{
    public TestModelImpl(TestPresenterImpl testPresenter) {
        super(testPresenter);
    }
}
