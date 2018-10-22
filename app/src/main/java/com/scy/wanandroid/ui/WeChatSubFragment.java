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
public class WeChatSubFragment extends Fragment {

    private static WeChatSubFragment weChatSubFragment = null;

    public static WeChatSubFragment create() {
        if (weChatSubFragment == null) {
            weChatSubFragment = new WeChatSubFragment();
        }
        return weChatSubFragment;
    }
    public WeChatSubFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_we_chat_sub, container, false);
    }

}
