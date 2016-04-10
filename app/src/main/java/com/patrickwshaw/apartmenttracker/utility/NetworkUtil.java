package com.patrickwshaw.apartmenttracker.utility;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.patrickwshaw.apartmenttracker.constants.NetworkConstants;

/**
 * Created by Patrick on 4/23/2015.
 */
public class NetworkUtil
{
    private static final LoggingUtil logger = new LoggingUtil("NetworkUtil", "NetworkUtil");
    private Activity activity;

    public NetworkUtil(Activity inActivity)
    {
        logger.logEnter("constructor(Activity");
        this.activity = inActivity;
        logger.logExit();
    }
    public String buildApartmentServiceURLBase()
    {
        logger.logEnter("buildApartmentServiceURLBase");

        String stringToReturn = "http://";

        stringToReturn += getServiceUrl();

        stringToReturn += ":" + NetworkConstants.SERVER_PORT + NetworkConstants.APARTMENT_REST_ENDPOINT;

        logger.logExit();
        return stringToReturn;
    }

    private String getServiceUrl()
    {
        logger.logEnter("getServiceUrl");
        //we're making the (probably poor) assumption that if the user is on wifi
        //then they are on my wifi so use the wifi address
        //otherwise, use the comcast address

        ConnectivityManager connMgr = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null)
        {
            logger.d("Network info was not null");
            if (networkInfo.isAvailable())
            {
                logger.d("Network is available");
                if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI)
                {
                    logger.d("Detected that the user as connected to wifi");
                    logger.logExit();
                    return NetworkConstants.WIFI_SERVER_URL;
                }
                else
                {
                    logger.d("User was not on wifi");
                    logger.logExit();
                    return NetworkConstants.NETWORK_SERVER_URL;
                }
            }
            else
            {
                logger.w("Network was not available, return comcast");
                logger.logExit();
                return NetworkConstants.NETWORK_SERVER_URL;
            }
        }
        else
        {
            logger.w("Network info was null - return comcast");
            logger.logExit();
            return NetworkConstants.NETWORK_SERVER_URL;
        }
    }
}
