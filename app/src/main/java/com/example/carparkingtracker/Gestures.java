package com.example.carparkingtracker;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

//not used at all
//originally this was going to be used to detect a swipe in the fragment and the activity class but it turns out that this is not needed to active this functionality
public class Gestures implements GestureDetector.OnGestureListener{
    private final GestureDetector aGesture;
    private static final int SWIPE_THRESHOLD = 100;
    private static final int SWIPE_VELOCITY_THRESHOLD = 100;
    private int currFragment;
    private Fragment FirstFloor;
    private Fragment SecondFloor;
    private ShakespeareActivity shakespeareActivity;
    public Gestures(Context context)
    {
        aGesture = new GestureDetector(context, this);
        //shakespeareActivity =
        /*
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.FragmentH, SecondFloor);

         */
        //super.attachPreviousFragment();
        //shakespeareActivity = new ShakespeareActivity();
        //attachNextFragment();
    }

    public GestureDetector getaGesture() {
        return aGesture;
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        Log.d("gestures", "onDown: ");
        return true;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    //code from https://stackoverflow.com/questions/4139288/android-how-to-handle-right-to-left-swipe-gestures
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        /*
        boolean result = false;
        try {
            float diffY = e2.getY() - e1.getY();
            float diffX = e2.getX() - e1.getX();
            if (Math.abs(diffX) > Math.abs(diffY)) {
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0) {
                        onSwipeRight();
                    } else {
                        onSwipeLeft();
                    }
                    result = true;
                }
            }
            else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                if (diffY > 0) {
                    onSwipeBottom();
                } else {
                    onSwipeTop();
                }
                result = true;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return result;

         */
        Log.d("gestures", "onFling: ");
        return true;
    }
    public void onSwipeRight() {
        //super.attachPreviousFragment();
        //Log.d("TAG", "onSwipeRight: ");

    }
    public void onSwipeLeft() {
        //super.attachNextFragment();
        //Log.d("TAG", "onSwipeLeft: ");
    }
    public void onSwipeTop() {
        //Log.d("TAG", "onSwipeTop: ");
    }

    public void onSwipeBottom() {
        //Log.d("TAG", "onSwipeBottom: ");
    }
}
