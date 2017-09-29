package com.egr.drillinghelper.api;

import com.egr.drillinghelper.bean.base.BaseResponseBean;
import com.egr.drillinghelper.bean.response.ContactUs;
import com.egr.drillinghelper.bean.response.ForgetPswdResponse;
import com.egr.drillinghelper.bean.response.LoginResponse;
import com.egr.drillinghelper.bean.response.RegisterResponse;
import com.egr.drillinghelper.bean.response.Store;
import com.egr.drillinghelper.bean.response.UserInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface NetApi {

    /**
     * 登录
     *
     * @param options
     * @return
     */
    @FormUrlEncoded
    @POST("user/login")
    Observable<BaseResponseBean<LoginResponse>> login(@FieldMap HashMap<String, Object> options);


    /**
     * 注册
     *
     * @param options
     * @return
     */
    @FormUrlEncoded
    @POST("user/register")
    Observable<BaseResponseBean<RegisterResponse>> register(@FieldMap HashMap<String, Object> options);

    /**
     * 忘记密码
     *
     * @param options
     * @return
     */
    @FormUrlEncoded
    @POST("user/forget")
    Observable<BaseResponseBean<ForgetPswdResponse>> forget(@FieldMap HashMap<String, Object> options);

    /**
     * 获取验证码
     *
     * @param type  注册：register
     * @param phone
     * @return
     */
    @GET("common/smscode")
    Observable<BaseResponseBean<String>> getVerCode(@Query("templateType") String type,
                                                    @Query("phone") String phone);

    /**
     * 个人中心数据
     *
     * @return
     */
    @GET("user/info")
    Observable<BaseResponseBean<UserInfo>> userInfo();

    /**
     * 联系我们
     *
     * @return
     */
    @GET("contact/list")
    Observable<BaseResponseBean<ContactUs>> contactList();

    /**
     * 配件列表
     * @param options
     * @return
     */
    @GET("store/list")
    Observable<BaseResponseBean<List<Store>>> storeList(@QueryMap HashMap<String, Object> options);

    @Multipart
    @POST("user/photo")
    Observable<BaseResponseBean<RegisterResponse>> userPhoto(@PartMap Map<String, RequestBody> photo);

}
