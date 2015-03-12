package com.amar.hello2;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.amar.hello2.R;
import com.amar.hello2.exception.MyRetrofitExceptionHandler;
import com.amar.hello2.model.User;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.List;

import retrofit.ErrorHandler;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

//frameworks\base\core\java\android\text\layout.java

@Fullscreen
@EActivity( R.layout.activity_rx )
public class RXActivity extends BaseActivity
{
    @ViewById( resName = "rxInfo" )
    protected EditText rxInfoEdt;

    @ViewById( resName = "rxInfoPath" )
    protected EditText rxInfoPathEdit;

    Layout ss;

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_rx );
    }


    @Override
    public boolean onCreateOptionsMenu( Menu menu )
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate( R.menu.rx,menu );
        return true;
    }

    private interface ApiManagerService
    {
        @GET( "/{file}" )
        Object getWeather( @Path( "file" ) String para );

        /**
         * [{"name":"amar","email":"amar@gmail.com","pw":"123456"},{"name":"sam","email":"sam@gmail.com","pw":"654321"}]
         * @param para
         * @return
         */
        @GET( "/{file}" )
        List<User> getUserList( @Path( "file" ) String para );
    }

    private static final RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint( "http://192.168.56.1/dinner" ).setErrorHandler( new MyRetrofitExceptionHandler() ).build();
    private static final ApiManagerService apiManager = restAdapter.create( ApiManagerService.class );

    @Click( resName = "rxGet" )
    public void rxGetBtn()
    {
        rxInfoEdt.setText( "读取解析中..." );

        //1、使用async
        //new MyAsyncTask().execute(rxInfoPathEdit.getText().toString());

        //2、使用rx库
        useRx( rxInfoPathEdit.getText().toString() );

        //3、使用注解库
        //useAnnotataion(rxInfoPathEdit.getText().toString());
    }

    @Background
    public void useAnnotataion( String info )
    {
        Object result = apiManager.getWeather( info );
        updateUiByAnnotation( result );
    }

    @UiThread
    public void updateUiByAnnotation( Object result )
    {
        rxInfoEdt.setText( result.toString() );

        logI( "Annotation result:" + result.toString() );
    }

    public void useRx( final String path )
    {
        Subscriber<Object> subscriber= new Subscriber<Object>()
        {
            @Override
            public void onCompleted()
            {

            }

            @Override
            public void onError( Throwable e )
            {
                rxInfoEdt.setText( e.getMessage() );
            }

            @Override
            public void onNext( Object o )
            {
                rxInfoEdt.setText( o.toString() );
            }
        };

        Observable.just( path ).flatMap( new Func1<String, Observable<?>>()
        {
            @Override
            public Observable<?> call( String s )
            {
                return RXActivity.getWeatherData( s );
            }
        } ).subscribeOn( Schedulers.io() ).observeOn( AndroidSchedulers.mainThread() ).subscribe( subscriber );
    }

    public static Observable<Object> getWeatherData( final String path )
    {
        return Observable.create( new Observable.OnSubscribe<Object>()
        {
            @Override
            public void call( Subscriber<? super Object> subscriber )
            {
                try
                {
                    RestAdapter.Builder builder = new RestAdapter.Builder().setEndpoint( "http://192.168.56.1/dinner" ).setErrorHandler( new MyRetrofitExceptionHandler() );

                    Gson gson = new GsonBuilder().setFieldNamingPolicy( FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES ).registerTypeAdapter( Date.class,new DateTypeAdapter() ).create();

                    builder.setConverter( new GsonConverter( gson ) );

                    RestAdapter restAdapter2 = builder.build();

                    ApiManagerService apiManager = restAdapter2.create( ApiManagerService.class );

                    List<User> list= apiManager.getUserList( path );

                    StringBuilder sb = new StringBuilder(  );
                    for(User user : list)
                    {
                        sb.append( user.toString() ).append( "\n" );
                    }
                    subscriber.onNext( sb.toString() );

                    subscriber.onCompleted();
                }
                catch ( Exception e )
                {
                    subscriber.onError( e );
                }
            }
        } );//.subscribeOn( Schedulers.io() );
    }


    public class MyAsyncTask extends AsyncTask<String, Integer, Object>
    {

        @Override
        protected Object doInBackground( String... params )
        {
            return apiManager.getWeather( params[ 0 ] );
        }

        @Override
        protected void onProgressUpdate( Integer... progress )
        {
            // Synchronized to UI thread.
            // Update progress bar, Notification, or other UI elements
        }

        @Override
        protected void onPostExecute( Object result )
        {
            rxInfoEdt.setText( result.toString() );

            logI( "AsyncTask result:" + result.toString() );
        }
    }

    private void listing601( String myFeed )
    {
        logI( "到这里还是正常" );
        /**
         * Listing 6-1: Opening an Internet data stream
         */
        //String myFeed = getString(R.string.my_feed);
        try
        {
            URL url = new URL( myFeed );

            // Create a new HTTP URL connection
            URLConnection connection = url.openConnection();
            HttpURLConnection httpConnection = ( HttpURLConnection ) connection;

            int responseCode = httpConnection.getResponseCode();
            if ( responseCode == HttpURLConnection.HTTP_OK )
            {
                InputStream in = httpConnection.getInputStream();
                processStream( in );
            }
        }
        catch ( MalformedURLException e )
        {
            logI( e.getMessage() );
        }
        catch ( IOException e )
        {
            logI( e.getMessage() );
        }
        catch ( Exception e )
        {
            logI( e.getMessage() );
        }
    }

    /**
     * Listing 6-2: Parsing XML using the XML Pull Parser
     */
    private void processStream( InputStream inputStream )
    {
        // Create a new XML Pull Parser.
        XmlPullParserFactory factory;
        try
        {
            factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware( true );
            XmlPullParser xpp = factory.newPullParser();
            // Assign a new input stream.
            xpp.setInput( inputStream,null );
            int eventType = xpp.getEventType();
            // Continue until the end of the document is reached.
            while ( eventType != XmlPullParser.END_DOCUMENT )
            {
                // Check for a start tag of the results tag.
                if ( eventType == XmlPullParser.START_TAG && xpp.getName().equals( "result" ) )
                {
                    eventType = xpp.next();
                    String name = "";
                    // Process each result within the result tag.
                    while ( !( eventType == XmlPullParser.END_TAG && xpp.getName().equals( "result" ) ) )
                    {
                        // Check for the name tag within the results tag.
                        if ( eventType == XmlPullParser.START_TAG && xpp.getName().equals( "name" ) )
                        // Extract the POI name.
                        {
                            name = xpp.nextText();
                        }
                        // Move on to the next tag.
                        eventType = xpp.next();
                    }
                    // Do something with each POI name.
                }
                // Move on to the next result tag.
                eventType = xpp.next();
            }
        }
        catch ( XmlPullParserException e )
        {
            Log.d( "PULLPARSER","XML Pull Parser Exception",e );
        }
        catch ( IOException e )
        {
            Log.d( "PULLPARSER","IO Exception",e );
        }
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
