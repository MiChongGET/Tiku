package com.example.michong_pc.tiku.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.michong_pc.tiku.R;
import com.example.michong_pc.tiku.bean.GridBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MiChong-pc on 2016/5/23.
 */
public class GridViewAdapter extends BaseAdapter {

    private Context context;
    List<GridBean> list = new ArrayList<>();

    public GridViewAdapter(Context context) {
        this.context = context;

        String name[] = new String[]{"章节练习", "题型训练", "考试模式", "错题集", "标记题目", "公式总结"};
        int ico[] = new int[]{R.drawable.zhangjie2, R.drawable.tixing, R.drawable.test, R.drawable.cuotiji, R.drawable.biaoji, R.drawable.zongjie};
        for (int i = 0, len = name.length; i < len; i++) {
            GridBean bean = new GridBean();
            bean.setName(name[i]);
            bean.setIcon(ico[i]);
            list.add(bean);
        }
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
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
            holder = new ViewHolder();
            holder.ico = (ImageView) convertView.findViewById(R.id.item_icon);
            holder.name = (TextView) convertView.findViewById(R.id.item_name);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }

         holder.ico.setImageResource(list.get(position).getIcon());
        holder.name.setText(list.get(position).getName());

        return convertView;
    }

    private class ViewHolder {
        private ImageView ico;
        private TextView name;

    }

}