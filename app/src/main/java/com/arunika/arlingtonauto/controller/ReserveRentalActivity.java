package com.arunika.arlingtonauto.controller;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.arunika.arlingtonauto.R;

public class ReserveRentalActivity extends BaseMenuActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_rental_search_results);
        //get list from intent
        //populate listview with it
    }
    public void onReserve(View view) {
        Toast.makeText(this, "I work!!!", Toast.LENGTH_SHORT).show();
        //send selected res to confirm activity
    }
}
