package com.scy.wanandroid.contract;

import com.scy.wanandroid.base.AbsPresenter;
import com.scy.wanandroid.base.AbsView;
import com.scy.wanandroid.entity.ProjectBean;

/**
 * @author: SCY
 * @date: 2019/5/27   14:52
 * @version:
 * @desc:
 */
public interface ProjectContract {

    interface ProjectView extends AbsView{

        /**
         * 项目的分类 tab
         * @param projectBean
         */
        void showProjectType(ProjectBean projectBean);
    }


    interface Presenter extends AbsPresenter<ProjectView>{

        /**
         * 获取项目列表
         */
        void getTabList();
    }
}
