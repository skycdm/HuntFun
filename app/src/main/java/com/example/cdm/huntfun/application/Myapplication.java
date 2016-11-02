package com.example.cdm.huntfun.application;

import android.app.Application;

import com.example.cdm.huntfun.BuildConfig;
import com.example.cdm.huntfun.pojo.UserInfo;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;


import org.xutils.x;

/**
 * Created by 执行Z on 2016/10/11.
 */
public class Myapplication extends Application {
    UserInfo userInfo=new UserInfo(2,"lucy");

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
    UserInfo sendDynamic=new UserInfo(1);

    public UserInfo getSendDynamic() {
        return sendDynamic;
    }

    public void setSendDynamic(UserInfo sendDynamic) {
        this.sendDynamic = sendDynamic;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG); // 是否输出debug日志, 开启debug会影响性能.
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(this);
        ImageLoader.getInstance().init(configuration);
    }
}
