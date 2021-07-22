package com.enveriesagestudios.jfood_android.request;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.enveriesagestudios.jfood_android.model.Food;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Buat Pesanan Request
 * Description : Used to handling Volley request to create Invoice
 *
 * @author Ilham Mulya Rafid
 * @version 28.05.2020
 *
 * @param for every mutator and every variables that called in Constructor
 *
 * @return for every class with it's parameter
 *
 */

public class BuatPesananRequest extends StringRequest {
    private static final String foodById_URL = "http://192.168.43.130:8080/food/";
    private static final String InvoiceCash_URL = "http://192.168.43.130:8080/invoice/addCashInvoice";
    private static final String InvoiceCashless_URL = "http://192.168.43.130:8080/invoice/addCashlessInvoice";
    private Map<String, String> params;

    //For get
    public BuatPesananRequest(int food_id,
                              Response.Listener<String> listener,
                              Response.ErrorListener errorListener) {
        super(Request.Method.GET, foodById_URL + food_id, listener, null);
        params = new HashMap<>();
    }

    //For Cash Invoice
    public BuatPesananRequest(ArrayList<Integer> foodarraylist,
                              int customerId,
                              int deliveryFee,
                              Response.Listener<String> listener,
                              Response.ErrorListener errorListener) {
        super(Method.POST, InvoiceCash_URL, listener, null);
        String[] str = new String[foodarraylist.size()];
        Object[] objArr = foodarraylist.toArray();
        int i = 0;
        for(Object obj : objArr){
            str[i++] = Integer.toString((Integer)obj);
        }
        String newString = Arrays.toString(str);
        newString = newString.substring(1, newString.length()-1);
        params = new HashMap<>();
        params.put("foodIdList", newString);
        params.put("customerId", String.valueOf(customerId));
        params.put("deliveryFee", String.valueOf(deliveryFee));
    }

    //For Cashless Invoice
    public BuatPesananRequest(ArrayList<Integer>  foodarraylist,
                              int customerId,
                              String promoCode,
                              Response.Listener<String> listener,
                              Response.ErrorListener errorListener) {
        super(Method.POST, InvoiceCashless_URL, listener, null);
        String[] str = new String[foodarraylist.size()];
        Object[] objArr = foodarraylist.toArray();
        int i = 0;
        for(Object obj : objArr){
            str[i++] = Integer.toString((Integer)obj);
        }
        String newString = Arrays.toString(str);
        newString = newString.substring(1, newString.length()-1);
        params = new HashMap<>();
        params.put("foodIdList", newString);
        params.put("customerId", String.valueOf(customerId));
        params.put("promoCode", promoCode);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
