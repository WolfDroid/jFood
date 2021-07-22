package IlhamMulyaRafid.jfood;
/**
 * Cashless Invoice.
 * Description : This class is used for getting and setting the Cashless Invoice information.
 *
 * @author Ilham Mulya Rafid
 * @version 09.04.2020
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
//Library

import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;


public class CashlessInvoice extends Invoice {
    private static final PaymentType PAYMENT_TYPE = PaymentType.Cashless;
    private Promo promo;

    //Constructor
    public CashlessInvoice(int id, ArrayList<Food> foods,Customer customer) {
        super(id, foods, customer);
    }
    public CashlessInvoice(int id, ArrayList<Food> foods,Customer customer, Promo promo) {
        super(id, foods, customer);
        this.promo = promo;
    }

    //Accessor
    @Override
    public PaymentType getPaymentType()
    {
        return PAYMENT_TYPE;
    }
    public Promo getPromo()
    {
        return promo;
    }

    //Mutator
    public void setPromo()
    {
        this.promo = promo;
    }
    public void setTotalPrice() {
        // Looping the Input
        super.totalPrice = 0;
        for (Food foodList : getFoods()) {
            super.totalPrice = super.totalPrice + foodList.getPrice();
        }
        if (promo != null && promo.getActive() == true && super.totalPrice > promo.getMinPrice());{
            totalPrice = totalPrice - promo.getDiscount();
        }
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
        if ( promo != null && getPromo().getActive() == true && super.totalPrice > getPromo().getMinPrice() ){
            string = string + "\nPromo = " + promo.getCode();
        }
        string = string +
                "\nCustomer = " + super.getCustomer().getName() +
                "\nTotal Price = " + getTotalPrice() +
                "\nPayment Type: " + PAYMENT_TYPE + "\n";

        return string;
    }
}

