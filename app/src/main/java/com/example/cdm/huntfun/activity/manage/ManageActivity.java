package com.example.cdm.huntfun.activity.manage;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.cdm.huntfun.R;
import com.example.cdm.huntfun.activity.manage.fragment.FragmentJoin;
import com.example.cdm.huntfun.activity.manage.fragment.FragmentPublish;

public class ManageActivity extends AppCompatActivity implements View.OnClickListener {

    Fragment[] fragments;
    FragmentJoin fragmentJoin;
    FragmentPublish fragmentPublish;
    //按钮的数组，一开始第一个按钮被选中
    Button[] tabs;

    int oldIndex;//用户看到的item
    int newIndex;//用户即将看到的item
    private Button btn_jion_frg;
    private Button btn_publish_frg;

    private ImageView iv_bank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);

        //初始化fragment
        fragmentJoin=new FragmentJoin();
        fragmentPublish=new FragmentPublish();

        //所有fragment的数组
        fragments=new Fragment[]{fragmentJoin,fragmentPublish};

        //设置按钮的数组
        tabs=new Button[3];
        tabs[0]=(Button) findViewById(R.id.btn_jion_frg);
        tabs[1]=(Button) findViewById(R.id.btn_publish_frg);
        //界面初始显示第一个fragment;添加第一个fragment
        getSupportFragmentManager().beginTransaction().add(R.id.frag, fragments[0]).commit();
        //初始时，按钮1选中
        tabs[0].setSelected(true);

        btn_jion_frg = ((Button) findViewById(R.id.btn_jion_frg));
        btn_publish_frg = ((Button) findViewById(R.id.btn_publish_frg));
        btn_jion_frg.setOnClickListener(this);
        btn_publish_frg.setOnClickListener(this);

        iv_bank = ((ImageView) findViewById(R.id.iv_bank));
        iv_bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        //点击按钮时，表示选中不同的项
        switch(v.getId()) {
            case R.id.btn_jion_frg:
                newIndex = 0;//选中第一项
                break;

            case R.id.btn_publish_frg:
                newIndex = 1;//选中第二项
                break;
        }
        switchFragment();
    }

    private void switchFragment() {
        FragmentTransaction transaction;
        //如果选择的项不是当前选中项，则替换；否则，不做操作
        if(newIndex!=oldIndex){

            transaction=getSupportFragmentManager().beginTransaction();

            transaction.hide(fragments[oldIndex]);//隐藏当前显示项


            //如果选中项没有加过，则添加
            if(!fragments[newIndex].isAdded()){
                //添加fragment
                transaction.add(R.id.frag,fragments[newIndex]);
            }
            //显示当前选择项
            transaction.show(fragments[newIndex]).commit();
        }
        //之前选中的项，取消选中
        tabs[oldIndex].setSelected(false);
        //当前选择项，按钮被选中
        tabs[newIndex].setSelected(true);


        //当前选择项变为选中项
        oldIndex=newIndex;
    }
}
