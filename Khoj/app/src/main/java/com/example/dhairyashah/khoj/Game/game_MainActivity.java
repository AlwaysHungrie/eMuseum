package com.example.dhairyashah.khoj.Game;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.dhairyashah.khoj.Game.Download_Pics.DecompressZip;
import com.example.dhairyashah.khoj.Game.Download_Pics.DownloadFile;
import com.example.dhairyashah.khoj.Game.Download_Pics.ExternalStorage;
import com.example.dhairyashah.khoj.R;

import java.io.File;

public class game_MainActivity extends AppCompatActivity {

    //////////////////////////////////////////////////////////////////////////
    // State
    //////////////////////////////////////////////////////////////////////////

    protected ProgressDialog mProgressDialog;

    //////////////////////////////////////////////////////////////////////////
    // Activity Lifecycle
    //////////////////////////////////////////////////////////////////////////

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView( R.layout.game_activity_main );

        // Keep the screen (and device) active as long as this app is frontmost.
        // This is to avoid going to sleep during the download.
        // http://stackoverflow.com/questions/4376902/difference-between-wakelock-and-flag-keep-screen-on

        //(Window.FEATURE_NO_TITLE);
        getWindow().addFlags( WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON );
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        startDownload();
    }

    //////////////////////////////////////////////////////////////////////////
    // Event handlers
    //////////////////////////////////////////////////////////////////////////

    /**
     * Invoked when user presses "Start download" button.
     */
    public void startDownload(  ) {
        //String url = ((TextView) findViewById(R.id.url_field)).getText().toString();
        String url = "http://dhairyashah.000webhostapp.com/image-assets.zip";
        new DownloadTask().execute( url );
    }

    //////////////////////////////////////////////////////////////////////////
    // Background Task
    //////////////////////////////////////////////////////////////////////////

    /**
     * Background task to download and unpack .zip file in background.
     */
    private class DownloadTask extends AsyncTask<String,Void,Exception> {

        @Override
        protected void onPreExecute() {
            //showProgress();
        }

        @Override
        protected Exception doInBackground(String... params) {
            String url = (String) params[0];

            try {
                //downloadAllAssets(url);
            } catch ( Exception e ) { return e; }


            return null;
        }

        @Override
        protected void onPostExecute(Exception result) {
            //dismissProgress();
            new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

                @Override
                public void run() {
                    // This method will be executed once the timer is over
                    // Start your app main activity
                    Intent intent = new Intent(game_MainActivity.this,LoginActivity.class);
                    startActivity(intent);

                    // close this activity
                    finish();
                }
            }, 3000);
            if ( result == null ) { return; }
            // something went wrong, post a message to user - you could use a dialog here or whatever
            Toast.makeText(game_MainActivity.this, result.getLocalizedMessage(), Toast.LENGTH_LONG ).show();

        }
    }

    //////////////////////////////////////////////////////////////////////////
    // Progress Dialog
    //////////////////////////////////////////////////////////////////////////

    protected void showProgress( ) {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle( R.string.progress_title );
        mProgressDialog.setMessage( getString(R.string.progress_detail) );
        mProgressDialog.setIndeterminate( true );
        mProgressDialog.setCancelable( false );
        mProgressDialog.show();
    }

    protected void dismissProgress() {
        // You can't be too careful.
        if (mProgressDialog != null && mProgressDialog.isShowing() && mProgressDialog.getWindow() != null) {
            try {
                mProgressDialog.dismiss();
            } catch ( IllegalArgumentException ignore ) { ; }
        }
        mProgressDialog = null;
    }

    //////////////////////////////////////////////////////////////////////////
    // File Download
    //////////////////////////////////////////////////////////////////////////

    /**
     * Download .zip file specified by url, then unzip it to a folder in external storage.
     *
     * @param url
     */
    private void downloadAllAssets( String url ) {
        // Temp folder for holding asset during download
        File zipDir =  ExternalStorage.getSDCacheDir(this, "tmp");
        // File path to store .zip file before unzipping
        File zipFile = new File( zipDir.getPath() + "/temp.zip" );


        // Folder to hold unzipped output
        File outputDir = ExternalStorage.getSDCacheDir(this, "unzipped");

        try {
            if(!zipFile.exists()) {

                DownloadFile.download(url, zipFile, zipDir);
                unzipFile(zipFile, outputDir);
            }
        } finally {
            //zipFile.delete();
        }

    }

    //////////////////////////////////////////////////////////////////////////
    // Zip Extraction
    //////////////////////////////////////////////////////////////////////////

    /**
     * Unpack .zip file.
     *
     * @param zipFile
     * @param destination
     */
    protected void unzipFile( File zipFile, File destination ) {
        DecompressZip decomp = new DecompressZip( zipFile.getPath(),
                destination.getPath() + File.separator );
        decomp.unzip();
    }

}