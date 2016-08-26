package com.example.michong_pc.tiku;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.michong_pc.tiku.adapter.GridViewAdapter;
import com.example.michong_pc.tiku.function_activity.Test;
import com.example.michong_pc.tiku.function_activity.train;
import com.example.michong_pc.tiku.function_activity.zhangjiexunlian;

/**
 * Created by MiChong-pc on 2016/5/21.
 */
public class Fragment_study extends android.support.v4.app.Fragment {
    private ImageView imageView;
    private GridView gridView;
    private GridViewAdapter adapter;
    View root;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_study_two, container, false);
        initView();
        return root;
    }

    private void initView() {
        gridView = (GridView) root.findViewById(R.id.gridview);
        adapter = new GridViewAdapter(getActivity());
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), ""+position, Toast.LENGTH_SHORT).show();
                switch (position){
                    case 0:
                        startActivity(new Intent(getActivity(),zhangjiexunlian.class));
                        break;
                    case 1:
                        startActivity(new Intent(getActivity(),train.class));
                        break;
                    case 2:
                        startActivity(new Intent(getActivity(),Test.class));
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                }
            }
        });
    }

}
