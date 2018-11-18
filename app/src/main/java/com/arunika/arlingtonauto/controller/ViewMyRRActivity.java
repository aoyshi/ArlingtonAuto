package com.arunika.arlingtonauto.controller;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.arunika.arlingtonauto.R;
import com.arunika.arlingtonauto.controller.BaseMenuActivity;
import com.arunika.arlingtonauto.model.ReservationDetails;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * View My RRs
 * Cancel My RR
 */
public class ViewMyRRActivity extends BaseMenuActivity {

    ArrayList<ReservationDetails> myResList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_my_rr);
        //ListView listView = (ListView)findViewById(R.id.);
        //get list from intent
        myResList = (ArrayList<ReservationDetails>)
                getIntent().getSerializableExtra("myResList");
        Toast.makeText(this, "size: "+myResList.size(), Toast.LENGTH_SHORT).show();
        //populate listview with it
        //CustomAdapter customAdapter = new CustomAdapter(this, availableResDetailsList, this);
        //listView.setAdapter(customAdapter);
    }

}
