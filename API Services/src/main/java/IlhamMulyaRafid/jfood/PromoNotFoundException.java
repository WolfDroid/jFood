package IlhamMulyaRafid.jfood;
/**
 * Seller Not Found Exception class.
 *
 * @author Ilham Mulya Rafid
 * @version 09.04.2020
 * @param promo_error = promo id that not found / meet error
 *
 */

import java.util.*;
public class PromoNotFoundException extends Exception{
    private int promo_error;
    //Constructor
    public PromoNotFoundException(int promo_input){
        super("Promo ID : ");
        this.promo_error = promo_input;
    }
    public String getMessage() {
        return super.getMessage() + promo_error + " not found";
    }
}
