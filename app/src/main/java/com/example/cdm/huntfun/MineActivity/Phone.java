package com.example.cdm.huntfun.MineActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.cdm.huntfun.MainActivity;
import com.example.cdm.huntfun.R;
import com.example.cdm.huntfun.pojo.SysOut;

public class Phone extends AppCompatActivity {

    private Button boda;
    private Button quxiao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone);

        SysOut.getInstance().addActivity(this);
        boda = ((Button) findViewById(R.id.boda));
        quxiao = ((Button) findViewById(R.id.phone_quxiao));

        boda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+"62025"));
                startActivity(intent);
            }
        });
       quxiao.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(getApplication(),MainActivity.class);
               intent.putExtra("tag","t");
               startActivity(intent);
           }
       });

    }
}
