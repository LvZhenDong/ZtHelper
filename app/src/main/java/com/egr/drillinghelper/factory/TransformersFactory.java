package com.egr.drillinghelper.factory;

import android.app.Activity;

import com.egr.drillinghelper.api.error.HandleFuc;
import com.egr.drillinghelper.api.error.HttpResponseFunc;
import com.egr.drillinghelper.api.error.NullResponseFuc;
import com.egr.drillinghelper.bean.base.BaseResponseBean;
import com.egr.drillinghelper.bean.response.NullBodyResponse;
import com.egr.drillinghelper.mvp.BaseMVPFragment;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;

import cn.nekocode.rxlifecycle.RxLifecycle;
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
                        .io());
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

    /**
     * 适用于返回body为null的情况
     *
     * @param activity
     * @return
     */
    public static ObservableTransformer<BaseResponseBean<NullBodyResponse>, NullBodyResponse>
    nullBodyTransformer(final BaseMVPActivity activity) {
        return new ObservableTransformer<BaseResponseBean<NullBodyResponse>, NullBodyResponse>() {
            @Override
            public ObservableSource<NullBodyResponse> apply(@NonNull
                                                                    Observable<BaseResponseBean<NullBodyResponse>> upstream) {
                return upstream.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers
                        .io())
                        .map(new NullResponseFuc())
                        .onErrorResumeNext(new HttpResponseFunc<NullBodyResponse>())
                        .compose(activity.<NullBodyResponse>bindUntilEvent(ActivityEvent.DESTROY));
            }
        };
    }

    /**
     * 适用于返回body为null的情况
     *
     * @param fragment
     * @return
     */
    public static ObservableTransformer<BaseResponseBean<NullBodyResponse>, NullBodyResponse>
    nullBodyTransformer(final BaseMVPFragment fragment) {
        return new ObservableTransformer<BaseResponseBean<NullBodyResponse>, NullBodyResponse>() {
            @Override
            public ObservableSource<NullBodyResponse> apply(@NonNull
                                                                    Observable<BaseResponseBean<NullBodyResponse>> upstream) {
                return upstream.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers
                        .io())
                        .map(new NullResponseFuc())
                        .onErrorResumeNext(new HttpResponseFunc<NullBodyResponse>())
                        .compose(fragment.<NullBodyResponse>bindUntilEvent(FragmentEvent.DESTROY));
            }
        };
    }

    /**
     * <p>zhihu rxlifecycle</p>
     * 适用于单个请求
     *
     * @param activity
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<BaseResponseBean<T>, T> commonTransformer(final
                                                                                      Activity
                                                                                              activity) {
        return new ObservableTransformer<BaseResponseBean<T>, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<BaseResponseBean<T>> upstream) {
                return upstream.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers
                        .io())
                        .map(new HandleFuc<T>()).onErrorResumeNext(new HttpResponseFunc<T>())
                        .compose(RxLifecycle.bind(activity).<T>withObservable());
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
                return upstream.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers
                        .io())
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