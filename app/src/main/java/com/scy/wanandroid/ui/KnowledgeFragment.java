package com.scy.wanandroid.ui;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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

/**
 * A simple {@link Fragment} subclass.
 */
public class KnowledgeFragment extends BaseFragment<KnowledgePresenter>
        implements KnowledgeContract.KnowledgeView,OnRefreshListener {

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

    private SmartRefreshLayout knowledgeRefresh;
    private RecyclerView knowledgeRecycler;
    private KnowledgeAdapter adapter;
    private HomeActivity context;
    private List<KnowledgeBean.DataBean> list=new ArrayList<>();
    @Override
    protected KnowledgePresenter createPresenter() {
        return new KnowledgePresenter();
    }

    @Override
    protected int onCreateView() {
        return R.layout.fragment_knowledge;
    }

    @Override
    protected void beforeInitView() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof HomeActivity){
            this.context= (HomeActivity) context;
        }
    }

    @Override
    protected void initView(View rootView) {
        knowledgeRefresh=rootView.findViewById(R.id.knowledgeRefresh);
        knowledgeRecycler=rootView.findViewById(R.id.knowledgeRecycler);
        adapter=new KnowledgeAdapter(R.layout.knowledge_item,list);
        knowledgeRecycler.setLayoutManager(new LinearLayoutManager(context));
        knowledgeRecycler.setAdapter(adapter);
    }

    @Override
    protected void initData() {

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
        list=knowledgeBean.getData();
        adapter.addData(list);
    }


    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mPresenter.refresh();
        refreshLayout.finishRefresh(1500);
    }
}
