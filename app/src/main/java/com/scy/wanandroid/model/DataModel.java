package com.scy.wanandroid.model;

import com.scy.wanandroid.entity.BannerBean;
import com.scy.wanandroid.entity.HomeArticleBean;
import com.scy.wanandroid.entity.RegisterBean;
import com.scy.wanandroid.http.HttpManager;
import com.scy.wanandroid.http.HttpService;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by SCY on 2018/10/19 at 9:59.
 */
public class DataModel implements HttpService {

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
        return HttpManager.create().register(userName, password, repassword);
    }

    @Override
    public Observable<BannerBean> getBannerData() {
        return HttpManager.create().getBannerData();
    }

    @Override
    public Observable<HomeArticleBean> getHomeArticleList(int page) {
        return HttpManager.create().getHomeArticleList(page);
    }

    @Override
    public Call<ResponseBody> record(String personId, String personName, String visitTimeStr, String cameraId, String faceUrl, String personType) {
        return null;
    }
}
