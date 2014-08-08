package com.amar.hello2;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.io.File;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.DownloadManager.Query;
import android.app.DownloadManager.Request;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class Internet1Activity extends BaseActivity
{

    private static final String TAG = "Chapter6_Internet";
    public static String APK_NAME = "hello-main2-debug.apk";

    private void listing601()
    {
        /**
         * Listing 6-1: Opening an Internet data stream
         */
        String myFeed = getString( R.string.my_feed );
        logI( "到这里还是正常" );
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
            Log.d( TAG, "Malformed URL Exception.", e );
        }
        catch ( IOException e )
        {
            Log.d( TAG, "IO Exception.", e );
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
            xpp.setInput( inputStream, null );
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
            Log.d( "PULLPARSER", "XML Pull Parser Exception", e );
        }
        catch ( IOException e )
        {
            Log.d( "PULLPARSER", "IO Exception", e );
        }
    }

    private void listing603( String url )
    {
        /**
         * Listing 6-3: Downloading files using the Download Manager
         */
        String serviceString = Context.DOWNLOAD_SERVICE;
        DownloadManager downloadManager = ( DownloadManager ) getSystemService( serviceString );

        DownloadManager.Request request = new Request( Uri.parse( url ) );
        long reference = downloadManager.enqueue( request );

        myDownloadReference = reference;
        Log.d( TAG, "Download Reference: " + reference );
    }

    private void listing603()
    {
        /**
         * Listing 6-3: Downloading files using the Download Manager
         */
        String serviceString = Context.DOWNLOAD_SERVICE;
        DownloadManager downloadManager = ( DownloadManager ) getSystemService( serviceString );

        Uri uri = Uri.parse( getString( R.string.my_feed2 ) );
        DownloadManager.Request request = new Request( uri );

        long reference = downloadManager.enqueue( request );

        //
        myDownloadReference = reference;
        Log.d( TAG, "Download Reference: " + reference );
    }

    private void listing604()
    {
        final DownloadManager downloadManager = ( DownloadManager ) getSystemService( Context.DOWNLOAD_SERVICE );

        /**
         * Listing 6-4: Monitoring downloads for completion
         */
        IntentFilter filter = new IntentFilter( DownloadManager.ACTION_DOWNLOAD_COMPLETE );

        BroadcastReceiver receiver = new BroadcastReceiver()
        {
            @Override
            public void onReceive( Context context, Intent intent )
            {
                long reference = intent.getLongExtra( DownloadManager.EXTRA_DOWNLOAD_ID, -1 );
                if ( myDownloadReference == reference )
                {
                    Query myDownloadQuery = new Query();
                    myDownloadQuery.setFilterById( reference );

                    Cursor myDownload = downloadManager.query( myDownloadQuery );
                    if ( myDownload.moveToFirst() )
                    {
                        int fileNameIdx = myDownload.getColumnIndex( DownloadManager.COLUMN_LOCAL_FILENAME );
                        int fileUriIdx = myDownload.getColumnIndex( DownloadManager.COLUMN_LOCAL_URI );

                        String fileName = myDownload.getString( fileNameIdx );
                        String fileUri = myDownload.getString( fileUriIdx );

                        // TODO Do something with the file.
                        Log.d( TAG, fileName + " : " + fileUri );
                        Intent installIntent = new Intent( Intent.ACTION_VIEW );
                        installIntent.setDataAndType( Uri.fromFile( new File( fileName ) ), "application/vnd.android.package-archive" );
                        startActivity( installIntent );
                    }
                    myDownload.close();

                }
            }
        };

        registerReceiver( receiver, filter );
    }

    private void listing605()
    {
        IntentFilter filter = new IntentFilter( DownloadManager.ACTION_NOTIFICATION_CLICKED );

        BroadcastReceiver receiver = new BroadcastReceiver()
        {
            @Override
            public void onReceive( Context context, Intent intent )
            {
                String extraID = DownloadManager.EXTRA_NOTIFICATION_CLICK_DOWNLOAD_IDS;
                long[] references = intent.getLongArrayExtra( extraID );
                for ( long reference : references )
                {
                    if ( reference == myDownloadReference )
                    {
                        // Do something with downloading file.
                    }
                }
            }
        };

        registerReceiver( receiver, filter );
    }

    void update()
    {
        Intent intent = new Intent( Intent.ACTION_VIEW );
        intent.setDataAndType( Uri.fromFile( new File( Environment.getExternalStorageDirectory(), APK_NAME ) ), "application/vnd.android.package-archive" );
        startActivity( intent );
    }

    private void listing607()
    {
        /**
         * Listing 6-7: Finding details of paused downloads
         */
        // Obtain the Download Manager Service.
        String serviceString = Context.DOWNLOAD_SERVICE;
        DownloadManager downloadManager;
        downloadManager = ( DownloadManager ) getSystemService( serviceString );

        // Create a query for paused downloads.
        Query pausedDownloadQuery = new Query();
        pausedDownloadQuery.setFilterByStatus( DownloadManager.STATUS_PAUSED );

        // Query the Download Manager for paused downloads.
        Cursor pausedDownloads = downloadManager.query( pausedDownloadQuery );

        // Find the column indexes for the data we require.
        int reasonIdx = pausedDownloads.getColumnIndex( DownloadManager.COLUMN_REASON );
        int titleIdx = pausedDownloads.getColumnIndex( DownloadManager.COLUMN_TITLE );
        int fileSizeIdx = pausedDownloads.getColumnIndex( DownloadManager.COLUMN_TOTAL_SIZE_BYTES );
        int bytesDLIdx = pausedDownloads.getColumnIndex( DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR );

        // Iterate over the result Cursor.
        while ( pausedDownloads.moveToNext() )
        {
            // Extract the data we require from the Cursor.
            String title = pausedDownloads.getString( titleIdx );
            int fileSize = pausedDownloads.getInt( fileSizeIdx );
            int bytesDL = pausedDownloads.getInt( bytesDLIdx );

            // Translate the pause reason to friendly text.
            int reason = pausedDownloads.getInt( reasonIdx );
            String reasonString = "Unknown";
            switch ( reason )
            {
                case DownloadManager.PAUSED_QUEUED_FOR_WIFI:
                    reasonString = "Waiting for WiFi";
                    break;
                case DownloadManager.PAUSED_WAITING_FOR_NETWORK:
                    reasonString = "Waiting for connectivity";
                    break;
                case DownloadManager.PAUSED_WAITING_TO_RETRY:
                    reasonString = "Waiting to retry";
                    break;
                default:
                    break;
            }

            // Construct a status summary
            StringBuilder sb = new StringBuilder();
            sb.append( title ).append( "\n" );
            sb.append( reasonString ).append( "\n" );
            sb.append( "Downloaded " ).append( bytesDL ).append( " / " ).append( fileSize );

            // Display the status
            Log.d( "DOWNLOAD", sb.toString() );
        }

        // Close the result Cursor.
        pausedDownloads.close();
    }

    private long myDownloadReference;

    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_internet1 );

        Button buttonOpen = ( Button ) findViewById( R.id.internet_button1 );
        buttonOpen.setOnClickListener( new OnClickListener()
        {
            public void onClick( View v )
            {
                new MyAsyncTask().execute();
            }
        } );

        Button buttonDownload = ( Button ) findViewById( R.id.internet_button2 );
        buttonDownload.setOnClickListener( new OnClickListener()
        {
            public void onClick( View v )
            {
                new MyAsyncTask2().execute();
            }
        } );

        Button buttonPaused = ( Button ) findViewById( R.id.internet_button3 );
        buttonPaused.setOnClickListener( new OnClickListener()
        {
            public void onClick( View v )
            {
                new MyAsyncTask3().execute();
            }
        } );

        Button downloadAPKBtn = ( Button ) findViewById( R.id.internet_button4 );
        downloadAPKBtn.setOnClickListener( new OnClickListener()
        {
            public void onClick( View v )
            {
                new MyDownloadApkAsyncTask().execute( "http://192.168.1.132:8080/myweb/" + APK_NAME );
            }
        } );
    }

    public class MyDownloadApkAsyncTask extends AsyncTask< String, Integer, String >
    {
        @Override
        protected String doInBackground( String... params )
        {
            listing605();
            listing604();
            listing603( params[ 0 ] );

            publishProgress( 1 );
            return null;
        }

        @Override
        protected void onProgressUpdate( Integer... progress )
        {
            // Synchronized to UI thread.
            // Update progress bar, Notification, or other UI elements
        }

        @Override
        protected void onPostExecute( String result )
        {
            // Synchronized to UI thread.
            // Report results via UI update, Dialog, or notifications
        }
    }


    public class MyAsyncTask extends AsyncTask< Void, Integer, String >
    {

        @Override
        protected String doInBackground( Void... params )
        {
            listing601();
            publishProgress( 1 );
            return null;
        }

        @Override
        protected void onProgressUpdate( Integer... progress )
        {
            // Synchronized to UI thread.
            // Update progress bar, Notification, or other UI elements
        }

        @Override
        protected void onPostExecute( String result )
        {
            // Synchronized to UI thread.
            // Report results via UI update, Dialog, or notifications
        }
    }

    public class MyAsyncTask2 extends AsyncTask< Void, Integer, String >
    {

        @Override
        protected String doInBackground( Void... params )
        {
            listing605();
            listing604();
            listing603();
            return null;
        }

    }

    public class MyAsyncTask3 extends AsyncTask< Void, Integer, String >
    {

        @Override
        protected String doInBackground( Void... params )
        {
            listing607();
            return null;
        }

    }
}
