package com.scy.wanandroid.ui;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.scy.wanandroid.R;
import com.scy.wanandroid.adapter.NavigationAdapter;
import com.scy.wanandroid.base.BaseFragment;
import com.scy.wanandroid.contract.NavigationContract;
import com.scy.wanandroid.entity.NavigationListData;
import com.scy.wanandroid.presenter.NavigationPresenter;
import com.scy.wanandroid.ui.home.HomeActivity;
import com.scy.wanandroid.utils.WanAndroidDialog;
import com.scy.wanandroid.utils.WanAndroidToast;

import java.util.List;

import butterknife.BindView;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.TabView;

/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationFragment extends BaseFragment<NavigationPresenter> implements NavigationContract.View {

    @SuppressLint("StaticFieldLeak")
    private static NavigationFragment navigationFragment = null;

    public static NavigationFragment create() {
        if (navigationFragment == null) {
            navigationFragment = new NavigationFragment();
        }
        return navigationFragment;
    }

    public NavigationFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.navigation_tab_layout)
    VerticalTabLayout mTabLayout;
    @BindView(R.id.normal_view)
    LinearLayout mNavigationGroup;
    @BindView(R.id.navigation_divider)
    View mDivider;
    @BindView(R.id.navigation_RecyclerView)
    RecyclerView mRecyclerView;

    private LinearLayoutManager mManager;
    private boolean needScroll;
    private int index;
    private boolean isClickTab;
    private HomeActivity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof HomeActivity) {
            activity = (HomeActivity) context;
        }
    }

    @Override
    protected NavigationPresenter createPresenter() {
        return new NavigationPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_navigation;
    }

    @Override
    protected void initDataAndEvents() {
        mPresenter.getNavigationList();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void showNavigationList(NavigationListData navigationListData) {
        List<NavigationListData.DataBean> navigationDataList = navigationListData.getData();
        mTabLayout.setTabAdapter(new TabAdapter() {
            @Override
            public int getCount() {
                return navigationDataList == null ? 0 : navigationDataList.size();
            }

            @Override
            public ITabView.TabBadge getBadge(int i) {
                return null;
            }

            @Override
            public ITabView.TabIcon getIcon(int i) {
                return null;
            }

            @Override
            public ITabView.TabTitle getTitle(int i) {
                return new TabView.TabTitle.Builder()
                        .setContent(navigationDataList.get(i).getName())
                        .setTextColor(ContextCompat.getColor(activity, R.color.colorPrimary),
                                ContextCompat.getColor(activity, R.color.black))
                        .build();
            }

            @Override
            public int getBackground(int i) {
                return -1;
            }
        });

        initRecyclerView(navigationDataList);
        leftRightLinkage();
    }

    @Override
    public void showApiErrorMsg(String s) {
        WanAndroidToast.toast(s);
    }

    @Override
    public void showErrorCodeMsg(String s) {
        WanAndroidDialog.dialog(s);
    }

    private void initRecyclerView(List<NavigationListData.DataBean> navigationDataList) {
        NavigationAdapter adapter = new NavigationAdapter(R.layout.item_navigation, navigationDataList);
        mRecyclerView.setAdapter(adapter);
        mManager = new LinearLayoutManager(activity);
        mRecyclerView.setLayoutManager(mManager);
        mRecyclerView.setHasFixedSize(true);
    }

    private void leftRightLinkage() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (needScroll && (newState == RecyclerView.SCROLL_STATE_IDLE)) {
                    scrollRecyclerView();
                }
                rightLinkageLeft(newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (needScroll) {
                    scrollRecyclerView();
                }
            }
        });
        mTabLayout.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabView tabView, int i) {
                isClickTab = true;
                selectTag(i);
            }

            @Override
            public void onTabReselected(TabView tabView, int i) {
            }
        });
    }

    private void selectTag(int i) {
        index = i;
        mRecyclerView.stopScroll();
        smoothScrollToPosition(i);
    }

    private void smoothScrollToPosition(int currentPosition) {
        int firstPosition = mManager.findFirstVisibleItemPosition();
        int lastPosition = mManager.findLastVisibleItemPosition();
        if (currentPosition <= firstPosition) {
            mRecyclerView.smoothScrollToPosition(currentPosition);
        } else if (currentPosition <= lastPosition) {
            int top = mRecyclerView.getChildAt(currentPosition - firstPosition).getTop();
            mRecyclerView.smoothScrollBy(0, top);
        } else {
            mRecyclerView.smoothScrollToPosition(currentPosition);
            needScroll = true;
        }
    }

    private void scrollRecyclerView() {
        needScroll = false;
        int indexDistance = index - mManager.findFirstVisibleItemPosition();
        if (indexDistance >= 0 && indexDistance < mRecyclerView.getChildCount()) {
            int top = mRecyclerView.getChildAt(indexDistance).getTop();
            mRecyclerView.smoothScrollBy(0, top);
        }
    }

    private void rightLinkageLeft(int newState) {
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            if (isClickTab) {
                isClickTab = false;
                return;
            }
            int firstPosition = mManager.findFirstVisibleItemPosition();
            if (index != firstPosition) {
                index = firstPosition;
                setChecked(index);
            }
        }
    }

    private void setChecked(int position) {
        if (isClickTab) {
            isClickTab = false;
        } else {
            if (mTabLayout == null) {
                return;
            }
            mTabLayout.setTabSelected(index);
        }
        index = position;
    }
}
