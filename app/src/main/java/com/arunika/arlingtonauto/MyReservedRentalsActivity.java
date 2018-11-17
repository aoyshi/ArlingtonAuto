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

public class MyReservedRentalsActivity extends BaseMenuActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView=(ListView)findViewById(R.id.request_list);
        ArrayList<CustomObject> objects = new ArrayList<CustomObject>();
        objects.add(new CustomObject("Economy", "$560.99","Fits 2 people",
                "10-30-2018 11:00 TO 11-02-2018 20:00","Extras: GPS | OnStar |","Confirmation #: 13"));
        objects.add(new CustomObject("Ultra", "$250.00","Fits 5 people",
                "10-26-2018 09:00 TO 10-29-2018 10:00","Extras: SiriusXM |","Confirmation #: 25"));
        objects.add(new CustomObject("Sports", "$100.12","Fits 3 people",
                "10-25-2018 12:00 TO 10-26-2018 08:00","Extras: None","Confirmation #: 7"));
        objects.add(new CustomObject("Smart", "$190.75","Fits 1 person",
                "09-23-2018 13:00 TO 09-23-2018 16:00", "Extras: GPS | OnStar | SiriusXM |", "Confirmation #: 19"));
        CustomAdapter customAdapter = new CustomAdapter(this, objects, this);
        listView.setAdapter(customAdapter);
    }

    public void searchRR (View view) {

    }

    class CustomObject {

        private String carName;
        private String totalPrice;
        private String capacity;
        private String duration;
        private String extras;
        private String confirmation;

        //private Button button;

        public CustomObject(String carName, String totalPrice, String capacity,
        String duration, String extras, String confirmation){//, Button button) {
            this.carName = carName;
            this.totalPrice = totalPrice;
            this.capacity=capacity;
            this.duration=duration;
            this.extras=extras;
            this.confirmation=confirmation;
            //this.button=button;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getExtras() {
            return extras;
        }

        public void setExtras(String extras) {
            this.extras = extras;
        }

        public String getConfirmation() {
            return confirmation;
        }

        public void setConfirmation(String confirmation) {
            this.confirmation = confirmation;
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
        private ArrayList<CustomObject> objects;
        private Activity activity;

        private class ViewHolder {
            TextView carNameTextView;
            TextView totalPriceTextView;
            TextView capacityTextView;
            TextView durationTextView;
            TextView extrasTextView;
            TextView confirmationTextView;
            Button reserveBtn;
        }


        public CustomAdapter(Context context, ArrayList<CustomObject> objects, Activity activity) {
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
        public CustomObject getItem(int position) {
            return objects.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.my_rr_list_item, null);

            holder.carNameTextView = (TextView) convertView.findViewById(R.id.carName);
            holder.totalPriceTextView = (TextView) convertView.findViewById(R.id.totalPrice);
            holder.capacityTextView = (TextView) convertView.findViewById(R.id.capacity);
            holder.durationTextView = (TextView) convertView.findViewById(R.id.duration);
            holder.extrasTextView = (TextView) convertView.findViewById(R.id.extras);
            holder.confirmationTextView = (TextView) convertView.findViewById(R.id.confirmationNumber);

            //holder.reserveBtn = (Button) convertView.findViewById(R.id.reserveBtn);

            holder.carNameTextView.setText(objects.get(position).getCarName());
            holder.totalPriceTextView.setText(objects.get(position).getTotalPrice());
            holder.capacityTextView.setText(objects.get(position).getCapacity());
            holder.durationTextView.setText(objects.get(position).getDuration());
            holder.extrasTextView.setText(objects.get(position).getExtras());
            holder.confirmationTextView.setText(objects.get(position).getConfirmation());

            return convertView;
        }


    }
}
