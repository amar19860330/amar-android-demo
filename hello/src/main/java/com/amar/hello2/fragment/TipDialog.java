package com.amar.hello2.fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
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
        WindowManager.LayoutParams lp = rootDialog.getWindow().getAttributes();
        rootDialog.getWindow().setAttributes( lp );
        rootDialog.getWindow().setGravity( Gravity.BOTTOM );

        return rootDialog;
    }
}
