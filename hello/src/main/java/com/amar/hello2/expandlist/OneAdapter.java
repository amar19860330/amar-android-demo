package com.amar.hello2.expandlist;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ExpandableListView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.amar.hello2.R;

import java.util.ArrayList;
import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * Created by SAM on 2015/1/11.
 */
public class OneAdapter extends BaseAdapter implements StickyListHeadersAdapter, SectionIndexer
{
    private final Activity mContext;
    private String[] mCountries;
    private int[] mSectionIndices;
    private Character[] mSectionLetters;
    private LayoutInflater mInflater;

    public OneAdapter( Activity context )
    {
        mContext = context;
        mInflater = LayoutInflater.from( context );
        mCountries = context.getResources().getStringArray( R.array.mycountries );
        mSectionIndices = getSectionIndices();
        mSectionLetters = getSectionLetters();
        initTestData();
    }

    private int[] getSectionIndices()
    {
        ArrayList<Integer> sectionIndices = new ArrayList<Integer>();
        char lastFirstChar = mCountries[ 0 ].charAt( 0 );
        sectionIndices.add( 0 );
        for ( int i = 1 ; i < mCountries.length ; i++ )
        {
            if ( mCountries[ i ].charAt( 0 ) != lastFirstChar )
            {
                lastFirstChar = mCountries[ i ].charAt( 0 );
                sectionIndices.add( i );
            }
        }
        int[] sections = new int[ sectionIndices.size() ];
        for ( int i = 0 ; i < sectionIndices.size() ; i++ )
        {
            sections[ i ] = sectionIndices.get( i );
        }
        return sections;
    }

    private Character[] getSectionLetters()
    {
        Character[] letters = new Character[ mSectionIndices.length ];
        for ( int i = 0 ; i < mSectionIndices.length ; i++ )
        {
            letters[ i ] = mCountries[ mSectionIndices[ i ] ].charAt( 0 );
        }
        return letters;
    }

    @Override
    public int getCount()
    {
        return mCountries.length;
    }

    @Override
    public Object getItem( int position )
    {
        return mCountries[ position ];
    }

    @Override
    public long getItemId( int position )
    {
        return position;
    }

    @Override
    public View getView( int position,View convertView,ViewGroup parent )
    {
        ViewHolder holder;

        if ( convertView == null )
        {
            holder = new ViewHolder();
            convertView = mInflater.inflate( R.layout.one_list_item_layout,parent,false );

            holder.sublist = ( CustExpListview ) convertView.findViewById( R.id.sublist );
            convertView.setTag( holder );
        }
        else
        {
            holder = ( ViewHolder ) convertView.getTag();
        }
        //holder.sublist.setText( mCountries[ position ] );
        //        SubExpandableListAdapter subExpandableListAdapter = new SubExpandableListAdapter( mContext );
        //        initTestData();
        //        subExpandableListAdapter.setData( testDataList.get( 0 ) );

        SubExpandableListAdapter subExpandableListAdapter = new SubExpandableListAdapter( mContext );

        holder.sublist.setAdapter( subExpandableListAdapter );
        subExpandableListAdapter.setData( testDataList.get( 0 ) );

        return convertView;
    }


    @Override
    public View getHeaderView( int position,View convertView,ViewGroup parent )
    {
        HeaderViewHolder holder;

        if ( convertView == null )
        {
            holder = new HeaderViewHolder();
            convertView = mInflater.inflate( R.layout.one_header,parent,false );
            holder.text = ( TextView ) convertView.findViewById( R.id.text1 );
            convertView.setTag( holder );
        }
        else
        {
            holder = ( HeaderViewHolder ) convertView.getTag();
        }

        // set header text as first char in name
        CharSequence headerChar = mCountries[ position ].subSequence( 0,1 );
        holder.text.setText( headerChar );

        return convertView;
    }

    /**
     * Remember that these have to be static, postion=1 should always return
     * the same Id that is.
     */
    @Override
    public long getHeaderId( int position )
    {
        // return the first character of the country as ID because this is what
        // headers are based upon
        return mCountries[ position ].subSequence( 0,1 ).charAt( 0 );
    }

    @Override
    public int getPositionForSection( int section )
    {
        if ( mSectionIndices.length == 0 )
        {
            return 0;
        }

        if ( section >= mSectionIndices.length )
        {
            section = mSectionIndices.length - 1;
        }
        else if ( section < 0 )
        {
            section = 0;
        }
        return mSectionIndices[ section ];
    }

    @Override
    public int getSectionForPosition( int position )
    {
        for ( int i = 0 ; i < mSectionIndices.length ; i++ )
        {
            if ( position < mSectionIndices[ i ] )
            {
                return i - 1;
            }
        }
        return mSectionIndices.length - 1;
    }

    @Override
    public Object[] getSections()
    {
        return mSectionLetters;
    }

    public void clear()
    {
        mCountries = new String[ 0 ];
        mSectionIndices = new int[ 0 ];
        mSectionLetters = new Character[ 0 ];
        notifyDataSetChanged();
    }

    public void restore()
    {
        mCountries = mContext.getResources().getStringArray( R.array.mycountries );
        mSectionIndices = getSectionIndices();
        mSectionLetters = getSectionLetters();
        notifyDataSetChanged();
    }

    class HeaderViewHolder
    {
        TextView text;
    }

    class ViewHolder
    {
        //TextView text;
        CustExpListview sublist;
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
