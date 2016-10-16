package com.example.michong_pc.tiku;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.michong_pc.tiku.ViewFlipper.MyViewFlipper;
import com.example.michong_pc.tiku.adapter.GridViewAdapter;
import com.example.michong_pc.tiku.function_activity.Error_mode.Error_Chapter;
import com.example.michong_pc.tiku.function_activity.Formulary.Formulay_chapter;
import com.example.michong_pc.tiku.function_activity.Sign.Sign;
import com.example.michong_pc.tiku.function_activity.Test_mode.Test;
import com.example.michong_pc.tiku.function_activity.Train_mode.train;
import com.example.michong_pc.tiku.function_activity.Chapter_mode.zhangjiexunlian;

/**
 * Created by MiChong-pc on 2016/5/21.
 */
public class Fragment_study extends android.support.v4.app.Fragment {
    private ImageView imageView;
    private GridView gridView;
    private GridViewAdapter adapter;
    private ViewFlipper viewFlipper;
    private float startx;
    int[] images = new int[]{R.drawable.pic,R.drawable.pic,R.drawable.pic};
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

        viewFlipper = (ViewFlipper) root.findViewById(R.id.pic);

        for (int i=0;i<images.length;i++){
            viewFlipper.addView(getImageView(images[i]));
        }
        viewFlipper.setInAnimation(getActivity(),R.anim.left_out);
        viewFlipper.setFlipInterval(3000);
        viewFlipper.startFlipping();
        root.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                    {
                        startx = event.getX();
                        break;
                    }
                    case MotionEvent.ACTION_MOVE:{
                        if (event.getX()-startx>100){
                            viewFlipper.setInAnimation(getActivity(),R.anim.push_right_in);
                            viewFlipper.showPrevious();
                        }
                        if(event.getX()-startx<100){
                            viewFlipper.setInAnimation(getActivity(),R.anim.push_left_out);
                            viewFlipper.showNext();
                        }
                        break;
                        }
                    case MotionEvent.ACTION_UP:{
                        break;
                    }
                    }
                return true;
            }
        });
        return root;
    }

    private ImageView getImageView(int resId){
        ImageView imageView = new ImageView(getActivity());
        imageView.setBackgroundResource(resId);
        return  imageView;
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
                        startActivity(new Intent(getActivity(),Error_Chapter.class));
                        break;
                    case 4:
                        startActivity(new Intent(getActivity(), Sign.class));
                        break;
                    case 5:
                        startActivity(new Intent(getActivity(),Formulay_chapter.class));
                        break;
                }
            }
        });
    }

}
