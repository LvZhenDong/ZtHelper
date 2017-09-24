package com.kklv.fragme.factory;

import android.app.Activity;

import com.kklv.fragme.api.error.HandleFuc;
import com.kklv.fragme.api.error.HttpResponseFunc;
import com.kklv.fragme.bean.base.BaseResponseBean;
import com.kklv.fragme.ui.base.BaseMVPActivity;
import com.trello.rxlifecycle2.android.ActivityEvent;

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
                return upstream.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
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
                return (Observable<T>) upstream.map(new HandleFuc<T>()).onErrorResumeNext(new HttpResponseFunc<T>());
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
    public static <T> ObservableTransformer<BaseResponseBean<T>, T> commonTransformer(final BaseMVPActivity activity) {
        return new ObservableTransformer<BaseResponseBean<T>, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<BaseResponseBean<T>> upstream) {
                return upstream.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                        .map(new HandleFuc<T>()).onErrorResumeNext(new HttpResponseFunc<T>())
                        .compose(activity.<T>bindUntilEvent(ActivityEvent.DESTROY));
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
    public static <T> ObservableTransformer<BaseResponseBean<T>, T> commonTransformer(final Activity activity) {
        return new ObservableTransformer<BaseResponseBean<T>, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<BaseResponseBean<T>> upstream) {
                return upstream.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                        .map(new HandleFuc<T>()).onErrorResumeNext(new HttpResponseFunc<T>())
                        .compose(RxLifecycle.bind(activity).<T>withObservable());
            }
        };
    }

//    /**
//     * 适用于单个请求
//     *
//     * @param fragment
//     * @param <T>
//     * @return
//     */
//    public static <T> ObservableTransformer<BaseResponseBean<T>, T> commonTransformer(final BaseMVPFragment fragment) {
//        return new ObservableTransformer<BaseResponseBean<T>, T>() {
//            @Override
//            public ObservableSource<T> apply(@NonNull Observable<BaseResponseBean<T>> upstream) {
//                return upstream.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
//                        .map(new HandleFuc<T>()).onErrorResumeNext(new HttpResponseFunc<T>())
//                        .compose(fragment.<T>bindUntilEvent(FragmentEvent.DESTROY));
//            }
//        };
//    }
//
//    /**
//     * 适用于两个请求顺序执行
//     *
//     * @param fragment
//     * @param <T>
//     * @return
//     */
//    public static <T> ObservableTransformer<T, T> otherTransformer(final BaseMVPFragment fragment) {
//        return new ObservableTransformer<T, T>() {
//            @Override
//            public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
//                return upstream.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
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
                return upstream.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                        .onErrorResumeNext(new HttpResponseFunc<T>())
                        .compose(activity.<T>bindUntilEvent(ActivityEvent.DESTROY));
            }
        };
    }
}