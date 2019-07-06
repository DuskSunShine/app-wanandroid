package com.scy.wanandroid.presenter;

import android.annotation.SuppressLint;

import com.scy.wanandroid.base.BaseObserver;
import com.scy.wanandroid.base.BasePresenter;
import com.scy.wanandroid.contract.NavigationContract;
import com.scy.wanandroid.entity.NavigationListData;
import com.scy.wanandroid.utils.AppUtils;
import com.scy.wanandroid.utils.RxUtils;

/**
 * @author: SCY
 * @date: 2019/5/28   14:47
 * @version:
 * @desc:
 */
public class NavigationPresenter extends BasePresenter<NavigationContract.View> implements NavigationContract.Presenter {



    @Override
    protected void startInteractive() {

    }

    @Override
    public void getNavigationList() {
        addSubscribe(mDataManager.getNaviList()
                .compose(RxUtils.rxSchedulerHelper())
                .subscribeWith(new BaseObserver<NavigationListData>(mView,"列表数据获取失败") {
                    @Override
                    public void onNext(NavigationListData navigationListData) {
                        mView.showNavigationList(navigationListData);
                        if (AppUtils.nonEmpty(navigationListData.getErrorMsg())){
                            mView.showErrorCodeMsg(navigationListData.getErrorMsg());
                        }
                    }
                }));
    }
}
