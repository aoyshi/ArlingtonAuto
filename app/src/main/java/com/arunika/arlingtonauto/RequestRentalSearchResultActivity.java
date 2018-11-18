package com.arunika.arlingtonauto;

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

import com.arunika.arlingtonauto.controller.BaseMenuActivity;

import java.util.ArrayList;

/** PLEASE NOTE:
 *  This is a temporary dummy class for populating ListView myrr_list with
 *  dummy data for the UIP screenshots.
 */

public class RequestRentalSearchResultActivity extends BaseMenuActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView=(ListView)findViewById(R.id.requestList);
        ArrayList<RequestRentalDetails> objects = new ArrayList<RequestRentalDetails>();
        //dummy data, don't take this seriously
        objects.add(new RequestRentalDetails("Economy", "$524.50","Fits 2 people"));
        objects.add(new RequestRentalDetails("Ultra", "$125.79","Fits 3 people"));
        objects.add(new RequestRentalDetails("Smart", "$320.99","Fits 4 people"));
        objects.add(new RequestRentalDetails("Intermediate", "$110.99","Fits 5 people"));
        CustomAdapter customAdapter = new CustomAdapter(this, objects, this);
        listView.setAdapter(customAdapter);
    }

    //dummy function for generating toasts upon button clicks
    public void searchRR (View view) {

    }

    //cancel button functionality has not been added yet

    class RequestRentalDetails {

        private String carName;
        private String totalPrice;
        private String capacity;
        //private Button button;

        public RequestRentalDetails(String carName, String totalPrice, String capacity){//, Button button) {
            this.carName = carName;
            this.totalPrice = totalPrice;
            this.capacity=capacity;
            //this.button=button;
        }

        public String getCarName() {
            return carName;
        }

        public void setCarName(String carName) {
            this.carName = carName;
        }

        public String getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(String totalPrice) {
            this.totalPrice = totalPrice;
        }

        public String getCapacity() {
            return capacity;
        }

        public void setCapacity(String capacity) {
            this.capacity = capacity;
        }

        /*public Button getButton() {
            return button;
        }

        public void setButton(Button button) {
            this.button = button;
        }*/

    }


    public class CustomAdapter extends BaseAdapter {

        private LayoutInflater inflater;
        private ArrayList<RequestRentalDetails> objects;
        private Activity activity;

        private class ViewHolder {
            TextView carNameTextView;
            TextView totalPriceTextView;
            TextView capacityTextView;
            Button reserveBtn;
        }


        public CustomAdapter(Context context, ArrayList<RequestRentalDetails> objects, Activity activity) {
            super();
            inflater = LayoutInflater.from(context);
            this.objects = objects;
            this.activity= activity;
        }

        @Override
        public int getCount() {
            return objects.size();
        }

        @Override
        public RequestRentalDetails getItem(int position) {
            return objects.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.request_list_item, null);

            holder.carNameTextView = (TextView) convertView.findViewById(R.id.carName);
            holder.totalPriceTextView = (TextView) convertView.findViewById(R.id.totalPrice);
            holder.capacityTextView = (TextView) convertView.findViewById(R.id.capacity);
            //holder.reserveBtn = (Button) convertView.findViewById(R.id.reserveBtn);

            holder.carNameTextView.setText(objects.get(position).getCarName());
            holder.totalPriceTextView.setText(objects.get(position).getTotalPrice());
            holder.capacityTextView.setText(objects.get(position).getCapacity());



            return convertView;
        }


    }
}
