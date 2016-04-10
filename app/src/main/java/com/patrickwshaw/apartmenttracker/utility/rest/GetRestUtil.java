package com.patrickwshaw.apartmenttracker.utility.rest;

import android.os.AsyncTask;

import com.patrickwshaw.apartmenttracker.utility.LoggingUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Patrick on 4/16/2015.
 */
public class GetRestUtil extends AsyncTask<String, Void, String>
{

    private static final LoggingUtil logger = new LoggingUtil("GetRestUtil", "GetRestUtil");

    @Override
    protected String doInBackground(String... urls)
    {
        logger.logEnter("doInBackground");
        try
        {
            return downloadUrl(urls[0]);
        }
        catch(IOException ioe)
        {
            logger.e("IOException thrown trying to read url: " + urls[0] + " message was:\n" + ioe.getMessage());
            logger.logExit();
            return null;
        }
    }

    protected String downloadUrl(String urlToDownload) throws IOException
    {
        logger.logEnter("downloadUrl");
        InputStream is = null;
        // Only display the first 500 characters of the retrieved
        // web page content.
        int len = 50000;

        try
        {
            logger.d("Creating a new URL for: " + urlToDownload);
            URL url = new URL(urlToDownload);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);

            // Starts the query

            logger.d("About to start the connection");
            conn.connect();
            int response = conn.getResponseCode();

            logger.d("The response code is: " + response);
            is = conn.getInputStream();

            // Convert the InputStream into a string
            String contentAsString = readIt(is, len);
            logger.logExit();
            return contentAsString;

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        }
        finally
        {
            if (is != null)
            {
                is.close();
            }
        }
    }

    public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException
    {
        logger.logEnter("readIt");
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");

        char[] buffer = new char[len];
        reader.read(buffer);

        String returnVal = new String(buffer).trim();

        logger.d("Got the response string: " + returnVal);

        logger.logExit();
        return returnVal;
    }

    @Override
    protected void onPostExecute(String result)
    {
        //default onPosExecute, just logs the response
        logger.logInnerClassEnter("GetNextUserIdTask", "onPostExecute");

        logger.d("Got the result: " + result);

        logger.logInnerClassExit("GetNextUserIdTask", "onPostExecute");
    }


}
