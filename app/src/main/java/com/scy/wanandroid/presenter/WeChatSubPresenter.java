package com.scy.wanandroid.presenter;

import com.scy.wanandroid.R;
import com.scy.wanandroid.base.BaseObserver;
import com.scy.wanandroid.base.BasePresenter;
import com.scy.wanandroid.contract.WeChatSubContract;
import com.scy.wanandroid.entity.WXArticles;
import com.scy.wanandroid.utils.AppUtils;
import com.scy.wanandroid.utils.RxUtils;

/**
 * Created by SCY on 2018/11/16 at 13:49.
 */
public class WeChatSubPresenter extends BasePresenter<WeChatSubContract.View>
        implements WeChatSubContract.Presenter {
    @Override
    protected void startInteractive() {
        getTabList();
    }


    @Override
    public void getTabList() {
        addSubscribe(mDataManager.getWxArticles()
        .compose(RxUtils.rxSchedulerHelper())
        .subscribeWith(new BaseObserver<WXArticles>(mView,
                R.string.wx_error) {
            @Override
            public void onNext(WXArticles wxArticles) {
                mView.showWxArticlesList(wxArticles);
                if (AppUtils.nonEmpty(wxArticles.getErrorMsg())){
                    mView.showErrorCodeMsg(wxArticles.getErrorMsg());
                }
            }
        }));
    }
}
