package com.arunika.arlingtonauto.controller;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.arunika.arlingtonauto.DAO.ReservationDAO;
import com.arunika.arlingtonauto.R;
import com.arunika.arlingtonauto.model.Reservation;
import com.arunika.arlingtonauto.model.ReservationDetails;
import com.arunika.arlingtonauto.model.User;
import com.google.gson.Gson;

import java.util.ArrayList;

public class FindMyRRActivity extends BaseMenuActivity {

    private EditText startDateField;
    private EditText startTimeField;
    private EditText endDateField;
    private EditText endTimeField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_my_rr_form);
        initViews();
    }

    private void initViews() {
        this.startDateField = (EditText) findViewById(R.id.startDate);
        this.startTimeField = (EditText) findViewById(R.id.startTime);
        this.endDateField = (EditText) findViewById(R.id.endDate);
        this.endTimeField = (EditText) findViewById(R.id.endTime);
    }

    public void onSearchRR(View view) {
        //get all form field data
        String startTimeAsString = startDateField.getText().toString() + " "
                + startTimeField.getText().toString();
        String endTimeAsString = endDateField.getText().toString() + " "
                + endTimeField.getText().toString();
        //get current user
        SharedPreferences preferences = getSharedPreferences("PREFS",0);
        User currentUser = new Gson().fromJson(preferences.getString("currentUser", ""), User.class);

        //get list of customer's RRs in given time range
        ArrayList<ReservationDetails> myResList = ReservationDAO.getInstance(this)
                .getCustomerReservations(startTimeAsString, endTimeAsString, currentUser.getId());
        //do stuff here

    }

}
