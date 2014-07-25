package com.amar.hello2;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;

import com.amar.hello2.fragment.Fragment1;
import com.amar.hello2.fragment.Fragment2;
import com.amar.hello2.fragment.Fragment3;
import com.amar.hello2.fragment.Fragment4;

public class TabHostDemoActivity extends FragmentActivity
{
    private FragmentTabHost mTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_tab_host_demo);
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        mTabHost.addTab(mTabHost.newTabSpec("simple").setIndicator("Simple",this.getResources().getDrawable(R.drawable.i1)), Fragment1.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("contacts1").setIndicator("Contacts1",this.getResources().getDrawable(R.drawable.i2)), Fragment2.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("contacts2").setIndicator("Contacts1",this.getResources().getDrawable(R.drawable.i3)), Fragment3.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("contacts3").setIndicator("Contacts1",this.getResources().getDrawable(R.drawable.i1)), Fragment4.class, null);
        mTabHost.setCurrentTab(2);

    }
}
//    setContentView(R.layout.activity_tab_host_demo);