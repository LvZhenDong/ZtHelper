package com.kklv.fragme.api;

import com.kklv.fragme.bean.base.BaseResponseBean;
import com.kklv.fragme.bean.request.LoginRequest;
import com.kklv.fragme.bean.response.NearbyShop;
import com.kklv.fragme.bean.response.Resident;
import com.kklv.fragme.bean.response.UserInfoBean;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface NetApi {

    @FormUrlEncoded
    @POST("jinen/api/account/login")
    Observable<BaseResponseBean<Resident>> login(@FieldMap HashMap<String, Object> options);
}
