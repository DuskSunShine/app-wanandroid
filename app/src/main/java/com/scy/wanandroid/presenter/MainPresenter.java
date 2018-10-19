package com.scy.wanandroid.presenter;

import android.support.v7.widget.AppCompatCheckedTextView;

import com.scy.wanandroid.R;
import com.scy.wanandroid.base.BasePresenter;
import com.scy.wanandroid.constants.Constants;
import com.scy.wanandroid.contract.MainContract;

import me.logg.Logg;

/**
 * Created by SCY on 2018/10/19 at 10:22.
 */
public class MainPresenter extends BasePresenter<MainContract.MainView>
implements MainContract.Presenter{

    @Override
    protected void startInteractive() {
        mView.addFragment();
        mView.initShow();
    }


    @Override
    public void  setCurrentPage(AppCompatCheckedTextView view) {
        switch (view.getId()){
            case R.id.homePage:
                Logg.e(R.id.homePage);
                mView.showFragment(Constants.HOME);
                break;
                case R.id.othersPage:
                    Logg.e(R.id.othersPage);
                    mView.showFragment(Constants.OTHERS);
                break;
        }
    }
}
