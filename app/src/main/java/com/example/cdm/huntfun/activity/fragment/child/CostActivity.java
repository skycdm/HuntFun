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

public class CostActivity extends AppCompatActivity {

    private ImageView back_cancle;
    private ImageView back_ok;
    private EditText edt_feiyong;

    String content=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cost);

        back_cancle = ((ImageView) findViewById(R.id.back_cancle));
        back_ok = ((ImageView) findViewById(R.id.back_ok));
        edt_feiyong = ((EditText) findViewById(R.id.edt_feiyong));

        back_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_feiyong.getText().toString().equals("")) {
                    Intent intent = new Intent();
                    intent.putExtra("cost", "未填写");
                    setResult(93, intent);
                    finish();
                }else {
                    Intent intent = new Intent();
                    intent.putExtra("cost", content);
                    setResult(93, intent);
                    finish();
                }
            }
        });

        back_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_feiyong.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(),"内容为空",Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent();
                    intent.putExtra("cost", edt_feiyong.getText().toString());
                    setResult(93, intent);
                    finish();
                }
            }
        });
        getData();
    }

    public void getData(){
        Intent intent=getIntent();
        content=intent.getStringExtra("cost");
        if (!content.equals("未填写")) {
            edt_feiyong.setText(content);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if (edt_feiyong.getText().toString().equals("")) {
                Intent intent = new Intent();
                intent.putExtra("cost", "未填写");
                setResult(93, intent);
                finish();
            }else {
                Intent intent=new Intent();
                intent.putExtra("cost",content);
                setResult(93,intent);
                finish();
            }
            return false;
        }
        return false;
    }
}
