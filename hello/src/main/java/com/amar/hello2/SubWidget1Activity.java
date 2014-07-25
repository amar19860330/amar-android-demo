package com.amar.hello2;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class SubWidget1Activity extends BaseActivity
{
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_widget1);

        DatePicker dp = (DatePicker) findViewById(R.id.mydatepicker);
        TimePicker tp = (TimePicker) findViewById(R.id.mytimepicker);

        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        hour = c.get(Calendar.HOUR);
        minute = c.get(Calendar.MINUTE);

        dp.init(year, month, day, new DatePicker.OnDateChangedListener()
        {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                SubWidget1Activity.this.year = year;
                SubWidget1Activity.this.month = monthOfYear;
                SubWidget1Activity.this.day = dayOfMonth;
                showDate(year, month, day, hour, minute);
            }
        });

        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener()
        {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute)
            {
                SubWidget1Activity.this.hour = hourOfDay;
                SubWidget1Activity.this.minute = minute;
                showDate(year, month, day, hour, minute);
            }
        });

    }

    private void showDate(int year, int month, int day, int hour, int minute)
    {
        EditText et = (EditText) findViewById(R.id.mydatetimeinfo);
        et.setText("您选择的时间是" + year + "-" + (month + 1) + "-" + day + " " + hour + ":" + minute);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sub_widget1, menu);
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
