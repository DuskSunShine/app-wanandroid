package com.scy.wanandroid.contract;

import com.scy.wanandroid.base.AbsPresenter;
import com.scy.wanandroid.base.AbsView;
import com.scy.wanandroid.entity.WXArticles;

/**
 * Created by SCY on 2018/11/16 at 13:48.
 */
public interface WeChatSubContract {

    interface View extends AbsView{

        /**
         * 展示列表数据
         * @param wxArticles
         */
        void showWxArticlesList(WXArticles wxArticles);

    }

    interface Presenter extends AbsPresenter<View>{
        /**
         * 获取公众号列表
         */
        void getTabList();
    }
}
