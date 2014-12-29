package com.amar.hello2;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ShareAppActivity extends Activity
{

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_share_app );
    }

    public void gotoWeixin( View view )
    {
        String fileName = takeScreenShot( getWindow().getDecorView() );
        shareToFriend( new File( fileName ) );

    }

    public void gotoWeixinPengyou( View view )
    {
        String fileName = takeScreenShot( getWindow().getDecorView() );
        shareToTimeLine( new File( fileName ) );
    }

    public void gotoSina( View view )
    {
        String fileName = takeScreenShot( getWindow().getDecorView() );

        try
        {
            ComponentName comp = new ComponentName( "com.sina.weibo", "com.sina.weibo.EditActivity" );
            Intent intent = new Intent( Intent.ACTION_SEND );
            intent.setType( "image/*" );
            intent.putExtra( Intent.EXTRA_TEXT, "it's text~~ http://www.baidu.com" );
            intent.putExtra( Intent.EXTRA_STREAM, Uri.fromFile( new File( fileName ) ) );
            intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
            intent.setComponent( comp );
            intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
            startActivity( intent );
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
    }

    public void gotoQQweibo( View view )
    {

    }

    public void gotoMsg( View view )
    {
        Uri uri = Uri.parse( "smsto:" + "" );
        Intent sendIntent2 = new Intent( Intent.ACTION_SENDTO, uri );
        sendIntent2.putExtra( "sms_body", "<a href=\"sms:12345678\">给1349831009发短信</a>" );
        startActivity( sendIntent2 );
    }

    private void shareToFriend( File file )
    {
        Intent intent = new Intent();
        ComponentName comp = new ComponentName( "com.tencent.mm", "com.tencent.mm.ui.tools.ShareImgUI" );
        intent.setComponent( comp );
        intent.setAction( "android.intent.action.SEND" );
        intent.setType( "image/*" );
        intent.putExtra( Intent.EXTRA_TEXT, "我是文字" );
        intent.putExtra( Intent.EXTRA_STREAM, Uri.fromFile( file ) );
        intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
        startActivity( intent );
    }

    private void shareToTimeLine( File file )
    {
        Intent intent = new Intent();
        ComponentName comp = new ComponentName( "com.tencent.mm", "com.tencent.mm.ui.tools.ShareToTimeLineUI" );
        intent.setComponent( comp );
        intent.setAction( "android.intent.action.SEND" );
        intent.setType( "image/*" );
        intent.putExtra( Intent.EXTRA_TEXT, "我是文字" );
        intent.putExtra( Intent.EXTRA_STREAM, Uri.fromFile( file ) );
        intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
        startActivity( intent );
    }

    private void getScreen()
    {
        View thisView = this.getWindow().getDecorView();
        if ( !thisView.isDrawingCacheEnabled() )
        {
            thisView.setDrawingCacheEnabled( true );
        }
        Bitmap bitmap = thisView.getDrawingCache();
    }

    public String takeScreenShot( View view )
    {
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMddHHmmss" );
        String datetime = sdf.format( new Date() );
        String picPath = Environment.getExternalStorageDirectory() + File.separator + "screenshot" + datetime + ".png";
        view.setDrawingCacheEnabled( true );
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        if ( bitmap != null )
        {
            try
            {
                FileOutputStream out = new FileOutputStream( picPath );
                bitmap.compress( Bitmap.CompressFormat.PNG, 100, out );
            }
            catch ( Exception e )
            {
                e.printStackTrace();
            }
        }
        return picPath;
    }

}
