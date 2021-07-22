package com.enveriesagestudios.jfood_android.model;

public class Promo{
    private int id;
    private String code;
    private int discount;
    private int minPrice;
    private boolean active;

    public Promo(int id, String code, int discount, int minPrice, boolean active){
        this.id = id;
        this.code = code;
        this.discount = discount;
        this.minPrice = minPrice;
        this.active = active;
    }

    //Accessor
    public int getId(){
        return id;
    }
    public String getCode(){
        return code;
    }
    public int getDiscount(){
        return discount;
    }
    public int getMinPrice(){
        return minPrice;
    }
    public boolean getActive(){
        return active;
    }

    //Mutator
    public void setId(int id){
        this.id = id;
    }
    public void setCode(String seller){
        this.code = code;
    }
    public void setDiscount(String name){
        this.discount = discount;
    }
    public void setMinPrice(int price){
        this.minPrice = minPrice;
    }
    public void setActive(boolean active){
        this.active = active;
    }

    //To String
    public String toString(){
        return "\nId = " + id +
                "\nCode = " + code +
                "\nDiscount = " + discount +
                "\nMinPrice = " + minPrice +
                "\nActive status = " + active;
    }

}

