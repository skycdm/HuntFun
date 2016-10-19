package com.example.cdm.huntfun.activity.fragment.child;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.cdm.huntfun.R;

public class TripActivity extends AppCompatActivity {

    private ImageView back_cancle;
    private ImageView back_ok;
    private EditText edt_xingcheng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip);

        back_cancle = ((ImageView) findViewById(R.id.back_cancle));
        back_ok = ((ImageView) findViewById(R.id.back_ok));
        edt_xingcheng = ((EditText) findViewById(R.id.edt_xingcheng));

        back_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("trip","未填写");
                setResult(92,intent);
                finish();
            }
        });

        back_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("trip",edt_xingcheng.getText().toString());
                setResult(92,intent);
                finish();
            }
        });
    }
}
