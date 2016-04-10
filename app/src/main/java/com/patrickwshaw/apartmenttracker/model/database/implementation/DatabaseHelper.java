package com.patrickwshaw.apartmenttracker.model.database.implementation;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.patrickwshaw.apartmenttracker.constants.DBConstants;
import com.patrickwshaw.apartmenttracker.utility.LoggingUtil;

/**
 * Created by Patrick on 4/5/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper
{
    private final LoggingUtil logger = new LoggingUtil("DatabaseHelper", "DatabaseHelper");

    public DatabaseHelper(Context context)
    {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        logger.logEnter("onCreate");

        logger.d("In OnCreate - Setting up the database");
        String tableCreateSql = DBConstants.createTableCreateText();
        logger.d(tableCreateSql);
        db.execSQL(tableCreateSql);

        logger.logExit();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        logger.logEnter("onUpdate");

        logger.d("On Upgrade - dropping the tables and recreating them");
        db.execSQL(DBConstants.createDropTableText());

        onCreate(db);

        logger.logExit();

    }
}
