package com.patrickwshaw.apartmenttracker.utility;

import android.app.Activity;
import android.widget.ArrayAdapter;

/**
 * Created by Patrick on 3/31/2015.
 */
public class EnumUtil
{
    public static ArrayAdapter<CharSequence> buildSpinnerAdapter(Activity context, Enum<?> enumClass[], boolean addBlank)
    {
        ArrayAdapter<CharSequence> returnVal = new ArrayAdapter<CharSequence>(context, android.R.layout.simple_spinner_item);
        if (addBlank)
        {
            returnVal.add("");
        }

        for (Enum myVal : enumClass)
        {
            returnVal.add(myVal.toString());
        }

        returnVal.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return returnVal;
    }
}
