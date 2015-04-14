package com.amar.hello2;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.amar.android.db.DaoMaster;
import com.amar.android.db.DaoSession;
import com.amar.android.db.Idiom;
import com.amar.android.db.IdiomDao;
import com.amar.hello2.fast.FClickById;
import com.amar.hello2.fast.Fast;

import java.util.List;


public class GreenDaoActivity extends Activity {

    private SQLiteDatabase sqLiteDatabase;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private IdiomDao idiomDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao);
        new Fast<GreenDaoActivity>().scanInActivity(this);

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "notes-db", null);
        sqLiteDatabase = helper.getWritableDatabase();
        daoMaster = new DaoMaster(sqLiteDatabase);
        daoSession = daoMaster.newSession();
        idiomDao = daoSession.getIdiomDao();
    }

    @FClickById(R.id.greenDaoTest1)
    void clickGreenDaoTest1() {
        for (long i = 0; i < 10; i++) {
            Idiom idiom = new Idiom(i, "a", "b", "c", "d", "e", "f");
            idiomDao.insert(idiom);
        }

    }

    @FClickById(R.id.greenDaoTest2)
    void clickGreenDaoTest2() {
        List<Idiom> list = idiomDao.queryRaw("");
        Log.d("size:", list.size() + "");
    }

}
