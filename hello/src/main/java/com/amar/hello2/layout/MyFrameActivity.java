package com.amar.hello2.layout;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.amar.hello2.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Office on 2014/6/12.
 */
public class MyFrameActivity extends Activity
{
    private int currentColor = 0;
    final int[] colors = new int[]{R.color.color7, R.color.color6, R.color.color5, R.color.color4, R.color.color3, R.color.color2, R.color.color1};
    final int[] names = new int[]{R.id.view1, R.id.view2, R.id.view3, R.id.view4, R.id.view5, R.id.view6, R.id.view7};
    TextView views[] = new TextView[7];

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myframelayout);
        for (int i = 0; i < 7; i++)
        {
            views[i] = (TextView) findViewById(names[i]);
        }
        final Handler handler = new Handler()
        {
            @Override
            public void handleMessage(Message msg)
            {
                //表明消息来自本程序所发送
                if (msg.what == 0x1122)
                {
                //依次改变7个TextView的背景色
                for (int i = 0; i < 7 - currentColor; i++)
                {
                    views[i].setBackgroundResource(colors[i + currentColor]);
                }
                for (int i = 7 - currentColor, j = 0; i < 7; i++, j++)
                {
                    views[i].setBackgroundResource(colors[j]);
                }
            }
                super.handleMessage(msg);
            }
        };
        //定义一个线程周期性的改变currentColor变量值
        new Timer().schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                currentColor++;
                if (currentColor >= 6)
                {
                    currentColor = 0;
                }
                //发送一条消息通知系统改变7个TextView组件的背景色
                Message m = new Message();
                //给该消息定义一个标识
                m.what = 0x1122;
                handler.sendMessage(m);
            }
        }, 0, 100);

    }
}
