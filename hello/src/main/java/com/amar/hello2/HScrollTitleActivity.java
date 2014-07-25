package com.amar.hello2;

/**
 * Created by SAM on 2014/7/5.
 */

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

/**
 * HorizontalScrollView和ViewPager联动效果
 * 上面为HorizontalScrollView,下面为ViewPager
 *
 * @author zj
 *         2012-5-23 下午1:07:06
 */
public class HScrollTitleActivity extends Activity implements OnCheckedChangeListener
{
    private RadioGroup mRadioGroup;
    private RadioButton mRadioButton1;
    private RadioButton mRadioButton2;
    private RadioButton mRadioButton3;
    private RadioButton mRadioButton4;
    private RadioButton mRadioButton5;
    private ImageView mImageView;
    private float mCurrentCheckedRadioLeft;//当前被选中的RadioButton距离左侧的距离
    private HorizontalScrollView mHorizontalScrollView;//上面的水平滚动控件
    private ViewPager mViewPager;   //下方的可横向拖动的控件
    private ArrayList<View> mViews;//用来存放下方滚动的layout(layout_1,layout_2,layout_3)

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hscroll);

        iniController();
        iniListener();
        iniVariable();

        mRadioButton1.setChecked(true);
        mViewPager.setCurrentItem(1);
        mCurrentCheckedRadioLeft = getCurrentCheckedRadioLeft();

    }

    private void iniVariable()
    {
        // TODO Auto-generated method stub
        mViews = new ArrayList<View>();
        mViews.add(getLayoutInflater().inflate(R.layout.lay1, null));
        mViews.add(getLayoutInflater().inflate(R.layout.lay2, null));
        mViews.add(getLayoutInflater().inflate(R.layout.lay3, null));
        mViews.add(getLayoutInflater().inflate(R.layout.lay1, null));
        mViews.add(getLayoutInflater().inflate(R.layout.lay2, null));
        mViews.add(getLayoutInflater().inflate(R.layout.lay3, null));
        mViews.add(getLayoutInflater().inflate(R.layout.lay1, null));

        mViewPager.setAdapter(new MyPagerAdapter());//设置ViewPager的适配器
    }

    /**
     * RadioGroup点击CheckedChanged监听
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId)
    {

        AnimationSet _AnimationSet = new AnimationSet(true);
        TranslateAnimation _TranslateAnimation;

        Log.i("zj", "checkedid=" + checkedId);
        if (checkedId == R.id.btn1)
        {
            _TranslateAnimation = new TranslateAnimation(mCurrentCheckedRadioLeft, getResources().getDimension(R.dimen.rdo1), 0f, 0f);
            _AnimationSet.addAnimation(_TranslateAnimation);
            _AnimationSet.setFillBefore(false);
            _AnimationSet.setFillAfter(true);
            _AnimationSet.setDuration(100);
            /*LayoutParams _LayoutParams1 = new LayoutParams(100, 4);
            _LayoutParams1.setMargins(0, 0, 0, 0);
            _LayoutParams1.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);*/
            //mImageView.bringToFront();
            mImageView.startAnimation(_AnimationSet);//开始上面蓝色横条图片的动画切换
            //mImageView.setLayoutParams(_LayoutParams1);
            mViewPager.setCurrentItem(1);//让下方ViewPager跟随上面的HorizontalScrollView切换
        }
        else if (checkedId == R.id.btn2)
        {
            _TranslateAnimation = new TranslateAnimation(mCurrentCheckedRadioLeft, getResources().getDimension(R.dimen.rdo2), 0f, 0f);

            _AnimationSet.addAnimation(_TranslateAnimation);
            _AnimationSet.setFillBefore(false);
            _AnimationSet.setFillAfter(true);
            _AnimationSet.setDuration(100);

            //mImageView.bringToFront();
            mImageView.startAnimation(_AnimationSet);

            mViewPager.setCurrentItem(2);
        }
        else if (checkedId == R.id.btn3)
        {
            _TranslateAnimation = new TranslateAnimation(mCurrentCheckedRadioLeft, getResources().getDimension(R.dimen.rdo3), 0f, 0f);

            _AnimationSet.addAnimation(_TranslateAnimation);
            _AnimationSet.setFillBefore(false);
            _AnimationSet.setFillAfter(true);
            _AnimationSet.setDuration(100);

            //mImageView.bringToFront();
            mImageView.startAnimation(_AnimationSet);

            mViewPager.setCurrentItem(3);
        }
        else if (checkedId == R.id.btn4)
        {
            _TranslateAnimation = new TranslateAnimation(mCurrentCheckedRadioLeft, getResources().getDimension(R.dimen.rdo4), 0f, 0f);

            _AnimationSet.addAnimation(_TranslateAnimation);
            _AnimationSet.setFillBefore(false);
            _AnimationSet.setFillAfter(true);
            _AnimationSet.setDuration(100);

            //mImageView.bringToFront();
            mImageView.startAnimation(_AnimationSet);
            mViewPager.setCurrentItem(4);
        }
        else if (checkedId == R.id.btn5)
        {
            _TranslateAnimation = new TranslateAnimation(mCurrentCheckedRadioLeft, getResources().getDimension(R.dimen.rdo5), 0f, 0f);

            _AnimationSet.addAnimation(_TranslateAnimation);
            _AnimationSet.setFillBefore(false);
            _AnimationSet.setFillAfter(true);
            _AnimationSet.setDuration(100);

            //mImageView.bringToFront();
            mImageView.startAnimation(_AnimationSet);

            mViewPager.setCurrentItem(5);
        }

        mCurrentCheckedRadioLeft = getCurrentCheckedRadioLeft();

        Log.i("zj", "getCurrentCheckedRadioLeft=" + getCurrentCheckedRadioLeft());
        Log.i("zj", "getDimension=" + getResources().getDimension(R.dimen.rdo2));

        mHorizontalScrollView.smoothScrollTo((int) mCurrentCheckedRadioLeft - (int) getResources().getDimension(R.dimen.rdo2), 0);
    }

    /**
     * 获得当前被选中的RadioButton距离左侧的距离
     */
    private float getCurrentCheckedRadioLeft()
    {
        // TODO Auto-generated method stub
        if (mRadioButton1.isChecked())
        {
            //Log.i("zj", "currentCheckedRadioLeft="+getResources().getDimension(R.dimen.rdo1));
            return getResources().getDimension(R.dimen.rdo1);
        }
        else if (mRadioButton2.isChecked())
        {
            //Log.i("zj", "currentCheckedRadioLeft="+getResources().getDimension(R.dimen.rdo2));
            return getResources().getDimension(R.dimen.rdo2);
        }
        else if (mRadioButton3.isChecked())
        {
            //Log.i("zj", "currentCheckedRadioLeft="+getResources().getDimension(R.dimen.rdo3));
            return getResources().getDimension(R.dimen.rdo3);
        }
        else if (mRadioButton4.isChecked())
        {
            //Log.i("zj", "currentCheckedRadioLeft="+getResources().getDimension(R.dimen.rdo4));
            return getResources().getDimension(R.dimen.rdo4);
        }
        else if (mRadioButton5.isChecked())
        {
            //Log.i("zj", "currentCheckedRadioLeft="+getResources().getDimension(R.dimen.rdo5));
            return getResources().getDimension(R.dimen.rdo5);
        }
        return 0f;
    }

    private void iniListener()
    {
        // TODO Auto-generated method stub

        mRadioGroup.setOnCheckedChangeListener(this);


        mViewPager.setOnPageChangeListener(new MyPagerOnPageChangeListener());
    }

    private void iniController()
    {
        // TODO Auto-generated method stub
        mRadioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        mRadioButton1 = (RadioButton) findViewById(R.id.btn1);
        mRadioButton2 = (RadioButton) findViewById(R.id.btn2);
        mRadioButton3 = (RadioButton) findViewById(R.id.btn3);
        mRadioButton4 = (RadioButton) findViewById(R.id.btn4);
        mRadioButton5 = (RadioButton) findViewById(R.id.btn5);

        mImageView = (ImageView) findViewById(R.id.img1);

        mHorizontalScrollView = (HorizontalScrollView) findViewById(R.id.horizontalScrollView);

        mViewPager = (ViewPager) findViewById(R.id.pager);
    }

    /**
     * ViewPager的适配器
     *
     * @author zj
     *         2012-5-24 下午2:26:57
     */
    private class MyPagerAdapter extends PagerAdapter
    {

        @Override
        public void destroyItem(View v, int position, Object obj)
        {
            // TODO Auto-generated method stub
            ((ViewPager) v).removeView(mViews.get(position));
        }

        @Override
        public void finishUpdate(View arg0)
        {
            // TODO Auto-generated method stub

        }

        @Override
        public int getCount()
        {
            // TODO Auto-generated method stub
            return mViews.size();
        }

        @Override
        public Object instantiateItem(View v, int position)
        {
            ((ViewPager) v).addView(mViews.get(position));
            return mViews.get(position);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1)
        {
            // TODO Auto-generated method stub
            return arg0 == arg1;
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1)
        {
            // TODO Auto-generated method stub

        }

        @Override
        public Parcelable saveState()
        {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public void startUpdate(View arg0)
        {
            // TODO Auto-generated method stub

        }

    }

    /**
     * ViewPager的PageChangeListener(页面改变的监听器)
     *
     * @author zj
     *         2012-5-24 下午3:14:27
     */
    private class MyPagerOnPageChangeListener implements OnPageChangeListener
    {

        @Override
        public void onPageScrollStateChanged(int arg0)
        {
            // TODO Auto-generated method stub

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2)
        {
            // TODO Auto-generated method stub

        }

        /**
         * 滑动ViewPager的时候,让上方的HorizontalScrollView自动切换
         */
        @Override
        public void onPageSelected(int position)
        {
            // TODO Auto-generated method stub
            //Log.i("zj", "position="+position);

            if (position == 0)
            {
                mViewPager.setCurrentItem(1);
            }
            else if (position == 1)
            {
                mRadioButton1.performClick();
            }
            else if (position == 2)
            {
                mRadioButton2.performClick();
            }
            else if (position == 3)
            {
                mRadioButton3.performClick();
            }
            else if (position == 4)
            {
                mRadioButton4.performClick();
            }
            else if (position == 5)
            {
                mRadioButton5.performClick();
            }
            else if (position == 6)
            {
                mViewPager.setCurrentItem(5);
            }
        }

    }

}