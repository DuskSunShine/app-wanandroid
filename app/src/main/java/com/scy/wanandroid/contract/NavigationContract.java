package com.scy.wanandroid.contract;

import com.scy.wanandroid.base.AbsPresenter;
import com.scy.wanandroid.base.AbsView;
import com.scy.wanandroid.entity.NavigationListData;

/**
 * @author: SCY
 * @date: 2019/5/28   14:37
 * @version:
 * @desc:
 */
public interface NavigationContract {

    interface View extends AbsView{
        void showNavigationList(NavigationListData navigationListData);
    }


    interface Presenter extends AbsPresenter<View>{
        void getNavigationList();
    }
}
