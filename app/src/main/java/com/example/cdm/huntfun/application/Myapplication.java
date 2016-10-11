package com.example.cdm.huntfun.application;

import android.app.Application;

import com.example.cdm.huntfun.BuildConfig;

import org.xutils.x;

/**
 * Created by 执行Z on 2016/10/11.
 */
public class Myapplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG); // 是否输出debug日志, 开启debug会影响性能.
    }
}
