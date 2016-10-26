package com.example.cdm.huntfun.MineActivity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.cdm.huntfun.R;
import com.example.cdm.huntfun.fragment.Fragment_daizhifu;
import com.example.cdm.huntfun.fragment.Fragment_jinxing;
import com.example.cdm.huntfun.fragment.Fragment_wancheng;
import com.example.cdm.huntfun.pojo.SysOut;

public class OrderActivity extends AppCompatActivity {
    private RadioGroup radio_check;
    TextView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order);

        SysOut.getInstance().addActivity(this);
        intview();

        initdate();
    }
    private void intview() {
        radio_check = ((RadioGroup) findViewById(R.id.radio_check_collection));
    }
    private void initdate() {
        view  = (TextView) findViewById(R.id.daizhifu);
        view.setTextColor(getResources().getColor(R.color.red));

        check_fragment(new Fragment_daizhifu());//界面首次展示的界面信息
        radio_check.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            Fragment fragment=null;
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.daizhifu:
                        fragment = new Fragment_daizhifu();
                        view.setTextColor(Color.BLACK);
                        view = (TextView) findViewById(R.id.daizhifu);
                        view.setTextColor(Color.RED);
                        break;
                    case R.id.jinxing:
                        fragment = new Fragment_jinxing();
                        view.setTextColor(Color.BLACK);
                        view = (TextView) findViewById(R.id.jinxing);
                        view.setTextColor(Color.RED);
                        break;
                    case R.id.wancheng:
                        fragment = new Fragment_wancheng();
                        view.setTextColor(Color.BLACK);
                        view = (TextView) findViewById(R.id.wancheng);
                        view.setTextColor(Color.RED);
                        break;
                    case R.id.fanhui:
                        fanhui();
                        break;
                }
               if(fragment!=null){
                   check_fragment(fragment);
               }
            }
        });
    }

    private void fanhui() {
          finish();
//        Intent intent = new Intent(getApplication(),MainActivity.class);
//        intent.putExtra("tag","t");
//        startActivity(intent);
    }

    public void check_fragment(Fragment fragment){
        android.app.FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.frameLayout,fragment);
        ft.commit();
    }
}
