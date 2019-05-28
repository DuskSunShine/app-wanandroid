package com.scy.wanandroid.presenter;

import com.scy.wanandroid.R;
import com.scy.wanandroid.base.BaseObserver;
import com.scy.wanandroid.base.BasePresenter;
import com.scy.wanandroid.contract.ProjectListContract;
import com.scy.wanandroid.entity.ProjectList;
import com.scy.wanandroid.utils.AppUtils;
import com.scy.wanandroid.utils.RxUtils;

/**
 * @author: SCY
 * @date: 2019/5/27   15:58
 * @version:
 * @desc:
 */
public class ProjectListPresenter extends BasePresenter<ProjectListContract.View>
        implements ProjectListContract.Presenter {

    private int currentPage=0;
    private int projectID;
    @Override
    protected void startInteractive() {

    }

    @Override
    public void getProjectList(int projectID, boolean isFirstLoad) {
        this.projectID=projectID;
        addSubscribe(mDataManager.getProjectList(currentPage,projectID)
        .compose(RxUtils.rxSchedulerHelper())
        .subscribeWith(new BaseObserver<ProjectList>(mView, R.string.project_list_error) {
            @Override
            public void onNext(ProjectList projectList) {
                mView.showProjectList(projectList,isFirstLoad);
                if (AppUtils.nonEmpty(projectList.getErrorMsg())){
                    mView.showErrorCodeMsg(projectList.getErrorMsg());
                }
            }
        }));
    }

    @Override
    public void loadMore() {
        currentPage++;
        getProjectList(projectID,false);
    }

    @Override
    public void refresh() {
        currentPage=0;
        getProjectList(projectID,true);
    }
}
