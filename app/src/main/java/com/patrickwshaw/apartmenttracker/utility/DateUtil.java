package com.patrickwshaw.apartmenttracker.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Patrick on 4/30/2015.
 */
public class DateUtil
{
    private static final String prettyDateFormat = "MM/dd/yyyy";
    private static SimpleDateFormat sdf = new SimpleDateFormat(prettyDateFormat);
    private static final LoggingUtil logger = new LoggingUtil("DateUtil", "DateUtil");


    public String createDateString(Calendar inCal)
    {
        logger.logEnter("createDateString");
        logger.logExit();
        return sdf.format(inCal.getTime());
    }

    public Calendar createCalendarFromString(String dateString)
    {
        logger.logEnter("createCalendarFromString");

        Calendar returnCal = Calendar.getInstance();
        try
        {
            returnCal.setTime(sdf.parse(dateString));
        }
        catch(ParseException pe)
        {
            logger.e("Got an excepton trying to parse the string:\n" + pe.getMessage());
            //TODO: Need to figure out what to do here, for now, we're just going to return null
            return null;
        }
        logger.logExit();

        return returnCal;
    }

    public String createDateStringFromValues(int year, int month, int day)
    {
        logger.logEnter("createDateStringFromValues");

        Calendar baseCal = Calendar.getInstance();
        baseCal.set(year, month, day);

        logger.logExit();

        return sdf.format(baseCal.getTime());
    }
}
