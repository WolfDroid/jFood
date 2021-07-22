package com.enveriesagestudios.jfood_android.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.enveriesagestudios.jfood_android.R;
import com.enveriesagestudios.jfood_android.model.Food;
import com.enveriesagestudios.jfood_android.model.Promo;
import com.enveriesagestudios.jfood_android.request.BuatPesananRequest;
import com.enveriesagestudios.jfood_android.request.CheckPromoRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Buat Pesanan Activity
 * Description : Not Being Used Right Now
 *
 * @author Ilham Mulya Rafid
 * @version 28.05.2020
 *
 * @param for every mutator and every variables that called in Constructor
 *
 * @return for every class with it's parameter
 *
 */

public class BuatPesananActivity extends AppCompatActivity{
    private LinearLayout foodLayoutBlock;
    private int currentuserId;
    private int id_food;
    private ArrayList<Integer> id_FoodList;
    private int deliveryFee;
    private String foodName;
    private String foodCategory;
    private double foodPrice;
    private String promoCode;
    private Promo avaliablePromo;
    private String selectedPayment;
    private LinearLayout newFoodBlock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_pesanan);

        //Getting Bundling from Before
        Intent currentUserIntent = getIntent();
        //final Bundle currentUserAndFoodData = currentUserIntent.getExtras();
        //currentuserId = currentUserAndFoodData.getInt("Customer_ID");
       //id_food = currentUserAndFoodData.getInt("Food_ID");
        //foodName = currentUserAndFoodData.getString("Food_Name");
        //foodCategory = currentUserAndFoodData.getString("Food_Category");
        //foodPrice = currentUserAndFoodData.getInt("Food_Price");

        //Food ID List
        final ArrayList<Integer> foodIdList = currentUserIntent.getIntegerArrayListExtra("Selected_FoodIdList");
        Log.d("Error", "" + foodIdList);

        final int foodindex1 = currentUserIntent.getIntExtra("CountFoodId_1",0);
        Log.d("Error", "" + foodindex1);

        LinearLayout foodBlockLayout = (LinearLayout) findViewById(R.id.food_block);
        newFoodBlock = new LinearLayout(this);
        foodBlockLayout.addView(newFoodBlock);

        // Food Name
        //final TextView tvFoodName = findViewById(R.id.food_name);
        //tvFoodName.setText(foodName);

        // Food Category
        //final TextView tvFoodCategory = findViewById(R.id.food_category);
        //tvFoodCategory.setText(foodCategory);

        //Food Price
        //final TextView tvFoodPrice = findViewById(R.id.food_price);
        //tvFoodPrice.setText(String.valueOf(foodPrice));

        //Cash or Cashless Radio Button with Grouping or Payment Type
        //final RadioButton rbViaCash = findViewById(R.id.cash);
        //final RadioButton rbViaCashless = findViewById(R.id.cashless);
        //final RadioGroup radioGroup = findViewById(R.id.radioGroup);

        //Delivery Fee
        final int deliveryFee = 10000;

        // Promo Code
        //final TextView tvTextCode = findViewById(R.id.textCode);
        //final EditText etPromoCode = findViewById(R.id.promo_code);
        //tvTextCode.setVisibility(View.INVISIBLE);
        //etPromoCode.setVisibility(View.INVISIBLE);

        // Total Price
        //final TextView tvTotalPrice = findViewById(R.id.total_price);
        //tvTotalPrice.setText(String.valueOf(0));

        // Count Button
        //final Button btnCount = findViewById(R.id.hitung);

        // Order button
        final TextView tvOrder = findViewById(R.id.pesanan);        //Also used for Title
        //final Button btnOrder = findViewById(R.id.pesan);
        //btnOrder.setVisibility(View.GONE);

        //On Click Listener Case for Radio Group choosing Cash or Cashless and Display Promo Code for Cashless
        /*
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.cashless) {
                    tvTextCode.setVisibility(View.VISIBLE);
                    etPromoCode.setVisibility(View.VISIBLE);
                    btnCount.setVisibility(View.VISIBLE);
                    btnOrder.setVisibility(View.GONE);
                } else {
                    tvTextCode.setVisibility(View.INVISIBLE);
                    etPromoCode.setVisibility(View.INVISIBLE);
                    btnCount.setVisibility(View.VISIBLE);
                    btnOrder.setVisibility(View.GONE);
                }
            }
        });

        //Onclick Listener Case for Count Button
        btnCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedRadioId = radioGroup.getCheckedRadioButtonId();
                if(selectedRadioId == R.id.cash){
                    tvTotalPrice.setText("Rp. " + ( foodPrice + deliveryFee ));
                    //Button VIsibility
                    btnCount.setVisibility(View.GONE);
                    btnOrder.setVisibility(View.VISIBLE);
                } else if (selectedRadioId == R.id.cashless) {
                    //Get Promo Code String
                    promoCode = etPromoCode.getText().toString();
                    //Listener Promo
                    final Response.Listener<String> promoResponse = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //Check if promo code is filled or not
                            if(promoCode.isEmpty()){
                                Toast.makeText(BuatPesananActivity.this, "No Promo Code Applied", Toast.LENGTH_LONG).show();
                                tvTotalPrice.setText("Rp. " + foodPrice);
                                //Button VIsibility
                                btnCount.setVisibility(View.GONE);
                                btnOrder.setVisibility(View.VISIBLE);
                            } else {
                                try{
                                    JSONObject jsonResponse = new JSONObject(response);
                                    //Get Discount Price
                                    int promoDiscountPrice = jsonResponse.getInt("discount");
                                    boolean promoStatus = jsonResponse.getBoolean("active");
                                    //Case if Promo can be Applied
                                    if (promoStatus == false){
                                        Toast.makeText(BuatPesananActivity.this, "Promo Code can no longer used", Toast.LENGTH_LONG).show();
                                    } else if (promoStatus == true){
                                        if(foodPrice < promoDiscountPrice) {
                                            Toast.makeText(BuatPesananActivity.this, "Promo Code cannot be Applied", Toast.LENGTH_LONG).show();
                                        } else {
                                            //Toast Feedback
                                            Toast.makeText(BuatPesananActivity.this, "Promo Code Applied", Toast.LENGTH_LONG).show();
                                            //Set Total Price
                                            tvTotalPrice.setText("Rp. " + ( foodPrice - promoDiscountPrice));
                                            //Button VIsibility
                                            btnCount.setVisibility(View.GONE);
                                            btnOrder.setVisibility(View.VISIBLE);
                                        }
                                    }
                                } catch (JSONException e){
                                    Toast.makeText(BuatPesananActivity.this, "Promo Code not found", Toast.LENGTH_LONG).show();
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
                    RequestQueue queue = Volley.newRequestQueue(BuatPesananActivity.this);
                    queue.add(promoRequest);
                }
            }
        });

        //Onclick Listener Case for Order Button
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            Toast.makeText(BuatPesananActivity.this, "Invoice Succesfully Created", Toast.LENGTH_LONG).show();
                            //Intent to Main Activity
                            Intent toTheMainActivity = new Intent(BuatPesananActivity.this, MainActivity.class);
                            startActivity(toTheMainActivity);
                            finish();
                        } catch (JSONException e){
                            Toast.makeText(BuatPesananActivity.this, "There are still On-going Order", Toast.LENGTH_LONG).show();
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
                    BuatPesananRequest createPesananRequest = new BuatPesananRequest(foodIdList, currentuserId, deliveryFee, responseListener, errorListener);
                    RequestQueue queue = Volley.newRequestQueue(BuatPesananActivity.this);
                    queue.add(createPesananRequest);
                } else if (selectedRadioId == R.id.cashless){
                    BuatPesananRequest createPesananRequest = new BuatPesananRequest(foodIdList, currentuserId, promoCode, responseListener, errorListener);
                    RequestQueue queue = Volley.newRequestQueue(BuatPesananActivity.this);
                    queue.add(createPesananRequest);
                }
            }
        });
    */

    }

}
