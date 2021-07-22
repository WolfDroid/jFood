package com.enveriesagestudios.jfood_android.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.enveriesagestudios.jfood_android.MainListAdapter;
import com.enveriesagestudios.jfood_android.R;
import com.enveriesagestudios.jfood_android.model.Food;
import com.enveriesagestudios.jfood_android.model.Location;
import com.enveriesagestudios.jfood_android.model.Seller;
import com.enveriesagestudios.jfood_android.request.BuatPesananRequest;
import com.enveriesagestudios.jfood_android.request.MenuRequest;
import com.enveriesagestudios.jfood_android.request.PesananFetchRequest;
import com.enveriesagestudios.jfood_android.utility.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Main Activity
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

public class MainActivity extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    private ArrayList<Seller> listSeller = new ArrayList<>();
    private ArrayList<Food> foodIdList = new ArrayList<>();
    private HashMap<Seller, ArrayList<Food>> childMapping = new HashMap<>();
    private int currentInvoiceID;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Getting the customer Data
        Intent currentUserandInvoiceIntent = getIntent();

        //Session Manager
        session = new SessionManager(getApplicationContext());

        //Fetching the Invoice ID
        currentInvoiceID = currentUserandInvoiceIntent.getIntExtra("Current_InvoiceID", 0);

        //Expandable List Button
        expListView = findViewById(R.id.lvExp);
        refreshList();

        AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
        builder1.setMessage(""+currentInvoiceID).create().show();

        //Set on child Listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener(){
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Food selected = childMapping.get(listSeller.get(groupPosition)).get(childPosition);
                //Create Order Intent
                Intent createOrderIntent = new Intent(MainActivity.this, BuatPesananActivity.class);
                //Bundling Food Data
                Bundle foodData = new Bundle();
                //Getting Food Data
                int food_id = childMapping.get(listSeller.get(groupPosition)).get(childPosition).getId();
                String food_name = childMapping.get(listSeller.get(groupPosition)).get(childPosition).getName();
                int food_price = childMapping.get(listSeller.get(groupPosition)).get(childPosition).getPrice();
                String food_category = childMapping.get(listSeller.get(groupPosition)).get(childPosition).getCategory();
                foodData.putInt("Food_ID", food_id);
                foodData.putString("Food_Name", food_name);
                foodData.putString("Food_Category", food_category);
                foodData.putInt("Food_Price", food_price);
                foodData.putInt("Customer_ID", session.getUserId());
                //Sending Bundle
                createOrderIntent.putExtras(foodData);
                startActivity(createOrderIntent);
                return false;
            }
        });

        //Button Pesanan Listener
        /*
        btPesanan.setOnClickListener(new View.OnClickListener() {
            //Getting current invoice ID
            @Override
            public void onClick(View view) {
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //JSONObject jsonResponse = new JSONObject(response);
                            JSONArray jsonResponse = new JSONArray(response);
                            if (jsonResponse != null) {
                                Intent mainIntent = new Intent(MainActivity.this, SelesaiPesananActivity.class);
                                for (int i = 0; i < jsonResponse.length(); i++)
                                {
                                    JSONObject invoice = jsonResponse.getJSONObject(i);
                                    //currentInvoiceId = invoice.getInt("id");
                                }
                                Toast.makeText(MainActivity.this, "Berhasil", Toast.LENGTH_LONG).show();
                                //mainIntent.putExtra("currentUserId", currentUserId);
                                //mainIntent.putExtra("currentInvoiceId", currentInvoiceId);
                                //startActivity(mainIntent);
                            }
                        } catch (JSONException e) {
                            Toast.makeText(MainActivity.this, "JSON FAILED", Toast.LENGTH_LONG).show();
                        }
                    }
                };
                Response.ErrorListener errorListener = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error", "Error Occured", error);
                    }
                };
                PesananFetchRequest pesananFetchRequest = new PesananFetchRequest(session.getUserId(), responseListener, errorListener);
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(pesananFetchRequest);
            }
        });

         */
    }

    //Getting Expandable List
    protected void refreshList() {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonResponse = new JSONArray(response);
                    for (int i=0; i<jsonResponse.length(); i++) {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);

                        JSONObject food = jsonResponse.getJSONObject(i);
                        JSONObject seller = food.getJSONObject("seller");
                        JSONObject location = seller.getJSONObject("location");

                        Location newLocation = new Location(
                                location.getString("province"),
                                location.getString("description"),
                                location.getString("city"));

                        Seller newSeller = new Seller(
                                seller.getInt("id"),
                                seller.getString("name"),
                                seller.getString("email"),
                                seller.getString("phoneNumber"),
                                newLocation);

                        Food newFood = new Food(
                                food.getInt("id"),
                                food.getString("name"),
                                food.getInt("price"),
                                food.getString("category"),
                                newSeller);

                        foodIdList.add(newFood);

                        boolean status = true;
                        for(Seller sellerStat : listSeller) {
                            if(sellerStat.getId() == newSeller.getId()){
                                status = false;
                            }
                        }
                        if(status){
                            listSeller.add(newSeller);
                        }
                    }

                    //Dupe control
                    for(Seller sellerStat : listSeller){
                        ArrayList<Food> tempFoodList = new ArrayList<>();
                        for(Food foodPtr : foodIdList){
                            if(foodPtr.getSeller().getId() == sellerStat.getId()){
                                tempFoodList.add(foodPtr);
                            }
                        }
                        childMapping.put(sellerStat, tempFoodList);
                    }

                    listAdapter = new MainListAdapter(MainActivity.this, listSeller, childMapping);
                    expListView.setAdapter(listAdapter);
                }
                catch (JSONException e) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Load Data Failed.").create().show();
                }
            }
        };

        MenuRequest menuRequest = new MenuRequest(responseListener);
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(menuRequest);
    }

}
