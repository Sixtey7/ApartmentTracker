package com.patrickwshaw.apartmenttracker.model.database.dao;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.patrickwshaw.apartmenttracker.constants.DBConstants;
import com.patrickwshaw.apartmenttracker.model.database.implementation.DatabaseHelper;
import com.patrickwshaw.apartmenttracker.model.model.PlaceContact;
import com.patrickwshaw.apartmenttracker.model.model.PlaceToLive;
import com.patrickwshaw.apartmenttracker.utility.LoggingUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Patrick on 4/5/2015.
 */
public class LivingDao
{
    private final LoggingUtil logger = new LoggingUtil("LivingDao", "LivingDao");
    private static DatabaseHelper dbh;

    public LivingDao(Activity context)
    {
        logger.logEnter("Constructer(Activity)");
        if (dbh == null)
        {
            logger.d("Creating a new database helper");
            dbh = new DatabaseHelper(context);
        }

        logger.logExit();
    }

    public int getNextId()
    {
        logger.logEnter("getNextId");

        String GET_MAX_ID_SQL = "SELECT MAX(" + DBConstants.ID_COLUMN_NAME + ") FROM " + DBConstants.DATABASE_TABLE_NAME + ";";

        logger.d(GET_MAX_ID_SQL);

        SQLiteDatabase db = dbh.getReadableDatabase();
        Cursor cursor = db.rawQuery(GET_MAX_ID_SQL, null);

        int valueToReturn = 1;
        if ((cursor != null) && (cursor.getCount() > 0))
        {
            logger.d("Results were returned from the database!");
            cursor.moveToFirst();
            int cursorValue = cursor.getInt(0);
            logger.d("The current biggest id in the database is: " + cursorValue);
            valueToReturn = cursorValue + 1;
        }
        else
        {
            logger.d("No results from the database, returning 1");
            //since value to return is defaulted to 1, no reason to set it here
        }
        cursor.close();
        db.close();
        logger.logExit();
        return  valueToReturn;
    }

    public PlaceToLive get(int idToGet)
    {
        logger.logEnter("get(int)");

        String GET_PLACE_TO_LIVE_SQL = "SELECT * FROM " + DBConstants.DATABASE_TABLE_NAME +
                " WHERE " + DBConstants.ID_COLUMN_NAME + " = " + idToGet + ";";

        logger.d(GET_PLACE_TO_LIVE_SQL);

        SQLiteDatabase db = dbh.getReadableDatabase();
        Cursor cursor = db.rawQuery(GET_PLACE_TO_LIVE_SQL, null);

        PlaceToLive returnVal = null;
        if ((cursor != null) && (cursor.getCount() > 0))
        {
            cursor.moveToFirst();
            returnVal = new PlaceToLive(cursor);
        }

        if (returnVal == null)
        {
            logger.w("Returning a null place to live!");
        }
        cursor.close();
        db.close();
        logger.logExit();
        return returnVal;
    }

    public List<PlaceToLive> getAll()
    {
        logger.logEnter("getAll");

        List<PlaceToLive> returnList = new ArrayList<PlaceToLive>();

        String GET_ALL_SQL = "SELECT * FROM " + DBConstants.DATABASE_TABLE_NAME + ";";

        SQLiteDatabase db = dbh.getReadableDatabase();

        Cursor cursor = db.rawQuery(GET_ALL_SQL, null);

        if ((cursor != null) && (cursor.getCount() > 0))
        {
            logger.d("There were: " + cursor.getCount() + " PlaceToLive entries returned");
            cursor.moveToFirst();
            while (!cursor.isAfterLast())
            {
                returnList.add(new PlaceToLive(cursor));
                cursor.moveToNext();
            }
        }

        logger.logExit();
        return  returnList;
    }

