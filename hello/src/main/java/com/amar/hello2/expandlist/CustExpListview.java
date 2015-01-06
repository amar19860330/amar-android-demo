package com.amar.hello2.expandlist;

import android.content.Context;
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

    protected void onMeasure( int widthMeasureSpec,int heightMeasureSpec )
    {
        //widthMeasureSpec = View.MeasureSpec.makeMeasureSpec( 960,View.MeasureSpec.AT_MOST );
        heightMeasureSpec = View.MeasureSpec.makeMeasureSpec( 600,View.MeasureSpec.AT_MOST );
        super.onMeasure( widthMeasureSpec,heightMeasureSpec );
    }
}
