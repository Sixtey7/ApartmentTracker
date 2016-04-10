package com.patrickwshaw.apartmenttracker.constants;

/**
 * Created by Patrick on 4/4/2015.
 */
public class DBConstants
{
    public static final String DATABASE_NAME = "com_patrickwshaw_apartment_tracker";

    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_TABLE_NAME = "PlaceToLive";

    public static final String ID_COLUMN_NAME = "ID";
    public static final String LAST_UPDATED_COLUMN_NAME = "LAST_UPDATED";
    public static final String PLACE_TO_LIVE_NOTES = "PLACE_TO_LIVE_NOTES";
    public static final String PLACE_TO_LIVE_STATUS = "PLACE_TO_LIVE_STATUS";
    public static final String PLACE_TO_LIVE_STATE_NAME = "PLACE_TO_LIVE_STATE";
    public static final String NAME_COLUMN_NAME = "NAME";
    public static final String STYLE_COLUMN_NAME = "STYLE";
    public static final String NUM_BEDS_COLUMN_NAME = "NUM_BEDS";
    public static final String NUM_BATHS_COLUMN_NAME = "NUM_BATHS";
    public static final String FLOORS_COLUMN_NAME = "FLOORS";
    public static final String SQ_FT_COLUMN_NAME = "SQ_FT";
    public static final String RATING_COLUMN_NAME = "RATING";
    public static final String FIRST_FLOOR_BEDROOM_COLUMN_NAME = "FIRST_FLOOR_BEDROOM";
    public static final String OFFICE_HOURS_COLUMN_NAME = "OFFICE_HOURS";
    public static final String OFFICE_PHONE_NUMBER_AREA_CODE_COLUMN_NAME = "OFFICE_PHONE_NUMBER_AREA_CODE";
    public static final String OFFICE_PHONE_NUMBER_FIRST_COLUMN_NAME = "OFFICE_PHONE_NUMBER_FIRST";
    public static final String OFFICE_PHONE_NUMBER_SECOND_CODE_COLUMN_NAME = "OFFICE_PHONE_NUMBER_SECOND";
    public static final String WEBSITE_COLUMN_NAME = "WEBSITE";

    public static final String STREET_COLUMN_NAME = "STREET";
    public static final String APARTMENT_COLUMN_NAME = "APARTMENT";
    public static final String CITY_COLUMN_NAME = "CITY";
    public static final String STATE_COLUMN_NAME = "STATE";
    public static final String ZIP_COLUMN_NAME = "ZIP";

    public static final String DISHWASHER_COLUMN_NAME = "DISHWASHER";
    public static final String DISPOSAL_COLUMN_NAME = "DISPOSAL";
    public static final String RANGE_COLUMN_NAME = "RANGE";
    public static final String FIOS_COLUMN_NAME = "FIOS";
    public static final String AC_COLUMN_NAME = "AC";
    public static final String HEAT_COLUMN_NAME = "HEAT";
    public static final String HEAT_SOURCE_COLUMN_NAME = "HEAT_SOURCE";
    public static final String WASHER_DRYER_COLUMN_NAME = "WASHER_DRYER";
    public static final String FIREPLACE_COLUMN_NAME = "FIREPLACE";
    public static final String GARAGE_COLUMN_NAME = "GARAGE";
    public static final String ATTIC_COLUMN_NAME = "ATTIC";
    public static final String BASEMENT_COLUMN_NAME = "BASEMENT";
    public static final String COUNTER_TOP_COLUMN_NAME = "COUNTER_TOP";
    public static final String PATIO_BALCONY_COLUMN_NAME = "PATIO_BALCONY";

    public static final String FITNESS_CENTER_COLUMN_NAME = "FITNESS_CENTER";
    public static final String FITNESS_CENTER_FEE_COLUMN_NAME = "FITNESS_CENTER_FEE";
    public static final String FITNESS_CENTER_FEE_TERM_COLUMN_NAME = "FITNESS_CENTER_FEE_TERM";
    public static final String POOL_COLUMN_NAME = "POOL";
    public static final String POOL_FEE_COLUMN_NAME = "POOL_FEE";
    public static final String POOL_FEE_TERM_COLUMN_NAME = "POOL_FEE_TERM";
    public static final String OUTDOOR_SPACE_COLUMN_NAME = "OUTDOOR_SPACE";

