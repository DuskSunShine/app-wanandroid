package com.scy.wanandroid.contract;

import com.scy.wanandroid.base.AbsPresenter;
import com.scy.wanandroid.base.AbsView;
import com.scy.wanandroid.entity.WxArticlesDetail;

/**
 * Created by SCY on 2018/11/16 at 15:03.
 */
public interface WxArticlesDetailContract {

    interface View extends AbsView{

        /**
         * 展示数据
         * @param wxArticlesDetail
         * @param isFirstLoad
         */
        void showWxArticleDetail(WxArticlesDetail wxArticlesDetail,boolean isFirstLoad);
    }

    interface Presenter extends AbsPresenter<View>{

        /**
         * 查看某个公众号历史数据
         * @param wxArticleID
         * @param isFirstLoad
         */
    void getWxArticleDetail(int wxArticleID,boolean isFirstLoad);

    void loadMore();

    void refresh();

    }
}
