package com.scy.wanandroid.ui;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.AppCompatTextView;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.scy.wanandroid.R;
import com.scy.wanandroid.base.BaseActivity;
import com.scy.wanandroid.constants.Constants;
import com.scy.wanandroid.contract.MainContract;
import com.scy.wanandroid.presenter.MainPresenter;
import com.scy.wanandroid.utils.AppUtils;

import java.util.HashMap;

import butterknife.BindView;


public class HomeActivity extends BaseActivity<MainPresenter>
        implements MainContract.MainView, BottomNavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.drawer)
    DrawerLayout drawer;
    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    @BindView(R.id.main_title)
    AppCompatTextView main_title;

    private HomeFragment homeFragment;
    private KnowledgeFragment knowledgeFragment;
    private WeChatSubFragment weChatSubFragment;
    private ProjectFragment projectFragment;

    @SuppressLint("UseSparseArrays")
    private HashMap<Integer, Fragment> fragments = new HashMap<>();


    @Override
    public int getContentViewId() {
        return R.layout.activity_home;
    }

    @Override
    public MainPresenter createPresenter() {
        if (AppUtils.isNull(homeFragment)) {
            homeFragment = HomeFragment.create();
            fragments.put(Constants.HOME, homeFragment);
        }
        if (AppUtils.isNull(knowledgeFragment)) {
            knowledgeFragment = KnowledgeFragment.create();
            fragments.put(Constants.KNOWLEDGE, knowledgeFragment);
        }
        if (AppUtils.isNull(weChatSubFragment)) {
            weChatSubFragment = WeChatSubFragment.create();
            fragments.put(Constants.WECHATSUB, weChatSubFragment);
        }
        if (AppUtils.isNull(projectFragment)) {
            projectFragment = ProjectFragment.create();
            fragments.put(Constants.PROJECT, projectFragment);
        }
        navigation.setOnNavigationItemSelectedListener(this);
        return new MainPresenter();
    }

    @Override
    public void initDataAndEvents() {

    }


    @Override
    public void onClick(View view) {

    }

    @Override
    public void initShow() {
        showFragment(Constants.HOME);
    }

    @Override
    public void addFragment() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.frameLayout, homeFragment);
        transaction.add(R.id.frameLayout, knowledgeFragment);
        transaction.add(R.id.frameLayout, weChatSubFragment);
        transaction.add(R.id.frameLayout, projectFragment);
        transaction.hide(homeFragment);
        transaction.hide(knowledgeFragment);
        transaction.hide(weChatSubFragment);
        transaction.hide(projectFragment);
        transaction.commitAllowingStateLoss();
    }

    @Override
    public void showFragment(int witch, int... hide) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        switch (witch) {
            case Constants.HOME:
                transaction.show(homeFragment);
                for (int f : hide) {
                    if (AppUtils.nonNull(fragments.get(f))) {
                        transaction.hide(fragments.get(f));
                    }
                }
                main_title.setText(R.string.title_home);
                break;
            case Constants.KNOWLEDGE:
                transaction.show(knowledgeFragment);
                for (int f : hide) {
                    if (AppUtils.nonNull(fragments.get(f))) {
                        transaction.hide(fragments.get(f));
                    }
                }
                main_title.setText(R.string.title_dashboard);
                break;
            case Constants.PROJECT:
                transaction.show(projectFragment);
                for (int f : hide) {
                    if (AppUtils.nonNull(fragments.get(f))) {
                        transaction.hide(fragments.get(f));
                    }
                }
                main_title.setText(R.string.title_notification);
                break;
            case Constants.WECHATSUB:
                transaction.show(weChatSubFragment);
                for (int f : hide) {
                    if (AppUtils.nonNull(fragments.get(f))) {
                        transaction.hide(fragments.get(f));
                    }
                }
                main_title.setText(R.string.title_notifications);
                break;
        }
        transaction.commitAllowingStateLoss();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                showFragment(Constants.HOME, Constants.KNOWLEDGE,
                        Constants.PROJECT, Constants.WECHATSUB);
                return true;
            case R.id.navigation_knowledge:
                showFragment(Constants.KNOWLEDGE, Constants.HOME,
                        Constants.PROJECT, Constants.WECHATSUB);
                return true;
            case R.id.navigation_wechatSub:
                showFragment(Constants.WECHATSUB, Constants.KNOWLEDGE,
                        Constants.PROJECT, Constants.HOME);
                return true;
            case R.id.navigation_project:
                showFragment(Constants.PROJECT, Constants.KNOWLEDGE,
                        Constants.HOME, Constants.WECHATSUB);
                return true;
        }
        return false;
    }

    @Override
    public void showApiErrorMsg(String s) {

    }

    @Override
    public void showErrorCodeMsg(String s) {

    }
}
