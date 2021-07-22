package com.enveriesagestudios.jfood_android.request;

import com.android.volley.AuthFailureError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import java.util.*;


/**
 * Register Request
 * Description : Used to handling Volley request o register
 *
 * @author Ilham Mulya Rafid
 * @version 28.05.2020
 *
 * @param for every mutator and every variables that called in Constructor
 *
 * @return for every class with it's parameter
 *
 */

public class RegisterRequest extends StringRequest {
    private static final String Promo_URL = "http://192.168.43.130:8080/customer/register";
    private Map<String, String> params;

    public RegisterRequest(String name, String email, String password, Response.Listener<String> listener, Response.ErrorListener errorListener){
        super(Method.POST, Promo_URL, listener, errorListener);
        params = new HashMap<>();
        params.put("name", name);
        params.put("email", email);
        params.put("password", password);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return params;
    }
}