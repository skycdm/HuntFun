package com.example.cdm.huntfun.activity.fragment.child;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.cdm.huntfun.R;

public class CostActivity extends AppCompatActivity {

    private ImageView back_cancle;
    private ImageView back_ok;
    private EditText edt_feiyong;

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
                Intent intent=new Intent();
                intent.putExtra("cost","未填写");
                setResult(93,intent);
                finish();
            }
        });

        back_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("cost",edt_feiyong.getText().toString());
                setResult(93,intent);
                finish();
            }
        });
    }
}
