package com.enveriesagestudios.jfood_android.ui.gallery;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.enveriesagestudios.jfood_android.InvoiceRecyclerViewAdapter;
import com.enveriesagestudios.jfood_android.MainListAdapter;
import com.enveriesagestudios.jfood_android.R;
import com.enveriesagestudios.jfood_android.RecyclerViewAdapter;
import com.enveriesagestudios.jfood_android.model.Food;
import com.enveriesagestudios.jfood_android.model.Invoice;
import com.enveriesagestudios.jfood_android.model.Location;
import com.enveriesagestudios.jfood_android.model.Seller;
import com.enveriesagestudios.jfood_android.request.MenuRequest;
import com.enveriesagestudios.jfood_android.request.PesananFetchRequest;
import com.enveriesagestudios.jfood_android.utility.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Gallery Fragment
 * Description : This class is used to get Invoice History Recycler View
 *
 * @author Ilham Mulya Rafid
 * @version 28.05.2020
 *
 * @param for every mutator and every variables that called in Constructor
 *
 * @return for every class with it's parameter
 *
 */

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    SessionManager session;
    private RecyclerView recyclerInvoiceView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        //Create Session
        session = new SessionManager(root.getContext());

        //Button Definition
        recyclerInvoiceView = root.findViewById(R.id.RVinvoiceRecyclerView);

        //Food Array List
        final ArrayList<Integer> invoiceIdList = new ArrayList<>();
        final ArrayList<Integer> invoiceTotalPriceList= new ArrayList<>();
        final ArrayList<String> invoicePaymentList = new ArrayList<>();
        final ArrayList<String> invoiceStatusList = new ArrayList<>();
        final ArrayList<String> invoiceDateList = new ArrayList<>();


        // Get Invoice By Customer
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonResponseArray = new JSONArray(response);
                    for (int i = 0; i < jsonResponseArray.length(); i++) {
                        JSONObject jObj = jsonResponseArray.getJSONObject(i);
                        invoiceIdList.add(jObj.getInt("id"));
                        invoicePaymentList.add(jObj.getString("paymentType"));
                        invoiceTotalPriceList.add(jObj.getInt("totalPrice"));
                        invoiceStatusList.add(jObj.getString("invoiceStatus"));
                        invoiceDateList.add(jObj.getString("date"));
                    }
                    //Going to Recycler View
                    InvoiceRecyclerViewAdapter invoiceAdapter =  new InvoiceRecyclerViewAdapter(getActivity().getApplicationContext(), invoiceIdList, invoiceTotalPriceList,  invoicePaymentList,  invoiceStatusList, invoiceDateList);
                    recyclerInvoiceView.setAdapter(invoiceAdapter);
                    recyclerInvoiceView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
                }
                catch (JSONException e) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(root.getContext());
                    builder.setMessage("Load Data Failed.").create().show();
                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error", "Error Occured", error);
            }
        };

        PesananFetchRequest fetchInvoice = new PesananFetchRequest(session.getUserId(), responseListener, errorListener);
        RequestQueue queue = Volley.newRequestQueue(root.getContext());
        queue.add(fetchInvoice);
        return root;
    }
}
