package IlhamMulyaRafid.jfood;
/**
 * Seller Not Found Exception class.
 *
 * @author Ilham Mulya Rafid
 * @version 09.04.2020
 * @param seller_error = seller id that error
 *
 */

import java.util.*;
public class SellerNotFoundException extends Exception{
    private int seller_error;
    //Constructor
    public SellerNotFoundException(int seller_input){
        super("Seller ID : ");
        this.seller_error = seller_input;
    }
    public String getMessage() {
        return super.getMessage() + seller_error + " not found";
    }
}
