package com.amar.hello2;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyDemo1Service extends Service
{
    public static final String TAG = "MyService";
    private MyBinder1 mBinder = new MyBinder1();
    public MyDemo1Service()
    {
    }

    @Override
    public IBinder onBind( Intent intent )
    {
        Log.d( TAG, "onBind() executed" );
        return mBinder;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        Log.d( TAG, "onCreate() executed" );
    }

    @Override
    public int onStartCommand( Intent intent, int flags, int startId )
    {
        Log.d( TAG, "onStartCommand() executed" );
        return super.onStartCommand( intent, flags, startId );
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        Log.d( TAG, "onDestroy() executed" );
    }

    class MyBinder1 extends Binder
    {
        public void startDownload()
        {
            Log.d( TAG, "startDownload() executed" );
            // 执行具体的下载任务
        }
    }

}
