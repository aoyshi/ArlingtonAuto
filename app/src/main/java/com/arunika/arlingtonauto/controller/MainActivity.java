package com.arunika.arlingtonauto.controller;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.arunika.arlingtonauto.R;
import com.arunika.arlingtonauto.model.User;
import com.arunika.arlingtonauto.DAO.UserDAO;
import com.google.gson.Gson;
import es.dmoral.toasty.Toasty;
/**
 * LOGIN CONTROLLER
 */
public class MainActivity extends AppCompatActivity {
    private EditText usernameField;
    private EditText passwordField;
    private UserDAO UserDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews(); //initialize all text field objects
        this.UserDAO = UserDAO.getInstance(this); //call singleton
    }
    private void initViews() {
        this.usernameField = (EditText) findViewById(R.id.username);
        this.passwordField = (EditText) findViewById(R.id.password);
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
        startActivity(new Intent(MainActivity.this,
                RegistrationActivity.class));
    }

}


