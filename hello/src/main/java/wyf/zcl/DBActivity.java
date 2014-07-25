package wyf.zcl;

import wyf.zcl.sqlitedb.SqLiteDBHelper; // 引入相关包
import android.app.Activity; // 引入相关包
import android.content.ContentValues; // 引入相关包
import android.database.Cursor; // 引入相关包
import android.database.sqlite.SQLiteDatabase; // 引入相关包
import android.os.Bundle; // 引入相关包
import android.view.View; // 引入相关包
import android.widget.Button; // 引入相关包
import android.widget.Toast;

public class DBActivity extends Activity
{
	/** Called when the activity is first created. */
	private Button createButton; // 创建数据库按钮

	private Button insertBut; // 增加数据库记录按钮

	private Button updateBut; // 更新数据库记录按钮

	private Button queryBut; // 查询数据库记录按钮

	@Override
	public void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );


	}
}
