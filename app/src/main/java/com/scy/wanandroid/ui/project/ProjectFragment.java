package com.scy.wanandroid.ui.project;


import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import com.flyco.tablayout.SlidingTabLayout;
import com.scy.wanandroid.R;
import com.scy.wanandroid.base.BaseFragment;
import com.scy.wanandroid.contract.ProjectContract;
import com.scy.wanandroid.entity.ProjectBean;
import com.scy.wanandroid.presenter.ProjectPresenter;
import com.scy.wanandroid.ui.home.HomeActivity;
import com.scy.wanandroid.utils.WanAndroidDialog;
import com.scy.wanandroid.utils.WanAndroidToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 项目页
 */
public class ProjectFragment extends BaseFragment<ProjectPresenter> implements ProjectContract.ProjectView {

    @SuppressLint("StaticFieldLeak")
    private static ProjectFragment projectFragment = null;

    public static ProjectFragment create() {
        if (projectFragment == null) {
            synchronized (ProjectFragment.class) {
                projectFragment = new ProjectFragment();
            }
        }
        return projectFragment;
    }

    public ProjectFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.project_tabLayout)
    SlidingTabLayout project_tabLayout;
    @BindView(R.id.project_viewpager)
    ViewPager project_viewpager;
    private List<ProjectListFragment> fragments = new ArrayList<>();
    private HomeActivity activity;
    private ProjectBean projectBean;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof HomeActivity) {
            activity = (HomeActivity) context;
        }
    }

    @Override
    protected ProjectPresenter createPresenter() {
        return new ProjectPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_project;
    }

    @Override
    protected void initDataAndEvents() {

    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void showProjectType(ProjectBean projectBean) {
        this.projectBean=projectBean;
        List<ProjectBean.DataBean> data = projectBean.getData();
        for (int i = 0; i < data.size(); i++) {
            fragments.add(ProjectListFragment.create(data.get(i).getId()));
        }
        project_viewpager.setOffscreenPageLimit(3);
        initViewpager();
        project_tabLayout.setViewPager(project_viewpager);
    }

    @Override
    public void showApiErrorMsg(String s) {
        WanAndroidToast.toast(s);
    }

    @Override
    public void showErrorCodeMsg(String s) {
        WanAndroidDialog.dialog(s);
    }

    private void initViewpager() {
        project_viewpager.setAdapter(new FragmentPagerAdapter(activity.getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments == null ? 0 : fragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return projectBean.getData().get(position).getName();

            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                //防止每次都从新加载
                //super.destroyItem(container, position, object);
            }
        });
    }
}
