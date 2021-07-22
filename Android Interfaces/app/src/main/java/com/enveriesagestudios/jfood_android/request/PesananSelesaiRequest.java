package com.enveriesagestudios.jfood_android.request;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.enveriesagestudios.jfood_android.model.InvoiceStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * Pesanana Selesai Request
 * Description : Used to handling Volley request o finish invoice request
 *
 * @author Ilham Mulya Rafid
 * @version 28.05.2020
 *
 * @param for every mutator and every variables that called in Constructor
 *
 * @return for every class with it's parameter
 *
 */

public class PesananSelesaiRequest extends StringRequest {
    private static final String Finish_URL = "http://192.168.43.130:8080/invoice/finishInvoice/";
    private Map <String, String> params;
    private int id_invoice;

    public PesananSelesaiRequest(int id_invoice, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(Method.PUT, Finish_URL+id_invoice, listener, errorListener);
        params = new HashMap<>();
        params.put("id", String.valueOf(id_invoice));
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return params;
    }

}

