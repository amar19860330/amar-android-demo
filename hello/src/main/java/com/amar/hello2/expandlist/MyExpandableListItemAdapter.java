/*
 * Copyright 2014 Niek Haarman
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.amar.hello2.expandlist;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;


import com.amar.hello2.R;
import com.nhaarman.listviewanimations.itemmanipulation.expandablelistitem.ExpandableListItemAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyExpandableListItemAdapter extends ExpandableListItemAdapter<Integer>
{

    private final Activity mContext;
    private final BitmapCache mMemoryCache;
    private LayoutInflater inflater;

    /**
     * Creates a new ExpandableListItemAdapter with the specified list, or an empty list if
     * items == null.
     */
    public MyExpandableListItemAdapter( final Activity context )
    {
        super( context,R.layout.activity_expandablelistitem_card,R.id.activity_expandablelistitem_card_title,R.id.activity_expandablelistitem_card_content );
        mContext = context;
        mMemoryCache = new BitmapCache();

        for ( int i = 0 ; i < 100 ; i++ )
        {
            add( i );
        }
        inflater = mContext.getLayoutInflater();
        initTestData();
    }

    @NonNull
    @Override
    public View getTitleView( final int position,final View convertView,@NonNull final ViewGroup parent )
    {
        TextView tv = ( TextView ) convertView;
        if ( tv == null )
        {
            tv = new TextView( mContext );
        }
        tv.setText( mContext.getString( R.string.expandorcollapsecard,( int ) getItem( position ) ) );
        return tv;
    }

    @NonNull
    @Override
    public View getContentView( final int position,final View convertView,@NonNull final ViewGroup parent )
    {
        CustExpListview listView = new CustExpListview( mContext );//inflater.inflate(  )
        //listView.setChildIndicator( null );
        listView.setGroupIndicator( null );
        InnerExpandableListAdapter adapter = new InnerExpandableListAdapter(mContext);
        adapter.setData( testDataList );
        listView.setAdapter( adapter );
        return listView;
    }

    //@NonNull
    //@Override
    public View getContentView2( final int position,final View convertView,@NonNull final ViewGroup parent )
    {
        ImageView imageView = ( ImageView ) convertView;
        if ( imageView == null )
        {
            imageView = new ImageView( mContext );
            imageView.setScaleType( ImageView.ScaleType.CENTER_CROP );
        }

        int imageResId;
        switch ( getItem( position ) % 5 )
        {
            case 0:
                imageResId = R.drawable.img_nature1;
                break;
            case 1:
                imageResId = R.drawable.img_nature2;
                break;
            case 2:
                imageResId = R.drawable.img_nature3;
                break;
            case 3:
                imageResId = R.drawable.img_nature4;
                break;
            default:
                imageResId = R.drawable.img_nature5;
        }

        Bitmap bitmap = getBitmapFromMemCache( imageResId );
        if ( bitmap == null )
        {
            bitmap = BitmapFactory.decodeResource( mContext.getResources(),imageResId );
            addBitmapToMemoryCache( imageResId,bitmap );
        }
        imageView.setImageBitmap( bitmap );

        return imageView;
    }

    private void addBitmapToMemoryCache( final int key,final Bitmap bitmap )
    {
        if ( getBitmapFromMemCache( key ) == null )
        {
            mMemoryCache.put( key,bitmap );
        }
    }

    private Bitmap getBitmapFromMemCache( final int key )
    {
        return mMemoryCache.get( key );
    }

    List<EmbedData> testDataList;
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
        EmbedData data16 = new EmbedData( 16,"data16" );
        EmbedData data17 = new EmbedData( 17,"data17" );

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
        data5.add( data16 );
        data5.add( data17 );

        testDataList = new ArrayList<>();

        testDataList.add( dataA );
        testDataList.add( dataB );
        testDataList.add( dataC );
        testDataList.add( dataD );
    }

    public class InnerExpandableListAdapter extends BaseExpandableListAdapter implements ExpandableListView.OnChildClickListener
    {
        protected LayoutInflater inflater;
        protected Activity context;
        protected List<EmbedData> data;
        protected ExpandableListView expandableList;

        public InnerExpandableListAdapter( Activity context)
        {
            this.context = context;
            this.inflater = LayoutInflater.from( context );
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

        public class ChildViewHoder
        {
            TextView info;

        }

        //该方法决定每个子选项的外观
        @Override
        public View getChildView( int groupPosition,int childPosition,boolean isLastChild,View convertView,ViewGroup parent )
        {
            EmbedData currentData = ( EmbedData ) getChild( groupPosition,childPosition );
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
}