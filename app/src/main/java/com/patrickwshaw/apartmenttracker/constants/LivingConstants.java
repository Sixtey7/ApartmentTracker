package com.patrickwshaw.apartmenttracker.constants;

/**
 * Created by Patrick on 3/26/2015.
 */
public class LivingConstants
{
    public static final String SELECTED_PLACE_TO_LIVE_TAG = "com.patrickwshaw.apartmenttracker.selected_place_to_live_tag";

    public enum placeToLiveStatus
    {
        RESEARCH,
        WAITING,
        VISIT,
        CONSIDER,
        REJECTED;

        public String toString()
        {
            switch(this)
            {
                case RESEARCH:
                    return "Researching";
                case WAITING:
                    return "Waiting";
                case VISIT:
                    return "Visiting";
                case CONSIDER:
                    return "Considering";
                case REJECTED:
                    return "Rejected";
            }

            return "";
        }

        public static placeToLiveStatus fromStringVal(String stringVal)
        {
            if (stringVal != null)
            {
                if (stringVal.equals(RESEARCH.toString()))
                {
                    return RESEARCH;
                }
                else if (stringVal.equals(WAITING.toString()))
                {
                    return WAITING;
                }
                else if (stringVal.equals(VISIT.toString()))
                {
                    return VISIT;
                }
                else if (stringVal.equals(CONSIDER.toString()))
                {
                    return CONSIDER;
                }
                else if (stringVal.equals(REJECTED.toString()))
                {
                    return REJECTED;
                }
                else
                {
                    return null;
                }
            }
            else
            {
                return null;
            }
        }
    }
    public enum housingTypes
    {
        APARTMENT,
        HOUSE;

        public String toString()
        {
            switch (this)
            {
                case APARTMENT:
                    return "Apartment";
                case HOUSE:
                    return "House";
            }

            return "";
        }

        public static housingTypes fromStringVal(String stringVal)
        {
            if (stringVal != null)
            {
                if (stringVal.equals(APARTMENT.toString()))
                {
                    return APARTMENT;
                }
                else if (stringVal.equals(HOUSE.toString()))
                {
                    return HOUSE;
                }
                else
                {
                    return null;
                }
            }
            else
            {
                return null;
            }
        }
    }

    public enum termTypes
    {
        MONTH,
        YEAR;


        public String toString()
        {
            switch (this)
            {
                case MONTH:
                    return "Month";
                case YEAR:
                    return "Year";
            }

            return "";
        }

        public static termTypes fromStringVal(String stringVal)
        {
            if (stringVal != null)
            {
                if (stringVal.equals(MONTH.toString()))
                {
                    return MONTH;
                }
                else if (stringVal.equals(YEAR.toString()))
                {
                    return YEAR;
                }
                else
                {
                    return null;
                }
            }
            else
            {
                return null;
            }
        }

        public String getDisplayString()
        {
            switch(this)
            {
                case MONTH:
                    return "mo";
                case YEAR:
                    return "yr";
            }

            return "";
        }
    }

    public enum rangeTypes
    {
        GAS,
        ELECTRIC,
        CONDUCTIVE;

        public String toString()
        {
            switch (this)
            {
                case GAS:
                    return "Gas";
                case ELECTRIC:
                    return "Electric";
                case CONDUCTIVE:
                    return "Conductive";
            }

            return "";
        }

        public static rangeTypes fromStringVal(String stringVal)
        {
            if (stringVal != null)
            {
                if (stringVal.equals(GAS.toString()))
                {
                    return GAS;
                }
                else if (stringVal.equals(ELECTRIC.toString()))
                {
                    return ELECTRIC;
                }
                else if (stringVal.equals(CONDUCTIVE.toString()))
                {
                    return CONDUCTIVE;
                }
                else
                {
                    return null;
                }
            }
            else
            {
                return null;
            }
        }
    }

    public enum acTypes
    {
        CENTRAL,
        WINDOW_UNITS;

        public String toString()
        {
            switch (this)
            {
                case CENTRAL:
                    return "Central";
                case WINDOW_UNITS:
                    return "Window Units";
            }

            return "";
        }

        public static acTypes fromStringVal(String stringVal)
        {
            if (stringVal != null)
            {
                if (stringVal.equals(CENTRAL.toString()))
                {
                    return CENTRAL;
                }
                else if (stringVal.equals(WINDOW_UNITS.toString()))
                {
                    return WINDOW_UNITS;
                }
                else
                {
                    return null;
                }
            }
            else
            {
                return null;
            }
        }
    }

    public enum heatSources
    {
        GAS,
        ELECTRIC;

