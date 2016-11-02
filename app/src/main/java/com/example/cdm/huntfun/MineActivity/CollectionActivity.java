package com.example.cdm.huntfun.MineActivity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.cdm.huntfun.MainActivity;
import com.example.cdm.huntfun.R;
import com.example.cdm.huntfun.fragment.Fragment_sport;
import com.example.cdm.huntfun.pojo.SysOut;

public class CollectionActivity extends AppCompatActivity {

    private RadioGroup radio_check;
    TextView view;
    private RadioButton fanhui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SysOut.getInstance().addActivity(this);
        setContentView(R.layout.activity_collection);

        intview();

        initdate();
    }

    private void intview() {
//        radio_check = ((RadioGroup) findViewById(R.id.radio_check_collection));
        fanhui = ((RadioButton) findViewById(R.id.fanhui));
    }


    private void initdate() {
        check_fragment(new Fragment_sport());

        fanhui.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                finish();
//                fanhui();
            }
        });

//        view =(TextView) findViewById(R.id.sport);
//        view.setTextColor(Color.RED);
//        check_fragment(new Fragment_sport());
//        radio_check.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            Fragment fragment=null;
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                switch(checkedId){
//                    case R.id.sport:
//                        fragment = new Fragment_sport();
//                        view.setTextColor(Color.BLACK);
//                        view =(TextView) findViewById(R.id.sport);
//                        view.setTextColor(Color.RED);
//                        break;
//                    case R.id.object:
//                        fragment = new Fragment_object();
//                        view.setTextColor(Color.BLACK);
//                        view =(TextView) findViewById(R.id.object);
//                        view.setTextColor(Color.RED);
//                        break;
//                    case R.id.story:
//                        fragment = new Fragment_story();
//                        view.setTextColor(Color.BLACK);
//                        view =(TextView) findViewById(R.id.story);
//                        view.setTextColor(Color.RED);
//                        break;
//                    case R.id.fanhui:
//                        finish();
////                        fanhui();
//                        break;
////                }
//                if(fragment!=null) {
//                    check_fragment(fragment);
//                }
//            }
//        });
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
