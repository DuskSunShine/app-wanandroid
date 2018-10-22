package com.scy.wanandroid.presenter;

import com.scy.wanandroid.R;
import com.scy.wanandroid.WanAndroidApp;
import com.scy.wanandroid.base.BaseObserver;
import com.scy.wanandroid.base.BasePresenter;
import com.scy.wanandroid.contract.HomePageContract;
import com.scy.wanandroid.entity.BannerBean;
import com.scy.wanandroid.entity.HomeArticleBean;
import com.scy.wanandroid.model.DataModel;
import com.scy.wanandroid.utils.RxUtils;



/**
 * Created by SCY on 2018/10/22 at 15:12.
 */
public class HomePagePresenter extends BasePresenter<HomePageContract.HomePageView>
implements HomePageContract.Presenter{
    @Override
    protected void startInteractive() {
        initBannerData();
    }

    @Override
    public void initBannerData() {
        addSubscribe(DataModel.getDataModel().getBannerData()
        .compose(RxUtils.rxSchedulerHelper())
        .compose(RxUtils.handleResult())
        .subscribeWith(new BaseObserver<BannerBean>(mView,
                WanAndroidApp.getWanAndroidApp().getString(R.string.banner_error)) {
            @Override
            public void onNext(BannerBean bannerBean) {
                mView.showBanner(bannerBean);
            }
        }));


    }

    @Override
    public void initHomeArticleData(int page) {
        addSubscribe(DataModel.getDataModel().getHomeArticleList(page)
        .compose(RxUtils.rxSchedulerHelper())
        .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<HomeArticleBean>(mView,
                        )));
    }
}
