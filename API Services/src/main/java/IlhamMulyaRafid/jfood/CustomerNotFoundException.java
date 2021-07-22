package IlhamMulyaRafid.jfood;
/**
 * Customer Not Found Exception class.
 *
 * @author Ilham Mulya Rafid
 * @version 09.04.2020
 * @param customer_error = customer id that returning an error fault.
 *
 */

import java.util.*;
public class CustomerNotFoundException extends Exception{
    private int customer_error;
    //Constructor
    public CustomerNotFoundException(int customer_input){
        super("Customer ID : ");
        this.customer_error = customer_input;
    }
    public String getMessage() {
        return super.getMessage() + customer_error + " not found";
    }
}
