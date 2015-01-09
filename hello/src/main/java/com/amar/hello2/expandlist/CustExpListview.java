package com.amar.hello2.expandlist;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ExpandableListView;

/**
 * Created by SAM on 2015/1/5.
 */
public class CustExpListview extends ExpandableListView
{
    public CustExpListview( Context context )
    {
        super( context );
    }

    public CustExpListview( Context context,AttributeSet attrs )
    {
        super( context,attrs );
    }

    public CustExpListview( Context context,AttributeSet attrs,int defStyleAttr )
    {
        super( context,attrs,defStyleAttr );
    }

    protected void onMeasure( int widthMeasureSpec,int heightMeasureSpec )
    {
       // DisplayMetrics dm = new DisplayMetrics();
        //getActivity().getWindowManager().getDefaultDisplay().getMetrics( dm );
       // dm.widthPixels   //宽度
       // dm.heightPixels  //高度
                //widthMeasureSpec = View.MeasureSpec.makeMeasureSpec( 960,View.MeasureSpec.AT_MOST );
        heightMeasureSpec = View.MeasureSpec.makeMeasureSpec( 1024,View.MeasureSpec.AT_MOST );
        super.onMeasure( widthMeasureSpec,heightMeasureSpec );
    }
}
