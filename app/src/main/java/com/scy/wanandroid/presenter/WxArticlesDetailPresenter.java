package com.scy.wanandroid.presenter;

import com.scy.wanandroid.R;
import com.scy.wanandroid.base.BaseObserver;
import com.scy.wanandroid.base.BasePresenter;
import com.scy.wanandroid.contract.WxArticlesDetailContract;
import com.scy.wanandroid.entity.WxArticlesDetail;
import com.scy.wanandroid.utils.AppUtils;
import com.scy.wanandroid.utils.RxUtils;

/**
 * Created by SCY on 2018/11/16 at 15:10.
 */
public class WxArticlesDetailPresenter extends BasePresenter<WxArticlesDetailContract.View>
implements WxArticlesDetailContract.Presenter{

    private int currentPage=0;
    private int wxArticleID;
    @Override
    protected void startInteractive() {

    }


    @Override
    public void getWxArticleDetail(int wxArticleID, boolean isFirstLoad) {
        this.wxArticleID=wxArticleID;
        addSubscribe(mDataManager.getWxArticlesDetail(wxArticleID,currentPage)
        .compose(RxUtils.rxSchedulerHelper())
        .subscribeWith(new BaseObserver<WxArticlesDetail>(mView,
                R.string.wx_detail_error) {
            @Override
            public void onNext(WxArticlesDetail wxArticlesDetail) {
                mView.showWxArticleDetail(wxArticlesDetail,isFirstLoad);
                if (AppUtils.nonEmpty(wxArticlesDetail.getErrorMsg())){
                    mView.showErrorCodeMsg(wxArticlesDetail.getErrorMsg());
                }
            }
        }));
    }

    @Override
    public void loadMore() {
        currentPage++;
        getWxArticleDetail(wxArticleID,false);
    }

    @Override
    public void refresh() {
        currentPage=0;
        getWxArticleDetail(wxArticleID,true);
    }
}
