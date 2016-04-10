package com.patrickwshaw.apartmenttracker.utility;

import android.util.Log;

import java.io.Serializable;
import java.util.Stack;

/**
 * Created by Patrick on 3/27/2015.
 */
public class LoggingUtil implements Serializable
{
    private String TAG;
    private String className;
    private Stack<String> classNames;

    //no one should call the default constructor
    private LoggingUtil()
    {
        classNames = new Stack<String>();

    }

    public LoggingUtil(String TAG, String className)
    {
        classNames = new Stack<String>();
        this.TAG = TAG;
        this.className = className;
    }

    public void logEnter(String methodName)
    {
        classNames.push(methodName);
        Log.d(TAG, className + " - " + methodName + " - Enter");
    }

    public void logExit()
    {
        String methodName = classNames.pop();
        Log.d(TAG, className + " - " + methodName + " - Exit");
    }

    public void logInnerClassEnter(String innerClassName, String methodName)
    {
        Log.d(TAG, className + " - " + innerClassName + " - " + methodName + " - Enter");
    }

    public void logInnerClassExit(String innerClassName, String methodName)
    {
        Log.d(TAG, className + " - " + innerClassName + " - " + methodName + " - Exit");
    }

    public void debug(String message)
    {
        Log.d(TAG, message);
    }

    public void info(String message)
    {
        Log.i(TAG, message);
    }

    public void warn(String message)
    {
        Log.w(TAG, message);
    }

    public void error(String message)
    {
        Log.e(TAG, message);
    }

    public void d(String message)
    {
        debug(message);
    }

    public void i(String message)
    {
        info(message);
    }

    public void w(String message)
    {
        warn(message);
    }

    public void e(String message)
    {
        error(message);
    }
}
