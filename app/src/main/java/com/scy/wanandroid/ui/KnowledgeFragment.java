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
public class KnowledgeFragment extends Fragment {

    private static KnowledgeFragment knowledgeFragment = null;

    public static KnowledgeFragment create() {
        if (knowledgeFragment == null) {
            knowledgeFragment = new KnowledgeFragment();
        }
        return knowledgeFragment;
    }

    public KnowledgeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_others, container, false);
    }

}
