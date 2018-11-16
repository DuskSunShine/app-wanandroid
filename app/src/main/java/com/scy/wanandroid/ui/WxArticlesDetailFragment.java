package com.scy.wanandroid.ui;


import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.scy.wanandroid.R;
import com.scy.wanandroid.adapter.WxArticlesDetailAdapter;
import com.scy.wanandroid.base.BaseFragment;
import com.scy.wanandroid.contract.WxArticlesDetailContract;
import com.scy.wanandroid.entity.WxArticlesDetail;
import com.scy.wanandroid.presenter.WxArticlesDetailPresenter;
import com.scy.wanandroid.utils.WanAndroidToast;
import com.scy.wanandroid.utils.WanAndroidDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class WxArticlesDetailFragment extends BaseFragment<WxArticlesDetailPresenter>
implements WxArticlesDetailContract.View ,OnRefreshLoadMoreListener {

    @SuppressLint("StaticFieldLeak")
    private static WxArticlesDetailFragment wxArticlesDetailFragment=null;
    private int wxArticleID;
    private WxArticlesDetailAdapter detailAdapter;
    private List<WxArticlesDetail.DataBean.DatasBean> datasBeans=new ArrayList<>();
    @BindView(R.id.wxArticles_refreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.wxArticlesRecycler)
    RecyclerView recyclerView;
    private HomeActivity activity;
    private boolean isFirstLoading=true;//第一次进入加载数据

    public static WxArticlesDetailFragment create(int wxArticleID){
            wxArticlesDetailFragment=new WxArticlesDetailFragment();
        wxArticlesDetailFragment.wxArticleID=wxArticleID;
        return wxArticlesDetailFragment;
    }

    public WxArticlesDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof HomeActivity){
            activity= (HomeActivity) context;
        }
    }

    @Override
    protected WxArticlesDetailPresenter createPresenter() {
        return new WxArticlesDetailPresenter();
    }

    @Override
    protected int onCreateView() {
        return R.layout.fragment_wx_articles_detail;
    }

    @Override
    protected void initDataAndEvents() {
        if (isFirstLoading) {
            mPresenter.getWxArticleDetail(wxArticleID, true);
            isFirstLoading=false;
        }
        detailAdapter = new WxArticlesDetailAdapter(R.layout.home_item,datasBeans);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setAdapter(detailAdapter);
        smartRefreshLayout.setOnRefreshLoadMoreListener(this);
        detailAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });

        detailAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void showApiErrorMsg(String s) {
        WanAndroidToast.toast(s);
    }

    @Override
    public void showErrorCodeMsg(String s) {
        WanAndroidDialog.dialog(s);
    }

    @Override
    public void showWxArticleDetail(WxArticlesDetail wxArticlesDetail, boolean isFirstLoad) {
        if (isFirstLoad){
            detailAdapter.replaceData(wxArticlesDetail.getData().getDatas());
        }else {
            if (detailAdapter.getItemCount()>=wxArticlesDetail.getData().getTotal()){
                smartRefreshLayout.finishLoadMore(1000,true,true);
            }
            detailAdapter.addData(wxArticlesDetail.getData().getDatas());
        }
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        mPresenter.loadMore();
        refreshLayout.finishLoadMore(1000);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mPresenter.refresh();
        refreshLayout.finishRefresh(1000);
    }
}
