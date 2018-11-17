package com.arunika.arlingtonauto;

import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

/** PLEASE NOTE:
 *  This class must be extended from all other activities you make
 *  It creates the three-dot nav menu in the action bar for Home and Logout
 *  DO NOT include in User HomeScreens or App MainScreen.
 */

public class BaseMenuActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                /*functionality hasnt been added yet*/
                Toast.makeText(this, "Go Home", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.logout:
                /*functionality hasnt been added yet*/
                Toast.makeText(this, "Logged Out", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
