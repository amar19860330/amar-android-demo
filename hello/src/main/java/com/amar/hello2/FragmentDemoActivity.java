package com.amar.hello2;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;

import com.amar.hello2.fragment.Fragment1;
import com.amar.hello2.fragment.Fragment2;


public class FragmentDemoActivity extends FragmentActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        //Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics display = this.getResources().getDisplayMetrics();
        int width = display.widthPixels;
        int height = display.heightPixels;
        if (width > height)
        {
            Fragment2 fragment = new Fragment2();
            //非扩展包的写法
            //getFragmentManager().beginTransaction().replace(R.id.fragmentLayout, fragment1).commit();
            //扩展包的写法
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentLayout, fragment).commit();
        }
        else
        {
            Fragment1 fragment = new Fragment1();
            //非扩展包的写法
            //getFragmentManager().beginTransaction().replace(R.id.fragmentLayout, fragment2).commit();
            //扩展包的写法
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentLayout, fragment).commit();
        }
    }


}
