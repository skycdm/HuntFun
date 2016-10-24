package com.example.cdm.huntfun.activity.manage;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.cdm.huntfun.R;
import com.example.cdm.huntfun.activity.manage.fragment.FragmentJoin;
import com.example.cdm.huntfun.activity.manage.fragment.FragmentPublish;

import java.util.ArrayList;
import java.util.List;

public class ManageActivity extends AppCompatActivity implements View.OnClickListener {

    List<Fragment> fragments=new ArrayList<Fragment>();
    FragmentJoin fragmentJoin;
    FragmentPublish fragmentPublish;

    private Button btn_jion_frg;
    private Button btn_publish_frg;

    private ImageView iv_bank;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);

        viewPager = ((ViewPager) findViewById(R.id.frag));

        //初始化fragment
        fragmentJoin=new FragmentJoin();
        fragmentPublish=new FragmentPublish();

        //所有fragment的数组
        fragments.add(fragmentJoin);
        fragments.add(fragmentPublish);

        btn_jion_frg = ((Button) findViewById(R.id.btn_jion_frg));
        btn_publish_frg = ((Button) findViewById(R.id.btn_publish_frg));
        btn_jion_frg.setOnClickListener(this);
        btn_publish_frg.setOnClickListener(this);

        viewPager.setAdapter(new MyFrageStatePagerAdapter(getSupportFragmentManager()));

        btn_jion_frg.setBackgroundResource(R.drawable.background);
        btn_jion_frg.setTextColor(getResources().getColor(R.color.text_color_selected));

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position==0){
                    btn_jion_frg.setBackgroundResource(R.drawable.background);
                    btn_jion_frg.setTextColor(getResources().getColor(R.color.text_color_selected));
                    btn_publish_frg.setBackground(null);
                    btn_publish_frg.setTextColor(getResources().getColor(R.color.publish_btn_press));
                }
                if (position==1){
                    btn_publish_frg.setBackgroundResource(R.drawable.background);
                    btn_publish_frg.setTextColor(getResources().getColor(R.color.text_color_selected));
                    btn_jion_frg.setBackground(null);
                    btn_jion_frg.setTextColor(getResources().getColor(R.color.publish_btn_press));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        iv_bank = ((ImageView) findViewById(R.id.iv_bank));
        iv_bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    class MyFrageStatePagerAdapter extends FragmentStatePagerAdapter {

        public MyFrageStatePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

    @Override
    public void onClick(View v) {
        //点击按钮时，表示选中不同的项
        switch(v.getId()) {
            case R.id.btn_jion_frg:
                changeView(0);//选中第一项
                btn_jion_frg.setBackgroundResource(R.drawable.background);
                btn_publish_frg.setBackground(null);
                break;

            case R.id.btn_publish_frg:
                changeView(1);//选中第二项
                btn_publish_frg.setBackgroundResource(R.drawable.background);
                btn_jion_frg.setBackground(null);
                break;
        }
    }

    //手动设置ViewPager要显示的视图
    private void changeView(int desTab)
    {
        viewPager.setCurrentItem(desTab, true);
    }

}
