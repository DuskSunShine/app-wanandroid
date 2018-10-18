package com.scy.wanandroid.contract;

import com.scy.wanandroid.base.AbsPresenter;
import com.scy.wanandroid.base.AbsView;

/**
 * Created by SCY on 2018/10/18 at 16:25.
 */
public interface SplashContract {

    interface SplashView extends AbsView{
        /**
         * 一段时间跳转到main
         */
        void startMainActivity();
    }

    interface Presenter extends AbsPresenter<SplashView>{

    }
}
