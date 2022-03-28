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
import android.widget.Button;
import android.widget.TextView;
import android.view.ViewGroup;

public class ShakespeareActivity extends Activity implements GestureDetector.OnGestureListener
{
    private TextView floorLabel;
    private Fragment FirstFloorFragment;
    private Fragment SecondFloorFragment;
    private Fragment ThirdFloorFragment;
    private Fragment FourthFloorFragment;
    private static final int SWIPE_THRESHOLD = 100;
    private static final int SWIPE_VELOCITY_THRESHOLD = 100;
    private int floorNumber;
    private SharedPreferences preferances;
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
        setContentView(R.layout.shakespear_layout);
        floorLabel = (TextView) findViewById(R.id.FloorNumber);
        //creates the floor data in the activity
        firstFloorData = new Floor(1);
        //it then sends this data to the fragment
        FirstFloorFragment = firstFloorFragement.newInstance(firstFloorData);
        secondFloorData = new Floor(2);
        SecondFloorFragment = secondFloorFragment.newInstance(secondFloorData);
        thirdFloorData = new Floor(3);
        ThirdFloorFragment = thirdFloorFragment.newInstance(thirdFloorData);
        fourthFloorData = new Floor(4);
        FourthFloorFragment = fourthFloorFragment.newInstance(fourthFloorData);
        gestures = new Gestures(this);
        myDector = new GestureDetector(this, this);
        editor = PreferenceManager.getDefaultSharedPreferences(this).edit();
        preferances = PreferenceManager.getDefaultSharedPreferences(this);
        Intent intent = getIntent();
        //hasAFavoriteFloorNumber = intent.getIntExtra("Floor Number", 0) != 0;
        favoriteIsThis = preferances.getString("Favorite Garage","").equals("Shakespeare");
        hasAFavoriteFloorNumber = preferances.getString("Preference","").equals("Favorite Garage");
        //checks the settings to see weather they have "favorite garage" enabled
        if(hasAFavoriteFloorNumber && favoriteIsThis) {
            //if they do then it retrieves this floor number from the SharedPreferences
            floorNumber = preferances.getInt("Floor#",1);
            setFloor(floorNumber);
            Log.d("shakespeare if", "hasAFavoriteFloorNumber " + intent.getIntExtra("Floor Number", 0));
        }
        //runs if they have "pick up where I left off" selected
        else {
            //this will set the floor number to the last updated value of "Last Floor#"
            floorNumber = PreferenceManager.getDefaultSharedPreferences(this).getInt("Last Floor#", 1);
            setFloor(floorNumber);
        }
    }
    //this is what changes the fragments
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
        //saves the last floor to the key last floor# so it can be accessed if they have it set to "Pick up where I left off"
        if(hasAFavoriteFloorNumber == false)
            editor.putInt("Last Floor#", floorNumber).commit();
    }
    //attaches the fragment and changes the label to corresponding floor number
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
    public boolean onDown(MotionEvent motionEvent) {
        //Log.d("shakspear", "onDown: ");
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
    //attaches previous fragment if they swipe right and the next fragment if they swipe left
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
                        Log.d("Shakespeare", "onSwipeRight " + floorNumber);
                    } else {
                        //onSwipeLeft
                        attachNextFragment();
                        Log.d("Shakespeare", "onSwipeLeft " + floorNumber);
                    }
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return true;
    }

    //updates "last_activity" to the name of the activity
    @Override
    protected void onResume() {
        super.onResume();
        // if "pick up where I left off" is selected
        if(hasAFavoriteFloorNumber == false) {
            editor.putString("last_activity", "ShakespeareActivity");
            editor.commit();
        }
    }
}
