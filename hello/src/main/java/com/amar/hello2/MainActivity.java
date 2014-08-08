package com.amar.hello2;

import wyf.zcl.DBActivity;

import android.net.Uri;
import android.os.Bundle;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.Toast;
import android.app.ProgressDialog;

import com.amar.hello2.tools.ShortcutUtils;

public class MainActivity extends BaseActivity
{
    private Button btn1 = null;

    private Button btn2 = null;

    private Button btn3 = null;

    private Button btn4 = null;

    private Button btn5 = null;

    private Button btn6 = null;

    private Button btn7 = null;

    private Button btn8 = null;

    private Button button10 = null;

    private EditText editText1 = null;

    private EditText editText2 = null;

    private Switch mySwitch = null;

    public static final int REQUEST_CODE = 501;

    private OnClickListener onClicklistener = new OnClickListener()
    {
        public void onClick( View v )
        {
            Intent intent = null;
            switch ( v.getId() )
            {
                case R.id.button1:
                    Toast.makeText( MainActivity.this, editText1.getText(), Toast.LENGTH_LONG ).show();
                    break;
                case R.id.button2:
                    intent = new Intent();
                    intent.setAction( Intent.ACTION_CALL );
                    Toast.makeText( MainActivity.this, editText2.getText(), Toast.LENGTH_LONG ).show();
                    intent.setData( Uri.parse( "tel:" + editText2.getText() ) );
                    startActivity( intent );
                    break;
                case R.id.button3:
                    intent = new Intent();
                    intent.setClass( MainActivity.this, SecondActivity.class );
                    intent.putExtra( "username", editText1.getText() + "" );
                    intent.putExtra( "number", editText2.getText() + "" );
                    // startActivity( intent );//无返回值
                    startActivityForResult( intent, REQUEST_CODE );
                    break;
                case R.id.button4:
                    intent = new Intent();
                    intent.setClass( MainActivity.this, ThirdActivity.class );
                    startActivity( intent );
                    break;
                case R.id.button5:
                    intent = new Intent();
                    intent.setClass( MainActivity.this, DBActivity.class );
                    startActivity( intent );
                    break;
                case R.id.button6:
                    intent = new Intent();
                    intent.setClass( MainActivity.this, NetWorkActivity.class );
                    startActivity( intent );
                    break;
                case R.id.button7:
                    intent = new Intent();
                    intent.setClass( MainActivity.this, FragmentDemoActivity.class );
                    startActivity( intent );
                    break;

                case R.id.button8:

                    final ProgressDialog pd = ProgressDialog.show( MainActivity.this, "hello", "请等待", true );

                    new Thread( new Runnable()
                    {
                        public void run()
                        {
                            try
                            {
                                Thread.sleep( 4000 );
                                pd.dismiss();
                            }
                            catch ( InterruptedException e )
                            {
                                e.printStackTrace();
                            }
                        }
                    } ).start();
                    break;
            }
        }
    };

    public void onclickLayout( View view )
    {
        Intent intent = new Intent( this, MyLayoutActivity.class );
        startActivity( intent );
    }

    @Override
    protected void onActivityResult( int requestCode, int resultCode, Intent data )
    {
        if ( REQUEST_CODE == requestCode )
        {
            if ( resultCode == SecondActivity.RESULT_CODE )
            {
                Bundle bundle = data.getExtras();

                String returnValue = bundle.getString( SecondActivity.RESULT_CONTENT );

                Toast.makeText( MainActivity.this, returnValue, Toast.LENGTH_LONG ).show();
            }
        }
    }

    void drawCircle()
    {
        RelativeLayout rootLayout = ( RelativeLayout ) findViewById( R.id.root );
        final DrawView dv = new DrawView( this );
        dv.setMinimumHeight( 500 );
        dv.setMinimumWidth( 300 );
        dv.setOnTouchListener( new View.OnTouchListener()
        {
            @Override
            public boolean onTouch( View v, MotionEvent event )
            {
                if ( mySwitch.isChecked() )
                {
                    dv.currentX = event.getX();
                    dv.currentY = event.getY();
                    dv.invalidate();
                    return false;
                }
                else
                {
                    return false;
                }
            }
        } );
        rootLayout.addView( dv );
    }


    public void initUI()
    {
        btn1 = ( Button ) this.findViewById( R.id.button1 );

        btn2 = ( Button ) this.findViewById( R.id.button2 );

        btn3 = ( Button ) this.findViewById( R.id.button3 );

        btn4 = ( Button ) this.findViewById( R.id.button4 );

        btn5 = ( Button ) this.findViewById( R.id.button5 );

        btn6 = ( Button ) this.findViewById( R.id.button6 );

        btn7 = ( Button ) this.findViewById( R.id.button7 );

        btn8 = ( Button ) this.findViewById( R.id.button8 );

        editText1 = ( EditText ) this.findViewById( R.id.editText1 );

        editText2 = ( EditText ) this.findViewById( R.id.editText2 );

        mySwitch = ( Switch ) findViewById( R.id.mySwitch );
    }

