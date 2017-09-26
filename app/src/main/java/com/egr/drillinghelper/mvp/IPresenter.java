package com.egr.drillinghelper.mvp;


/**
 * @param <V>
 * @author Ymmmsick
 * @date 2017-05-12 09:29:14
 */
public interface IPresenter<V extends IView> {
    void attachView(V view);

    void detachView(boolean retainInstance);
}
