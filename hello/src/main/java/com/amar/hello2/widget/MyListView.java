package com.amar.hello2.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

/**
 * Created by SAM on 2014/12/28.
 */
public class MyListView extends ListView
{
    public MyListView( Context context )
    {
        super( context );
    }

    public MyListView( Context context, AttributeSet attrs )
    {
        super( context, attrs );
    }

    public MyListView( Context context, AttributeSet attrs, int defStyle )
    {
        super( context, attrs, defStyle );
    }

    private MyListView mListView;

    public void setRelatedListView( MyListView listView )
    {
        mListView = listView;
    }

    @Override
    public boolean onTouchEvent( MotionEvent ev )
    {
        if ( null != mListView )
        {
            mListView.onTouch( ev );
        }

        return super.onTouchEvent( ev );
    }

    public void onTouch( MotionEvent ev )
    {
        super.onTouchEvent( ev );
    }

    @Override
    protected void onMeasure( int widthMeasureSpec, int heightMeasureSpec )
    {
        super.onMeasure( widthMeasureSpec, heightMeasureSpec );
        int width = 0;
        int height = getMeasuredHeight();

        int widthMode = MeasureSpec.getMode( widthMeasureSpec );
        int widthSize = MeasureSpec.getSize( widthMeasureSpec );

        if ( widthMode == MeasureSpec.EXACTLY )
        {
            width = widthSize;
        }
        else if ( widthMode == MeasureSpec.AT_MOST )
        {
            final int childCount = getChildCount();
            for ( int i = 0 ; i < childCount ; i++ )
            {
                View item = getChildAt( i );
                measureChild( item, widthMeasureSpec, heightMeasureSpec );
                width = Math.max( width, item.getMeasuredWidth() );
            }
        }
        setMeasuredDimension( width, height );
    }
}
