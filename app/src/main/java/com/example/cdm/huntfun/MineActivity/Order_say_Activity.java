package com.example.cdm.huntfun.MineActivity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.cdm.huntfun.MainActivity;
import com.example.cdm.huntfun.R;
import com.example.cdm.huntfun.fragment.Fragment_is_order_say;
import com.example.cdm.huntfun.fragment.Fragment_no_order_say;
import com.example.cdm.huntfun.pojo.SysOut;

public class Order_say_Activity extends AppCompatActivity {
    private RadioGroup radio_check;
    TextView view1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_say_);

        SysOut.getInstance().addActivity(this);
        intview();

        initdate();
    }
    private void intview() {
        radio_check = ((RadioGroup) findViewById(R.id.radio_check_collection));
    }

    private void initdate() {
        view1=(TextView) findViewById(R.id.no_order_say);
        view1.setTextColor(Color.RED);
        check_fragment(new Fragment_no_order_say());
        radio_check.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            Fragment fragment=null;
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.is_order_say:
                        fragment = new Fragment_is_order_say();
                        view1.setTextColor(Color.BLACK);
                        view1 =(TextView) findViewById(R.id.is_order_say);
                        view1.setTextColor(Color.RED);
                        break;
                    case R.id.no_order_say:
                        fragment = new Fragment_no_order_say();
                        view1.setTextColor(Color.BLACK);
                        view1 =(TextView) findViewById(R.id.no_order_say);
                        view1.setTextColor(Color.RED);
                        break;
                    case R.id.fanhui:
                        fanhui();
                        break;
                }
                if(fragment!=null) {
                    check_fragment(fragment);
                }
            }
        });
    }

    private void fanhui() {
        Intent intent = new Intent(getApplication(),MainActivity.class);
        intent.putExtra("tag","t");
        startActivity(intent);
    }

    public void check_fragment(Fragment fragment){
        android.app.FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.frameLayout,fragment);
        ft.commit();
    }

}