        public String toString()
        {
            switch (this)
            {
                case GAS:
                    return "Gas";
                case ELECTRIC:
                    return "Electric";
            }

            return "";
        }

        public static heatSources fromStringVal(String stringVal)
        {
            if (stringVal != null)
            {
                if (stringVal.equals(GAS.toString()))
                {
                    return GAS;
                }
                else if (stringVal.equals(ELECTRIC.toString()))
                {
                    return ELECTRIC;
                }
                else
                {
                    return null;
                }
            }
            else
            {
                return null;
            }
        }
    }

    public enum heatTypes
    {
        CENTRAL,
        BASEBOARD,
        RADIATOR;

        public String toString()
        {
            switch (this)
            {
                case CENTRAL:
                    return "Central";
                case BASEBOARD:
                    return "Baseboard";
                case RADIATOR:
                    return "Radiator";
            }

            return "";
        }

        public static heatTypes fromStringVal(String stringVal)
        {
            if (stringVal != null)
            {
                if (stringVal.equals(CENTRAL.toString()))
                {
                    return CENTRAL;
                }
                else if (stringVal.equals(BASEBOARD.toString()))
                {
                    return BASEBOARD;
                }
                else if (stringVal.equals(RADIATOR.toString()))
                {
                    return RADIATOR;
                }
                else
                {
                    return null;
                }
            }
            else
            {
                return null;
            }
        }
    }

    public enum washerDryerTypes
    {
        NONE,
        HOOKUPS,
        IN_UNIT;

        public String toString()
        {
            switch(this)
            {
                case NONE:
                    return "None";
                case HOOKUPS:
                    return "Hookups";
                case IN_UNIT:
                    return "In-Unit";
            }

            return "";
        }

        public static washerDryerTypes fromStringVal(String stringVal)
        {
            if (stringVal != null)
            {
                if (stringVal.equals(NONE.toString()))
                {
                    return NONE;
                }
                else if (stringVal.equals(HOOKUPS.toString()))
                {
                    return HOOKUPS;
                }
                else if (stringVal.equals(IN_UNIT.toString()))
                {
                    return IN_UNIT;
                }
                else
                {
                    return null;
                }
            }
            else
            {
                return null;
            }
        }
    }

    public enum garageTypes
    {
        NO,
        ONE_CAR,
        TWO_CAR,
        THREE_CAR,
        FOUR_CAR;

        public String toString()
        {
            switch (this)
            {
                case NO:
                    return "No Garage";
                case ONE_CAR:
                    return "One Car";
                case TWO_CAR:
                    return "Two Car";
                case THREE_CAR:
                    return "Three Car";
                case FOUR_CAR:
                    return "Four Car";
            }

            return "";
        }

        public static garageTypes fromStringVal(String stringVal)
        {
            if (stringVal != null)
            {
                if (stringVal.equals(NO.toString()))
                {
                    return NO;
                } else if (stringVal.equals(ONE_CAR.toString()))
                {
                    return ONE_CAR;
                } else if (stringVal.equals(TWO_CAR.toString()))
                {
                    return TWO_CAR;
                } else if (stringVal.equals(THREE_CAR.toString()))
                {
                    return THREE_CAR;
                } else if (stringVal.equals(FOUR_CAR.toString()))
                {
                    return FOUR_CAR;
                } else
                {
                    return null;
                }
            }
            else
            {
                return null;
            }
        }
    }

    public enum firstFloorBedroomTypes
    {
        NO,
        YES,
        ONLY_MASTER;

        public String toString()
        {
            switch (this)
            {
                case NO:
                    return "No";
                case YES:
                    return "Yes";
                case ONLY_MASTER:
                    return "Only Master";
            }

            return "";
        }

        public static firstFloorBedroomTypes fromStringVal(String stringVal)
        {
            if (stringVal != null)
            {
                if (stringVal.equals(NO.toString()))
                {
                    return NO;
                } else if (stringVal.equals(YES.toString()))
                {
                    return YES;
                } else if (stringVal.equals(ONLY_MASTER.toString()))
                {
                    return ONLY_MASTER;
                } else
                {
                    return null;
                }
            }
            else
            {
                return null;
            }
        }
    }

    public enum outdoorDogSpaceTypes
    {
        NO,
        FENCED_YARD,
        LOCAL_DOG_PARK;

        public String toString()
        {
            switch (this)
            {
                case NO:
                    return "No";
                case FENCED_YARD:
                    return "Fenced Yard";
                case LOCAL_DOG_PARK:
                    return "Local Dog Park";
            }

            return "";
        }

