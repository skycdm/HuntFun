package com.example.cdm.huntfun.MineActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cdm.huntfun.ImagView.ImagView;
import com.example.cdm.huntfun.R;
import com.example.cdm.huntfun.pojo.Constent;
import com.example.cdm.huntfun.pojo.Sport;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class SingleInfo extends AppCompatActivity {
    private ImagView headimg;
    private TextView name;
    private TextView num;
    private TextView sportname;
    private TextView money;
    private TextView begintime;
    private TextView endtime;
    private TextView miaoshu;
    private Button change;
    private Button quxiao;
    Sport sport;
    private android.widget.ImageView ImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_info);


        Intent intent = getIntent();
        String sp = intent.getStringExtra("sport");

        Gson gson = new Gson();

        Type type = new TypeToken<Sport>(){}.getType();

        sport = gson.fromJson(sp,type);

        initView();

    }

    private void initView() {
        ImageView = ((ImageView) findViewById(R.id.img));
        headimg = ((ImagView) findViewById(R.id.headimg));
        name = ((TextView) findViewById(R.id.name));
        num = ((TextView) findViewById(R.id.num));
        sportname = ((TextView) findViewById(R.id.sportname));
        money = ((TextView) findViewById(R.id.money));
        begintime = ((TextView) findViewById(R.id.begintime));
        endtime = ((TextView) findViewById(R.id.endtime));
        miaoshu = ((TextView) findViewById(R.id.miaoshu));
        change = ((Button) findViewById(R.id.change));
        quxiao = ((Button) findViewById(R.id.quxiao));
        initDate();
        click();
    }
    private void click() {
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson = new Gson();
                String gg = gson.toJson(sport);
                Intent intent = new Intent(SingleInfo.this,ChangeSport.class);
                intent.putExtra("sport",gg);
                startActivity(intent);
                finish();
            }
        });
        quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void initDate() {
        RequestParams rp = null;
        try {
            rp = new RequestParams(Constent.URL+"huntfunService/HeadImg?name="+ URLEncoder.encode( sport.getUname(),"utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        x.http().get(rp, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                String[] img = result.split("/");
                x.image().bind(headimg,Constent.URL+"huntfunService/image/"+img[1]);
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

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });
        x.image().bind(ImageView,Constent.URL+"huntfunService/image/"+sport.getImg());
        try {
            name.setText(URLDecoder.decode(sport.getUname(),"utf-8"));
            num.setText("人数:"+ URLDecoder.decode(sport.getNum(),"utf-8")+"");
            sportname.setText(URLDecoder.decode(sport.getSportName(),"utf-8"));
            money.setText("单价:￥"+sport.getMoney()+"");
            begintime.setText("开始时间:"+sport.getTimeBegin());
            endtime.setText("结束时间:"+sport.getTimeEnd());
            miaoshu.setText(URLDecoder.decode(sport.getTheme(),"utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
