package com.enveriesagestudios.jfood_android.request;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Login Request
 * Description : Used to handling Volley request to login request
 *
 * @author Ilham Mulya Rafid
 * @version 28.05.2020
 *
 * @param for every mutator and every variables that called in Constructor
 *
 * @return for every class with it's parameter
 *
 */

public class LoginRequest extends StringRequest {
    private static final String Login_URL = "http://192.168.43.130:8080/customer/login";
    private Map<String, String> params;

    public LoginRequest(String email, String password, Response.Listener<String> listener, Response.ErrorListener errorListener){
        super(Method.POST, Login_URL, listener, errorListener);
        params = new HashMap<>();
        params.put("email", email);
        params.put("password", password);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return params;
    }
}
