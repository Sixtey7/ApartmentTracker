package com.patrickwshaw.apartmenttracker.model.model;

import android.database.Cursor;

import com.patrickwshaw.apartmenttracker.constants.DBConstants;
import com.patrickwshaw.apartmenttracker.utility.LoggingUtil;

import java.io.Serializable;

/**
 * Created by Patrick on 3/26/2015.
 */
public class PlaceAddress implements Serializable
{
    private LoggingUtil logger = new LoggingUtil("PlaceAddress", "PlaceAddress");
    private String street;
    private String apartmentNo;
    private String city;
    private String state;
    private Integer zipCode;

    public PlaceAddress()
    {
        logger.logEnter("constructor(no args)");
        logger.logExit();
    }

    public PlaceAddress(String[] addressArray)
    {
        logger.logEnter("constructor(String[]");
        if (addressArray.length != 3)
        {
            logger.e("An address array of an invalid length was passed in, passed in length was: : " + addressArray.length);
            throw new IllegalArgumentException("Invalid Address - Wrong Address Array Length");
        }

        //if we got here, we can assume we have an array of three - some number of those can still be numm though

        //line 0 contains street
        this.street = addressArray[0];

        if (this.street.equals(""))
        {
            this.street = null;
        }

        //line 1 contains apartment number
        this.apartmentNo = addressArray[1];

        if (this.apartmentNo.equals(""))
        {
            this.apartmentNo = null;
        }

        if (addressArray[2] != null)
        {
            logger.d("addressArray[2] was not null - attempting to parse it");
            int firstCommaPos = addressArray[2].indexOf(',');
            if (firstCommaPos < 0)
            {
                logger.e("No first comma was found - can't parse out line 2");
                throw new IllegalArgumentException("Invalid Address - No Comma in Address 2");
            }

            int secondCommaPos = addressArray[2].indexOf(',', firstCommaPos + 1);
            if (secondCommaPos < 0)
            {
                logger.e("No Second comma was found - can't parse out line 2");
                throw new IllegalArgumentException("Invalid Address - Only One Comma in Address 2");
            }

            //if we got here, we're in the clear, found two commas
            this.city = addressArray[2].substring(0, firstCommaPos);
            if (this.city.equals(""))
            {
                this.city = null;
            }

            this.state = addressArray[2].substring(firstCommaPos + 1, secondCommaPos);
            if (this.state.equals(""))
            {
                this.state = null;
            }


            String zipString = addressArray[2].substring(secondCommaPos + 1).trim();
            if (zipString.equals(""))
            {
                this.zipCode = null;
            }
            else
            {
                this.zipCode = Integer.parseInt(zipString);
            }

            logger.d("Parsed out City: " + this.city);
            logger.d("Parsed out State: " + this.state);
            logger.d("Parsed on Zip Code: " + this.zipCode);
        }
        else
        {
            logger.d("addressArray[2] was null");
        }

        logger.logExit();
    }

    public PlaceAddress(Cursor cursor)
    {
        logger.logEnter("constructor(Cursor");

        this.street = cursor.getString(cursor.getColumnIndex(DBConstants.STREET_COLUMN_NAME));
        this.apartmentNo = cursor.getString(cursor.getColumnIndex(DBConstants.APARTMENT_COLUMN_NAME));
        this.city = cursor.getString(cursor.getColumnIndex(DBConstants.CITY_COLUMN_NAME));
        this.state = cursor.getString(cursor.getColumnIndex(DBConstants.STATE_COLUMN_NAME));

        int columnIndex = cursor.getColumnIndex(DBConstants.ZIP_COLUMN_NAME);
        if (cursor.isNull(columnIndex))
        {
            this.zipCode = null;
        }
        else
        {
            this.zipCode = cursor.getInt(columnIndex);
        }

        logger.logExit();
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getApartmentNo() {
        return apartmentNo;
    }

    public void setApartmentNo(String apartmentNo) {
        this.apartmentNo = apartmentNo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public String getAddressLine1()
    {
        return street;
    }

    public String getAddressLine2()
    {
        if (apartmentNo == null)
        {
            return null;
        }

        return apartmentNo;
    }

    public String getAddressLine3()
    {
        String returnString = "";
        if (city == null && state == null && zipCode == null)
        {
            return returnString;
        }

        if (city != null)
        {
            returnString = returnString + city;
        }
        returnString = returnString + ", ";
        if (state != null)
        {
            returnString = returnString + state;
        }
        returnString = returnString + ", ";
        if (zipCode != null)
        {
            returnString = returnString + zipCode;
        }

        return returnString;
    }

    public String[] getAddressLines()
    {
        String[] returnString = new String[3];
        returnString[0] = getAddressLine1();
        returnString[1] = getAddressLine2();
        returnString[2] = getAddressLine3();

        return returnString;
    }
}
