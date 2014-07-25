package com.amar.hello2.layout;

import android.app.Activity;
import android.os.Bundle;

import com.amar.hello2.R;

/**
 * Created by Office on 2014/6/12.
 */
public class MyLinearActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.mylinearlayout);
    }
}
