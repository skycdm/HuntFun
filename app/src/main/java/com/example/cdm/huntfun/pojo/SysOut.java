package com.example.cdm.huntfun.pojo;

import android.app.Application;

import java.util.LinkedList;
import java.util.List;

/** 退出程序
 * Created by lian on 2016/10/25.
 */
public class SysOut extends Application {
    private List<android.app.Activity> mList = new LinkedList<android.app.Activity>();
    private static SysOut instance;

    private SysOut() {
    }
    public synchronized static SysOut getInstance() {
        if (null == instance) {
            instance = new SysOut();
        }
        return instance;
    }
    // add Activity
    public void addActivity(android.app.Activity activity) {
        mList.add(activity);
    }

    public void exit() {
        try {
            for (android.app.Activity activity : mList) {
                if (activity != null)
                     activity.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }
    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }
}
