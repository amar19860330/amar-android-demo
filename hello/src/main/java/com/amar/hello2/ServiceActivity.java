package com.amar.hello2;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

import android.app.ActivityManager.RunningTaskInfo;
import android.app.ActivityManager.RunningServiceInfo;

import java.util.List;


@EActivity( resName = "activity_service" )
public class ServiceActivity extends Activity
{
    private String tag = this.getClass().getName();

    private MyDemo1Service.MyBinder1 myBinder = null;

    private ServiceConnection connection = new ServiceConnection()
    {
        @Override
        public void onServiceDisconnected( ComponentName name )
        {
        }

        @Override
        public void onServiceConnected( ComponentName name, IBinder service )
        {
            myBinder = ( MyDemo1Service.MyBinder1 ) service;
            myBinder.startDownload();
        }
    };

    /**
     * 检测所有已经安装程序的包名
     */
    void printPackage()
    {
        PackageManager pManager = getPackageManager();
        List< PackageInfo > packageInfos = pManager.getInstalledPackages( 0 ); //PackageManager.GET_UNINSTALLED_PACKAGES
        for ( PackageInfo packInfo : packageInfos )
        {
            if ( ( packInfo.applicationInfo.flags & packInfo.applicationInfo.FLAG_SYSTEM ) <= 0 )
            {
                //Log.d( tag, packInfo.packageName ); //包名
                Intent intent = pManager.getLaunchIntentForPackage( packInfo.packageName );
                if ( intent != null )
                {
                    Log.d( tag, packInfo.packageName + "===>" + intent.getComponent().getClassName() );
                }
            }
        }
    }

    void isRunning()
    {
        ActivityManager am = ( ActivityManager ) this.getSystemService( Context.ACTIVITY_SERVICE );
        List< RunningTaskInfo > list = am.getRunningTasks( 100 );
        for ( RunningTaskInfo info : list )
        {
            Log.i( tag, "name==>" + info.baseActivity.getPackageName() + "===>" + info.topActivity.getPackageName() + "==>" + info.topActivity.toString() );
        }
    }

    @Click( resName = "service_start_1" )
    void start1()
    {
        //printPackage();
        isRunning();
        Intent startIntent = new Intent( this, MyDemo1Service.class );
        startService( startIntent );
    }

    @Click( resName = "service_stop_1" )
    void stop1()
    {
        Intent startIntent = new Intent( this, MyDemo1Service.class );
        stopService( startIntent );
    }

    @Click( resName = "service_status_1" )
    void status1()
    {

    }

    @Click( resName = "service_bind_1" )
    void bind1()
    {
        Intent bindIntent = new Intent( this, MyDemo1Service.class );
        bindService( bindIntent, connection, BIND_AUTO_CREATE );
    }

    @Click( resName = "service_unbind_1" )
    void unbind1()
    {
        unbindService( connection );
    }
}
