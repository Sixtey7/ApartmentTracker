package com.patrickwshaw.apartmenttracker.view.listener;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.patrickwshaw.apartmenttracker.utility.LoggingUtil;

/**
 * Created by Patrick on 3/28/2015.
 */
public class SelectPlaceOnTouchListener implements RecyclerView.OnItemTouchListener
{

    private final LoggingUtil logger = new LoggingUtil("SelectPlaceOnTouchListener", "SelectPlaceOnTouchListener");

    private OnItemClickListener mListener;

    public interface  OnItemClickListener
    {
        public void onItemClick(View view, int position);
    }


    GestureDetector mGestureDetector;

    public SelectPlaceOnTouchListener(Context context, OnItemClickListener listener)
    {
        logger.logEnter("Constructor");
        mListener = listener;

        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
        logger.logExit();
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent)
    {
        logger.logEnter("onInterceptTouchEvent");
        View childView = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
        if (childView != null && mListener != null && mGestureDetector.onTouchEvent(motionEvent))
        {
            mListener.onItemClick(childView, recyclerView.getChildPosition(childView));
            logger.logExit();
            return true;
        }

        logger.logExit();
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent)
    {}
}
