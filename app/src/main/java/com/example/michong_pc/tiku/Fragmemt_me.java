package com.example.michong_pc.tiku;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.List;
import java.util.Map;

/**
 * Created by MiChong-pc on 2016/5/21.
 */
public class Fragmemt_me extends android.support.v4.app.Fragment {
    private GridView gridView ;
    private List<Map<String,Object>>datalist;
    private  String[] number = {"0"};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root  = inflater.inflate(R.layout.fragment_me,null);

        return root;

    }
}
