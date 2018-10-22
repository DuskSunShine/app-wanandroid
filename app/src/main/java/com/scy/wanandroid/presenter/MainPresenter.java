package com.scy.wanandroid.presenter;


import com.scy.wanandroid.base.BasePresenter;
import com.scy.wanandroid.contract.MainContract;


/**
 * Created by SCY on 2018/10/19 at 10:22.
 */
public class MainPresenter extends BasePresenter<MainContract.MainView>
implements MainContract.Presenter{

    @Override
    protected void startInteractive() {
        mView.addFragment();
        mView.initShow();
    }


}
