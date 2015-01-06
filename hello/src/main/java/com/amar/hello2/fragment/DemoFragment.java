package com.amar.hello2.fragment;


import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.app.Fragment;
import android.transition.Explode;
import android.transition.Transition;
import android.transition.TransitionSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.amar.hello2.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DemoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DemoFragment extends DialogFragment implements View.OnClickListener
{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DemoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DemoFragment newInstance( String param1,String param2 )
    {
        DemoFragment fragment = new DemoFragment();
        Bundle args = new Bundle();
        args.putString( ARG_PARAM1,param1 );
        args.putString( ARG_PARAM2,param2 );
        fragment.setArguments( args );
        return fragment;
    }

    public DemoFragment()
    {
        // Required empty public constructor
    }

    @Override
    public void onStart()
    {
        super.onStart();
        DisplayMetrics dm = new DisplayMetrics();
        Window window = getDialog().getWindow();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics( dm );
        //window.setLayout( dm.widthPixels, getDialog().getWindow().getAttributes().height );
        //window.setGravity( Gravity.BOTTOM );

        window.setBackgroundDrawableResource( R.drawable.bg_dialog );
    }

    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        if ( getArguments() != null )
        {
            mParam1 = getArguments().getString( ARG_PARAM1 );
            mParam2 = getArguments().getString( ARG_PARAM2 );
        }
        //getDialog().getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
    }

    Button closeBtn;

    public void initUI( View rootView )
    {
        closeBtn = ( Button ) rootView.findViewById( R.id.close );
        closeBtn.setOnClickListener( this );
    }

    @Override
    public void onClick( View v )
    {
        if ( closeBtn != null && v.getId() == closeBtn.getId() )
        {
            this.dismiss();
        }
    }

    @Override
    public View onCreateView( LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState )
    {
        View view = inflater.inflate( R.layout.fragment_demo,container,false );
        initUI( view );
        return view;
    }

    @Override
    public Dialog onCreateDialog( Bundle savedInstanceState )
    {
        setStyle( STYLE_NO_TITLE,0 );
        Dialog dialog = super.onCreateDialog( savedInstanceState );
        dialog.getWindow().getAttributes().windowAnimations = R.style.my_dialog;
        return dialog;
    }
}
