package com.enveriesagestudios.jfood_android.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.enveriesagestudios.jfood_android.R;
import com.enveriesagestudios.jfood_android.model.InvoiceStatus;
import com.enveriesagestudios.jfood_android.request.BuatPesananRequest;
import com.enveriesagestudios.jfood_android.request.PesananBatalRequest;
import com.enveriesagestudios.jfood_android.request.PesananFetchRequest;
import com.enveriesagestudios.jfood_android.request.PesananSelesaiRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Selesai Pesanan Activity
 * Description : Not Being used right now
 *
 * @author Ilham Mulya Rafid
 * @version 28.05.2020
 *
 * @param for every mutator and every variables that called in Constructor
 *
 * @return for every class with it's parameter
 *
 */


public class SelesaiPesananActivity extends AppCompatActivity {
    private int currentInvoiceID;
    private String currentCustomerName;
    private String invoiceCompleted = "FINISHED";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selesai_pesanan);

        //Invoice Text Definition
        // Invoice ID
        final TextView tvInvoiceID = findViewById(R.id.invoiceId);
        // Customer Name
        final TextView tvCustomerName = findViewById(R.id.customerName);
        // Food Name
        final TextView tvFoodName = findViewById(R.id.foodName);
        // Odering Date
        final TextView tvOrderingDate = findViewById(R.id.orderDate);
        // Total Price
        final TextView tvTotalPrice = findViewById(R.id.totalPrice);
        // Invoice Status
        final TextView tvInvoiceStatus = findViewById(R.id.invoiceStatus);

        //Food Array List
        final ArrayList<String> foodList = new ArrayList<String>();
        final ArrayList<String> customerList = new ArrayList<String>();

        //Getting the customer and invoice data
        final Intent currentUserandInvoiceIntent = getIntent();
        currentInvoiceID = currentUserandInvoiceIntent.getIntExtra("Current_InvoiceID", 0);
        final Bundle currentUser = currentUserandInvoiceIntent.getExtras();
        currentCustomerName = currentUser.getString("Customer_Name");

        //Selesai Pesanan and Batal Pesanan Button
        final Button btCompleteInvoice = findViewById(R.id.completeInvoice);
        final Button btCancelInvoice = findViewById(R.id.cancelInvoice);


        //Fetching the Invoice ID
        final Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    //Fetching the data
                    JSONObject jsonResponse = new JSONObject(response);
                    //Food Name
                    if(jsonResponse!=null) {
                        //Printing
                        tvInvoiceID.setText("" + jsonResponse.getInt("id"));
                        tvTotalPrice.setText("" + jsonResponse.getInt("totalPrice"));
                        tvOrderingDate.setText(jsonResponse.getString("date"));
                        tvInvoiceStatus.setText(jsonResponse.getString("invoiceStatus"));
                        //Printing Customer
                        JSONObject jsonCustomerResponse = new JSONObject(jsonResponse.getString("customer"));
                        tvCustomerName.setText(""+ jsonCustomerResponse.getString("name"));
                        //Printing Foods
                        JSONArray jsonArrayResponseFoods = jsonResponse.getJSONArray("foods");
                        for (int i = 0; i < jsonArrayResponseFoods.length(); i++) {
                            JSONObject jObj = jsonArrayResponseFoods.getJSONObject(i);
                            foodList.add(jObj.getString("name"));
                        }
                        tvFoodName.setText("" + foodList.toString().replace("[", "").replace("]", ""));
                    }

                } catch (JSONException e) {
                    Toast.makeText(SelesaiPesananActivity.this, "There are no on ongoing invoice", Toast.LENGTH_LONG).show();
                }
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error", "Error Occured", error);
            }
        };
        //Fetching the Data
        PesananFetchRequest pesananFetchRequest = new PesananFetchRequest(currentInvoiceID, responseListener, errorListener);
        RequestQueue queue = Volley.newRequestQueue(SelesaiPesananActivity.this);
        queue.add(pesananFetchRequest);

        //Selesai Button Listener
        btCompleteInvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            if (jsonResponse != null) {
                                Toast.makeText(SelesaiPesananActivity.this, "Order Completed", Toast.LENGTH_LONG).show();
                                Intent mainIntent = new Intent(SelesaiPesananActivity.this, MainActivity.class);
                                startActivity(mainIntent);
                                finish();
                            }
                        } catch (JSONException e) {
                            Toast.makeText(SelesaiPesananActivity.this, "JSON FAILED", Toast.LENGTH_LONG).show();
                        }
                    }
                };
                Response.ErrorListener errorListener = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error", "Error Occured", error);
                    }
                };
                //PesananSelesaiRequest pesananSelesaiRequest = new PesananSelesaiRequest(currentInvoiceID, InvoiceStatus.Finished,responseListener, errorListener);
                //RequestQueue queue = Volley.newRequestQueue(SelesaiPesananActivity.this);
                //queue.add(pesananSelesaiRequest);
            }
        });

        //Batal Button Listener
        btCancelInvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            if (jsonResponse != null) {
                                Toast.makeText(SelesaiPesananActivity.this, "Order cancelled", Toast.LENGTH_LONG).show();
                                Intent mainIntent = new Intent(SelesaiPesananActivity.this, MainActivity.class);
                                startActivity(mainIntent);
                                finish();
                            }
                        } catch (JSONException e) {
                            Toast.makeText(SelesaiPesananActivity.this, "JSON FAILED", Toast.LENGTH_LONG).show();
                        }
                    }
                };
                Response.ErrorListener errorListener = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error", "Error Occured", error);
                    }
                };
                PesananBatalRequest pesananBatalRequest = new PesananBatalRequest(currentInvoiceID, responseListener,errorListener);
                RequestQueue queue = Volley.newRequestQueue(SelesaiPesananActivity.this);
                queue.add(pesananBatalRequest);
            }
        });

    }
}
