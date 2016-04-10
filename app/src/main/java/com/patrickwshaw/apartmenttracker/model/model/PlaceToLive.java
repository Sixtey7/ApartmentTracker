package com.patrickwshaw.apartmenttracker.model.model;

import android.database.Cursor;
import android.provider.CalendarContract;

import com.patrickwshaw.apartmenttracker.constants.DBConstants;
import com.patrickwshaw.apartmenttracker.constants.LivingConstants;
import com.patrickwshaw.apartmenttracker.model.database.dao.LivingDao;
import com.patrickwshaw.apartmenttracker.utility.LoggingUtil;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Patrick on 3/26/2015.
 */
public class PlaceToLive implements Serializable
{
    private final LoggingUtil logger = new LoggingUtil("PlaceToLive", "PlaceToLive");

    private Integer id;
    private Calendar lastUpdated;
    private String placeToLiveNotes;
    private LivingConstants.placeToLiveStatus status;
    private String name;
    private LivingConstants.housingTypes type;
    private Float numBeds;
    private Float numBaths;
    private Integer numFloors;
    private Float sqFt;
    private Float rating;
    private LivingConstants.firstFloorBedroomTypes firstFloorBedroom;
    private String officeHours;
    private String website;

    //includes other objects
    private PlaceAddress address;
    private PlacePhoneNumber officeNumber;
    private PlaceAmenities amenities;
    private PlaceComplex complex;
    private PlaceDistances distances;
    private PlacePrices prices;
    private PlaceContact contact;

    public PlaceToLive()
    {
        logger.logEnter("constructor(no args)");
        //default constructor
        this.address = new PlaceAddress();
        this.officeNumber = new PlacePhoneNumber();
        this.amenities = new PlaceAmenities();
        this.complex = new PlaceComplex();
        this.distances = new PlaceDistances();
        this.prices = new PlacePrices();
        this.contact = new PlaceContact();
        logger.logExit();
    }

    public PlaceToLive(String name)
    {
        logger.logEnter("constructor(string)");
        this.name = name;

        this.address = new PlaceAddress();
        this.officeNumber = new PlacePhoneNumber();
        this.amenities = new PlaceAmenities();
        this.complex = new PlaceComplex();
        this.distances = new PlaceDistances();
        this.prices = new PlacePrices();
        this.contact = new PlaceContact();
        logger.logExit();
    }

