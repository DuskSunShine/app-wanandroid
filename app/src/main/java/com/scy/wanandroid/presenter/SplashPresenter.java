package com.scy.wanandroid.presenter;

import com.scy.wanandroid.base.BasePresenter;
import com.scy.wanandroid.contract.SplashContract;
import com.scy.wanandroid.utils.RxUtils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

/**
 * Created by SCY on 2018/10/18 at 16:28.
 */
public class SplashPresenter extends BasePresenter<SplashContract.SplashView>
        implements SplashContract.Presenter {


    @Override
    protected void startInteractive() {
        addSubscribe(Observable.timer(2, TimeUnit.SECONDS)
                .compose(RxUtils.rxSchedulerHelper())
                .subscribe(aLong -> mView.startMainActivity()));
    }
}
