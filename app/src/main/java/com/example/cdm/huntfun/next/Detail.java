package com.example.cdm.huntfun.next;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cdm.huntfun.R;
import com.example.cdm.huntfun.pojo.Activity;
import com.example.cdm.huntfun.util.NetUtil;

import org.xutils.x;

public class Detail extends AppCompatActivity {

    protected ImageView iv_back;
    protected ImageView iv_desc;
    protected Activity activity;
    protected TextView tv_theme;
    protected TextView kai_shi;

    protected TextView tv_desc_price;
    protected TextView tv_address;
    protected TextView tv_zong_gong;
    protected TextView tv_desc;
    protected TextView tv_care_desc;
    protected TextView tv_desc_price1;
    protected ImageButton shou_c;
    protected Button li_ji;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initView();
        initEvent();
        initData();
    }

    public void initView(){

        iv_back = ((ImageView) findViewById(R.id.iv_back));
        iv_desc = ((ImageView) findViewById(R.id.iv_desc));
        tv_theme = ((TextView) findViewById(R.id.tv_theme));
        kai_shi = ((TextView) findViewById(R.id.kai_shi));
        tv_zong_gong = ((TextView) findViewById(R.id.tv_zong_gong));
        tv_desc_price = ((TextView) findViewById(R.id.tv_desc_price));
        tv_address = ((TextView) findViewById(R.id.tv_address));
        tv_desc = ((TextView) findViewById(R.id.tv_desc));
        tv_care_desc = ((TextView) findViewById(R.id.tv_care_desc));
        tv_desc_price1 = ((TextView) findViewById(R.id.tv_desc_price1));
        shou_c = ((ImageButton) findViewById(R.id.shou_c));
        li_ji = ((Button) findViewById(R.id.li_ji));

    }


    public void initEvent(){
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });
        shou_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shou_c.setImageResource(R.drawable.s_c);
            }
        });
        li_ji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Detail.this,OrderActivity.class);
                intent.putExtra("activity1",activity);
                startActivity(intent);

            }
        });

    }


    public void initData(){
        Intent intent=getIntent();
        activity=intent.getParcelableExtra("activity");
        x.image().bind(iv_desc, NetUtil.url+activity.getActivityImgurl());
        tv_theme.setText(activity.getActivityTheme());
        kai_shi.setText(activity.getActivityBeginTime()+"");
        tv_desc_price.setText(" ￥ "+activity.getActivityCost());
        tv_address.setText(activity.getActivityAddress());
        tv_desc.setText(activity.getActivityDesc());
        tv_care_desc.setText(activity.getActivityCare());
        tv_desc_price1.setText("￥ "+activity.getActivityCost());
    }
}