    public static final String PATRICK_WORK_DISTANCE_COLUMN_NAME = "PATRICK_WORK_DISTANCE";
    public static final String PATRICK_WORK_TIME_COLUMN_NAME = "PATRICK_WORK_TIME";
    public static final String PATRICK_WORK_TOLLS_COLUMN_NAME = "PATRICK_WORK_TOLLS";
    public static final String DANIELLE_CLASS_DISTANCE_DRIVE_COLUMN_NAME = "DANIELLE_CLASS_DISTANCE_DRIVE";
    public static final String DANIELLE_CLASS_TIME_DRIVE_COLUMN_NAME = "DANIELLE_CLASS_TIME_DRIVE";
    public static final String DANIELLE_CLASS_TIME_PUBLIC_COLUMN_NAME = "DANIELLE_CLASS_TIME_PUBLIC";
    public static final String SRT_DISTANCE_COLUMN_NAME = "SRT_DISTANCE";

    public static final String PRICE_COLUMN_NAME = "PRICE";
    public static final String TERM_COLUMN_NAME = "TERM";
    public static final String LEASING_PERIOD_COLUMN_NAME = "LEASING_PERIOD";
    public static final String LEASING_PERIOD_TERM_COLUMN_NAME = "LEASING_PERIOD_TERM";
    public static final String SECURITY_DEPOSIT_COLUMN_NAME = "SECURITY_DEPOSIT";
    public static final String SECURITY_DEPOSIT_REFUNDABLE_COLUMN_NAME = "SECURITY_DEPOSIT_REFUNDABLE";
    public static final String SECURITY_DEPOSIT_TYPE = "SECURITY_DEPOSIT_TYPE";
    public static final String ELECTRIC_INCLUDED_COLUMN_NAME = "ELECTRIC_INCLUDED";
    public static final String SEWAGE_INCLUDED_COLUMN_NAME = "SEWAGE_INCLUDED";
    public static final String WATER_INCLUDED_COLUMN_NAME = "WATER_INCLUDED";
    public static final String GAS_INCLUDED_COLUMN_NAME = "GAS_INCLUDED";
    public static final String TRASH_INCLUDED_COLUMN_NAME = "TRASH_INCLUDED";
    public static final String DOG_FEE_COLUMN_NAME = "DOG_FEE";
    public static final String DOG_FEE_TERM_COLUMN_NAME = "DOG_FEE_TERM";
    public static final String DOG_FEE_DEPOSIT_COLUMN_NAME = "DOG_FEE_DEPOSIT";
    public static final String DOG_FEE_DEPOSIT_REFUNDABLE_COLUMN_NAME = "DOG_FEE_DEPOSIT_REFUNDABLE";

    public static final String CONTACT_TYPE_COLUMN_NAME = "CONTACT";
    public static final String CONTACT_DATE_COLUMN_NAME = "CONTACT_DATE";


