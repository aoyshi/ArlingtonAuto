package com.arunika.arlingtonauto.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.arunika.arlingtonauto.R;
import com.arunika.arlingtonauto.model.User;
import com.google.gson.Gson;
import es.dmoral.toasty.Toasty;

/** All 3 HomeScreen Activities must extend SystemUserActivity.
 *  It contains 2 functions common to ALL 3 types of System Users.
 *  EditOwnProfile and Logout.
 *  This inheritance keeps code centralized and cleaner.
 */
public class AdminHomeActivity extends SystemUserActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
    }
    public void revokeRenter(View view) {
        startActivity(new Intent(this,RevokeRenterActivity.class));
    }
    public void editAnotherUser(View view) {
        startActivity(new Intent(this,FindUserProfileActivity.class));
    }
}


