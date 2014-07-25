package com.amar.hello2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ThirdActivity extends BaseActivity
{

	public static final int RESULT_CODE = 503;

	public static final String RESULT_CONTENT = "content";

	private Button btn3_1 = null;

	private Button btn3_2 = null;

	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );

		setContentView( R.layout.third );

		Intent oldIntent = getIntent();

		Bundle bundle = oldIntent.getExtras();

		btn3_1 = ( Button ) this.findViewById( R.id.button3_1 );

		btn3_2 = ( Button ) this.findViewById( R.id.button3_2 );

		btn3_1.setOnClickListener( onClicklistener );

		btn3_2.setOnClickListener( onClicklistener );
	}

	private OnClickListener onClicklistener = new OnClickListener()
	{
		public void onClick( View v )
		{
			switch ( v.getId() )
			{
			case R.id.button3_1:
				break;
			case R.id.button3_2:
				finish();
				break;
			}
		}
	};
}
