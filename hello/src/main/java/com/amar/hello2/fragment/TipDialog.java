package com.amar.hello2.fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.amar.hello2.R;

/**
 * Created by SAM on 2014/9/3.
 */
public class TipDialog extends DialogFragment
{
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState )
    {
        View view = inflater.inflate( R.layout.tip_dialog, container, false );

        return view;
    }

    @Override
    public Dialog onCreateDialog( Bundle savedInstanceState )
    {
        setStyle( STYLE_NO_TITLE,R.style.MyDialog );
        Dialog rootDialog = super.onCreateDialog( savedInstanceState );

        rootDialog.getWindow().getAttributes().windowAnimations = R.style.my_dialog;
        rootDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        rootDialog.getWindow().setGravity( Gravity.CENTER_VERTICAL );

        return rootDialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics( dm );
        int width = dpToPx(300,this.getActivity());
        getDialog().getWindow().setLayout( width, getDialog().getWindow().getAttributes().height );
    }

    public int dpToPx(int dp,Context context)
    {
        float scale =context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public int pxToDp(float pxValue,Context context)
    {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
