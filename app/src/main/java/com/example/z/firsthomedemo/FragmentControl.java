package com.example.z.firsthomedemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;

/**
 * @author zgq
 * @version 1.0  2018/4/4
 * @describe --
 */
public class FragmentControl {

    private FragmentManager fm;
    private int containerId;
    private ArrayList<Fragment> fragments;

    private static FragmentControl fragmentControl;


    public static FragmentControl getInstance(FragmentActivity activity, ArrayList<Fragment> fragments, int containerId) {

        if (fragmentControl == null) {
            synchronized (FragmentControl.class) {
                if (fragmentControl == null) {
                    fragmentControl = new FragmentControl(activity, fragments, containerId);
                }
            }
        }
        return fragmentControl;
    }

    private FragmentControl(FragmentActivity activity, ArrayList<Fragment> mFragments, int containerId) {
        this.containerId = containerId;
        this.fragments = mFragments;
        fm = activity.getSupportFragmentManager();
        initFragment();
    }

    private void initFragment() {
        FragmentTransaction ft = fm.beginTransaction();
        for (Fragment fragment : fragments) {
            ft.add(containerId, fragment);
        }
        ft.commit();
    }

    public void showFragment(int position){
        hideFragments();
        Fragment fragment = fragments.get(position);
        FragmentTransaction ft = fm.beginTransaction();
        if (fragment != null){
            ft.show(fragment);
        }
        ft.commit();
    }

    public void hideFragments() {
        FragmentTransaction ft = fm.beginTransaction();
        for (Fragment f : fragments) {
            if (f != null) {
                ft.hide(f);
            }
        }
        ft.commit();
    }

    public Fragment getFragment(int position){
        return fragments.get(position);
    }


}


