package com.enveriesagestudios.jfood_android.request;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.enveriesagestudios.jfood_android.model.InvoiceStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * Pesanan batal Request
 * Description : Used to handling Volley request to Cancel the Order
 *
 * @author Ilham Mulya Rafid
 * @version 28.05.2020
 *
 * @param for every mutator and every variables that called in Constructor
 *
 * @return for every class with it's parameter
 *
 */

public class PesananBatalRequest extends StringRequest {
    private static final String BatalRequest_URL = "http://192.168.43.130:8080/invoice/cancelInvoice/";
    private Map<String, String> params;
    private int id_invoice;

    public PesananBatalRequest(int id_invoice, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(Method.PUT,BatalRequest_URL + id_invoice, listener, errorListener);
        params = new HashMap<>();
        params.put("id", String.valueOf(id_invoice));
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return params;
    }
}
