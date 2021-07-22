package IlhamMulyaRafid.jfood;
/**
 * InvoiceNotFoundExceptionAlready Exception class.
 *
 * @author Ilham Mulya Rafid
 * @version 09.04.2020
 *
 *
 */
import java.util.*;
public class InvoiceNotFoundException extends Exception{
    private int invoice_error;
    //Constructor
    public InvoiceNotFoundException(int invoice_input){
        super("Invoice ID : ");
        this.invoice_error= invoice_input;
    }
    public String getMessage() {
        return super.getMessage() + invoice_error + " not found";
    }
}
