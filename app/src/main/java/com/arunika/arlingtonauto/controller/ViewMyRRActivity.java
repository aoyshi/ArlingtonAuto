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

import com.arunika.arlingtonauto.R;
import com.arunika.arlingtonauto.controller.BaseMenuActivity;

import java.util.ArrayList;

/**
 * View My RRs
 * Cancel My RR
 */
public class ViewMyRRActivity extends BaseMenuActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_my_rr);
        ListView listView=(ListView)findViewById(R.id.requestList);
        //ArrayList<CustomObject> objects = new ArrayList<CustomObject>();
        //CustomAdapter customAdapter = new CustomAdapter(this, objects, this);
        //listView.setAdapter(customAdapter);
    }

}
