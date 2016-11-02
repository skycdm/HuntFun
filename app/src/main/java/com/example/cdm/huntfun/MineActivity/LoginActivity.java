package com.example.cdm.huntfun.MineActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cdm.huntfun.MainActivity;
import com.example.cdm.huntfun.R;
import com.example.cdm.huntfun.pojo.Constent;
import com.example.cdm.huntfun.pojo.Sport_name;
import com.example.cdm.huntfun.pojo.SysOut;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText user_name;
    private EditText use_psw;
    private Button user_login;
    private TextView user_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SysOut.getInstance().addActivity(this);
        user_name = ((EditText) findViewById(R.id.user_name));
        use_psw = ((EditText) findViewById(R.id.user_psw));
        user_login = ((Button) findViewById(R.id.user_login));
        user_add = ((TextView) findViewById(R.id.user_add));

        user_login.setOnClickListener(this);
        user_add.setOnClickListener(this);
        getDate();
    }

    private void getDate() {

        SharedPreferences sp = getSharedPreferences("user",MODE_PRIVATE);
//        SharedPreferences.Editor spe = sp.edit();
        String allow = sp.getString("allow","");

        if(allow.length()>0){
            String[] allows = allow.split("/");
            Sport_name.NAME = allows[0];
            Sport_name.ID=allows[1];
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_login:
                user_login();
                break;
            case R.id.user_add:
                user_add();
                break;
        }
    }

    private void user_login() {
        String name = null;
        try {
            name = URLEncoder.encode(user_name.getText().toString().trim(),"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String psw = null;
        try {
            psw = URLEncoder.encode(use_psw.getText().toString().trim(),"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
           RequestParams params = new RequestParams(Constent.URL+"huntfunService/getInformation?name="+name+"&psw="+psw+"&flag=1");

        x.http().get(params, new Callback.CacheCallback<String>() {
                @Override
                public void onSuccess(String result) {

                    if (result.length() ==0) {
                        Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_LONG).show();
                    } else {
                        Sport_name.NAME=user_name.getText().toString().trim();
                        Sport_name.ID=result;
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        addShp(user_name.getText().toString().trim(),result);
                    }
                }
                @Override
                public void onError(Throwable ex, boolean isOnCallback) {

                }

                @Override
                public void onCancelled(Callback.CancelledException cex) {

                }

                @Override
                public void onFinished() {

                }

                @Override
                public boolean onCache(String result) {
                    return false;
                }
            });
    }

    private void addShp(String name,String id) {
        SharedPreferences sp = getSharedPreferences("user",MODE_PRIVATE);
        SharedPreferences.Editor spe = sp.edit();
        spe.clear();
        String a = name+"/"+id;
        spe.putString("allow",a.substring(0,a.length()));
        spe.commit();
    }

    private void user_add() {
        Intent inten = new Intent(LoginActivity.this, AddActivity.class);
        startActivity(inten);
    }
}
