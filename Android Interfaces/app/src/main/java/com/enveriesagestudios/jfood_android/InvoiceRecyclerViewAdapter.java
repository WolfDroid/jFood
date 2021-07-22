package com.enveriesagestudios.jfood_android;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.enveriesagestudios.jfood_android.request.PesananBatalRequest;
import com.enveriesagestudios.jfood_android.request.PesananSelesaiRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Invoice Recycler View Adapter
 * Description : This Class is used to display the Recycler View on the Invoice
 *
 * @author Ilham Mulya Rafid
 * @version 28.05.2020
 *
 * @param for every mutator and every variables that called in Constructor
 *
 * @return for every class with it's parameter
 *
 */

public class InvoiceRecyclerViewAdapter extends RecyclerView.Adapter<InvoiceRecyclerViewAdapter.TestViewHolder> {

    Context context_;
    ArrayList<Integer> Invoice_ID, Invoice_TotalPrices;
    ArrayList<String> Payment_Types, Invoice_Status, Invoice_Date;


    public InvoiceRecyclerViewAdapter(Context context, ArrayList<Integer> invoiceIdList, ArrayList<Integer> invoiceTotalPriceList, ArrayList<String> invoicePaymentList, ArrayList<String> invoiceStatusList, ArrayList<String> invoiceDateList) {
        context_ = context;
        Invoice_ID = invoiceIdList;
        Invoice_TotalPrices = invoiceTotalPriceList;
        Payment_Types = invoicePaymentList;
        Invoice_Status = invoiceStatusList;
        Invoice_Date = invoiceDateList;
    }

    @NonNull
    @Override
    public TestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context_);
        View view = inflater.inflate(R.layout.invoice_recycler_row, parent,false);
        return new TestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InvoiceRecyclerViewAdapter.TestViewHolder holder, final int position) {
        holder.myText1_InvoiceID.setText("Invoice ID : " + Invoice_ID.get(position).toString());
        holder.myText2_InvoiceTotalPrice.setText("Rp." + Invoice_TotalPrices.get(position).toString());
        holder.myText3_PaymentType.setText(Payment_Types.get(position));
        holder.myText4_InvoiceStatus.setText(Invoice_Status.get(position));
        holder.myText5_InvoiceDate.setText(Invoice_Date.get(position));
        holder.completeInvoiceButton.setVisibility(View.GONE);
        holder.cancelInvoiceButton.setVisibility(View.GONE);
        if(Invoice_Status.get(position).equals("Ongoing")) {
            holder.completeInvoiceButton.setVisibility(View.VISIBLE);
            holder.cancelInvoiceButton.setVisibility(View.VISIBLE);
            holder.completeInvoiceButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                if (jsonResponse != null) {
                                    Toast.makeText(context_, "Order Completed", Toast.LENGTH_LONG).show();
                                }
                            } catch (JSONException e) {
                                Toast.makeText(context_, "JSON FAILED", Toast.LENGTH_LONG).show();
                            }
                        }
                    };
                    Response.ErrorListener errorListener = new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("Error", "Error Occured", error);
                        }
                    };
                    PesananSelesaiRequest pesananSelesaiRequest = new PesananSelesaiRequest(position + 1, responseListener, errorListener);
                    RequestQueue queue = Volley.newRequestQueue(context_);
                    queue.add(pesananSelesaiRequest);
                }
            });
            holder.cancelInvoiceButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                if (jsonResponse != null) {
                                    Toast.makeText(context_, "Order cancelled", Toast.LENGTH_LONG).show();
                                }
                            } catch (JSONException e) {
                                Toast.makeText(context_, "JSON FAILED", Toast.LENGTH_LONG).show();
                            }
                        }
                    };
                    Response.ErrorListener errorListener = new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("Error", "Error Occured", error);
                        }
                    };
                    PesananBatalRequest pesananBatalRequest = new PesananBatalRequest(position + 1, responseListener, errorListener);
                    RequestQueue queue = Volley.newRequestQueue(context_);
                    queue.add(pesananBatalRequest);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return Invoice_ID.size();
    }

    public class TestViewHolder extends RecyclerView.ViewHolder{
        TextView myText1_InvoiceID, myText2_InvoiceTotalPrice, myText3_PaymentType, myText4_InvoiceStatus, myText5_InvoiceDate;
        Button completeInvoiceButton, cancelInvoiceButton;
        public TestViewHolder(@NonNull View itemView) {
            super(itemView);
            myText1_InvoiceID = itemView.findViewById(R.id.tvInvoiceId);
            myText2_InvoiceTotalPrice = itemView.findViewById(R.id.tvInvoiceTotalPrice);
            myText3_PaymentType = itemView.findViewById(R.id.tvPaymentType);
            myText4_InvoiceStatus = itemView.findViewById(R.id.tvInvoiceStatus);
            myText5_InvoiceDate = itemView.findViewById(R.id.tvOrderDate);
            completeInvoiceButton = itemView.findViewById(R.id.completeButton);
            cancelInvoiceButton = itemView.findViewById(R.id.cancelButton);
        }
    }

    private void showSignoutDialog() {
    }
}
