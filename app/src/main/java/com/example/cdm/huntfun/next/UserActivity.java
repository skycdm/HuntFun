package com.example.cdm.huntfun.next;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cdm.huntfun.R;
import com.example.cdm.huntfun.pojo.Activity;
import com.example.cdm.huntfun.pojo.User;
import com.example.cdm.huntfun.util.NetUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity {

    protected RelativeLayout rl_tou;
    protected ImageButton ib_guanzhu;

    protected ImageView iv_user_touxiang;
    protected TextView tv_user_nick;
    protected TextView tv_user_desc;
    private Activity activity;
    private Integer activityId;
    private boolean flag = false;
    protected TextView tv_fensi;
    private List<User> userList=new ArrayList<User>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        initView();

        initData();

        initEvent();

    }

    public void initView(){
        rl_tou = ((RelativeLayout) findViewById(R.id.rl_user_touming));
        rl_tou.getBackground().setAlpha(65);
        iv_user_touxiang = ((ImageView) findViewById(R.id.iv_user_touxiang));
        ib_guanzhu = ((ImageButton) findViewById(R.id.ib_guanzhu));
        tv_user_nick = ((TextView) findViewById(R.id.tv_user_nick));
        tv_user_desc = ((TextView) findViewById(R.id.tv_user_desc));
        tv_fensi = ((TextView) findViewById(R.id.tv_fensi));
    }
    public void initEvent(){
        SharedPreferences sharedPreferences=getSharedPreferences("sp",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        //editor.putString("shoucang")
        String shoucang = sharedPreferences.getString("guanzhu","");
        String[] ids = shoucang.split("|");
        activityId = activity.getActivityId();
        for(String id:ids){
            if(id.equals(activityId+"")){
                flag = true;
            }
        }

        if (flag){
            ib_guanzhu.setImageResource(R.drawable.guan2);
        }else{
            ib_guanzhu.setImageResource(R.drawable.guan1);
        }
        ib_guanzhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences=getSharedPreferences("sp",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                //editor.putString("shoucang")
                String shoucang = sharedPreferences.getString("guanzhu","");
                String[] ids = shoucang.split("|");   //1|3|12
                activityId = activity.getActivityId();

                if (flag){//选中状态
                    ib_guanzhu.setImageResource(R.drawable.guan1);
                    // 数据库操作：删除在该数据库中2个人的关联
                    deleteConcern();

                    Toast.makeText(UserActivity.this,"取消关注",Toast.LENGTH_SHORT).show();
                    String s = "";
                    for(String id:ids){
                        if(!id.equals(activityId+"")){
                            s+=id+"|";
                        }
                    }
                    editor.putString("guanzhu",s.substring(0,s.length()-1));
                    flag=false;
                }else{
                    ib_guanzhu.setImageResource(R.drawable.guan2);
                    //  数据库操作：在数据库中加入两个关联：

                     insertConcern();

                    Toast.makeText(UserActivity.this,"关注成功",Toast.LENGTH_SHORT).show();
                    String s = "";
                    for (String id:ids){
                        if (!id.equals(activityId+"")){
                            s+=id+"|";
                        }
                        s=s+activityId;
                    }
                    editor.putString("guanzhu",s.substring(0,s.length()));
                    flag=true;
                }
                editor.commit();
            }
        });
    }
    public void initData(){
        Intent intent=getIntent();
        activity= intent.getParcelableExtra("activity2");

        tv_user_nick.setText(activity.getUser().getUserName());
        tv_user_desc.setText(activity.getUser().getUserIntroduce());
        x.image().bind(iv_user_touxiang, NetUtil.url+activity.getUser().getImageUrl());
        getUserByConcernId();
    }
    public void insertConcern(){
        RequestParams requestParams=new RequestParams(NetUtil.url+"InsertConcernServlet");
        requestParams.addQueryStringParameter("userId",1+"");
        requestParams.addQueryStringParameter("concernUserId",activity.getUser().getUserId()+"");
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
    public void deleteConcern(){
        RequestParams requestParams=new RequestParams(NetUtil.url+"DeleteConcernServlet");
        requestParams.addQueryStringParameter("userId",1+"");
        requestParams.addQueryStringParameter("concernUserId",activity.getUser().getUserId()+"");
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
    public void getUserByConcernId(){
        RequestParams requestParams=new RequestParams(NetUtil.url+"GetUserByConcernIdServlet");
        requestParams.addQueryStringParameter("concernUserId",activity.getUser().getUserId()+"");
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                Gson gson=new Gson();
                Type type=new TypeToken<List<User>>(){}.getType();
                List<User> newList=gson.fromJson(result,type);
                userList.clear();
                userList.addAll(newList);
                tv_fensi.setText(userList.size()+"");
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