package com.scy.wanandroid.http;


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

    @POST("/user/register")
    Observable<AlgoCameraConfig> register(@Query("username" String userName,
            @Query("password") Stirng password,@Query("repassword") String repassword));

    @POST("/iwg-welcome/app/person/findAllList")
    Observable<PersonData> getAllPersons();

    @POST("/iwg-welcome/app/attendencemark/findList")
    Observable<WelcomeWords> getWelcomeWords();

    @POST("/iwg-welcome/app/visit/record/save")
    Call<ResponseBody> record(@Query("personId") String personId,
                              @Query("personName") String personName,
                              @Query("visitTimeStr") String visitTimeStr,
                              @Query("cameraId") String cameraId,
                              @Query("faceUrl") String faceUrl,
                              @Query("personType") String personType);

}