    public PlaceToLive(Cursor cursor)
    {
        logger.logEnter("constructor(Cursor)");

        this.id = cursor.getInt(cursor.getColumnIndex(DBConstants.ID_COLUMN_NAME));
        this.status = LivingConstants.placeToLiveStatus.fromStringVal(cursor.getString(cursor.getColumnIndex(DBConstants.PLACE_TO_LIVE_STATE_NAME)));
        this.name = cursor.getString(cursor.getColumnIndex(DBConstants.NAME_COLUMN_NAME));
        this.placeToLiveNotes = cursor.getString(cursor.getColumnIndex(DBConstants.PLACE_TO_LIVE_NOTES));
        this.type = LivingConstants.housingTypes.fromStringVal(cursor.getString(cursor.getColumnIndex(DBConstants.STYLE_COLUMN_NAME)));

        int columnIndex = cursor.getColumnIndex(DBConstants.NUM_BEDS_COLUMN_NAME);
        if (cursor.isNull(columnIndex))
        {
            this.numBeds = null;
        }
        else
        {
            this.numBeds = cursor.getFloat(columnIndex);
        }

        columnIndex = cursor.getColumnIndex(DBConstants.NUM_BATHS_COLUMN_NAME);
        if (cursor.isNull(columnIndex))
        {
            this.numBaths = null;
        }
        else
        {
            this.numBaths = cursor.getFloat(columnIndex);
        }

        columnIndex = cursor.getColumnIndex(DBConstants.FLOORS_COLUMN_NAME);
        if (cursor.isNull(columnIndex))
        {
            this.numFloors = null;
        }
        else
        {
            this.numFloors = cursor.getInt(columnIndex);
        }

        columnIndex = cursor.getColumnIndex(DBConstants.SQ_FT_COLUMN_NAME);
        if (cursor.isNull(columnIndex))
        {
            this.sqFt = null;
        }
        else
        {
            this.sqFt = cursor.getFloat(columnIndex);
        }

        columnIndex = cursor.getColumnIndex(DBConstants.RATING_COLUMN_NAME);
        if (cursor.isNull(columnIndex))
        {
            this.rating = null;
        }
        else
        {
            this.rating = cursor.getFloat(columnIndex);
        }
        this.firstFloorBedroom = LivingConstants.firstFloorBedroomTypes.fromStringVal(cursor.getString(cursor.getColumnIndex(DBConstants.FIRST_FLOOR_BEDROOM_COLUMN_NAME)));
        this.officeHours = cursor.getString(cursor.getColumnIndex(DBConstants.OFFICE_HOURS_COLUMN_NAME));
        this.website = cursor.getString(cursor.getColumnIndex(DBConstants.WEBSITE_COLUMN_NAME));

        this.address = new PlaceAddress(cursor);
        this.officeNumber = new PlacePhoneNumber(cursor);
        this.amenities = new PlaceAmenities(cursor);
        this.complex = new PlaceComplex(cursor);
        this.distances = new PlaceDistances(cursor);
        this.prices = new PlacePrices(cursor);
        this.contact = new PlaceContact(cursor);

        Calendar lastUpdated = Calendar.getInstance();
        Date lastUpdatedDate = new Date();
        lastUpdatedDate.setTime(cursor.getLong(cursor.getColumnIndex(DBConstants.LAST_UPDATED_COLUMN_NAME)));
        lastUpdated.setTime(lastUpdatedDate);
        this.setLastUpdated(lastUpdated);
        logger.logExit();
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public LivingConstants.placeToLiveStatus getStatus() { return status; }

    public void setStatus(LivingConstants.placeToLiveStatus status) { this.status = status; }

    public String getPlaceToLiveNotes() { return placeToLiveNotes; }

    public void setPlaceToLiveNotes(String placeToLiveNotes) { this.placeToLiveNotes = placeToLiveNotes; }

    public Calendar getLastUpdated() { return this.lastUpdated; }

    public void setLastUpdated(Calendar lastUpdated) { this.lastUpdated = lastUpdated; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LivingConstants.housingTypes getType() {
        return type;
    }

    public void setType(LivingConstants.housingTypes type) {
        this.type = type;
    }

    public Float getNumBeds() {
        return numBeds;
    }

    public void setNumBeds(Float numBeds) {
        this.numBeds = numBeds;
    }

    public Float getNumBaths() {
        return numBaths;
    }

    public void setNumBaths(Float numBaths) {
        this.numBaths = numBaths;
    }

    public Integer getNumFloors() {
        return numFloors;
    }

    public void setNumFloors(Integer numFloors) {
        this.numFloors = numFloors;
    }

    public Float getSqFt() {
        return sqFt;
    }

    public void setSqFt(Float sqFt) {
        this.sqFt = sqFt;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public LivingConstants.firstFloorBedroomTypes getFirstFloorBedroom() {
        return firstFloorBedroom;
    }

    public void setFirstFloorBedroom(LivingConstants.firstFloorBedroomTypes firstFloorBedroom) {
        this.firstFloorBedroom = firstFloorBedroom;
    }

    public String getOfficeHours() {
        return officeHours;
    }

    public void setOfficeHours(String officeHours) {
        this.officeHours = officeHours;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public PlaceAddress getAddress() {
        return address;
    }

    public void setAddress(PlaceAddress address) {
        this.address = address;
    }

    public PlacePhoneNumber getOfficeNumber() {
        return officeNumber;
    }

    public void setOfficeNumber(PlacePhoneNumber officeNumber) {
        this.officeNumber = officeNumber;
    }

    public PlaceAmenities getAmenities() {
        return amenities;
    }

    public void setAmenities(PlaceAmenities amenities) {
        this.amenities = amenities;
    }

    public PlaceComplex getComplex() {
        return complex;
    }

    public void setComplex(PlaceComplex complex) {
        this.complex = complex;
    }

    public PlaceDistances getDistances() {
        return distances;
    }

    public void setDistances(PlaceDistances distances) {
        this.distances = distances;
    }

    public PlacePrices getPrices() {
        return prices;
    }

    public void setPrices(PlacePrices prices) {
        this.prices = prices;
    }

    public PlaceContact getContact() { return contact; }

    public void setContact(PlaceContact contact) { this.contact = contact; }
}
