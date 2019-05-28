package com.scy.wanandroid.ui.project;


import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.scy.wanandroid.R;
import com.scy.wanandroid.adapter.ProjectListAdapter;
import com.scy.wanandroid.adapter.WxArticlesDetailAdapter;
import com.scy.wanandroid.base.BaseFragment;
import com.scy.wanandroid.contract.ProjectListContract;
import com.scy.wanandroid.entity.ProjectList;
import com.scy.wanandroid.presenter.ProjectListPresenter;
import com.scy.wanandroid.ui.home.HomeActivity;
import com.scy.wanandroid.utils.WanAndroidDialog;
import com.scy.wanandroid.utils.WanAndroidToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 项目列表页
 */
public class ProjectListFragment extends BaseFragment<ProjectListPresenter> implements ProjectListContract.View,
        OnRefreshLoadMoreListener {

    @BindView(R.id.projectList_refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.projectListRecycler)
    RecyclerView recyclerView;
    private ProjectListAdapter adapter;
    private List<ProjectList.DataBean.DatasBean> list = new ArrayList<>();
    private HomeActivity activity;

    public ProjectListFragment() {
        // Required empty public constructor
    }

    @SuppressLint("StaticFieldLeak")
    private static ProjectListFragment projectListFragment = null;
    private int projectId;
    private boolean isFirstLoading=true;//第一次进入加载数据

    public static ProjectListFragment create(int projectId) {
        projectListFragment = new ProjectListFragment();
        projectListFragment.projectId = projectId;
        return projectListFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof HomeActivity) {
            activity = (HomeActivity) context;
        }
    }

    @Override
    protected ProjectListPresenter createPresenter() {
        return new ProjectListPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_project_list;
    }

    @Override
    protected void initDataAndEvents() {
        if (isFirstLoading) {
            mPresenter.getProjectList(projectId, true);
            isFirstLoading=false;
        }
        adapter = new ProjectListAdapter(R.layout.project_item,list);
        adapter.setContext(activity);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setAdapter(adapter);
        refreshLayout.setOnRefreshLoadMoreListener(this);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void showProjectList(ProjectList projectList, boolean isFirstLoad) {
        if (isFirstLoad){
            adapter.replaceData(projectList.getData().getDatas());
        }else {
            if (adapter.getItemCount()>=projectList.getData().getTotal()){
                refreshLayout.finishLoadMore(1000,true,true);
                return;
            }
            adapter.addData(projectList.getData().getDatas());
        }
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
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        mPresenter.loadMore();
        refreshLayout.finishLoadMore(1000);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mPresenter.refresh();
        refreshLayout.finishLoadMore(1000);
    }
}
