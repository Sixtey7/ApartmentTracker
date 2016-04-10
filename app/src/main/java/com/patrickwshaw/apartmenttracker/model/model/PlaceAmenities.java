package com.patrickwshaw.apartmenttracker.model.model;

import android.database.Cursor;

import com.patrickwshaw.apartmenttracker.constants.DBConstants;
import com.patrickwshaw.apartmenttracker.constants.LivingConstants;
import com.patrickwshaw.apartmenttracker.utility.LoggingUtil;

import java.io.Serializable;

/**
 * Created by Patrick on 3/26/2015.
 */
public class PlaceAmenities implements Serializable
{
    private final LoggingUtil logger = new LoggingUtil("PlaceAmenities", "PlaceAmenities");

    private Boolean hasDishwasher;
    private Boolean hasDisposal;
    private LivingConstants.rangeTypes range;
    private Boolean hasFios;
    private LivingConstants.acTypes ac;
    private LivingConstants.heatSources heatSource;
    private LivingConstants.heatTypes heat;
    private LivingConstants.washerDryerTypes washerDryer;
    private Boolean firePlace;
    private LivingConstants.garageTypes garage;
    private LivingConstants.atticBasementTypes attic;
    private LivingConstants.atticBasementTypes basement;
    private LivingConstants.countertopTypes countertops;
    private Boolean patio_balcony;

    public PlaceAmenities()
    {
        logger.logEnter("constructor(no args)");
        //default constructor
        logger.logExit();
    }

    public PlaceAmenities(Cursor cursor)
    {
        logger.logEnter("constructor(cursor)");

        int booleanVal = 0;

        booleanVal = cursor.getInt(cursor.getColumnIndex(DBConstants.DISHWASHER_COLUMN_NAME));
        this.hasDishwasher = (booleanVal == 1);

        booleanVal = cursor.getInt(cursor.getColumnIndex(DBConstants.DISPOSAL_COLUMN_NAME));
        this.hasDisposal = (booleanVal == 1);

        this.range = LivingConstants.rangeTypes.fromStringVal(cursor.getString(cursor.getColumnIndex(DBConstants.RANGE_COLUMN_NAME)));

        booleanVal = cursor.getInt(cursor.getColumnIndex(DBConstants.FIOS_COLUMN_NAME));
        this.hasFios = (booleanVal == 1);

        this.ac = LivingConstants.acTypes.fromStringVal(cursor.getString(cursor.getColumnIndex(DBConstants.AC_COLUMN_NAME)));
        this.heat = LivingConstants.heatTypes.fromStringVal(cursor.getString(cursor.getColumnIndex(DBConstants.HEAT_COLUMN_NAME)));
        this.heatSource = LivingConstants.heatSources.fromStringVal(cursor.getString(cursor.getColumnIndex(DBConstants.HEAT_SOURCE_COLUMN_NAME)));
        this.washerDryer = LivingConstants.washerDryerTypes.fromStringVal(cursor.getString(cursor.getColumnIndex(DBConstants.WASHER_DRYER_COLUMN_NAME)));

        booleanVal = cursor.getInt(cursor.getColumnIndex(DBConstants.FIREPLACE_COLUMN_NAME));
        this.firePlace = (booleanVal == 1);

        this.garage = LivingConstants.garageTypes.fromStringVal(cursor.getString(cursor.getColumnIndex(DBConstants.GARAGE_COLUMN_NAME)));
        this.attic = LivingConstants.atticBasementTypes.fromStringVal(cursor.getString(cursor.getColumnIndex(DBConstants.ATTIC_COLUMN_NAME)));
        this.basement = LivingConstants.atticBasementTypes.fromStringVal(cursor.getString(cursor.getColumnIndex(DBConstants.BASEMENT_COLUMN_NAME)));
        this.countertops = LivingConstants.countertopTypes.fromStringVal(cursor.getString(cursor.getColumnIndex(DBConstants.COUNTER_TOP_COLUMN_NAME)));

        booleanVal = cursor.getInt(cursor.getColumnIndex(DBConstants.PATIO_BALCONY_COLUMN_NAME));
        this.patio_balcony = (booleanVal == 1);

        logger.logExit();
    }

    public Boolean getHasDishwasher() {
        return hasDishwasher;
    }

    public void setHasDishwasher(Boolean hasDishwasher) {
        this.hasDishwasher = hasDishwasher;
    }

    public Boolean getHasDisposal() {
        return hasDisposal;
    }

    public void setHasDisposal(Boolean hasDisposal) {
        this.hasDisposal = hasDisposal;
    }

    public LivingConstants.rangeTypes getRange() {
        return range;
    }

    public void setRange(LivingConstants.rangeTypes range) {
        this.range = range;
    }

    public Boolean getHasFios() {
        return hasFios;
    }

    public void setHasFios(Boolean hasFios) {
        this.hasFios = hasFios;
    }

    public LivingConstants.acTypes getAc() {
        return ac;
    }

    public void setAc(LivingConstants.acTypes ac) {
        this.ac = ac;
    }

    public LivingConstants.heatSources getHeatSource() {
        return heatSource;
    }

    public void setHeatSource(LivingConstants.heatSources heatSource) {
        this.heatSource = heatSource;
    }

    public LivingConstants.heatTypes getHeat() {
        return heat;
    }

    public void setHeat(LivingConstants.heatTypes heat) {
        this.heat = heat;
    }

    public LivingConstants.washerDryerTypes getWasherDryer() { return washerDryer; }

    public void setWasherDryer(LivingConstants.washerDryerTypes washerDryer)
    {
        this.washerDryer = washerDryer;
    }

    public Boolean getFirePlace() {
        return firePlace;
    }

    public void setFirePlace(Boolean firePlace) {
        this.firePlace = firePlace;
    }

    public LivingConstants.garageTypes getGarage() {
        return garage;
    }

    public void setGarage(LivingConstants.garageTypes garage) {
        this.garage = garage;
    }

    public LivingConstants.atticBasementTypes getAttic() {
        return attic;
    }

    public void setAttic(LivingConstants.atticBasementTypes attic) {
        this.attic = attic;
    }

    public LivingConstants.atticBasementTypes getBasement() {
        return basement;
    }

    public void setBasement(LivingConstants.atticBasementTypes basement) {
        this.basement = basement;
    }

    public LivingConstants.countertopTypes getCountertops() {
        return countertops;
    }

    public void setCountertops(LivingConstants.countertopTypes countertops) {
        this.countertops = countertops;
    }

    public Boolean getPatio_balcony() {
        return patio_balcony;
    }

    public void setPatio_balcony(Boolean patio_balcony) {
        this.patio_balcony = patio_balcony;
    }
}
