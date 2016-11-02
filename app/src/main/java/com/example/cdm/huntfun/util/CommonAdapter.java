package com.example.cdm.huntfun.util;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by 执行Z on 2016/10/11.
 */
public abstract class CommonAdapter<T> extends BaseAdapter {

    Context context;
    List<T> lists;
    int layoutId;
    public CommonAdapter(Context context, List<T> lists,int layoutId){

        this.context=context;
        this.lists=lists;
        this.layoutId=layoutId;

    }


    @Override
    public int getCount() {
        return (lists!=null)?lists.size():0;
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);//每个item的数据源
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //找到控件，赋值
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder=ViewHolder.get(context,layoutId,convertView,parent);
        convert(viewHolder,lists.get(position),position);
        return viewHolder.getConvertView();
    }


    //取出控件，赋值
    public abstract void  convert(ViewHolder viewHolder,T t,int position);

    public void remove(int position){
        lists.remove(position);//删除
        this.notifyDataSetChanged();//刷新，更改适配器对象的数据源
    }
}