    public static String createTableCreateText() {

        return "CREATE TABLE " + DATABASE_TABLE_NAME + " (" +
                ID_COLUMN_NAME + " INTEGER PRIMARY KEY, " +
                LAST_UPDATED_COLUMN_NAME + " REAL, " +
                PLACE_TO_LIVE_NOTES + " TEXT," +
                PLACE_TO_LIVE_STATUS + " TEXT," +
                PLACE_TO_LIVE_STATE_NAME + " TEXT, " +
                NAME_COLUMN_NAME + " TEXT, " +
                STYLE_COLUMN_NAME + " TEXT, " +
                NUM_BATHS_COLUMN_NAME + " REAL, " +
                NUM_BEDS_COLUMN_NAME + " REAL, " +
                FLOORS_COLUMN_NAME + " INTEGER, " +
                SQ_FT_COLUMN_NAME + " REAL, " +
                RATING_COLUMN_NAME + " REAL, " +
                FIRST_FLOOR_BEDROOM_COLUMN_NAME + " INTEGER, " +
                OFFICE_HOURS_COLUMN_NAME + " TEXT, " +
                OFFICE_PHONE_NUMBER_AREA_CODE_COLUMN_NAME + " TEXT, " +
                OFFICE_PHONE_NUMBER_FIRST_COLUMN_NAME + " TEXT, " +
                OFFICE_PHONE_NUMBER_SECOND_CODE_COLUMN_NAME + " TEXT, " +
                WEBSITE_COLUMN_NAME + " TEXT, " +
                STREET_COLUMN_NAME + " TEXT, " +
                APARTMENT_COLUMN_NAME + " TEXT, " +
                CITY_COLUMN_NAME + " TEXT, " +
                STATE_COLUMN_NAME + " TEXT, " +
                ZIP_COLUMN_NAME + " INTEGER, " +
                DISHWASHER_COLUMN_NAME + " INTEGER, " +
                DISPOSAL_COLUMN_NAME + " INTEGER, " +
                RANGE_COLUMN_NAME + " TEXT, " +
                FIOS_COLUMN_NAME + " INTEGER, " +
                AC_COLUMN_NAME + " TEXT, " +
                HEAT_COLUMN_NAME + " TEXT, " +
                HEAT_SOURCE_COLUMN_NAME + " TEXT, " +
                WASHER_DRYER_COLUMN_NAME + " TEXT," +
                FIREPLACE_COLUMN_NAME + " INTEGER, " +
                GARAGE_COLUMN_NAME + " TEXT, " +
                ATTIC_COLUMN_NAME + " TEXT, " +
                BASEMENT_COLUMN_NAME + " TEXT, " +
                COUNTER_TOP_COLUMN_NAME + " TEXT, " +
                PATIO_BALCONY_COLUMN_NAME + " INTEGER, " +
                FITNESS_CENTER_COLUMN_NAME + " INTEGER, " +
                FITNESS_CENTER_FEE_COLUMN_NAME + " INTEGER, " +
                FITNESS_CENTER_FEE_TERM_COLUMN_NAME + " TEXT, " +
                POOL_COLUMN_NAME + " INTEGER, " +
                POOL_FEE_COLUMN_NAME + " INTEGER, " +
                POOL_FEE_TERM_COLUMN_NAME + " TEXT, " +
                OUTDOOR_SPACE_COLUMN_NAME + " TEXT, " +
                PATRICK_WORK_DISTANCE_COLUMN_NAME + " INTEGER, " +
                PATRICK_WORK_TIME_COLUMN_NAME + " INTEGER, " +
                PATRICK_WORK_TOLLS_COLUMN_NAME + " INTEGER, " +
                DANIELLE_CLASS_DISTANCE_DRIVE_COLUMN_NAME + " INTEGER, " +
                DANIELLE_CLASS_TIME_DRIVE_COLUMN_NAME + " INTEGER, " +
                DANIELLE_CLASS_TIME_PUBLIC_COLUMN_NAME + " INTEGER, " +
                SRT_DISTANCE_COLUMN_NAME + " REAL, " +
                PRICE_COLUMN_NAME + " REAL, " +
                TERM_COLUMN_NAME + " TEXT, " +
                LEASING_PERIOD_COLUMN_NAME + " INTEGER, " +
                LEASING_PERIOD_TERM_COLUMN_NAME + " TEXT, " +
                SECURITY_DEPOSIT_COLUMN_NAME + " REAL, " +
                SECURITY_DEPOSIT_REFUNDABLE_COLUMN_NAME + " INTEGER, " +
                SECURITY_DEPOSIT_TYPE + " TEXT," +
                ELECTRIC_INCLUDED_COLUMN_NAME + " INTEGER, " +
                GAS_INCLUDED_COLUMN_NAME + " INTEGER, " +
                TRASH_INCLUDED_COLUMN_NAME + " INTEGER, " +
                WATER_INCLUDED_COLUMN_NAME + " INTEGER, " +
                SEWAGE_INCLUDED_COLUMN_NAME + " INTEGER, " +
                DOG_FEE_COLUMN_NAME + " REAL, " +
                DOG_FEE_TERM_COLUMN_NAME + " TEXT, " +
                DOG_FEE_DEPOSIT_COLUMN_NAME + " REAL, " +
                DOG_FEE_DEPOSIT_REFUNDABLE_COLUMN_NAME + " INTEGER, " +
                CONTACT_TYPE_COLUMN_NAME + " TEXT, " +
                CONTACT_DATE_COLUMN_NAME + " REAL " +
                ");";
    }

    public static String createDropTableText()
    {
        return "DROP TABLE IF EXISTS " + DATABASE_TABLE_NAME + ";";
    }
}
