package com.amar.hello2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;

import com.npi.blureffect.*;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

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

    private static final String BLURRED_IMG_PATH = "blurred_image.png";
    private static final int TOP_HEIGHT = 700;

    @ViewById( resName = "list" )
    ListView mList;
    @ViewById( resName = "background_switch" )
    Switch mSwitch;
    private float alpha;


    //    mSwitch = ( Switch ) findViewById( R.id.background_switch );
    //    mList = ( ListView ) findViewById( R.id.list );


    @AfterViews
    void afterViews()
    {
        requestWindowFeature( Window.FEATURE_INDETERMINATE_PROGRESS );
        final int screenWidth = ImageUtils.getScreenWidth( this );
    }


    /**
     * Get the screen width.
     *
     * @param context
     * @return the screen width
     */
    @SuppressWarnings( "deprecation" )
    @SuppressLint( "NewApi" )
    public static int getScreenWidth( Activity context )
    {

        Display display = context.getWindowManager().getDefaultDisplay();
        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2 )
        {
            Point size = new Point();
            display.getSize( size );
            return size.x;
        }
        return display.getWidth();
    }
}
