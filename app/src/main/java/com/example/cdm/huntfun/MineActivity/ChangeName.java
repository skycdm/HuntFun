package com.example.cdm.huntfun.MineActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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


public class ChangeName extends AppCompatActivity {

    private TextView old_name;
    private EditText newName;
    private Button newName_upload;
    private Button newName_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SysOut.getInstance().addActivity(this);
        setContentView(R.layout.activity_change_name);

        initView();
    }

    private void initView() {
        old_name = ((TextView) findViewById(R.id.old_name));
        old_name.setText(Sport_name.NAME+"");
        newName = ((EditText) findViewById(R.id.newName));
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

        newName_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = Sport_name.ID;
                String name = null;
                try {
                    name = URLEncoder.encode(newName.getText().toString().trim(),"utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                int flag = 0;
                RequestParams rp = new RequestParams(Constent.URL+"huntfunService/myselfChange?name="+name+"&flag="+flag+"&id="+id);
//                System.out.println(Constent.URL+"ww/myselfChange?name="+name+"flag="+flag+"id="+id);
                final String finalName = name;
                x.http().get(rp, new Callback.CacheCallback<String>() {
                    @Override
                    public void onSuccess(String result) {

                        SharedPreferences sp = getSharedPreferences("user",MODE_PRIVATE);
                        SharedPreferences.Editor spe = sp.edit();
                        spe.clear();
                        String a = newName.getText().toString().trim()+"/"+Sport_name.ID;
                        spe.putString("allow",a.substring(0,a.length()));
                        spe.commit();
                        Sport_name.NAME = newName.getText().toString().trim();
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
            }
        });
    }
}
