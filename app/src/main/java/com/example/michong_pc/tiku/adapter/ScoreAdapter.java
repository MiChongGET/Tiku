package com.example.michong_pc.tiku.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.michong_pc.tiku.R;
import com.example.michong_pc.tiku.bean.ScoreBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MiChong-pc on 2016/7/25.
 */
public class ScoreAdapter extends BaseAdapter {
    private Context context;
    List<ScoreBean> list  = new ArrayList<>();
    //题目的数目

    public ScoreAdapter(Context context, List<ScoreBean> list ){
        this.list=list;
        this.context = context;

    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
         ViewHolder  viewHolder;
        if(convertView ==null){
            convertView  = LayoutInflater.from(context).inflate(R.layout.item_score,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.num = (TextView) convertView.findViewById(R.id.tihao);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.num.setText(list.get(position).getNum());
        return convertView;
    }
    private  class  ViewHolder{
        private TextView num;
    }
}
