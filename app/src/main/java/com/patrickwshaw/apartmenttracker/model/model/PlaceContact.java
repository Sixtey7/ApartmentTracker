package com.patrickwshaw.apartmenttracker.model.model;

import android.database.Cursor;

import com.patrickwshaw.apartmenttracker.constants.DBConstants;
import com.patrickwshaw.apartmenttracker.constants.LivingConstants;
import com.patrickwshaw.apartmenttracker.utility.LoggingUtil;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Patrick on 4/29/2015.
 */
public class PlaceContact implements Serializable
{
    private static final LoggingUtil logger = new LoggingUtil("PlaceContact", "PlaceContact");
    private LivingConstants.contactType contactType;
    private Calendar contactDate;

    public PlaceContact()
    {
        logger.logEnter("constructor(no args)");

        logger.logExit();
    }

    public PlaceContact(Cursor cursor)
    {
        logger.logEnter("constructor(cursor");
        int columnIndex = cursor.getColumnIndex(DBConstants.CONTACT_TYPE_COLUMN_NAME);
        if (!(cursor.isNull(columnIndex)))
        {
            this.contactType = LivingConstants.contactType.fromStringVal(cursor.getString(cursor.getColumnIndex(DBConstants.CONTACT_TYPE_COLUMN_NAME)));
        }

        columnIndex = cursor.getColumnIndex(DBConstants.CONTACT_DATE_COLUMN_NAME);
        if (!(cursor.isNull(columnIndex)))
        {
            Calendar contactTime = Calendar.getInstance();
            Date contactTimeDate = new Date();
            contactTimeDate.setTime(cursor.getLong(cursor.getColumnIndex(DBConstants.CONTACT_DATE_COLUMN_NAME)));
            contactTime.setTime(contactTimeDate);
            this.setContactDate(contactTime);
        }

        logger.logExit();
    }

    public LivingConstants.contactType getContactType()
    {
        return contactType;
    }

    public void setContactType(LivingConstants.contactType contactType)
    {
        this.contactType = contactType;
    }

    public Calendar getContactDate()
    {
        return contactDate;
    }

    public void setContactDate(Calendar contactDate)
    {
        this.contactDate = contactDate;
    }
}
