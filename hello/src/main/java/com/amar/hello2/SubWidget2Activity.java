package com.amar.hello2;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.amar.hello2.R;
import com.amar.hello2.fragment.TipDialog;
import com.npi.blureffect.Blur;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class SubWidget2Activity extends BaseActivity implements AdapterView.OnItemSelectedListener
{
    private TabHost tabHost;
    private static final int VOICE_RECOGNITION = 1;

    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_sub_widget2 );

        initImageView1();

        addAnimationOnImage();

        showSpeech();
        try
        {
            tabHost = ( TabHost ) this.findViewById( R.id.TabHost01 );
            tabHost.setup();

            tabHost.addTab( tabHost.newTabSpec( "tab_1" ).setContent( R.id.LinearLayout1 ).setIndicator( "TAB1",this.getResources().getDrawable( R.drawable.i1 ) ) );
            tabHost.addTab( tabHost.newTabSpec( "tab_2" ).setContent( R.id.LinearLayout2 ).setIndicator( "TAB2",this.getResources().getDrawable( R.drawable.i2 ) ) );
            tabHost.addTab( tabHost.newTabSpec( "tab_3" ).setContent( R.id.LinearLayout3 ).setIndicator( "TAB3",this.getResources().getDrawable( R.drawable.i3 ) ) );
            tabHost.addTab( tabHost.newTabSpec( "tab_4" ).setContent( R.id.LinearLayout4 ).setIndicator( "TAB4",this.getResources().getDrawable( R.drawable.i1 ) ) );

            tabHost.setCurrentTab( 1 );
        }
        catch ( Exception ex )
        {
            ex.printStackTrace();
            Log.d( "EXCEPTION",ex.getMessage() );
        }
        initTab4();

        initSpin();

        initBlur();
    }

    boolean changed = false;

    private void initBlur()
    {
        //TextView02 widget2_img_view
        final TextView changeTxt = ( TextView ) findViewById( R.id.TextView02 );
        final ImageView imgView = ( ImageView ) findViewById( R.id.widget2_img_view );

        changeTxt.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick( View v )
            {
                int radius = 24;
                if ( changed )
                {
                    changeTxt.setText( "正在还原..." );
                    radius = 12;
                }
                else
                {
                    changeTxt.setText( "正在模糊..." );
                }
                final int _radius = radius;
                new Thread( new Runnable()
                {

                    @Override
                    public void run()
                    {
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inSampleSize = 2;
                        Bitmap image = BitmapFactory.decodeResource( getResources(),R.drawable.wall026,options );
                        try
                        {
                            final Bitmap newImg = Blur.fastblur( SubWidget2Activity.this,image,_radius );

                            runOnUiThread( new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    imgView.setImageBitmap( newImg );

                                    setProgressBarIndeterminateVisibility( false );

                                    changeTxt.setText( "收工" );
                                }
                            } );
                        }
                        catch ( Exception e )
                        {
                            e.printStackTrace();
                        }
                    }
                } ).start();
            }
        } );

    }

    private Spinner typeSpinner;

    public void initSpin()
    {
        typeSpinner = ( Spinner ) findViewById( R.id.type_spinner );
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>( this,R.layout.no_pic_choose_item,R.id.choose_item );
        arrayAdapter.setDropDownViewResource( R.layout.no_pic_choose_item );
        String[] strArr = getResources().getStringArray( R.array.type_selector );
        arrayAdapter.addAll( strArr );
        typeSpinner.setAdapter( arrayAdapter );
        typeSpinner.setOnItemSelectedListener( this );
    }

    public void onItemSelected( AdapterView<?> parent,View view,int position,long id )
    {

    }

    public void onNothingSelected( AdapterView<?> parent )
    {
    }

    private EditText nameText;
    private EditText ageText;
    private TextView resultText;
    public static final String ConfigName = "helloConfig";
    private Button tipButton;
    private Button waitButton;

    public void initTab4()
    {
        nameText = ( EditText ) this.findViewById( R.id.name );
        ageText = ( EditText ) this.findViewById( R.id.age );
        resultText = ( TextView ) this.findViewById( R.id.showText );

        Button button = ( Button ) this.findViewById( R.id.button );
        Button showButton = ( Button ) this.findViewById( R.id.showButton );
        tipButton = ( Button ) this.findViewById( R.id.tipButton );
        waitButton = ( Button ) this.findViewById( R.id.waitButton );

        button.setOnClickListener( listener );
        showButton.setOnClickListener( listener );
        tipButton.setOnClickListener( listener );
        waitButton.setOnClickListener( listener );

        // 回显
        SharedPreferences sharedPreferences = getSharedPreferences( ConfigName,Context.MODE_PRIVATE );
        String nameValue = sharedPreferences.getString( "name","" );
        int ageValue = sharedPreferences.getInt( "age",1 );
        nameText.setText( nameValue );
        ageText.setText( String.valueOf( ageValue ) );
    }

    private void showTipDialog()
    {
        TipDialog cf = new TipDialog();
        FragmentTransaction ft = this.getFragmentManager().beginTransaction();
        ft.setTransition( FragmentTransaction.TRANSIT_FRAGMENT_FADE );
        cf.show( ft,"cf" );
    }

    private void showWaitDialog()
    {

    }

    private View.OnClickListener listener = new View.OnClickListener()
    {
        public void onClick( View v )
        {
            Button button = ( Button ) v;
            //文件存放在/data/data/<package name>/shared_prefs目录下
            SharedPreferences sharedPreferences = getSharedPreferences( ConfigName,Context.MODE_PRIVATE );
            switch ( button.getId() )
            {
                case R.id.button:
                    String name = nameText.getText().toString();
                    int age = Integer.parseInt( ageText.getText().toString() );
                    SharedPreferences.Editor editor = sharedPreferences.edit(); //获取编辑器
                    editor.putString( "name",name );
                    editor.putInt( "age",age );
                    editor.commit();//提交修改
                    Toast.makeText( SubWidget2Activity.this,"保存成功",Toast.LENGTH_LONG ).show();
                    break;
                case R.id.showButton:
                    String nameValue = sharedPreferences.getString( "name","" );
                    int ageValue = sharedPreferences.getInt( "age",1 );
                    resultText.setText( "姓名：" + nameValue + "，年龄：" + ageValue );
                    break;
                case R.id.tipButton:
                    showTipDialog();
                    break;
                case R.id.waitButton:
                    showWaitDialog();
                    break;
            }
        }
    };

    public void showSpeech()
    {
        Button button = ( Button ) findViewById( R.id.speechBtn );
        button.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick( View v )
            {
                speechInput();
                speechWebSearch();
            }
        } );
    }

    public void addAnimationOnImage()
    {
        final ImageView imageView = ( ImageView ) findViewById( R.id.widget2_img_view2 );
        final Animation anim = AnimationUtils.loadAnimation( this,R.anim.my_anim );
        anim.setFillAfter( true );

        Button btn = ( Button ) findViewById( R.id.showAnimationBtn1 );
        btn.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick( View v )
            {
                imageView.startAnimation( anim );

            }
        } );

    }

    public void initImageView1()
    {
        ImageView imageView = ( ImageView ) findViewById( R.id.widget2_img_view );
        final ClipDrawable drawable = ( ClipDrawable ) imageView.getDrawable();
        final Handler handler = new Handler()
        {

            @Override
            public void handleMessage( Message msg )
            {
                // TODO Auto-generated method stub
                //如果该程序是本程序所发送的
                if ( msg.what == 0x123 )
                {
                    //修改ClipDrawable的level值
                    drawable.setLevel( drawable.getLevel() + 400 );
                }
            }
        };
        final Timer timer = new Timer();
        timer.schedule( new TimerTask()
        {

            @Override
            public void run()
            {
                Message msg = new Message();
                msg.what = 0x123;
                //发送消息，通知应用修改ClipDrawable对象的Level值
                handler.sendMessage( msg );
                //取消定时器

                if ( drawable.getLevel() >= 10000 )
                {
                    timer.cancel();
                }
            }
        },0,100 );
    }

    private void speechInput()
    {
        /**
         * Listing 11-2: Initiating a speech recognition request
         */
        Intent intent = new Intent( RecognizerIntent.ACTION_RECOGNIZE_SPEECH );
        // Specify free form input
        intent.putExtra( RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM );
        intent.putExtra( RecognizerIntent.EXTRA_PROMPT,"or forever hold your peace" );
        intent.putExtra( RecognizerIntent.EXTRA_MAX_RESULTS,1 );
        intent.putExtra( RecognizerIntent.EXTRA_LANGUAGE,Locale.ENGLISH );
        startActivityForResult( intent,VOICE_RECOGNITION );
    }

    /**
     * Listing 11-3: Finding the results of a speech recognition request
     */
    @Override
    protected void onActivityResult( int requestCode,int resultCode,Intent data )
    {
        if ( requestCode == VOICE_RECOGNITION && resultCode == RESULT_OK )
        {
            ArrayList<String> results;

            results = data.getStringArrayListExtra( RecognizerIntent.EXTRA_RESULTS );

            float[] confidence;

            String confidenceExtra = RecognizerIntent.EXTRA_CONFIDENCE_SCORES;
            confidence = data.getFloatArrayExtra( confidenceExtra );

            // TODO Do something with the recognized voice strings
        }
        super.onActivityResult( requestCode,resultCode,data );
    }

    private void speechWebSearch()
    {
        /**
         * Listing 11-4: Finding the results of a speech recognition request
         */
        Intent intent = new Intent( RecognizerIntent.ACTION_WEB_SEARCH );
        intent.putExtra( RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH );
        startActivityForResult( intent,0 );

    }
}
