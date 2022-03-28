package com.example.carparkingtracker;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Set;


public class WelcomeActivity extends Activity {
    private Intent Shakespeare;
    private Intent Cervantes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_layout);
        getActionBar().setDisplayShowTitleEnabled(false);
        String lastActivity = PreferenceManager.getDefaultSharedPreferences(this).getString("last_activity", "");
        String savePreference = PreferenceManager.getDefaultSharedPreferences(this).getString("Preference", "Pick Up where you left off");
        String favoriteGarage = PreferenceManager.getDefaultSharedPreferences(this).getString("Favorite Garage", "Shakespeare");
        int floorNumber = PreferenceManager.getDefaultSharedPreferences(this).getInt("Floor#", 2);
        Shakespeare = new Intent(this, ShakespeareActivity.class);
        Cervantes = new Intent(this, CervantesActivity.class);
        if(savePreference.equals("Pick Up where you left off")) {
            Log.d("TAG", "Pick Up where you left off if");
            if (lastActivity.equals("ShakespeareActivity")) {
                startActivity(Shakespeare);
            }
            else if (lastActivity.equals("CervantesActivity")) {
                startActivity(Cervantes);
            }
        }
        else if(savePreference.equals("Favorite Garage")) {
                Log.d("TAG", "Favorite Garage if");
                if (favoriteGarage.equals("Shakespeare")) {
                    //Shakespeare.putExtra("Floor Number", floorNumber)
                    startActivity(Shakespeare);
                }
                else if (favoriteGarage.equals("Cervantes"))
                    startActivity(Cervantes.putExtra("Floor Number", floorNumber));
            }
        Log.d("welcome", "onCreate: ");
        //placeholder for my sanity
        //startActivity(new Intent(this, SettingsActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.Shakespear)
        {
            startActivity(Shakespeare);
            return true;
        }
        else if(id == R.id.Cervantes)
        {
            startActivity(Cervantes);
            return true;
        }
        else if(id == R.id.Settings)
        {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
