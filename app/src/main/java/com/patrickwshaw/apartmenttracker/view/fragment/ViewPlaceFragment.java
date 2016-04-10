package com.patrickwshaw.apartmenttracker.view.fragment;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

import com.melnykov.fab.FloatingActionButton;
import com.melnykov.fab.ObservableScrollView;
import com.patrickwshaw.apartmenttracker.R;
import com.patrickwshaw.apartmenttracker.constants.LivingConstants;
import com.patrickwshaw.apartmenttracker.model.model.PlaceToLive;
import com.patrickwshaw.apartmenttracker.utility.DateUtil;
import com.patrickwshaw.apartmenttracker.utility.LoggingUtil;

import org.w3c.dom.Text;

/**
 * Created by Patrick on 3/28/2015.
 */
public class ViewPlaceFragment extends Fragment
{
    private final LoggingUtil logger = new LoggingUtil("ViewPlaceFragment", "ViewPlaceFragment");

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

        View v = inflater.inflate(R.layout.view_place_fragment_layout, parent, false);

        /**
         * General View Card
         */
        CardView generalCardView = (CardView) v.findViewById(R.id.general_card_view);
        generalCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                logger.logInnerClassEnter("GeneralCardView", "onClick");
                logger.d("The user has clicked on the general card view - opening the edit view");
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                Fragment editPlaceFragment = new EditPlaceFragment();

                //pack up a bundle that holds the object the user selected
                Bundle args = new Bundle();
                args.putSerializable(LivingConstants.SELECTED_PLACE_TO_LIVE_TAG, selectedPlaceToLive);

                editPlaceFragment.setArguments(args);

                transaction.replace(R.id.fragmentContainer, editPlaceFragment);

                transaction.addToBackStack(this.getClass().getSimpleName());

