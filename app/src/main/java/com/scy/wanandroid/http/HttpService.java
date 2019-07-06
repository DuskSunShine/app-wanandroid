package com.scy.wanandroid.http;



import com.scy.wanandroid.entity.BannerBean;
import com.scy.wanandroid.entity.HomeArticleBean;
import com.scy.wanandroid.entity.KnowledgeBean;
import com.scy.wanandroid.entity.KnowledgeDetail;
import com.scy.wanandroid.entity.NavigationListData;
import com.scy.wanandroid.entity.ProjectBean;
import com.scy.wanandroid.entity.ProjectList;
import com.scy.wanandroid.entity.RegisterBean;
import com.scy.wanandroid.entity.WXArticles;
import com.scy.wanandroid.entity.WxArticlesDetail;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by SCY on 2018/10/16 at 16:42.
 */
public interface HttpService {

    /**
     * 注册
     *
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
     *
     * @return
     */
    @GET("/banner/json")
    Observable<BannerBean> getBannerData();

    /**
     * 获取首页文章列表
     *
     * @param page 页数
     * @return 首页文章列表数据
     */
    @GET("article/list/{page}/json")
    Observable<HomeArticleBean> getHomeArticleList(@Path("page") int page);

    /**
     *体系数据
     * @return
     */
    @GET("/tree/json")
    Observable<KnowledgeBean> getKnowledgeList();

    /**
     * 知识体系下的文章
     * http://www.wanandroid.com/article/list/0/json?cid=60
     *
     * @param page page num
     * @param cid second page id
     * @return 知识体系数据
     */
    @GET("/article/list/{page}/json")
    Observable<KnowledgeDetail> getKnowledgeDetail(@Path("page") int page,
                                                   @Query("cid") int cid);

    /**
     * 获取公众号列表
     * @return
     */
    @GET("/wxarticle/chapters/json")
    Observable<WXArticles> getWxArticles();

    /**
     * 查看某个公众号历史数据
     * @param wxArticleID
     * @param page
     * @return
     */
    @GET("/wxarticle/list/{wxArticleID}/{page}/json")
    Observable<WxArticlesDetail> getWxArticlesDetail(@Path("wxArticleID") int wxArticleID,
                                                     @Path("page") int page);

    /**
     * 项目分类
     * https://www.wanandroid.com/project/tree/json
     * @return
     */
    @GET("/project/tree/json")
    Observable<ProjectBean> getProjectTab();

    /**
     *  项目列表数据
     * 某一个分类下项目列表数据，分页展示
     * @param page
     * @param cid
     * @return
     */
    @GET("/project/list/{page}/json")
    Observable<ProjectList> getProjectList(@Path("page") int page,
                                           @Query("cid") int cid);

    /**
     * 导航数据
     * @return
     */
    @GET("/navi/json")
    Observable<NavigationListData> getNaviList();
}
