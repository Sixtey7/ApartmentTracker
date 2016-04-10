package com.patrickwshaw.apartmenttracker.utility;

import com.patrickwshaw.apartmenttracker.constants.DBConstants;
import com.patrickwshaw.apartmenttracker.constants.JSONConstants;
import com.patrickwshaw.apartmenttracker.constants.LivingConstants;
import com.patrickwshaw.apartmenttracker.model.model.PlacePhoneNumber;
import com.patrickwshaw.apartmenttracker.model.model.PlaceToLive;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Patrick on 4/15/2015.
 */
public class JSONUtil
{
    private static final LoggingUtil logger = new LoggingUtil("JSONUtil", "JSONUtil");
    private static final String ISODateFormat = "yyyy-MM-dd'T'HH:mm:ss.SS";
    private static SimpleDateFormat sdf = new SimpleDateFormat(ISODateFormat);

    private static JSONObject placeToLiveToJSON(PlaceToLive placeToLive) throws JSONException
    {
        logger.logEnter("placeToLiveToJSON");

        JSONObject returnObject = new JSONObject();

        returnObject.put(JSONConstants.ID_JSON_NAME, placeToLive.getId());
        returnObject.put(JSONConstants.LAST_UPDATED_JSON_NAME, sdf.format(placeToLive.getLastUpdated().getTime()));

        if (placeToLive.getStatus() != null)
        {
            returnObject.put(JSONConstants.PLACE_TO_LIVE_STATE_NAME, placeToLive.getStatus());
        }

        if (placeToLive.getName() != null)
        {
            returnObject.put(JSONConstants.NAME_JSON_NAME, placeToLive.getName());
        }

        if (placeToLive.getPlaceToLiveNotes() != null)
        {
            returnObject.put(JSONConstants.PLACE_TO_LIVE_NOTES, placeToLive.getPlaceToLiveNotes());
        }

        if (placeToLive.getStatus() != null)
        {
            returnObject.put(JSONConstants.PLACE_TO_LIVE_STATUS, placeToLive.getStatus().toString());
        }

        if (placeToLive.getType() != null)
        {
            returnObject.put(JSONConstants.STYLE_JSON_NAME, placeToLive.getType().toString());
        }

        if (placeToLive.getNumBeds() != null)
        {
            returnObject.put(JSONConstants.NUM_BEDS_JSON_NAME, placeToLive.getNumBeds());
        }

        if (placeToLive.getNumBaths() != null)
        {
            returnObject.put(JSONConstants.NUM_BATHS_JSON_NAME, placeToLive.getNumBaths());
        }

        if (placeToLive.getNumFloors() != null)
        {
            returnObject.put(JSONConstants.FLOORS_JSON_NAME, placeToLive.getNumFloors());
        }

        if (placeToLive.getSqFt() != null)
        {
            returnObject.put(JSONConstants.SQ_FT_JSON_NAME, placeToLive.getSqFt());
        }

        if (placeToLive.getRating() != null)
        {
            returnObject.put(JSONConstants.RATING_JSON_NAME, placeToLive.getRating());
        }

        if (placeToLive.getFirstFloorBedroom() != null)
        {
            returnObject.put(JSONConstants.FIRST_FLOOR_BEDROOM_JSON_NAME, placeToLive.getFirstFloorBedroom().toString());
        }

        if (placeToLive.getOfficeHours() != null)
        {
            returnObject.put(JSONConstants.OFFICE_HOURS_JSON_NAME, placeToLive.getOfficeHours());
        }

        if (placeToLive.getOfficeNumber() != null)
        {
            returnObject.put(JSONConstants.OFFICE_PHONE_NUMBER_JSON_NAME, placeToLive.getOfficeNumber().toString());
        }

        if (placeToLive.getWebsite() != null)
        {
            returnObject.put(JSONConstants.WEBSITE_JSON_NAME, placeToLive.getWebsite());
        }

        if (placeToLive.getAddress() != null)
        {
            if (placeToLive.getAddress().getStreet() != null)
            {
                returnObject.put(JSONConstants.STREET_JSON_NAME, placeToLive.getAddress().getStreet());
            }

            if (placeToLive.getAddress().getApartmentNo() != null)
            {
                returnObject.put(JSONConstants.APARTMENT_JSON_NAME, placeToLive.getAddress().getApartmentNo());
            }

            if (placeToLive.getAddress().getCity() != null)
            {
                returnObject.put(JSONConstants.CITY_JSON_NAME, placeToLive.getAddress().getCity());
            }

            if (placeToLive.getAddress().getState() != null)
            {
                returnObject.put(JSONConstants.STATE_JSON_NAME, placeToLive.getAddress().getState());
            }

            if (placeToLive.getAddress().getZipCode() != null)
            {
                returnObject.put(JSONConstants.ZIP_JSON_NAME, placeToLive.getAddress().getZipCode());
            }
        }

        if (placeToLive.getAmenities() != null)
        {
            if (placeToLive.getAmenities().getHasDishwasher() != null)
            {
                returnObject.put(JSONConstants.DISHWASHER_JSON_NAME, placeToLive.getAmenities().getHasDishwasher());
            }

            if (placeToLive.getAmenities().getHasDisposal() != null)
            {
                returnObject.put(JSONConstants.DISPOSAL_JSON_NAME, placeToLive.getAmenities().getHasDisposal());
            }

            if (placeToLive.getAmenities().getRange() != null)
            {
                returnObject.put(JSONConstants.RANGE_JSON_NAME, placeToLive.getAmenities().getRange().toString());
            }

            if (placeToLive.getAmenities().getHasDishwasher() != null)
            {
                returnObject.put(JSONConstants.DISHWASHER_JSON_NAME, placeToLive.getAmenities().getHasDishwasher());
            }

            if (placeToLive.getAmenities().getHasFios() != null)
            {
                returnObject.put(JSONConstants.FIOS_JSON_NAME, placeToLive.getAmenities().getHasFios());
            }

            if (placeToLive.getAmenities().getAc() != null)
            {
                returnObject.put(JSONConstants.AC_JSON_NAME, placeToLive.getAmenities().getAc().toString());
            }

            if (placeToLive.getAmenities().getHeat() != null)
            {
                returnObject.put(JSONConstants.HEAT_JSON_NAME, placeToLive.getAmenities().getHeat().toString());
            }

            if (placeToLive.getAmenities().getHeatSource() != null)
            {
                returnObject.put(JSONConstants.HEAT_SOURCE_JSON_NAME, placeToLive.getAmenities().getHeatSource().toString());
            }

            if (placeToLive.getAmenities().getWasherDryer() != null)
            {
                returnObject.put(JSONConstants.WASHER_DRYER_JSON_NAME, placeToLive.getAmenities().getWasherDryer().toString());
            }

            if (placeToLive.getAmenities().getFirePlace() != null)
            {
                returnObject.put(JSONConstants.FIREPLACE_JSON_NAME, placeToLive.getAmenities().getFirePlace().toString());
            }

            if (placeToLive.getAmenities().getGarage() != null)
            {
                returnObject.put(JSONConstants.GARAGE_JSON_NAME, placeToLive.getAmenities().getGarage().toString());
            }

            if (placeToLive.getAmenities().getAttic() != null)
            {
                returnObject.put(JSONConstants.ATTIC_JSON_NAME, placeToLive.getAmenities().getAttic().toString());
            }

            if (placeToLive.getAmenities().getBasement() != null)
            {
                returnObject.put(JSONConstants.BASEMENT_JSON_NAME, placeToLive.getAmenities().getBasement().toString());
            }

            if (placeToLive.getAmenities().getCountertops() != null)
            {
                returnObject.put(JSONConstants.COUNTER_TOP_JSON_NAME, placeToLive.getAmenities().getCountertops().toString());
            }

            if (placeToLive.getAmenities().getPatio_balcony() != null)
            {
                returnObject.put(JSONConstants.PATIO_BALCONY_JSON_NAME, placeToLive.getAmenities().getPatio_balcony().toString());
            }
        }

        if (placeToLive.getComplex() != null)
        {
            if (placeToLive.getComplex().getHasFitnessCenter()!= null)
            {
                returnObject.put(JSONConstants.FITNESS_CENTER_JSON_NAME, placeToLive.getComplex().getHasFitnessCenter());
            }

            if (placeToLive.getComplex().getFitnessCenterFee()!= null)
            {
                returnObject.put(JSONConstants.FITNESS_CENTER_FEE_JSON_NAME, placeToLive.getComplex().getFitnessCenterFee());
            }

            if (placeToLive.getComplex().getFitnessCenterFeeTerm()!= null)
            {
                returnObject.put(JSONConstants.FITNESS_CENTER_FEE_TERM_JSON_NAME, placeToLive.getComplex().getFitnessCenterFeeTerm().toString());
            }

            if (placeToLive.getComplex().getHasPool()!= null)
            {
                returnObject.put(JSONConstants.POOL_JSON_NAME, placeToLive.getComplex().getHasPool());
            }

            if (placeToLive.getComplex().getPoolFee()!= null)
            {
                returnObject.put(JSONConstants.POOL_FEE_JSON_NAME, placeToLive.getComplex().getPoolFee());
            }

            if (placeToLive.getComplex().getPoolFeeTerm()!= null)
            {
                returnObject.put(JSONConstants.POOL_FEE_TERM_JSON_NAME, placeToLive.getComplex().getPoolFeeTerm().toString());
            }

            if (placeToLive.getComplex().getOutdoorDogSpace()!= null)
            {
                returnObject.put(JSONConstants.OUTDOOR_SPACE_JSON_NAME, placeToLive.getComplex().getOutdoorDogSpace().toString());
            }
        }

        if (placeToLive.getDistances() != null)
        {
            if (placeToLive.getDistances().getPatrickWorkDistance() != null)
            {
                returnObject.put(JSONConstants.PATRICK_WORK_DISTANCE_JSON_NAME, placeToLive.getDistances().getPatrickWorkDistance());
            }

            if (placeToLive.getDistances().getPatrickWorkTime() != null)
            {
                returnObject.put(JSONConstants.PATRICK_WORK_TIME_JSON_NAME, placeToLive.getDistances().getPatrickWorkTime());
            }

            if (placeToLive.getDistances().getPatrickWorkTolls()!= null)
            {
                returnObject.put(JSONConstants.PATRICK_WORK_TOLLS_JSON_NAME, placeToLive.getDistances().getPatrickWorkTolls());
            }

            if (placeToLive.getDistances().getDanielleClassDistanceDrive() != null)
            {
                returnObject.put(JSONConstants.DANIELLE_CLASS_DISTANCE_DRIVE_JSON_NAME, placeToLive.getDistances().getDanielleClassDistanceDrive());
            }

            if (placeToLive.getDistances().getDanielleClassTimeDrive() != null)
            {
                returnObject.put(JSONConstants.DANIELLE_CLASS_TIME_DRIVE_JSON_NAME, placeToLive.getDistances().getDanielleClassTimeDrive());
            }

            if (placeToLive.getDistances().getDanielleClassTimePublic() != null)
            {
                returnObject.put(JSONConstants.DANIELLE_CLASS_TIME_PUBLIC_JSON_NAME, placeToLive.getDistances().getDanielleClassTimePublic());
            }

            if (placeToLive.getDistances().getSrtDistance() != null)
            {
                returnObject.put(JSONConstants.SRT_DISTANCE_JSON_NAME, placeToLive.getDistances().getSrtDistance());
            }
        }

        if (placeToLive.getPrices() != null)
        {
            if (placeToLive.getPrices().getPrice() != null)
            {
                returnObject.put(JSONConstants.PRICE_JSON_NAME, placeToLive.getPrices().getPrice());
            }

            if (placeToLive.getPrices().getTerm() != null)
            {
                returnObject.put(JSONConstants.TERM_JSON_NAME, placeToLive.getPrices().getTerm().toString());
            }

            if (placeToLive.getPrices().getLeasePeriod() != null)
            {
                returnObject.put(JSONConstants.LEASING_PERIOD_JSON_NAME, placeToLive.getPrices().getLeasePeriod());
            }

            if (placeToLive.getPrices().getLeasePeriodTerm() != null)
            {
                returnObject.put(JSONConstants.LEASING_PERIOD_TERM_JSON_NAME, placeToLive.getPrices().getLeasePeriodTerm().toString());
            }

            if (placeToLive.getPrices().getSecurityDeposit() != null)
            {
                returnObject.put(JSONConstants.SECURITY_DEPOSIT_JSON_NAME, placeToLive.getPrices().getSecurityDeposit());
            }

            if (placeToLive.getPrices().getSecurityDepositRefundable() != null)
            {
                returnObject.put(JSONConstants.SECURITY_DEPOSIT_REFUNDABLE_JSON_NAME, placeToLive.getPrices().getSecurityDepositRefundable());
            }

            if (placeToLive.getPrices().getSecurityDepositType() != null)
            {
                returnObject.put(JSONConstants.SECURITY_DEPOSIT_TYPE, placeToLive.getPrices().getSecurityDepositType().toString());
            }

            if (placeToLive.getPrices().getElectricIncluded() != null)
            {
                returnObject.put(JSONConstants.ELECTRIC_INCLUDED_JSON_NAME, placeToLive.getPrices().getElectricIncluded());
            }

            if (placeToLive.getPrices().getSewageIncluded() != null)
            {
                returnObject.put(JSONConstants.SEWAGE_INCLUDED_JSON_NAME, placeToLive.getPrices().getSewageIncluded());
            }

            if (placeToLive.getPrices().getWaterIncluded() != null)
            {
                returnObject.put(JSONConstants.WATER_INCLUDED_JSON_NAME, placeToLive.getPrices().getWaterIncluded());
            }

            if (placeToLive.getPrices().getGasIncluded() != null)
            {
                returnObject.put(JSONConstants.GAS_INCLUDED_JSON_NAME, placeToLive.getPrices().getGasIncluded());
            }

            if (placeToLive.getPrices().getTrashIncluded() != null)
            {
                returnObject.put(JSONConstants.TRASH_INCLUDED_JSON_NAME, placeToLive.getPrices().getTrashIncluded());
            }

            if (placeToLive.getPrices().getDogFee() != null)
            {
                returnObject.put(JSONConstants.DOG_FEE_JSON_NAME, placeToLive.getPrices().getDogFee());
            }

            if (placeToLive.getPrices().getDogFeeTerm() != null)
            {
                returnObject.put(JSONConstants.DOG_FEE_TERM_JSON_NAME, placeToLive.getPrices().getDogFeeTerm().toString());
            }

            if (placeToLive.getPrices().getDogFeeDeposit() != null)
            {
                returnObject.put(JSONConstants.DOG_FEE_DEPOSIT_JSON_NAME, placeToLive.getPrices().getDogFeeDeposit());
            }

            if (placeToLive.getPrices().getDogFeeDepositRefundable() != null)
            {
                returnObject.put(JSONConstants.DOG_FEE_DEPOSIT_REFUNDABLE_JSON_NAME, placeToLive.getPrices().getDogFeeDepositRefundable());
            }
        }

        if (placeToLive.getContact() != null)
        {
            if (placeToLive.getContact().getContactType() != null)
            {
                returnObject.put(JSONConstants.CONTACT_TYPE_JSON_NAME, placeToLive.getContact().getContactType().toString());
            }

            if (placeToLive.getContact().getContactDate() != null)
            {
                returnObject.put(JSONConstants.CONTACT_DATE_JSON_NAME, sdf.format(placeToLive.getContact().getContactDate().getTime()));
            }
        }

        logger.logExit();
        return returnObject;
    }

