

package com.scy.wanandroid.widget;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Interpolator;

/**
 * FAB 行为控制器,当页面向下滑动，fab显示出来
  */

public class ScrollDownShowBehavior extends FloatingActionButton.Behavior {

    private static final Interpolator FAST_OUT_SLOW_IN_INTERPOLATOR = new LinearOutSlowInInterpolator();
    /**
     * 是否正在动画
     */
    private boolean isAnimateIng = false;

    /**
     * 是否已经显示
     */
    private boolean isShow = true;

    public ScrollDownShowBehavior(Context context, AttributeSet attrs) {
        super();
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout,
                                       FloatingActionButton child, View directTargetChild,
                                       View target, int nestedScrollAxes) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL
                || super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child,
                               View target, int dxConsumed, int dyConsumed,
                               int dxUnconsumed, int dyUnconsumed) {
        // 手指上滑，隐藏FAB
        if ((dyConsumed > 0 || dyUnconsumed > 0) && !isAnimateIng && isShow) {
            translateHide(child, new StateListener() {
                @Override
                public void onAnimationStart(View view) {
                    super.onAnimationStart(view);
                    isShow = false;
                }
            });
        } else if ((dyConsumed < 0 || dyUnconsumed < 0 && !isAnimateIng && !isShow)) {
            // 手指下滑，显示FAB
            translateShow(child, new StateListener() {
                @Override
                public void onAnimationStart(View view) {
                    super.onAnimationStart(view);
                    isShow = true;
                }
            });
        }
    }

    class StateListener implements ViewPropertyAnimatorListener {
        @Override
        public void onAnimationStart(View view) {
            isAnimateIng = true;
        }

        @Override
        public void onAnimationEnd(View view) {
            isAnimateIng = false;
        }

        @Override
        public void onAnimationCancel(View view) {
            isAnimateIng = false;
        }
    }

    /**
     * 隐藏view
     *
     * @param view View
     * @param viewPropertyAnimatorListener ViewPropertyAnimatorListener
     */
    private  void translateHide(View view, ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
        view.setVisibility(View.VISIBLE);
        ViewCompat.animate(view)
                .translationY(350)
                .setDuration(400)
                .setInterpolator(FAST_OUT_SLOW_IN_INTERPOLATOR)
                .setListener(viewPropertyAnimatorListener)
                .start();
    }

    /**
     * 显示view
     *
     * @param view View
     * @param viewPropertyAnimatorListener ViewPropertyAnimatorListener
     */
    private  void translateShow(View view, ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
        view.setVisibility(View.VISIBLE);
        ViewCompat.animate(view)
                .translationY(0)
                .setDuration(400)
                .setListener(viewPropertyAnimatorListener)
                .setInterpolator(FAST_OUT_SLOW_IN_INTERPOLATOR)
                .start();
    }
}