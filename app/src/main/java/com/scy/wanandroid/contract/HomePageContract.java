package com.scy.wanandroid.contract;

import com.scy.wanandroid.base.AbsPresenter;
import com.scy.wanandroid.base.AbsView;
import com.scy.wanandroid.entity.BannerBean;
import com.scy.wanandroid.entity.HomeArticleBean;

/**
 * Created by SCY on 2018/10/22 at 15:11.
 */
public interface HomePageContract {

    interface HomePageView extends AbsView{

        void showBanner(BannerBean bannerBean);

        void showHomeArticleList(HomeArticleBean homeArticleBean,boolean isRefresh);
    }

    interface Presenter extends AbsPresenter<HomePageView>{
        /**
         * banner 数据
         */
        void initBannerData();

        /**
         * 首页列表数据
         */
        void initHomeArticleData(int page,boolean isRefresh);

        /**
         * 刷新数据
         */
        void refresh();

        /**
         * 加载更多
         */
        void loadMore();
    }
}
