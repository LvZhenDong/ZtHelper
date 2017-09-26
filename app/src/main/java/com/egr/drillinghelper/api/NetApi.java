package com.egr.drillinghelper.api;

import com.egr.drillinghelper.bean.base.BaseResponseBean;
import com.egr.drillinghelper.bean.response.Resident;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface NetApi {

    @FormUrlEncoded
    @POST("jinen/api/account/login")
    Observable<BaseResponseBean<Resident>> login(@FieldMap HashMap<String, Object> options);
}