    public static JSONArray placesToLiveToJSONArray(List<PlaceToLive> placeToLiveList) throws JSONException
    {
        logger.logEnter("placesToLiveToJSONArray");
        JSONArray jsonArray = new JSONArray();
        for (PlaceToLive placeToLive : placeToLiveList)
        {
            JSONObject json = placeToLiveToJSON(placeToLive);

            jsonArray.put(json);
        }
        logger.logExit();
        return jsonArray;
    }

    public static PlaceToLive JSONToPlaceToLive(JSONObject jsonObject) throws JSONException
    {
        logger.logEnter("JSONToPlaceToLive");
        logger.d("Running for json object:\n" + jsonObject.toString());
        logger.d("Length: " + jsonObject.length());
        Iterator<String> myStrings = jsonObject.keys();
        while (myStrings.hasNext())
        {
            logger.d("Key: " + myStrings.next());
        }

        PlaceToLive placeToLive = new PlaceToLive();
        if (jsonObject.has(JSONConstants.ID_JSON_NAME))
        {
            logger.d("HAS ID");
            placeToLive.setId(jsonObject.getInt(JSONConstants.ID_JSON_NAME));
        }
        else
        {
            logger.d("DOES NOT HAVE ID");
        }

        Calendar updateDate = Calendar.getInstance();
        if (jsonObject.has(JSONConstants.LAST_UPDATED_JSON_NAME))
        {
            try
            {
                updateDate.setTime(sdf.parse(jsonObject.getString(JSONConstants.LAST_UPDATED_JSON_NAME)));
                placeToLive.setLastUpdated(updateDate);
            }
            catch(ParseException pe)
            {
                logger.w("Got an error trying to parse the date from the json:\n" + pe.getMessage());
                placeToLive.setLastUpdated(updateDate);
            }
        }
        else
        {
            placeToLive.setLastUpdated(updateDate);
        }

        if (jsonObject.has(JSONConstants.PLACE_TO_LIVE_STATE_NAME))
        {
            placeToLive.setStatus(LivingConstants.placeToLiveStatus.fromStringVal(jsonObject.getString(JSONConstants.PLACE_TO_LIVE_STATE_NAME)));
        }

        if (jsonObject.has(JSONConstants.NAME_JSON_NAME))
        {
            placeToLive.setName(jsonObject.getString(JSONConstants.NAME_JSON_NAME));
        }

        if (jsonObject.has(JSONConstants.PLACE_TO_LIVE_NOTES))
        {
            placeToLive.setPlaceToLiveNotes(jsonObject.getString(JSONConstants.PLACE_TO_LIVE_NOTES));
        }

        if (jsonObject.has(JSONConstants.PLACE_TO_LIVE_STATUS))
        {
            placeToLive.setStatus(LivingConstants.placeToLiveStatus.fromStringVal(jsonObject.getString(JSONConstants.PLACE_TO_LIVE_STATUS)));
        }

        if (jsonObject.has(JSONConstants.STYLE_JSON_NAME))
        {
            placeToLive.setType(LivingConstants.housingTypes.fromStringVal(jsonObject.getString(JSONConstants.STYLE_JSON_NAME)));
        }

        if (jsonObject.has(JSONConstants.NUM_BEDS_JSON_NAME))
        {
            placeToLive.setNumBeds((float) jsonObject.getDouble(JSONConstants.NUM_BEDS_JSON_NAME));
        }

        if (jsonObject.has(JSONConstants.NUM_BATHS_JSON_NAME))
        {
            placeToLive.setNumBaths((float) jsonObject.getDouble(JSONConstants.NUM_BATHS_JSON_NAME));
        }

        if (jsonObject.has(JSONConstants.FLOORS_JSON_NAME))
        {
            placeToLive.setNumFloors(jsonObject.getInt(JSONConstants.FLOORS_JSON_NAME));
        }

        if (jsonObject.has(JSONConstants.SQ_FT_JSON_NAME))
        {
            placeToLive.setSqFt((float) jsonObject.getDouble(JSONConstants.SQ_FT_JSON_NAME));
        }

        if (jsonObject.has(JSONConstants.RATING_JSON_NAME))
        {
            placeToLive.setRating((float) jsonObject.getDouble(JSONConstants.RATING_JSON_NAME));
        }

        if (jsonObject.has(JSONConstants.FIRST_FLOOR_BEDROOM_JSON_NAME))
        {
            placeToLive.setFirstFloorBedroom(LivingConstants.firstFloorBedroomTypes.fromStringVal(jsonObject.getString(JSONConstants.FIRST_FLOOR_BEDROOM_JSON_NAME)));
        }

        if (jsonObject.has(JSONConstants.OFFICE_HOURS_JSON_NAME))
        {
            placeToLive.setOfficeHours(jsonObject.getString(JSONConstants.OFFICE_HOURS_JSON_NAME));
        }

        if (jsonObject.has(JSONConstants.OFFICE_PHONE_NUMBER_JSON_NAME))
        {
            try
            {
                placeToLive.setOfficeNumber(new PlacePhoneNumber(jsonObject.getString(JSONConstants.OFFICE_PHONE_NUMBER_JSON_NAME)));
            }
            catch(IllegalArgumentException iae)
            {
                logger.w("Got an IllegalArgumentException trying to parse phone number from JSON:\n" + iae.getMessage());
            }
        }

        if (jsonObject.has(JSONConstants.WEBSITE_JSON_NAME))
        {
            placeToLive.setWebsite(jsonObject.getString(JSONConstants.WEBSITE_JSON_NAME));
        }

        if (jsonObject.has(JSONConstants.STREET_JSON_NAME))
        {
            placeToLive.getAddress().setStreet(jsonObject.getString(JSONConstants.STREET_JSON_NAME));
        }

        if (jsonObject.has(JSONConstants.APARTMENT_JSON_NAME))
        {
            placeToLive.getAddress().setApartmentNo(jsonObject.getString(JSONConstants.APARTMENT_JSON_NAME));
        }

        if (jsonObject.has(JSONConstants.CITY_JSON_NAME))
        {
            placeToLive.getAddress().setCity(jsonObject.getString(JSONConstants.CITY_JSON_NAME));
        }

        if (jsonObject.has(JSONConstants.STATE_JSON_NAME))
        {
            placeToLive.getAddress().setState(jsonObject.getString(JSONConstants.STATE_JSON_NAME));
        }

        if (jsonObject.has(JSONConstants.ZIP_JSON_NAME))
        {
            placeToLive.getAddress().setZipCode(jsonObject.getInt(JSONConstants.ZIP_JSON_NAME));
        }

        if (jsonObject.has(JSONConstants.DISHWASHER_JSON_NAME))
        {
            placeToLive.getAmenities().setHasDishwasher(jsonObject.getBoolean(JSONConstants.DISHWASHER_JSON_NAME));
        }

        if (jsonObject.has(JSONConstants.DISPOSAL_JSON_NAME))
        {
            placeToLive.getAmenities().setHasDisposal(jsonObject.getBoolean(JSONConstants.DISPOSAL_JSON_NAME));
        }

        if (jsonObject.has(JSONConstants.RANGE_JSON_NAME))
        {
            placeToLive.getAmenities().setRange(LivingConstants.rangeTypes.fromStringVal(jsonObject.getString(JSONConstants.RANGE_JSON_NAME)));
        }

        if (jsonObject.has(JSONConstants.FIOS_JSON_NAME))
        {
            placeToLive.getAmenities().setHasFios(jsonObject.getBoolean(JSONConstants.FIOS_JSON_NAME));
        }

        if (jsonObject.has(JSONConstants.AC_JSON_NAME))
        {
            placeToLive.getAmenities().setAc(LivingConstants.acTypes.fromStringVal(jsonObject.getString(JSONConstants.AC_JSON_NAME)));
        }

        if (jsonObject.has(JSONConstants.HEAT_JSON_NAME))
        {
            placeToLive.getAmenities().setHeat(LivingConstants.heatTypes.fromStringVal(jsonObject.getString(JSONConstants.HEAT_JSON_NAME)));
        }

        if (jsonObject.has(JSONConstants.HEAT_SOURCE_JSON_NAME))
        {
            placeToLive.getAmenities().setHeatSource(LivingConstants.heatSources.fromStringVal(jsonObject.getString(JSONConstants.HEAT_SOURCE_JSON_NAME)));
        }

        if (jsonObject.has(JSONConstants.WASHER_DRYER_JSON_NAME))
        {
            placeToLive.getAmenities().setWasherDryer(LivingConstants.washerDryerTypes.fromStringVal(jsonObject.getString(JSONConstants.WASHER_DRYER_JSON_NAME)));
        }

        if (jsonObject.has(JSONConstants.FIREPLACE_JSON_NAME))
        {
            placeToLive.getAmenities().setFirePlace(jsonObject.getBoolean(JSONConstants.FIREPLACE_JSON_NAME));
        }

        if (jsonObject.has(JSONConstants.GARAGE_JSON_NAME))
        {
            placeToLive.getAmenities().setGarage(LivingConstants.garageTypes.fromStringVal(jsonObject.getString(JSONConstants.GARAGE_JSON_NAME)));
        }

        if (jsonObject.has(JSONConstants.ATTIC_JSON_NAME))
        {
            placeToLive.getAmenities().setAttic(LivingConstants.atticBasementTypes.fromStringVal(jsonObject.getString(JSONConstants.ATTIC_JSON_NAME)));
        }

        if (jsonObject.has(JSONConstants.BASEMENT_JSON_NAME))
        {
            placeToLive.getAmenities().setBasement(LivingConstants.atticBasementTypes.fromStringVal(jsonObject.getString(JSONConstants.BASEMENT_JSON_NAME)));
        }

        if (jsonObject.has(JSONConstants.COUNTER_TOP_JSON_NAME))
        {
            placeToLive.getAmenities().setCountertops(LivingConstants.countertopTypes.fromStringVal(jsonObject.getString(JSONConstants.COUNTER_TOP_JSON_NAME)));
        }

        if (jsonObject.has(JSONConstants.PATIO_BALCONY_JSON_NAME))
        {
            placeToLive.getAmenities().setPatio_balcony(jsonObject.getBoolean(JSONConstants.PATIO_BALCONY_JSON_NAME));
        }

        if (jsonObject.has(JSONConstants.FITNESS_CENTER_JSON_NAME))
        {
            placeToLive.getComplex().setHasFitnessCenter(jsonObject.getBoolean(JSONConstants.FITNESS_CENTER_JSON_NAME));
        }

        if (jsonObject.has(JSONConstants.FITNESS_CENTER_FEE_JSON_NAME))
        {
            placeToLive.getComplex().setFitnessCenterFee(jsonObject.getInt(JSONConstants.FITNESS_CENTER_FEE_JSON_NAME));
        }

        if (jsonObject.has(JSONConstants.FITNESS_CENTER_FEE_TERM_JSON_NAME))
        {
            placeToLive.getComplex().setFitnessCenterFeeTerm(LivingConstants.termTypes.fromStringVal(jsonObject.getString(JSONConstants.FITNESS_CENTER_FEE_TERM_JSON_NAME)));
        }

        if (jsonObject.has(JSONConstants.POOL_JSON_NAME))
        {
            placeToLive.getComplex().setHasPool(jsonObject.getBoolean(JSONConstants.POOL_JSON_NAME));
        }

        if (jsonObject.has(JSONConstants.POOL_FEE_JSON_NAME))
        {
            placeToLive.getComplex().setPoolFee(jsonObject.getInt(JSONConstants.POOL_FEE_JSON_NAME));
        }

        if (jsonObject.has(JSONConstants.POOL_FEE_TERM_JSON_NAME))
        {
            placeToLive.getComplex().setPoolFeeTerm(LivingConstants.termTypes.fromStringVal(jsonObject.getString(JSONConstants.POOL_FEE_TERM_JSON_NAME)));
        }

        if (jsonObject.has(JSONConstants.OUTDOOR_SPACE_JSON_NAME))
        {
            placeToLive.getComplex().setOutdoorDogSpace(LivingConstants.outdoorDogSpaceTypes.fromStringVal(jsonObject.getString(JSONConstants.OUTDOOR_SPACE_JSON_NAME)));
        }

        if (jsonObject.has(JSONConstants.PATRICK_WORK_DISTANCE_JSON_NAME))
        {
            placeToLive.getDistances().setPatrickWorkDistance((float) jsonObject.getDouble(JSONConstants.PATRICK_WORK_DISTANCE_JSON_NAME));
        }

        if (jsonObject.has(JSONConstants.PATRICK_WORK_TIME_JSON_NAME))
        {
            placeToLive.getDistances().setPatrickWorkTime((float) jsonObject.getDouble(JSONConstants.PATRICK_WORK_TIME_JSON_NAME));
        }

        if (jsonObject.has(JSONConstants.PATRICK_WORK_TOLLS_JSON_NAME))
        {
            placeToLive.getDistances().setPatrickWorkTolls(jsonObject.getBoolean(JSONConstants.PATRICK_WORK_TOLLS_JSON_NAME));
        }

        if (jsonObject.has(JSONConstants.DANIELLE_CLASS_DISTANCE_DRIVE_JSON_NAME))
        {
            placeToLive.getDistances().setDanielleClassDistanceDrive((float) jsonObject.getDouble(JSONConstants.DANIELLE_CLASS_DISTANCE_DRIVE_JSON_NAME));
        }

        if (jsonObject.has(JSONConstants.DANIELLE_CLASS_TIME_DRIVE_JSON_NAME))
        {
            placeToLive.getDistances().setDanielleClassTimeDrive((float) jsonObject.getDouble(JSONConstants.DANIELLE_CLASS_TIME_PUBLIC_JSON_NAME));
        }

        if (jsonObject.has(JSONConstants.DANIELLE_CLASS_TIME_PUBLIC_JSON_NAME))
        {
            placeToLive.getDistances().setDanielleClassTimePublic((float) jsonObject.getDouble(JSONConstants.DANIELLE_CLASS_TIME_PUBLIC_JSON_NAME));
        }

        if (jsonObject.has(JSONConstants.SRT_DISTANCE_JSON_NAME))
        {
            placeToLive.getDistances().setSrtDistance((float) jsonObject.getDouble(JSONConstants.SRT_DISTANCE_JSON_NAME));
        }

        if (jsonObject.has(JSONConstants.PRICE_JSON_NAME))
        {
            placeToLive.getPrices().setPrice((float) jsonObject.getDouble(JSONConstants.PRICE_JSON_NAME));
        }

        if (jsonObject.has(JSONConstants.TERM_JSON_NAME))
        {
            placeToLive.getPrices().setTerm(LivingConstants.termTypes.fromStringVal(jsonObject.getString(JSONConstants.TERM_JSON_NAME)));
        }

        if (jsonObject.has(JSONConstants.LEASING_PERIOD_JSON_NAME))
        {
            placeToLive.getPrices().setLeasePeriod((float) jsonObject.getDouble(JSONConstants.LEASING_PERIOD_JSON_NAME));
        }

        if (jsonObject.has(JSONConstants.LEASING_PERIOD_TERM_JSON_NAME))
        {
            placeToLive.getPrices().setLeasePeriodTerm(LivingConstants.termTypes.fromStringVal(jsonObject.getString(JSONConstants.LEASING_PERIOD_TERM_JSON_NAME)));
        }

        if (jsonObject.has(JSONConstants.SECURITY_DEPOSIT_JSON_NAME))
        {
            placeToLive.getPrices().setSecurityDeposit((float) jsonObject.getDouble(JSONConstants.SECURITY_DEPOSIT_JSON_NAME));
        }

        if (jsonObject.has(JSONConstants.SECURITY_DEPOSIT_REFUNDABLE_JSON_NAME))
        {
            placeToLive.getPrices().setSecurityDepositRefundable(jsonObject.getBoolean(JSONConstants.SECURITY_DEPOSIT_REFUNDABLE_JSON_NAME));
        }

        if (jsonObject.has(JSONConstants.SECURITY_DEPOSIT_TYPE))
        {
            placeToLive.getPrices().setSecurityDepositType(LivingConstants.securityDepositTypes.fromStringVal(jsonObject.getString(JSONConstants.SECURITY_DEPOSIT_TYPE)));
        }

        if (jsonObject.has(JSONConstants.ELECTRIC_INCLUDED_JSON_NAME))
        {
            placeToLive.getPrices().setElectricIncluded(jsonObject.getBoolean(JSONConstants.ELECTRIC_INCLUDED_JSON_NAME));
        }

        if (jsonObject.has(JSONConstants.SEWAGE_INCLUDED_JSON_NAME))
        {
            placeToLive.getPrices().setSewageIncluded(jsonObject.getBoolean(JSONConstants.SEWAGE_INCLUDED_JSON_NAME));
        }

        if (jsonObject.has(JSONConstants.WATER_INCLUDED_JSON_NAME))
        {
            placeToLive.getPrices().setWaterIncluded(jsonObject.getBoolean(JSONConstants.WATER_INCLUDED_JSON_NAME));
        }

        if (jsonObject.has(JSONConstants.GAS_INCLUDED_JSON_NAME))
        {
            placeToLive.getPrices().setGasIncluded(jsonObject.getBoolean(JSONConstants.GAS_INCLUDED_JSON_NAME));
        }

        if (jsonObject.has(JSONConstants.TRASH_INCLUDED_JSON_NAME))
        {
            placeToLive.getPrices().setTrashIncluded(jsonObject.getBoolean(JSONConstants.TRASH_INCLUDED_JSON_NAME));
        }

        if (jsonObject.has(JSONConstants.DOG_FEE_JSON_NAME))
        {
            placeToLive.getPrices().setDogFee((float) jsonObject.getDouble(JSONConstants.DOG_FEE_JSON_NAME));
        }

        if (jsonObject.has(JSONConstants.DOG_FEE_TERM_JSON_NAME))
        {
            placeToLive.getPrices().setDogFeeTerm(LivingConstants.termTypes.fromStringVal(jsonObject.getString(JSONConstants.DOG_FEE_TERM_JSON_NAME)));
        }

        if (jsonObject.has(JSONConstants.DOG_FEE_DEPOSIT_JSON_NAME))
        {
            placeToLive.getPrices().setDogFeeDeposit((float) jsonObject.getDouble(JSONConstants.DOG_FEE_DEPOSIT_JSON_NAME));
        }

        if (jsonObject.has(JSONConstants.DOG_FEE_DEPOSIT_REFUNDABLE_JSON_NAME))
        {
            placeToLive.getPrices().setDogFeeDepositRefundable(jsonObject.getBoolean(JSONConstants.DOG_FEE_DEPOSIT_REFUNDABLE_JSON_NAME));
        }

        if (jsonObject.has(JSONConstants.CONTACT_TYPE_JSON_NAME))
        {
            placeToLive.getContact().setContactType(LivingConstants.contactType.fromStringVal(jsonObject.getString(JSONConstants.CONTACT_TYPE_JSON_NAME)));
        }

        if (jsonObject.has(JSONConstants.CONTACT_DATE_JSON_NAME))
        {
            try
            {
                Calendar contactDate = Calendar.getInstance();
                contactDate.setTime(sdf.parse(jsonObject.getString(JSONConstants.CONTACT_DATE_JSON_NAME)));
                placeToLive.getContact().setContactDate(contactDate);
            }
            catch(ParseException pe)
            {
                logger.w("Got an error trying to parse the date from the json:\n" + pe.getMessage());
            }
        }

        logger.logExit();
        return placeToLive;
    }

    public static List<PlaceToLive> JSONArrayToPlacesToLive(JSONArray placesToLive) throws JSONException
    {
        logger.logEnter("JSONArrayToPlacesToLive");
        List<PlaceToLive> returnList = new ArrayList<PlaceToLive>();

        for (int i = 0; i < placesToLive.length(); i++)
        {
            JSONObject thisObject = placesToLive.getJSONObject(i);

            returnList.add(JSONToPlaceToLive(thisObject));
        }

        logger.logExit();
        return returnList;
    }
}