    public boolean keyExists(int id)
    {
        logger.logEnter("keyExists(id)");

        PlaceToLive placeToLive = this.get(id);

        if (placeToLive == null)
        {
            logger.d("returning false");
            logger.logExit();
            return false;
        }
        else
        {
            logger.d("returning true");
            logger.logExit();
            return true;
        }
    }
    public boolean save(PlaceToLive placeToLive)
    {
        logger.logEnter("save");

        //update the last date updated
        placeToLive.setLastUpdated(Calendar.getInstance());

        //get the current version of this place to live (if needed)
        PlaceToLive currentPlaceToLive = null;
        if (placeToLive.getId() == null)
        {
            logger.d("Id was null - setting it");
            placeToLive.setId(this.getNextId());
        }
        else
        {
            currentPlaceToLive = this.get(placeToLive.getId());
        }

        //put in the values
        ContentValues contentValues = new ContentValues();

        contentValues.put(DBConstants.ID_COLUMN_NAME, placeToLive.getId());
        contentValues.put(DBConstants.LAST_UPDATED_COLUMN_NAME, placeToLive.getLastUpdated().getTime().getTime());

        if (placeToLive.getStatus() != null)
        {
            contentValues.put(DBConstants.PLACE_TO_LIVE_STATE_NAME, placeToLive.getStatus().toString());
        }

        contentValues.put(DBConstants.NAME_COLUMN_NAME, placeToLive.getName());
        if (placeToLive.getType() != null)
        {
            contentValues.put(DBConstants.STYLE_COLUMN_NAME, placeToLive.getType().toString());
        }
        contentValues.put(DBConstants.NUM_BEDS_COLUMN_NAME, placeToLive.getNumBeds());
        contentValues.put(DBConstants.NUM_BATHS_COLUMN_NAME, placeToLive.getNumBeds());
        contentValues.put(DBConstants.FLOORS_COLUMN_NAME, placeToLive.getNumFloors());
        contentValues.put(DBConstants.SQ_FT_COLUMN_NAME, placeToLive.getSqFt());
        contentValues.put(DBConstants.RATING_COLUMN_NAME, placeToLive.getRating());
        if (placeToLive.getFirstFloorBedroom() != null)
        {
            contentValues.put(DBConstants.FIRST_FLOOR_BEDROOM_COLUMN_NAME, placeToLive.getFirstFloorBedroom().toString());
        }

        contentValues.put(DBConstants.OFFICE_HOURS_COLUMN_NAME, placeToLive.getOfficeHours());

        if (placeToLive.getOfficeNumber() != null)
        {
            if (placeToLive.getOfficeNumber().getAreaCode() != null)
            {
                contentValues.put(DBConstants.OFFICE_PHONE_NUMBER_AREA_CODE_COLUMN_NAME, placeToLive.getOfficeNumber().getAreaCode());
            }

            if (placeToLive.getOfficeNumber().getPhoneNumberFirst() != null)
            {
                contentValues.put(DBConstants.OFFICE_PHONE_NUMBER_FIRST_COLUMN_NAME, placeToLive.getOfficeNumber().getPhoneNumberFirst());
            }

            if (placeToLive.getOfficeNumber().getPhoneNumberSecond() != null)
            {
                contentValues.put(DBConstants.OFFICE_PHONE_NUMBER_SECOND_CODE_COLUMN_NAME, placeToLive.getOfficeNumber().getPhoneNumberSecond());
            }
        }

        contentValues.put(DBConstants.WEBSITE_COLUMN_NAME, placeToLive.getWebsite());

        if (placeToLive.getAddress() != null)
        {
            contentValues.put(DBConstants.STREET_COLUMN_NAME, placeToLive.getAddress().getStreet());
            contentValues.put(DBConstants.APARTMENT_COLUMN_NAME, placeToLive.getAddress().getApartmentNo());
            contentValues.put(DBConstants.CITY_COLUMN_NAME, placeToLive.getAddress().getCity());
            contentValues.put(DBConstants.STATE_COLUMN_NAME, placeToLive.getAddress().getState());
            contentValues.put(DBConstants.ZIP_COLUMN_NAME, placeToLive.getAddress().getZipCode());
        }

        if (placeToLive.getAmenities().getHasDishwasher() != null)
        {
            contentValues.put(DBConstants.DISHWASHER_COLUMN_NAME, (placeToLive.getAmenities().getHasDishwasher() ? 1 : 0));
        }
        if (placeToLive.getAmenities().getHasDisposal() != null)
        {
            contentValues.put(DBConstants.DISPOSAL_COLUMN_NAME, (placeToLive.getAmenities().getHasDisposal() ? 1 : 0));
        }
        if (placeToLive.getAmenities().getRange() != null)
        {
            contentValues.put(DBConstants.RANGE_COLUMN_NAME, placeToLive.getAmenities().getRange().toString());
        }
        if (placeToLive.getAmenities().getHasFios() != null)
        {
            contentValues.put(DBConstants.FIOS_COLUMN_NAME, (placeToLive.getAmenities().getHasFios() ? 1 : 0));
        }
        if (placeToLive.getAmenities().getAc() != null)
        {
            contentValues.put(DBConstants.AC_COLUMN_NAME, placeToLive.getAmenities().getAc().toString());
        }
        if (placeToLive.getAmenities().getHeat() != null)
        {
            contentValues.put(DBConstants.HEAT_COLUMN_NAME, placeToLive.getAmenities().getHeat().toString());
        }
        if (placeToLive.getAmenities().getHeatSource() != null)
        {
            contentValues.put(DBConstants.HEAT_SOURCE_COLUMN_NAME, placeToLive.getAmenities().getHeatSource().toString());
        }

        if (placeToLive.getAmenities().getWasherDryer() != null)
        {
            contentValues.put(DBConstants.WASHER_DRYER_COLUMN_NAME, placeToLive.getAmenities().getWasherDryer().toString());
        }

        if (placeToLive.getAmenities().getFirePlace() != null)
        {
            contentValues.put(DBConstants.FIREPLACE_COLUMN_NAME, (placeToLive.getAmenities().getFirePlace() ? 1 : 0));
        }
        if (placeToLive.getAmenities().getGarage() != null)
        {
            contentValues.put(DBConstants.GARAGE_COLUMN_NAME, placeToLive.getAmenities().getGarage().toString());
        }
        if (placeToLive.getAmenities().getAttic() != null)
        {
            contentValues.put(DBConstants.ATTIC_COLUMN_NAME, placeToLive.getAmenities().getAttic().toString());
        }
        if (placeToLive.getAmenities().getBasement() != null)
        {
            contentValues.put(DBConstants.BASEMENT_COLUMN_NAME, placeToLive.getAmenities().getBasement().toString());
        }
        if (placeToLive.getAmenities().getCountertops() != null)
        {
            contentValues.put(DBConstants.COUNTER_TOP_COLUMN_NAME, placeToLive.getAmenities().getCountertops().toString());
        }
        if (placeToLive.getAmenities().getPatio_balcony() != null)
        {
            contentValues.put(DBConstants.PATIO_BALCONY_COLUMN_NAME, (placeToLive.getAmenities().getPatio_balcony() ? 1 : 0));
        }
        if (placeToLive.getComplex().getHasFitnessCenter() != null)
        {
            contentValues.put(DBConstants.FITNESS_CENTER_COLUMN_NAME, (placeToLive.getComplex().getHasFitnessCenter() ? 1 : 0));
        }
        contentValues.put(DBConstants.FITNESS_CENTER_FEE_COLUMN_NAME, placeToLive.getComplex().getFitnessCenterFee());
        if (placeToLive.getComplex().getFitnessCenterFeeTerm() != null)
        {
            contentValues.put(DBConstants.FITNESS_CENTER_FEE_TERM_COLUMN_NAME, placeToLive.getComplex().getFitnessCenterFeeTerm().toString());
        }
        if (placeToLive.getComplex().getHasPool() != null)
        {
            contentValues.put(DBConstants.POOL_COLUMN_NAME, (placeToLive.getComplex().getHasPool() ? 1 : 0));
        }
        contentValues.put(DBConstants.POOL_FEE_COLUMN_NAME, placeToLive.getComplex().getPoolFee());
        if (placeToLive.getComplex().getPoolFeeTerm() != null)
        {
            contentValues.put(DBConstants.POOL_FEE_TERM_COLUMN_NAME, placeToLive.getComplex().getPoolFeeTerm().toString());
        }
        if (placeToLive.getComplex().getOutdoorDogSpace() != null)
        {
            contentValues.put(DBConstants.OUTDOOR_SPACE_COLUMN_NAME, placeToLive.getComplex().getOutdoorDogSpace().toString());
        }

        contentValues.put(DBConstants.PATRICK_WORK_DISTANCE_COLUMN_NAME, placeToLive.getDistances().getPatrickWorkDistance());
        contentValues.put(DBConstants.PATRICK_WORK_TIME_COLUMN_NAME, placeToLive.getDistances().getPatrickWorkTime());

        if (placeToLive.getDistances().getPatrickWorkTolls() != null)
        {
            contentValues.put(DBConstants.PATRICK_WORK_TOLLS_COLUMN_NAME, (placeToLive.getDistances().getPatrickWorkTolls() ? 1 : 0));
        }

        contentValues.put(DBConstants.DANIELLE_CLASS_DISTANCE_DRIVE_COLUMN_NAME, placeToLive.getDistances().getDanielleClassDistanceDrive());
        contentValues.put(DBConstants.DANIELLE_CLASS_TIME_DRIVE_COLUMN_NAME, placeToLive.getDistances().getDanielleClassTimeDrive());
        contentValues.put(DBConstants.DANIELLE_CLASS_TIME_PUBLIC_COLUMN_NAME, placeToLive.getDistances().getDanielleClassTimePublic());
        contentValues.put(DBConstants.SRT_DISTANCE_COLUMN_NAME, placeToLive.getDistances().getSrtDistance());

        contentValues.put(DBConstants.PRICE_COLUMN_NAME, placeToLive.getPrices().getPrice());
        if (placeToLive.getPrices().getTerm() != null)
        {
            contentValues.put(DBConstants.TERM_COLUMN_NAME, placeToLive.getPrices().getTerm().toString());
        }
        contentValues.put(DBConstants.LEASING_PERIOD_COLUMN_NAME, placeToLive.getPrices().getLeasePeriod());
        if (placeToLive.getPrices().getLeasePeriodTerm() != null)
        {
            contentValues.put(DBConstants.LEASING_PERIOD_TERM_COLUMN_NAME, placeToLive.getPrices().getLeasePeriodTerm().toString());
        }
        contentValues.put(DBConstants.SECURITY_DEPOSIT_COLUMN_NAME, placeToLive.getPrices().getSecurityDeposit());
        if (placeToLive.getPrices().getSecurityDepositRefundable() != null)
        {
            contentValues.put(DBConstants.SECURITY_DEPOSIT_REFUNDABLE_COLUMN_NAME, (placeToLive.getPrices().getSecurityDepositRefundable() ? 1 : 0));
        }

        if (placeToLive.getPrices().getSecurityDepositType() != null)
        {
            contentValues.put(DBConstants.SECURITY_DEPOSIT_TYPE, placeToLive.getPrices().getSecurityDepositType().toString());
        }

        if (placeToLive.getPrices().getElectricIncluded() != null)
        {
            contentValues.put(DBConstants.ELECTRIC_INCLUDED_COLUMN_NAME, (placeToLive.getPrices().getElectricIncluded() ? 1 : 0));
        }
        if (placeToLive.getPrices().getSewageIncluded() != null)
        {
            contentValues.put(DBConstants.SEWAGE_INCLUDED_COLUMN_NAME, (placeToLive.getPrices().getSewageIncluded() ? 1 : 0));
        }
        if (placeToLive.getPrices().getWaterIncluded() != null)
        {
            contentValues.put(DBConstants.WATER_INCLUDED_COLUMN_NAME, (placeToLive.getPrices().getWaterIncluded() ? 1 : 0));
        }
        if (placeToLive.getPrices().getGasIncluded() != null)
        {
            contentValues.put(DBConstants.GAS_INCLUDED_COLUMN_NAME, (placeToLive.getPrices().getGasIncluded() ? 1 : 0));
        }
        if (placeToLive.getPrices().getTrashIncluded() != null)
        {
            contentValues.put(DBConstants.TRASH_INCLUDED_COLUMN_NAME, (placeToLive.getPrices().getTrashIncluded() ? 1 : 0));
        }
        contentValues.put(DBConstants.DOG_FEE_COLUMN_NAME, placeToLive.getPrices().getDogFee());
        if (placeToLive.getPrices().getDogFeeTerm() != null)
        {
            contentValues.put(DBConstants.DOG_FEE_TERM_COLUMN_NAME, placeToLive.getPrices().getDogFeeTerm().toString());
        }
        contentValues.put(DBConstants.DOG_FEE_DEPOSIT_COLUMN_NAME, placeToLive.getPrices().getDogFeeDeposit());
        if (placeToLive.getPrices().getDogFeeDepositRefundable() != null)
        {
            contentValues.put(DBConstants.DOG_FEE_DEPOSIT_REFUNDABLE_COLUMN_NAME, (placeToLive.getPrices().getDogFeeDepositRefundable() ? 1 : 0));
        }
        contentValues.put(DBConstants.PLACE_TO_LIVE_NOTES, placeToLive.getPlaceToLiveNotes());

        if (placeToLive.getContact() != null)
        {
            if (placeToLive.getContact().getContactType() != null)
            {
                contentValues.put(DBConstants.CONTACT_TYPE_COLUMN_NAME, placeToLive.getContact().getContactType().toString());
            }

            if (placeToLive.getContact().getContactDate() != null)
            {
                contentValues.put(DBConstants.CONTACT_DATE_COLUMN_NAME, placeToLive.getContact().getContactDate().getTime().getTime());
            }
        }

        //save the item
        SQLiteDatabase db = dbh.getWritableDatabase();
        long returnVal;
        if (currentPlaceToLive == null)
        {
            logger.d("The current place to live was null - inserting a new one");
            returnVal = db.insert(DBConstants.DATABASE_TABLE_NAME, null, contentValues);

        else
        {
            logger.d("The current place to live was not null, updating it");
            returnVal = db.update(DBConstants.DATABASE_TABLE_NAME, contentValues, DBConstants.ID_COLUMN_NAME + " = ?", new String[]{String.valueOf(placeToLive.getId())});

        }
        //and see if it exists
        logger.logExit();
        return (returnVal > 0);
    }

    public void deleteAll()
    {
        logger.logEnter("deleteAll");

        String DELETE_ALL_SQL = "DELETE FROM " + DBConstants.DATABASE_TABLE_NAME + ";";

        SQLiteDatabase db = dbh.getWritableDatabase();

        db.execSQL(DELETE_ALL_SQL);

        logger.logExit();
    }
}
