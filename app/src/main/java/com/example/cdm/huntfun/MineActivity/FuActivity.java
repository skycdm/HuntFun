package com.example.cdm.huntfun.MineActivity;

import android.app.Application;

import org.xutils.x;

/**
 * Created by lian on 2016/9/21.
 */
public class FuActivity extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(org.xutils.BuildConfig.DEBUG); // 是否输出debug日志, 开启debug会影响性能.
    }
}
