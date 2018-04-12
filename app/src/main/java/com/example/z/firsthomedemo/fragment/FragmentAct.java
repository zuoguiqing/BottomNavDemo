package com.example.z.firsthomedemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.z.firsthomedemo.R;

/**
 * @author zgq
 * @version 1.0
 * @describe --活动
 * @date 2018/4/4
 */
public class FragmentAct extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null){
            view = inflater.inflate(R.layout.fragment_act, container, false);
        }
        return view;
    }
}
