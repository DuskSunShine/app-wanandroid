package com.scy.wanandroid.contract;

import com.scy.wanandroid.base.AbsPresenter;
import com.scy.wanandroid.base.AbsView;
import com.scy.wanandroid.entity.KnowledgeDetail;

/**
 * Created by SCY on 2018/11/15 at 15:32.
 */
public interface KnowledgeDetailContract {

    interface View extends AbsView{

        /**
         * 展示数据
         * @param knowledgeDetail
         */
        void showKnowledgeDetail(KnowledgeDetail knowledgeDetail,boolean isFirstLoad);
    }

    interface Presenter extends AbsPresenter<View>{

        /**
         *  获取体系各tab文章
         *
         * @param cid 分类的id
         * @param isFirstLoad 是否第一次加载数据
         */
        void getDetailKnowledge(int cid,boolean isFirstLoad);

        void refresh(int cid);

        void loadMore(int cid);
    }
}
