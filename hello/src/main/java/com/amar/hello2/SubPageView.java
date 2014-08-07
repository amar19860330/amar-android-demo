package com.amar.hello2;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_sub_page_view)
public class SubPageView extends BaseActivity
{
    @ViewById( resName = "scroll_view" )
    protected ScrollView scrollView;

    @ViewById( resName = "scroll_middle" )
    protected Button middleBtn;

    @ViewById( resName = "unit_scroll" )
    protected EditText unitEdtExt;

    @ViewById( resName = "height_scroll" )
    protected TextView heightTxt;

    @AfterViews
    public void afterViews()
    {
    }

    @Click(R.id.up_scroll)
    void upScroll()
    {
        int scrollY = scrollView.getScrollY();
        scrollHeightOffset( scrollY - getUnit(), scrollView );
    }

    @Click( R.id.down_scroll )
    void downScroll()
    {
        int scrollY = scrollView.getScrollY();
        scrollHeightOffset( scrollY + getUnit(), scrollView );
    }

    @Click( R.id.mid_scroll )
    void middleScroll()
    {
        int[] location = new int[ 2 ];
        middleBtn.getLocationOnScreen( location );

        int start = scrollView.getScrollY();
        //int offset = start - location[ 0 ];

        scrollHeightOffset( location[ 1 ], scrollView );
    }

    void scrollHeightOffset( final int heightOffset, final ScrollView scrollView )
    {
        scrollView.post( new Runnable()
        {
            @Override
            public void run()
            {
                scrollView.smoothScrollTo( 0, heightOffset );

                heightTxt.setText( "Y:" + heightOffset );
            }
        } );
    }

    private int getUnit()
    {
        String unit = unitEdtExt.getText().toString();

        int unitInt = "".equals( unit ) ? 0 : Integer.parseInt( unit );

        return unitInt;
    }

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_sub_page_view );
    }


    @Override
    public boolean onCreateOptionsMenu( Menu menu )
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate( R.menu.sub_page_view, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item )
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if ( id == R.id.action_settings )
        {
            return true;
        }
        return super.onOptionsItemSelected( item );
    }
}
