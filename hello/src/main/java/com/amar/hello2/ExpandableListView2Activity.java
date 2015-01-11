package com.amar.hello2;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import com.amar.hello2.expandlist.CustExpListview;
import com.amar.hello2.expandlist.EmbedData;
import com.amar.hello2.expandlist.Group;
import com.amar.hello2.expandlist.People;
import com.amar.hello2.expandlist.PinnedHeaderExpandableListView;
import com.amar.hello2.expandlist.StickyLayout;
import com.amar.hello2.expandlist.SubExpandableListAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.ViewsById;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


@EActivity( resName = "activity_expandable_list_view2" )
public class ExpandableListView2Activity extends BaseActivity implements ExpandableListView.OnChildClickListener, ExpandableListView.OnGroupClickListener, PinnedHeaderExpandableListView.OnHeaderUpdateListener, StickyLayout.OnGiveUpTouchEventListener
{
    @ViewById( resName = "expandable" )
    PinnedHeaderExpandableListView expandableListView;
    //@ViewById( resName = "sticky_layout" )
    StickyLayout stickyLayout;

    private ArrayList<Group> groupList;
    private ArrayList<List<People>> childList;
    private MyexpandableListAdapter adapter;

    @AfterViews
    void afterView()
    {
        initData();
        adapter = new MyexpandableListAdapter( this );
        expandableListView.setAdapter( adapter );

        for ( int i = 0, count = expandableListView.getCount() ; i < count ; i++ )
        {
            expandableListView.expandGroup( i );
        }
        expandableListView.setOnHeaderUpdateListener( this );
        expandableListView.setOnChildClickListener( this );
        expandableListView.setOnGroupClickListener( this );
        //stickyLayout.setOnGiveUpTouchEventListener( this );
    }

    void initData()
    {
        groupList = new ArrayList<Group>();
        Group group = null;
        for ( int i = 0 ; i < 3 ; i++ )
        {
            group = new Group();
            group.setTitle( "group-" + i );
            groupList.add( group );
        }

        childList = new ArrayList<List<People>>();
        for ( int i = 0 ; i < groupList.size() ; i++ )
        {
            ArrayList<People> childTemp;
            if ( i == 0 )
            {
                childTemp = new ArrayList<People>();
                for ( int j = 0 ; j < 13 ; j++ )
                {
                    People people = new People();
                    people.setName( "yy-" + j );
                    people.setAge( 30 );
                    people.setAddress( "sh-" + j );

                    childTemp.add( people );
                }
            }
            else if ( i == 1 )
            {
                childTemp = new ArrayList<People>();
                for ( int j = 0 ; j < 8 ; j++ )
                {
                    People people = new People();
                    people.setName( "ff-" + j );
                    people.setAge( 40 );
                    people.setAddress( "sh-" + j );

                    childTemp.add( people );
                }
            }
            else
            {
                childTemp = new ArrayList<People>();
                for ( int j = 0 ; j < 23 ; j++ )
                {
                    People people = new People();
                    people.setName( "hh-" + j );
                    people.setAge( 20 );
                    people.setAddress( "sh-" + j );

                    childTemp.add( people );
                }
            }
            childList.add( childTemp );
        }

    }

    @Override
    public boolean onGroupClick( final ExpandableListView parent,final View v,int groupPosition,final long id )
    {

        return false;
    }

    @Override
    public boolean onChildClick( ExpandableListView parent,View v,int groupPosition,int childPosition,long id )
    {
        Toast.makeText( ExpandableListView2Activity.this,childList.get( groupPosition ).get( childPosition ).getName(),Toast.LENGTH_SHORT ).show();

        return false;
    }

    @Override
    public View getPinnedHeader()
    {
        View headerView = ( ViewGroup ) getLayoutInflater().inflate( R.layout.group,null );
        headerView.setLayoutParams( new LayoutParams( LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT ) );

        return headerView;
    }

    @Override
    public void updatePinnedHeader( View headerView,int firstVisibleGroupPos )
    {
        Group firstVisibleGroup = ( Group ) adapter.getGroup( firstVisibleGroupPos );
        TextView textView = ( TextView ) headerView.findViewById( R.id.group );
        textView.setText( firstVisibleGroup.getTitle() );
    }

    @Override
    public boolean giveUpTouchEvent( MotionEvent event )
    {
        if ( expandableListView.getFirstVisiblePosition() == 0 )
        {
            View view = expandableListView.getChildAt( 0 );
            if ( view != null && view.getTop() >= 0 )
            {
                return true;
            }
        }
        return false;
    }

    class MyexpandableListAdapter extends BaseExpandableListAdapter
    {
        private Activity context;

        private LayoutInflater inflater;

        public MyexpandableListAdapter( Activity context )
        {
            this.context = context;
            inflater = LayoutInflater.from( context );
            initTestData();
        }

