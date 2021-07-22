package com.enveriesagestudios.jfood_android.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.enveriesagestudios.jfood_android.NavigationDrawer;
import com.enveriesagestudios.jfood_android.R;
import com.enveriesagestudios.jfood_android.RecyclerViewAdapter;
import com.enveriesagestudios.jfood_android.model.Food;
import com.enveriesagestudios.jfood_android.request.BuatPesananRequest;
import com.enveriesagestudios.jfood_android.request.CheckPromoRequest;
import com.enveriesagestudios.jfood_android.request.PesananFetchRequest;
import com.enveriesagestudios.jfood_android.ui.home.HomeFragment;
import com.enveriesagestudios.jfood_android.utility.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * Add to Chart Activity
 * Description : Used to handling add to chart activity
 *
 * @author Ilham Mulya Rafid
 * @version 28.05.2020
 *
 * @param for every mutator and every variables that called in Constructor
 *
 * @return for every class with it's parameter
 *
 */

public class AddToChartActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Button btOrder;
    TextView tvTitle;
    private int totalFoodPrice;
    private String promoCode;
    SessionManager session;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_chart);

        session = new SessionManager(getApplicationContext());

        //Button Definition
        recyclerView = findViewById(R.id.recyclerView);
        btOrder = findViewById(R.id.btnOrder);
        tvTitle = findViewById(R.id.titleCart);
        //Cash or Cashless Radio Button with Grouping or Payment Type
        final RadioButton rbViaCash = findViewById(R.id.cash);
        final RadioButton rbViaCashless = findViewById(R.id.cashless);
        final RadioGroup radioGroup = findViewById(R.id.radioGroup);
        // Promo Code
        final TextView tvTextCode = findViewById(R.id.textCode);
        final EditText etPromoCode = findViewById(R.id.promo_code);
        tvTextCode.setVisibility(View.GONE);
        etPromoCode.setVisibility(View.GONE);
        // Total Price
        final TextView tvStaticTotalPrice = findViewById(R.id.staticTotalPrice);
        tvStaticTotalPrice.setVisibility(View.GONE);
        final TextView tvTotalPrice = findViewById(R.id.total_price);
        tvTotalPrice.setVisibility(View.GONE);
        // Order button
        final Button btnCount = findViewById(R.id.count_confirm);
        btnCount.setVisibility(View.GONE);
        final Button btOrder = findViewById(R.id.btnOrder);
        btOrder.setVisibility(View.GONE);
        // Delivery Fee Button
        final TextView tvDeliveryFeeStat = findViewById(R.id.deliveryFee_static);
        tvDeliveryFeeStat.setVisibility(View.GONE);
        final TextView tvDeliveryFee = findViewById(R.id.tvDeliveryFee);
        tvDeliveryFee.setVisibility(View.GONE);


        //Delivery Fee
        final int deliveryFee = 10000;
        int totalFoodPrice = 0;

        //Getting the Value from Before
        Intent currentInvoice = getIntent();

        ArrayList<Food> Food_Array_List = new ArrayList<>();
        Food_Array_List = currentInvoice.getParcelableArrayListExtra("Selected_FoodArray");
        final ArrayList<Integer> foodIdList = new ArrayList<>(currentInvoice.getIntegerArrayListExtra("Selected_FoodIdList"));

        //Initializer Group By ID
        ArrayList<Food> foodSelectedListName1 = new ArrayList<>();
        ArrayList<Food> foodSelectedListName2 = new ArrayList<>();
        ArrayList<Food> foodSelectedListName3 = new ArrayList<>();

        Set<Integer> food_IDSET = new HashSet<>();

        //Initialize Set that Clicked
        Integer[] foodIdSet = {1, 2, 3};
        Integer[] foodPriceSet = {0, 0, 0};
        String[] foodNameSet = {"", "", ""};
        String[] foodCategorySet = {"", "", ""};
        //Click Counter
        Integer[] Click_Counter = {0, 0, 0};
        //Total Price per Food
        Integer[] totalPricePerFood = {0, 0, 0};

        //Group Selected Food by ID
        for (Food c : Food_Array_List) {
            food_IDSET.add(c.getId());
            if (c.getId() == 1) {
                foodSelectedListName1.add(c);
                foodNameSet[0] = c.getName();
                foodPriceSet[0] = c.getPrice();
                foodCategorySet[0] = c.getCategory();
                Click_Counter[0] = Click_Counter[0] + 1;
                totalPricePerFood[0] = totalPricePerFood[0] + c.getPrice();
            } else if (c.getId() == 2) {
                foodSelectedListName2.add(c);
                foodNameSet[1] = c.getName();
                foodPriceSet[1] = c.getPrice();
                foodCategorySet[1] = c.getCategory();
                Click_Counter[1] = Click_Counter[1] + 1;
                totalPricePerFood[1] =totalPricePerFood[1] + c.getPrice();
            } else if (c.getId() == 3) {
                foodSelectedListName3.add(c);
                foodNameSet[2] = c.getName();
                foodPriceSet[2] = c.getPrice();
                foodCategorySet[2] = c.getCategory();
                Click_Counter[2] = Click_Counter[2] + 1;
                totalPricePerFood[2] = totalPricePerFood[2] + c.getPrice();
            }
            totalFoodPrice = totalFoodPrice + c.getPrice();
        }

        //Going to Recycler View
        RecyclerViewAdapter chartAdapter =  new RecyclerViewAdapter(this, foodIdSet, foodNameSet, foodPriceSet, foodCategorySet, Click_Counter, totalPricePerFood);
        recyclerView.setAdapter(chartAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //On Click Listener Case for Radio Group choosing Cash or Cashless and Display Promo Code for Cashless
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.cashless) {
                    tvTextCode.setVisibility(View.VISIBLE);
                    etPromoCode.setVisibility(View.VISIBLE);
                    tvDeliveryFeeStat.setVisibility(View.GONE);
                    tvDeliveryFee.setVisibility(View.GONE);
                    btnCount.setVisibility(View.VISIBLE);
                    btOrder.setVisibility(View.GONE);
                } else if (checkedId == R.id.cash){
                    tvTextCode.setVisibility(View.GONE);
                    etPromoCode.setVisibility(View.GONE);
                    tvDeliveryFeeStat.setVisibility(View.VISIBLE);
                    tvDeliveryFee.setVisibility(View.VISIBLE);
                    btnCount.setVisibility(View.VISIBLE);
                    btOrder.setVisibility(View.GONE);
                    tvDeliveryFee.setText("Rp. " + deliveryFee);
                }
            }
        });

        //Count on Click Listener
        final int finalTotalFoodPrice = totalFoodPrice;
        btnCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedRadioId = radioGroup.getCheckedRadioButtonId();
                if(selectedRadioId == R.id.cash){
                    tvTotalPrice.setText("Rp. " + ( finalTotalFoodPrice + deliveryFee ));
                    //Button VIsibility
                    btnCount.setVisibility(View.GONE);
                    btOrder.setVisibility(View.VISIBLE);
                    tvStaticTotalPrice.setVisibility(View.VISIBLE);
                    tvTotalPrice.setVisibility(View.VISIBLE);
                } else if (selectedRadioId == R.id.cashless) {
                    //Get Promo Code String
                    promoCode = etPromoCode.getText().toString();
                    //Listener Promo
                    final Response.Listener<String> promoResponse = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //Check if promo code is filled or not
                            if(promoCode.isEmpty()){
                                Toast.makeText(AddToChartActivity.this, "No Promo Code Applied", Toast.LENGTH_LONG).show();
                                tvTotalPrice.setText("Rp. " + finalTotalFoodPrice);
                                //Button VIsibility
                                btnCount.setVisibility(View.GONE);
                                btOrder.setVisibility(View.VISIBLE);
                                tvStaticTotalPrice.setVisibility(View.VISIBLE);
                                tvTotalPrice.setVisibility(View.VISIBLE);
                            } else {
                                try{
                                    JSONObject jsonResponse = new JSONObject(response);
                                    //Get Discount Price
                                    int promoDiscountPrice = jsonResponse.getInt("discount");
                                    boolean promoStatus = jsonResponse.getBoolean("active");
                                    //Case if Promo can be Applied
                                    if (promoStatus == false){
                                        Toast.makeText(AddToChartActivity.this, "Promo Code can no longer used", Toast.LENGTH_LONG).show();
                                    } else if (promoStatus == true){
                                        if(finalTotalFoodPrice < promoDiscountPrice) {
                                            Toast.makeText(AddToChartActivity.this, "Promo Code cannot be Applied", Toast.LENGTH_LONG).show();
                                        } else {
                                            //Toast Feedback
                                            Toast.makeText(AddToChartActivity.this, "Promo Code Applied", Toast.LENGTH_LONG).show();
                                            //Set Total Price
                                            tvTotalPrice.setText("Rp. " + ( finalTotalFoodPrice - promoDiscountPrice));
                                            //Button VIsibility
                                            btnCount.setVisibility(View.GONE);
                                            btOrder.setVisibility(View.VISIBLE);
                                            tvStaticTotalPrice.setVisibility(View.VISIBLE);
                                            tvTotalPrice.setVisibility(View.VISIBLE);
                                        }
                                    }
                                } catch (JSONException e){
                                    Toast.makeText(AddToChartActivity.this, "Promo Code not found", Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                    };
                    Response.ErrorListener errorPromo = new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("Error", "Error Occured", error);
                        }
                    };
                    //Volley Request for Promo Request
                    CheckPromoRequest promoRequest = new CheckPromoRequest(promoCode, promoResponse, errorPromo);
                    RequestQueue queue = Volley.newRequestQueue(AddToChartActivity.this);
                    queue.add(promoRequest);
                }
            }
        });

        btOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            Toast.makeText(AddToChartActivity.this, "Invoice Succesfully Created", Toast.LENGTH_LONG).show();
                            //Intent to Main Activity
                            Intent toTheMainActivity = new Intent(AddToChartActivity.this, NavigationDrawer.class);
                            startActivity(toTheMainActivity);
                            finish();
                        } catch (JSONException e){
                            Toast.makeText(AddToChartActivity.this, "Failed to Create Invoice", Toast.LENGTH_LONG).show();
                        }
                    }
                };
                Response.ErrorListener errorListener = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error", "Error Occured", error);
                    }
                };
                //Volley Request Checker
                int selectedRadioId = radioGroup.getCheckedRadioButtonId();
                if (selectedRadioId == R.id.cash){
                    BuatPesananRequest createPesananRequest = new BuatPesananRequest(foodIdList, session.getUserId(), deliveryFee, responseListener, errorListener);
                    RequestQueue queue = Volley.newRequestQueue(AddToChartActivity.this);
                    queue.add(createPesananRequest);
                } else if (selectedRadioId == R.id.cashless){
                    BuatPesananRequest createPesananRequest = new BuatPesananRequest(foodIdList, session.getUserId(), promoCode, responseListener, errorListener);
                    RequestQueue queue = Volley.newRequestQueue(AddToChartActivity.this);
                    queue.add(createPesananRequest);
                }
            }
        });
    }

}
