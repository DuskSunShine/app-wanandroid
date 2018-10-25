package com.scy.wanandroid.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scy.wanandroid.R;
import com.scy.wanandroid.entity.KnowledgeBean;
import com.scy.wanandroid.utils.AppUtils;

import java.util.List;

/**
 * Created by SCY on 2018/10/25 at 17:06.
 */
public class KnowledgeAdapter extends BaseQuickAdapter<KnowledgeBean.DataBean,KnowledgeAdapter.KnowledgeViewHolder> {


    public KnowledgeAdapter(int layoutResId, @Nullable List<KnowledgeBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(KnowledgeViewHolder helper, KnowledgeBean.DataBean item) {
        helper.setText(R.id.knowledge_title,item.getName());
        helper.setTextColor(R.id.knowledge_title,AppUtils.randomColor());

        StringBuilder builder=new StringBuilder();
        for (int i = 0; i < item.getChildren().size(); i++) {
            builder.append(item.getChildren().get(i).getName())
            .append("  ");
        }
        helper.setText(R.id.content,builder);
        helper.setTextColor(R.id.content,AppUtils.randomColor());
    }

    class KnowledgeViewHolder extends BaseViewHolder{
        private AppCompatTextView knowledge_title;
        private AppCompatTextView content;
        public KnowledgeViewHolder(View view) {
            super(view);
            knowledge_title=view.findViewById(R.id.knowledge_title);
            content=view.findViewById(R.id.content);
        }
    }
}
