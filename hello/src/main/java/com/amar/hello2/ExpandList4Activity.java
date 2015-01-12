package com.amar.hello2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.amar.hello2.expandlist.MyExpandableListItemAdapter;
import com.amar.hello2.expandlist.MyListAdapter;
import com.nhaarman.listviewanimations.appearance.StickyListHeadersAdapterDecorator;
import com.nhaarman.listviewanimations.appearance.simple.AlphaInAnimationAdapter;
import com.nhaarman.listviewanimations.util.StickyListHeadersListViewWrapper;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

@EActivity( resName = "activity_expand_list4" )
public class ExpandList4Activity extends Activity
{
    @ViewById( resName = "activity_mylist_listview" )
    ListView mListView;

    private static final int INITIAL_DELAY_MILLIS = 500;

    @AfterViews
    void afterViews2()
    {
        MyExpandableListItemAdapter mExpandableListItemAdapter = new MyExpandableListItemAdapter( this );
        AlphaInAnimationAdapter alphaInAnimationAdapter = new AlphaInAnimationAdapter( mExpandableListItemAdapter );
        alphaInAnimationAdapter.setAbsListView( mListView );

        assert alphaInAnimationAdapter.getViewAnimator() != null;
        alphaInAnimationAdapter.getViewAnimator().setInitialDelayMillis( INITIAL_DELAY_MILLIS );

        mListView.setAdapter( alphaInAnimationAdapter );

        Toast.makeText( this,R.string.explainexpand,Toast.LENGTH_LONG ).show();
    }

    /*@AfterViews
    void afterViews()
    {
        StickyListHeadersListView listView = ( StickyListHeadersListView ) findViewById( R.id.activity_stickylistheaders_listview );
        listView.setFitsSystemWindows( true );
        MyListAdapter adapter = new MyListAdapter( this );
        AlphaInAnimationAdapter animationAdapter = new AlphaInAnimationAdapter( adapter );
        StickyListHeadersAdapterDecorator stickyListHeadersAdapterDecorator = new StickyListHeadersAdapterDecorator( animationAdapter );
        stickyListHeadersAdapterDecorator.setListViewWrapper( new StickyListHeadersListViewWrapper( listView ) );

        assert animationAdapter.getViewAnimator() != null;
        animationAdapter.getViewAnimator().setInitialDelayMillis( 500 );

        assert stickyListHeadersAdapterDecorator.getViewAnimator() != null;
        stickyListHeadersAdapterDecorator.getViewAnimator().setInitialDelayMillis( 500 );

        listView.setAdapter( stickyListHeadersAdapterDecorator );
        listView.setOnHeaderClickListener( new StickyListHeadersListView.OnHeaderClickListener()
        {
            @Override
            public void onHeaderClick( StickyListHeadersListView stickyListHeadersListView,View view,int i,long l,boolean b )
            {
                Log.d("","");
                Log.d("","");
            }
        } );
    }*/
}
