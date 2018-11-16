package com.scy.wanandroid.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scy.wanandroid.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationFragment extends Fragment {

    private static NavigationFragment navigationFragment=null;

    public static NavigationFragment create(){
        if (navigationFragment==null){
            navigationFragment=new NavigationFragment();
        }
        return navigationFragment;
    }
    public NavigationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_navigation, container, false);
    }

}
