package com.example.z.firsthomedemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @author zgq
 * @version 1.0
 * @describe --
 * @date 2018/4/4
 */
public class MainPagerAdp extends FragmentPagerAdapter {

    private List<Fragment> fragments;

    public MainPagerAdp(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
