package com.arunika.arlingtonauto.controller;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.arunika.arlingtonauto.R;
import es.dmoral.toasty.Toasty;

/** All 3 HomeScreen Activities must extend SystemUserActivity.
 *  It contains 2 functions common to ALL 3 types of System Users.
 *  EditOwnProfile and Logout.
 *  This inheritance keeps code centralized and cleaner.
 */
public class CustomerHomeActivity extends SystemUserActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_home);
    }
    public void requestRental(View view) {
        Toasty.success(this,"I work - Request Rental!", Toast.LENGTH_SHORT, true).show();
    }
    public void viewMyRR(View view) {
        Toasty.success(this,"I work - View RR!", Toast.LENGTH_SHORT, true).show();
    }
}