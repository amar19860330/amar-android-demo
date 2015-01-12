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

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amar.hello2.R;
import com.nhaarman.listviewanimations.ArrayAdapter;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

public class ExpandListAdapter extends ArrayAdapter<String> implements StickyListHeadersAdapter
{
    private final Context mContext;

    @Override
    public long getHeaderId( final int position )
    {
        long result = position / 3;
        return result;
    }

    public ExpandListAdapter( final Context context )
    {
        mContext = context;
        for ( int i = 0 ; i < 100 ; i++ )
        {
            add( mContext.getString( R.string.row_number,i ) );
        }
    }

    @Override
    public long getItemId( final int position )
    {
        return 0;//getItem( position ).hashCode();
    }

    @Override
    public boolean hasStableIds()
    {
        return true;
    }

    @Override
    public View getView( final int position,final View convertView,final ViewGroup parent )
    {
        TextView view = ( TextView ) convertView;
        if ( view == null )
        {
            view = ( TextView ) LayoutInflater.from( mContext ).inflate( R.layout.list_row,parent,false );
        }

        view.setText( getItem( position ) );
        return view;
    }

    @Override
    public View getHeaderView( final int position,final View convertView,final ViewGroup parent )
    {
        TextView view = ( TextView ) convertView;
        if ( view == null )
        {
            view = ( TextView ) LayoutInflater.from( mContext ).inflate( R.layout.list_header,parent,false );
        }

        view.setText( mContext.getString( R.string.header,getHeaderId( position ) ) );
        //view.setText( mContext.getString( R.string.header,position ) );
        return view;
    }

}