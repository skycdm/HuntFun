package com.example.cdm.huntfun.MineActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.cdm.huntfun.MainActivity;
import com.example.cdm.huntfun.R;
import com.example.cdm.huntfun.pojo.SysOut;

public class SystemSet extends AppCompatActivity {

    private TextView fanhui;
    private TextView change_name;
    private TextView change_psw;
    private TextView chang_tuisong;
    private TextView tuichu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_set);
        SysOut.getInstance().addActivity(this);
        initView();
    }

    private void initView() {
        fanhui = ((TextView) findViewById(R.id.fanhui));//返回
        change_name = ((TextView) findViewById(R.id.chang_name));//修改姓名
        change_psw = ((TextView) findViewById(R.id.go_head));//修改密码
        chang_tuisong = ((TextView) findViewById(R.id.chang_tuisong));//推送设置
        tuichu = ((TextView) findViewById(R.id.tuichu));//退出
        click();
    }

    private void click() {

        tuichu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getSharedPreferences("user",MODE_PRIVATE);
                SharedPreferences.Editor spe = sp.edit();
                spe.clear();
                spe.commit();
                SysOut.getInstance().exit();
            }
        });

        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),MainActivity.class);
                intent.putExtra("tag","t");
                startActivity(intent);
            }
        });
        change_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),ChangeName.class);
                startActivity(intent);
            }
        });
        change_psw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),ChangePsw.class);
                startActivity(intent);
            }
        });
    }
}
