package com.amar.hello2.fragment;


import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
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
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amar.hello2.R;

import java.util.ArrayList;
import java.util.List;

import fr.tvbarthel.lib.blurdialogfragment.BlurDialogFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DemoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DemoFragment extends BlurDialogFragment implements View.OnClickListener
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

        window.setBackgroundDrawable( new ColorDrawable( Color.TRANSPARENT ) );

        getActivity().getWindowManager().getDefaultDisplay().getMetrics( dm );

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

    public class MyAdapter extends BaseAdapter
    {
        List<Integer> _dataList;
        protected LayoutInflater inflater;
        Context _context;

        public MyAdapter( Context context )
        {
            this.inflater = LayoutInflater.from( context );
            this._dataList = new ArrayList<>();
            this._context = context;
        }

        public void setData( List<Integer> dataList )
        {
            this._dataList = dataList;
            this.notifyDataSetChanged();
        }

        @Override
        public int getCount()
        {
            return this._dataList.size();
        }

        @Override
        public Object getItem( int position )
        {
            return _dataList.get( position );
        }

        @Override
        public long getItemId( int position )
        {
            return 0;
        }

        @Override
        public View getView( int position,View convertView,ViewGroup parent )
        {
            View view = inflater.inflate( R.layout.embe_expandlist_head,null );

            RelativeLayout layout = ( RelativeLayout ) view.findViewById( R.id.root );
            TextView param1 = ( TextView ) view.findViewById( R.id.param1 );
            param1.setText( "position:" + position );
            return view;
        }
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
        FrameLayout layout = ( FrameLayout ) view;
        //layout.setBackground(  );
        //layout.setBackground( getResources().getDrawable( R.color.translucent_background ) );
        layout.setBackgroundResource( R.color.translucent_background );
        //layout.setForeground( null );
        initUI( view );
        return view;
    }

    Button closeBtn;

    public void initUI( View rootView )
    {
        closeBtn = ( Button ) rootView.findViewById( R.id.close );
        closeBtn.setOnClickListener( this );
        ListView list = ( ListView ) rootView.findViewById( R.id.list );
        MyAdapter adapter = new MyAdapter( this.getActivity() );
        list.setAdapter( adapter );
        List<Integer> testData = new ArrayList<>();
        for ( int i = 0 ; i < 20 ; i++ )
        {
            testData.add( i );
        }
        adapter.setData( testData );
    }

    @Override
    public Dialog onCreateDialog( Bundle savedInstanceState )
    {
        setStyle( STYLE_NO_TITLE,R.style.MyDialog );
        Dialog dialog = super.onCreateDialog( savedInstanceState );
        Window window = dialog.getWindow();

        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        window.setFlags( WindowManager.LayoutParams.FLAG_BLUR_BEHIND,WindowManager.LayoutParams.FLAG_BLUR_BEHIND );
        window.setAttributes( lp );

        //Drawable drawable = getResources().getDrawable( R.drawable.dialog_bg );
        //window.setBackgroundDrawable( drawable );

        //dialog.setFeatureDrawableAlpha(  );
        try
        {
            //@android:color/transparent
            //dialog.setFeatureDrawableAlpha(  );

            //WindowManager.LayoutParams wl = dialog.getWindow().getAttributes();
            //wl.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
            //wl.alpha = 0.5f;
            //dialog.getWindow().setAttributes( wl );
            //            WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
            //            lp.alpha = 0;
            //            dialog.getWindow().setAttributes( lp );
            //            lp.dimAmount=0.5f;
            //            dialog.getWindow().setAttributes(lp);

            //            ColorDrawable colorDrawable = new ColorDrawable( Color.TRANSPARENT );
            //            colorDrawable.setAlpha( 128 );
            //            dialog.getWindow().setBackgroundDrawable( colorDrawable );
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
        dialog.getWindow().getAttributes().windowAnimations = R.style.my_dialog;
        return dialog;
    }
}
