package com.scy.wanandroid.ui;


import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.scy.wanandroid.R;
import com.scy.wanandroid.adapter.HomeAdapter;
import com.scy.wanandroid.base.BaseFragment;
import com.scy.wanandroid.contract.HomePageContract;
import com.scy.wanandroid.entity.BannerBean;
import com.scy.wanandroid.entity.HomeArticleBean;
import com.scy.wanandroid.presenter.HomePagePresenter;
import com.scy.wanandroid.utils.AppToast;
import com.scy.wanandroid.utils.AppUtils;
import com.scy.wanandroid.utils.GlideImageLoader;
import com.scy.wanandroid.utils.WanAndroidDialog;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment<HomePagePresenter>
        implements HomePageContract.HomePageView, OnRefreshLoadMoreListener {

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

    @BindView(R.id.smartRefresh)
    SmartRefreshLayout smartRefresh;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    private HomeAdapter homeAdapter;
    private HomeActivity homeActivity;
    private Banner mBanner;
    private List<String> mBannerTitleList;
    private List<String> mBannerUrlList;
    private List<HomeArticleBean.DataBean.DatasBean> homepageList = new ArrayList<>();
    private List<HomeArticleBean.DataBean.DatasBean> data;

    @Override
    protected HomePagePresenter createPresenter() {
        return new HomePagePresenter();
    }

    @Override
    protected int onCreateView() {
        return R.layout.fragment_home;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof HomeActivity) {
            homeActivity = (HomeActivity) context;
        }
    }


    @Override
    public void initDataAndEvents() {
        homeAdapter = new HomeAdapter(R.layout.home_item, homepageList);
        recycler.setLayoutManager(new LinearLayoutManager(homeActivity));

        CardView mHeaderGroup = (CardView) LayoutInflater.from(homeActivity).inflate(R.layout.head_banner, null);
        mBanner = mHeaderGroup.findViewById(R.id.head_banner);
        mHeaderGroup.removeView(mBanner);
        homeAdapter.addHeaderView(mBanner);
        recycler.setAdapter(homeAdapter);

        mPresenter.initHomeArticleData(0, true);

        homeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                data = homeAdapter.getData();
                if (!data.isEmpty()) {
                    AppUtils.goWebView(homeActivity, data.get(position).getLink()
                            , data.get(position).getTitle());
                }
            }
        });

        homeAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
        smartRefresh.setOnRefreshLoadMoreListener(this);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void showBanner(BannerBean bannerBean) {
        mBannerTitleList = new ArrayList<>();
        List<String> bannerImageList = new ArrayList<>();
        mBannerUrlList = new ArrayList<>();
        for (BannerBean.DataBean banner : bannerBean.getData()) {
            mBannerTitleList.add(banner.getTitle());
            bannerImageList.add(banner.getImagePath());
            mBannerUrlList.add(banner.getUrl());
        }
        //设置banner样式
        mBanner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        mBanner.setImages(bannerImageList);
        //设置banner动画效果
        mBanner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        mBanner.setBannerTitles(mBannerTitleList);
        //设置自动轮播，默认为true
        mBanner.isAutoPlay(true);
        //设置轮播时间
        mBanner.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.CENTER);

        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                AppUtils.goWebView(homeActivity, mBannerUrlList.get(position),
                        mBannerTitleList.get(position));
            }
        });
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();
    }

    @Override
    public void showHomeArticleList(HomeArticleBean homeArticleBean, boolean isRefresh) {
        if (isRefresh) {
            homepageList = homeArticleBean.getData().getDatas();
            homeAdapter.addData(homepageList);
        } else {
            homepageList.addAll(homeArticleBean.getData().getDatas());
            homeAdapter.replaceData(homepageList);
        }

    }

    @Override
    public void showApiErrorMsg(String s) {
        AppToast.toast(s);
    }

    @Override
    public void showErrorCodeMsg(String s) {
        WanAndroidDialog.dialog(s);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mBanner != null) {
            mBanner.startAutoPlay();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mBanner != null) {
            mBanner.stopAutoPlay();
        }
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        mPresenter.loadMore();
        refreshLayout.finishLoadMore(1500);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        homepageList.clear();
        mPresenter.refresh();
        refreshLayout.finishRefresh(1500);
    }
}
