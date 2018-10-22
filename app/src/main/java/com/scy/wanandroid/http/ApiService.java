package com.scy.wanandroid.http;


import com.scy.wanandroid.base.BaseResponse;
import com.scy.wanandroid.entity.BannerBean;
import com.scy.wanandroid.entity.HomeArticleBean;
import com.scy.wanandroid.entity.RegisterBean;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by SCY on 2018/10/16 at 16:42.
 */
public interface ApiService {

    /**
     * 注册
     * @param userName
     * @param password
     * @param repassword
     * @return
     */
    @POST("/user/register")
    Observable<RegisterBean> register(@Query("username") String userName,
                                      @Query("password") String password,
                                      @Query("repassword") String repassword);

    /**
     * 首页banner
     * @return
     */
    @GET("/banner/json")
    Observable<BaseResponse<BannerBean>> getBannerData();

    /**
     * 获取首页文章列表
     *
     * @param page 页数
     * @return 首页文章列表数据
     */
    @GET("article/list/{num}/json")
    Observable<BaseResponse<HomeArticleBean>> getHomeArticleList(@Path("page") int page);


    @POST("/iwg-welcome/app/visit/record/save")
    Call<ResponseBody> record(@Query("personId") String personId,
                              @Query("personName") String personName,
                              @Query("visitTimeStr") String visitTimeStr,
                              @Query("cameraId") String cameraId,
                              @Query("faceUrl") String faceUrl,
                              @Query("personType") String personType);

}
