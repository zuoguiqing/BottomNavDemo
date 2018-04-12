package com.longe.myresource.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * MyBaseAdapter  List方面的 adapter 基类，代码复用
 *
 * @author ShiXiang
 * @version 1.0, 2016/10/26
 */
public abstract class MyBaseAdapter<T> extends BaseAdapter {

    protected Context context;
    protected LayoutInflater inflater;

    private List<T> data = new ArrayList<>();

    public MyBaseAdapter(Context context) {
        this.context = context;
        //        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater = LayoutInflater.from(context);
    }

    public List<T> getAllData() {
        return data;
    }

    /**
     * 添加一个数据到集合中
     *
     * @param t
     * @param isClearOld 是否清除旧的数据
     */
    public void addDataToAdapter(T t, boolean isClearOld) {
        if (t == null) {
            return;
        }
        if (isClearOld) {
            data.clear();
        }
        data.add(t);
    }

    /**
     * 添加多个数据到集合中
     *
     * @param t
     * @param isClearOld 是否清除旧数据
     */
    public void addDataToAdapter(List<T> t, boolean isClearOld) {
        if (t == null) {
            return;
        }

        List<T> list = new ArrayList<>(t);

        if (isClearOld) {
            data.clear();
        }

        data.addAll(list);
        notifyDataSetChanged();
    }

    //添加一个数据到集合的头部
    public void addDataToHead(T t) {
        if (t == null) {
            return;
        }
        data.add(0, t);
    }


    //添加多个数据到集合的头部
    public void addDataToHead(ArrayList<T> t) {
        if (t == null) {
            return;
        }
        data.addAll(0, t);
    }

    //更新adapter的集合中的数据
    public void updataAdapter() {
        this.notifyDataSetChanged();
    }

    //清楚adapter的集合中的数据
    public void clearData() {
        data.clear();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public T getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getMyView(position, convertView, parent);
    }

    //用来供继承的适配器实现此方法，返回视图
    public abstract View getMyView(int position, View convertView, ViewGroup parent);

}
