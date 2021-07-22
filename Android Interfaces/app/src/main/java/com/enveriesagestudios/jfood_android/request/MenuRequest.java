package com.enveriesagestudios.jfood_android.request;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Menu Request
 * Description : Used to handling Volley request to listing the food list
 *
 * @author Ilham Mulya Rafid
 * @version 28.05.2020
 *
 * @param for every mutator and every variables that called in Constructor
 *
 * @return for every class with it's parameter
 *
 */

public class MenuRequest extends StringRequest {
    private static final String Menu_URL = "http://192.168.43.130:8080/food";
    private Map<String, String> params;

    public MenuRequest(Response.Listener<String> listener) {
        super(Request.Method.GET, Menu_URL, listener, null);
        params = new HashMap<>();
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return params;
    }

}
