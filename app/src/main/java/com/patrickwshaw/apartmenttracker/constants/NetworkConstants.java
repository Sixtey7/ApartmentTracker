package com.patrickwshaw.apartmenttracker.constants;

/**
 * Created by Patrick on 4/23/2015.
 */
public class NetworkConstants
{
    //endpoints
    public static final String WIFI_SERVER_URL = "192.172.1.214";
    public static final String NETWORK_SERVER_URL = "73.188.183.3";

    public static final String SERVER_PORT = "9080";

    //Name of the rest endpoint
    public static final String APARTMENT_REST_BASE = "/ApartmentService/rest";
    public static final String APARTMENT_REST_ENDPOINT = APARTMENT_REST_BASE + "/ApartmentService";

    //Name of the endpoint to get all apartments
    public static final String GET_ALL_APARTMENTS_ENDPOINT = "getAllApartments";

    //Name of the endpoint to save apartments
    public static final String SAVE_APARTMENT_ENDPOINT = "saveApartments";
}
