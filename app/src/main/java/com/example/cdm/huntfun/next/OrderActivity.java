package com.example.cdm.huntfun.next;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    protected Button bt_commit;
    private int yu_number;
    protected EditText et_name;
    protected EditText et_phoneNumber;
    private int activityNumber;
    private double money;
    private double allMoney;


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
        bt_commit = ((Button) findViewById(R.id.bt_commit));
        et_name = ((EditText) findViewById(R.id.et_name));
        et_phoneNumber = ((EditText) findViewById(R.id.et_phoneNumber));
    }

    public void initEvent(){



        bt_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(OrderActivity.this,CountActivity.class);
                intent.putExtra("image",activity.getActivityImgurl());
                intent.putExtra("beginTime",activity.getActivityBeginTime()+"");
                intent.putExtra("yu_number",yu_number);
                intent.putExtra("yu_name",et_name.getText().toString());
                intent.putExtra("yu_phoneNumber",et_phoneNumber.getText().toString());
                intent.putExtra("allMoney",allMoney);
                intent.putExtra("activityId",activity.getActivityId());
                startActivity(intent);
            }
        });


        money=activity.getActivityCost();
        yu_number=Integer.parseInt(order_item_number.getText().toString());

        tv_order_money.setText(" ￥"+money);
        allMoney=money;

        iv_back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        cart_item_jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (yu_number<activityNumber) {
                        yu_number++;
                        order_item_number.setText(yu_number + "");
                    }else {
                        Toast.makeText(getApplicationContext(),"对不起预约数量已达上限",Toast.LENGTH_SHORT).show();
                    }
                        allMoney = money * yu_number;
                        tv_order_money.setText(" ￥" + allMoney);
                }
        });
        cart_item_jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (yu_number>1){
                    yu_number--;
                    order_item_number.setText(yu_number+"");
                }
                allMoney=money*yu_number;
                tv_order_money.setText(" ￥"+allMoney);
            }
        });
    }

    public void initData(){
        Intent intent=getIntent();
        activity=intent.getParcelableExtra("activity1");
        activityNumber=intent.getIntExtra("activityNumber",0);
        x.image().bind(iv_order, NetUtil.url+activity.getActivityImgurl());
        tv_order_time.setText(activity.getActivityBeginTime()+"");
    }
}
