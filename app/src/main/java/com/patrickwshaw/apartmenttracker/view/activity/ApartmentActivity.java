package com.patrickwshaw.apartmenttracker.view.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.patrickwshaw.apartmenttracker.R;
import com.patrickwshaw.apartmenttracker.utility.LoggingUtil;
import com.patrickwshaw.apartmenttracker.view.fragment.SelectPlaceFragment;


public class ApartmentActivity extends ActionBarActivity {

    private LoggingUtil logger = new LoggingUtil("ApartmentActivity", "ApartmentActivity");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logger.logEnter("onCreate");

        setContentView(R.layout.activity_apartment_layout);

        FragmentManager fm = getFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);

        if (fragment == null)
        {
            logger.d("Fragment was unll - adding a new one");
            fragment = new SelectPlaceFragment();
            fm.beginTransaction()
                    .add(R.id.fragmentContainer, fragment)
                    .commit();
        }

        /*
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        */

        logger.logExit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        logger.logEnter("onCreateOptionsMenu");
        getMenuInflater().inflate(R.menu.menu_apartment, menu);
        logger.logExit();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        logger.logEnter("onOptionsItemSelected");
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            logger.logExit();
            return true;
        }
        logger.logExit();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed()
    {
        logger.logEnter("onBackPressed()");
        logger.d("There are: " + getFragmentManager().getBackStackEntryCount() + " entries in the back stack");
        if (getFragmentManager().getBackStackEntryCount() > 0)
        {
            logger.d("Popping hte back stack...");
            getFragmentManager().popBackStack();
        }
        else
        {
            logger.d("Calling super.onBackPressed");
            super.onBackPressed();
        }
        logger.logExit();
    }
}
