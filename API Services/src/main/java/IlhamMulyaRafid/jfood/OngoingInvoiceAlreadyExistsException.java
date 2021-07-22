package IlhamMulyaRafid.jfood;
/**
 * OngoingInvoiceAlreadyExistsException Exception class.
 *
 * @author Ilham Mulya Rafid
 * @version 09.04.2020
 * @param invoice_error = invoice object that returning the error
 *
 */
import java.util.*;
public class OngoingInvoiceAlreadyExistsException extends Exception{
    private Invoice invoice_error;
    //Constructor
    public OngoingInvoiceAlreadyExistsException(Invoice invoice_input){
        super("Ongoing Invoice ");
        this.invoice_error= invoice_input;
    }
    public String getMessage() {
        return super.getMessage() + "Already Exists";
    }
}
