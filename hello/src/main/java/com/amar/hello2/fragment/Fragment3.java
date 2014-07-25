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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fragment3 extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        Log.d("Fragment 3", "onCreateView");

        View view = inflater.inflate(R.layout.fragment3, container, false);
        // ---Inflate the layout for this fragment---
        SimpleAdapter adapter = new SimpleAdapter(getActivity(), getData(), R.layout.vlist,
                new String[]{"vlistTitle", "vlistInfo", "vlistImg"}, new int[]{R.id.vlistTitle, R.id.vlistInfo, R.id.vlistImg});
        ListView lv = (ListView)( view.findViewById(R.id.fragment3list));
        lv.setAdapter(adapter);

        return view;
    }

    private List<Map<String, Object>> getData()
    {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("vlistTitle", "G1");
        map.put("vlistInfo", "google 1");
        map.put("vlistImg", R.drawable.i1);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("vlistTitle", "G2");
        map.put("vlistInfo", "google 2");
        map.put("vlistImg", R.drawable.i2);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("vlistTitle", "G3");
        map.put("vlistInfo", "google 3");
        map.put("vlistImg", R.drawable.i3);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("vlistTitle", "G1");
        map.put("vlistInfo", "google 1");
        map.put("vlistImg", R.drawable.i1);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("vlistTitle", "G2");
        map.put("vlistInfo", "google 2");
        map.put("vlistImg", R.drawable.i2);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("vlistTitle", "G3");
        map.put("vlistInfo", "google 3");
        map.put("vlistImg", R.drawable.i3);
        list.add(map);
        return list;
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        Log.d("Fragment 3", "onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Log.d("Fragment 3", "onCreate");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        Log.d("Fragment 3", "onActivityCreated");
    }

    @Override
    public void onStart()
    {
        super.onStart();
        Log.d("Fragment 3", "onStart");
    }

    @Override
    public void onResume()
    {
        super.onResume();
        Log.d("Fragment 3", "onResume");
    }

    @Override
    public void onPause()
    {
        super.onPause();
        Log.d("Fragment 3", "onPause");
    }

    @Override
    public void onStop()
    {
        super.onStop();
        Log.d("Fragment 3", "onStop");
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        Log.d("Fragment 3", "onDestroyView");
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        Log.d("Fragment 3", "onDestroy");
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        Log.d("Fragment 3", "onDetach");
    }
}
