package com.enveriesagestudios.jfood_android.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.enveriesagestudios.jfood_android.R;
import com.enveriesagestudios.jfood_android.request.RegisterRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Register Activity
 * Description : Used to handling Register Activity
 *
 * @author Ilham Mulya Rafid
 * @version 28.05.2020
 *
 * @param for every mutator and every variables that called in Constructor
 *
 * @return for every class with it's parameter
 *
 */

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Definisi button
        final EditText nameInput = findViewById(R.id.etName);
        final EditText emailInput = findViewById(R.id.etEmail);
        final EditText passwordInput = findViewById(R.id.etPassword);
        final Button registerButton = findViewById(R.id.btnRegister);


        //Register Button
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Pattern Checker
                if (emailInput.getText().toString().isEmpty() && nameInput.getText().toString().isEmpty() && passwordInput.getText().toString().isEmpty()) {
                    emailInput.setError("Required");
                    passwordInput.setError("Required");
                    nameInput.setError("Required");
                } else {
                    String name = nameInput.getText().toString();
                    if (checkValidEmail(emailInput.getText().toString().trim())){
                        String email = emailInput.getText().toString();
                        if (checkValidPassword(passwordInput.getText().toString())){
                            String  password = passwordInput.getText().toString();
                            Response.Listener<String> responseListener = new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try{
                                        JSONObject jsonResponse = new JSONObject(response);
                                        Toast.makeText(RegisterActivity.this, "Register Successful", Toast.LENGTH_LONG).show();
                                        Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                                        startActivity(loginIntent);
                                        finish();
                                    } catch (JSONException e){
                                        Toast.makeText(RegisterActivity.this, "Register Failed", Toast.LENGTH_LONG).show();
                                    }
                                }
                            };

                            Response.ErrorListener errorListener = new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Log.d("Error", "Error Occured", error);
                                }
                            };

                            RegisterRequest registerRequest = new RegisterRequest(name, email, password, responseListener, errorListener);
                            RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                            queue.add(registerRequest);
                        }else{
                            passwordInput.setError("Password Invalid");
                        }
                    }else{
                        emailInput.setError("Email Invalid");
                    }

                }
            }
        });
    }

    public boolean checkValidPassword(final String password_chek) {
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,}$";
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password_chek);
        return matcher.matches();
    }

    public boolean checkValidEmail(final String email_check) {
        final String PASSWORD_PATTERN = "^[\\w%&_*~]+(?:\\.[\\w&_*~]+)*@(?!-)(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(email_check);
        return matcher.matches();
    }
}
