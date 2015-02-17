package com.amar.hello2;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import android.app.ActivityManager.RunningTaskInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amar.hello2.service.push.ExampleUtil;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;


@EActivity( resName = "activity_service" )
public class ServiceActivity extends Activity
{
    private String tag = this.getClass().getName();

    private MyDemo1Service.MyBinder1 myBinder = null;

    public static boolean isForeground = false;

    @AfterViews
    void afterViews()
    {
        registerMessageReceiver();
    }

    private ServiceConnection connection = new ServiceConnection()
    {
        @Override
        public void onServiceDisconnected( ComponentName name )
        {
        }

        @Override
        public void onServiceConnected( ComponentName name,IBinder service )
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
        List<PackageInfo> packageInfos = pManager.getInstalledPackages( 0 ); //PackageManager.GET_UNINSTALLED_PACKAGES
        for ( PackageInfo packInfo : packageInfos )
        {
            if ( ( packInfo.applicationInfo.flags & packInfo.applicationInfo.FLAG_SYSTEM ) <= 0 )
            {
                //Log.d( tag, packInfo.packageName ); //包名
                Intent intent = pManager.getLaunchIntentForPackage( packInfo.packageName );
                if ( intent != null )
                {
                    Log.d( tag,packInfo.packageName + "===>" + intent.getComponent().getClassName() );
                }
            }
        }
    }

    void isRunning()
    {
        ActivityManager am = ( ActivityManager ) this.getSystemService( Context.ACTIVITY_SERVICE );
        List<RunningTaskInfo> list = am.getRunningTasks( 100 );
        for ( RunningTaskInfo info : list )
        {
            Log.i( tag,"name==>" + info.baseActivity.getPackageName() + "===>" + info.topActivity.getPackageName() + "==>" + info.topActivity.toString() );
        }
    }

    @Click( resName = "service_start_1" )
    void start1()
    {
        printPackage();
        isRunning();
        Intent startIntent = new Intent( this,MyDemo1Service.class );
        startService( startIntent );
    }

    @Click( resName = "service_stop_1" )
    void stop1()
    {
        Intent startIntent = new Intent( this,MyDemo1Service.class );
        stopService( startIntent );
    }

    @Click( resName = "service_status_1" )
    void status1()
    {

    }

    @Click( resName = "service_bind_1" )
    void bind1()
    {
        Intent bindIntent = new Intent( this,MyDemo1Service.class );
        bindService( bindIntent,connection,BIND_AUTO_CREATE );
    }

    @Click( resName = "service_unbind_1" )
    void unbind1()
    {
        unbindService( connection );
    }

    @Override
    protected void onResume()
    {
        isForeground = true;
        super.onResume();
    }


    @Override
    protected void onPause()
    {
        isForeground = false;
        super.onPause();
    }


    @Override
    protected void onDestroy()
    {
        unregisterReceiver( mMessageReceiver );
        super.onDestroy();
    }

    /**
     * 以下是推送
     */
    private MessageReceiver mMessageReceiver;

    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";

    public static final String KEY_TITLE = "title";

    public static final String KEY_MESSAGE = "message";

    public static final String KEY_EXTRAS = "extras";

    public void registerMessageReceiver()
    {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority( IntentFilter.SYSTEM_HIGH_PRIORITY );
        filter.addAction( MESSAGE_RECEIVED_ACTION );
        registerReceiver( mMessageReceiver,filter );
    }

    public class MessageReceiver extends BroadcastReceiver
    {

        @Override
        public void onReceive( Context context,Intent intent )
        {
            if ( MESSAGE_RECEIVED_ACTION.equals( intent.getAction() ) )
            {
                String messge = intent.getStringExtra( KEY_MESSAGE );
                String extras = intent.getStringExtra( KEY_EXTRAS );
                StringBuilder showMsg = new StringBuilder();
                showMsg.append( KEY_MESSAGE + " : " + messge + "\n" );
                if ( !ExampleUtil.isEmpty( extras ) )
                {
                    showMsg.append( KEY_EXTRAS + " : " + extras + "\n" );
                }
                setCostomMsg( showMsg.toString() );
            }
        }
    }

    @ViewById( R.id.msg_rec )
    EditText msgText;

    private void setCostomMsg( String msg )
    {
        if ( null != msgText )
        {
            msgText.setText( msg );
            msgText.setVisibility( android.view.View.VISIBLE );
        }
    }

    @Click( R.id.msg_resume )
    void msgResume()
    {
        JPushInterface.resumePush( getApplicationContext() );
    }

    @Click( R.id.msg_stop )
    void msgStop()
    {
        JPushInterface.stopPush( getApplicationContext() );
    }