        // 返回父列表个数
        @Override
        public int getGroupCount()
        {
            return groupList.size();
        }

        // 返回子列表个数
        @Override
        public int getChildrenCount( int groupPosition )
        {
            return childList.get( groupPosition ).size();
        }

        @Override
        public Object getGroup( int groupPosition )
        {

            return groupList.get( groupPosition );
        }

        @Override
        public Object getChild( int groupPosition,int childPosition )
        {
            return childList.get( groupPosition ).get( childPosition );
        }

        @Override
        public long getGroupId( int groupPosition )
        {
            return groupPosition;
        }

        @Override
        public long getChildId( int groupPosition,int childPosition )
        {
            return childPosition;
        }

        @Override
        public boolean hasStableIds()
        {

            return true;
        }

        @Override
        public View getGroupView( int groupPosition,boolean isExpanded,View convertView,ViewGroup parent )
        {
            GroupHolder groupHolder = null;
            if ( convertView == null )
            {
                groupHolder = new GroupHolder();
                convertView = inflater.inflate( R.layout.group,null );
                groupHolder.textView = ( TextView ) convertView.findViewById( R.id.group );
                groupHolder.imageView = ( ImageView ) convertView.findViewById( R.id.image );
                convertView.setTag( groupHolder );
            }
            else
            {
                groupHolder = ( GroupHolder ) convertView.getTag();
            }

            groupHolder.textView.setText( ( ( Group ) getGroup( groupPosition ) ).getTitle() );
            if ( isExpanded )// ture is Expanded or false is not isExpanded
            {
                groupHolder.imageView.setImageResource( R.drawable.arrowup );
            }
            else
            {
                groupHolder.imageView.setImageResource( R.drawable.arrowdown );
            }
            return convertView;
        }

        @Override
        public View getChildView( int groupPosition,int childPosition,boolean isLastChild,View convertView,ViewGroup parent )
        {
            ViewHolder holder;

            if ( convertView == null )
            {
                holder = new ViewHolder();
                convertView = inflater.inflate( R.layout.one_list_item_layout,parent,false );
                holder.sublist = ( CustExpListview ) convertView.findViewById( R.id.sublist );
                holder.subExpandableListAdapter = new SubExpandableListAdapter( context );
                convertView.setTag( holder );
            }
            else
            {
                holder = ( ViewHolder ) convertView.getTag();
            }

            //SubExpandableListAdapter subExpandableListAdapter = new SubExpandableListAdapter( context );

            holder.sublist.setAdapter( holder.subExpandableListAdapter );
            holder.subExpandableListAdapter.setData( testDataList.get( 1 ) );
            int length = holder.sublist.getCount();
            for ( int i = 0 ; i < length ; i++ )
            {
                holder.sublist.expandGroup( i );
            }
            return convertView;
        }
        //@Override
        public View getChildView2( int groupPosition,int childPosition,boolean isLastChild,View convertView,ViewGroup parent )
        {
            ChildHolder childHolder = null;
            if ( convertView == null )
            {
                childHolder = new ChildHolder();
                convertView = inflater.inflate( R.layout.child,null );

                childHolder.textName = ( TextView ) convertView.findViewById( R.id.name );
                childHolder.textAge = ( TextView ) convertView.findViewById( R.id.age );
                childHolder.textAddress = ( TextView ) convertView.findViewById( R.id.address );
                childHolder.imageView = ( ImageView ) convertView.findViewById( R.id.image );
                Button button = ( Button ) convertView.findViewById( R.id.button1 );
                button.setOnClickListener( new OnClickListener()
                {
                    @Override
                    public void onClick( View v )
                    {
                        Toast.makeText( ExpandableListView2Activity.this,"clicked pos=",Toast.LENGTH_SHORT ).show();
                    }
                } );

                convertView.setTag( childHolder );
            }
            else
            {
                childHolder = ( ChildHolder ) convertView.getTag();
            }

            childHolder.textName.setText( ( ( People ) getChild( groupPosition,childPosition ) ).getName() );
            childHolder.textAge.setText( String.valueOf( ( ( People ) getChild( groupPosition,childPosition ) ).getAge() ) );
            childHolder.textAddress.setText( ( ( People ) getChild( groupPosition,childPosition ) ).getAddress() );

            return convertView;
        }

        @Override
        public boolean isChildSelectable( int groupPosition,int childPosition )
        {
            return true;
        }
    }

    class ViewHolder
    {
        CustExpListview sublist;
        SubExpandableListAdapter subExpandableListAdapter;//
    }

    class GroupHolder
    {
        TextView textView;

        ImageView imageView;
    }

    class ChildHolder
    {
        TextView textName;

        TextView textAge;

        TextView textAddress;

        ImageView imageView;
    }

    List<EmbedData> testDataList = new ArrayList<>();

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
}
