package com.patrickwshaw.apartmenttracker.utility.temp;

import android.app.Activity;
import android.widget.ArrayAdapter;

import com.patrickwshaw.apartmenttracker.constants.LivingConstants;
import com.patrickwshaw.apartmenttracker.model.database.dao.LivingDao;
import com.patrickwshaw.apartmenttracker.model.model.PlaceAddress;
import com.patrickwshaw.apartmenttracker.model.model.PlaceAmenities;
import com.patrickwshaw.apartmenttracker.model.model.PlaceComplex;
import com.patrickwshaw.apartmenttracker.model.model.PlaceDistances;
import com.patrickwshaw.apartmenttracker.model.model.PlacePhoneNumber;
import com.patrickwshaw.apartmenttracker.model.model.PlacePrices;
import com.patrickwshaw.apartmenttracker.model.model.PlaceToLive;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patrick on 3/26/2015.
 */
public class HackUtil
{
    private static List<PlaceToLive> placesToLive;

    public static void createPlacesToLive(Activity activity)
    {
        LivingDao dao = new LivingDao(activity);
        for (int x = 0; x < 10; x++)
        {
            PlaceToLive tempPlaceToLive = new PlaceToLive();

            int id = x;
            String name = "Complex " + x;
            LivingConstants.housingTypes type;
            LivingConstants.firstFloorBedroomTypes firstFloorBedroom;
            if (x % 2 == 0)
            {
                //even
                type = LivingConstants.housingTypes.APARTMENT;
                firstFloorBedroom = LivingConstants.firstFloorBedroomTypes.NO;

            }
            else
            {
                //odd
                type = LivingConstants.housingTypes.HOUSE;
                if (x % 3 == 0)
                {
                    firstFloorBedroom = LivingConstants.firstFloorBedroomTypes.ONLY_MASTER;
                }
                else
                {
                    firstFloorBedroom = LivingConstants.firstFloorBedroomTypes.YES;
                }
            }
            float numBeds = x;
            float numBaths = x + .5f;
            int numFloors = 1;
            float sqFt = 100 * x;
            float rating = 3;
            String officeHours = "M-F 9-5";
            String website = "http://www.google.com";

            //includes other objects
            PlaceAddress address = createAddress(x);
            PlacePhoneNumber officeNumber = createPhoneNumber(x);
            PlaceAmenities amenities = createAmenities(x);
            PlaceComplex complex = createPlaceComplex(x);
            PlaceDistances distances = createPlaceDistances(x);
            PlacePrices prices = createPlacePrices(x);

            tempPlaceToLive.setId(id);
            tempPlaceToLive.setName(name);
            tempPlaceToLive.setType(type);
            tempPlaceToLive.setFirstFloorBedroom(firstFloorBedroom);
            tempPlaceToLive.setNumBeds(numBeds);
            tempPlaceToLive.setNumBaths(numBaths);
            tempPlaceToLive.setNumFloors(numFloors);
            tempPlaceToLive.setSqFt(sqFt);
            tempPlaceToLive.setRating(rating);
            tempPlaceToLive.setOfficeHours(officeHours);
            tempPlaceToLive.setWebsite(website);
            tempPlaceToLive.setAddress(address);
            tempPlaceToLive.setOfficeNumber(officeNumber);
            tempPlaceToLive.setAmenities(amenities);
            tempPlaceToLive.setComplex(complex);
            tempPlaceToLive.setDistances(distances);
            tempPlaceToLive.setPrices(prices);

            dao.save(tempPlaceToLive);
        }
    }

    private static PlaceAddress createAddress(int number)
    {
        PlaceAddress returnAddress = new PlaceAddress();
        String street = number + " Street St.";
        String apartmentNo = number + "A";
        String city = "City " + number;
        String state = "PA";
        int zip;
        if (number < 10)
        {
            zip = number + (number * 10) + (number * 100) + (number * 1000) + (number * 10000);
        }
        else
        {
            zip = number + (number * 10) + (number * 100) + (number * 1000);
        }

        returnAddress.setStreet(street);
        returnAddress.setApartmentNo(apartmentNo);
        returnAddress.setCity(city);
        returnAddress.setState(state);
        returnAddress.setZipCode(zip);

        return returnAddress;
    }

