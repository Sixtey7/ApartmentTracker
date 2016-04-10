package com.patrickwshaw.apartmenttracker.model.model;

import android.database.Cursor;

import com.patrickwshaw.apartmenttracker.constants.DBConstants;
import com.patrickwshaw.apartmenttracker.constants.LivingConstants;
import com.patrickwshaw.apartmenttracker.utility.LoggingUtil;

import java.io.Serializable;

/**
 * Created by Patrick on 3/26/2015.
 */
public class PlacePrices implements Serializable
{
    private final LoggingUtil logger = new LoggingUtil("PlacePrices", "PlacePrices");

    private Float price;
    private LivingConstants.termTypes term;
    private Float leasePeriod;
    private LivingConstants.termTypes leasePeriodTerm;

    private Float securityDeposit;
    private Boolean securityDepositRefundable;
    private LivingConstants.securityDepositTypes securityDepositType;

    private Boolean trashIncluded;
    private Boolean sewageIncluded;
    private Boolean gasIncluded;
    private Boolean waterIncluded;
    private Boolean electricIncluded;

    private Float dogFee;
    private LivingConstants.termTypes dogFeeTerm;
    private Float dogFeeDeposit;
    private Boolean dogFeeDepositRefundable;

    public PlacePrices()
    {
        logger.logEnter("constructor(no args)");
        //default constructor
        logger.logExit();
    }

