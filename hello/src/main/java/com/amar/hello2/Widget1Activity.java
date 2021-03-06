package com.amar.hello2;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.amar.hello2.fragment.ExampleDialogFragment;
import com.amar.hello2.fragment.TipDialog;

public class Widget1Activity extends BaseActivity
{

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_widget1 );
    }

    public void gotoGreenDaoExample(View view)
    {
        Intent intent = new Intent(this,GreenDaoActivity.class);
        startActivity(intent);
    }
    public void getdialogExample(View view)
    {
        ExampleDialogFragment exampleDialogFragment = new ExampleDialogFragment();
        FragmentTransaction ft = this.getFragmentManager().beginTransaction();
        ft.setTransition( FragmentTransaction.TRANSIT_FRAGMENT_FADE );
        exampleDialogFragment.show( ft,"cf" );
    }
    public void getOnedialog(View view)
    {
        TipDialog cf = new TipDialog();
        FragmentTransaction ft = this.getFragmentManager().beginTransaction();
        ft.setTransition( FragmentTransaction.TRANSIT_FRAGMENT_FADE );
        cf.show( ft,"cf" );
    }
    public void gotoExpandListView5(View view)
    {
        Intent intent = new Intent( this, ExpandList5Activity_.class );
        startActivity( intent );
    }
    public void gotoExpandListView4(View view)
    {
        Intent intent = new Intent( this, ExpandList4Activity_.class );
        startActivity( intent );
    }

    public void gotoExpandListView3(View view)
    {
        Intent intent = new Intent( this, ExpandList3Activity_.class );
        startActivity( intent );
    }

    public void gotoExpandListView2(View view)
    {
        Intent intent = new Intent( this, ExpandableListView2Activity_.class );
        startActivity( intent );
    }

    public void gotoExpandListView( View view )
    {
        Intent intent = new Intent( this, EmbedExpandListViewActivity_.class );
        startActivity( intent );
    }
    public void gotoBlur( View view )
    {
        Intent intent = new Intent( this, BlurActivity_.class );
        startActivity( intent );
    }

    public void gotoImageList( View view )
    {
        Intent intent = new Intent( this,ListImageActivity_.class );
        startActivity( intent );
    }

    public void gotoMasterDetail( View view )
    {


    }

    public void gotoShare( View view )
    {
        Intent intent = new Intent( this,ShareAppActivity.class );
        startActivity( intent );
    }

    public void gotoDrawer( View view )
    {
        Intent intent = new Intent( this,DrawerActivity.class );
        startActivity( intent );
    }

    public void gotoService( View view )
    {
        Intent intent = new Intent( this,ServiceActivity_.class );
        startActivity( intent );
    }

    public void gotoRadioSrcollView( View view )
    {
        Intent intent = new Intent( this,RadioScrollActivity.class );
        startActivity( intent );
    }

    public void gotoHSrcollView( View view )
    {
        Intent intent = new Intent( this,HScrollTitleActivity.class );
        startActivity( intent );
    }

    public void gotoPageView( View view )
    {
        Intent intent = new Intent( this,WeiBoActivity.class );
        startActivity( intent );
    }

    public void gotoSubPageView( View view )
    {
        Intent intent = new Intent( this,SubPageView_.class );
        startActivity( intent );
    }

    public void gotoRxView( View view )
    {
        Intent intent = new Intent( this,RXActivity_.class );
        startActivity( intent );
    }

    public void gotoPageDragDropView( View view )
    {
        Intent intent = new Intent( this,ca.laplanete.mobile.example.MainActivity.class );
        startActivity( intent );
    }

    public void gotoAntotationView( View view )
    {
        Intent intent = new Intent( this,Annotation1Activity_.class );
        startActivity( intent );
    }

    public void onclickListview1( View view )
    {
        Intent intent = new Intent( this,ScrollListView.class );
        startActivity( intent );
    }

    public void onclickListview2( View view )
    {
        Intent intent = new Intent( this,TabHostDemoActivity.class );
        startActivity( intent );
    }

    public void onclickListview3( View view )
    {
        Intent intent = new Intent( this,ExpandableListViewActivity.class );
        startActivity( intent );
    }

    public void onclickInternetDown( View view )
    {
        Intent intent = new Intent( this,Internet1Activity.class );
        startActivity( intent );
    }

    public void onclickSubWiget1( View view )
    {
        Intent intent = new Intent( this,SubWidget1Activity.class );
        startActivity( intent );
    }

    public void onclickSubWiget3( View view )
    {
        Intent intent = new Intent( this,SubWidget3Activity.class );
        startActivity( intent );
    }

    public void onclickSubWiget2( View view )
    {
        Intent intent = new Intent( this,SubWidget2Activity.class );
        startActivity( intent );
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu )
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate( R.menu.widget1,menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item )
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if ( id == R.id.action_settings )
        {
            return true;
        }
        return super.onOptionsItemSelected( item );
    }
}
