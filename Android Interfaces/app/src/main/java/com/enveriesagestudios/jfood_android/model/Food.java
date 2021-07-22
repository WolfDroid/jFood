package com.enveriesagestudios.jfood_android.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Food implements Parcelable {
    // instance variables - replace the example below with your own
    private int id;
    private String name;
    private int price;
    private String category;
    private Seller seller;

    //Constructor Depracated
    public Food(int id, String name , int price, String category){
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    //Constructor
    public Food(int id, String name , int price, String category, Seller seller){
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.seller = seller;
    }


    protected Food(Parcel in) {
        id = in.readInt();
        name = in.readString();
        price = in.readInt();
        category = in.readString();
    }

    public static final Creator<Food> CREATOR = new Creator<Food>() {
        @Override
        public Food createFromParcel(Parcel in) {
            return new Food(in);
        }

        @Override
        public Food[] newArray(int size) {
            return new Food[size];
        }
    };

    //Accessor
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public Seller getSeller(){
        return seller;
    }
    public int getPrice(){
        return price;
    }
    public String getCategory(){
        return category;
    }

    //Mutator
    public void setId(int id){
        this.id = id;
    }
    public void setSeller(Seller seller){
        this.seller = seller;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setPrice(int price){
        this.price = price;
    }
    public void setCategory(String category){
        this.category = category;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeInt(this.price);
        dest.writeString(this.category);
    }
}
