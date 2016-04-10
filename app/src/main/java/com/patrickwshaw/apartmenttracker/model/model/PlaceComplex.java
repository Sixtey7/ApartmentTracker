package com.patrickwshaw.apartmenttracker.model.model;

import android.database.Cursor;

import com.patrickwshaw.apartmenttracker.constants.DBConstants;
import com.patrickwshaw.apartmenttracker.constants.LivingConstants;
import com.patrickwshaw.apartmenttracker.utility.LoggingUtil;

import java.io.Serializable;

/**
 * Created by Patrick on 3/26/2015.
 */
public class PlaceComplex implements Serializable
{
    private final LoggingUtil logger = new LoggingUtil("PlaceComplex", "PlaceComplex");

    private Boolean hasFitnessCenter;
    private Integer fitnessCenterFee;
    private LivingConstants.termTypes fitnessCenterFeeTerm;
    private Boolean hasPool;
    private Integer poolFee;
    private LivingConstants.termTypes poolFeeTerm;
    private LivingConstants.outdoorDogSpaceTypes outdoorDogSpace;

    public PlaceComplex()
    {
        logger.logEnter("constructor(no args)");
        //default constructor
        logger.logExit();
    }

    public PlaceComplex(Cursor cursor)
    {
        logger.logEnter("constructor(Cursor");
        int booleanVal = 0;

        booleanVal = cursor.getInt(cursor.getColumnIndex(DBConstants.FITNESS_CENTER_COLUMN_NAME));
        this.hasFitnessCenter = (booleanVal == 1);

        int columnIndex = cursor.getColumnIndex(DBConstants.FITNESS_CENTER_FEE_COLUMN_NAME);
        if (cursor.isNull(columnIndex))
        {
            this.fitnessCenterFee = null;
        }
        else
        {
            this.fitnessCenterFee = cursor.getInt(columnIndex);
        }
        this.fitnessCenterFeeTerm = LivingConstants.termTypes.fromStringVal(cursor.getString(cursor.getColumnIndex(DBConstants.FITNESS_CENTER_FEE_TERM_COLUMN_NAME)));

        booleanVal = cursor.getInt(cursor.getColumnIndex(DBConstants.POOL_COLUMN_NAME));
        this.hasPool = (booleanVal == 1);

        columnIndex = cursor.getColumnIndex(DBConstants.POOL_FEE_COLUMN_NAME);
        if (cursor.isNull(columnIndex))
        {
            this.poolFee = null;
        }
        else
        {
            this.poolFee = cursor.getInt(columnIndex);
        }
        this.poolFeeTerm = LivingConstants.termTypes.fromStringVal(cursor.getString(cursor.getColumnIndex(DBConstants.POOL_FEE_TERM_COLUMN_NAME)));

        this.outdoorDogSpace = LivingConstants.outdoorDogSpaceTypes.fromStringVal(cursor.getString(cursor.getColumnIndex(DBConstants.OUTDOOR_SPACE_COLUMN_NAME)));

        logger.logExit();
    }

    public Boolean getHasFitnessCenter() {
        return hasFitnessCenter;
    }

    public void setHasFitnessCenter(Boolean hasFitnessCenter) {
        this.hasFitnessCenter = hasFitnessCenter;
    }

    public Integer getFitnessCenterFee() {
        return fitnessCenterFee;
    }

    public void setFitnessCenterFee(Integer fitnessCenterFee) {
        this.fitnessCenterFee = fitnessCenterFee;
    }

    public LivingConstants.termTypes getFitnessCenterFeeTerm() {
        return fitnessCenterFeeTerm;
    }

    public void setFitnessCenterFeeTerm(LivingConstants.termTypes fitnessCenterFeeTerm) {
        this.fitnessCenterFeeTerm = fitnessCenterFeeTerm;
    }

    public Boolean getHasPool() {
        return hasPool;
    }

    public void setHasPool(Boolean hasPool) {
        this.hasPool = hasPool;
    }

    public Integer getPoolFee() {
        return poolFee;
    }

    public void setPoolFee(Integer poolFee) {
        this.poolFee = poolFee;
    }

    public LivingConstants.termTypes getPoolFeeTerm() {
        return poolFeeTerm;
    }

    public void setPoolFeeTerm(LivingConstants.termTypes poolFeeTerm) {
        this.poolFeeTerm = poolFeeTerm;
    }

    public LivingConstants.outdoorDogSpaceTypes getOutdoorDogSpace() {
        return outdoorDogSpace;
    }

    public void setOutdoorDogSpace(LivingConstants.outdoorDogSpaceTypes outdoorDogSpace) {
        this.outdoorDogSpace = outdoorDogSpace;
    }
}
