package com.arunika.arlingtonauto.controller;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.arunika.arlingtonauto.DAO.CarDAO;
import com.arunika.arlingtonauto.R;
import com.arunika.arlingtonauto.model.Car;

import es.dmoral.toasty.Toasty;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class ViewAllAvailableCarsActivity extends BaseMenuActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_available_cars);
        ListView listView = (ListView)findViewById(R.id.all_available_cars_list);
        //get list from intent
        ArrayList<Car> allCarList = (ArrayList<Car>)
                getIntent().getSerializableExtra("allCarList");
        //populate listview with it
        CustomAdapter customAdapter = new CustomAdapter(this, allCarList, this);
        listView.setAdapter(customAdapter);
    }

    public class CustomAdapter extends BaseAdapter {
        private LayoutInflater inflater;
        private ArrayList<Car> carList;
        private Activity activity;
        private class ViewHolder {
            TextView carNameTextView;
            TextView capacityTextView;
            TextView weekdayTextView;
            TextView weekendRateTextView;
            TextView weekRateTextView;
            TextView gpsRateTextView;
            TextView onstarRateTextView;
            TextView siriusRateTextView;
        }

        public CustomAdapter(Context context, ArrayList<Car> carList, Activity activity) {
            super();
            inflater = LayoutInflater.from(context);
            this.carList = carList;
            this.activity= activity;
        }
        @Override
        public int getCount() {
            if (carList != null) {
                return carList.size();
            }
            else {
                return 0;
            }
        }
        @Override
        public Car getItem(int position) {
            return carList.get(position);
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            CustomAdapter.ViewHolder holder = new CustomAdapter.ViewHolder();
            convertView = inflater.inflate(R.layout.all_available_cars_list_item, null);

            final Car car = getItem(position);

            holder.carNameTextView = (TextView) convertView.findViewById(R.id.carName);
            holder.capacityTextView = (TextView) convertView.findViewById(R.id.capacity);
            holder.weekdayTextView = (TextView) convertView.findViewById(R.id.weekdayRate);
            holder.weekendRateTextView = (TextView) convertView.findViewById(R.id.weekendRate);
            holder.weekRateTextView = (TextView) convertView.findViewById(R.id.weekRate);
            holder.gpsRateTextView = (TextView) convertView.findViewById(R.id.gpsRate);
            holder.onstarRateTextView = (TextView) convertView.findViewById(R.id.onstarRate);
            holder.siriusRateTextView = (TextView) convertView.findViewById(R.id.siriusRate);

            holder.carNameTextView.setText("Car Name: " + carList.get(position).getName());
            holder.capacityTextView.setText("Capacity: " + carList.get(position).getCapacity());
            holder.weekdayTextView.setText("Weekday Rate: $" + carList.get(position).getWeekdayRate());
            holder.weekendRateTextView.setText("Weekend Rate: $" + carList.get(position).getWeekendRate());
            holder.weekRateTextView.setText("Week Rate: $" + carList.get(position).getWeeklyRate());
            holder.gpsRateTextView.setText("GPS Rate: $" + carList.get(position).getDailyGps());
            holder.onstarRateTextView.setText("OnStar Rate: $" + carList.get(position).getDailyOnstar());
            holder.siriusRateTextView.setText("Sirium Rate: $" + carList.get(position).getDailySirius());

            return convertView;
        }
    }
}
