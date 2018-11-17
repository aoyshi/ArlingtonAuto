package com.arunika.arlingtonauto;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.arunika.arlingtonauto.controller.BaseMenuActivity;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

/** I've included comment explanations for all components of this code
 *
 *  PLEASE NOTE:
 *  This is a TEMPORARY DUMMY class for populating the ListView with
 *  dummy data for the UIP screenshots.
 *  NOT MVC yet.
 *  In MVC< this activity file will become the controller, and
 *  the CustomObject class here will become the model
 *  And the controller activity will need to interact with a DAO
 *  to fetch infro from db into the model, and back to the view
 *  via the CustomAdapter class for this particular ListView
 */

public class AllRentalReservationsActivity extends BaseMenuActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView=(ListView)findViewById(R.id.all_rr_list);

        /* this is just static string data mimicing a real db object */
        //make dummy arraylist of reservation objects
        ArrayList<CustomObject> objects = new ArrayList<CustomObject>();

        objects.add(new CustomObject("Ultra Sports", "Renter: helen_Troy",
                "10-26-2018 09:00 TO 10-29-2018 11:00"));
        objects.add(new CustomObject("Intermediate", "Renter: bond007",
                "10-18-2018 15:00 TO 10-25-2018 12:00"));
        objects.add(new CustomObject("MiniVan", "Renter: joshua1994",
                "10-18-2018 09:00 TO 10-18-2018 14:00"));

        //pass arraylist of objects to adapter class we made below
        CustomAdapter customAdapter = new AllRentalReservationsActivity.CustomAdapter(this, objects, this);

        //set adapter to the listview so it can populate the list with the rows
        listView.setAdapter(customAdapter);

    }

    /**dummy function for generating a custom toast for screenshot purposes
     * click any button in the screen to generate toast*/
    public void searchRR (View view) {
        /* custom toast, use if you dont have Toasty dependency downloaded*/
        /*View view2;
        TextView text;
        Toast toast;
        toast = Toast.makeText(this, "Successfully Deleted!", Toast.LENGTH_SHORT);
        view2 = toast.getView();
        text = (TextView) view2.findViewById(android.R.id.message);
        text.setTextColor(Color.WHITE);
        text.setShadowLayer(0,0,0,0);
        view2.setBackgroundResource(R.color.colorBlack);
        toast.show();*/

        //these are better-looking toasts from a maven dependency
        /* see project and app build.gradle files to see how the
         * dependency was added
         * */
        Toasty.error(this, "This is an error toast example.", Toast.LENGTH_SHORT, true).show();
        Toasty.success(this, "This is a Success! example", Toast.LENGTH_SHORT, true).show();
    }

    /* these will be replaced by our model java classes later on */
    class CustomObject {

        private String carName;
        private String renterUsername;
        private String duration;

        public CustomObject(String carName, String renterUsername, String duration)
            {
                this.carName = carName;
                this.renterUsername = renterUsername;
                this.duration = duration;
            }

        public String getCarName() {
            return carName;
        }

        public void setCarName(String carName) {
            this.carName = carName;
        }

        public String getRenterUsername() {
            return renterUsername;
        }

        public void setRenterUsername(String renterUsername) {
            this.renterUsername = renterUsername;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        }

    //we need an adapater class for each ListView we create
    /** the adapter takes items from the object
     * and uses that to fill in the all_rr_list_item.xml layout
     * it creates a new layout (row) for every object passed in to it
     * */
    public class CustomAdapter extends BaseAdapter {

        private LayoutInflater inflater;
        private ArrayList<CustomObject> objects;
        private Activity activity;

        private class ViewHolder {
            TextView carNameTextView;
            TextView durationTextView;
            TextView renterUsernameTextView;
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
            convertView = inflater.inflate(R.layout.all_rr_list_item, null);

            holder.carNameTextView = (TextView) convertView.findViewById(R.id.carName);
            holder.durationTextView = (TextView) convertView.findViewById(R.id.duration);
            holder.renterUsernameTextView = (TextView) convertView.findViewById(R.id.renterUsername);

            holder.carNameTextView.setText(objects.get(position).getCarName());
            holder.renterUsernameTextView.setText(objects.get(position).getRenterUsername());
            holder.durationTextView.setText(objects.get(position).getDuration());

            return convertView;
        }


    }
}
