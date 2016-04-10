package com.patrickwshaw.apartmenttracker.view.listener;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.patrickwshaw.apartmenttracker.R;
import com.patrickwshaw.apartmenttracker.constants.LivingConstants;
import com.patrickwshaw.apartmenttracker.model.database.dao.LivingDao;
import com.patrickwshaw.apartmenttracker.model.model.PlaceToLive;
import com.patrickwshaw.apartmenttracker.utility.LoggingUtil;
import com.patrickwshaw.apartmenttracker.view.dialog.DialogFactory;
import com.patrickwshaw.apartmenttracker.view.fragment.EditPlaceFragment;

/**
 * Created by Patrick on 3/28/2015.
 */
public class AddNewPlaceListener implements ImageButton.OnClickListener
{
    private final LoggingUtil logger = new LoggingUtil("AddNewPlaceListener", "AddNewPlaceListener");

    private Activity activity;

    //no one should call the default constructor
    private AddNewPlaceListener()
    {
        //default constructor
    }

    public AddNewPlaceListener(Activity activity)
    {
        logger.logEnter("Constructor");
        this.activity = activity;
        logger.logExit();
    }

    @Override
    public void onClick(View v)
    {
        logger.logEnter("onClick");

        logger.d("User selected to add a new complex entry");

        //create a text dialog to prompt the user to enter a name
        AlertDialog createPlaceToLiveDialog = DialogFactory.createTextPrompt(activity, R.string.create_place_to_live_dialog_title, R.string.create_place_to_live_dialog_text);
        createPlaceToLiveDialog.setOnDismissListener(new DialogInterface.OnDismissListener()
        {
            @Override
            public void onDismiss(DialogInterface dialog)
            {
                logger.logInnerClassEnter("createPlaceToLiveDialog", "onDismiss");

                String userResponse = DialogFactory.userResponse;

                if (userResponse.equals(""))
                {
                    logger.w("No Response Detected");
                }
                else
                {
                    logger.d("The user entered: " + userResponse);
                    PlaceToLive newPlaceToLive = new PlaceToLive(userResponse);

                    //and save it
                    LivingDao livingDao = new LivingDao(activity);
                    livingDao.save(newPlaceToLive);
                    logger.d(newPlaceToLive.toString());

                    //open the edit place fragment with the newly added checklist
                    FragmentTransaction transaction = activity.getFragmentManager().beginTransaction();

                    Fragment editPlaceFragment = new EditPlaceFragment();

                    //pack up a bundle that holds the object the user selected
                    Bundle args = new Bundle();
                    args.putSerializable(LivingConstants.SELECTED_PLACE_TO_LIVE_TAG, newPlaceToLive);

                    editPlaceFragment.setArguments(args);

                    transaction.replace(R.id.fragmentContainer, editPlaceFragment);

                    transaction.addToBackStack(this.getClass().getSimpleName());

                    transaction.commit();
                }

                logger.logInnerClassExit("createPlaceToLiveDialog", "onDismiss");
            }
        });

        createPlaceToLiveDialog.show();

        logger.logExit();
    }
}
