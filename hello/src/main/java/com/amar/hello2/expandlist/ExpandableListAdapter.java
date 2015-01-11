package com.amar.hello2.expandlist;

import android.app.Activity;
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

import java.util.List;

/**
 * Created by SAM on 2015/1/5.
 */
public class ExpandableListAdapter extends BaseExpandableListAdapter implements ExpandableListView.OnChildClickListener
{
    protected LayoutInflater inflater;
    protected Activity context;
    protected List<EmbedData> data;
    protected ExpandableListView expandableList;

    public ExpandableListAdapter( Activity context,ExpandableListView expandableList )
    {
        this.context = context;
        this.inflater = LayoutInflater.from( context );
        this.expandableList = expandableList;
    }

    int _groupPosition;
    int _childPosition;

    @Override
    public boolean onChildClick( ExpandableListView parent,View v,int groupPosition,int childPosition,long id )
    {
        this._groupPosition = groupPosition;
        this._childPosition = childPosition;
        return false;
    }

    //该方法决定每个子选项的外观
    @Override
    public View getChildView( int groupPosition,int childPosition,boolean isLastChild,View convertView,ViewGroup parent )
    {
        EmbedData currentData = ( EmbedData ) getChild( groupPosition,childPosition );
        CustExpListview custExpListview = new CustExpListview( context );

        SubExpandableListAdapter subExpandableListAdapter = new SubExpandableListAdapter( context,custExpListview );
        subExpandableListAdapter.setData( currentData );
        custExpListview.setAdapter( subExpandableListAdapter );
        custExpListview.setGroupIndicator( null );
        custExpListview.setOnChildClickListener( subExpandableListAdapter );

        int length = currentData.subData.size();
        for ( int i = 0 ; i < length ; i++ )
        {
            custExpListview.expandGroup( i );
        }

        return custExpListview;
    }

    //该方法决定每个组选项的外观
    @Override
    public View getGroupView( int groupPosition,boolean isExpanded,View convertView,ViewGroup parent )
    {
        View view = inflater.inflate( R.layout.embe_expandlist_head,null );
        TextView info = ( TextView ) view.findViewById( R.id.param3 );
        EmbedData currentData = ( EmbedData ) getGroup( groupPosition );
        info.setText( currentData.name + ":" + currentData.id );

        return view;
    }

    public void setData( List<EmbedData> data )
    {
        this.data = data;
        this.notifyDataSetChanged();
        //change();
    }

    @Override
    public void notifyDataSetChanged()
    {
        super.notifyDataSetChanged();
    }

    public void change()
    {


        int length = expandableList.getCount();
        for ( int i = 0 ; i < length ; i++ )
        {
            expandableList.expandGroup( i );
        }
        //        new Thread( new Runnable()
        //        {
        //            @Override
        //            public void run()
        //            {
        //                context.runOnUiThread( new Runnable()
        //                {
        //                    @Override
        //                    public void run()
        //                    {
        //                        int length = expandableList.getCount();
        //                        for ( int i = 0 ; i < length ; i++ )
        //                        {
        //                            expandableList.expandGroup( i );
        //                        }
        //                    }
        //                } );
        //            }
        //        } ).start();
    }

    //获取指定组位置、指定子列表项处的子列表项数据
    @Override
    public Object getChild( int groupPosition,int childPosition )
    {
        return data.get( groupPosition ).get( childPosition );
    }

    @Override
    public long getChildId( int groupPosition,int childPosition )
    {
        return childPosition;
    }

    @Override
    public int getChildrenCount( int groupPosition )
    {
        return data.get( groupPosition ).subData.size();
    }

    //获取指定组位置处的组数据
    @Override
    public Object getGroup( int groupPosition )
    {
        return data.get( groupPosition );
    }

    @Override
    public int getGroupCount()
    {
        return data.size();
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
