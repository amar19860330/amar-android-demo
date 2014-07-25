package com.amar.hello2;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;

import com.amar.hello2.R;
import com.amar.hello2.fragment.Fragment2;
import com.amar.hello2.fragment.Fragment3;
import com.amar.hello2.fragment.Fragment4;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import java.util.Arrays;

public class RadioScrollActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener
{

    private static final String TAG = "JustTestActivity";
    /**
     * Called when the activity is first created.
     */
    HorizontalScrollView scrollBar;
    RadioGroup radioGroup;
    int widthX;
    ImageView arrowl;
    ImageView arrowr;

    Fragment[] fragmentArray = null;
    private int[] radioBtnIdsArr = null;

    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_radio_scroll );
        scrollBar = ( HorizontalScrollView ) this.findViewById( R.id.scroll_bar );
        radioGroup = ( RadioGroup ) this.findViewById( R.id.radioGroup );
        arrowl = ( ImageView ) this.findViewById( R.id.arrowl );
        arrowr = ( ImageView ) this.findViewById( R.id.arrowr );
        scrollBar.fling( 1000 );
        //通过判断目标的scrollX来控制左右两边的图片显隐
        scrollBar.setOnTouchListener( new OnTouchListener()
        {

            @Override
            public boolean onTouch( View view, MotionEvent event )
            {
                Log.i( TAG, "scrollX ---->" + scrollBar.getScrollX() );
                Log.i( TAG, "windth ---->" + scrollBar.getWidth() );
                LinearLayout linear = ( LinearLayout ) scrollBar.getChildAt( 0 );
                int radioWidth = linear.getChildAt( 0 ).getWidth();
                Log.i( TAG, "radio width--->" + radioWidth );
                if ( scrollBar.getScrollX() < 6 )
                {
                    arrowl.setVisibility( View.GONE );

                }
                else if ( scrollBar.getScrollX() + scrollBar.getWidth() > radioWidth - 6 )
                {

                    arrowr.setVisibility( View.GONE );
                }
                else
                {
                    arrowr.setVisibility( View.VISIBLE );
                    arrowl.setVisibility( View.VISIBLE );
                }
                return false;
            }
        } );

        fragmentArray = new Fragment[ 9 ];

        fragmentArray[ 0 ] = new Fragment2();
        fragmentArray[ 1 ] = new Fragment3();
        fragmentArray[ 2 ] = new Fragment4();
        fragmentArray[ 3 ] = new Fragment2();
        fragmentArray[ 4 ] = new Fragment3();
        fragmentArray[ 5 ] = new Fragment4();
        fragmentArray[ 6 ] = new Fragment2();
        fragmentArray[ 7 ] = new Fragment3();
        fragmentArray[ 8 ] = new Fragment4();

        radioBtnIdsArr = new int[ 9 ];
        radioBtnIdsArr[ 0 ] = R.id.radio1;
        radioBtnIdsArr[ 1 ] = R.id.radio2;
        radioBtnIdsArr[ 2 ] = R.id.radio3;
        radioBtnIdsArr[ 3 ] = R.id.radio4;
        radioBtnIdsArr[ 4 ] = R.id.radio5;
        radioBtnIdsArr[ 5 ] = R.id.radio6;
        radioBtnIdsArr[ 6 ] = R.id.radio7;
        radioBtnIdsArr[ 7 ] = R.id.radio8;
        radioBtnIdsArr[ 8 ] = R.id.radio9;
        radioGroup.setOnCheckedChangeListener( this );
    }

    int currentIndex = -1;

    @Override
    public void onCheckedChanged( RadioGroup group, int checkedId )
    {
        int index = Arrays.binarySearch( radioBtnIdsArr, checkedId );
        if ( index == currentIndex )
        {
            return;
        }
        else
        {
            currentIndex = index;
        }
        Fragment fragmentWillShow = fragmentArray[ index ];
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations( 0, R.anim.fragment_close );
        fragmentTransaction.replace( R.id.radio_scroll_content, fragmentWillShow );
        fragmentTransaction.commit();

    }
}
