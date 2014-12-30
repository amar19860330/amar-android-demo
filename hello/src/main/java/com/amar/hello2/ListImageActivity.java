package com.amar.hello2;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.os.Handler;


import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import android.view.ViewGroup.LayoutParams;
import android.widget.Toast;

import com.amar.hello2.widget.MyListView;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

@EActivity( resName = "activity_list_image" )
public class ListImageActivity extends BaseActivity implements View.OnTouchListener
{
    @ViewById( resName = "other_menu" )
    MyListView menuListView;
    @ViewById( resName = "other" )
    MyListView otherListView;

    private float mDownX;
    private float mDownY;
    private boolean isClick;
    Subscriber< Long > otherSubscriber = null;
    Subscriber< Long > menuSubscriber = null;
    //AtomicBoolean otherAllowScroll = new AtomicBoolean( true );
    //AtomicBoolean menuAllowScroll = new AtomicBoolean( true );
    AtomicBoolean allowScroll = new AtomicBoolean( true );

    @AfterViews
    void afterViews()
    {
        ImageListAdapter adapterOther = new ImageListAdapter( this, 160 );
        ImageListAdapter adapterMenu = new ImageListAdapter( this, 300 );

        List< Integer > imageList = new ArrayList< Integer >();
        imageList.add( R.drawable.menu_1 );
        imageList.add( R.drawable.menu_2 );
        imageList.add( R.drawable.menu_3 );
        imageList.add( R.drawable.menu_4 );
        imageList.add( R.drawable.menu_5 );
        imageList.add( R.drawable.menu_6 );
        imageList.add( R.drawable.menu_7 );
        imageList.add( R.drawable.menu_8 );
        imageList.add( R.drawable.menu_9 );
        imageList.add( R.drawable.menu_10 );
        imageList.add( R.drawable.menu_11 );
        imageList.add( R.drawable.menu_12 );

        adapterOther.addData( imageList );
        adapterMenu.addData( imageList );
        adapterMenu.addData( imageList.subList( 0, 6 ) );

        otherListView.setAdapter( adapterOther );
        menuListView.setAdapter( adapterMenu );

        otherListView.setSelection( 5000 );
        menuListView.setSelection( 5000 );

        menuListView.setOnScrollListener( adapterMenu );
        otherListView.setOnScrollListener( adapterOther );

        menuListView.setOnItemClickListener( adapterMenu );
        otherListView.setOnItemClickListener( adapterOther );

        menuListView.setOnTouchListener( this );
        otherListView.setOnTouchListener( this );

        otherListView.setRelatedListView( menuListView );
        menuListView.setRelatedListView( otherListView );

        scrcollControl( menuListView, 50, 2, allowScroll );
        scrcollControl( otherListView, 30, 2, allowScroll );
    }

    void scrcollControl( ListView listView, int scrollDelay, int scrollSpeed, AtomicBoolean allowScroll )
    {
        Handler srcollHandler = new Handler();
        Runnable scrollRunnable = new ScrollRunnable( listView, srcollHandler, scrollDelay, scrollSpeed, allowScroll );
        srcollHandler.postDelayed( scrollRunnable, scrollDelay );
    }

    class ScrollRunnable implements Runnable
    {
        ListView _listView;
        int _scrollDelay;
        int _scrollSpeed;
        Handler _srcollHandler;
        AtomicBoolean _allowScroll;

        public ScrollRunnable( ListView listView, Handler srcollHandler, int scrollDelay, int scrollSpeed, AtomicBoolean allowScroll )
        {
            this._listView = listView;
            this._scrollDelay = scrollDelay;
            this._scrollSpeed = scrollSpeed;
            this._srcollHandler = srcollHandler;
            this._allowScroll = allowScroll;
        }

        @Override
        public void run()
        {
            if ( !_allowScroll.get() )
            {
                _srcollHandler.postDelayed( this, _scrollDelay );
                return;
            }

            _listView.smoothScrollBy( _scrollSpeed, _scrollDelay );

            _srcollHandler.postDelayed( this, _scrollDelay );
        }
    }