                transaction.commit();
                logger.logInnerClassExit("GeneralCardView", "onClick");
            }
        });
        //making the assumption that name is never null
        ((TextView) v.findViewById(R.id.generalViewName)).setText(selectedPlaceToLive.getName());

        if (selectedPlaceToLive.getStatus() != null)
        {
            ((TextView) v.findViewById(R.id.generalViewStatus)).setText(selectedPlaceToLive.getStatus().toString());
        }

        if (selectedPlaceToLive.getType() != null)
        {
            ((TextView)v.findViewById(R.id.generalViewStyle)).setText(selectedPlaceToLive.getType().toString());
        }

        if (selectedPlaceToLive.getNumBeds() != null)
        {
            ((TextView) v.findViewById(R.id.generalViewNumBeds)).setText(selectedPlaceToLive.getNumBeds() + "");
        }

        if (selectedPlaceToLive.getNumBaths() != null)
        {
            ((TextView)v.findViewById(R.id.generalViewNumBaths)).setText(selectedPlaceToLive.getNumBaths() + "");
        }

        if (selectedPlaceToLive.getNumFloors() != null)
        {
            ((TextView)v.findViewById(R.id.generalViewNumFloors)).setText(selectedPlaceToLive.getNumFloors() + "");
        }

        if (selectedPlaceToLive.getSqFt() != null)
        {
            ((TextView)v.findViewById(R.id.generalViewSqFt)).setText(selectedPlaceToLive.getSqFt() + "");
        }

        if (selectedPlaceToLive.getRating() != null)
        {
            ((TextView)v.findViewById(R.id.generalViewRating)).setText(selectedPlaceToLive.getRating() + "");
        }

        if (selectedPlaceToLive.getAddress() != null)
        {
            String[] apartmentAddress = selectedPlaceToLive.getAddress().getAddressLines();
            if (apartmentAddress[0] != null)
            {
                ((TextView) v.findViewById(R.id.generalViewAddressLine1)).setText(apartmentAddress[0]);
            }

            if (apartmentAddress[1] != null)
            {
                ((TextView) v.findViewById(R.id.generalViewAddressLine2)).setText(apartmentAddress[1]);
            }

            if (apartmentAddress[2] != null)
            {
                ((TextView) v.findViewById(R.id.generalViewAddressLine3)).setText(apartmentAddress[2]);
            }
        }

        if (selectedPlaceToLive.getFirstFloorBedroom() != null)
        {
            ((TextView)v.findViewById(R.id.generalViewFirstFloorBedroom)).setText(selectedPlaceToLive.getFirstFloorBedroom().toString());
        }

        if (selectedPlaceToLive.getOfficeHours() != null)
        {
            ((TextView)v.findViewById(R.id.generalViewOfficeHours)).setText(selectedPlaceToLive.getOfficeHours());
        }

        if (selectedPlaceToLive.getOfficeNumber() != null)
        {
            ((TextView)v.findViewById(R.id.generalViewOfficeNumber)).setText(selectedPlaceToLive.getOfficeNumber().toString());
        }

        if (selectedPlaceToLive.getWebsite() != null)
        {
            ((TextView)v.findViewById(R.id.generalViewWebsite)).setText(selectedPlaceToLive.getWebsite());
        }

        /**
         * Amenities View Card
         */
        if (selectedPlaceToLive.getAmenities().getHasDishwasher() != null)
        {
            if (selectedPlaceToLive.getAmenities().getHasDishwasher())
            {
                ((TextView)v.findViewById(R.id.amenitiesViewDishwasher)).setText(R.string.viewComplexBooleanPositive);
            }
            else
            {
                ((TextView)v.findViewById(R.id.amenitiesViewDishwasher)).setText(R.string.viewComplexBooleanNegative);
            }
        }

        if (selectedPlaceToLive.getAmenities().getHasDisposal() != null)
        {
            if (selectedPlaceToLive.getAmenities().getHasDisposal())
            {
                ((TextView)v.findViewById(R.id.amenitiesViewDisposal)).setText(R.string.viewComplexBooleanPositive);
            }
            else
            {
                ((TextView)v.findViewById(R.id.amenitiesViewDisposal)).setText(R.string.viewComplexBooleanNegative);
            }
        }

        if (selectedPlaceToLive.getAmenities().getRange() != null)
        {
            ((TextView)v.findViewById(R.id.amenitiesViewRange)).setText(selectedPlaceToLive.getAmenities().getRange().toString());
        }

        if (selectedPlaceToLive.getAmenities().getHasFios() != null)
        {
            if (selectedPlaceToLive.getAmenities().getHasFios())
            {
                ((TextView)v.findViewById(R.id.amenitiesViewFios)).setText(R.string.viewComplexBooleanPositive);
            }
            else
            {
                ((TextView)v.findViewById(R.id.amenitiesViewFios)).setText(R.string.viewComplexBooleanNegative);
            }
        }

        if (selectedPlaceToLive.getAmenities().getAc() != null)
        {
            ((TextView)v.findViewById(R.id.amenitiesViewAC)).setText(selectedPlaceToLive.getAmenities().getAc().toString());
        }

        if (selectedPlaceToLive.getAmenities().getHeatSource() != null)
        {
            ((TextView)v.findViewById(R.id.amenitiesViewHeatSource)).setText(selectedPlaceToLive.getAmenities().getHeatSource().toString());
        }

        if (selectedPlaceToLive.getAmenities().getHeat() != null)
        {
            ((TextView)v.findViewById(R.id.amenitiesViewHeat)).setText(selectedPlaceToLive.getAmenities().getHeat().toString());
        }

        if (selectedPlaceToLive.getAmenities().getWasherDryer() != null)
        {
            ((TextView) v.findViewById(R.id.amenitiesViewWasherDryer)).setText(selectedPlaceToLive.getAmenities().getWasherDryer().toString());
        }

        if (selectedPlaceToLive.getAmenities().getFirePlace() != null)
        {
            if (selectedPlaceToLive.getAmenities().getFirePlace())
            {
                ((TextView)v.findViewById(R.id.amenitiesViewFireplace)).setText(R.string.viewComplexBooleanPositive);
            }
            else
            {
                ((TextView)v.findViewById(R.id.amenitiesViewFireplace)).setText(R.string.viewComplexBooleanNegative);
            }
        }

        if (selectedPlaceToLive.getAmenities().getGarage() != null)
        {
            ((TextView)v.findViewById(R.id.amenitiesViewGarage)).setText(selectedPlaceToLive.getAmenities().getGarage().toString());
        }

        if (selectedPlaceToLive.getAmenities().getAttic() != null)
        {
            ((TextView)v.findViewById(R.id.amenitiesViewAttic)).setText(selectedPlaceToLive.getAmenities().getAttic().toString());
        }

        if (selectedPlaceToLive.getAmenities().getBasement() != null)
        {
            ((TextView)v.findViewById(R.id.amenitiesViewBasement)).setText(selectedPlaceToLive.getAmenities().getBasement().toString());
        }

        if (selectedPlaceToLive.getAmenities().getCountertops() != null)
        {
            ((TextView)v.findViewById(R.id.amenitiesViewCountertops)).setText(selectedPlaceToLive.getAmenities().getCountertops().toString());
        }

        if (selectedPlaceToLive.getAmenities().getPatio_balcony() != null)
        {
            if (selectedPlaceToLive.getAmenities().getPatio_balcony())
            {
                ((TextView)v.findViewById(R.id.amenitiesViewPatioBalcony)).setText(R.string.viewComplexBooleanPositive);
            }
            else
            {
                ((TextView)v.findViewById(R.id.amenitiesViewPatioBalcony)).setText(R.string.viewComplexBooleanNegative);
            }
        }

        /**
         * Complex View Card
         */
        if (selectedPlaceToLive.getComplex().getHasFitnessCenter() != null)
        {
            if (selectedPlaceToLive.getComplex().getHasFitnessCenter())
            {
                ((TextView) v.findViewById(R.id.complexViewFitnessCenter)).setText(R.string.viewComplexBooleanPositive);
            }
            else
            {
                ((TextView) v.findViewById(R.id.complexViewFitnessCenter)).setText(R.string.viewComplexBooleanNegative);
            }
        }

        if (selectedPlaceToLive.getComplex().getFitnessCenterFee() != null)
        {
            if (selectedPlaceToLive.getComplex().getFitnessCenterFeeTerm() != null)
            {
                ((TextView) v.findViewById(R.id.complexViewFitnessCenterFee)).setText(selectedPlaceToLive.getComplex().getFitnessCenterFee() + "/" + selectedPlaceToLive.getComplex().getFitnessCenterFeeTerm().getDisplayString());
            }
            else
            {
                ((TextView) v.findViewById(R.id.complexViewFitnessCenterFee)).setText(selectedPlaceToLive.getComplex().getFitnessCenterFee() + "");
            }
        }

        if (selectedPlaceToLive.getComplex().getHasPool() != null)
        {
            if (selectedPlaceToLive.getComplex().getHasPool())
            {
                ((TextView) v.findViewById(R.id.complexViewPool)).setText(R.string.viewComplexBooleanPositive);
            }
            else
            {
                ((TextView) v.findViewById(R.id.complexViewPool)).setText(R.string.viewComplexBooleanNegative);
            }
        }

        if (selectedPlaceToLive.getComplex().getPoolFee() != null)
        {
            if (selectedPlaceToLive.getComplex().getPoolFeeTerm() != null)
            {
                ((TextView) v.findViewById(R.id.complexViewPoolFee)).setText(selectedPlaceToLive.getComplex().getPoolFee() + "/" + selectedPlaceToLive.getComplex().getPoolFeeTerm().getDisplayString());
            }
            else
            {
                ((TextView) v.findViewById(R.id.complexViewFitnessCenterFee)).setText(selectedPlaceToLive.getComplex().getFitnessCenterFee() + "");
            }
        }

        if (selectedPlaceToLive.getComplex().getOutdoorDogSpace() != null)
        {
            ((TextView) v.findViewById(R.id.complexViewOutdoorSpace)).setText(selectedPlaceToLive.getComplex().getOutdoorDogSpace().toString());
        }

        /**
         * Distance View Card
         */
        if (selectedPlaceToLive.getDistances().getPatrickWorkDistance() != null)
        {
            ((TextView) v.findViewById(R.id.distanceViewPatrickWorkDistance)).setText(selectedPlaceToLive.getDistances().getPatrickWorkDistance().toString());
        }

        if (selectedPlaceToLive.getDistances().getPatrickWorkTime() != null)
        {
            ((TextView) v.findViewById(R.id.distanceViewPatrickWorkTime)).setText(selectedPlaceToLive.getDistances().getPatrickWorkTime().toString());
        }

        if (selectedPlaceToLive.getDistances().getPatrickWorkTolls() != null)
        {
            if (selectedPlaceToLive.getDistances().getPatrickWorkTolls())
            {
                ((TextView) v.findViewById(R.id.distanceViewPatrickWorkTolls)).setText(R.string.viewComplexBooleanPositive);
            }
            else
            {
                ((TextView) v.findViewById(R.id.distanceViewPatrickWorkTolls)).setText(R.string.viewComplexBooleanNegative);
            }
        }

        if (selectedPlaceToLive.getDistances().getDanielleClassDistanceDrive() != null)
        {
            ((TextView) v.findViewById(R.id.distanceViewDanielleClassDriveDistance)).setText(selectedPlaceToLive.getDistances().getDanielleClassDistanceDrive().toString());
        }

        if (selectedPlaceToLive.getDistances().getDanielleClassTimeDrive() != null)
        {
            ((TextView) v.findViewById(R.id.distanceViewDanielleClassDriveTime)).setText(selectedPlaceToLive.getDistances().getDanielleClassTimeDrive().toString());
        }

        if (selectedPlaceToLive.getDistances().getDanielleClassTimePublic() != null)
        {
            ((TextView) v.findViewById(R.id.distanceViewDanielleClassPublicTime)).setText(selectedPlaceToLive.getDistances().getDanielleClassTimePublic().toString());
        }

        if (selectedPlaceToLive.getDistances().getSrtDistance() != null)
        {
            ((TextView) v.findViewById(R.id.distanceViewSRTDistance)).setText(selectedPlaceToLive.getDistances().getSrtDistance().toString());
        }

        /**
         * Price View Card
         */

        if (selectedPlaceToLive.getPrices().getPrice() != null)
        {
            if (selectedPlaceToLive.getPrices().getTerm() != null)
            {
                ((TextView) v.findViewById(R.id.priceViewPrice)).setText(selectedPlaceToLive.getPrices().getPrice().toString() + "/" + selectedPlaceToLive.getPrices().getTerm().getDisplayString());
            }
            else
            {
                ((TextView) v.findViewById(R.id.priceViewPrice)).setText(selectedPlaceToLive.getPrices().getPrice().toString());
            }
        }

        if (selectedPlaceToLive.getPrices().getSecurityDeposit() != null)
        {
            ((TextView) v.findViewById(R.id.priceViewSecurityDeposit)).setText(selectedPlaceToLive.getPrices().getSecurityDeposit().toString());
        }

        if (selectedPlaceToLive.getPrices().getSecurityDepositRefundable() != null)
        {
            if (selectedPlaceToLive.getPrices().getSecurityDepositRefundable())
            {
                ((TextView) v.findViewById(R.id.priceViewSecurityDepositRefundable)).setText(R.string.viewComplexBooleanPositive);
            }
            else
            {
                ((TextView) v.findViewById(R.id.priceViewSecurityDepositRefundable)).setText(R.string.viewComplexBooleanNegative);
            }
        }

        if (selectedPlaceToLive.getPrices().getSecurityDepositType() != null)
        {
            ((TextView) v.findViewById(R.id.priceViewSecurityDepositType)).setText(selectedPlaceToLive.getPrices().getSecurityDepositType().toString());
        }

        if (selectedPlaceToLive.getPrices().getElectricIncluded() != null)
        {
            if (selectedPlaceToLive.getPrices().getElectricIncluded())
            {
                ((TextView) v.findViewById(R.id.priceViewElectricIncluded)).setText(R.string.viewComplexBooleanPositive);
            }
            else
            {
                ((TextView) v.findViewById(R.id.priceViewElectricIncluded)).setText(R.string.viewComplexBooleanNegative);
            }
        }

        if (selectedPlaceToLive.getPrices().getSewageIncluded() != null)
        {
            if (selectedPlaceToLive.getPrices().getSewageIncluded())
            {
                ((TextView) v.findViewById(R.id.priceViewSewageIncluded)).setText(R.string.viewComplexBooleanPositive);
            }
            else
            {
                ((TextView) v.findViewById(R.id.priceViewSewageIncluded)).setText(R.string.viewComplexBooleanNegative);
            }
        }

        if (selectedPlaceToLive.getPrices().getWaterIncluded() != null)
        {
            if (selectedPlaceToLive.getPrices().getWaterIncluded())
            {
                ((TextView) v.findViewById(R.id.priceViewWaterIncluded)).setText(R.string.viewComplexBooleanPositive);
            }
            else
            {
                ((TextView) v.findViewById(R.id.priceViewWaterIncluded)).setText(R.string.viewComplexBooleanNegative);
            }
        }

        if (selectedPlaceToLive.getPrices().getGasIncluded() != null)
        {
            if (selectedPlaceToLive.getPrices().getGasIncluded())
            {
                ((TextView) v.findViewById(R.id.priceViewGasIncluded)).setText(R.string.viewComplexBooleanPositive);
            }
            else
            {
                ((TextView) v.findViewById(R.id.priceViewGasIncluded)).setText(R.string.viewComplexBooleanNegative);
            }
        }

        if (selectedPlaceToLive.getPrices().getTrashIncluded() != null)
        {
            if (selectedPlaceToLive.getPrices().getTrashIncluded())
            {
                ((TextView) v.findViewById(R.id.priceViewTrashIncluded)).setText(R.string.viewComplexBooleanPositive);
            }
            else
            {
                ((TextView) v.findViewById(R.id.priceViewTrashIncluded)).setText(R.string.viewComplexBooleanNegative);
            }
        }

        if (selectedPlaceToLive.getPrices().getDogFee() != null)
        {
            if (selectedPlaceToLive.getPrices().getDogFeeTerm() != null)
            {
                ((TextView) v.findViewById(R.id.priceViewDogFee)).setText(selectedPlaceToLive.getPrices().getDogFee().toString() + "/" + selectedPlaceToLive.getPrices().getDogFeeTerm().getDisplayString());
            }
            else
            {
                ((TextView) v.findViewById(R.id.priceViewDogFee)).setText(selectedPlaceToLive.getPrices().getDogFee().toString());
            }
        }

        if (selectedPlaceToLive.getPrices().getDogFeeDeposit() != null)
        {
            ((TextView) v.findViewById(R.id.priceViewDogFeeDeposit)).setText(selectedPlaceToLive.getPrices().getDogFeeDeposit().toString());
        }

        if (selectedPlaceToLive.getPrices().getDogFeeDepositRefundable() != null)
        {
            if (selectedPlaceToLive.getPrices().getDogFeeDepositRefundable())
            {
                ((TextView) v.findViewById(R.id.priceViewDogFeeDepositRefundable)).setText(R.string.viewComplexBooleanPositive);
            }
            else
            {
                ((TextView) v.findViewById(R.id.priceViewDogFeeDepositRefundable)).setText(R.string.viewComplexBooleanNegative);
            }
        }

        /**
         * Notes View Card
         */
        if (selectedPlaceToLive.getPlaceToLiveNotes() != null)
        {
            ((TextView) v.findViewById(R.id.notesViewNotes)).setText(selectedPlaceToLive.getPlaceToLiveNotes());
        }

        /**
         * Contact View Card
         */
        if (selectedPlaceToLive.getContact() != null)
        {
            if (selectedPlaceToLive.getContact().getContactType() != null)
            {
                ((TextView) v.findViewById(R.id.contactViewLastContactType)).setText(selectedPlaceToLive.getContact().getContactType().toString());
            }

            if (selectedPlaceToLive.getContact().getContactDate() != null)
            {
                DateUtil de = new DateUtil();
                ((TextView) v.findViewById(R.id.contactViewLastContactDate)).setText(de.createDateString(selectedPlaceToLive.getContact().getContactDate()));
            }
        }

        /**
         * Scroll View
         */
        ObservableScrollView viewScrollView = (ObservableScrollView) v.findViewById(R.id.viewPlaceScrollView);

        /**
         * Save Button
         */
        FloatingActionButton editButton = (FloatingActionButton) v.findViewById(R.id.selectPlaceEditButton);
        editButton.setTag(v);
        editButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View button)
            {
                logger.logInnerClassEnter("EditButton", "onClick");

                logger.d("The user selected to edit the place to live - starting the transaction");

                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                Fragment editPlaceFragment = new EditPlaceFragment();

                Bundle args = new Bundle();
                args.putSerializable(LivingConstants.SELECTED_PLACE_TO_LIVE_TAG, selectedPlaceToLive);

                editPlaceFragment.setArguments(args);

                transaction.replace(R.id.fragmentContainer, editPlaceFragment);

                transaction.addToBackStack(this.getClass().getSimpleName());

                transaction.commit();

                logger.logInnerClassExit("EditButton", "onClick");
            }
        });
        editButton.attachToScrollView(viewScrollView);

        logger.logExit();
        return v;
    }
}