    public void uiFunction()
    {
        btn1.setOnClickListener( onClicklistener );

        btn2.setOnClickListener( onClicklistener );

        btn3.setOnClickListener( onClicklistener );

        btn4.setOnClickListener( onClicklistener );

        btn5.setOnClickListener( onClicklistener );

        btn6.setOnClickListener( onClicklistener );

        btn7.setOnClickListener( onClicklistener );

        btn8.setOnClickListener( onClicklistener );
    }


    int notificationID = 1;

    public void onClickNotification( View view )
    {
        Intent i = new Intent( this, MainActivity.class );
        i.putExtra( "notification", notificationID );
        PendingIntent pendingIntent = PendingIntent.getActivity( this, 0, i, 0 );

        NotificationManager nm = ( NotificationManager ) getSystemService( NOTIFICATION_SERVICE );

        Notification notif = new Notification( R.drawable.ic_launcher, "Reminder: Meeting starts in 5 minutes", System.currentTimeMillis() );

        CharSequence from = "System Alarm";
        CharSequence message = "Meeting with customer at 3pm...";

        notif.setLatestEventInfo( this, from, message, pendingIntent );

        // ---100ms delay, vibrate for 250ms, pause for 100 ms and
        // then vibrate for 500ms---
        notif.vibrate = new long[]{ 100 , 250 , 100 , 500 };
        nm.notify( notificationID, notif );
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu )
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate( R.menu.main, menu );
        return true;
    }

    @Override
    protected void onDestroy()
    {
        Log.i( this.getClass().getName(), "in onDestroy" );
        super.onDestroy();
    }

    @Override
    protected void onPause()
    {
        Log.i( this.getClass().getName(), "in onPause" );
        super.onPause();
    }

    @Override
    protected void onRestart()
    {
        Log.i( this.getClass().getName(), "in onRestart" );
        super.onRestart();
    }

    @Override
    protected void onResume()
    {
        Log.i( this.getClass().getName(), "in onResume" );
        super.onResume();
    }

    @Override
    protected void onStart()
    {
        Log.i( this.getClass().getName(), "in onStart" );
        super.onStart();
    }

    @Override
    protected void onStop()
    {
        Log.i( this.getClass().getName(), "in onStop" );
        super.onStop();
    }

    @Override
    protected void onSaveInstanceState( Bundle outState )
    {
        super.onSaveInstanceState( outState );
        Log.i( this.getClass().getName(), "in onSaveInstanceState" );
    }

    @Override
    protected void onRestoreInstanceState( Bundle savedInstanceState )
    {
        super.onRestoreInstanceState( savedInstanceState );
        Log.i( this.getClass().getName(), "in onRestoreInstanceState" );
    }

    public void onclickGotoWidget1( View view )
    {
        Intent intent = new Intent( this, Widget1Activity.class );
        startActivity( intent );
    }

    private void addShortcutToDesktop()
    {
        Intent shortcut = new Intent( "com.android.launcher.action.INSTALL_SHORTCUT" );
        // 不允许重建
        shortcut.putExtra( "duplicate", false );
        // 设置名字
        shortcut.putExtra( Intent.EXTRA_SHORTCUT_NAME, this.getString( R.string.app_name ) );
        // 设置图标
        shortcut.putExtra( Intent.EXTRA_SHORTCUT_ICON_RESOURCE, Intent.ShortcutIconResource.fromContext( this, R.drawable.ic_launcher ) );
        // 设置意图和快捷方式关联程序
        shortcut.putExtra( Intent.EXTRA_SHORTCUT_INTENT, new Intent( this, this.getClass() ).setAction( Intent.ACTION_MAIN ) );
        // 发送广播
        sendBroadcast( shortcut );
    }

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        Log.i( this.getClass().getName(), "in onCreate" );

        super.onCreate( savedInstanceState );

        setContentView( R.layout.activity_main );

        initUI();

        uiFunction();

        drawCircle();

        ShortcutUtils shortcutUtils = new ShortcutUtils();
        String shortcutName = getString( R.string.app_name );
//        if ( !shortCut.hasShortcut( this, shortcutName ) )
//        {
            //shortcutUtils.creatShortCut( this, shortcutName, R.drawable.ic_launcher );
//        }
    }

}
