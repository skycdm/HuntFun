package com.example.cdm.huntfun.activity.fragment.child;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.cdm.huntfun.R;

public class GatherActivity extends AppCompatActivity {

    private ImageView back_cancle;
    private ImageView back_ok;
    private EditText edt_didian;


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
                Intent intent=new Intent();
                intent.putExtra("gather","未填写");
                setResult(91,intent);
                finish();
            }
        });

        back_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("gather",edt_didian.getText().toString());
                setResult(91,intent);
                finish();
            }
        });
    }
}
