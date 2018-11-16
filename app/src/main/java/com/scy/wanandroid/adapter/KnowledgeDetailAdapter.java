package com.scy.wanandroid.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scy.wanandroid.R;
import com.scy.wanandroid.entity.KnowledgeDetail;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.logg.Logg;

/**
 * Created by SCY on 2018/11/15 at 17:01.
 */
public class KnowledgeDetailAdapter extends BaseQuickAdapter<KnowledgeDetail.DataBean.DatasBean, KnowledgeDetailAdapter.ViewHolder> {


    public KnowledgeDetailAdapter(int layoutResId, @Nullable List<KnowledgeDetail.DataBean.DatasBean> data) {
        super(layoutResId, data);
    }

    @Override
    public int getItemCount() {
        Logg.e("知识体系详细",mData.size());
        return super.getItemCount();
    }

    @Override
    protected void convert(ViewHolder helper, KnowledgeDetail.DataBean.DatasBean item) {
        if (item.isCollect()){
            helper.collect.setImageResource(R.mipmap.icon_favo_on);
        }else {
            helper.collect.setImageResource(R.mipmap.icon_favo_off);
        }
        helper.author.setText(item.getAuthor());
        if(item.getNiceDate().contains("小时")||
                item.getNiceDate().contains("一天")
                ||item.getNiceDate().contains("1天")){
            helper.setBackgroundRes(R.id.updateTime,R.mipmap.tag_green);
        }else {
            helper.setBackgroundRes(R.id.updateTime,R.mipmap.tag_gray);
        }
        helper.updateTime.setText(item.getNiceDate());
        helper.title.setText(item.getTitle());
        helper.chapterName.setText(String.format("%s/%s", item.getSuperChapterName(), item.getChapterName()));
    }

    class ViewHolder extends BaseViewHolder {
        @BindView(R.id.collect)
        AppCompatImageView collect;
        @BindView(R.id.author)
        AppCompatTextView author;
        @BindView(R.id.updateTime)
        AppCompatTextView updateTime;
        @BindView(R.id.title)
        AppCompatTextView title;
        @BindView(R.id.chapterName)
        AppCompatTextView chapterName;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
