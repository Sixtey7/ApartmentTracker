package com.patrickwshaw.apartmenttracker.model.model;

import android.database.Cursor;

import com.patrickwshaw.apartmenttracker.constants.DBConstants;
import com.patrickwshaw.apartmenttracker.utility.LoggingUtil;

import java.io.Serializable;

/**
 * Created by Patrick on 3/26/2015.
 */
public class PlaceDistances implements Serializable
{
    private final LoggingUtil logger = new LoggingUtil("PlaceDistances", "PlaceDistances");

    private Float patrickWorkDistance;
    private Float patrickWorkTime;
    private Boolean patrickWorkTolls;
    private Float danielleClassDistanceDrive;
    private Float danielleClassTimeDrive;
    private Float danielleClassTimePublic;
    private Float srtDistance;

    public PlaceDistances()
    {
        logger.logEnter("constructor");
        //default constructor
        logger.logExit();
    }

    public PlaceDistances(Cursor cursor)
    {
        logger.logEnter("constructor(Cursor");

        int columnIndex = cursor.getColumnIndex(DBConstants.PATRICK_WORK_DISTANCE_COLUMN_NAME);
        if (cursor.isNull(columnIndex))
        {
            this.patrickWorkDistance = null;
        }
        else
        {
            this.patrickWorkDistance = cursor.getFloat(columnIndex);
        }

        columnIndex = cursor.getColumnIndex(DBConstants.PATRICK_WORK_TIME_COLUMN_NAME);
        if (cursor.isNull(columnIndex))
        {
            this.patrickWorkTime = null;
        }
        else
        {
            this.patrickWorkTime = cursor.getFloat(columnIndex);
        }

        columnIndex = cursor.getColumnIndex(DBConstants.PATRICK_WORK_TOLLS_COLUMN_NAME);
        if (cursor.isNull(columnIndex))
        {
            this.patrickWorkTolls = null;
        }
        else
        {
            this.patrickWorkTolls = (cursor.getInt(columnIndex) == 1);
        }

        columnIndex = cursor.getColumnIndex(DBConstants.DANIELLE_CLASS_DISTANCE_DRIVE_COLUMN_NAME);
        if (cursor.isNull(columnIndex))
        {
            this.danielleClassDistanceDrive = null;
        }
        else
        {
            this.danielleClassDistanceDrive = cursor.getFloat(columnIndex);
        }

        columnIndex = cursor.getColumnIndex(DBConstants.DANIELLE_CLASS_TIME_DRIVE_COLUMN_NAME);
        if (cursor.isNull(columnIndex))
        {
            this.danielleClassTimeDrive = null;
        }
        else
        {
            this.danielleClassTimeDrive = cursor.getFloat(columnIndex);
        }

        columnIndex = cursor.getColumnIndex(DBConstants.DANIELLE_CLASS_TIME_PUBLIC_COLUMN_NAME);
        if (cursor.isNull(columnIndex))
        {
            this.danielleClassTimePublic = null;
        }
        else
        {
            this.danielleClassTimePublic = cursor.getFloat(columnIndex);
        }

        columnIndex = cursor.getColumnIndex(DBConstants.SRT_DISTANCE_COLUMN_NAME);
        if (cursor.isNull(columnIndex))
        {
            this.srtDistance = null;
        }
        else
        {
            this.srtDistance = cursor.getFloat(columnIndex);
        }

        logger.logExit();
    }
    public Float getPatrickWorkDistance() {
        return patrickWorkDistance;
    }

    public void setPatrickWorkDistance(Float patrickWorkDistance) {
        this.patrickWorkDistance = patrickWorkDistance;
    }

    public Float getPatrickWorkTime() {
        return patrickWorkTime;
    }

    public void setPatrickWorkTime(Float patrickWorkTime) {
        this.patrickWorkTime = patrickWorkTime;
    }

    public Boolean getPatrickWorkTolls() { return patrickWorkTolls; }

    public void setPatrickWorkTolls(Boolean patrickWorkTolls)
    {
        this.patrickWorkTolls = patrickWorkTolls;
    }

    public Float getDanielleClassDistanceDrive() {
        return danielleClassDistanceDrive;
    }

    public void setDanielleClassDistanceDrive(Float danielleClassDistanceDrive) {
        this.danielleClassDistanceDrive = danielleClassDistanceDrive;
    }

    public Float getDanielleClassTimeDrive() {
        return danielleClassTimeDrive;
    }

    public void setDanielleClassTimeDrive(Float danielleClassTimeDrive) {
        this.danielleClassTimeDrive = danielleClassTimeDrive;
    }

    public Float getDanielleClassTimePublic() {
        return danielleClassTimePublic;
    }

    public void setDanielleClassTimePublic(Float danielleClassTimePublic) {
        this.danielleClassTimePublic = danielleClassTimePublic;
    }

    public Float getSrtDistance() {
        return srtDistance;
    }

    public void setSrtDistance(Float srtDistance) {
        this.srtDistance = srtDistance;
    }
}
