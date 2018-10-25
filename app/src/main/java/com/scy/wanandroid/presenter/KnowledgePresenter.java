package com.scy.wanandroid.presenter;

import com.scy.wanandroid.R;
import com.scy.wanandroid.WanAndroidApp;
import com.scy.wanandroid.base.BaseObserver;
import com.scy.wanandroid.base.BasePresenter;
import com.scy.wanandroid.contract.KnowledgeContract;
import com.scy.wanandroid.entity.KnowledgeBean;
import com.scy.wanandroid.model.DataModel;
import com.scy.wanandroid.utils.AppUtils;
import com.scy.wanandroid.utils.RxUtils;

/**
 * Created by SCY on 2018/10/25 at 14:39.
 */
public class KnowledgePresenter extends BasePresenter<KnowledgeContract.KnowledgeView>
implements KnowledgeContract.Presenter{

    @Override
    protected void startInteractive() {
        getKnowledgeList();
    }


    private void getKnowledgeList(){
        addSubscribe(DataModel.getDataModel().getKnowledgeList()
        .compose(RxUtils.rxSchedulerHelper())
        .subscribeWith(new BaseObserver<KnowledgeBean>(mView,
                WanAndroidApp.getWanAndroidApp().getString(R.string.knowget_error)) {
            @Override
            public void onNext(KnowledgeBean knowledgeBean) {
                    mView.showKnowledge(knowledgeBean);
                    if (AppUtils.nonEmpty(knowledgeBean.getErrorMsg())){
                        mView.showErrorCodeMsg(knowledgeBean.getErrorMsg());
                    }
            }
        }));
    }

    @Override
    public void refresh() {
        getKnowledgeList();
    }
}
