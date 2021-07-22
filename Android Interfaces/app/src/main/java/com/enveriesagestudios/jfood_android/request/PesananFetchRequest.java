package com.enveriesagestudios.jfood_android.request;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Pesanan fetch Request
 * Description : Used to handling Volley request to fetching the invoice
 *
 * @author Ilham Mulya Rafid
 * @version 28.05.2020
 *
 * @param for every mutator and every variables that called in Constructor
 *
 * @return for every class with it's parameter
 *
 */

public class PesananFetchRequest extends StringRequest {
    private static final String invoice_URL = "http://192.168.43.130:8080/invoice/customer/";
    private Map<String, String> params;
    private int customer_id;

    public PesananFetchRequest(int customer_id,
                               Response.Listener<String> listener,
                               Response.ErrorListener errorListener) {
        super(Request.Method.GET, invoice_URL + customer_id, listener, null);
        params = new HashMap<>();
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return params;
    }
}
