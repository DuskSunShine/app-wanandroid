package com.scy.wanandroid.ui.knowledge;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.flyco.tablayout.SlidingTabLayout;
import com.scy.wanandroid.R;
import com.scy.wanandroid.base.BaseSwipeActivity;
import com.scy.wanandroid.constants.IntentKey;
import com.scy.wanandroid.contract.KnowledgeDetailContract;
import com.scy.wanandroid.entity.KnowledgeBean;
import com.scy.wanandroid.entity.KnowledgeDetail;
import com.scy.wanandroid.presenter.KnowledgeDetailPresenter;
import java.util.ArrayList;
import butterknife.BindView;

public class KnowledgeDetailActivity extends BaseSwipeActivity<KnowledgeDetailPresenter>
        implements KnowledgeDetailContract.View {

    @BindView(R.id.knowledge_detail_back)
    ImageView back;
    @BindView(R.id.knowledge_detail_tabLayout)
    SlidingTabLayout slidingTabLayout;
    @BindView(R.id.knowledge_detail_viewpager)
    ViewPager viewPager;
    @BindView(R.id.knowledge_detail_title)
    TextView title;
    private ArrayList<KnowledgeDetailFragment> fragments = new ArrayList<>();
    private KnowledgeBean.DataBean bean;
    private int cid;

    @Override
    public int getContentViewId() {
        return R.layout.activity_knowledge_detail;
    }

    @Override
    public KnowledgeDetailPresenter createPresenter() {
        return new KnowledgeDetailPresenter();
    }

    @Override
    public void initDataAndEvents() {
        Intent intent = getIntent();
        if (intent != null) {
            bean = (KnowledgeBean.DataBean)
                    intent.getSerializableExtra(IntentKey.KNOWLEDGE_DETAIL);
            title.setText(bean.getName());
            int size = bean.getChildren().size();
            for (int i = 0; i < size; i++) {
                fragments.add(KnowledgeDetailFragment.create(bean.getChildren().get(i).getId()));
            }
        }

        viewPager.setOffscreenPageLimit(3);
        initViewpager();
        slidingTabLayout.setViewPager(viewPager);
        setToBack(back);
    }

    private void initViewpager() {
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
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
                return bean.getChildren().get(position).getName();

            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                //super.destroyItem(container, position, object);
            }
        });
    }

    private void postCid(int i) {
        cid = bean.getChildren().get(i).getId();
        Bundle args = new Bundle();
        args.putInt(IntentKey.CID, cid);
        fragments.get(i).setArguments(args);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void showApiErrorMsg(String s) {

    }

    @Override
    public void showErrorCodeMsg(String s) {

    }


    @Override
    public void showKnowledgeDetail(KnowledgeDetail knowledgeDetail, boolean isFirstLoad) {

    }
}
