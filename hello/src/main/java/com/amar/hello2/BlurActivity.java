package com.amar.hello2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;

import com.npi.blureffect.*;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.AbsListView.LayoutParams;

import java.io.File;

@EActivity( resName = "activity_blur" )
public class BlurActivity extends BaseActivity
{
    @ViewById( resName = "blurred_image" )
    ImageView mBlurredImage;
    View headerView;
    @ViewById( resName = "normal_image" )
    ImageView mNormalImage;
    @ViewById( resName = "blurred_image_header" )
    ScrollableImageView mBlurredImageHeader;

    @ViewById( resName = "list" )
    ListView mList;
    @ViewById( resName = "background_switch" )
    Switch mSwitch;
    private float alpha = 1f;
    private static final String BLURRED_IMG_PATH = "blurred_image.png";
    private static final int TOP_HEIGHT = 700;

    @AfterViews
    void afterViews()
    {
        try
        {
            init();
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
    }

    public void init()
    {
        //requestWindowFeature( Window.FEATURE_INDETERMINATE_PROGRESS );
        final int screenWidth = ImageUtils.getScreenWidth( this );
        mBlurredImageHeader.setScreenWidth( screenWidth );
        mSwitch.setOnCheckedChangeListener( new OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged( CompoundButton buttonView,boolean isChecked )
            {
                if ( isChecked )
                {
                    mBlurredImage.setAlpha( alpha );
                }
                else
                {
                    mBlurredImage.setAlpha( 0f );
                }
            }
        } );

        final File blurredImage = new File( getFilesDir() + File.separator + BLURRED_IMG_PATH );
        if ( !blurredImage.exists() )
        {
            setProgressBarIndeterminateVisibility( true );

            new Thread( new Runnable()
                {
                    @Override
                    public void run()
                    {
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inSampleSize = 2;
                    Bitmap image = BitmapFactory.decodeResource( getResources(),R.drawable.scenery,options );
                    Bitmap newImg = Blur.fastblur( BlurActivity.this,image,3 );
                    ImageUtils.storeImage( newImg,blurredImage );
                    runOnUiThread( new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            updateView( screenWidth );

                            setProgressBarIndeterminateVisibility( false );
                        }
                    } );

                }
            } ).start();
        }
        else
        {
            updateView( screenWidth );
        }
        String[] strings = getResources().getStringArray( R.array.list_content );
        headerView = new View( this );
        headerView.setLayoutParams( new LayoutParams( LayoutParams.MATCH_PARENT,TOP_HEIGHT ) );
        mList.addHeaderView( headerView );
        mList.setAdapter( new ArrayAdapter<String>( this,R.layout.blur_list_item,strings ) );
        mList.setOnScrollListener( new AbsListView.OnScrollListener()
        {

            @Override
            public void onScrollStateChanged( AbsListView view,int scrollState )
            {

            }

            @Override
            public void onScroll( AbsListView view,int firstVisibleItem,int visibleItemCount,int totalItemCount )
            {
                try
                {
                    alpha = ( float ) -headerView.getTop() / ( float ) TOP_HEIGHT;
                    if ( alpha > 1 )
                    {
                        alpha = 1;
                    }

                    if ( mSwitch.isChecked() )
                    {
                        mBlurredImage.setAlpha( alpha );
                    }
                    mBlurredImage.setTop( headerView.getTop() / 2 );
                    mNormalImage.setTop( headerView.getTop() / 2 );
                    mBlurredImageHeader.handleScroll( headerView.getTop() / 2 );
                }
                catch ( Exception e )
                {
                    e.printStackTrace();
                }

            }
        } );
    }

    private void updateView( final int screenWidth )
    {
        Bitmap bmpBlurred = BitmapFactory.decodeFile( getFilesDir() + File.separator + BLURRED_IMG_PATH );
        bmpBlurred = Bitmap.createScaledBitmap( bmpBlurred,screenWidth,( int ) ( bmpBlurred.getHeight() * ( ( float ) screenWidth ) / ( float ) bmpBlurred.getWidth() ),false );
        mBlurredImage.setImageBitmap( bmpBlurred );
        //mBlurredImageHeader.setoriginalImage( bmpBlurred );
    }
}
