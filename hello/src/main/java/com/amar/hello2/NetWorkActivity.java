package com.amar.hello2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class NetWorkActivity extends BaseActivity
{

	private Button closeWifiBtn = null;

	private Button openWifiBtn = null;

	private Button gotoWirelessSetBtn = null;

	private Button getNetWorkStateBtn = null;

	private EditText wifiStateTxt = null;

	private EditText mobileStateTxt = null;

	private EditText signalIntensityTxt = null;

	private EditText wireOtherInfoTxt = null;

	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );

		setContentView( R.layout.networkstate );

		initUI();

		uiFunction();
	}

	public void initUI()
	{
		closeWifiBtn = ( Button ) this.findViewById( R.id.closeWifiBtn );

		openWifiBtn = ( Button ) this.findViewById( R.id.openWifiBtn );

		gotoWirelessSetBtn = ( Button ) this.findViewById( R.id.gotoWirelessSetBtn );

		getNetWorkStateBtn = ( Button ) this.findViewById( R.id.getNetWorkStateBtn );

		wifiStateTxt = ( EditText ) this.findViewById( R.id.wifiStateTxt );

		mobileStateTxt = ( EditText ) this.findViewById( R.id.mobileStateTxt );

		signalIntensityTxt = ( EditText ) this.findViewById( R.id.signalIntensityTxt );

		wireOtherInfoTxt = ( EditText ) this.findViewById( R.id.wireOtherInfoTxt );
	}

	public void uiFunction()
	{
		closeWifiBtn.setOnClickListener( onClicklistener );

		openWifiBtn.setOnClickListener( onClicklistener );

		gotoWirelessSetBtn.setOnClickListener( onClicklistener );

		getNetWorkStateBtn.setOnClickListener( onClicklistener );
	}

	private OnClickListener onClicklistener = new OnClickListener()
	{
		public void onClick( View v )
		{
			WifiManager mWifiManager = ( WifiManager ) getSystemService( Context.WIFI_SERVICE );

			switch ( v.getId() )
			{
			case R.id.closeWifiBtn:
				if ( mWifiManager.isWifiEnabled() )
				{
					mWifiManager.setWifiEnabled( false );
				}
				break;
			case R.id.openWifiBtn:
				if ( ! mWifiManager.isWifiEnabled() )
				{
					mWifiManager.setWifiEnabled( true );
				}
				break;
			case R.id.gotoWirelessSetBtn:
				startActivity( new Intent( android.provider.Settings.ACTION_WIRELESS_SETTINGS ) );
				break;
			case R.id.getNetWorkStateBtn:
				getNetWorkState();
				break;
			}
		}
	};

	private void getNetWorkState()
	{
		ConnectivityManager connectivityManager = ( ConnectivityManager ) getSystemService( Context.CONNECTIVITY_SERVICE );
		
		State mobile = connectivityManager.getNetworkInfo( ConnectivityManager.TYPE_MOBILE ).getState();
		
		State wifi = connectivityManager.getNetworkInfo( ConnectivityManager.TYPE_WIFI ).getState();

		mobileStateTxt.setText("mobile:"+ mobile.toString() );
		wifiStateTxt.setText( "wifi:"+wifi.toString() );

		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

		StringBuilder sb = new StringBuilder();
		
		boolean available = networkInfo.isAvailable();

		String typeName = networkInfo.getTypeName();

		sb.append( "available:" ).append( available );
		sb.append( ",typeName:" ).append( typeName );

		wireOtherInfoTxt.setText( sb.toString() );
	
		WifiManager mWifiManager = ( WifiManager ) getSystemService( Context.WIFI_SERVICE );
		WifiInfo wifiInfo = mWifiManager.getConnectionInfo();

		signalIntensityTxt.setText( wifiInfo.getSSID() + ":" + wifiInfo.getLinkSpeed() + "/" + wifiInfo.getRssi() );
		
		closeWifiBtn.setEnabled( true );
		
		openWifiBtn.setEnabled( true );
		
	}
}
