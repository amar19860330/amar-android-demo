package com.amar.hello2.expandlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.amar.hello2.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SAM on 2015/1/5.
 */
public class SubExpandableListAdapter extends BaseExpandableListAdapter implements AdapterView.OnItemClickListener
{
    protected LayoutInflater inflater;
    protected Context context;
    protected EmbedData data;

    @Override
    public void onItemClick( AdapterView<?> parent,View view,int position,long id )
    {

    }

    public SubExpandableListAdapter( Context context )
    {
        this.context = context;
        this.inflater = LayoutInflater.from( context );
    }

    @Override
    public View getChildView( int groupPosition,int childPosition,boolean isLastChild,View convertView,ViewGroup parent )
    {
        View view = inflater.inflate( R.layout.embe_expandlist_subitem,null );
        TextView info = ( TextView ) view.findViewById( R.id.param3 );
        try
        {
            EmbedData currentData = ( EmbedData ) getChild( groupPosition,childPosition );
            if ( currentData != null )
            {
                info.setText( currentData.name + ":" + currentData.id );
            }
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
        return view;
    }

    //该方法决定每个组选项的外观
    @Override
    public View getGroupView( int groupPosition,boolean isExpanded,View convertView,ViewGroup parent )
    {
        View view = inflater.inflate( R.layout.embe_expandlist_item,null );
        TextView info = ( TextView ) view.findViewById( R.id.param3 );
        EmbedData currentData = ( EmbedData ) getGroup( groupPosition );
        if ( currentData != null )
        {
            info.setText( currentData.name + ":" + currentData.id );
        }
        return view;
    }

    public void setData( EmbedData data )
    {
        this.data = data;
        this.notifyDataSetChanged();
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
        // return data.subData.size();
        return data == null ? 0 : ( data.subData == null ? 0 : 1 );//只显示一个Child，否则会造成的重复显示
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