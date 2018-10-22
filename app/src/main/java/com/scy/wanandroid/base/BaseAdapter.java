package com.scy.wanandroid.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by SCY on 2018/7/10 at 15:52.
 */

public abstract class BaseAdapter<T> extends RecyclerView.Adapter {

    private List<T> data=new ArrayList<>();
    private Context context;
    private LayoutInflater layoutInflater;
    public BaseAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    /**
     *
     * @return 布局资源文件
     */
    public abstract int getLayoutId();

    /**
     * 绑定数据
     * @param holder
     * @param position
     */
    public abstract void onBindData(BaseViewHolder holder, int position,List<T> data);

    /**
     *
     * @param t 指定位置添加数据
     * @param position  需要更新的item位置
     * @param recyclerView
     */
    public void addItemScrollToPosition(T t, int position, RecyclerView recyclerView) {
        data.add(position,t);
        notifyItemChanged(position);
        recyclerView.scrollToPosition(position);
    }

    /**
     *
     @param t 指定位置添加数据
      * @param position  需要更新的item位置
     * @param recyclerView
     */
    public void addItem(T t, int position, RecyclerView recyclerView) {
        data.add(position,t);
        notifyItemChanged(position);
        recyclerView.scrollToPosition(position);
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = layoutInflater.inflate(getLayoutId(), parent, false);
         BaseViewHolder baseViewHolder=new BaseViewHolder(inflate);
        return baseViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, @NonNull List payloads) {
        super.onBindViewHolder(holder, position, payloads);
        if (payloads.isEmpty()){
            onBindData((BaseViewHolder) holder, position,data);
        }
    }

    @Override
    public int getItemCount() {
        return data.isEmpty()?0:data.size();
    }

    public  class BaseViewHolder extends RecyclerView.ViewHolder{
        private Map<Integer,View> mView;
        public BaseViewHolder(View itemView) {
            super(itemView);
            mView = new HashMap<>();
        }
        /**
         * 获取设置的view
         * @param id
         * @return
         */
        public View getView(int id) {
            View view = mView.get(id);
            if (view == null) {
                view = itemView.findViewById(id);
                mView.put(id, view);
            }
            return view;
        }
    }
}
