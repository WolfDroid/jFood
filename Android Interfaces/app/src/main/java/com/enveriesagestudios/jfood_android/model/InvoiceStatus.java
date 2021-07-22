package com.enveriesagestudios.jfood_android.model;

public enum InvoiceStatus {
    Ongoing ("Ongoing"),
    Finished ("Finished"),
    Cancelled ("Cancelled");

    private String invoiceStatus;

    //Converting into String Type
    InvoiceStatus ( String invoiceStatus ){
        this.invoiceStatus = invoiceStatus;
    }

    //Returning the String type
    public String toString(){
        return invoiceStatus;
    }
}