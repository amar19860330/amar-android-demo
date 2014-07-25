package com.amar.hello2.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.amar.hello2.R;


public class Fragment4 extends Fragment
{
    final public String tag = "Fragment4";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        Log.d(tag, "onCreateView");

        View view = inflater.inflate(R.layout.fragment4, container, false);
        // ---Inflate the layout for this fragment---

        return view;
    }

    
    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        Log.d(tag, "onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Log.d(tag, "onCreate");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        Log.d(tag, "onActivityCreated");
    }

    @Override
    public void onStart()
    {
        super.onStart();
        Log.d(tag, "onStart");
    }

    @Override
    public void onResume()
    {
        super.onResume();
        Log.d(tag, "onResume");
    }

    @Override
    public void onPause()
    {
        super.onPause();
        Log.d(tag, "onPause");
    }

    @Override
    public void onStop()
    {
        super.onStop();
        Log.d(tag, "onStop");
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        Log.d(tag, "onDestroyView");
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        Log.d(tag, "onDestroy");
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        Log.d(tag, "onDetach");
    }
}
