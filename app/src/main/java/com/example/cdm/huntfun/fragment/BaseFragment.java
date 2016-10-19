package com.example.cdm.huntfun.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Created by cdm on 2016/10/9.
 */
public abstract class BaseFragment extends Fragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
        initEvent();
    }

    public abstract  void initView();//找控件
    public abstract  void initEvent();//设置控件的事件
    public abstract  void initData();//设置界面初始值
}
