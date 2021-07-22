package IlhamMulyaRafid.jfood;
/**
 * Write a description of class InvoiceStatus here.
 *
 * @Ilham Mulya Rafid
 * @version 12.03.2020
 */

public enum InvoiceStatus {
    Ongoing ("Ongoing"),
    Finished ("Finished"),
    Cancelled ("Cancelled");
    
    private String invoiceStatus;
    
    //Converting into String Type
    InvoiceStatus ( String invoiceStatus ){
        this.invoiceStatus = invoiceStatus;
    }
    
    //Returning the String type
    public String toString(){
        return invoiceStatus;
    }
}
