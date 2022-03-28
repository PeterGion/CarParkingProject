package com.example.carparkingtracker;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;

//this is exactly the same as shakespeare reference comments for each method
public class CervantesActivity extends Activity implements GestureDetector.OnGestureListener {
    private Fragment FirstFloorFragment;
    private Fragment SecondFloorFragment;
    private Fragment ThirdFloorFragment;
    private Fragment FourthFloorFragment;
    private static final int SWIPE_THRESHOLD = 100;
    private static final int SWIPE_VELOCITY_THRESHOLD = 100;
    private int floorNumber;
    private SharedPreferences preferances;
    private TextView floorLabel;
    private Gestures gestures;
    protected GestureDetector myDector;
    private SharedPreferences.Editor editor;
    private boolean hasAFavoriteFloorNumber;
    private boolean favoriteIsThis;
    private Floor firstFloorData;
    private Floor secondFloorData;
    private Floor thirdFloorData;
    private Floor fourthFloorData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cervantes_layout);
        //create all of the floor data in the activity so that it isn't created everytime the fragment is created
        firstFloorData = new Floor(1);
        FirstFloorFragment = cervantesFirstFloorFragment.newInstance(firstFloorData);
        secondFloorData = new Floor(2);
        SecondFloorFragment = cervantesSecondFloorFragment.newInstance(secondFloorData);
        thirdFloorData = new Floor(3);
        ThirdFloorFragment = cervantesThirdFloorFragment.newInstance(thirdFloorData);
        fourthFloorData = new Floor(4);
        FourthFloorFragment = cervantesFourthFloorFragment.newInstance(fourthFloorData);

        floorLabel = (TextView) findViewById(R.id.FloorNumber);
        gestures = new Gestures(this);
        myDector = new GestureDetector(this, this);
        editor = PreferenceManager.getDefaultSharedPreferences(this).edit();
        preferances = PreferenceManager.getDefaultSharedPreferences(this);
        Intent intent = getIntent();
        //hasAFavoriteFloorNumber = intent.getIntExtra("Floor Number", 0) != 0;
        favoriteIsThis = preferances.getString("Favorite Garage","").equals("Cervantes");
        hasAFavoriteFloorNumber = preferances.getString("Preference","").equals("Favorite Garage");
        if(hasAFavoriteFloorNumber && favoriteIsThis) {
            floorNumber = preferances.getInt("Floor#",1);
            setFloor(floorNumber);
            Log.d("shakespeare if", "hasAFavoriteFloorNumber " + intent.getIntExtra("Floor Number", 0));
        }
        else {
            floorNumber = PreferenceManager.getDefaultSharedPreferences(this).getInt("Last Floor#", 1);
            setFloor(floorNumber);
            Log.d("shakespeare else", "Doesn't have AFavoriteFloorNumber " + PreferenceManager.getDefaultSharedPreferences(this).getInt("Last Floor#", 0) + "");
        }
    }

    public void attachFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.FragmentH, fragment);
        fragmentTransaction.commit();
    }
    public void attachNextFragment()
    {
        if(floorNumber == 1) {
            setFloor(2);
            floorNumber++;
        }
        else if(floorNumber == 2) {
            setFloor(3);
            floorNumber++;
        }
        else if(floorNumber == 3)
        {
            setFloor(4);
            floorNumber++;
        }
        if(hasAFavoriteFloorNumber == false)
            editor.putInt("Last Floor#", floorNumber).commit();

        /*
        currFragment++;
        if(currFragment == 2)
        {
            attachFragment(SecondFloor);
        }
        //floorLabel.setText("Floor " + currFragme nt);

         */
    }
    public void setFloor(int floorNumber)
    {
        if(floorNumber == 1)
        {
            attachFragment(FirstFloorFragment);
            floorLabel.setText("Floor 1");
        }
        else if(floorNumber == 2) {
            attachFragment(SecondFloorFragment);
            floorLabel.setText("Floor 2");
        }
        else if(floorNumber == 3) {
            attachFragment(ThirdFloorFragment);
            floorLabel.setText("Floor 3");
        }
        else if(floorNumber == 4)
        {
            attachFragment(FourthFloorFragment);
            floorLabel.setText("Floor 4");
        }
    }
    public void attachPreviousFragment()
    {
        if(floorNumber == 2) {
            setFloor(1);
            floorNumber--;
        }
        else if(floorNumber == 3) {
            setFloor(2);
            floorNumber--;
        }
        else if(floorNumber == 4) {
            setFloor(3);
            floorNumber--;
        }
        if(hasAFavoriteFloorNumber == false)
            editor.putInt("Last Floor#", floorNumber).commit();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return myDector.onTouchEvent(event);
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(this).edit();
        editor.putString("last_activity", "CervantesActivity");
        editor.commit();
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        try {
            float diffY = e2.getRawY() - e1.getRawY();
            float diffX = e2.getRawX() - e1.getRawX();
            if (Math.abs(diffX) > Math.abs(diffY)) {
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0) {
                        //onSwipeRight
                        attachPreviousFragment();
                        Log.d("cervantes", "onSwipeRight " + floorNumber);
                    } else {
                        //onSwipeLeft
                        attachNextFragment();
                        Log.d("cervantes", "onSwipeLeft " + floorNumber);
                    }
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return true;
    }
}
