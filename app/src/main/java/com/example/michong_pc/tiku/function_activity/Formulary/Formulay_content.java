package com.example.michong_pc.tiku.function_activity.Formulary;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.michong_pc.tiku.R;

import java.util.ArrayList;
import java.util.List;

public class Formulay_content extends AppCompatActivity {
    ExpandableListView el ;
//    private String[] unit_chapter = new String[]{
//            "1.1  气体分子动理论", "1.2  摩尔气体常数", "1.3  理想气体方程", "1.4  分子平均动能分布"
//    };
//    private String[][] content = new String[][]{
//            {"文档编辑", "文档排版", "文档处理", "文档打印"},
//            {"表格编辑", "表格排版", "表格处理", "表格打印"},
//            {"收发邮件", "管理邮箱", "登录登出", "注册绑定"},
//            {"演示编辑", "演示排版", "演示处理", "演示打印"},};
    private ExpandableListView expandableListView;
    private List<String> Group;
    private List<List<String>> Child;
    private List<String> ChildFirst;
    private List<String> ChildSecond;
    private List<String> ChildThird;
    private List<List<Integer>> ChildPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulay_content);
        //传递第几套的数值
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        Toolbar toolbar = (Toolbar) findViewById(R.id.id_tool_bar);
        toolbar.setTitle(b.getString("capter"));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        Group = new ArrayList<String>();
        Group.add("1.1  气体分子动理论");
        Group.add("1.2  摩尔气体常数");
        Group.add("1.3  理想气体方程");
        ChildFirst = new ArrayList<String>();
        ChildFirst.add("我的电脑");
        ChildFirst.add("我的手机");
        ChildFirst.add("我的打印机");

        ChildSecond = new ArrayList<String>();
        ChildSecond.add("大娃");
        ChildSecond.add("二娃");
        ChildSecond.add("三娃");

        ChildThird = new ArrayList<String>();
        ChildThird.add("小炮");
        ChildThird.add("德玛");
        ChildThird.add("亚索");

        Child = new ArrayList<List<String>>();
        Child.add(ChildFirst);
        Child.add(ChildSecond);
        Child.add(ChildThird);

        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        expandableListView.setAdapter(new MyExpandableListViewAdapter(Formulay_content.this));
        //expandableListView.setGroupIndicator(null);
    }

    class MyExpandableListViewAdapter extends BaseExpandableListAdapter {

        private Context context;

        public MyExpandableListViewAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getGroupCount() {
            return Group.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return Child.get(groupPosition).size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return Group.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return Child.get(groupPosition).get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            GroupHolder groupHolder =null;
            if(convertView==null){
                convertView = getLayoutInflater().from(context).inflate(
                        R.layout.groupview, null);      //把界面放到缓冲区
                groupHolder = new GroupHolder();          //实例化我们创建的这个类
                groupHolder.txt = (TextView) convertView.findViewById(R.id.notice_item_date);  //实例化类里的TextView
                groupHolder.txt.setTextSize(20);
                groupHolder.txt.getPaint().setAntiAlias(true);//抗锯齿
                convertView.setTag(groupHolder);                                 //给view对象一个标签，告诉计算机我们已经在缓冲区里放了一个view，下回直                                                                               //接来拿就行了
            } else {
                groupHolder = (GroupHolder) convertView.getTag();     //然后他就直接来拿
            }
            groupHolder.txt.setText(Group.get(groupPosition));//最后在相应的group里设置他相应的Text
//判断isExpanded就可以控制是按下还是关闭，同时更换图片
//            if(isExpanded){
//                parentImageViw.setBackgroundResource(R.drawable.arrow_down);
//            }else{
//                parentImageViw.setBackgroundResource(R.drawable.arrow_up);
//            }
//
           return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            ItemHolder itemHolder = null;
            if (convertView == null) {
                convertView = getLayoutInflater().from(context).inflate(
                        R.layout.child, null);
                itemHolder = new ItemHolder();
                itemHolder.txt = (TextView) convertView.findViewById(R.id.group);
                convertView.setTag(itemHolder);
            } else {
                itemHolder = (ItemHolder) convertView.getTag();
            }
            itemHolder.txt.setText(Child.get(groupPosition).get(
                    childPosition));
            itemHolder.txt.setTextSize(18);
            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return false;
        }
    }
    }

class GroupHolder {
    public TextView txt;
    public ImageView img;
}

class ItemHolder {
    public ImageView img;
    public TextView txt;
}
