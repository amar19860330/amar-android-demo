package com.amar.hello2;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

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

    void isRunning()
    {
        ActivityManager am = ( ActivityManager ) this.getSystemService( Context.ACTIVITY_SERVICE );
        List< ActivityManager.RunningTaskInfo > list = am.getRunningTasks( 100 );
        for ( ActivityManager.RunningTaskInfo info : list )
        {
            Log.i( TAG, "name==>" + info.baseActivity.getPackageName() + "===>" + info.topActivity.getPackageName() + "==>" + info.topActivity.toString() );
        }
    }

    public static int PREPARE_TIME_WHAT = 20082008;
    private Timer timer;

    private boolean isRunning = false;

    @Override
    public int onStartCommand( Intent intent, int flags, int startId )
    {
        Log.d( TAG, "onStartCommand() executed" );
        isRunning = true;
        final Handler prepareHandler = new Handler()
        {
            public void handleMessage( Message msg )
            {
                if ( msg.what == PREPARE_TIME_WHAT )
                {
                    isRunning();
                }
            }
        };

        TimerTask counterTask = new TimerTask()
        {
            @Override
            public void run()
            {
                Message msg = new Message();
                msg.what = PREPARE_TIME_WHAT;
                prepareHandler.sendMessage( msg );
            }
        };
        if ( timer == null )
        {
            timer = new Timer();
            timer.schedule( counterTask, 3000, 10 * 1000 );
        }
        return super.onStartCommand( intent, flags, startId );
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        Log.d( TAG, "onDestroy() executed" );
        isRunning = false;
        if ( timer != null )
        {
            timer.cancel();
            timer = null;
        }
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