        public static outdoorDogSpaceTypes fromStringVal(String stringVal)
        {
            if (stringVal != null)
            {
                if (stringVal.equals(NO.toString()))
                {
                    return NO;
                } else if (stringVal.equals(FENCED_YARD.toString()))
                {
                    return FENCED_YARD;
                } else if (stringVal.equals(LOCAL_DOG_PARK.toString()))
                {
                    return LOCAL_DOG_PARK;
                } else
                {
                    return null;
                }
            }
            else
            {
                return null;
            }
        }
    }

    public enum atticBasementTypes
    {
        NO,
        YES,
        FINISHED;

        public String toString()
        {
            switch (this)
            {
                case NO:
                    return "No";
                case YES:
                    return "Yes";
                case FINISHED:
                    return "Finished";
            }

            return "";
        }

        public static atticBasementTypes fromStringVal(String stringVal)
        {
            if (stringVal != null)
            {
                if (stringVal.equals(NO.toString()))
                {
                    return NO;
                } else if (stringVal.equals(YES.toString()))
                {
                    return YES;
                } else if (stringVal.equals(FINISHED.toString()))
                {
                    return FINISHED;
                } else
                {
                    return null;
                }
            }
            else
            {
                return null;
            }
        }
    }

    public enum countertopTypes
    {
        NORMAL,
        GRANITE;

        public String toString()
        {
            switch (this)
            {
                case NORMAL:
                    return "Normal";
                case GRANITE:
                    return "Granite";
            }

            return "";
        }

        public static countertopTypes fromStringVal(String stringVal)
        {
            if (stringVal != null)
            {
                if (stringVal.equals(NORMAL.toString()))
                {
                    return NORMAL;
                } else if (stringVal.equals(GRANITE.toString()))
                {
                    return GRANITE;
                } else
                {
                    return null;
                }
            }
            else
            {
                return null;
            }
        }


    }
    public enum securityDepositTypes
    {
        SECURITY_DEPOSIT_ONLY,
        SECURITY_DEPOSIT_FIRST,
        SECURITY_DEPOSIT_FIRST_LAST;

        public String toString()
        {
            switch(this)
            {
                case SECURITY_DEPOSIT_ONLY:
                    return "SD Only";
                case SECURITY_DEPOSIT_FIRST:
                    return "SD and First";
                case SECURITY_DEPOSIT_FIRST_LAST:
                    return "SD, First, and Last";
            }

            return "";
        }

        public static securityDepositTypes fromStringVal(String stringVal)
        {
            if (stringVal != null)
            {
                if (stringVal.equals(SECURITY_DEPOSIT_ONLY.toString()))
                {
                    return SECURITY_DEPOSIT_ONLY;
                }
                else if(stringVal.equals(SECURITY_DEPOSIT_FIRST.toString()))
                {
                    return SECURITY_DEPOSIT_FIRST;
                }
                else if(stringVal.equals(SECURITY_DEPOSIT_FIRST_LAST))
                {
                    return SECURITY_DEPOSIT_FIRST_LAST;
                }
                else
                {
                    return null;
                }
            }
            else
            {
                return null;
            }
        }
    }
    public enum contactType
    {
        NO_CONTACT,
        EMAIL_SENT,
        EMAIL_RECEIVED,
        PHONE_CALL_VOICEMAIL,
        PHONE_CONTACT_MADE;

        public String toString()
        {
            switch (this)
            {
                case NO_CONTACT:
                    return "No Contact";
                case EMAIL_SENT:
                    return "Sent Email";
                case EMAIL_RECEIVED:
                    return "Received Email";
                case PHONE_CALL_VOICEMAIL:
                    return "Left Voicemail";
                case PHONE_CONTACT_MADE:
                    return "Made Phone Contact";
            }

            return "";
        }
            public static contactType fromStringVal(String stringVal)
            {
                if (stringVal != null)
                {
                    if (stringVal.equals(NO_CONTACT.toString()))
                    {
                        return NO_CONTACT;
                    } else if (stringVal.equals(EMAIL_SENT.toString()))
                    {
                        return EMAIL_SENT;
                    } else if (stringVal.equals(EMAIL_RECEIVED.toString()))
                    {
                        return EMAIL_RECEIVED;
                    } else if (stringVal.equals(PHONE_CALL_VOICEMAIL.toString()))
                    {
                        return PHONE_CALL_VOICEMAIL;
                    } else if (stringVal.equals(PHONE_CONTACT_MADE.toString()))
                    {
                        return PHONE_CONTACT_MADE;
                    } else
                    {
                        return null;
                    }
                }
                else
                {
                    return null;

            }
        }
    }
}
