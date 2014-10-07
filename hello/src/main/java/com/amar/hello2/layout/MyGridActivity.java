package com.amar.hello2.layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;

import com.amar.hello2.R;

/**
 * Created by Office on 2014/6/12.
 */
public class MyGridActivity extends Activity
{
    GridLayout mygridLayout;

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );

        setContentView( R.layout.mygridlayout );

        mygridLayout = ( GridLayout ) findViewById( R.id.mygrid_layout );
    }

    public void addMoreBtn( View view )
    {
        View inner1 = View.inflate( this, R.layout.dynamic_view, null );
        EditText editText1 = ( EditText ) inner1.findViewById( R.id.editText2_1 );
        editText1.setText( mygridLayout.getChildCount() + 1 + "" );
        mygridLayout.addView( inner1 );

    }

    public void clearBtn( View view )
    {
        //mygridLayout.removeAllViews();
        mygridLayout.removeViewAt( mygridLayout.getChildCount() - 1 );
    }
}
