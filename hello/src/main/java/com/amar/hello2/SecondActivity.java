package com.amar.hello2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SecondActivity extends BaseActivity
{

	public static final int RESULT_CODE = 502;

	public static final String RESULT_CONTENT = "content";

	private Button btn2_1 = null;

	private EditText editText2_1 = null;

	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );

		setContentView( R.layout.second );

		Intent oldIntent = getIntent();

		Bundle bundle = oldIntent.getExtras();

		btn2_1 = ( Button ) this.findViewById( R.id.button2_1 );

		editText2_1 = ( EditText ) this.findViewById( R.id.editText2_1 );

		editText2_1.setText( "" + bundle.getString( "username" ) + ":" + bundle.getString( "number" ) );

		btn2_1.setOnClickListener( onClicklistener );
	}

	private OnClickListener onClicklistener = new OnClickListener()
	{
		public void onClick( View v )
		{
			switch ( v.getId() )
			{
			case R.id.button2_1:
				Intent goToIntent = new Intent();
				goToIntent.setClass( SecondActivity.this , MainActivity.class );
				// startActivity( goToIntent );

				// 有返回值的处理
				goToIntent.putExtra( RESULT_CONTENT , editText2_1.getText().toString() );
				setResult( RESULT_CODE , goToIntent );
                finish(); // 返回上级activity
				break;
			}
		}
	};

	public int add( int a , int b )
	{
		return a + b;
	}
}
