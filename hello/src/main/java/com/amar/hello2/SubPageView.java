package com.amar.hello2;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amar.hello2.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EActivity(R.layout.activity_sub_page_view)
public class SubPageView extends Activity
{
    @ViewById(resName = "subPageBtn1")
    protected Button subPageBtn1;

    @ViewById(resName = "subPageBtn2")
    protected Button subPageBtn2;

    @ViewById(resName = "subPageBtn3")
    protected Button subPageBtn3;

    @ViewById(resName = "subPageBtn4")
    protected Button subPageBtn4;

    @ViewById(resName = "subPageItem1")
    protected LinearLayout subPageItem1;

    @ViewById(resName = "subPageItem2")
    protected LinearLayout subPageItem2;

    @ViewById(resName = "subPageItem3")
    protected LinearLayout subPageItem3;

    @ViewById(resName = "subPageItem4")
    protected LinearLayout subPageItem4;

    @ViewById(resName = "subPageContainer")
    protected RelativeLayout subPageContainer;

    public List<View> pageItemList = new ArrayList<View>();

    public List<Button> pageBtnList = new ArrayList<Button>();

    @AfterViews
    public void afterViews()
    {
        if( pageItemList.size()==0)
        {
            pageItemList.add(subPageItem1);
            pageItemList.add(subPageItem2);
            pageItemList.add(subPageItem3);
            pageItemList.add(subPageItem4);

            pageBtnList.add(subPageBtn1);
            pageBtnList.add(subPageBtn2);
            pageBtnList.add(subPageBtn3);
            pageBtnList.add(subPageBtn4);
        }
    }


    @Click({R.id.subPageBtn1, R.id.subPageBtn2, R.id.subPageBtn3, R.id.subPageBtn4})
    void clickChangePage(View view)
    {
        Button btn = (Button) view;
        int index = findIndex(btn);
        changeView(index);
    }

    public int findIndex(Button findBtn)
    {
        int index = -1;
        for (int j = 0; j < pageBtnList.size(); j++)
        {
            if (findBtn.getId() == pageBtnList.get(j).getId())
            {
                index = j;
                break;
            }
        }
        return index;
    }

    public void changeView(int i)
    {
        for (int j = 0; j < pageItemList.size(); j++)
        {
            View layout = subPageContainer.findViewById(pageItemList.get(j).getId());

            if (i == j)
            {
                layout.setVisibility(View.VISIBLE);
            }
            else
            {
                layout.setVisibility(View.GONE);
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_page_view);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sub_page_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings)
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
