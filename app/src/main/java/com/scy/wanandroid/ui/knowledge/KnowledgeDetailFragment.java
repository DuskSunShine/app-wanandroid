package com.scy.wanandroid.ui.knowledge;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.scy.wanandroid.R;
import com.scy.wanandroid.adapter.KnowledgeDetailAdapter;
import com.scy.wanandroid.base.BaseFragment;
import com.scy.wanandroid.constants.IntentKey;
import com.scy.wanandroid.contract.KnowledgeDetailContract;
import com.scy.wanandroid.entity.KnowledgeDetail;
import com.scy.wanandroid.presenter.KnowledgeDetailPresenter;
import com.scy.wanandroid.utils.WanAndroidToast;
import com.scy.wanandroid.utils.AppUtils;
import com.scy.wanandroid.utils.WanAndroidDialog;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import me.logg.Logg;

/**
 * A simple {@link Fragment} subclass.
 */
public class KnowledgeDetailFragment extends BaseFragment<KnowledgeDetailPresenter>
implements KnowledgeDetailContract.View,OnRefreshLoadMoreListener {

    @SuppressLint("StaticFieldLeak")
    private static KnowledgeDetailFragment knowledgeDetailFragment = null;
    private int cid;
    private KnowledgeDetailAdapter knowledgeAdapter;
    private boolean isFirstLoading=true;//第一次加载该页面
    public static KnowledgeDetailFragment create(int cid) {
        knowledgeDetailFragment = new KnowledgeDetailFragment();
        knowledgeDetailFragment.cid=cid;
        return knowledgeDetailFragment;
    }
    public KnowledgeDetailFragment() {

    }

    @BindView(R.id.knowledge_detail_smartRefresh)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.knowledge_detail_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.knowledge_detail_footer)
    ClassicsFooter footer;
    private KnowledgeDetailActivity activity;
    private List<KnowledgeDetail.DataBean.DatasBean> dataBeanList=new ArrayList<>();
    @Override
    protected KnowledgeDetailPresenter createPresenter() {
        return new KnowledgeDetailPresenter();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof KnowledgeDetailActivity){
            activity= (KnowledgeDetailActivity) context;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_knowledge_detail;
    }

    @Override
    protected void initDataAndEvents() {
        dispatchCid();
        knowledgeAdapter = new KnowledgeDetailAdapter(R.layout.home_item, dataBeanList);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setAdapter(knowledgeAdapter);

        //mPresenter.getDetailKnowledge(0, true);

        knowledgeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<KnowledgeDetail.DataBean.DatasBean> data = knowledgeAdapter.getData();
                if (!data.isEmpty()){
                    AppUtils.goWebView(activity,
                            data.get(position).getLink(),
                            data.get(position).getTitle());
                }
            }
        });

        knowledgeAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
        refreshLayout.setOnRefreshLoadMoreListener(this);
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



    private void dispatchCid() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            cid = arguments.getInt(IntentKey.CID);
            Logg.e("cid",cid);
        }
        if (isFirstLoading) {
            mPresenter.getDetailKnowledge(cid, true);
            isFirstLoading=false;
        }
    }

    @Override
    public void showKnowledgeDetail(KnowledgeDetail knowledgeDetail, boolean isFirstLoad) {
        if (isFirstLoad) {
            //dataBeanList = knowledgeDetail.getData().getDatas();
            knowledgeAdapter.replaceData(knowledgeDetail.getData().getDatas());
        } else {
            //dataBeanList.addAll(knowledgeDetail.getData().getDatas());
            if (knowledgeAdapter.getItemCount()>=knowledgeDetail.getData().getTotal()){
                refreshLayout.finishLoadMore(1000,true,true);
                return;
            }
            knowledgeAdapter.addData(knowledgeDetail.getData().getDatas());
        }
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        mPresenter.loadMore(cid);
        refreshLayout.finishLoadMore(1000);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mPresenter.refresh(cid);
        refreshLayout.finishRefresh(1000);
    }
}
