package com.patrickwshaw.apartmenttracker.view.fragment;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.DialogInterface;
import android.location.Address;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;
import com.melnykov.fab.ObservableScrollView;
import com.patrickwshaw.apartmenttracker.R;
import com.patrickwshaw.apartmenttracker.constants.LivingConstants;
import com.patrickwshaw.apartmenttracker.model.database.dao.LivingDao;
import com.patrickwshaw.apartmenttracker.model.model.PlaceAddress;
import com.patrickwshaw.apartmenttracker.model.model.PlaceContact;
import com.patrickwshaw.apartmenttracker.model.model.PlacePhoneNumber;
import com.patrickwshaw.apartmenttracker.model.model.PlaceToLive;
import com.patrickwshaw.apartmenttracker.utility.DateUtil;
import com.patrickwshaw.apartmenttracker.utility.EnumUtil;
import com.patrickwshaw.apartmenttracker.utility.LoggingUtil;
import com.patrickwshaw.apartmenttracker.view.dialog.DatePickerFragment;

import java.util.Calendar;
import java.util.Enumeration;
import java.util.zip.CheckedOutputStream;

/**
 * Created by Patrick on 3/29/2015.
 */
public class EditPlaceFragment extends Fragment
{

    private final LoggingUtil logger = new LoggingUtil("EditPlaceFragment", "EditPlaceFragment");

    private PlaceToLive selectedPlaceToLive;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        logger.logEnter("onCreate");


        //for now, turn off the options menu
        //TODO: Determine if we want an options menu
        setHasOptionsMenu(false);

        //unpack the bundle
        Bundle args = this.getArguments();
        if (args.containsKey(LivingConstants.SELECTED_PLACE_TO_LIVE_TAG))
        {
            //good, we have what we need
            selectedPlaceToLive = (PlaceToLive) args.getSerializable(LivingConstants.SELECTED_PLACE_TO_LIVE_TAG);
            logger.d("Got the Place To Live: " + selectedPlaceToLive.getName());
        }
        else
        {
            //TODO: Add some error handling
            getFragmentManager().popBackStack();
        }

