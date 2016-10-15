package com.example.michong_pc.tiku;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.michong_pc.tiku.Login.Login;

import java.util.List;
import java.util.Map;

/**
 * Created by MiChong-pc on 2016/5/21.
 */
public class Fragmemt_me extends android.support.v4.app.Fragment {
    private GridView gridView ;
    private List<Map<String,Object>>datalist;
    private TextView name;
    private TextView number;
    private SharedPreferences mSharedPreferences;
    private Button exit;
    private SharedPreferences.Editor editor;
    public View root;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSharedPreferences= getActivity().getSharedPreferences("user",Context.MODE_PRIVATE);
         editor = mSharedPreferences.edit();

    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root  = inflater.inflate(R.layout.fragment_me,null);
        name = (TextView) root.findViewById(R.id.name);
        number = (TextView) root.findViewById(R.id.number);

        //获取用户名和学号
        name.setText("姓名： "+mSharedPreferences.getString("name","null"));
        number.setText("学号： "+mSharedPreferences.getString("num","null"));
//        name.setText("123123");
//        number.setText("qweqweqw");
        exit = (Button) root.findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editor.clear();
                editor.commit();
                Toast.makeText(getActivity(),"已经退出",Toast.LENGTH_LONG).show();
                startActivity(new Intent(getActivity(), Login.class));

            }
        });

        return root;

    }
}
