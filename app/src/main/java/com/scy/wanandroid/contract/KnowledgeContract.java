package com.scy.wanandroid.contract;

import com.scy.wanandroid.base.AbsPresenter;
import com.scy.wanandroid.base.AbsView;
import com.scy.wanandroid.entity.KnowledgeBean;

/**
 * Created by SCY on 2018/10/25 at 14:37.
 */
public interface KnowledgeContract {
    interface KnowledgeView extends AbsView{
        /**
         * 展示数据
         * @param knowledgeBean
         */
        void showKnowledge(KnowledgeBean knowledgeBean);



    }

    interface Presenter extends AbsPresenter<KnowledgeView> {

        /**
         * 刷新
         */
        void refresh();
    }
}
