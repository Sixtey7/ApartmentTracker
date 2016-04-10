package com.patrickwshaw.apartmenttracker.serviceInterfaces;

import android.app.Activity;
import android.widget.Toast;

import com.patrickwshaw.apartmenttracker.R;
import com.patrickwshaw.apartmenttracker.constants.NetworkConstants;
import com.patrickwshaw.apartmenttracker.model.database.dao.LivingDao;
import com.patrickwshaw.apartmenttracker.model.model.PlaceToLive;
import com.patrickwshaw.apartmenttracker.utility.JSONUtil;
import com.patrickwshaw.apartmenttracker.utility.LoggingUtil;
import com.patrickwshaw.apartmenttracker.utility.NetworkUtil;
import com.patrickwshaw.apartmenttracker.utility.rest.GetRestUtil;
import com.patrickwshaw.apartmenttracker.utility.rest.PostRestUtil;
import com.patrickwshaw.apartmenttracker.view.fragment.SelectPlaceFragment;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.List;

/**
 * Created by Patrick on 4/23/2015.
 */
public class ApartmentServiceInterface
{
    private static final LoggingUtil logger = new LoggingUtil("ApartmentServiceInterface", "ApartmentServiceInterface");

    private Activity activity;

    public ApartmentServiceInterface(Activity inActivity)
    {
        logger.logEnter("constructor(Activity");
        this.activity = inActivity;
        logger.logExit();
    }

    /*public void retrieveAllApartments()
    {
        logger.logEnter("retrieveAllUsers");
        NetworkUtil networkUtil = new NetworkUtil(activity);
        String urlToCall = networkUtil.buildApartmentServiceURLBase() + "/" + NetworkConstants.GET_ALL_APARTMENTS_ENDPOINT;

        logger.d("Built the following url to call: " + urlToCall);
        GetAllApartmentsTask aat = new GetAllApartmentsTask();
        aat.execute(urlToCall);

        logger.logExit();
    }*/

    public void updateApartmentsFromServer()
    {
        logger.logEnter("updateApartmentsFromServer");

        Toast toast = Toast.makeText(activity, R.string.toast_get_from_server_start,Toast.LENGTH_SHORT);
        toast.show();

        NetworkUtil networkUtil = new NetworkUtil(activity);
        String urlToCall = networkUtil.buildApartmentServiceURLBase() + "/" + NetworkConstants.GET_ALL_APARTMENTS_ENDPOINT;

        logger.d("Built the following url to call: " + urlToCall);
        UpdateFromServerTask ufst = new UpdateFromServerTask();
        ufst.execute(urlToCall);

        logger.logExit();
    }

    public void postAllApartments()
    {
        logger.logEnter("postAllApartments");

        Toast toast = Toast.makeText(activity, R.string.toast_post_to_server_start, Toast.LENGTH_SHORT);
        toast.show();

        LivingDao livingDao = new LivingDao(activity);
        List<PlaceToLive> allPlacesToLive = livingDao.getAll();

        logger.d("Living Dao returned " + allPlacesToLive.size() + " places to live");
        if (allPlacesToLive.size() > 0)
        {
            logger.d("We have places to live - try to post them");
            try
            {
                //turn the places to live into json that we can then pass to the server
                JSONArray placesToLiveJSON = JSONUtil.placesToLiveToJSONArray(allPlacesToLive);

                //build the url to call
                NetworkUtil networkUtil = new NetworkUtil(activity);
                String urlToCall = networkUtil.buildApartmentServiceURLBase() + "/" + NetworkConstants.SAVE_APARTMENT_ENDPOINT;
                logger.d("Built the following URL to call: " + urlToCall);

                //and execute the post task
                PostApartmentsTask postApartmentsTask = new PostApartmentsTask(placesToLiveJSON.toString());
                postApartmentsTask.execute(urlToCall);

            }
            catch(JSONException jse)
            {
                logger.e("Got the following error trying to turn places to live into json:\n" + jse.getMessage());
                logger.logExit();
                return;
            }
        }
        else
        {
            logger.w("There were no places to live - so we're not going to do anything");
        }

        logger.logExit();
    }

