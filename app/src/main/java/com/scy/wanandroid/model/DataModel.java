package com.scy.wanandroid.model;

import com.scy.wanandroid.base.BaseResponse;
import com.scy.wanandroid.entity.BannerBean;
import com.scy.wanandroid.entity.HomeArticleBean;
import com.scy.wanandroid.entity.RegisterBean;
import com.scy.wanandroid.http.ApiManager;
import com.scy.wanandroid.http.ApiService;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by SCY on 2018/10/19 at 9:59.
 */
public class DataModel implements ApiService {

    private DataModel() {
    }

    private static volatile DataModel dataModel = null;

    public static DataModel getDataModel() {

        if (dataModel == null) {
            synchronized (DataModel.class) {
                dataModel = new DataModel();
            }
        }
        return dataModel;
    }

    @Override
    public Observable<RegisterBean> register(String userName, String password, String repassword) {
        return ApiManager.create().register(userName, password, repassword);
    }

    @Override
    public Observable<BaseResponse<BannerBean>> getBannerData() {
        return ApiManager.create().getBannerData();
    }

    @Override
    public Observable<BaseResponse<HomeArticleBean>> getHomeArticleList(int page) {
        return ApiManager.create().getHomeArticleList(page);
    }

    @Override
    public Call<ResponseBody> record(String personId, String personName, String visitTimeStr, String cameraId, String faceUrl, String personType) {
        return null;
    }
}
