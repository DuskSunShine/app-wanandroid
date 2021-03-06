package com.scy.wanandroid.contract;


import com.scy.wanandroid.base.AbsPresenter;
import com.scy.wanandroid.base.AbsView;

/**
 * Created by SCY on 2018/10/19 at 10:23.
 */
public interface MainContract {

    interface MainView extends AbsView{
        /**
         * 进入时显示的界面
         */
        void initShow();

        void addFragment();

        void showFragment(int witch,int... hide);



    }

    interface Presenter extends AbsPresenter<MainView>{

    }
}
