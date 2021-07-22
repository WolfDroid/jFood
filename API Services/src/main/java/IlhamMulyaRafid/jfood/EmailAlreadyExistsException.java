package IlhamMulyaRafid.jfood;
/**
 * Email Already Exists Exception class.
 *
 * @author Ilham Mulya Rafid
 * @version 09.04.2020
 * @param customer_error = customer object that returning an error
 *
 */

import java.util.*;
public class EmailAlreadyExistsException extends Exception{
    private Customer customer_error;
    //Constructor
    public EmailAlreadyExistsException(Customer customer_input){
        super("Customer Email : ");
        this.customer_error = customer_input;
    }
    public String getMessage() {
        return super.getMessage() + customer_error.getEmail() + " already Exists";
    }
}
