package com.example.cdm.huntfun.next;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cdm.huntfun.R;
import com.example.cdm.huntfun.pojo.Activity;
import com.example.cdm.huntfun.util.NetUtil;

import org.xutils.x;

public class OrderActivity extends AppCompatActivity {

    protected ImageView iv_order;
    protected TextView tv_order_time;
    protected Activity activity;
    protected ImageView iv_back1;
    protected Button cart_item_jian;
    protected TextView order_item_number;
    protected Button cart_item_jia;
    protected TextView tv_order_money;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        initView();
        initData();
        initEvent();

    }
    public void initView(){

        iv_order = ((ImageView) findViewById(R.id.iv_order));
        tv_order_time = ((TextView) findViewById(R.id.tv_order_time));
        iv_back1 = ((ImageView) findViewById(R.id.iv_back1));
        cart_item_jian = ((Button) findViewById(R.id.cart_item_jian));
        order_item_number = ((TextView) findViewById(R.id.order_item_number));
        cart_item_jia = ((Button) findViewById(R.id.cart_item_jia));
        tv_order_money = ((TextView) findViewById(R.id.tv_order_money));
    }

    public void initEvent(){

        final double allMoney=activity.getActivityCost();

        tv_order_money.setText(" ￥"+allMoney);

        iv_back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        cart_item_jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               order_item_number.setText(Integer.parseInt(order_item_number.getText().toString())+1+"");
                tv_order_money.setText(" ￥"+(allMoney*Integer.parseInt(order_item_number.getText().toString())));
            }
        });
        cart_item_jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(order_item_number.getText().toString())>1){
                    order_item_number.setText(Integer.parseInt(order_item_number.getText().toString())-1+"");
                }
                tv_order_money.setText(" ￥"+(allMoney*Integer.parseInt(order_item_number.getText().toString())));
            }
        });
    }

    public void initData(){
        Intent intent=getIntent();
        activity=intent.getParcelableExtra("activity1");
        x.image().bind(iv_order,NetUtil.url+activity.getActivityImgurl());
        tv_order_time.setText(activity.getActivityBegintime()+"");
    }
}
