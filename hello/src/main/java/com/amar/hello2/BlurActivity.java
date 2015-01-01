package com.amar.hello2;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

@EActivity( resName = "activity_blur" )
public class BlurActivity extends BaseActivity
{
    @AfterViews
    void afterViews()
    {
        
    }

}
