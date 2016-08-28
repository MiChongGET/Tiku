package com.example.michong_pc.tiku.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.michong_pc.tiku.R;
import com.example.michong_pc.tiku.bean.GridBean;
import com.example.michong_pc.tiku.bean.TestBean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/28.
 */
public class BaseAdapter extends android.widget.BaseAdapter {
    private LayoutInflater mInflater;
    List<TestBean> list;
    public BaseAdapter(Context context, List<TestBean> list){
        mInflater = LayoutInflater.from(context);
        this.list = list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       convertView = mInflater.inflate(R.layout.list_test,null);
        ImageView iv = (ImageView) convertView.findViewById(R.id.image_test);
        iv.setImageResource(list.get(position).getIcon());

        return convertView;
    }
}