    private void detectItemClick( ListView listView, MotionEvent event )
    {
        switch ( event.getAction() & MotionEvent.ACTION_MASK )
        {
            case MotionEvent.ACTION_DOWN:
                mDownX = event.getX();
                mDownY = event.getY();
                isClick = true;
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                if ( isClick )
                {
                    // Find the item view that was touched (perform a hit test)
                    Rect rect = new Rect();
                    int childCount = listView.getChildCount();
                    int[] listViewCoords = new int[ 2 ];
                    listView.getLocationOnScreen( listViewCoords );
                    int x = ( int ) event.getRawX() - listViewCoords[ 0 ];
                    int y = ( int ) event.getRawY() - listViewCoords[ 1 ];
                    View child;
                    for ( int i = 0 ; i < childCount ; i++ )
                    {
                        child = listView.getChildAt( i );
                        if ( child == null )
                        {
                            return;
                        }
                        child.getHitRect( rect );
                        if ( rect.contains( x, y ) )
                        {
                            int position = listView.getPositionForView( child );
                            try
                            {
                                AdapterView.OnItemClickListener listener = ( AdapterView.OnItemClickListener ) listView.getAdapter();
                                listener.onItemClick( listView, child, position, 0 );
                            }
                            catch ( Exception e )
                            {
                            }
                            break;
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                final float SCROLL_THRESHOLD = 10;
                if ( isClick && ( Math.abs( mDownX - event.getX() ) > SCROLL_THRESHOLD || Math.abs( mDownY - event.getY() ) > SCROLL_THRESHOLD ) )
                {
                    isClick = false;
                }
                break;
            default:
                break;
        }
    }

    /**
     * @param v
     * @param event
     * @return
     */
    @Override
    public boolean onTouch( View v, MotionEvent event )
    {
        if ( v.getId() == menuListView.getId() )
        {
            dealAction( event, menuSubscriber, allowScroll );
            detectItemClick( menuListView, event );
        }
        if ( v.getId() == otherListView.getId() )
        {
            dealAction( event, otherSubscriber, allowScroll );
            detectItemClick( otherListView, event );
        }

        return false;
    }

    public void dealAction( MotionEvent event, Subscriber< Long > subscriber,final AtomicBoolean allowScroll )
    {
        if ( event.getAction() == MotionEvent.ACTION_DOWN )
        {
            allowScroll.set( false );
        }
        else if ( event.getAction() == MotionEvent.ACTION_UP )
        {
            subscriber = new Subscriber< Long >()
            {
                @Override
                public void onCompleted()
                {

                }

                @Override
                public void onError( Throwable e )
                {

                }

                @Override
                public void onNext( Long o )
                {
                    allowScroll.set( true );
                }
            };
            Observable.timer( 1, TimeUnit.SECONDS ).subscribe( subscriber );
        }
        else if ( event.getAction() == MotionEvent.ACTION_MOVE )
        {

            if ( subscriber != null && !subscriber.isUnsubscribed() )
            {
                subscriber.unsubscribe();
            }
        }
    }

    public class ImageListAdapter extends BaseAdapter implements AbsListView.OnScrollListener, AdapterView.OnItemClickListener
    {
        protected LayoutInflater inflater;
        List< Integer > dataList;
        int height;
        Context _context;

        public ImageListAdapter( Context context, int height )
        {
            this.inflater = LayoutInflater.from( context );
            this.dataList = new ArrayList< Integer >();
            this.height = height;
            this._context = context;
        }

        @Override
        public void onItemClick( AdapterView< ? > parent, View view, int position, long id )
        {
            Toast.makeText( _context, getItem( position ).toString() + ":" + position, Toast.LENGTH_SHORT ).show();
        }

        @Override
        public void onScrollStateChanged( AbsListView view, int scrollState )
        {
        }

        @Override
        public void onScroll( AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount )
        {
//            if ( firstVisibleItem <= 2 )
//            {
//                view.setSelection( dataList.size() + 2 );
//            }
//            else if ( firstVisibleItem + visibleItemCount > getCount() - 2 )
//            {
//                view.setSelection( firstVisibleItem - dataList.size() );
//            }
        }

        public void addData( List< Integer > dataList )
        {
            this.dataList.addAll( dataList );
            this.notifyDataSetChanged();
        }

        private int friction = 10000;
        @Override
        public int getCount()
        {
            return this.dataList.size() * friction;
        }

        @Override
        public Object getItem( int position )
        {
            return this.dataList.get( position % dataList.size() );
        }

        @Override
        public long getItemId( int position )
        {
            return position;
        }

        @Override
        public View getView( int position, View convertView, ViewGroup parent )
        {
            final ViewHolder viewHolder;
            if ( convertView == null )
            {
                viewHolder = new ViewHolder();
                convertView = inflater.inflate( R.layout.list_image, null );
                viewHolder.imageView = ( ImageView ) convertView.findViewById( R.id.image );
                viewHolder.rootView = ( LinearLayout ) convertView.findViewById( R.id.root );
                viewHolder.titleTxt = ( TextView ) convertView.findViewById( R.id.title );
                convertView.setTag( viewHolder );
            }
            else
            {
                viewHolder = ( ViewHolder ) convertView.getTag();
            }

            final int _position = position;
            //LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams( LayoutParams.MATCH_PARENT, height );
            //AbsListView.LayoutParams params = new AbsListView.LayoutParams( LayoutParams.MATCH_PARENT, height );
            //viewHolder.rootView.setLayoutParams( lparams );

            viewHolder.titleTxt.setText( position + ":" + position % dataList.size() );
            viewHolder.imageView.setImageResource( dataList.get( position % dataList.size() ) );
            return convertView;
        }

        public final class ViewHolder
        {
            ImageView imageView;
            LinearLayout rootView;
            TextView titleTxt;
        }
    }

}
