package com.amar.hello2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ExpandableListViewActivity extends BaseActivity
{
    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.my_expandable_listview );
        //创建一个BaseExpandableListAdapter对象
        final ExpandableListAdapter adapter = new BaseExpandableListAdapter()
        {
            int[] logos = new int[]{ R.drawable.p,R.drawable.z,R.drawable.t };
            private String[] armTypes = new String[]{ "神族兵种","虫族兵种","人族兵种" };
            private String[][] arms = new String[][]{ { "狂战士","龙骑士","黑暗圣堂","电兵" },{ "小狗","刺蛇","飞龙","自爆飞机","大象" },{ "机枪兵","护士MM","幽灵","坦克","护卫舰","科学球" } };

            //获取指定组位置、指定子列表项处的子列表项数据
            @Override
            public Object getChild( int groupPosition,int childPosition )
            {
                return arms[ groupPosition ][ childPosition ];
            }

            @Override
            public long getChildId( int groupPosition,int childPosition )
            {
                return childPosition;
            }

            @Override
            public int getChildrenCount( int groupPosition )
            {
                return arms[ groupPosition ].length;
            }

            private TextView getTextView()
            {
                AbsListView.LayoutParams lp = new AbsListView.LayoutParams( ViewGroup.LayoutParams.MATCH_PARENT,64 );
                TextView textView = new TextView( ExpandableListViewActivity.this );
                textView.setLayoutParams( lp );
                textView.setGravity( Gravity.CENTER_VERTICAL | Gravity.LEFT );
                textView.setPadding( 90,0,0,0 );
                textView.setTextSize( 20 );
                return textView;
            }

            //该方法决定每个子选项的外观
            @Override
            public View getChildView( int groupPosition,int childPosition,boolean isLastChild,View convertView,ViewGroup parent )
            {
                View view = getLayoutInflater().inflate( R.layout.vlist,null );

                ImageView image = ( ImageView ) view.findViewById( R.id.vlistImg );
                TextView title = ( TextView ) view.findViewById( R.id.vlistTitle );
                TextView info = ( TextView ) view.findViewById( R.id.vlistInfo );

                title.setText( getChild( groupPosition,childPosition ).toString() );
                info.setText( "position:" + groupPosition + "," + childPosition );
                image.setImageResource( R.drawable.i3 );
                return view;
            }

            //获取指定组位置处的组数据
            @Override
            public Object getGroup( int groupPosition )
            {
                return armTypes[ groupPosition ];
            }

            @Override
            public int getGroupCount()
            {
                return armTypes.length;
            }

            @Override
            public long getGroupId( int groupPosition )
            {
                return groupPosition;
            }

            //该方法决定每个组选项的外观
            @Override
            public View getGroupView( int groupPosition,boolean isExpanded,View convertView,ViewGroup parent )
            {
                LinearLayout ll = new LinearLayout( ExpandableListViewActivity.this );
                ll.setOrientation( LinearLayout.HORIZONTAL );
                ImageView logo = new ImageView( ExpandableListViewActivity.this );
                logo.setImageResource( logos[ groupPosition ] );
                //ll.addView(logo);
                TextView textView = getTextView();
                textView.setText( getGroup( groupPosition ).toString() );
                ll.addView( textView );
                return ll;
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
        };
        final ExpandableListView expandListView = ( ExpandableListView ) findViewById( R.id.expandablelist );
        expandListView.setOnGroupExpandListener( new ExpandableListView.OnGroupExpandListener()
        {
            @Override
            public void onGroupExpand( int groupPosition )
            {
                for ( int i = 0 ; i < adapter.getGroupCount() ; i++ )
                {
                    if ( groupPosition != i )
                    {
                        expandListView.collapseGroup( i );
                    }
                }
            }
        } );

        expandListView.setAdapter( adapter );
    }
}