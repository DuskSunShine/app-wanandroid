package com.scy.wanandroid.base;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import com.scy.wanandroid.R;
import com.scy.wanandroid.utils.AppManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created on 2017/7/27 0027.
 */

public abstract class BaseSwipeActivity<P extends AbsPresenter> extends SwipeBackActivity
        implements View.OnClickListener ,AbsView{
    protected SwipeBackLayout mSwipeBackLayout;
    protected P mPresenter;
    private Unbinder unBinder;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //huawei 8.0 shutdown
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(getContentViewId());
        unBinder=ButterKnife.bind(this);
        mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);

        //set activity can scroll
        mSwipeBackLayout.setEnableGesture(true);
        // get display with
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int phoneWidth = dm.widthPixels;

        //set scroll size,default is 50dip,屏幕一半的位置就可以滑动销毁
        //mSwipeBackLayout.setEdgeSize(phoneWidth / 2);

        AppManager.getInstance().addActivity(this);

        if (getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        AppManager.getInstance().addActivity(this);
        mPresenter=createPresenter();
        if (mPresenter!=null) {
            mPresenter.attachView(this);
        }else {
            throw  new NullPointerException(mPresenter.getClass().getSimpleName()+"未创建");
        }
        initDataAndEvents();
    }

    public abstract int getContentViewId();

    public abstract P createPresenter();

    public abstract void initDataAndEvents();



    /**
     * It doesn't have to be forced findviewbyid
     */
    public <T extends View> T findViewByIdNoCast(int id) {
        return (T) findViewById(id);
    }

    public void setOnClick(int... ids) {
        for (int id : ids)
            findViewById(id).setOnClickListener(this);

    }

    public void setOnClick(View... views) {
        for (View view : views)
            view.setOnClickListener(this);

    }

    /**
     * Set the View to the return key
     */
    protected void setToBack(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    /**
     * Set the View to the return key
     */
    protected void setToBack(int id) {
        findViewById(id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().remove(this);
        if (mPresenter!=null&&
                mPresenter.isAttachedView()){
            mPresenter.detachView();
            mPresenter=null;
        }
        if (unBinder != null && unBinder != Unbinder.EMPTY) {
            unBinder.unbind();
            unBinder = null;
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.right_in_show,R.anim.right_out);
    }
}
