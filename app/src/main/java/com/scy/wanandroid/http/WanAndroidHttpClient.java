package com.scy.wanandroid.http;


import com.scy.wanandroid.constants.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by SCY on 2018/10/16 at 16:51.
 */
public class WanAndroidHttpClient {
    private static HttpService httpService = null;

    public static HttpService create() {
        if (null == httpService) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true)
                    .build();
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Constants.BASEURL)
                    .build();
            httpService = retrofit.create(HttpService.class);
        }
        return httpService;
    }
}
