package com.amar.hello2;

import wyf.zcl.sqlitedb.SqLiteDBHelper;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View; // 引入相关包
import android.widget.Button; // 引入相关包
import android.view.View.OnClickListener;

public class DBActivity extends Activity
{
    String tag = "DBActivity";
    /**
     * Called when the activity is first created.
     */
    private Button createButton; // 创建数据库按钮

    private Button insertBut; // 增加数据库记录按钮

    private Button updateBut; // 更新数据库记录按钮

    private Button queryBut; // 查询数据库记录按钮

    private OnClickListener clickListener = new OnClickListener()
    {
        SqLiteDBHelper db = new SqLiteDBHelper( DBActivity.this );
        SQLiteDatabase dbRead;
        SQLiteDatabase dbWrite;

        @Override
        public void onClick( View v )
        {
            switch ( v.getId() )
            {
                case R.id.ButtonCreate:

                    break;
                case R.id.ButtonInsert:

                    dbWrite = db.getWritableDatabase();
                    ContentValues cv = new ContentValues();
                    cv.put( "username", "Jim" );
                    cv.put( "userid", "1" );
                    dbWrite.insert( "myuser", null, cv );

                    cv = new ContentValues();
                    cv.put( "username", "amar" );
                    cv.put( "userid", "2" );
                    dbWrite.insert( "myuser", null, cv );

                    db.close();
                    break;
                case R.id.ButtonUpdate:
                    break;
                case R.id.ButtonQuery:
                    dbRead = db.getReadableDatabase();
                    Cursor c = dbRead.query( "myuser", null, null, null, null, null, null );
                    while ( c.moveToNext() )
                    {
                        String name = c.getString( c.getColumnIndex( "username" ) );
                        String sex = c.getString( c.getColumnIndex( "userid" ) );
                        Log.i( tag, String.format( "username=%s,userid=%s", name, sex ) );
                    }
                    db.close();
                    break;
            }
        }
    };

    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );

        setContentView( R.layout.db );

        createButton = ( Button ) this.findViewById( R.id.ButtonCreate );
        insertBut = ( Button ) this.findViewById( R.id.ButtonInsert );
        updateBut = ( Button ) this.findViewById( R.id.ButtonUpdate );
        queryBut = ( Button ) this.findViewById( R.id.ButtonQuery );

        createButton.setOnClickListener( clickListener );
        insertBut.setOnClickListener( clickListener );
        updateBut.setOnClickListener( clickListener );
        queryBut.setOnClickListener( clickListener );

    }
}
