package com.example.carparkingtracker;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class SettingsActivity extends Activity {
    private RadioGroup setting;
    private RadioGroup garageSelection;
    private RadioGroup floor;
    private RadioButton floorOne;
    private RadioButton floorTwo;
    private RadioButton floorThree;
    private RadioButton floorFour;
    private TextView garageSelectionText;
    private TextView floorText;
    private SharedPreferences.Editor editor;
    private SharedPreferences getter;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_layout);
        setting = (RadioGroup) findViewById(R.id.setting);

        garageSelection = (RadioGroup) findViewById(R.id.GarageSelection);
        floor = (RadioGroup) findViewById(R.id.floorNumbers);
        floorOne = (RadioButton) findViewById(R.id.floorOne);
        floorTwo = (RadioButton) findViewById(R.id.floorTwo);
        floorThree = (RadioButton) findViewById(R.id.floorThree);
        floorFour = (RadioButton) findViewById(R.id.floorFour);

        setting.setOnCheckedChangeListener(settingsListener);
        garageSelection.setOnCheckedChangeListener(settingsListener);
        floor.setOnCheckedChangeListener(settingsListener);
        garageSelectionText = (TextView) findViewById(R.id.garageSelection);
        floorText = (TextView) findViewById(R.id.floor);
        editor = PreferenceManager.getDefaultSharedPreferences(getBaseContext()).edit();
        getter = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        if(getter.getString("Preference","").equals("Pick Up where you left off"))
        {
            setting.check(R.id.Pickup);
        }
        else if(getter.getString("Preference","").equals("Favorite Garage"))
        {
            setting.check(R.id.favorite);
            if(getter.getString("Favorite Garage","").equals("Shakespeare"))
                garageSelection.check(R.id.Shakespeare);
            else if (getter.getString("Favorite Garage","").equals("Cervantes"))
                garageSelection.check(R.id.Cervantes_selection);

            if(getter.getInt("Floor#",0) == 1)
                floor.check(R.id.floorOne);
            else if(getter.getInt("Floor#",0) == 2)
                floor.check(R.id.floorTwo);
            else if(getter.getInt("Floor#",0) == 3)
                floor.check(R.id.floorThree);
            else if(getter.getInt("Floor#",0) == 4)
                floor.check(R.id.floorFour);
        }
    }

    private RadioGroup.OnCheckedChangeListener settingsListener = new RadioGroup.OnCheckedChangeListener() {
        public void onCheckedChanged(RadioGroup rbGroup, int radioId) {
            switch (radioId) {
                case R.id.Pickup:
                    editor.putString("Preference", "Pick Up where you left off");
                    editor.commit();
                    garageSelection.setVisibility(View.INVISIBLE);
                    garageSelectionText.setVisibility(View.INVISIBLE);
                    floor.setVisibility(View.INVISIBLE);
                    floorText.setVisibility(View.INVISIBLE);
                    Log.d("TAG", "Pick Up where you left off");

                    break;
                case R.id.favorite:
                    editor.putString("Preference", "Favorite Garage");
                    editor.commit();
                    garageSelection.setVisibility(View.VISIBLE);
                    garageSelectionText.setVisibility(View.VISIBLE);
                    floor.setVisibility(View.VISIBLE);
                    floorText.setVisibility(View.VISIBLE);
                    Log.d("TAG", "favorite");
                    break;
                case R.id.Shakespeare:
                    //PreferenceManager.getDefaultSharedPreferences(getBaseContext()).edit().putInt("Floor#", 0);
                    editor.putString("Favorite Garage", "Shakespeare");
                    editor.commit();
                    Log.d("TAG", "Shakespeare");
                    Log.d("TAG", "Floor# " + PreferenceManager.getDefaultSharedPreferences(getBaseContext()).getInt("Floor#", 0));
                    break;
                case R.id.Cervantes_selection:
                    editor.putString("Favorite Garage", "Cervantes");
                    editor.commit();
                    Log.d("TAG", "Cervantes");
                    break;
            }
            if(radioId == R.id.floorOne)
            {
                editor.putInt("Floor#", 1);
                editor.commit();
                Log.d("TAG", "1");
            }
            else if(radioId == R.id.floorTwo)
            {
                editor.putInt("Floor#", 2);
                editor.commit();
                Log.d("TAG", "2");
            }
            else if(radioId == R.id.floorThree)
            {
                editor.putInt("Floor#", 3);
                editor.commit();
                Log.d("TAG", "3");
            }
            else if(radioId == R.id.floorFour)
            {
                editor.putInt("Floor#", 4);
                editor.commit();
                Log.d("TAG", "4");
            }
        }
    };
}
