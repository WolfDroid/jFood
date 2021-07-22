package com.enveriesagestudios.jfood_android.model;

public enum PaymentType {
    Cashless ("Cashless" ),
    Cash ("Cash");

    private String paymentType;

    //Converting into String Type
    PaymentType ( String paymentType ){
        this.paymentType = paymentType;
    }

    //Returning the String Type
    public String toString(){
        return paymentType;
    }
}
