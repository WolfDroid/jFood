package IlhamMulyaRafid.jfood;
/**
 * Cash Invoice.
 * Description : This class is used for getting and setting the Cash Invoice information.
 *
 * @author Ilham Mulya Rafid
 * @version 2.04.2020
 * 
 * @param for every mutator and every variables that called in Constructor
 * @param id = Unique number of the Invoice for identifying
 * @param idFood = Unique number from the Food information
 * @param date = Date of the invoice when it's created
 * @param totalPrice = the total amount of the price in transaction
 * @param customer = the information of the Customer
 * @param paymentType = the type of Payment Information.
 * @param status = the type of Payment status Information.
 * 
 * @return for every class with it's parameter
 * @return id = Returning the value ID of the Invoice
 * @return idFood = Returning the value ID of the Food
 * @return date = Returning the date value from the Invoice
 * @return totalPrice = Returning the total amount of the price in transaction
 * @return customer = Returning the information of the Customer.
 * @return paymentType = Returning the type of Payment.
 * @return status = Returning the type of Payment Status.
 * 
 */

import java.util.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class CashInvoice extends Invoice {
     private static final PaymentType PAYMENT_TYPE = PaymentType.Cash;
     private int deliveryFee = 0;

     //Constructor
     public CashInvoice(int id, ArrayList<Food> foods, Customer customer){
        super(id, foods, customer);
     }
     public CashInvoice(int id, ArrayList<Food> foods, Customer customer, int deliveryFee){
        super(id, foods, customer);
        this.deliveryFee = deliveryFee;
     }

    //Accessor
     public PaymentType getPaymentType(){
         return PAYMENT_TYPE;
     }
     public int getDeliveryFee(){
        return deliveryFee;
    }

     //Setter
     public void setDeliveryFee(int deliveryFee){
         this.deliveryFee = deliveryFee;
     }
     public void setTotalPrice() {
        // Looping the Input
         super.totalPrice = 0;
         for (Food foodList : getFoods()) {
             super.totalPrice = super.totalPrice + foodList.getPrice();
         }
         super.totalPrice = super.totalPrice + deliveryFee;
    }

     //to String
     public String toString(){
        String string = "";
        Date date = getDate().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMMM yyyy");
        String formatted = formatter.format(date);

        //Temporal Variable
        String foodName = "";
        for( Food foodList : super.getFoods() ) {
            foodName += foodList.getName();
        }
        //Stringy
                string = "\n===========INVOICE==============" +
                         "\nId = " + super.getId() +
                         "\nDate = " + date +
                         "\nFood = " + foodName;
                         // Delivery Fee Cases
                         if ( deliveryFee > 0 ){
                             string = string + "\nDelivery Fee = " + deliveryFee;
                         }
                 string = string +
                         "\nCustomer = " + super.getCustomer().getName() +
                         "\nTotal Price = " + super.getTotalPrice() +
                         "\nPayment Type: " + PAYMENT_TYPE + "\n";
         return string;
     }

}
