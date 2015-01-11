package com.amar.hello2;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.amar.hello2.expandlist.OneAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.WeakHashMap;

import se.emilsjolander.stickylistheaders.ExpandableStickyListHeadersListView;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

@EActivity(resName = "activity_expand_list3")
public class ExpandList3Activity extends Activity
{
    @ViewById(resName = "list")
    ExpandableStickyListHeadersListView onelist;
    OneAdapter oneAdapter;
    @AfterViews
    void afterViews()
    {
        oneAdapter = new OneAdapter(this);
        onelist.setAdapter( oneAdapter );
        onelist.setAnimExecutor( new AnimationExecutor() );
        onelist.setOnHeaderClickListener( new StickyListHeadersListView.OnHeaderClickListener()
        {
            @Override
            public void onHeaderClick( StickyListHeadersListView l,View header,int itemPosition,long headerId,boolean currentlySticky )
            {
                if ( onelist.isHeaderCollapsed( headerId ) )
                {
                    onelist.expand( headerId );
                }
                else
                {
                    onelist.collapse( headerId );
                }
            }
        } );
    }
    WeakHashMap<View, Integer> mOriginalViewHeightPool = new WeakHashMap<View, Integer>();
    class AnimationExecutor implements ExpandableStickyListHeadersListView.IAnimationExecutor
    {

        @Override
        public void executeAnim( final View target,final int animType )
        {
            if ( ExpandableStickyListHeadersListView.ANIMATION_EXPAND == animType && target.getVisibility() == View.VISIBLE )
            {
                return;
            }
            if ( ExpandableStickyListHeadersListView.ANIMATION_COLLAPSE == animType && target.getVisibility() != View.VISIBLE )
            {
                return;
            }
            if ( mOriginalViewHeightPool.get( target ) == null )
            {
                mOriginalViewHeightPool.put( target,target.getHeight() );
            }
            final int viewHeight = mOriginalViewHeightPool.get( target );
            float animStartY = animType == ExpandableStickyListHeadersListView.ANIMATION_EXPAND ? 0f : viewHeight;
            float animEndY = animType == ExpandableStickyListHeadersListView.ANIMATION_EXPAND ? viewHeight : 0f;
            final ViewGroup.LayoutParams lp = target.getLayoutParams();
            ValueAnimator animator = ValueAnimator.ofFloat( animStartY,animEndY );
            animator.setDuration( 200 );
            target.setVisibility( View.VISIBLE );
            animator.addListener( new Animator.AnimatorListener()
            {
                @Override
                public void onAnimationStart( Animator animator )
                {
                }

                @Override
                public void onAnimationEnd( Animator animator )
                {
                    if ( animType == ExpandableStickyListHeadersListView.ANIMATION_EXPAND )
                    {
                        target.setVisibility( View.VISIBLE );
                    }
                    else
                    {
                        target.setVisibility( View.GONE );
                    }
                    target.getLayoutParams().height = viewHeight;
                }

                @Override
                public void onAnimationCancel( Animator animator )
                {

                }

                @Override
                public void onAnimationRepeat( Animator animator )
                {

                }
            } );
            animator.addUpdateListener( new ValueAnimator.AnimatorUpdateListener()
            {
                @Override
                public void onAnimationUpdate( ValueAnimator valueAnimator )
                {
                    lp.height = ( ( Float ) valueAnimator.getAnimatedValue() ).intValue();
                    target.setLayoutParams( lp );
                    target.requestLayout();
                }
            } );
            animator.start();

        }
    }
}
