package com.egr.drillinghelper.mvp;

import android.content.Context;
import android.support.v4.app.Fragment;


/**
 * Created by Ymmmsick on 5/12/17.
 */

public class BaseModel<P extends BasePresenter> implements IModel {
    protected P presenter;

    public BaseModel(P p) {
        presenter = p;
    }

    protected Context getContext() {
        if (presenter.getView() instanceof BaseMVPFragment)
            return ((Fragment) presenter.getView()).getActivity();
        else
            return (Context) presenter.getView();
    }
}
