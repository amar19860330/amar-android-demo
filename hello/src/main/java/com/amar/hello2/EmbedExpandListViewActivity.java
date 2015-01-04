package com.amar.hello2;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EActivity(resName = "activity_embed_expand_list_view")
public class EmbedExpandListViewActivity extends BaseActivity
{

    @ViewById(resName = "expandablelist")
    ExpandableListView expandableListView;

    List<EmbedData> testDataList;

    @AfterViews
    void afterViews()
    {
        initTestData();

        final ExpandableListAdapter expandableListAdapter = new ExpandableListAdapter( this );
        expandableListView.setOnGroupExpandListener( new ExpandableListView.OnGroupExpandListener()
        {
            @Override
            public void onGroupExpand( int groupPosition )
            {
                for ( int i = 0 ; i < expandableListAdapter.getGroupCount() ; i++ )
                {
                    if ( groupPosition != i )
                    {
                        expandableListView.collapseGroup( i );
                    }
                }
            }
        } );

        expandableListAdapter.setData( testDataList );
        expandableListView.setAdapter( expandableListAdapter );
    }

    public class ExpandableListAdapter extends BaseExpandableListAdapter
    {
        protected LayoutInflater inflater;
        Context context;

        private List<EmbedData> data;

        public ExpandableListAdapter( Context context )
        {
            this.context = context;
            this.inflater = LayoutInflater.from( context );
        }

        public void setData( List<EmbedData> data )
        {
            this.data = data;
            this.notifyDataSetChanged();
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

        //该方法决定每个子选项的外观
        @Override
        public View getChildView( int groupPosition,int childPosition,boolean isLastChild,View convertView,ViewGroup parent )
        {
            View view = inflater.inflate( R.layout.embe_expandlist_subhead,null );
            TextView info = ( TextView ) view.findViewById( R.id.param3 );
            EmbedData currentData = ( EmbedData ) getChild( groupPosition,childPosition );
            info.setText( currentData.name + ":" + currentData.id );

            final ExpandableListAdapter expandableListAdapter = new ExpandableListAdapter( context );
            expandableListView.setOnGroupExpandListener( new ExpandableListView.OnGroupExpandListener()
            {
                @Override
                public void onGroupExpand( int groupPosition )
                {
                    for ( int i = 0 ; i < expandableListAdapter.getGroupCount() ; i++ )
                    {
                        if ( groupPosition != i )
                        {
                            expandableListView.collapseGroup( i );
                        }
                    }
                }
            } );

            expandableListAdapter.setData( testDataList );
            expandableListView.setAdapter( expandableListAdapter );

            return view;
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
            return true;
        }
    }

    public class EmbedData
    {
        public String name;
        public int id;
        List<EmbedData> subData = new ArrayList<>();

        public EmbedData( int id,String name )
        {
            this.name = name;
            this.id = id;
        }

        public void add( EmbedData data )
        {
            subData.add( data );
        }

        public EmbedData get( int index )
        {
            return subData.get( index );
        }
    }

    void initTestData()
    {
        EmbedData dataA = new EmbedData( -1,"dataA" );
        EmbedData dataB = new EmbedData( -2,"dataB" );
        EmbedData dataC = new EmbedData( -3,"dataC" );
        EmbedData dataD = new EmbedData( -4,"dataD" );

        EmbedData data1 = new EmbedData( 1,"data1" );
        EmbedData data2 = new EmbedData( 2,"data2" );
        EmbedData data3 = new EmbedData( 3,"data3" );
        EmbedData data4 = new EmbedData( 4,"data4" );
        EmbedData data5 = new EmbedData( 5,"data5" );
        EmbedData data6 = new EmbedData( 6,"data6" );
        EmbedData data7 = new EmbedData( 7,"data7" );
        EmbedData data8 = new EmbedData( 8,"data8" );
        EmbedData data9 = new EmbedData( 9,"data9" );
        EmbedData data10 = new EmbedData( 10,"data10" );
        EmbedData data11 = new EmbedData( 11,"data11" );
        EmbedData data12 = new EmbedData( 12,"data12" );
        EmbedData data13 = new EmbedData( 13,"data13" );
        EmbedData data14 = new EmbedData( 14,"data14" );
        EmbedData data15 = new EmbedData( 15,"data15" );

        dataA.add( data1 );
        dataA.add( data2 );
        dataA.add( data3 );

        dataB.add( data4 );
        dataB.add( data5 );

        dataC.add( data6 );
        dataC.add( data7 );
        dataC.add( data8 );

        dataD.add( data9 );

        data1.add( data10 );
        data1.add( data11 );
        data1.add( data12 );

        data2.add( data13 );
        data2.add( data14 );

        data4.add( data15 );

        testDataList = new ArrayList<>();

        testDataList.add( dataA );
        testDataList.add( dataB );
        testDataList.add( dataC );
        testDataList.add( dataD );
    }
}
