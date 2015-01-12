package com.amar.hello2.expandlist;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.amar.hello2.R;
import com.amar.hello2.fragment.DemoFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SAM on 2015/1/5.
 */
public class SubExpandableListAdapter extends BaseExpandableListAdapter implements ExpandableListView.OnChildClickListener
{
    protected LayoutInflater inflater;
    protected Activity context;
    protected EmbedData data;
    protected ExpandableListView expandableList;

    @Override
    public boolean onChildClick( ExpandableListView parent,View v,int groupPosition,int childPosition,long id )
    {
        Log.d( "SubExpandableListAdapter","onChildClick" + groupPosition + ":" + childPosition );

        FragmentTransaction ft = context.getFragmentManager().beginTransaction();
        ft.setTransition( FragmentTransaction.TRANSIT_FRAGMENT_FADE );
        DemoFragment gameMenuFragment = new DemoFragment();
        ft.add( gameMenuFragment,"aa" );
        ft.commitAllowingStateLoss();

        return false;
    }
    ViewHolder tempHolder;
    View tempConvertView;
    public SubExpandableListAdapter( Activity context,ExpandableListView expandableList )
    {
        this.context = context;
        this.inflater = LayoutInflater.from( context );
        this.expandableList = expandableList;
        tempHolder = new ViewHolder();
        tempConvertView = inflater.inflate( R.layout.embe_expandlist_item,null );
    }

    public SubExpandableListAdapter( Activity _contex )
    {
        this.context = _contex;
        this.inflater = LayoutInflater.from( context );
    }

    public class ChildViewHoder
    {
        TextView info;

    }

    @Override
    public View getChildView( int groupPosition,int childPosition,boolean isLastChild,View convertView,ViewGroup parent )
    {
        ChildViewHoder holder;
        if ( convertView == null )
        {
            holder = new ChildViewHoder();
            convertView = inflater.inflate( R.layout.embe_expandlist_subitem,null );
            holder.info = ( TextView ) convertView.findViewById( R.id.param3 );
            convertView.setTag( holder );
        }
        else
        {
            holder = (ChildViewHoder)convertView.getTag();
        }

        EmbedData currentData = ( EmbedData ) getChild( groupPosition,childPosition );
        if ( currentData != null )
        {
            holder.info.setText( currentData.name + ":" + currentData.id );
        }
        return convertView;
    }


    //该方法决定每个组选项的外观
    @Override
    public View getGroupView( int groupPosition,boolean isExpanded,View convertView,ViewGroup parent )
    {
        ViewHolder holder;
        EmbedData currentData = ( EmbedData ) getGroup( groupPosition );
        if ( convertView == null )
        {
            holder = new ViewHolder();
            convertView = inflater.inflate( R.layout.embe_expandlist_item,null );
            holder.info = ( TextView ) convertView.findViewById( R.id.param3 );
            convertView.setTag( holder );
        }
        else
        {
            holder = ( ViewHolder ) convertView.getTag();
        }
        holder.info.setText( currentData.name + ":" + currentData.id );

        return convertView;
    }

    public class ViewHolder
    {
        TextView info;
    }

    public void setData( EmbedData data )
    {
        this.data = data;
        this.notifyDataSetChanged();
    }

    public void change()
    {
        if ( expandableList == null )
        {
            return;
        }
        new Thread( new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    Thread.sleep( 1000 );
                }
                catch ( InterruptedException e )
                {
                    e.printStackTrace();
                }
                context.runOnUiThread( new Runnable()
                {
                    @Override
                    public void run()
                    {
                        int length = expandableList.getCount();
                        for ( int i = 0 ; i < length ; i++ )
                        {
                            expandableList.expandGroup( i );
                        }
                    }
                } );
            }
        } ).start();
    }

    //获取指定组位置、指定子列表项处的子列表项数据
    @Override
    public Object getChild( int groupPosition,int childPosition )
    {
        return data.get( childPosition ) == null ? null : data.get( childPosition );
    }

    @Override
    public long getChildId( int groupPosition,int childPosition )
    {
        return childPosition;
    }

    @Override
    public int getChildrenCount( int groupPosition )
    {
        return data.subData.size();
        //return data == null ? 0 : ( data.subData == null ? 0 : 1 );//只显示一个Child，否则会造成的重复显示
    }

    //获取指定组位置处的组数据
    @Override
    public Object getGroup( int groupPosition )
    {
        return data;
    }

    @Override
    public int getGroupCount()
    {
        return data == null ? 0 : 1;
    }

    @Override
    public long getGroupId( int groupPosition )
    {
        return groupPosition;
    }

    @Override
    public void onGroupExpanded( int groupPosition )
    {

    }

    @Override
    public boolean isChildSelectable( int groupPosition,int childPosition )
    {
        return true;
    }

    @Override
    public boolean hasStableIds()
    {
        return false;
    }
}
