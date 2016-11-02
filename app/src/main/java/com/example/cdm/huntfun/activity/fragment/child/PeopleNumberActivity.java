package com.example.cdm.huntfun.activity.fragment.child;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.cdm.huntfun.R;

public class PeopleNumberActivity extends AppCompatActivity {

    private ImageView back_cancle;
    private ImageView back_ok;
    private EditText edt_renshu;

    String content=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_number);

        back_cancle = ((ImageView) findViewById(R.id.back_cancle));
        back_ok = ((ImageView) findViewById(R.id.back_ok));
        edt_renshu = ((EditText) findViewById(R.id.edt_renshu));

        back_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_renshu.getText().toString().equals("")) {
                    Intent intent = new Intent();
                    intent.putExtra("num", "未填写");
                    setResult(94, intent);
                    finish();
                }else {
                    Intent intent = new Intent();
                    intent.putExtra("num", content);
                    setResult(94, intent);
                    finish();
                }
            }
        });

        back_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_renshu.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(),"内容为空",Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent();
                    intent.putExtra("num", edt_renshu.getText().toString());
                    setResult(94, intent);
                    finish();
                }
            }
        });
        getData();
    }

    public void getData(){
        Intent intent=getIntent();
        content=intent.getStringExtra("num");
        if (!content.equals("未填写")) {
            edt_renshu.setText(content);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if (edt_renshu.getText().toString().equals("")) {
                Intent intent = new Intent();
                intent.putExtra("num", "未填写");
                setResult(94, intent);
                finish();
            }else {
                Intent intent=new Intent();
                intent.putExtra("num",content);
                setResult(94,intent);
                finish();
            }
            return false;
        }
        return false;
    }
}
