package com.patrickwshaw.apartmenttracker.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.patrickwshaw.apartmenttracker.R;
import com.patrickwshaw.apartmenttracker.model.model.PlaceToLive;
import com.patrickwshaw.apartmenttracker.utility.LoggingUtil;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Patrick on 3/27/2015.
 */
public class SelectPlaceRecyclerViewAdapter extends RecyclerView.Adapter<SelectPlaceRecyclerViewAdapter.ViewHolder>
{
    private LoggingUtil logger = new LoggingUtil("SelectPlaceRecyclerViewAdapter", "SelectPlaceRecyclerViewAdapter");

    private List<PlaceToLive> placeToLiveList;

    public SelectPlaceRecyclerViewAdapter(List<PlaceToLive> placeToLiveList)
    {
        logger.logEnter("Constructor");
        this.placeToLiveList = placeToLiveList;
        logger.logExit();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        logger.logEnter("onCreateViewHolder");
        //create a new view
        View itemLayoutView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.select_apartment_fragment_row_layout, null);

        //create ViewHolder
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        logger.logExit();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i)
    {
        logger.logEnter("onBindViewHolder");
        //find the place to live entry that we're binding
        PlaceToLive placeToBind = placeToLiveList.get(i);

        //bind the data to the view holder
        viewHolder.placeName.setText(placeToBind.getName());

        if (placeToBind.getStatus() != null)
        {
            viewHolder.placeStatus.setText(placeToBind.getStatus().toString());
        }

        String[] placeAddressLines = placeToBind.getAddress().getAddressLines();
        if (placeAddressLines[0] != null)
        {
            viewHolder.placeAddressLine1.setText(placeAddressLines[0]);
        }

        if (placeAddressLines[1] != null)
        {
            viewHolder.placeAddressLine2.setText(placeAddressLines[1]);
        }

        if (placeAddressLines[2] != null)
        {
            viewHolder.placeAddressLine3.setText(placeAddressLines[2]);
        }

        if (placeToBind.getOfficeNumber() != null)
        {
            viewHolder.placeOfficeNumber.setText(placeToBind.getOfficeNumber().toString());
        }

        if (placeToBind.getOfficeHours() != null)
        {
            viewHolder.placeOfficeHours.setText(placeToBind.getOfficeHours());
        }

        if (placeToBind.getWebsite() != null)
        {
            viewHolder.placeWebsite.setText(placeToBind.getWebsite());
        }

        logger.logExit();
    }

    @Override
    public int getItemCount()
    {
        return placeToLiveList.size();
    }

    public PlaceToLive getItemAt(int position)
    {
        logger.logEnter("getItemAt");
        logger.logExit();
        return placeToLiveList.get(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView placeName;
        public TextView placeStatus;
        public TextView placeAddressLine1;
        public TextView placeAddressLine2;
        public TextView placeAddressLine3;
        public TextView placeOfficeNumber;
        public TextView placeOfficeHours;
        public TextView placeWebsite;

        public ViewHolder(View itemLayoutView)
        {
            super(itemLayoutView);
            placeName = (TextView) itemLayoutView.findViewById(R.id.selectPlaceCardPlaceName);
            placeStatus = (TextView) itemLayoutView.findViewById(R.id.selectPlaceCardStatus);
            placeAddressLine1 = (TextView) itemLayoutView.findViewById(R.id.selectPlaceCardPlaceAddressLine1);
            placeAddressLine2 = (TextView) itemLayoutView.findViewById(R.id.selectPlaceCardPlaceAddressLine2);
            placeAddressLine3 = (TextView) itemLayoutView.findViewById(R.id.selectPlaceCardPlaceAddressLine3);
            placeOfficeNumber = (TextView) itemLayoutView.findViewById(R.id.selectPlaceCardPlaceOfficePhoneNumber);
            placeOfficeHours = (TextView) itemLayoutView.findViewById(R.id.selectPlaceCardPlaceOfficeHours);
            placeWebsite = (TextView) itemLayoutView.findViewById(R.id.selectPlaceCardPlaceWebsite);
        }
    }
}
