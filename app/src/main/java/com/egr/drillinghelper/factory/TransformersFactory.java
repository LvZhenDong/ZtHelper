package com.egr.drillinghelper.factory;

import com.egr.drillinghelper.api.error.EmptyBodyFuc;
import com.egr.drillinghelper.api.error.HandleFuc;
import com.egr.drillinghelper.api.error.HttpResponseFunc;
import com.egr.drillinghelper.bean.base.BaseResponseBean;
import com.egr.drillinghelper.mvp.BaseMVPFragment;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Ymmmsick
 */
public class TransformersFactory {

    public static <T> ObservableTransformer<T, T> defaultSchedulers() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
                return upstream.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers
                        .newThread());
            }
        };
    }

    public static <T> ObservableTransformer<T, T> all_io() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
                return upstream.observeOn(Schedulers.io()).subscribeOn(Schedulers.io());
            }
        };
    }

    public static <T> ObservableTransformer<BaseResponseBean<T>, T> errorTransformer() {
        return new ObservableTransformer<BaseResponseBean<T>, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<BaseResponseBean<T>> upstream) {
                return (Observable<T>) upstream.map(new HandleFuc<T>()).onErrorResumeNext(new
                        HttpResponseFunc<T>());
            }
        };
    }

    /**
     * 适用于单个请求
     *
     * @param activity
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<BaseResponseBean<T>, T> commonTransformer(final
                                                                                      BaseMVPActivity activity) {
        return new ObservableTransformer<BaseResponseBean<T>, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<BaseResponseBean<T>> upstream) {
                return upstream.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers
                        .io())
                        .map(new HandleFuc<T>())
                        .onErrorResumeNext(new HttpResponseFunc<T>())
                        .compose(activity.<T>bindUntilEvent(ActivityEvent.DESTROY));
            }
        };
    }

    public static ObservableTransformer<BaseResponseBean, BaseResponseBean> emptyTrans(final BaseMVPActivity activity) {
        return new ObservableTransformer<BaseResponseBean, BaseResponseBean>() {
            @Override
            public ObservableSource<BaseResponseBean> apply(@NonNull Observable<BaseResponseBean> upstream) {
                return upstream
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .map(new EmptyBodyFuc())
                        .onErrorResumeNext(new HttpResponseFunc())
                        .compose(activity.<Void>bindUntilEvent(ActivityEvent.DESTROY));
            }
        };
    }

    public static ObservableTransformer<BaseResponseBean, BaseResponseBean> emptyTrans(final BaseMVPFragment activity) {
        return new ObservableTransformer<BaseResponseBean, BaseResponseBean>() {
            @Override
            public ObservableSource<BaseResponseBean> apply(@NonNull Observable<BaseResponseBean> upstream) {
                return upstream
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .map(new EmptyBodyFuc())
                        .onErrorResumeNext(new HttpResponseFunc())
                        .compose(activity.<Void>bindUntilEvent(FragmentEvent.DESTROY));
            }
        };
    }

    public static ObservableTransformer<BaseResponseBean, BaseResponseBean> emptyTrans() {
        return new ObservableTransformer<BaseResponseBean, BaseResponseBean>() {
            @Override
            public ObservableSource<BaseResponseBean> apply(@NonNull Observable<BaseResponseBean> upstream) {
                return upstream
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .map(new EmptyBodyFuc())
                        .onErrorResumeNext(new HttpResponseFunc());
            }
        };
    }


    /**
     * 适用于单个请求
     *
     * @param fragment
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<BaseResponseBean<T>, T> commonTransformer(final
                                                                                      BaseMVPFragment fragment) {
        return new ObservableTransformer<BaseResponseBean<T>, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<BaseResponseBean<T>> upstream) {
                return upstream.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                        .map(new HandleFuc<T>())
                        .onErrorResumeNext(new HttpResponseFunc<T>())
                        .compose(fragment.<T>bindUntilEvent(FragmentEvent.DESTROY));
            }
        };
    }
//
//    /**
//     * 适用于两个请求顺序执行
//     *
//     * @param fragment
//     * @param <T>
//     * @return
//     */
//    public static <T> ObservableTransformer<T, T> otherTransformer(final BaseMVPFragment
// fragment) {
//        return new ObservableTransformer<T, T>() {
//            @Override
//            public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
//                return upstream.observeOn(AndroidSchedulers.mainThread()).subscribeOn
// (Schedulers.io())
//                        .onErrorResumeNext(new HttpResponseFunc<T>())
//                        .compose(fragment.<T>bindUntilEvent(FragmentEvent.DESTROY));
//            }
//        };
//    }

    /**
     * 适用于两个请求顺序执行
     *
     * @param activity
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> otherTransformer(final BaseMVPActivity activity) {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
                return upstream.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers
                        .io())
                        .onErrorResumeNext(new HttpResponseFunc<T>())
                        .compose(activity.<T>bindUntilEvent(ActivityEvent.DESTROY));
            }
        };
    }


}