package IlhamMulyaRafid.jfood;
/**
 * Invoice class.
 * Description : This class is used for getting and setting the Invoice information.
 *
 * @author Ilham Mulya Rafid
 * @version 20.03.2020
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

//External Library
import java.util.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
