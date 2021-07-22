package com.enveriesagestudios.jfood_android.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.*;
import android.view.View;
import android.widget.*;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.enveriesagestudios.jfood_android.NavigationDrawer;
import com.enveriesagestudios.jfood_android.R;
import com.enveriesagestudios.jfood_android.model.Customer;
import com.enveriesagestudios.jfood_android.request.LoginRequest;
import com.enveriesagestudios.jfood_android.utility.SessionManager;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Login Actiyity
 * Description : Used to handling the login Activity
 *
 * @author Ilham Mulya Rafid
 * @version 28.05.2020
 *
 * @param for every mutator and every variables that called in Constructor
 *
 * @return for every class with it's parameter
 *
 */

public class LoginActivity extends AppCompatActivity {
    // Define Variable
    private String currentCustomerName;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Session Manager
        final SessionManager session = new SessionManager(this);

        //Definisi Button
        final EditText etEmail = findViewById(R.id.etEmail);
        final EditText etPassword = findViewById(R.id.etPassword);
        final Button btnLogin = findViewById(R.id.btnLogin);
        final TextView tvRegister = findViewById(R.id.tvRegister);

        if(session.isLoggedIn()){
            Intent mainIntent = new Intent(LoginActivity.this, NavigationDrawer.class);
            startActivity(mainIntent);
            finish();
        }

        //Login Button
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString();
                String password  = etPassword.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                            currentCustomerName = jsonResponse.getString("name");
                            //Intent to Main
                            Intent mainIntent = new Intent(LoginActivity.this, NavigationDrawer.class);
                            //Parsing Variable
                            Bundle userData = new Bundle();
                            userData.putInt("Customer_ID", jsonResponse.getInt("id"));
                            userData.putString("Customer_Name", jsonResponse.getString("name"));
                            userData.putString("Customer_Email", jsonResponse.getString("email"));
                            mainIntent.putExtras(userData);
                            //Start Main Activity
                            startActivity(mainIntent);
                            session.createLoginSession(jsonResponse.getInt("id"), jsonResponse.getString("name"),  jsonResponse.getString("email"));
                            finish();
                        } catch (JSONException e){
                            Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
                        }
                    }
                };

                Response.ErrorListener errorListener = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error", "Error Occured", error);
                    }
                };

                LoginRequest loginRequest = new LoginRequest(email, password, responseListener, errorListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });

        //Register Button
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registrationIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registrationIntent);
            }
        });

        //Checking if the user is logged in or not
    }
}
