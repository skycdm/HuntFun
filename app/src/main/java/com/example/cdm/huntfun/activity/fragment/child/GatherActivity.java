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

public class GatherActivity extends AppCompatActivity {

    private ImageView back_cancle;
    private ImageView back_ok;
    private EditText edt_didian;


    String content=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gather);

        back_cancle = ((ImageView) findViewById(R.id.back_cancle));
        back_ok = ((ImageView) findViewById(R.id.back_ok));
        edt_didian = ((EditText) findViewById(R.id.edt_didian));

        back_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_didian.getText().toString().equals("")) {
                    Intent intent = new Intent();
                    intent.putExtra("gather", "未填写");
                    setResult(91, intent);
                    finish();
                }else {
                    Intent intent = new Intent();
                    intent.putExtra("gather", content);
                    setResult(91, intent);
                    finish();
                }
            }
        });

        back_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_didian.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(),"内容为空",Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent();
                    intent.putExtra("gather", edt_didian.getText().toString());
                    setResult(91, intent);
                    finish();
                }
            }
        });
        getData();
    }

    public void getData(){
        Intent intent=getIntent();
        content=intent.getStringExtra("gather");
        if (!content.equals("未填写")) {
            edt_didian.setText(content);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if (edt_didian.getText().toString().equals("")) {
                Intent intent = new Intent();
                intent.putExtra("gather", "未填写");
                setResult(91, intent);
                finish();
            }else {
                Intent intent=new Intent();
                intent.putExtra("gather",content);
                setResult(91,intent);
                finish();
            }
            return false;
        }
        return false;
    }
}
