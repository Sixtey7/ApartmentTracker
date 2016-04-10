package com.patrickwshaw.apartmenttracker.utility.rest;

import android.os.AsyncTask;

import com.patrickwshaw.apartmenttracker.utility.LoggingUtil;


import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Patrick on 4/23/2015.
 */
public class PostRestUtil extends AsyncTask<String, Void, String>
{
    private static final LoggingUtil logger = new LoggingUtil("PostRestUtil", "PostRestUtil");

    private String dataToSend;

    public PostRestUtil()
    {
        super();
    }

    /**
     * Constructor that takes in the data to send
     * (allows the user to just pass in that data up front, rather than having to pass it in later)
     * @param dataToSend
     */
    public PostRestUtil(String dataToSend)
    {
        super();
        this.dataToSend = dataToSend;
    }

    public void setDataToSend(String dataToSend)
    {
        this.dataToSend = dataToSend;
    }

    @Override
    protected String doInBackground(String... urls)
    {
        logger.logEnter("doInBackground");
        try
        {
            return postToUrl(urls[0]);
        }
        catch(IOException ioe)
        {
            logger.e("IOException thrown trying to read url: " + urls[0] + " message was:\n" + ioe.getMessage());
            logger.logExit();
            return null;
        }
    }

    protected String postToUrl(String urlToPostTo) throws IOException
    {
        logger.logEnter("postToUrl");
        logger.d("Posting to url: " + urlToPostTo);
        String response = "";
        try
        {
            /**
             * Create the Request
             */
            URL url = new URL(urlToPostTo);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* millseconds */);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type",
                    "application/json");
            conn.setChunkedStreamingMode(0);
            conn.setDoInput(true);
            conn.setDoOutput(true);

            OutputStream out = new BufferedOutputStream(conn.getOutputStream());

            logger.d("Sending the message:\n" + dataToSend);

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
            writer.write(dataToSend);
            writer.flush();
            writer.close();
            out.close();

            int responseCode = conn.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK)
            {
                logger.d("Response was HTTP OK - let's go");
                String line;

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                while ((line = reader.readLine()) != null)
                {
                    response += line + "\n";
                }
            }
            else
            {
                logger.d("Response Code was something other than OK - returning nothing");
                response = "";
            }
        }
        catch(Exception ex)
        {
            logger.e("Got the following error POSTing to the service:\n" + ex.getMessage());
        }
        logger.d("Returning the response:\n" + response);
        logger.logExit();
        return response;
    }

    @Override
    protected void onPostExecute(String result)
    {
        //default onPostExecute, just logs the response
        logger.logInnerClassEnter("GetNextUserIdTask", "onPostExecute");

        logger.d("Got the result: " + result);

        logger.logInnerClassExit("GetNextUserIdTask", "onPostExecute");
    }

}
