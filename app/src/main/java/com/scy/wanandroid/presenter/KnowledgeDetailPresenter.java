package com.scy.wanandroid.presenter;

import com.scy.wanandroid.R;
import com.scy.wanandroid.WanAndroidApp;
import com.scy.wanandroid.base.BaseObserver;
import com.scy.wanandroid.base.BasePresenter;
import com.scy.wanandroid.constants.Constants;
import com.scy.wanandroid.contract.KnowledgeDetailContract;
import com.scy.wanandroid.entity.KnowledgeDetail;
import com.scy.wanandroid.utils.AppUtils;
import com.scy.wanandroid.utils.RxBus;
import com.scy.wanandroid.utils.RxUtils;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Created by SCY on 2018/11/15 at 15:31.
 */
public class KnowledgeDetailPresenter extends BasePresenter<KnowledgeDetailContract.View>
implements KnowledgeDetailContract.Presenter {


    private int currentPage=0;
    private int cid;
    @Override
    protected void startInteractive() {

    }


    @Override
    public void getDetailKnowledge(int cid,boolean isFirstLoad) {
        this.cid=cid;
        addSubscribe(mDataManager.getKnowledgeDetail(currentPage,this.cid)
        .compose(RxUtils.rxSchedulerHelper())
        .subscribeWith(new BaseObserver<KnowledgeDetail>(mView,WanAndroidApp.getWanAndroidApp()
                .getString(R.string.list_error)) {
            @Override
            public void onNext(KnowledgeDetail knowledgeDetail) {
                mView.showKnowledgeDetail(knowledgeDetail,isFirstLoad);
                if (AppUtils.nonEmpty(knowledgeDetail.getErrorMsg())) {
                    mView.showErrorCodeMsg(knowledgeDetail.getErrorMsg());
                }
            }
        }));
    }

    @Override
    public void refresh(int cid) {
        currentPage=0;
        this.cid=cid;
        getDetailKnowledge(this.cid,true);
    }

    @Override
    public void loadMore(int cid) {
        this.cid=cid;
        currentPage++;
        getDetailKnowledge(this.cid,false);
    }
}
