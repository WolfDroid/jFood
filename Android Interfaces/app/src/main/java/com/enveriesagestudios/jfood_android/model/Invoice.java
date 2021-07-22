package com.enveriesagestudios.jfood_android.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public abstract class Invoice {
    // Variables
    private int id;
    private ArrayList<Food> foods;
    private Calendar date;
    protected int totalPrice;
    private Customer customer;
    private InvoiceStatus invoiceStatus;

    //Constructor
    public Invoice(int id,ArrayList<Food> foods, Customer customer){
        this.id = id;
        this.foods = foods;
        this.customer = customer;
        this.date = Calendar.getInstance();
        this.invoiceStatus = InvoiceStatus.Ongoing;
    }

    //Accessor
    public int getId(){
        return id;
    }
    public ArrayList<Food> getFoods(){
        return foods;
    }
    public Calendar getDate(){
        return date;
    }
    public int getTotalPrice(){
        return totalPrice;
    }
    public Customer getCustomer(){
        return customer;
    }
    public abstract PaymentType getPaymentType();
    public InvoiceStatus getInvoiceStatus(){
        return invoiceStatus;
    }

    //Mutator
    public void setId(int id){
        this.id = id;
    }
    public void setFoods( ArrayList<Food> foods ){
        this.foods = foods;
    }
    public void setDate(Calendar date){
        this.date = date;
    }
    public void setDate(int year, int month, int dayOfMonth){
        this.date = new GregorianCalendar(year, month-1, dayOfMonth);
    }
    public abstract void setTotalPrice();
    public void setCustomer(Customer customer){
        this.customer = customer;
    }
    public void setInvoiceStatus(InvoiceStatus invoiceStatus){
        this.invoiceStatus = invoiceStatus;
    }

    //To String
    public abstract String toString();

}
