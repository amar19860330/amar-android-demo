package com.amar.hello2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.google.analytics.tracking.android.EasyTracker;

import de.greenrobot.event.EventBus;

/**
 * Created by Office on 2014/6/23.
 */
public class BaseActivity extends Activity
{
    public void logI(String info)
    {
        Log.i(this.getClass().getName(),info);
    }

    public void logD(String info)
    {
        Log.d(this.getClass().getName(),info);
    }

    public void logE(String info)
    {
        Log.e(this.getClass().getName(),info);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        EasyTracker.getInstance(this).activityStart(this);
    }

    public void onEvent(String event)
    {
        Log.i(this.getClass().getName(),"event:"+event);
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        EasyTracker.getInstance(this).activityStop(this);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
