package com.demo.healthy;

import android.app.Application;
import android.os.StrictMode;
import org.xutils.x;


/**
 * 初始化程序类
 */
public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        //从Android 7.0开始，一个应用提供自身文件给其它应用使用时，如果给出一个file:
        // 格式的URI的话，应用会抛出FileUriExposedException。这是由于谷歌认为目标app可能不具有文件权限，会造成潜在的问题。
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
    }
}
