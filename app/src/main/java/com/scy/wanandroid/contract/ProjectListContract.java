package com.scy.wanandroid.contract;

import com.scy.wanandroid.base.AbsPresenter;
import com.scy.wanandroid.base.AbsView;
import com.scy.wanandroid.entity.ProjectList;

/**
 * @author: SCY
 * @date: 2019/5/27   15:53
 * @version:
 * @desc:
 */
public interface ProjectListContract {

    interface View extends AbsView{

        /**
         * 展示数据
         * @param projectList
         * @param isFirstLoad
         */
        void showProjectList(ProjectList projectList, boolean isFirstLoad);
    }


    interface Presenter extends AbsPresenter<View>{

        /**
         * 查看某个公众号历史数据
         * @param projectID 文章id
         * @param isFirstLoad 是否第一次进入加载
         */
        void getProjectList(int projectID,boolean isFirstLoad);

        void loadMore();

        void refresh();
    }
}