    public PlacePrices(Cursor cursor)
    {
        logger.logEnter("constructor(Cursor)");

        int booleanVal = 0;
        int columnIndex = 0;

        columnIndex = cursor.getColumnIndex(DBConstants.PRICE_COLUMN_NAME);
        if (cursor.isNull(columnIndex))
        {
            this.price = null;
        }
        else
        {
            this.price = cursor.getFloat(columnIndex);
        }

        columnIndex = cursor.getColumnIndex(DBConstants.TERM_COLUMN_NAME);
        if (cursor.isNull(columnIndex))
        {
            this.term = null;
        }
        else
        {
            this.term = LivingConstants.termTypes.fromStringVal(cursor.getString(columnIndex));
        }

        columnIndex = cursor.getColumnIndex(DBConstants.LEASING_PERIOD_COLUMN_NAME);
        if (cursor.isNull(columnIndex))
        {
            this.leasePeriod = null;
        }
        else
        {
            this.leasePeriod = cursor.getFloat(columnIndex);
        }

        this.leasePeriodTerm = LivingConstants.termTypes.fromStringVal(cursor.getString(cursor.getColumnIndex(DBConstants.LEASING_PERIOD_TERM_COLUMN_NAME)));

        columnIndex = cursor.getColumnIndex(DBConstants.SECURITY_DEPOSIT_COLUMN_NAME);
        if (cursor.isNull(columnIndex))
        {
            this.securityDeposit = null;
        }
        else
        {
            this.securityDeposit = cursor.getFloat(cursor.getColumnIndex(DBConstants.SECURITY_DEPOSIT_COLUMN_NAME));
        }

        booleanVal = cursor.getInt(cursor.getColumnIndex(DBConstants.SECURITY_DEPOSIT_REFUNDABLE_COLUMN_NAME));
        this.securityDepositRefundable = (booleanVal == 1);

        this.securityDepositType = LivingConstants.securityDepositTypes.fromStringVal(cursor.getString(cursor.getColumnIndex(DBConstants.SECURITY_DEPOSIT_TYPE)));

        booleanVal = cursor.getInt(cursor.getColumnIndex(DBConstants.TRASH_INCLUDED_COLUMN_NAME));
        this.trashIncluded = (booleanVal == 1);

        booleanVal = cursor.getInt(cursor.getColumnIndex(DBConstants.WATER_INCLUDED_COLUMN_NAME));
        this.waterIncluded = (booleanVal == 1);

        booleanVal = cursor.getInt(cursor.getColumnIndex(DBConstants.SEWAGE_INCLUDED_COLUMN_NAME));
        this.sewageIncluded = (booleanVal == 1);

        booleanVal = cursor.getInt(cursor.getColumnIndex(DBConstants.ELECTRIC_INCLUDED_COLUMN_NAME));
        this.electricIncluded = (booleanVal == 1);

        booleanVal = cursor.getInt(cursor.getColumnIndex(DBConstants.GAS_INCLUDED_COLUMN_NAME));
        this.gasIncluded = (booleanVal == 1);


        columnIndex = cursor.getColumnIndex(DBConstants.DOG_FEE_COLUMN_NAME);
        if (cursor.isNull(columnIndex))
        {
            this.dogFee = null;
        }
        else
        {
            this.dogFee = cursor.getFloat(columnIndex);
        }

        this.dogFeeTerm = LivingConstants.termTypes.fromStringVal(cursor.getString(cursor.getColumnIndex(DBConstants.DOG_FEE_TERM_COLUMN_NAME)));

        columnIndex = cursor.getColumnIndex(DBConstants.DOG_FEE_DEPOSIT_COLUMN_NAME);
        if (cursor.isNull(columnIndex))
        {
            this.dogFeeDeposit = null;
        }
        else
        {
            this.dogFeeDeposit = cursor.getFloat(columnIndex);
        }

        booleanVal = cursor.getInt(cursor.getColumnIndex(DBConstants.DOG_FEE_DEPOSIT_REFUNDABLE_COLUMN_NAME));
        this.dogFeeDepositRefundable = (booleanVal == 1);

        logger.logExit();
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public LivingConstants.termTypes getTerm() {
        return term;
    }

    public void setTerm(LivingConstants.termTypes term) {
        this.term = term;
    }

    public Float getLeasePeriod() { return leasePeriod; }

    public void setLeasePeriod(Float leasePeriod)
    {
        this.leasePeriod = leasePeriod;
    }

    public LivingConstants.termTypes getLeasePeriodTerm() { return leasePeriodTerm; }

    public void setLeasePeriodTerm(LivingConstants.termTypes leasePeriodTerm)
    {
        this.leasePeriodTerm = leasePeriodTerm;
    }

    public Float getSecurityDeposit() {
        return securityDeposit;
    }

    public void setSecurityDeposit(Float securityDeposit) {
        this.securityDeposit = securityDeposit;
    }

    public Boolean getSecurityDepositRefundable() {
        return securityDepositRefundable;
    }

    public void setSecurityDepositRefundable(Boolean securityDepositRefundable) {
        this.securityDepositRefundable = securityDepositRefundable;
    }

    public LivingConstants.securityDepositTypes getSecurityDepositType() { return securityDepositType; }

    public void setSecurityDepositType(LivingConstants.securityDepositTypes securityDepositType) { this.securityDepositType = securityDepositType; }

    public Boolean getTrashIncluded() {
        return trashIncluded;
    }

    public void setTrashIncluded(Boolean trashIncluded) {
        this.trashIncluded = trashIncluded;
    }

    public Boolean getSewageIncluded() {
        return sewageIncluded;
    }

    public void setSewageIncluded(Boolean sewageIncluded) {
        this.sewageIncluded = sewageIncluded;
    }

    public Boolean getGasIncluded() {
        return gasIncluded;
    }

    public void setGasIncluded(Boolean gasIncluded) {
        this.gasIncluded = gasIncluded;
    }

    public Boolean getWaterIncluded() {
        return waterIncluded;
    }

    public void setWaterIncluded(Boolean waterIncluded) {
        this.waterIncluded = waterIncluded;
    }

    public Boolean getElectricIncluded() {
        return electricIncluded;
    }

    public void setElectricIncluded(Boolean electricIncluded) {
        this.electricIncluded = electricIncluded;
    }

    public Float getDogFee() {
        return dogFee;
    }

    public void setDogFee(Float dogFee) {
        this.dogFee = dogFee;
    }

    public LivingConstants.termTypes getDogFeeTerm() {
        return dogFeeTerm;
    }

    public void setDogFeeTerm(LivingConstants.termTypes dogFeeTerm) {
        this.dogFeeTerm = dogFeeTerm;
    }

    public Float getDogFeeDeposit() {
        return dogFeeDeposit;
    }

    public void setDogFeeDeposit(Float dogFeeDeposit) {
        this.dogFeeDeposit = dogFeeDeposit;
    }

    public Boolean getDogFeeDepositRefundable() {
        return dogFeeDepositRefundable;
    }

    public void setDogFeeDepositRefundable(Boolean dogFeeDepositRefundable) {
        this.dogFeeDepositRefundable = dogFeeDepositRefundable;
    }
}
