package com.scy.wanandroid.ui;


import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scy.wanandroid.R;
import com.scy.wanandroid.base.BaseFragment;
import com.scy.wanandroid.contract.HomePageContract;
import com.scy.wanandroid.entity.BannerBean;
import com.scy.wanandroid.entity.HomeArticleBean;
import com.scy.wanandroid.presenter.HomePagePresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment<HomePagePresenter>
        implements HomePageContract.HomePageView {

    @SuppressLint("StaticFieldLeak")
    private static HomeFragment homeFragment = null;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment create() {
        if (homeFragment == null) {
            homeFragment = new HomeFragment();
        }
        return homeFragment;
    }

    private SmartRefreshLayout smartRefresh;
    private RecyclerView recycler;
    private int currentPage=0;

    @Override
    protected HomePagePresenter createPresenter() {
        return new HomePagePresenter();
    }

    @Override
    protected int onCreateView() {
        return R.layout.fragment_home;
    }

    @Override
    protected void beforeInitView() {

    }

    @Override
    protected void initView(View rootView) {
        smartRefresh=rootView.findViewById(R.id.smartRefresh);
        recycler=rootView.findViewById(R.id.recycler);
    }

    @Override
    protected void initData() {
        mPresenter.initHomeArticleData(currentPage);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void showBanner(BannerBean bannerBean) {

    }

    @Override
    public void showHomeArticleList(HomeArticleBean homeArticleBean) {

    }

    @Override
    public void showApiErrorMsg(String s) {

    }
}
