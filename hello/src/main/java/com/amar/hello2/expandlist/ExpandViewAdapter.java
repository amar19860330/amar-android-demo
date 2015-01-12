package com.amar.hello2.expandlist;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.amar.hello2.R;

import java.util.ArrayList;
import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * Created by SAM on 2015/1/12.
 */
public class ExpandViewAdapter extends BaseAdapter implements StickyListHeadersAdapter
{
    private final Activity mContext;

    public List<Person> data;
    public List<Person> headData;

    public ExpandViewAdapter( final Activity context )
    {
        this.mContext = context;
        initTestData();
        setData( testSubDataList,testHeadDataList );
    }

    public void setData( List<Person> data,List<Person> headData )
    {
        this.data = data;
        this.headData = headData;
        notifyDataSetChanged();
    }

    @Override
    public long getHeaderId( int position )
    {
        return data.get( position ).parentid;
    }

    @Override
    public int getCount()
    {
        return data.size();
    }

    @Override
    public Object getItem( int position )
    {
        return data != null && data.size() > ( position - 1 ) ? data.get( position ) : null;
    }

    @Override
    public long getItemId( int position )
    {
        return getItem( position ).hashCode();
    }

    @Override
    public View getView( int position,View convertView,ViewGroup parent )
    {
        TextView view = ( TextView ) convertView;
        if ( view == null )
        {
            view = ( TextView ) LayoutInflater.from( mContext ).inflate( R.layout.list_row,parent,false );
        }
        Person person = ( Person ) getItem( position );
        if ( person.visible != 1 )
        {
            view.setVisibility( View.GONE );
        }
        else
        {
            view.setVisibility( View.VISIBLE );
        }
        view.setText( person.name );
        return view;
    }

    @Override
    public View getHeaderView( int position,View convertView,ViewGroup parent )
    {
        TextView view = ( TextView ) convertView;
        if ( view == null )
        {
            view = ( TextView ) LayoutInflater.from( mContext ).inflate( R.layout.list_header,parent,false );
        }
        Person headPerson = null;
        for ( int i = 0 ; i < headData.size() ; i++ )
        {
            if ( data.get( position ).parentid == headData.get( i ).id )
            {
                headPerson = headData.get( i );
                break;
            }
        }

        // headPerson = headData.get( data.get( position ).parentid - 1 );
        if ( headPerson != null )
        {
            view.setText( mContext.getString( R.string.header,headPerson.id ) );
        }

        return view;
    }

    List<Person> testSubDataList;
    List<Person> testHeadDataList;

    public void initTestData()
    {
        testSubDataList = new ArrayList<>();
        testHeadDataList = new ArrayList<>();

        Person pA = new Person( 999,"姓名A",18,1,1,0 );
        Person pB = new Person( 888,"姓名B",18,1,1,0 );
        Person pC = new Person( 777,"姓名C",18,1,1,0 );
        int length = 15;

        for ( int i = 0 ; i < length ; i++ )
        {
            Person p = new Person( i,"姓名" + i,18 + i,1,2,999 );
            testSubDataList.add( p );
        }
        for ( int i = length ; i < length * 2 ; i++ )
        {
            Person p = new Person( i,"姓名" + i,18 + i,1,2,888 );
            testSubDataList.add( p );
        }
        for ( int i = length * 2 ; i < length * 3 ; i++ )
        {
            Person p = new Person( i,"姓名" + i,18 + i,1,2,777 );
            testSubDataList.add( p );
        }
        testHeadDataList.add( pA );
        testHeadDataList.add( pB );
        testHeadDataList.add( pC );
    }
}
