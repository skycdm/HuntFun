package com.example.cdm.huntfun.MineActivity;

import android.content.Intent;
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


public class ChangePsw extends AppCompatActivity {

    private EditText old_psw;
    private EditText new_psw;
    private EditText new_psw_again;
    private Button newName_upload;
    private Button newName_back;
    private TextView old_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SysOut.getInstance().addActivity(this);
        setContentView(R.layout.activity_change_psw);

        initView();
    }

    private void initView() {
        old_name = ((TextView) findViewById(R.id.old_name));
        old_name.setText(Sport_name.NAME);
        old_psw = ((EditText) findViewById(R.id.old_psw));
        new_psw = ((EditText) findViewById(R.id.new_psw));
        new_psw_again = ((EditText) findViewById(R.id.new_psw_again));
        newName_upload = ((Button) findViewById(R.id.newName_upload));
        newName_back = ((Button) findViewById(R.id.newName_back));
        
        click();
    }
    private void click() {
        newName_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //修改密码
        newName_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldpwd = null;
                try {
                    oldpwd = URLEncoder.encode(old_psw.getText().toString().trim(),"utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                String newpwd = null;
                try {
                    newpwd = URLEncoder.encode(new_psw.getText().toString().trim(),"utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                String againpwd = null;
                try {
                    againpwd = URLEncoder.encode(new_psw_again.getText().toString().trim(),"utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                int flag = 2;
                RequestParams rp = new RequestParams(Constent.URL+"huntfunService/myselfChange?psw="+oldpwd+"&flag="+flag+"&id="+ Sport_name.ID);
                final String finalNewpwd = newpwd;
                final String finalAgainpwd = againpwd;
                x.http().get(rp, new Callback.CacheCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        String id = Sport_name.ID;
                        System.out.println(id+"=====id");
                        if(result.length()>0){
                            if(finalNewpwd.equals(finalAgainpwd)){
                                int flag = 1;
                                RequestParams rp = new RequestParams(Constent.URL+"huntfunService/myselfChange?psw="+finalAgainpwd+"&flag="+flag+"&id="+ Sport_name.ID);
                                x.http().get(rp, new Callback.CacheCallback<String>() {
                                    @Override
                                    public void onSuccess(String result) {
                                        Intent intent = new Intent(getApplication(),MainActivity.class);
                                        intent.putExtra("tag","t");
                                        startActivity(intent);
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
                            }else{
                                Toast.makeText(getApplicationContext(),"两次密码不同，重新输入",Toast.LENGTH_SHORT).show();
                                new_psw_again.setText("");
                            }
                        }
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
            }
        });
    }
}
