package com.example.michong_pc.tiku;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import java.util.ArrayList;

public class Activity_home extends AppCompatActivity {
    private Fragment contentFragment;
    private Fragment_study study;
    private Fragment_community community;
    private Fragmemt_me me;
    private ArrayList<AHBottomNavigationItem> bottomNavigationItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        intUI();
    }
    public void intUI(){
        AHBottomNavigation bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);

// Create items
            AHBottomNavigationItem item1 = new AHBottomNavigationItem("学习", R.drawable.study, Color.parseColor("#455C65"));
        //AHBottomNavigationItem item2 = new AHBottomNavigationItem("社区", R.drawable.community, Color.parseColor("#00886A"));
        AHBottomNavigationItem item3 = new AHBottomNavigationItem("我", R.drawable.me, Color.parseColor("#8B6B62"));

// Add items
//        bottomNavigation.addItem(item1);
//        bottomNavigation.addItem(item2);
//        bottomNavigation.addItem(item3);

        bottomNavigationItems.add(item1);
        //bottomNavigationItems.add(item2);
        bottomNavigationItems.add(item3);
        bottomNavigation.addItems(bottomNavigationItems);
        bottomNavigation.setColored(false);
// Set background color
        //bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#ff0000"));

// Change colors
        bottomNavigation.setAccentColor(Color.parseColor("#F63D2B"));
        bottomNavigation.setInactiveColor(Color.parseColor("#747474"));

// Use colored navigation with circle reveal effect
        bottomNavigation.setColored(false);
        study = new Fragment_study();

        getSupportFragmentManager().beginTransaction().replace(R.id.main_content,study).commit();
// Set listener
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {

            @Override
            public void onTabSelected(int position,boolean wasSelected) {
                // Do something cool here...
                Toast.makeText(Activity_home.this,"位置"+position,Toast.LENGTH_SHORT).show();
                switch(position){
                    case 0:
                        study = new Fragment_study();
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_content,study).commit();
                        break;
//                    case 1:
//                        community = new Fragment_community();
//                        getSupportFragmentManager().beginTransaction().replace(R.id.main_content,community).commit();
//                        break;
                    case 1:
                        me  = new Fragmemt_me();
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_content,me).commit();
                        break;
                }
            }
        });
    }
}
