package com.patrickwshaw.apartmenttracker.view.fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.melnykov.fab.FloatingActionButton;
import com.patrickwshaw.apartmenttracker.R;
import com.patrickwshaw.apartmenttracker.constants.LivingConstants;
import com.patrickwshaw.apartmenttracker.model.database.dao.LivingDao;
import com.patrickwshaw.apartmenttracker.serviceInterfaces.ApartmentServiceInterface;
import com.patrickwshaw.apartmenttracker.utility.LoggingUtil;
import com.patrickwshaw.apartmenttracker.utility.NetworkUtil;
import com.patrickwshaw.apartmenttracker.view.adapter.SelectPlaceRecyclerViewAdapter;
import com.patrickwshaw.apartmenttracker.view.dialog.DialogFactory;
import com.patrickwshaw.apartmenttracker.view.listener.AddNewPlaceListener;
import com.patrickwshaw.apartmenttracker.view.listener.SelectPlaceOnTouchListener;

/**
 * Created by Patrick on 3/27/2015.
 */
public class SelectPlaceFragment extends Fragment
{
    private LoggingUtil logger = new LoggingUtil("SelectPlaceFragment", "SelectPlaceFragment");

    private static LivingDao livingDao;

    private static RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        logger.logEnter("onCreate");

        getActivity().setTitle(R.string.selectPlaceFragmentTitle);

        //turn on the options menu
        setHasOptionsMenu(true);

        livingDao = new LivingDao(getActivity());

        if (livingDao.getAll().size() == 0)
        {
            logger.d("There are currently no places to live in the database - we should probably query the server");
            DialogInterface.OnClickListener yesNoListener = new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    switch (which)
                    {
                        case DialogInterface.BUTTON_POSITIVE:
                            logger.d("The user selected to get the latest from the server - do it");
                            ApartmentServiceInterface asi = new ApartmentServiceInterface(getActivity());
                            asi.updateApartmentsFromServer();
                            break;
                        case DialogInterface.BUTTON_NEGATIVE:
                            logger.d("The user selected not to update from the server - don't do anything");
                            break;
                        default:
                            logger.e("The user somehow found a third button!! ERROR");
                            break;
                    }
                }
            };

            AlertDialog yesNoPrompt = DialogFactory.createYesNoPrompt(getActivity(), R.string.update_checklist_from_server_prompt_title, R.string.update_checklist_from_server_prompt_text, R.string.update_checklist_from_server_prompt_positive_label, R.string.update_checklist_from_server_prompt_negative_label, yesNoListener);
            yesNoPrompt.show();
        }

        logger.logExit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState)
    {
        super.onCreateView(inflater, parent, savedInstanceState);
        logger.logEnter("onCreateView");

        //inflate the view
        View v = inflater.inflate(R.layout.select_apartment_fragment_layout, parent, false);

        //setup the recyclerview
        recyclerView = (RecyclerView) v.findViewById(R.id.selectPlaceRecyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //add the ontouchadapter
        recyclerView.addOnItemTouchListener(new SelectPlaceOnTouchListener(getActivity(), new SelectPlaceOnTouchListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                logger.logEnter("onItemClick");
                logger.d("Position " + position + " was clicked!");
                SelectPlaceRecyclerViewAdapter adapter = (SelectPlaceRecyclerViewAdapter) recyclerView.getAdapter();

                logger.d("Complex: " + adapter.getItemAt(position).getName() + " was clicked!");

                logger.d("Opening the View Place Fragment...");

                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                Fragment viewPlaceFragment = new ViewPlaceFragment();

                //pack up a bundle that holds the object the user selected
                Bundle args = new Bundle();
                args.putSerializable(LivingConstants.SELECTED_PLACE_TO_LIVE_TAG, adapter.getItemAt(position));

                viewPlaceFragment.setArguments(args);

                transaction.replace(R.id.fragmentContainer, viewPlaceFragment);

                transaction.addToBackStack(this.getClass().getSimpleName());

                transaction.commit();

                logger.logExit();

            }
        }));

        //setup the adapter
        SelectPlaceRecyclerViewAdapter placeRecyclerViewAdapter = new SelectPlaceRecyclerViewAdapter(livingDao.getAll());

        //PWS: If we eventually need a handler for the adapter, here's the place

        recyclerView.setAdapter(placeRecyclerViewAdapter);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        /**
        * Add Button
        */
        FloatingActionButton addButton = (FloatingActionButton) v.findViewById(R.id.selectPlaceAddButton);
        addButton.setOnClickListener(new AddNewPlaceListener(getActivity()));
        addButton.attachToRecyclerView(recyclerView);

        logger.logExit();
        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        logger.logEnter("onCreateOptionsMenu");

        inflater.inflate(R.menu.select_place_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);

        logger.logExit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        logger.logEnter("onOptionsItemSelected");
        switch (menuItem.getItemId())
        {
            case R.id.select_apartment_update_from_server:
                logger.d("Id matched select apartment test get");
                ApartmentServiceInterface asiGet = new ApartmentServiceInterface(getActivity());
                asiGet.updateApartmentsFromServer();
                logger.logExit();
                return true;
            case R.id.select_apartment_post_to_server:
                logger.d("Id matched select apartment test post");
                ApartmentServiceInterface asiPost = new ApartmentServiceInterface(getActivity());
                asiPost.postAllApartments();
                logger.logExit();
                return true;
            default:
                logger.d("Matched nothing - in the default case");
                logger.logExit();
                return false;
        }
    }

    public static void refreshAdapter()
    {
        SelectPlaceRecyclerViewAdapter placeRecyclerViewAdapter = new SelectPlaceRecyclerViewAdapter(livingDao.getAll());
        recyclerView.setAdapter(placeRecyclerViewAdapter);
    }
}