        logger.logExit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState)
    {
        super.onCreateView(inflater, parent, savedInstanceState);
        logger.logEnter("onCreateView");

        View v = inflater.inflate(R.layout.edit_place_fragment_layout, parent, false);

        /**
         * General Card View
         */
        ((EditText) v.findViewById(R.id.generalEditName)).setText(selectedPlaceToLive.getName());

        if (selectedPlaceToLive.getAddress() != null)
        {
            String[] addressArray = selectedPlaceToLive.getAddress().getAddressLines();
            if (addressArray[0] != null)
            {
                ((EditText) v.findViewById(R.id.generalEditAddress1)).setText(addressArray[0]);
            }

            if (addressArray[1] != null)
            {
                ((EditText) v.findViewById(R.id.generalEditAddress2)).setText(addressArray[1]);
            }

            if (addressArray[2] != null)
            {
                ((EditText) v.findViewById(R.id.generalEditAddress3)).setText(addressArray[2]);
            }
        }

        Spinner statusSpinner = (Spinner) v.findViewById(R.id.generalEditStatus);
        statusSpinner.setAdapter(EnumUtil.buildSpinnerAdapter(getActivity(), LivingConstants.placeToLiveStatus.values(), true));

        if (selectedPlaceToLive.getStatus() != null)
        {
            // + 1 due to the 0 position being blank
            statusSpinner.setSelection(selectedPlaceToLive.getStatus().ordinal() + 1);
        }
        else
        {
            statusSpinner.setSelection(0);
        }

        Spinner styleSpinner = (Spinner) v.findViewById(R.id.generalEditStyle);
        styleSpinner.setAdapter(EnumUtil.buildSpinnerAdapter(getActivity(), LivingConstants.housingTypes.values(), true));
        if (selectedPlaceToLive.getType() != null)
        {
            // + 1 due to the 0 position being blank
            styleSpinner.setSelection(selectedPlaceToLive.getType().ordinal() + 1);
        }
        else
        {
            styleSpinner.setSelection(0);
        }

        if (selectedPlaceToLive.getNumBeds() != null)
        {
            ((EditText) v.findViewById(R.id.generalEditBeds)).setText(selectedPlaceToLive.getNumBeds().toString());
        }

        if (selectedPlaceToLive.getNumBeds() != null)
        {
            ((EditText) v.findViewById(R.id.generalEditBaths)).setText(selectedPlaceToLive.getNumBaths().toString());
        }

        if (selectedPlaceToLive.getNumFloors() != null)
        {
            ((EditText) v.findViewById(R.id.generalEditFloors)).setText(selectedPlaceToLive.getNumFloors().toString());
        }

        if (selectedPlaceToLive.getSqFt() != null)
        {
            ((EditText) v.findViewById(R.id.generalEditSqFt)).setText(selectedPlaceToLive.getSqFt().toString());
        }

        if (selectedPlaceToLive.getRating() != null)
        {
            ((EditText) v.findViewById(R.id.generalEditRating)).setText(selectedPlaceToLive.getRating().toString());
        }

        Spinner firstFloorBedroomSpinner = (Spinner) v.findViewById(R.id.generalEditFirstFloor);
        firstFloorBedroomSpinner.setAdapter(EnumUtil.buildSpinnerAdapter(getActivity(), LivingConstants.firstFloorBedroomTypes.values(), true));

        if (selectedPlaceToLive.getFirstFloorBedroom() != null)
        {
            firstFloorBedroomSpinner.setSelection(selectedPlaceToLive.getFirstFloorBedroom().ordinal() + 1, false);
        }
        else
        {
            firstFloorBedroomSpinner.setSelection(0, false);
        }

        if (selectedPlaceToLive.getOfficeHours() != null)
        {
            ((EditText) v.findViewById(R.id.generalEditOfficeHours)).setText(selectedPlaceToLive.getOfficeHours());
        }

        if (selectedPlaceToLive.getOfficeNumber() != null)
        {
            ((EditText) v.findViewById(R.id.generalEditPhoneNumber)).setText(selectedPlaceToLive.getOfficeNumber().toString());
        }

        if (selectedPlaceToLive.getWebsite() != null)
        {
            ((EditText) v.findViewById(R.id.generalEditWebsite)).setText(selectedPlaceToLive.getWebsite());
        }


        /**
         * Amenities Card View
         */
        if (selectedPlaceToLive.getAmenities().getHasDishwasher() != null)
        {
            ((CheckBox) v.findViewById(R.id.amenitiesEditDishwasher)).setChecked(selectedPlaceToLive.getAmenities().getHasDishwasher());
        }

        if (selectedPlaceToLive.getAmenities().getHasDisposal() != null)
        {
            ((CheckBox) v.findViewById(R.id.amenitiesEditDisposal)).setChecked(selectedPlaceToLive.getAmenities().getHasDisposal());
        }

        Spinner rangeSpinner = (Spinner) v.findViewById(R.id.amenitiesEditRange);
        rangeSpinner.setAdapter(EnumUtil.buildSpinnerAdapter(getActivity(), LivingConstants.rangeTypes.values(), true));

        if (selectedPlaceToLive.getAmenities().getRange() != null)
        {
            rangeSpinner.setSelection(selectedPlaceToLive.getAmenities().getRange().ordinal() + 1, false);
        }
        else
        {
            rangeSpinner.setSelection(0, false);
        }

        if (selectedPlaceToLive.getAmenities().getHasFios() != null)
        {
            ((CheckBox) v.findViewById(R.id.amenitiesEditFios)).setChecked(selectedPlaceToLive.getAmenities().getHasFios());
        }

        Spinner acSpinner = (Spinner) v.findViewById(R.id.amenitiesEditAC);
        acSpinner.setAdapter(EnumUtil.buildSpinnerAdapter(getActivity(), LivingConstants.acTypes.values(), true));

        if (selectedPlaceToLive.getAmenities().getAc() != null)
        {
            acSpinner.setSelection(selectedPlaceToLive.getAmenities().getAc().ordinal() + 1, false);
        }
        else
        {
            acSpinner.setSelection(0, false);
        }

        Spinner heatSpinner = (Spinner) v.findViewById(R.id.amenitiesEditHeat);
        heatSpinner.setAdapter(EnumUtil.buildSpinnerAdapter(getActivity(), LivingConstants.heatTypes.values(), true));

        if (selectedPlaceToLive.getAmenities().getHeat() != null)
        {
            heatSpinner.setSelection(selectedPlaceToLive.getAmenities().getHeat().ordinal() + 1, false);
        }
        else
        {
            heatSpinner.setSelection(0, false);
        }

        Spinner heatSourceSpinner = (Spinner) v.findViewById(R.id.amenitiesEditHeatSource);
        heatSourceSpinner.setAdapter(EnumUtil.buildSpinnerAdapter(getActivity(), LivingConstants.heatSources.values(), true));

        if (selectedPlaceToLive.getAmenities().getHeatSource() != null)
        {
            heatSourceSpinner.setSelection(selectedPlaceToLive.getAmenities().getHeatSource().ordinal() + 1, false);
        }
        else
        {
            heatSourceSpinner.setSelection(0, false);
        }

        Spinner washerDryerSpinner = (Spinner) v.findViewById(R.id.amenitiesEditWasherDryer);
        washerDryerSpinner.setAdapter(EnumUtil.buildSpinnerAdapter(getActivity(), LivingConstants.washerDryerTypes.values(), true));

        if (selectedPlaceToLive.getAmenities().getWasherDryer() != null)
        {
            washerDryerSpinner.setSelection(selectedPlaceToLive.getAmenities().getWasherDryer().ordinal() + 1, false);
        }
        else
        {
            washerDryerSpinner.setSelection(0, false);
        }

        if (selectedPlaceToLive.getAmenities().getFirePlace() != null)
        {
            if (selectedPlaceToLive.getAmenities().getFirePlace())
            {
                ((CheckBox) v.findViewById(R.id.amenitiesEditFireplace)).setChecked(selectedPlaceToLive.getAmenities().getFirePlace());
            }
        }

        Spinner garageSpinner = (Spinner) v.findViewById(R.id.amenitiesEditGarage);
        garageSpinner.setAdapter(EnumUtil.buildSpinnerAdapter(getActivity(), LivingConstants.garageTypes.values(), true));

        if (selectedPlaceToLive.getAmenities().getGarage() != null)
        {
            garageSpinner.setSelection(selectedPlaceToLive.getAmenities().getGarage().ordinal() + 1, false);
        }
        else
        {
            garageSpinner.setSelection(0, false);
        }

        Spinner atticSpinner = (Spinner) v.findViewById(R.id.amenitiesEditAttic);
        atticSpinner.setAdapter(EnumUtil.buildSpinnerAdapter(getActivity(), LivingConstants.atticBasementTypes.values(), true));

        if (selectedPlaceToLive.getAmenities().getAttic() != null)
        {
            atticSpinner.setSelection(selectedPlaceToLive.getAmenities().getAttic().ordinal() + 1, false);
        }
        else
        {
            atticSpinner.setSelection(0, false);
        }

        Spinner basementSpinner = (Spinner) v.findViewById(R.id.amenitiesEditBasement);
        basementSpinner.setAdapter(EnumUtil.buildSpinnerAdapter(getActivity(), LivingConstants.atticBasementTypes.values(), true));

        if (selectedPlaceToLive.getAmenities().getBasement() != null)
        {
            basementSpinner.setSelection(selectedPlaceToLive.getAmenities().getBasement().ordinal() + 1, false);
        }
        else
        {
            basementSpinner.setSelection(0, false);
        }

        Spinner countertopSpinner = (Spinner) v.findViewById(R.id.amenitiesEditCounters);
        countertopSpinner.setAdapter(EnumUtil.buildSpinnerAdapter(getActivity(), LivingConstants.countertopTypes.values(), true));

        if (selectedPlaceToLive.getAmenities().getCountertops() != null)
        {
            countertopSpinner.setSelection(selectedPlaceToLive.getAmenities().getCountertops().ordinal() + 1, false);
        }
        else
        {
            countertopSpinner.setSelection(0, false);
        }

        if (selectedPlaceToLive.getAmenities().getPatio_balcony() != null)
        {
            ((CheckBox) v.findViewById(R.id.amenitiesEditPatioBalcony)).setChecked(selectedPlaceToLive.getAmenities().getPatio_balcony());
        }

        /**
         * Complex Card
         */
        if (selectedPlaceToLive.getComplex().getHasFitnessCenter() != null)
        {
            ((CheckBox) v.findViewById(R.id.complexEditFitnessCenter)).setChecked(selectedPlaceToLive.getComplex().getHasFitnessCenter());
        }

        if (selectedPlaceToLive.getComplex().getFitnessCenterFee() != null)
        {
            ((TextView) v.findViewById(R.id.complexEditFitnessCenterFee)).setText(selectedPlaceToLive.getComplex().getFitnessCenterFee().toString());
        }

        Spinner fitnessCenterFeeTermSpinner = (Spinner) v.findViewById(R.id.complexEditFitnessCenterFeeTerm);
        fitnessCenterFeeTermSpinner.setAdapter(EnumUtil.buildSpinnerAdapter(getActivity(), LivingConstants.termTypes.values(), true));
        if (selectedPlaceToLive.getComplex().getFitnessCenterFeeTerm() != null)
        {
            fitnessCenterFeeTermSpinner.setSelection(selectedPlaceToLive.getComplex().getFitnessCenterFeeTerm().ordinal() + 1, false);
        }
        else
        {
            fitnessCenterFeeTermSpinner.setSelection(0, false);
        }

        if (selectedPlaceToLive.getComplex().getHasPool() != null)
        {
            ((CheckBox) v.findViewById(R.id.complexEditPool)).setChecked(selectedPlaceToLive.getComplex().getHasPool());
        }

        if (selectedPlaceToLive.getComplex().getPoolFee() != null)
        {
            ((TextView) v.findViewById(R.id.complexEditPoolFee)).setText(selectedPlaceToLive.getComplex().getPoolFee().toString());
        }

        Spinner poolFeeTermSpinner = (Spinner) v.findViewById(R.id.complexEditPoolFeeTerm);
        poolFeeTermSpinner.setAdapter(EnumUtil.buildSpinnerAdapter(getActivity(), LivingConstants.termTypes.values(), true));

        if (selectedPlaceToLive.getComplex().getPoolFeeTerm() != null)
        {
            poolFeeTermSpinner.setSelection(selectedPlaceToLive.getComplex().getPoolFeeTerm().ordinal() + 1, false);
        }
        else
        {
            poolFeeTermSpinner.setSelection(0, false);
        }

        Spinner outdoorSpaceSpinner = (Spinner) v.findViewById(R.id.complexEditOutdoorSpace);
        outdoorSpaceSpinner.setAdapter(EnumUtil.buildSpinnerAdapter(getActivity(), LivingConstants.outdoorDogSpaceTypes.values(), true));

        if (selectedPlaceToLive.getComplex().getOutdoorDogSpace() != null)
        {
            outdoorSpaceSpinner.setSelection(selectedPlaceToLive.getComplex().getOutdoorDogSpace().ordinal() + 1, false);
        }
        else
        {
            outdoorSpaceSpinner.setSelection(0, false);
        }

        /**
         * Distance Card View
         */
        if (selectedPlaceToLive.getDistances().getPatrickWorkDistance() != null)
        {
            ((TextView) v.findViewById(R.id.distanceEditPatrickWorkDistance)).setText(selectedPlaceToLive.getDistances().getPatrickWorkDistance().toString());
        }

        if (selectedPlaceToLive.getDistances().getPatrickWorkTime() != null)
        {
            ((TextView) v.findViewById(R.id.distanceEditPatrickWorkTime)).setText(selectedPlaceToLive.getDistances().getPatrickWorkTime().toString());
        }

        if (selectedPlaceToLive.getDistances().getPatrickWorkTolls() != null)
        {
            ((CheckBox) v.findViewById(R.id.distanceEditPatrickWorkTolls)).setChecked(selectedPlaceToLive.getDistances().getPatrickWorkTolls());
        }

        if (selectedPlaceToLive.getDistances().getDanielleClassDistanceDrive() != null)
        {
            ((TextView) v.findViewById(R.id.distanceEditDanielleClassDistanceDrive)).setText(selectedPlaceToLive.getDistances().getDanielleClassDistanceDrive().toString());
        }

        if (selectedPlaceToLive.getDistances().getDanielleClassTimeDrive() != null)
        {
            ((TextView) v.findViewById(R.id.distanceEditDanielleClassTimeDrive)).setText(selectedPlaceToLive.getDistances().getDanielleClassTimeDrive().toString());
        }

        if (selectedPlaceToLive.getDistances().getDanielleClassTimePublic() != null)
        {
            ((TextView) v.findViewById(R.id.distanceEditDanielleClassTimePublic)).setText(selectedPlaceToLive.getDistances().getDanielleClassTimePublic().toString());
        }

        if (selectedPlaceToLive.getDistances().getSrtDistance() != null)
        {
            ((TextView) v.findViewById(R.id.distanceEditSRTDistance)).setText(selectedPlaceToLive.getDistances().getSrtDistance().toString());
        }

        /**
         * Price Card View
         */
        if (selectedPlaceToLive.getPrices().getPrice() != null)
        {
            ((TextView) v.findViewById(R.id.priceEditPrice)).setText(selectedPlaceToLive.getPrices().getPrice().toString());
        }

        Spinner priceTermSpinner = (Spinner) v.findViewById(R.id.priceEditPriceTerm);
        priceTermSpinner.setAdapter(EnumUtil.buildSpinnerAdapter(getActivity(), LivingConstants.termTypes.values(), true));

        if (selectedPlaceToLive.getPrices().getTerm() != null)
        {
            priceTermSpinner.setSelection(selectedPlaceToLive.getPrices().getTerm().ordinal() + 1, false);
        }
        else
        {
            priceTermSpinner.setSelection(0, false);
        }

        if (selectedPlaceToLive.getPrices().getLeasePeriod() != null)
        {
            ((TextView) v.findViewById(R.id.priceEditLeasePeriod)).setText(selectedPlaceToLive.getPrices().getLeasePeriod().toString());
        }

        Spinner priceLeasePeriodTermSpinner = (Spinner) v.findViewById(R.id.priceEditLeasePeriodTerm);
        priceLeasePeriodTermSpinner.setAdapter(EnumUtil.buildSpinnerAdapter(getActivity(), LivingConstants.termTypes.values(), true));

        if (selectedPlaceToLive.getPrices().getLeasePeriodTerm() != null)
        {
            priceLeasePeriodTermSpinner.setSelection(selectedPlaceToLive.getPrices().getLeasePeriodTerm().ordinal() + 1, false);
        }
        else
        {
            priceLeasePeriodTermSpinner.setSelection(0, false);
        }

        if (selectedPlaceToLive.getPrices().getSecurityDeposit() != null)
        {
            ((TextView) v.findViewById(R.id.priceEditSecurityDeposit)).setText(selectedPlaceToLive.getPrices().getSecurityDeposit().toString());
        }

        if (selectedPlaceToLive.getPrices().getSecurityDepositRefundable() != null)
        {
            ((CheckBox) v.findViewById(R.id.priceEditSecurityDepositRefundable)).setChecked(selectedPlaceToLive.getPrices().getSecurityDepositRefundable());
        }

        Spinner securityDepositTypeSpinner = (Spinner) v.findViewById(R.id.priceEditSecurityDepositType);
        securityDepositTypeSpinner.setAdapter(EnumUtil.buildSpinnerAdapter(getActivity(), LivingConstants.securityDepositTypes.values(), true));

        if (selectedPlaceToLive.getPrices().getSecurityDepositType() != null)
        {
            securityDepositTypeSpinner.setSelection(selectedPlaceToLive.getPrices().getSecurityDepositType().ordinal() + 1, false);
        }
        else
        {
            securityDepositTypeSpinner.setSelection(0, false);
        }

        if (selectedPlaceToLive.getPrices().getElectricIncluded() != null)
        {
            ((CheckBox) v.findViewById(R.id.priceEditElectricIncluded)).setChecked(selectedPlaceToLive.getPrices().getElectricIncluded());
        }

        if (selectedPlaceToLive.getPrices().getWaterIncluded() != null)
        {
            ((CheckBox) v.findViewById(R.id.priceEditWaterIncluded)).setChecked(selectedPlaceToLive.getPrices().getWaterIncluded());
        }

        if (selectedPlaceToLive.getPrices().getSewageIncluded() != null)
        {
            ((CheckBox) v.findViewById(R.id.priceEditSewageIncluded)).setChecked(selectedPlaceToLive.getPrices().getSewageIncluded());
        }

        if (selectedPlaceToLive.getPrices().getGasIncluded() != null)
        {
            ((CheckBox) v.findViewById(R.id.priceEditGasIncluded)).setChecked(selectedPlaceToLive.getPrices().getGasIncluded());
        }

        if (selectedPlaceToLive.getPrices().getTrashIncluded() != null)
        {
            ((CheckBox) v.findViewById(R.id.priceEditTrashIncluded)).setChecked(selectedPlaceToLive.getPrices().getTrashIncluded());
        }

        if (selectedPlaceToLive.getPrices().getDogFee() != null)
        {
            ((TextView) v.findViewById(R.id.priceEditDogFee)).setText(selectedPlaceToLive.getPrices().getDogFee().toString());
        }

        Spinner dogFeeTermSpinner = (Spinner) v.findViewById(R.id.priceEditDogFeeTerm);
        dogFeeTermSpinner.setAdapter(EnumUtil.buildSpinnerAdapter(getActivity(), LivingConstants.termTypes.values(), true));
        if (selectedPlaceToLive.getPrices().getDogFeeTerm() != null)
        {
            dogFeeTermSpinner.setSelection(selectedPlaceToLive.getPrices().getDogFeeTerm().ordinal() + 1, false);
        }
        else
        {
            dogFeeTermSpinner.setSelection(0, false);
        }

        if (selectedPlaceToLive.getPrices().getDogFeeDeposit() != null)
        {
            ((TextView) v.findViewById(R.id.priceEditDogFeeDeposit)).setText(selectedPlaceToLive.getPrices().getDogFeeDeposit().toString());
        }

        if (selectedPlaceToLive.getPrices().getDogFeeDepositRefundable() != null)
        {
            ((CheckBox) v.findViewById(R.id.priceEditDogFeeDepositRefundable)).setChecked(selectedPlaceToLive.getPrices().getDogFeeDepositRefundable());
        }

        /**
         * Notes Edit Card
         */
        if (selectedPlaceToLive.getPlaceToLiveNotes() != null)
        {
            ((TextView) v.findViewById(R.id.notesEditNotes)).setText(selectedPlaceToLive.getPlaceToLiveNotes());
        }

        /**
         * Contact Edit Card
         */
        if (selectedPlaceToLive.getContact() != null)
        {
            Spinner contactTypeSpinner = (Spinner) v.findViewById(R.id.contactEditLastContactType);
            contactTypeSpinner.setAdapter(EnumUtil.buildSpinnerAdapter(getActivity(), LivingConstants.contactType.values(), true));
            if (selectedPlaceToLive.getContact().getContactType() != null)
            {
                contactTypeSpinner.setSelection(selectedPlaceToLive.getContact().getContactType().ordinal() + 1, false);
            }
            else
            {
                contactTypeSpinner.setSelection(0, false);
            }

            final TextView dateTextView = (TextView) v.findViewById(R.id.contactEditLastContactDate);
            if (selectedPlaceToLive.getContact().getContactDate() != null)
            {
                DateUtil dateUtil = new DateUtil();
                dateTextView.setText(dateUtil.createDateString(selectedPlaceToLive.getContact().getContactDate()));
            }

            Button dateEditButton = (Button) v.findViewById(R.id.contactEditLastContactDateButton);
            dateEditButton.setOnClickListener(new Button.OnClickListener()
            {

                @Override
                public void onClick(View v)
                {
                    final Calendar defaultCalendar = Calendar.getInstance();
                    if (selectedPlaceToLive.getContact().getContactDate() != null)
                    {
                        defaultCalendar.set(
                                selectedPlaceToLive.getContact().getContactDate().get(Calendar.YEAR),
                                selectedPlaceToLive.getContact().getContactDate().get(Calendar.MONTH),
                                selectedPlaceToLive.getContact().getContactDate().get(Calendar.DAY_OF_MONTH));
                    }
                    DatePickerDialog dateDialog = new DatePickerDialog(
                            getActivity(),
                            new DatePickerDialog.OnDateSetListener()
                            {
                                @Override
                                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
                                {
                                    /*
                                    if (selectedPlaceToLive.getContact() == null)
                                    {
                                        logger.w("Had to default contact - it was null!");
                                        selectedPlaceToLive.setContact(new PlaceContact());
                                    }

                                    if(selectedPlaceToLive.getContact().getContactDate() == null)
                                    {
                                        logger.w("Had to default contact date - it was null");
                                        selectedPlaceToLive.getContact().setContactDate(Calendar.getInstance());
                                    }

                                    selectedPlaceToLive.getContact().getContactDate().set(year, monthOfYear, dayOfMonth);
                                    */
                                    DateUtil dateUtil = new DateUtil();
                                    dateTextView.setText(dateUtil.createDateStringFromValues(year, monthOfYear, dayOfMonth));

                                }
                            },
                            defaultCalendar.get(Calendar.YEAR),
                            defaultCalendar.get(Calendar.MONTH),
                            defaultCalendar.get(Calendar.DAY_OF_MONTH)
                    );

                    dateDialog.show();
                }
            });
        }

        /**
         * Scroll View
         */
        ObservableScrollView editScrollView = (ObservableScrollView) v.findViewById(R.id.editPlaceScrollView);


        /**
         * Save Button
         */
        FloatingActionButton saveButton = (FloatingActionButton) v.findViewById(R.id.editPlaceSaveButton);
        saveButton.setTag(v);
        saveButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View button)
            {
                logger.logInnerClassEnter("SaveButton", "onClick");

                logger.d("The user selected to save the form - start saving");

                View parentView = (View) button.getTag();

                if (parentView != null)
                {
                    logger.d("Was able to get the parent view out of the tag - saving the values");
                    updateAndSavePlaceToLiveFromValues(parentView);
                    Toast successToast = Toast.makeText(getActivity(), R.string.toast_save_to_database_success, Toast.LENGTH_SHORT);
                    successToast.show();
                }
                else
                {
                    logger.e("Unable to get the parent view out of the button tag - cannot save");
                    Toast errorToast = Toast.makeText(getActivity(), R.string.toast_save_to_database_error, Toast.LENGTH_LONG);
                    errorToast.show();
                }


                logger.logInnerClassExit("SaveButton", "onClick");
            }
        });
        saveButton.attachToScrollView(editScrollView);
        logger.logExit();
        return v;
    }

    public void updateAndSavePlaceToLiveFromValues(View v)
    {
        logger.logEnter("updateAndSavePlaceToLiveFromValues");
        int selectedPosition;
        /**
         * General Card View
         */
        selectedPlaceToLive.setName(((TextView) v.findViewById(R.id.generalEditName)).getText().toString());

        selectedPosition = ((Spinner) v.findViewById(R.id.generalEditStatus)).getSelectedItemPosition();
        if (selectedPosition == 0)
        {
            selectedPlaceToLive.setStatus(null);
        }
        else
        {
            selectedPlaceToLive.setStatus(LivingConstants.placeToLiveStatus.values()[selectedPosition - 1]);
        }

        selectedPosition = ((Spinner) v.findViewById(R.id.generalEditStyle)).getSelectedItemPosition();
        if (selectedPosition == 0)
        {
            selectedPlaceToLive.setType(null);
        } else
        {
            selectedPlaceToLive.setType(LivingConstants.housingTypes.values()[selectedPosition - 1]);
        }

        String textString = ((TextView) v.findViewById(R.id.generalEditBeds)).getText().toString();
        if (!(textString.equals("")))
        {
            selectedPlaceToLive.setNumBeds(Float.parseFloat(textString));
        }

        textString = ((TextView) v.findViewById(R.id.generalEditBaths)).getText().toString();
        if (!(textString.equals("")))
        {
            selectedPlaceToLive.setNumBaths(Float.parseFloat(textString));
        }

        textString = ((TextView) v.findViewById(R.id.generalEditFloors)).getText().toString();
        if (!(textString.equals("")))
        {
            selectedPlaceToLive.setNumFloors(Integer.parseInt(textString));
        }

        textString = ((TextView) v.findViewById(R.id.generalEditSqFt)).getText().toString();
        if (!(textString.equals("")))
        {
            selectedPlaceToLive.setSqFt(Float.parseFloat(textString));
        }

        textString = ((TextView) v.findViewById(R.id.generalEditRating)).getText().toString();
        if (!(textString.equals("")))
        {
            selectedPlaceToLive.setRating(Float.parseFloat(textString));
        }

        selectedPosition = ((Spinner) v.findViewById(R.id.generalEditFirstFloor)).getSelectedItemPosition();
        if (selectedPosition == 0)
        {
            selectedPlaceToLive.setFirstFloorBedroom(null);
        } else
        {
            selectedPlaceToLive.setFirstFloorBedroom(LivingConstants.firstFloorBedroomTypes.values()[selectedPosition - 1]);
        }

        selectedPlaceToLive.setOfficeHours(((TextView) v.findViewById(R.id.generalEditOfficeHours)).getText().toString());

        textString = ((TextView) v.findViewById(R.id.generalEditPhoneNumber)).getText().toString();
        if (!(textString.equals("")))
        {
            try
            {
                selectedPlaceToLive.setOfficeNumber(new PlacePhoneNumber(textString));
            }
            catch(IllegalArgumentException iae)
            {
                //TODO: If we add error handling, here's the spot
                logger.w("Got an error parsing the phone number - setting to null:\n" + iae.getMessage());
                selectedPlaceToLive.setOfficeNumber(null);
            }
        }
        selectedPlaceToLive.setWebsite(((TextView) v.findViewById(R.id.generalEditWebsite)).getText().toString());

        String addressArray[] = new String[3];
        addressArray[0] = ((TextView) v.findViewById(R.id.generalEditAddress1)).getText().toString();
        addressArray[1] = ((TextView) v.findViewById(R.id.generalEditAddress2)).getText().toString();
        addressArray[2] = ((TextView) v.findViewById(R.id.generalEditAddress3)).getText().toString();
        try
        {
            selectedPlaceToLive.setAddress(new PlaceAddress(addressArray));
        }
        catch(IllegalArgumentException iae)
        {
            //TODO: If we add error handling, here's a spot too
            logger.w("Got an error parsing the address - setting to null:\n" + iae.getMessage());
            selectedPlaceToLive.setAddress(null);
        }

        /**
         * Amenities Card View
         */
        selectedPlaceToLive.getAmenities().setHasDishwasher(((CheckBox) v.findViewById(R.id.amenitiesEditDishwasher)).isChecked());
        selectedPlaceToLive.getAmenities().setHasDishwasher(((CheckBox) v.findViewById(R.id.amenitiesEditDisposal)).isChecked());

        selectedPosition = ((Spinner) v.findViewById(R.id.amenitiesEditRange)).getSelectedItemPosition();
        if (selectedPosition == 0)
        {
            selectedPlaceToLive.getAmenities().setRange(null);
        } else
        {
            selectedPlaceToLive.getAmenities().setRange(LivingConstants.rangeTypes.values()[selectedPosition - 1]);
        }

        selectedPlaceToLive.getAmenities().setHasFios(((CheckBox) v.findViewById(R.id.amenitiesEditFios)).isChecked());

        selectedPosition = ((Spinner) v.findViewById(R.id.amenitiesEditAC)).getSelectedItemPosition();
        if (selectedPosition == 0)
        {
            selectedPlaceToLive.getAmenities().setAc(null);
        } else
        {
            selectedPlaceToLive.getAmenities().setAc(LivingConstants.acTypes.values()[selectedPosition - 1]);
        }

        selectedPosition = ((Spinner) v.findViewById(R.id.amenitiesEditHeat)).getSelectedItemPosition();
        if (selectedPosition == 0)
        {
            selectedPlaceToLive.getAmenities().setHeat(null);
        } else
        {
            selectedPlaceToLive.getAmenities().setHeat(LivingConstants.heatTypes.values()[selectedPosition - 1]);
        }

        selectedPosition = ((Spinner) v.findViewById(R.id.amenitiesEditHeatSource)).getSelectedItemPosition();
        if (selectedPosition == 0)
        {
            selectedPlaceToLive.getAmenities().setHeatSource(null);
        } else
        {
            selectedPlaceToLive.getAmenities().setHeatSource(LivingConstants.heatSources.values()[selectedPosition - 1]);
        }

        selectedPosition = ((Spinner) v.findViewById(R.id.amenitiesEditWasherDryer)).getSelectedItemPosition();
        if (selectedPosition == 0)
        {
            selectedPlaceToLive.getAmenities().setWasherDryer(null);
        } else
        {
            selectedPlaceToLive.getAmenities().setWasherDryer(LivingConstants.washerDryerTypes.values()[selectedPosition - 1]);
        }

        selectedPlaceToLive.getAmenities().setFirePlace(((CheckBox) v.findViewById(R.id.amenitiesEditFireplace)).isChecked());

        selectedPosition = ((Spinner) v.findViewById(R.id.amenitiesEditGarage)).getSelectedItemPosition();
        if (selectedPosition == 0)
        {
            selectedPlaceToLive.getAmenities().setGarage(null);
        } else
        {
            selectedPlaceToLive.getAmenities().setGarage(LivingConstants.garageTypes.values()[selectedPosition - 1]);
        }

        selectedPosition = ((Spinner) v.findViewById(R.id.amenitiesEditAttic)).getSelectedItemPosition();
        if (selectedPosition == 0)
        {
            selectedPlaceToLive.getAmenities().setAttic(null);
        } else
        {
            selectedPlaceToLive.getAmenities().setAttic(LivingConstants.atticBasementTypes.values()[selectedPosition - 1]);
        }

        selectedPosition = ((Spinner) v.findViewById(R.id.amenitiesEditBasement)).getSelectedItemPosition();
        if (selectedPosition == 0)
        {
            selectedPlaceToLive.getAmenities().setBasement(null);
        } else
        {
            selectedPlaceToLive.getAmenities().setBasement(LivingConstants.atticBasementTypes.values()[selectedPosition - 1]);
        }

        selectedPosition = ((Spinner) v.findViewById(R.id.amenitiesEditCounters)).getSelectedItemPosition();
        if (selectedPosition == 0)
        {
            selectedPlaceToLive.getAmenities().setCountertops(null);
        } else
        {
            selectedPlaceToLive.getAmenities().setCountertops(LivingConstants.countertopTypes.values()[selectedPosition - 1]);
        }

        selectedPlaceToLive.getAmenities().setPatio_balcony(((CheckBox) v.findViewById(R.id.amenitiesEditPatioBalcony)).isChecked());

        /**
         * Complex Card View
         */
        selectedPlaceToLive.getComplex().setHasFitnessCenter(((CheckBox) v.findViewById(R.id.complexEditFitnessCenter)).isChecked());
        textString = ((TextView) v.findViewById(R.id.complexEditFitnessCenterFee)).getText().toString();
        if (!(textString.equals("")))
        {
            selectedPlaceToLive.getComplex().setFitnessCenterFee(Integer.parseInt(textString));
        }

        selectedPosition = ((Spinner) v.findViewById(R.id.complexEditFitnessCenterFeeTerm)).getSelectedItemPosition();
        if (selectedPosition == 0)
        {
            selectedPlaceToLive.getComplex().setFitnessCenterFeeTerm(null);
        } else
        {
            selectedPlaceToLive.getComplex().setFitnessCenterFeeTerm(LivingConstants.termTypes.values()[selectedPosition - 1]);
        }

        selectedPlaceToLive.getComplex().setHasPool(((CheckBox) v.findViewById(R.id.complexEditPool)).isChecked());

        textString = ((TextView) v.findViewById(R.id.complexEditPoolFee)).getText().toString();
        if (!(textString.equals("")))
        {
            selectedPlaceToLive.getComplex().setPoolFee(Integer.parseInt(textString));
        }

        selectedPosition = ((Spinner) v.findViewById(R.id.complexEditPoolFeeTerm)).getSelectedItemPosition();
        if (selectedPosition == 0)
        {
            selectedPlaceToLive.getComplex().setPoolFeeTerm(null);
        } else
        {
            selectedPlaceToLive.getComplex().setPoolFeeTerm(LivingConstants.termTypes.values()[selectedPosition - 1]);
        }

        selectedPosition = ((Spinner) v.findViewById(R.id.complexEditOutdoorSpace)).getSelectedItemPosition();
        if (selectedPosition == 0)
        {
            selectedPlaceToLive.getComplex().setOutdoorDogSpace(null);
        } else
        {
            selectedPlaceToLive.getComplex().setOutdoorDogSpace(LivingConstants.outdoorDogSpaceTypes.values()[selectedPosition - 1]);
        }

        /**
         * Distance Card View
         */
        textString = ((TextView) v.findViewById(R.id.distanceEditPatrickWorkDistance)).getText().toString();
        if (!(textString.equals("")))
        {
            selectedPlaceToLive.getDistances().setPatrickWorkDistance(Float.parseFloat(textString));
        }

        textString = ((TextView) v.findViewById(R.id.distanceEditPatrickWorkTime)).getText().toString();
        if (!(textString.equals("")))
        {
            selectedPlaceToLive.getDistances().setPatrickWorkTime(Float.parseFloat(textString));
        }

        selectedPlaceToLive.getDistances().setPatrickWorkTolls(((CheckBox) v.findViewById(R.id.distanceEditPatrickWorkTolls)).isChecked());

        textString = ((TextView) v.findViewById(R.id.distanceEditDanielleClassDistanceDrive)).getText().toString();
        if (!(textString.equals("")))
        {
            selectedPlaceToLive.getDistances().setDanielleClassDistanceDrive(Float.parseFloat(textString));
        }

        textString = ((TextView) v.findViewById(R.id.distanceEditDanielleClassTimeDrive)).getText().toString();
        if (!(textString.equals("")))
        {
            selectedPlaceToLive.getDistances().setDanielleClassTimeDrive(Float.parseFloat(textString));
        }

        textString = ((TextView) v.findViewById(R.id.distanceEditDanielleClassTimePublic)).getText().toString();
        if (!(textString.equals("")))
        {
            selectedPlaceToLive.getDistances().setDanielleClassTimePublic(Float.parseFloat(textString));
        }

        textString = ((TextView) v.findViewById(R.id.distanceEditSRTDistance)).getText().toString();
        if (!(textString.equals("")))
        {
            selectedPlaceToLive.getDistances().setSrtDistance(Float.parseFloat(textString));
        }

        /**
         * Price Card View
         */
        textString = ((TextView) v.findViewById(R.id.priceEditPrice)).getText().toString();
        if (!(textString.equals("")))
        {
            selectedPlaceToLive.getPrices().setPrice(Float.parseFloat(textString));
        }

        selectedPosition = ((Spinner) v.findViewById(R.id.priceEditPriceTerm)).getSelectedItemPosition();
        if (selectedPosition == 0)
        {
            selectedPlaceToLive.getPrices().setTerm(null);
        } else
        {
            selectedPlaceToLive.getPrices().setTerm(LivingConstants.termTypes.values()[selectedPosition - 1]);
        }

        textString = ((TextView) v.findViewById(R.id.priceEditLeasePeriod)).getText().toString();
        if (!(textString.equals("")))
        {
            selectedPlaceToLive.getPrices().setLeasePeriod(Float.parseFloat(textString));
        }

        selectedPosition = ((Spinner) v.findViewById(R.id.priceEditLeasePeriodTerm)).getSelectedItemPosition();
        if (selectedPosition == 0)
        {
            selectedPlaceToLive.getPrices().setLeasePeriodTerm(null);
        }
        else
        {
            selectedPlaceToLive.getPrices().setLeasePeriodTerm(LivingConstants.termTypes.values()[selectedPosition - 1]);
        }

        textString = ((TextView) v.findViewById(R.id.priceEditSecurityDeposit)).getText().toString();
        if (!(textString.equals("")))
        {
            selectedPlaceToLive.getPrices().setSecurityDeposit(Float.parseFloat(textString));
        }
        selectedPlaceToLive.getPrices().setSecurityDepositRefundable(((CheckBox) v.findViewById(R.id.priceEditSecurityDepositRefundable)).isChecked());

        selectedPosition = ((Spinner) v.findViewById(R.id.priceEditSecurityDepositType)).getSelectedItemPosition();
        if (selectedPosition == 0)
        {
            selectedPlaceToLive.getPrices().setSecurityDepositType(null);
        } else
        {
            selectedPlaceToLive.getPrices().setSecurityDepositType(LivingConstants.securityDepositTypes.values()[selectedPosition - 1]);
        }

        selectedPlaceToLive.getPrices().setElectricIncluded(((CheckBox) v.findViewById(R.id.priceEditElectricIncluded)).isChecked());
        selectedPlaceToLive.getPrices().setWaterIncluded(((CheckBox) v.findViewById(R.id.priceEditWaterIncluded)).isChecked());
        selectedPlaceToLive.getPrices().setSewageIncluded(((CheckBox) v.findViewById(R.id.priceEditSewageIncluded)).isChecked());
        selectedPlaceToLive.getPrices().setTrashIncluded(((CheckBox) v.findViewById(R.id.priceEditTrashIncluded)).isChecked());
        selectedPlaceToLive.getPrices().setGasIncluded(((CheckBox) v.findViewById(R.id.priceEditGasIncluded)).isChecked());

        textString = ((TextView) v.findViewById(R.id.priceEditDogFee)).getText().toString();
        if (!(textString.equals("")))
        {
            selectedPlaceToLive.getPrices().setDogFee(Float.parseFloat(textString));
        }

        selectedPosition = ((Spinner) v.findViewById(R.id.priceEditDogFeeTerm)).getSelectedItemPosition();
        if (selectedPosition == 0)
        {
            selectedPlaceToLive.getPrices().setDogFeeTerm(null);
        }
        else
        {
            selectedPlaceToLive.getPrices().setDogFeeTerm(LivingConstants.termTypes.values()[selectedPosition - 1]);
        }

        textString = ((TextView) v.findViewById(R.id.priceEditDogFeeDeposit)).getText().toString();
        if (!(textString.equals("")))
        {
            selectedPlaceToLive.getPrices().setDogFeeDeposit(Float.parseFloat(textString));
        }
        selectedPlaceToLive.getPrices().setDogFeeDepositRefundable(((CheckBox) v.findViewById(R.id.priceEditDogFeeDepositRefundable)).isChecked());

        /**
         * Notes Edit Card
         */
        textString = ((TextView) v.findViewById(R.id.notesEditNotes)).getText().toString();
        if (!(textString.equals("")))
        {
            selectedPlaceToLive.setPlaceToLiveNotes(textString);
        }

        /**
         * Contact Edit Card
         */
        selectedPosition = ((Spinner) v.findViewById(R.id.contactEditLastContactType)).getSelectedItemPosition();
        if (selectedPosition == 0)
        {
            selectedPlaceToLive.getContact().setContactType(null);
        } else
        {
            selectedPlaceToLive.getContact().setContactType(LivingConstants.contactType.values()[selectedPosition - 1]);
        }

        textString = ((TextView) v.findViewById(R.id.contactEditLastContactDate)).getText().toString();
        if (textString != null)
        {
            DateUtil dateUtil = new DateUtil();
            selectedPlaceToLive.getContact().setContactDate(dateUtil.createCalendarFromString(textString));
        }

        /**
         * DAO
         */
        LivingDao dao = new LivingDao(getActivity());
        dao.save(selectedPlaceToLive);
        logger.logExit();
    }
}
