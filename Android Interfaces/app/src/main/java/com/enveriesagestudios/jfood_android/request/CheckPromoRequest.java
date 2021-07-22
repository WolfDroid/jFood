package com.enveriesagestudios.jfood_android.request;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Check Promo Request
 * Description : Used to handling Volley request to Promo Request
 *
 * @author Ilham Mulya Rafid
 * @version 28.05.2020
 *
 * @param for every mutator and every variables that called in Constructor
 *
 * @return for every class with it's parameter
 *
 */

public class CheckPromoRequest extends StringRequest {
    private static final String PromoByCode_URL = "http://192.168.43.130:8080/promo/";
    private Map<String, String> params;
    private String code;

    public CheckPromoRequest(String code, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(Method.GET, PromoByCode_URL+ code, listener, errorListener);
        params = new HashMap<>();
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return params;
    }

}
