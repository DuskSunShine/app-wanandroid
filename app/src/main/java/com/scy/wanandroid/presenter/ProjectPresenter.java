package com.scy.wanandroid.presenter;

import com.scy.wanandroid.R;
import com.scy.wanandroid.base.BaseObserver;
import com.scy.wanandroid.base.BasePresenter;
import com.scy.wanandroid.contract.ProjectContract;
import com.scy.wanandroid.entity.ProjectBean;
import com.scy.wanandroid.utils.AppUtils;
import com.scy.wanandroid.utils.RxUtils;

/**
 * @author: SCY
 * @date: 2019/5/27   15:04
 * @version:
 * @desc:
 */
public class ProjectPresenter extends BasePresenter<ProjectContract.ProjectView> implements ProjectContract.Presenter{
    @Override
    protected void startInteractive() {
        getTabList();
    }

    @Override
    public void getTabList() {
        addSubscribe(mDataManager.getProjectTab()
        .compose(RxUtils.rxSchedulerHelper())
        .subscribeWith(new BaseObserver<ProjectBean>(mView,R.string.project_error) {
            @Override
            public void onNext(ProjectBean projectBean) {
                mView.showProjectType(projectBean);
                if (AppUtils.nonEmpty(projectBean.getErrorMsg())){
                    mView.showErrorCodeMsg(projectBean.getErrorMsg());
                }
            }
        }));
    }
}
