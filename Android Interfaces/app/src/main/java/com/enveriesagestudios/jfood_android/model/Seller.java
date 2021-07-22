package com.enveriesagestudios.jfood_android.model;

import com.enveriesagestudios.jfood_android.model.Location;

public class Seller {
    // instance variables
    private int id;
    private String name;
    private String email;
    private String phoneNumber;
    private Location location;

    //Constructor
    public Seller(int id, String name, String email, String phoneNumber, Location location){
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.location = location;
    }

    //Accessor
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }
    public Location getLocation(){
        return location;
    }

    //Mutator
    public void setId(int id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    public void setLocation(Location location){
        this.location = location;
    }

}
