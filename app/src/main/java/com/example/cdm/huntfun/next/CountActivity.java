package com.example.cdm.huntfun.next;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cdm.huntfun.R;
import com.example.cdm.huntfun.util.NetUtil;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class CountActivity extends AppCompatActivity {

    protected TextView tv_count_time;
    protected TextView tv_count;
    protected TextView tv_count_name;
    protected TextView tv_count_phone;
    protected TextView tv_count_money;
    protected ImageView iv_count_image;
    protected Button bt_commit;
    private int activityId;
    private int yu_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count);
        initView();
        initData();
        initEvent();
    }
    public void initView(){
        tv_count_time = ((TextView) findViewById(R.id.tv_count_time));
        tv_count = ((TextView) findViewById(R.id.tv_count));
        tv_count_name = ((TextView) findViewById(R.id.tv_count_name));
        tv_count_phone = ((TextView) findViewById(R.id.tv_count_phone));
        tv_count_money = ((TextView) findViewById(R.id.tv_count_money));
        iv_count_image = ((ImageView) findViewById(R.id.iv_count_image));
        bt_commit = ((Button) findViewById(R.id.bt_commit));
    }
    public void initData(){
        Intent intent=getIntent();
        String image=intent.getStringExtra("image");
        activityId=intent.getIntExtra("activityId",0);
        yu_number=intent.getIntExtra("yu_number",0);
        String yu_name=intent.getStringExtra("yu_name");
        String yu_phone=intent.getStringExtra("yu_phoneNumber");
        Double allMoney=intent.getDoubleExtra("allMoney",0);
        x.image().bind(iv_count_image, NetUtil.url+image);
        tv_count_time.setText(intent.getStringExtra("beginTime")+"");
        tv_count.setText(yu_number+"");
        tv_count_name.setText(yu_name);
        tv_count_phone.setText(yu_phone);
        tv_count_money.setText("￥ "+allMoney);
    }
    public void initEvent(){
        bt_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"支付成功",Toast.LENGTH_SHORT).show();
                updateNumber();
            }
        });
    }

    public void updateNumber(){
        RequestParams requestParams=new RequestParams(NetUtil.url+"UpdateNumberServlet");
        requestParams.addQueryStringParameter("activityId",activityId+"");
        requestParams.addQueryStringParameter("number",yu_number+"");
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

}
