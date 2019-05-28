package com.scy.wanandroid.model;

import com.scy.wanandroid.entity.BannerBean;
import com.scy.wanandroid.entity.HomeArticleBean;
import com.scy.wanandroid.entity.KnowledgeBean;
import com.scy.wanandroid.entity.KnowledgeDetail;
import com.scy.wanandroid.entity.ProjectBean;
import com.scy.wanandroid.entity.ProjectList;
import com.scy.wanandroid.entity.RegisterBean;
import com.scy.wanandroid.entity.WXArticles;
import com.scy.wanandroid.entity.WxArticlesDetail;
import com.scy.wanandroid.http.WanAndroidHttpClient;
import com.scy.wanandroid.http.HttpService;

import io.reactivex.Observable;

/**
 * Created by SCY on 2018/10/19 at 9:59.
 */
public class DataManager implements HttpService {

    private DataManager() {
    }

    private static volatile DataManager dataManager = null;

    public static DataManager getDataManager() {

        if (dataManager == null) {
            synchronized (DataManager.class) {
                dataManager = new DataManager();
            }
        }
        return dataManager;
    }

    @Override
    public Observable<RegisterBean> register(String userName, String password, String repassword) {
        return WanAndroidHttpClient.create().register(userName, password, repassword);
    }

    @Override
    public Observable<BannerBean> getBannerData() {
        return WanAndroidHttpClient.create().getBannerData();
    }

    @Override
    public Observable<HomeArticleBean> getHomeArticleList(int page) {
        return WanAndroidHttpClient.create().getHomeArticleList(page);
    }

    @Override
    public Observable<KnowledgeBean> getKnowledgeList() {
        return WanAndroidHttpClient.create().getKnowledgeList();
    }

    @Override
    public Observable<KnowledgeDetail> getKnowledgeDetail(int page, int cid) {
        return WanAndroidHttpClient.create().getKnowledgeDetail(page,cid);
    }

    @Override
    public Observable<WXArticles> getWxArticles() {
        return WanAndroidHttpClient.create().getWxArticles();
    }

    @Override
    public Observable<WxArticlesDetail> getWxArticlesDetail(int wxArticleID, int page) {
        return WanAndroidHttpClient.create().getWxArticlesDetail(wxArticleID,page);
    }

    @Override
    public Observable<ProjectBean> getProjectTab() {
        return WanAndroidHttpClient.create().getProjectTab();
    }

    @Override
    public Observable<ProjectList> getProjectList(int page, int cid) {
        return WanAndroidHttpClient.create().getProjectList(page, cid);
    }

}