    private static PlacePhoneNumber createPhoneNumber(int number)
    {
        PlacePhoneNumber returnPhoneNumber = new PlacePhoneNumber();

        int areaCode;
        int phoneNumberFirst;
        int phoneNumberSecond;

        if (number < 10)
        {
            areaCode = number + (number * 10) + (number * 100);
            phoneNumberFirst = number + (number * 10) + (number * 100);
            phoneNumberSecond = number + (number * 10) + (number * 100) + (number * 1000);
        }
        else
        {
            areaCode = number + (number * 10);
            phoneNumberFirst = number + (number * 10);
            phoneNumberSecond = number + (number * 10) + (number * 100);
        }

        returnPhoneNumber.setAreaCode(areaCode + "");
        returnPhoneNumber.setPhoneNumberFirst(phoneNumberFirst + "");
        returnPhoneNumber.setPhoneNumberSecond(phoneNumberSecond + "");

        return returnPhoneNumber;
    }

    private static PlaceAmenities createAmenities(int number)
    {
        boolean hasDishwasher;
        boolean hasDisposal;
        boolean hasFios;
        boolean firePlace;
        boolean patio_balcony;
        LivingConstants.rangeTypes range;
        LivingConstants.acTypes ac;
        LivingConstants.heatSources heatSource;
        LivingConstants.heatTypes heat;
        LivingConstants.garageTypes garage;
        LivingConstants.atticBasementTypes attic;
        LivingConstants.atticBasementTypes basement;
        LivingConstants.countertopTypes countertops;

        if (number % 2 == 0)
        {
            hasDishwasher = true;
            hasDisposal = false;
            hasFios = true;
            firePlace = false;
            patio_balcony = true;
            range = LivingConstants.rangeTypes.ELECTRIC;
            ac = LivingConstants.acTypes.CENTRAL;
            heatSource = LivingConstants.heatSources.ELECTRIC;
            heat = LivingConstants.heatTypes.BASEBOARD;
            garage = LivingConstants.garageTypes.FOUR_CAR;
            attic = LivingConstants.atticBasementTypes.FINISHED;
            basement = LivingConstants.atticBasementTypes.YES;
            countertops = LivingConstants.countertopTypes.GRANITE;
        }
        else
        {
            hasDishwasher = false;
            hasDisposal = true;
            hasFios = false;
            firePlace = true;
            patio_balcony = false;
            range = LivingConstants.rangeTypes.GAS;
            ac = LivingConstants.acTypes.WINDOW_UNITS;
            heatSource = LivingConstants.heatSources.GAS;
            heat = LivingConstants.heatTypes.CENTRAL;
            garage = LivingConstants.garageTypes.NO;
            attic = LivingConstants.atticBasementTypes.YES;
            basement = LivingConstants.atticBasementTypes.FINISHED;
            countertops = LivingConstants.countertopTypes.NORMAL;
        }

        PlaceAmenities returnAmenities = new PlaceAmenities();
        returnAmenities.setHasDishwasher(hasDishwasher);
        returnAmenities.setHasDisposal(hasDisposal);
        returnAmenities.setHasFios(hasFios);
        returnAmenities.setFirePlace(firePlace);
        returnAmenities.setPatio_balcony(patio_balcony);
        returnAmenities.setRange(range);
        returnAmenities.setAc(ac);
        returnAmenities.setHeatSource(heatSource);
        returnAmenities.setHeat(heat);
        returnAmenities.setGarage(garage);
        returnAmenities.setAttic(attic);
        returnAmenities.setBasement(basement);
        returnAmenities.setCountertops(countertops);

        return returnAmenities;
    }

    private static PlaceComplex createPlaceComplex(int number)
    {
        boolean hasFitnessCenter;
        boolean hasPool;
        boolean allowsDogs;

        LivingConstants.termTypes fitnessCenterFeeTerm;
        LivingConstants.termTypes poolFeeTerm;
        LivingConstants.outdoorDogSpaceTypes outdoorDogSpace;

        if (number % 2 == 0)
        {
            hasFitnessCenter = true;
            hasPool = false;
            allowsDogs = true;
            fitnessCenterFeeTerm = LivingConstants.termTypes.MONTH;
            poolFeeTerm = LivingConstants.termTypes.YEAR;
            outdoorDogSpace = LivingConstants.outdoorDogSpaceTypes.LOCAL_DOG_PARK;
        }
        else
        {
            hasFitnessCenter = false;
            hasPool = true;
            allowsDogs = false;
            fitnessCenterFeeTerm = LivingConstants.termTypes.YEAR;
            poolFeeTerm = LivingConstants.termTypes.MONTH;
            outdoorDogSpace = LivingConstants.outdoorDogSpaceTypes.FENCED_YARD;
        }

        int fitnessCenterFee = number * 10;
        int poolFee = number * 100;

        PlaceComplex returnComplex = new PlaceComplex();
        returnComplex.setHasFitnessCenter(hasFitnessCenter);
        returnComplex.setHasPool(hasPool);
        returnComplex.setFitnessCenterFeeTerm(fitnessCenterFeeTerm);
        returnComplex.setPoolFeeTerm(poolFeeTerm);
        returnComplex.setOutdoorDogSpace(outdoorDogSpace);
        returnComplex.setFitnessCenterFee(fitnessCenterFee);
        returnComplex.setPoolFee(poolFee);

        return returnComplex;

    }

