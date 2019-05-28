package com.scy.wanandroid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scy.wanandroid.R;
import com.scy.wanandroid.entity.ProjectList;
import com.scy.wanandroid.utils.GlideLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: SCY
 * @date: 2019/5/28   9:42
 * @version:
 * @desc:
 */
public class ProjectListAdapter extends BaseQuickAdapter<ProjectList.DataBean.DatasBean, ProjectListAdapter.ViewHolder> {

    private Context context;
    public ProjectListAdapter(int layoutResId, @Nullable List<ProjectList.DataBean.DatasBean> data) {
        super(layoutResId, data);
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    protected void convert(ViewHolder helper, ProjectList.DataBean.DatasBean item) {
        GlideLoader.loadImage(context,helper.project_image,item.getEnvelopePic(),
                R.drawable.ic_broken_image_black_24dp,R.drawable.ic_broken_image_black_24dp,false);
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
        helper.content.setText(item.getDesc());
        if (item.isCollect()){
            helper.collect.setImageResource(R.mipmap.icon_favo_on);
        }else {
            helper.collect.setImageResource(R.mipmap.icon_favo_off);
        }
        helper.chapterName.setText(String.format("%s/%s", item.getSuperChapterName(), item.getChapterName()));
    }

    class ViewHolder extends BaseViewHolder{
        @BindView(R.id.project_image)
        AppCompatImageView project_image;
        @BindView(R.id.author)
        AppCompatTextView author;
        @BindView(R.id.updateTime)
        AppCompatTextView updateTime;
        @BindView(R.id.content)
        AppCompatTextView content;
        @BindView(R.id.title)
        AppCompatTextView title;
        @BindView(R.id.chapterName)
        AppCompatTextView chapterName;
        @BindView(R.id.collect)
        AppCompatImageView collect;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }
    }
}
