package com.scy.wanandroid.ui;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scy.wanandroid.R;
import com.scy.wanandroid.adapter.KnowledgeAdapter;
import com.scy.wanandroid.base.BaseFragment;
import com.scy.wanandroid.contract.KnowledgeContract;
import com.scy.wanandroid.entity.KnowledgeBean;
import com.scy.wanandroid.presenter.KnowledgePresenter;
import com.scy.wanandroid.utils.AppToast;
import com.scy.wanandroid.utils.AppUtils;
import com.scy.wanandroid.utils.WanAndroidDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class KnowledgeFragment extends BaseFragment<KnowledgePresenter>
        implements KnowledgeContract.KnowledgeView, OnRefreshListener {

    private static KnowledgeFragment knowledgeFragment = null;

    public static KnowledgeFragment create() {
        if (knowledgeFragment == null) {
            knowledgeFragment = new KnowledgeFragment();
        }
        return knowledgeFragment;
    }

    public KnowledgeFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.knowledgeRefresh)
    SmartRefreshLayout knowledgeRefresh;
    @BindView(R.id.knowledgeRecycler)
    RecyclerView knowledgeRecycler;
    private KnowledgeAdapter adapter;
    private HomeActivity context;
    private List<KnowledgeBean.DataBean> list = new ArrayList<>();

    @Override
    protected KnowledgePresenter createPresenter() {
        return new KnowledgePresenter();
    }

    @Override
    protected int onCreateView() {
        return R.layout.fragment_knowledge;
    }

    @Override
    protected void initDataAndEvents() {

        adapter = new KnowledgeAdapter(R.layout.knowledge_item, list);
        knowledgeRecycler.setLayoutManager(new LinearLayoutManager(context));
        knowledgeRecycler.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof HomeActivity) {
            this.context = (HomeActivity) context;
        }
    }


    @Override
    public void onClick(View view) {

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
    public void showKnowledge(KnowledgeBean knowledgeBean) {
        list.clear();
        list = knowledgeBean.getData();
        adapter.addData(list);
    }


    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mPresenter.refresh();
        refreshLayout.finishRefresh(1500);
    }
}
