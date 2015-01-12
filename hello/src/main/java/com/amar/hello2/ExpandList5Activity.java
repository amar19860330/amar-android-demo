package com.amar.hello2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.amar.hello2.expandlist.ExpandListAdapter;
import com.amar.hello2.expandlist.ExpandViewAdapter;
import com.amar.hello2.expandlist.MyListAdapter;
import com.amar.hello2.expandlist.Person;
import com.nhaarman.listviewanimations.appearance.StickyListHeadersAdapterDecorator;
import com.nhaarman.listviewanimations.appearance.simple.AlphaInAnimationAdapter;
import com.nhaarman.listviewanimations.util.StickyListHeadersListViewWrapper;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

@EActivity( resName = "activity_expand_list5" )
public class ExpandList5Activity extends Activity
{
    @AfterViews
    void afterViews()
    {
        final StickyListHeadersListView listView = ( StickyListHeadersListView ) findViewById( R.id.activity_stickylistheaders_listview );
        listView.setFitsSystemWindows( true );
        final ExpandViewAdapter adapter = new ExpandViewAdapter( this );
        AlphaInAnimationAdapter animationAdapter = new AlphaInAnimationAdapter( adapter );
        StickyListHeadersAdapterDecorator stickyListHeadersAdapterDecorator = new StickyListHeadersAdapterDecorator( animationAdapter );
        stickyListHeadersAdapterDecorator.setListViewWrapper( new StickyListHeadersListViewWrapper( listView ) );

        assert animationAdapter.getViewAnimator() != null;
        animationAdapter.getViewAnimator().setInitialDelayMillis( 500 );

        assert stickyListHeadersAdapterDecorator.getViewAnimator() != null;
        stickyListHeadersAdapterDecorator.getViewAnimator().setInitialDelayMillis( 500 );

        listView.setAdapter( stickyListHeadersAdapterDecorator );

        listView.setOnItemClickListener( new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick( AdapterView<?> parent,View view,int position,long id )
            {
                Log.d( "","" );
                Log.d( "","" );
            }
        } );

        listView.setOnHeaderClickListener( new StickyListHeadersListView.OnHeaderClickListener()
        {
            @Override
            public void onHeaderClick( StickyListHeadersListView stickyListHeadersListView,View headView,int position,long headId,boolean b )
            {
                int childCount = stickyListHeadersListView.getListChildCount();
                int count = stickyListHeadersListView.getChildCount();
                View listChild = stickyListHeadersListView.getListChildAt( 1 );

                Person headPerson = null;

                for ( int i = 0 ; i < adapter.headData.size() ; i++ )
                {
                    if ( headId == adapter.headData.get( i ).id )
                    {
                        headPerson = adapter.headData.get( i );
                        break;
                    }
                }
                if ( headPerson == null )
                {
                    return;
                }
                for ( Person person : adapter.data )
                {
                    if ( headPerson.id == person.parentid )
                    {
                        if ( headPerson.visible == 1 )
                        {
                            person.visible = 2;
                        }
                        else
                        {
                            person.visible = 1;
                        }
                    }
                }
                if ( headPerson.visible == 1 )
                {
                    headPerson.visible = 2;
                }
                else
                {
                    headPerson.visible = 1;
                }
                adapter.notifyDataSetChanged();

                Log.d( "","" );
                Log.d( "","" );
                Log.d( "",count + "," + childCount + "," + listChild.getId() );

            }
        } );
    }

}
