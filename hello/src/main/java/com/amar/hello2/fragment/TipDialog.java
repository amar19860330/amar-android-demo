package com.amar.hello2.fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        setStyle( STYLE_NO_TITLE, 0 );

        return super.onCreateDialog( savedInstanceState );
    }
}