    @Click( R.id.msg_init )
    void msgInit()
    {
        try
        {
            JPushInterface.init( getApplicationContext() );
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
    }

    /**
     * 设置标签
     */
    @ViewById( R.id.msg_tag )
    EditText msgTagEditText;

    /**
     * 设置标签
     */
    @ViewById( R.id.msg_alias )
    EditText msgAliasEditText;

    @Click( R.id.msg_set_tag )
    void setTag()
    {
        String tag = msgTagEditText.getText().toString().trim();

        // 检查 tag 的有效性
        if ( TextUtils.isEmpty( tag ) )
        {
            Toast.makeText( ServiceActivity.this,"无效标签",Toast.LENGTH_SHORT ).show();
            return;
        }

        // ","隔开的多个 转换成 Set
        String[] sArray = tag.split( "," );
        Set<String> tagSet = new LinkedHashSet<String>();
        for ( String sTagItme : sArray )
        {
            if ( !ExampleUtil.isValidTagAndAlias( sTagItme ) )
            {
                Toast.makeText( ServiceActivity.this,"设置标签失败了",Toast.LENGTH_SHORT ).show();
                return;
            }
            tagSet.add( sTagItme );
        }

        // 调用JPush API设置Tag
        mHandler.sendMessage( mHandler.obtainMessage( MSG_SET_TAGS,tagSet ) );
    }


    @Click( R.id.msg_set_alias )
    void setAlias()
    {
        String alias = msgAliasEditText.getText().toString().trim();
        if ( TextUtils.isEmpty( alias ) )
        {
            Toast.makeText( ServiceActivity.this,"设置标签失败了",Toast.LENGTH_SHORT ).show();
            return;
        }
        if ( !ExampleUtil.isValidTagAndAlias( alias ) )
        {
            Toast.makeText( ServiceActivity.this,"设置标签失败了",Toast.LENGTH_SHORT ).show();
            return;
        }

        //调用JPush API设置Alias
        mHandler.sendMessage( mHandler.obtainMessage( MSG_SET_ALIAS,alias ) );
    }

    private static final int MSG_SET_ALIAS = 1001;

    private static final int MSG_SET_TAGS = 1002;
    private static final String TAG = "JPush";
    private final Handler mHandler = new Handler()
    {
        @SuppressWarnings( "unchecked" )
        @Override
        public void handleMessage( android.os.Message msg )
        {
            super.handleMessage( msg );
            switch ( msg.what )
            {
                case MSG_SET_ALIAS:
                    Log.d( TAG,"Set alias in handler." );
                    JPushInterface.setAliasAndTags( getApplicationContext(),( String ) msg.obj,null,mAliasCallback );
                    break;

                case MSG_SET_TAGS:
                    Log.d( TAG,"Set tags in handler." );
                    JPushInterface.setAliasAndTags( getApplicationContext(),null,( Set<String> ) msg.obj,mTagsCallback );
                    break;

                default:
                    Log.i( TAG,"Unhandled msg - " + msg.what );
            }
        }
    };

    private final TagAliasCallback mAliasCallback = new TagAliasCallback()
    {

        @Override
        public void gotResult( int code,String alias,Set<String> tags )
        {
            String logs;
            switch ( code )
            {
                case 0:
                    logs = "Set tag and alias success";
                    Log.i( TAG,logs );
                    break;

                case 6002:
                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
                    Log.i( TAG,logs );
                    if ( ExampleUtil.isConnected( getApplicationContext() ) )
                    {
                        mHandler.sendMessageDelayed( mHandler.obtainMessage( MSG_SET_ALIAS,alias ),1000 * 60 );
                    }
                    else
                    {
                        Log.i( TAG,"No network" );
                    }
                    break;

                default:
                    logs = "Failed with errorCode = " + code;
                    Log.e( TAG,logs );
            }

            ExampleUtil.showToast( logs,getApplicationContext() );
        }

    };

    private final TagAliasCallback mTagsCallback = new TagAliasCallback()
    {

        @Override
        public void gotResult( int code,String alias,Set<String> tags )
        {
            String logs;
            switch ( code )
            {
                case 0:
                    logs = "Set tag and alias success";
                    Log.i( TAG,logs );
                    break;

                case 6002:
                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
                    Log.i( TAG,logs );
                    if ( ExampleUtil.isConnected( getApplicationContext() ) )
                    {
                        mHandler.sendMessageDelayed( mHandler.obtainMessage( MSG_SET_TAGS,tags ),1000 * 60 );
                    }
                    else
                    {
                        Log.i( TAG,"No network" );
                    }
                    break;

                default:
                    logs = "Failed with errorCode = " + code;
                    Log.e( TAG,logs );
            }

            ExampleUtil.showToast( logs,getApplicationContext() );
        }

    };
}
