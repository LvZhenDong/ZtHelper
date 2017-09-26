package com.egr.drillinghelper.mvp;

/**
 * @param <V>
 * @author Ymmmsick
 * @date 2017-05-12 09:28:56
 */
public interface IMvpBase<V extends IView> {
    V getMvpView();
}
