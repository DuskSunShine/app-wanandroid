package com.scy.wanandroid.ui.wechat;


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
import com.scy.wanandroid.contract.WeChatSubContract;
import com.scy.wanandroid.entity.WXArticles;
import com.scy.wanandroid.presenter.WeChatSubPresenter;
import com.scy.wanandroid.ui.home.HomeActivity;
import com.scy.wanandroid.utils.WanAndroidToast;
import com.scy.wanandroid.utils.WanAndroidDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class WXArticlesFragment extends BaseFragment<WeChatSubPresenter>
implements WeChatSubContract.View {

    @SuppressLint("StaticFieldLeak")
    private static WXArticlesFragment WXArticlesFragment = null;

    public static WXArticlesFragment create() {
        if (WXArticlesFragment == null) {
            WXArticlesFragment = new WXArticlesFragment();
        }
        return WXArticlesFragment;
    }

    @BindView(R.id.wxArticles_tabLayout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.wxArticles_viewpager)
    ViewPager viewPager;
    private List<WxArticlesDetailFragment> fragments=new ArrayList<>();
    private HomeActivity activity;
    private WXArticles wxArticles;
    public WXArticlesFragment() {
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
    protected WeChatSubPresenter createPresenter() {
        return new WeChatSubPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_we_chat_sub;
    }

    @Override
    protected void initDataAndEvents() {

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
    public void showWxArticlesList(WXArticles wxArticles) {
        this.wxArticles=wxArticles;
        List<WXArticles.DataBean> data = wxArticles.getData();
        for (int i = 0; i < data.size(); i++) {
            fragments.add(WxArticlesDetailFragment.create(data.get(i).getId()));
        }
        viewPager.setOffscreenPageLimit(3);
        initViewpager();
        tabLayout.setViewPager(viewPager);
    }

    private void initViewpager() {
        viewPager.setAdapter(new FragmentPagerAdapter(activity.getSupportFragmentManager()) {
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
                return wxArticles.getData().get(position).getName();

            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                //防止每次都从新加载
                //super.destroyItem(container, position, object);
            }
        });
    }
}
