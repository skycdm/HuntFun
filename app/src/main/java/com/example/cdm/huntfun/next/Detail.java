package com.example.cdm.huntfun.next;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cdm.huntfun.R;
import com.example.cdm.huntfun.pojo.Activity;
import com.example.cdm.huntfun.util.NetUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    protected ImageView iv_tou_xiang;
    private int userId;
    private boolean flag1 = false;
    private int  activityId;
    private int activityNumer;
    private long l;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initView();

        initData();

        initEvent();
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
        iv_tou_xiang = ((ImageView) findViewById(R.id.iv_tou_xiang));

    }


    public void initEvent(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String time=sdf.format(activity.getActivityBeginTime());
        Log.e("sss","====================================="+time);
        Date curDate=new Date(System.currentTimeMillis());
        String str=sdf.format(curDate);
        Date d1=null;
        try {
            d1=sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date d2=null;
        try {
            d2=sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
         l=d1.getTime()-d2.getTime();


        SharedPreferences sharedPreferences=getSharedPreferences("guanzhu_sp",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        //editor.putString("shoucang")
        String shoucang = sharedPreferences.getString("shoucang","");
        String[] ids = shoucang.split("|");
         activityId = activity.getActivityId();
        for(String id:ids){
            if(id.equals(activityId+"")){
                flag1 = true;
            }
        }

        if (flag1){
            shou_c.setImageResource(R.drawable.s_c);
        }else{
            shou_c.setImageResource(R.drawable.shou_cang);
        }
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });
        shou_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences=getSharedPreferences("guanzhu_sp",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                //editor.putString("shoucang")
                String shoucang = sharedPreferences.getString("shoucang","");
                String[] ids = shoucang.split("|");   //1|3|12
                activityId = activity.getActivityId();
                if (flag1){//选中状态
                    shou_c.setImageResource(R.drawable.shou_cang);
                    // 数据库操作：删除收藏在该数据库的活动
                      delete();

                    Toast.makeText(Detail.this,"取消收藏",Toast.LENGTH_SHORT).show();
                    String s = "";
                    for(String id:ids){
                        if(!id.equals(activityId+"")){
                            s+=id+"|";
                        }
                    }
                    editor.putString("shoucang",s.substring(0,s.length()-1));
                    flag1=false;
                }else{
                    shou_c.setImageResource(R.drawable.s_c);
                    //数据库操作：添加收藏该活动
                    collect();
                    Toast.makeText(Detail.this,"收藏成功",Toast.LENGTH_SHORT).show();
                    String s = "";
                    for (String id:ids){
                        if (!id.equals(activityId+"")){
                            s+=id+"|";
                        }
                        s=s+activityId;
                    }
                    editor.putString("shoucang",s.substring(0,s.length()));
                    flag1=true;
                }
                editor.commit();
            }
        });
        li_ji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (activityNumer<=0){
                    Toast.makeText(getApplicationContext(),"对不起，活动数量已不足",Toast.LENGTH_SHORT).show();
                }else if (l>0){
                    Toast.makeText(getApplicationContext(),"对不起,该活动已结束",Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(Detail.this, OrderActivity.class);
                    intent.putExtra("activity1", activity);
                    intent.putExtra("activityNumber",activityNumer);
                    startActivity(intent);
                }
            }
        });

        iv_tou_xiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Detail.this,UserActivity.class);
                intent.putExtra("activity2",activity);
                startActivity(intent);
            }
        });

    }


    public void initData(){

        Intent intent=getIntent();
        activity=intent.getParcelableExtra("activity");
        x.image().bind(iv_desc, NetUtil.url+activity.getActivityImgurl());
        x.image().bind(iv_tou_xiang, NetUtil.url+activity.getUser().getImageUrl());
        tv_theme.setText(activity.getActivityTheme());
        kai_shi.setText(new SimpleDateFormat("yyyy-MM-dd").format(activity.getActivityBeginTime()));
        getNumber();
        //    tv_zong_gong.setText(activity.getActivityPeopleNumber()+"");
        tv_desc_price.setText(" ￥ "+activity.getActivityCost());
        tv_address.setText(activity.getActivityAddress());
        tv_desc.setText(activity.getActivityDesc());
        tv_care_desc.setText(activity.getActivityCare());
        tv_desc_price1.setText("￥ "+activity.getActivityCost());
    }

    public void collect(){

        RequestParams requestParams =new RequestParams(NetUtil.url+"insert_collect");
        requestParams.addQueryStringParameter("userId",1+"");
        requestParams.addQueryStringParameter("activityId",activity.getActivityId()+"");
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
    public void delete(){
        RequestParams requestParams=new RequestParams(NetUtil.url+"delete_collect");
        requestParams.addQueryStringParameter("userId",1+"");
        requestParams.addQueryStringParameter("activityId",activity.getActivityId()+"");
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
    public void getNumber(){
        RequestParams requestParams=new RequestParams(NetUtil.url+"get_number");
        requestParams.addQueryStringParameter("activityId",activity.getActivityId()+"");
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson=new Gson();
                Type type=new TypeToken<Integer>(){}.getType();
                activityNumer=gson.fromJson(result,type);
                Log.e("ss","======================================"+activityNumer);
                tv_zong_gong.setText(activityNumer+"");
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
