package com.example.cdm.huntfun.next;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.cdm.huntfun.R;

public class SearchActivity extends AppCompatActivity {

    protected EditText query;
    protected TextView tv_search1;
    protected ImageButton search_clear;
    protected Button bt1;
    protected Button bt2;
    protected Button bt3;
    protected Button bt4;
    protected Button bt5;
    protected Button bt6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
        initEvent();
    }
    public void initView(){
        query = ((EditText) findViewById(R.id.query));
        tv_search1 = ((TextView) findViewById(R.id.tv_search1));
        search_clear = ((ImageButton) findViewById(R.id.search_clear));
        bt1= ((Button) findViewById(R.id.bt1));
        bt2= ((Button) findViewById(R.id.bt2));
        bt3= ((Button) findViewById(R.id.bt3));
        bt4= ((Button) findViewById(R.id.bt4));
        bt5= ((Button) findViewById(R.id.bt5));
        bt6= ((Button) findViewById(R.id.bt6));

    }
    public void initEvent(){
        search_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query.getText().clear();
            }
        });

        tv_search1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  Intent intent=new Intent(SearchActivity.this,SearchBarActivity.class);
                  intent.putExtra("theme",query.getText().toString());
                  startActivity(intent);
            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SearchActivity.this,Search_DetailActivity.class);
                intent.putExtra("search_name","音乐");
                startActivity(intent);

            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SearchActivity.this,Search_DetailActivity.class);
                intent.putExtra("search_name","运动");
                startActivity(intent);

            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SearchActivity.this,Search_DetailActivity.class);
                intent.putExtra("search_name","旅行");
                startActivity(intent);
            }
        });
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SearchActivity.this,Search_DetailActivity.class);
                intent.putExtra("search_name","技能");
                startActivity(intent);
            }
        });
        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SearchActivity.this,Search_DetailActivity.class);
                intent.putExtra("search_name","美食");
                startActivity(intent);
            }
        });
        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SearchActivity.this,Search_DetailActivity.class);
                intent.putExtra("search_name","");
                startActivity(intent);
            }
        });
    }

}
