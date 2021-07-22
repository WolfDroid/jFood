package com.enveriesagestudios.jfood_android;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Response;
import com.enveriesagestudios.jfood_android.activity.AddToChartActivity;
import com.enveriesagestudios.jfood_android.model.Food;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


/**
 * Invoice Recycler View Adapter
 * Description : This Class is used to display the Cart Data
 *
 * @author Ilham Mulya Rafid
 * @version 28.05.2020
 *
 * @param for every mutator and every variables that called in Constructor
 *
 * @return for every class with it's parameter
 *
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Integer[] Product_Quantities, Product_totalPrices;
    Integer[] food_id_set, Product_Prices;
    String[] Product_Names, Product_Categories;

    Context context;

    public RecyclerViewAdapter(AddToChartActivity stringListener, Integer[] foodIdSet, String[] foodNameSet, Integer[] foodPriceSet, String[] foodCategorySet, Integer[] click_counter, Integer[] totalPricePerFood) {
        context = stringListener;
        food_id_set = foodIdSet;
        Product_Names = foodNameSet;
        Product_Prices = foodPriceSet;
        Product_Categories = foodCategorySet;
        Product_Quantities = click_counter;
        Product_totalPrices = totalPricePerFood;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.myText1_name.setText(Product_Names[position]);
        holder.myText2_category.setText(Product_Categories[position]);
        holder.myText3_price.setText(Integer.toString(Product_Prices[position]));
        holder.myText4_quantity.setText(Integer.toString(Product_Quantities[position]));
        holder.myText5_totalPricePerFood.setText(Integer.toString(Product_totalPrices[position]));
    }

    @Override
    public int getItemCount() {
        return food_id_set.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView myText1_name, myText2_category, myText3_price, myText4_quantity, myText5_totalPricePerFood;
        ImageView myPhoto1_foodPhoto;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myText1_name = itemView.findViewById(R.id.food_name);
            myText2_category = itemView.findViewById(R.id.food_category);
            myText3_price = itemView.findViewById(R.id.food_price);
            myText4_quantity = itemView.findViewById(R.id.tvQuantity);
            myText5_totalPricePerFood = itemView.findViewById(R.id.tvtotalPricePerFood);
            myPhoto1_foodPhoto = itemView.findViewById(R.id.food_photo);
        }
    }
}