    private static PlaceDistances createPlaceDistances(int number)
    {
        float patrickWorkDistance = number * 10;
        float patrickWorkTime = number * 5;
        float danielleClassDistanceDrive = number * 15;
        float danielleClassTimeDrive = number * 7;
        float danielleClassTimePublic = number * 16;
        float srtDistance = number * 10;

        PlaceDistances returnPlaceDistances = new PlaceDistances();

        returnPlaceDistances.setPatrickWorkDistance(patrickWorkDistance);
        returnPlaceDistances.setPatrickWorkTime(patrickWorkTime);
        returnPlaceDistances.setDanielleClassDistanceDrive(danielleClassDistanceDrive);
        returnPlaceDistances.setDanielleClassTimeDrive(danielleClassTimeDrive);
        returnPlaceDistances.setDanielleClassTimePublic(danielleClassTimePublic);
        returnPlaceDistances.setSrtDistance(srtDistance);

        return returnPlaceDistances;
    }

    private static PlacePrices createPlacePrices(int number)
    {
        float price = number * 1000;
        float securityDeposit = number * 100;
        float dogFee = number * 50;
        float dogFeeDeposit = number * 15;

        LivingConstants.termTypes dogFeeTerm;
        LivingConstants.termTypes term;
        boolean securityDepositRefundable;
        boolean trashIncluded;
        boolean sewageIncluded;
        boolean gasIncluded;
        boolean waterIncluded;
        boolean electricIncluded;
        boolean dogFeeDepositRefundable;

        if (number % 2 == 0)
        {
            dogFeeTerm = LivingConstants.termTypes.MONTH;
            term = LivingConstants.termTypes.YEAR;
            securityDepositRefundable = true;
            trashIncluded = false;
            sewageIncluded = true;
            gasIncluded = false;
            waterIncluded = true;
            electricIncluded = false;
            dogFeeDepositRefundable = true;
        }
        else
        {
            dogFeeTerm = LivingConstants.termTypes.YEAR;
            term = LivingConstants.termTypes.MONTH;
            securityDepositRefundable = false;
            trashIncluded = true;
            sewageIncluded = false;
            gasIncluded = true;
            waterIncluded = false;
            electricIncluded = true;
            dogFeeDepositRefundable = false;
        }

        PlacePrices returnPrices = new PlacePrices();

        returnPrices.setPrice(price);
        returnPrices.setSecurityDeposit(securityDeposit);
        returnPrices.setDogFee(dogFee);
        returnPrices.setDogFeeDeposit(dogFeeDeposit);
        returnPrices.setDogFeeTerm(dogFeeTerm);
        returnPrices.setTerm(term);
        returnPrices.setSecurityDepositRefundable(securityDepositRefundable);
        returnPrices.setTrashIncluded(trashIncluded);
        returnPrices.setSewageIncluded(sewageIncluded);
        returnPrices.setGasIncluded(gasIncluded);
        returnPrices.setWaterIncluded(waterIncluded);
        returnPrices.setElectricIncluded(electricIncluded);
        returnPrices.setDogFeeDepositRefundable(dogFeeDepositRefundable);

        return returnPrices;

    }

    public static List<PlaceToLive> getPlacesToLive()
    {
        return placesToLive;
    }

    public static void setPlacesToLive(List<PlaceToLive> inPlacesToLive)
    {
        placesToLive = inPlacesToLive;
    }

    public ArrayAdapter<CharSequence> setupStuff(Activity context, Enum<?> myEnum)
    {
        ArrayAdapter<CharSequence> returnVal = new ArrayAdapter<CharSequence>(context, android.R.layout.simple_spinner_item);
        returnVal.add("");

        for (Enum myVal : myEnum.getDeclaringClass().getEnumConstants())
        {
            returnVal.add(myVal.toString());
        }

        return returnVal;
    }
}
