package com.amar.hello2.expandlist;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SAM on 2015/1/5.
 */
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
