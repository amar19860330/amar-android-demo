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

import java.util.Timer;
import java.util.TimerTask;

public class Fragment2 extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // ---Inflate the layout for this fragment---

        return inflater.inflate(R.layout.fragment2, container, false);


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
