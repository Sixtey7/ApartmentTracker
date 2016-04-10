package com.patrickwshaw.apartmenttracker.view.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.EditText;

import com.patrickwshaw.apartmenttracker.R;
import com.patrickwshaw.apartmenttracker.utility.LoggingUtil;

/**
 * Created by Patrick on 4/6/2015.
 */
public class DialogFactory
{
    public static String userResponse;

    private static final LoggingUtil logger = new LoggingUtil("DialogFactory", "DialogFactory");
    public static final AlertDialog createErrorDialog(Activity activity, String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(R.string.error_dialog_title);
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        return builder.create();
    }

    public static final AlertDialog createWarningDialog(Activity activity, String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(R.string.warning_dialog_title);
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int id)
            {
                dialog.cancel();
            }

        });

        return builder.create();
    }

    public static final AlertDialog createWarningDialog(Activity activity, int messageId)
    {
        return DialogFactory.createWarningDialog(activity, activity.getString(messageId));
    }

    public static final AlertDialog createNetworkErrorDialog(Activity activity)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(activity.getString(R.string.network_error_dialog_title));
        builder.setMessage(activity.getString(R.string.network_error_dialog_message));
        builder.setCancelable(false);
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.cancel();
            }
        });
        return builder.create();
    }

    public static final AlertDialog createErrorDialog(Activity activity)
    {
        return createErrorDialog(activity, activity.getString(R.string.error_dialog_message));
    }

    public static final AlertDialog createTextPrompt(Activity activity, int messageTitle, int messageText)
    {
        logger.logEnter("createTextPrompt(Activity, int, int)");

        AlertDialog.Builder alert = new AlertDialog.Builder(activity);

        userResponse = "";
        alert.setTitle(messageTitle);
        alert.setMessage(messageText);

        // Set an EditText view to get user input
        final EditText inputField = new EditText(activity);
        alert.setView(inputField);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int whichButton)
            {
                userResponse = inputField.getText().toString();
                dialog.dismiss();
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int whichButton)
            {
                userResponse = "";
                dialog.cancel();
            }
        });

        logger.logExit();
        return alert.create();
    }

    public static final AlertDialog createYesNoPrompt(Activity activity, int title, int message, int yesLabel, int noLabel, DialogInterface.OnClickListener onClickListener)
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(activity);

        alert.setTitle(title);
        alert.setTitle(message);

        alert.setPositiveButton(yesLabel, onClickListener);

        alert.setNegativeButton(noLabel, onClickListener);

        return alert.create();
    }
}
