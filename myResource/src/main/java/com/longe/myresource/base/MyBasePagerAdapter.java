package com.longe.myresource.base;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * MyBasePagerAdapter  pagerAdapter的基类， 代码复用
 *
 * @author 左桂清
 * @version 1.0, 2016/10/26
 */
public class MyBasePagerAdapter extends PagerAdapter {

    //视图集合
    private ArrayList<View> views = new ArrayList<>();

    //标题集合
    private ArrayList<String> titles = new ArrayList<>();

    /**
     * 添加单个视图
     *
     * @param v
     */
    public void addViewToAdapter(View v) {
        if (v != null) {
            views.add(v);
        }
    }

    /**
     * 添加多个视图
     *
     * @param v
     */
    public void addViewToAdapter(List<View> v) {
        if (v != null) {
            views.addAll(v);
        }
    }

    /**
     * 添加单个标题
     *
     * @param s
     */
    public void addTitleToAdapter(String s) {
        if (s != null) {
            titles.add(s);
        }
    }

    /**
     * 添加多个标题
     *
     * @param s
     */
    public void addTitleToAdapter(List<String> s) {
        if (s != null) {
            titles.addAll(s);
        }
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = views.get(position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = views.get(position);
        container.removeView(view);
    }
}
