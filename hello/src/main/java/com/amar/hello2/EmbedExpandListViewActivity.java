package com.amar.hello2;

import android.widget.ExpandableListView;

import com.amar.hello2.expandlist.CustExpListview;
import com.amar.hello2.expandlist.EmbedData;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.amar.hello2.expandlist.ExpandableListAdapter;

@EActivity(resName = "activity_embed_expand_list_view")
public class EmbedExpandListViewActivity extends BaseActivity implements Serializable
{
    private static final long serialVersionUID = 2762018523406420889L;

    @ViewById(resName = "expandablelist")
    CustExpListview expandableListView;

    List<EmbedData> testDataList;

    @AfterViews
    void afterViews()
    {
        initTestData();

        final ExpandableListAdapter expandableListAdapter = new ExpandableListAdapter( this,expandableListView );

        expandableListView.setOnGroupExpandListener( new ExpandableListView.OnGroupExpandListener()
        {
            @Override
            public void onGroupExpand( int groupPosition )
            {
                for ( int i = 0 ; i < expandableListAdapter.getGroupCount() ; i++ )
                {
                    if ( groupPosition != i )
                    {
                        //expandableListView.collapseGroup( i );
                    }
                }
            }
        } );
        expandableListAdapter.setData( testDataList );
        expandableListView.setAdapter( expandableListAdapter );
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
