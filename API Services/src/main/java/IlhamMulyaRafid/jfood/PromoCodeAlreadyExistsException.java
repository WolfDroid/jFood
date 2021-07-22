package IlhamMulyaRafid.jfood;
/**
 * Promo Already Exists Exception class.
 *
 * @author Ilham Mulya Rafid
 * @version 09.04.2020
 * @param promo_error = promo code object that error
 *
 *
 */
public class PromoCodeAlreadyExistsException extends Exception{
    private Promo promo_error;
    //Constructor
    public PromoCodeAlreadyExistsException(Promo promo_input){
        super("Promo Code : ");
        this.promo_error = promo_input;
    }
    public String getMessage() {
        return super.getMessage() + promo_error.getCode() + " already exists";
    }
}
