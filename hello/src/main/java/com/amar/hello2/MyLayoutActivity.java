package com.amar.hello2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.amar.hello2.layout.MyAbsActivity;
import com.amar.hello2.layout.MyFrameActivity;
import com.amar.hello2.layout.MyGridActivity;
import com.amar.hello2.layout.MyLinearActivity;
import com.amar.hello2.layout.MyRelatActivity;
import com.amar.hello2.layout.MyTableActivity;


public class MyLayoutActivity extends BaseActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.mylayout);
    }

    public void gotoTableLayout(View view)
    {
        Intent intent = new Intent(this, MyTableActivity.class);
        startActivity(intent);
    }

    public void gotoGridLayout(View view)
    {
        Intent intent = new Intent(this, MyGridActivity.class);
        startActivity(intent);
    }

    public void gotoLinearLayout(View view)
    {
        Intent intent = new Intent(this, MyLinearActivity.class);
        startActivity(intent);
    }

    public void gotoAbsLayout(View view)
    {
        Intent intent = new Intent(this, MyAbsActivity.class);
        startActivity(intent);
    }

    public void gotoFrameLayout(View view)
    {
        Intent intent = new Intent(this, MyFrameActivity.class);
        startActivity(intent);
    }

    public void gotoRelatLayout(View view)
    {
        Intent intent = new Intent(this, MyRelatActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mylayout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings)
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
