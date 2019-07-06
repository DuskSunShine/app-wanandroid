package com.scy.wanandroid.adapter;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scy.wanandroid.R;
import com.scy.wanandroid.entity.NavigationListData;
import com.scy.wanandroid.utils.AppUtils;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;





public class NavigationAdapter extends BaseQuickAdapter<NavigationListData.DataBean, NavigationAdapter.NavigationViewHolder> {


    public NavigationAdapter(int layoutResId, @Nullable List<NavigationListData.DataBean> data) {
        super(layoutResId, data);
    }



    @Override
    protected void convert(NavigationViewHolder helper, NavigationListData.DataBean item) {
        if (!TextUtils.isEmpty(item.getName())) {
            helper.setText(R.id.item_navigation_tv, item.getName());
        }
        TagFlowLayout mTagFlowLayout = helper.getView(R.id.item_navigation_flow_layout);
        List<NavigationListData.DataBean.ArticlesBean> mArticles = item.getArticles();
        mTagFlowLayout.setAdapter(new TagAdapter<NavigationListData.DataBean.ArticlesBean>(mArticles) {
            @Override
            public View getView(com.zhy.view.flowlayout.FlowLayout parent, int position, NavigationListData.DataBean.ArticlesBean feedArticleData) {
                TextView tv = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.flow_layout_tv,
                        mTagFlowLayout, false);
                if (feedArticleData == null) {
                    return null;
                }
                String name = feedArticleData.getTitle();
                tv.setPadding(AppUtils.dp2px(10), AppUtils.dp2px(10),
                        AppUtils.dp2px(10), AppUtils.dp2px(10));
                tv.setText(name);
                tv.setTextColor(AppUtils.randomColor());
                mTagFlowLayout.setOnTagClickListener((view, position1, parent1) -> {
                    AppUtils.goWebView((Activity) parent.getContext(),mArticles.get(position1).getLink(),mArticles.get(position1).getTitle());
                    return true;
                });
                return tv;
            }
        });
    }

    class NavigationViewHolder extends BaseViewHolder {

        @BindView(R.id.item_navigation_tv)
        TextView mTitle;
        @BindView(R.id.item_navigation_flow_layout)
        FlowLayout mFlowLayout;

        public NavigationViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
