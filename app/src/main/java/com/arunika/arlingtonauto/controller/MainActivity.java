package com.arunika.arlingtonauto.controller;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.arunika.arlingtonauto.R;
import com.arunika.arlingtonauto.model.Car;
import com.arunika.arlingtonauto.model.User;
import com.arunika.arlingtonauto.DAO.UserDAO;
import com.arunika.arlingtonauto.DAO.CarDAO;
import com.google.gson.Gson;
import es.dmoral.toasty.Toasty;
/**
 * LOGIN CONTROLLER
 */
public class MainActivity extends AppCompatActivity {
    private EditText usernameField;
    private EditText passwordField;
    private UserDAO UserDAO;
    private CarDAO CarDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews(); //initialize all text field objects
        this.UserDAO = UserDAO.getInstance(this); //call singleton
        this.CarDAO = CarDAO.getInstance(this); // call singleton
        initCars(); //initialize all cars in the database.
    }
    private void initViews() {
        this.usernameField = (EditText) findViewById(R.id.username);
        this.passwordField = (EditText) findViewById(R.id.password);
    }
    private void initCars() {
        Car car1 = new Car();
        car1.setId(1);
        car1.setName("Smart");
        car1.setCapacity(1);
        car1.setWeekdayRate(32.99);
        car1.setWeekendRate(37.99);
        car1.setWeeklyRate(230.93);
        car1.setDailyGps(3);
        car1.setDailyOnstar(5);
        car1.setDailySirius(7);

        Car car2 = new Car();
        car2.setId(2);
        car2.setName("Economy");
        car2.setCapacity(3);
        car2.setWeekdayRate(39.99);
        car2.setWeekendRate(44.99);
        car2.setWeeklyRate(279.93);
        car2.setDailyGps(3);
        car2.setDailyOnstar(5);
        car2.setDailySirius(7);

        Car car3 = new Car();
        car3.setId(3);
        car3.setName("Compact");
        car3.setCapacity(4);
        car3.setWeekdayRate(44.99);
        car3.setWeekendRate(49.99);
        car3.setWeeklyRate(314.93);
        car3.setDailyGps(3);
        car3.setDailyOnstar(5);
        car3.setDailySirius(7);

        Car car4 = new Car();
        car4.setId(4);
        car4.setName("Intermediate");
        car4.setCapacity(4);
        car4.setWeekdayRate(45.99);
        car4.setWeekendRate(50.99);
        car4.setWeeklyRate(321.93);
        car4.setDailyGps(3);
        car4.setDailyOnstar(5);
        car4.setDailySirius(7);

        Car car5 = new Car();
        car5.setId(5);
        car5.setName("Standard");
        car5.setCapacity(5);
        car5.setWeekdayRate(48.99);
        car5.setWeekendRate(53.99);
        car5.setWeeklyRate(342.93);
        car5.setDailyGps(3);
        car5.setDailyOnstar(5);
        car5.setDailySirius(7);

        Car car6 = new Car();
        car6.setId(6);
        car6.setName("Full Size");
        car6.setCapacity(6);
        car6.setWeekdayRate(52.99);
        car6.setWeekendRate(57.99);
        car6.setWeeklyRate(370.93);
        car6.setDailyGps(3);
        car6.setDailyOnstar(5);
        car6.setDailySirius(7);

        long insertId = 0;
        insertId = this.CarDAO.insertCar(car1);
        insertId = this.CarDAO.insertCar(car2);
        insertId = this.CarDAO.insertCar(car3);
        insertId = this.CarDAO.insertCar(car4);
        insertId = this.CarDAO.insertCar(car5);
        insertId = this.CarDAO.insertCar(car6);

        //See if insertion was successfull. toast should show ID=6. if it shows id=-1, then it means insertion wasn't successful for some reason (bug)
        Toast.makeText(this, "last car id: "+insertId, Toast.LENGTH_SHORT).show();
    }
    public void onLogin(View view) {
        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();
        User user = UserDAO.getUser(username);
        String errorMsg = "Incorrect credentials!";
        if( user != null) {
            if (user.verifyPassword(password)) {
                /**
                 * Save currentUser object in shared preferences(login session)
                 * currentUser object will now be accessible
                 * from every activity until logout
                 */
                SharedPreferences preferences = getSharedPreferences("PREFS",MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                /**
                 * Usually, sharedPref only allows storing String var
                 * GSON allows storing objects in shared preferences
                 * GSON stores it in JSON format
                 * currentUser object will now be accessible from every activity
                 */
                Gson gson = new Gson();
                String currentUser = gson.toJson(user);
                editor.putString("currentUser", currentUser);
                editor.apply();
                errorMsg = null;
                //redirect to approp. homepage
                String role = user.getRole();
                if (role.equalsIgnoreCase("Customer"))
                    startActivity(new Intent(this,CustomerHomeActivity.class));
                else if (role.equalsIgnoreCase("Manager"))
                    startActivity(new Intent(this,ManagerHomeActivity.class));
                else
                    startActivity(new Intent(this,AdminHomeActivity.class));
            }
        }
        if(errorMsg != null) //credentials were wrong
            Toasty.error(this, errorMsg, Toast.LENGTH_LONG, true).show();
    }

    public void onRegisterBtnClick(View view) {
        startActivity(new Intent(this,
                RegistrationActivity.class));
    }
}


