package com.amar.hello2.fragment;

import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amar.hello2.R;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.AbstractWheelTextAdapter;

public class Fragment2 extends Fragment
{
    WheelView wheelView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // ---Inflate the layout for this fragment---
        View rootView = inflater.inflate(R.layout.fragment2, container, false);

        wheelView = (WheelView)rootView.findViewById(R.id.wheelView);

        wheelView.setVisibleItems(5);
        wheelView.setCyclic(true);
        wheelView.setViewAdapter(new AbstractWheelTextAdapter(getActivity(), R.layout.wheel_text_view)
        {
            @Override
            protected CharSequence getItemText(int index)
            {
                return  new Date(  ( (new Date().getTime())/1000+index)*1000 ).toString();
            }
            @Override
            public int getItemsCount() {
                return 60;
            }
        });

        return rootView;
    }

    @Override
    public void onStart()
    {
        super.onStart();

        initBtn1();

    }



    public void initBtn1()
    {
        Button btnGetText = (Button) getActivity().findViewById(R.id.btnGetText);
        btnGetText.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                if (getActivity().findViewById(R.id.lblFragment1) != null)
                {
                    TextView lbl = (TextView) getActivity().findViewById(R.id.lblFragment1);
                    Toast.makeText(getActivity(), lbl.getText(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

}
