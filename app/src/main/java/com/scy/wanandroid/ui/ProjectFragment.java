package com.scy.wanandroid.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scy.wanandroid.R;


public class ProjectFragment extends Fragment {

   private static ProjectFragment projectFragment=null;

   public static ProjectFragment create(){
       if (projectFragment==null){
           synchronized (ProjectFragment.class){
               projectFragment=new ProjectFragment();
           }
       }
       return projectFragment;
   }
    public ProjectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_project, container, false);
    }
}
