package com.scy.wanandroid.ui;



import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.AppCompatCheckedTextView;
import android.view.View;

import com.scy.wanandroid.R;
import com.scy.wanandroid.base.BaseActivity;
import com.scy.wanandroid.constants.Constants;
import com.scy.wanandroid.contract.MainContract;
import com.scy.wanandroid.presenter.MainPresenter;
import com.scy.wanandroid.utils.AppUtils;


public class MainActivity extends BaseActivity<MainPresenter>
        implements MainContract.MainView {

    private DrawerLayout drawer;
    private AppCompatCheckedTextView homePage;
    private AppCompatCheckedTextView othersPage;

    private HomeFragment homeFragment;
    private OthersFragment othersFragment;
    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    public void beforeInitView() {
        if (AppUtils.isNull(homeFragment)){
            homeFragment=HomeFragment.create();
        }
        if (AppUtils.isNull(othersFragment)){
            othersFragment=OthersFragment.create();
        }
    }

    @Override
    public void initView() {
        drawer=findViewById(R.id.drawer);
        homePage=findViewById(R.id.homePage);
        othersPage=findViewById(R.id.othersPage);
        setOnClick(homePage,othersPage);

    }

    @Override
    public void initData() {

    }

    @Override
    public void initShow() {

        showFragment(Constants.HOME);
    }

    @Override
    public void addFragment() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.frameLayout,homeFragment);
        transaction.add(R.id.frameLayout,othersFragment);
        transaction.commitAllowingStateLoss();
    }

    @Override
    public void showFragment( int witch) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if (witch==Constants.HOME) {
            transaction.show(homeFragment);
            transaction.hide(othersFragment);
            homePage.setChecked(true);
            othersPage.setChecked(false);
        }
        else {
            transaction.show(othersFragment);
            transaction.hide(homeFragment);
            othersPage.setChecked(true);
            homePage.setChecked(false);
        }
        transaction.commitAllowingStateLoss();
    }


    @Override
    public void onClick(View view) {
        mPresenter.setCurrentPage((AppCompatCheckedTextView) view);
    }
}