    public class UpdateFromServerTask extends GetRestUtil
    {
        @Override
        protected String doInBackground(String... urls)
        {
            logger.logInnerClassEnter("GetAllApartmentsTask", "doInBackGround");
            try
            {
                String result = downloadUrl(urls[0]);
                logger.logInnerClassExit("GetAllApartmentsTask", "doInBackGround");
                return result;
            }
            catch (IOException ioe)
            {
                logger.e("Exception caught during download of url:\n" + ioe.getMessage());
                logger.logInnerClassExit("GetAllApartmentsTask", "doInBackGround");
                return null;
            }
        }

        @Override
        public void onPostExecute(String result)
        {
            logger.logInnerClassEnter("GetAllApartmentsTask", "onPostExecute");

            logger.d("Got the result:\n" + result);

            JSONArray resultJSONArray;
            try
            {
                resultJSONArray = new JSONArray(result);
            }
            catch(JSONException jse)
            {
                logger.e("Got the following error trying to create the json array:\n" + jse.getMessage());
                Toast.makeText(activity, R.string.toast_get_from_server_error, Toast.LENGTH_LONG).show();
                logger.logInnerClassExit("GetAllApartmentsTask", "onPostExecute");
                return;
            }
            try
            {
                logger.d("There were: " + resultJSONArray.length() + " items in the json array - processing");
                if (resultJSONArray.length() > 0)
                {
                    LivingDao livingDao = new LivingDao(activity);
                    List<PlaceToLive> resultList = JSONUtil.JSONArrayToPlacesToLive(resultJSONArray);

                    for (PlaceToLive thisServerPlaceToLive : resultList)
                    {
                        logger.d("Running for: " + thisServerPlaceToLive.getId() + " - " + thisServerPlaceToLive.getName());
                        PlaceToLive clientPlaceToLive = livingDao.get(thisServerPlaceToLive.getId());
                        if (clientPlaceToLive != null)
                        {
                            logger.d("There is a matching client value");
                            if (clientPlaceToLive.getLastUpdated().before(thisServerPlaceToLive.getLastUpdated()))
                            {
                                logger.d("The server last updated time was newer - saving it");
                                livingDao.save(thisServerPlaceToLive);
                            }
                            else
                            {
                                logger.d("The client last updated time was newer - not doing anything");
                            }

                        }
                        else
                        {
                            logger.d("There was not a matching client value");
                            livingDao.save(thisServerPlaceToLive);
                        }
                    }
                }

            }
            catch(JSONException jseArray)
            {
                logger.e("Got the following error trying to parse the json:\n" + jseArray.getMessage());
                Toast.makeText(activity, R.string.toast_get_from_server_error, Toast.LENGTH_LONG).show();
                logger.logInnerClassExit("GetAllApartmentsTask", "onPostExecute");
                return;
            }
            SelectPlaceFragment.refreshAdapter();
            Toast.makeText(activity, R.string.toast_get_from_server_end, Toast.LENGTH_LONG).show();
            logger.logInnerClassExit("GetAllApartmentsTask", "onPostExecute");

        }
    }

    public class PostApartmentsTask extends PostRestUtil
    {
        public PostApartmentsTask()
        {
            super();
            logger.logInnerClassEnter("PostApartmentsTask", "constructor(no args)");
            logger.logInnerClassExit("PostApartmentsTask", "constructor(no args)");
        }

        public PostApartmentsTask(String messageToSend)
        {
            super(messageToSend);
            logger.logInnerClassEnter("PostApartmentsTask", "constructor(String)");
            logger.logInnerClassExit("PostApartmentsTask", "constructor(String)");
        }

        @Override
        public void onPostExecute(String result)
        {
            logger.logInnerClassEnter("PostApartmentsTask", "onPostExecute");

            //TODO: Need to handle the result (if we're even expecting one
            logger.d("Got the response:\n" + result);

            Toast.makeText(activity, R.string.toast_post_to_server_end, Toast.LENGTH_LONG).show();

            logger.logInnerClassExit("PostApartmentsTask", "onPostExecute");
        }
    }
}
