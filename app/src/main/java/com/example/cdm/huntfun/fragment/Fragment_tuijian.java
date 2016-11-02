package com.example.cdm.huntfun.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.cdm.huntfun.MainActivity;
import com.example.cdm.huntfun.R;
import com.example.cdm.huntfun.activity.PublishActivity;
import com.example.cdm.huntfun.activity.fragment.sqlitedraft.SqliteActivity;
import com.example.cdm.huntfun.activity.manage.ManageActivity;


/**
 * Created by lian on 2016/9/19.
 */
public class Fragment_tuijian extends Fragment {

    private RelativeLayout rel_publish;
    private RelativeLayout rel_manage;
    private RelativeLayout ccc;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tuijian,null);

        rel_publish = ((RelativeLayout) view.findViewById(R.id.rel_publish));

        rel_publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),PublishActivity.class);
                startActivity(intent);
            }
        });

        rel_manage = ((RelativeLayout) view.findViewById(R.id.rel_manage));
        rel_manage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMan=new Intent(getActivity(), ManageActivity.class);
                startActivity(intentMan);
            }
        });

        ccc = ((RelativeLayout) view.findViewById(R.id.ccc));
        ccc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMan=new Intent(getActivity(), SqliteActivity.class);
                startActivity(intentMan);
            }
        });
        return view;
    }
}