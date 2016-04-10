package com.patrickwshaw.apartmenttracker.model.model;

import android.database.Cursor;

import com.patrickwshaw.apartmenttracker.constants.DBConstants;
import com.patrickwshaw.apartmenttracker.utility.LoggingUtil;

import java.io.Serializable;

/**
 * Created by Patrick on 3/26/2015.
 */
public class PlacePhoneNumber implements Serializable
{
    private final LoggingUtil logger = new LoggingUtil("PlacePhoneNumber", "PlacePhoneNumber");
    private String areaCode;
    private String phoneNumberFirst;
    private String phoneNumberSecond;

    public PlacePhoneNumber()
    {
        logger.logEnter("constructor(no args)");

        logger.logExit();
    }
    public PlacePhoneNumber(String phoneNumber)
    {
        logger.logEnter("Constructor(String)");

        if (phoneNumber.length() > 10)
        {
            int placeOfInterest = 0;
            int parPlace = phoneNumber.indexOf('(');
            if (parPlace < 0)
            {
                logger.d("Par place was 0, assuming that first three chars are area code");
                this.areaCode = phoneNumber.substring(0, 2);
                logger.d("Parsed out area code: " + this.areaCode);
                placeOfInterest = 2;
            }
            else
            {
                logger.d("Found a location of the first par - position: " + parPlace + " - looking for a second");
                int parPlaceSecond = phoneNumber.indexOf(')', parPlace);
                if (parPlaceSecond < 0)
                {
                    logger.e("Found a first par, but didn't find a second, this sucks");
                    throw new IllegalArgumentException("Phone number is in a bad format - had left par, but not second");
                }
                else
                {
                    logger.d("Found both a open and close par (Close par at: " + parPlaceSecond + " - we can parse out an area code");
                    this.areaCode = phoneNumber.substring(parPlace + 1, parPlaceSecond);
                    logger.d("Parsed out area code: " + this.areaCode);
                    placeOfInterest = parPlaceSecond;
                }
            }

            int dashPlace = phoneNumber.indexOf("-");
            if (dashPlace < 0)
            {
                logger.d("No dash was found - assuming all of the numbers are bunched together");
                this.phoneNumberFirst = phoneNumber.substring(placeOfInterest + 1, placeOfInterest + 3);
                this.phoneNumberSecond = phoneNumber.substring(placeOfInterest + 4).trim();
                logger.d("Parsed Out Phone Number First: " + this.phoneNumberFirst);
                logger.d("Parsed Out Phone Number Second: " + this.phoneNumberSecond);
            }
            else
            {
                logger.d("Found a dash at position: " + dashPlace);
                this.phoneNumberFirst = phoneNumber.substring(placeOfInterest + 1, dashPlace).trim();
                this.phoneNumberSecond = phoneNumber.substring(dashPlace + 1);
                logger.d("Parsed Out Phone Number First: " + this.phoneNumberFirst);
                logger.d("Parsed Out Phone Number Second: " + this.phoneNumberSecond);
            }

        }
        else
        {
            logger.e("Phone number was too short, only was: " + phoneNumber.length() + " characters");
            throw new IllegalArgumentException("Phone number was not in correct format (too short)");
        }


        logger.logExit();
    }

    public PlacePhoneNumber(Cursor cursor)
    {
        logger.logEnter("constructor(Cursor)");
        int columnIndex = cursor.getColumnIndex(DBConstants.OFFICE_PHONE_NUMBER_AREA_CODE_COLUMN_NAME);
        if (cursor.isNull(columnIndex))
        {
            this.areaCode = null;
        }
        else
        {
            this.areaCode = cursor.getString(columnIndex);
        }

        columnIndex = cursor.getColumnIndex(DBConstants.OFFICE_PHONE_NUMBER_FIRST_COLUMN_NAME);
        if (cursor.isNull(columnIndex))
        {
            this.phoneNumberFirst = null;
        }
        else
        {
            this.phoneNumberFirst = cursor.getString(columnIndex);
        }

        columnIndex = cursor.getColumnIndex(DBConstants.OFFICE_PHONE_NUMBER_SECOND_CODE_COLUMN_NAME);
        if (cursor.isNull(columnIndex))
        {
            this.phoneNumberSecond = null;
        }
        else
        {
            this.phoneNumberSecond = cursor.getString(columnIndex);
        }

        logger.logExit();
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getPhoneNumberFirst() {
        return phoneNumberFirst;
    }

    public void setPhoneNumberFirst(String phoneNumberFirst) {
        this.phoneNumberFirst = phoneNumberFirst;
    }

    public String getPhoneNumberSecond() {
        return phoneNumberSecond;
    }

    public void setPhoneNumberSecond(String phoneNumberSecond) {
        this.phoneNumberSecond = phoneNumberSecond;
    }

    @Override
    public String toString()
    {
        if (areaCode != null && phoneNumberFirst != null && phoneNumberSecond != null)
        {
            return "(" + areaCode + ") " + phoneNumberFirst + "-" + phoneNumberSecond;
        }
        else
        {
            return "";
        }
    }
}
