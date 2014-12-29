package com.amar.hello2.widget;

import android.content.Context;
import android.util.AttributeSet;
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

    public void doReportScrollStateChange()
    {
        //   reportScrollStateChange(OnScrollListener.SCROLL_STATE_FLING);
    }
}
