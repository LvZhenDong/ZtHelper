package com.egr.drillinghelper.api;

import com.egr.drillinghelper.bean.base.BaseResponseBean;
import com.egr.drillinghelper.bean.response.ForgetPswdResponse;
import com.egr.drillinghelper.bean.response.LoginResponse;
import com.egr.drillinghelper.bean.response.RegisterResponse;
import com.egr.drillinghelper.bean.response.UserInfo;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface NetApi {

    /**
     * 登录
     * @param options
     * @return
     */
    @FormUrlEncoded
    @POST("user/login")
    Observable<BaseResponseBean<LoginResponse>> login(@FieldMap HashMap<String,Object> options);


    /**
     * 注册
     * @param options
     * @return
     */
    @FormUrlEncoded
    @POST("user/register")
    Observable<BaseResponseBean<RegisterResponse>> register(@FieldMap HashMap<String,Object> options);

    /**
     * 忘记密码
     * @param options
     * @return
     */
    @FormUrlEncoded
    @POST("user/forget")
    Observable<BaseResponseBean<ForgetPswdResponse>> forget(@FieldMap HashMap<String,Object> options);

    /**
     * 获取验证码
     * @param type 注册：register
     * @param phone
     * @return
     */
    @GET("common/smscode")
    Observable<BaseResponseBean<String>> getVerCode(@Query("templateType")String type,
                                            @Query("phone")String phone);

    /**
     *
     * @return
     */
    @GET("user/info")
    Observable<BaseResponseBean<UserInfo>> userInfo();

}
