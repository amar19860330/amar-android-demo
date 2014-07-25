package com.amar.hello2.fragment;

import com.amar.hello2.R;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;

import java.util.Arrays;

public class Fragment1 extends Fragment implements RadioGroup.OnCheckedChangeListener
{
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState )
    {

        Log.d( "Fragment 1", "onCreateView" );

        return inflater.inflate( R.layout.fragment1, container, false );
    }


    @Override
    public void onAttach( Activity activity )
    {
        super.onAttach( activity );
        Log.d( "Fragment 1", "onAttach" );

    }

    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        Log.d( "Fragment 1", "onCreate" );

    }

    int[] radioButtonIds = new int[]{ R.id.fragment1_menu_1 , R.id.fragment1_menu_2 , R.id.fragment1_menu_3 , R.id.fragment1_menu_4 };

    @Override
    public void onActivityCreated( Bundle savedInstanceState )
    {
        super.onActivityCreated( savedInstanceState );
        Log.d( "Fragment 1", "onActivityCreated" );
        initDialog();

        initPopupWindow();
        RadioGroup radioGroup = ( RadioGroup ) getActivity().findViewById( R.id.fragment1_menu );
        radioGroup.setOnCheckedChangeListener( this );
    }

    @Override
    public void onCheckedChanged( RadioGroup group, int checkedId )
    {
        int index = Arrays.binarySearch( radioButtonIds, checkedId );
        Fragment fragment = null;
        if ( index % 2 == 0 )
        {
            fragment = new Fragment3();
        }
        else
        {
            fragment = new Fragment4();
        }
        getChildFragmentManager().beginTransaction().replace( R.id.fragment1_layout, fragment ).commit();
    }

    @Override
    public void onStart()
    {
        super.onStart();
        Log.d( "Fragment 1", "onStart" );
    }

    public void initPopupWindow()
    {
        View root = this.getActivity().getLayoutInflater().inflate( R.layout.third, null );
        final PopupWindow popupWindow = new PopupWindow( root, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT );
        Button openPopupWinBtn = ( Button ) getActivity().findViewById( R.id.fragment1_openwindow_btn2 );

        openPopupWinBtn.setOnClickListener( new OnClickListener()
        {
            @Override
            public void onClick( View v )
            {
                popupWindow.showAsDropDown( v );
                popupWindow.showAtLocation( getActivity().findViewById( R.id.fragment1_openwindow_btn2 ), Gravity.CENTER, 20, 20 );
            }
        } );
        root.findViewById( R.id.button3_2 ).setOnClickListener( new OnClickListener()
        {
            @Override
            public void onClick( View v )
            {
                popupWindow.dismiss();
            }
        } );
    }

    public void initDialog()
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder( getActivity() );

        Button openWindowBtn = ( Button ) getActivity().findViewById( R.id.fragment1_openwindow_btn );

        openWindowBtn.setOnClickListener( new OnClickListener()
        {
            public void onClick( View v )
            {
                builder.setIcon( R.drawable.t );
                builder.setTitle( "this is my first dialog" );
                builder.setMessage( "一个简单的对话框" );
                RelativeLayout relativeLayout = ( RelativeLayout ) getActivity().getLayoutInflater().inflate( R.layout.myrelatlayout, null );
                builder.setView( relativeLayout );
                builder.setPositiveButton( "确定", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick( DialogInterface dialog, int which )
                    {
                        TextView show = ( TextView ) getActivity().findViewById( R.id.fragment1_window_info );
                        show.setText( "用户点击了确定按钮" );
                    }
                } );
                builder.create().show();
            }
        } );
        builder.setNegativeButton( "取消", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick( DialogInterface dialog, int which )
                    {
                        TextView show = ( TextView ) getActivity().findViewById( R.id.fragment1_window_info );
                        show.setText( "用户点击了取消按钮" );

                    }
                }
        );
    }

    @Override
    public void onResume()
    {
        super.onResume();
        Log.d( "Fragment 1", "onResume" );
    }

    @Override
    public void onPause()
    {
        super.onPause();
        Log.d( "Fragment 1", "onPause" );
    }

    @Override
    public void onStop()
    {
        super.onStop();
        Log.d( "Fragment 1", "onStop" );
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        Log.d( "Fragment 1", "onDestroyView" );
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        Log.d( "Fragment 1", "onDestroy" );
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        Log.d( "Fragment 1", "onDetach" );
    }
}
