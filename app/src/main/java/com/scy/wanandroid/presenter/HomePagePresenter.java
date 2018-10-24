package com.scy.wanandroid.presenter;

import android.text.TextUtils;

import com.scy.wanandroid.R;
import com.scy.wanandroid.WanAndroidApp;
import com.scy.wanandroid.base.BaseObserver;
import com.scy.wanandroid.base.BasePresenter;
import com.scy.wanandroid.contract.HomePageContract;
import com.scy.wanandroid.entity.BannerBean;
import com.scy.wanandroid.entity.HomeArticleBean;
import com.scy.wanandroid.model.DataModel;
import com.scy.wanandroid.utils.AppUtils;
import com.scy.wanandroid.utils.RxUtils;



/**
 * Created by SCY on 2018/10/22 at 15:12.
 */
public class HomePagePresenter extends BasePresenter<HomePageContract.HomePageView>
implements HomePageContract.Presenter{

    private int currentPage=0;
    @Override
    protected void startInteractive() {
        initBannerData();
    }

    @Override
    public void initBannerData() {
        addSubscribe(DataModel.getDataModel().getBannerData()
        .compose(RxUtils.rxSchedulerHelper())
                .subscribeWith(new BaseObserver<BannerBean>(mView,
                        WanAndroidApp.getWanAndroidApp()
                                .getString(R.string.banner_error)) {
                    @Override
                    public void onNext(BannerBean bannerBean) {
                        mView.showBanner(bannerBean);
                        if (AppUtils.nonEmpty(bannerBean.getErrorMsg())) {
                            mView.showErrorCodeMsg(bannerBean.getErrorMsg());
                        }
                    }
                }));

    }

    @Override
    public void initHomeArticleData(int page,boolean isRefresh) {
        addSubscribe(DataModel.getDataModel().getHomeArticleList(page)
        .compose(RxUtils.rxSchedulerHelper())
                .subscribeWith(new BaseObserver<HomeArticleBean>(mView,
                        WanAndroidApp.getWanAndroidApp()
                                .getString(R.string.home_article_error)) {
                    @Override
                    public void onNext(HomeArticleBean homeArticleBean) {
                        mView.showHomeArticleList(homeArticleBean,isRefresh);
                        if (AppUtils.nonEmpty(homeArticleBean.getErrorMsg())) {
                            mView.showErrorCodeMsg(homeArticleBean.getErrorMsg());

                        }
                    }
                }));
    }

    @Override
    public void refresh() {
        initBannerData();
        initHomeArticleData(0,true);
    }

    @Override
    public void loadMore() {
        currentPage++;
        initHomeArticleData(currentPage,false);
    }
}
