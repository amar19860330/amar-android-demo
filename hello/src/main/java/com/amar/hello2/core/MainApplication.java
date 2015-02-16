package com.amar.hello2.core;

import android.app.Application;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by SAM on 2015/2/16.
 */
public class MainApplication extends Application
{
    private static final String TAG = "JPush";

    @Override
    public void onCreate()
    {
        super.onCreate();

        JPushInterface.setDebugMode( true );    // 设置开启日志,发布时请关闭日志
        JPushInterface.init( this );            // 初始化 JPush
    }
}
