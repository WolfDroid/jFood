package com.enveriesagestudios.jfood_android.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.enveriesagestudios.jfood_android.MainListAdapter;
import com.enveriesagestudios.jfood_android.R;
import com.enveriesagestudios.jfood_android.RecyclerViewAdapter;
import com.enveriesagestudios.jfood_android.activity.AddToChartActivity;
import com.enveriesagestudios.jfood_android.activity.BuatPesananActivity;
import com.enveriesagestudios.jfood_android.activity.MainActivity;
import com.enveriesagestudios.jfood_android.activity.SelesaiPesananActivity;
import com.enveriesagestudios.jfood_android.model.Food;
import com.enveriesagestudios.jfood_android.model.Location;
import com.enveriesagestudios.jfood_android.model.Seller;
import com.enveriesagestudios.jfood_android.request.BuatPesananRequest;
import com.enveriesagestudios.jfood_android.request.MenuRequest;
import com.enveriesagestudios.jfood_android.request.PesananFetchRequest;
import com.enveriesagestudios.jfood_android.utility.SessionManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Home Fragment Class
 * Description : This Class is used to display the Expandable List for the Seller
 *
 * @author Ilham Mulya Rafid
 * @version 28.05.2020
 *
 * @param for every mutator and every variables that called in Constructor
 *
 * @return for every class with it's parameter
 *
 */

import static androidx.appcompat.app.AlertDialog.*;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    private ArrayList<Seller> listSeller = new ArrayList<>();
    private ArrayList<Food> foodIdList = new ArrayList<>();
    private HashMap<Seller, ArrayList<Food>> childMapping = new HashMap<>();
    private int currentInvoiceID;
    SessionManager session;
    public int counter = 0;
    private int selection_food;


    public View onCreateView(@NonNull final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        final View root = inflater.inflate(R.layout.activity_main, container, false);
        //Create Session
        session = new SessionManager(root.getContext());
        //Expandable List Button
        expListView = root.findViewById(R.id.lvExp);
        refreshList();

        //Food List
        final ArrayList<Food> foodArrayList = new ArrayList<Food>();
        //Food ID List
        final ArrayList<Integer> foodIdList = new ArrayList<Integer>();

        //Food ID List
        final ArrayList<Integer> foodPriceList = new ArrayList<Integer>();

        //Floating Chart Button
        final FloatingActionButton chartButton = root.findViewById(R.id.fab_chart);
        chartButton.setVisibility(View.GONE);
        //Counter

        chartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createOrderIntent = new Intent(root.getContext(), AddToChartActivity.class);
                //Getting the Food ID from Food List
                createOrderIntent.putIntegerArrayListExtra("Selected_FoodIdList", foodIdList);
                createOrderIntent.putParcelableArrayListExtra("Selected_FoodArray", foodArrayList);
                startActivity(createOrderIntent);
            }
        });


        Bundle foodData = new Bundle();
        //foodData.putParcelableArrayList("foodList",foodArrayList);
        //Set on child Listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener(){
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Food selected = childMapping.get(listSeller.get(groupPosition)).get(childPosition);
                int food_id_selected = selected.getId();
                int food_price = selected.getPrice();
                String food_category = selected.getCategory();

                //Adding to Array
                foodIdList.add(food_id_selected);
                foodPriceList.add(food_price);
                foodArrayList.add(selected);

                //Adding
                Snackbar.make(root, "Adding food to cart", Snackbar.LENGTH_LONG).setAction("Action",null).show();
                chartButton.setVisibility(View.VISIBLE);
                /*
                //Create Order Intent
                Intent createOrderIntent = new Intent(getActivity().getApplicationContext(), BuatPesananActivity.class);
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
                 */
                return false;
            }
        });
        return root;
    }

    //Getting Expandable List
    protected void refreshList() {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonResponse = new JSONArray(response);
                    for (int i=0; i<jsonResponse.length(); i++) {
                            Builder builder1 = new Builder(getActivity().getApplicationContext());

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

                    listAdapter = new MainListAdapter(getActivity().getApplicationContext(),listSeller, childMapping);
                    expListView.setAdapter(listAdapter);
                }
                catch (JSONException e) {
                    Builder builder = new Builder(getActivity().getApplicationContext());
                    builder.setMessage("Load Data Failed.").create().show();
                }
            }
        };

        MenuRequest menuRequest = new MenuRequest(responseListener);
        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        queue.add(menuRequest);
    }

}
