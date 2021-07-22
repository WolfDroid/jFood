package IlhamMulyaRafid.jfood;
/**
 * DatabaseInvoice Class.
 * Description : This class is containing the datas of the Invoice.
 *
 * @author Ilham Mulya Rafid
 * @version 09.04.2020
 *
 * @param for every mutator and every variables that called in Constructor
 *
 * @return for every class with it's parameter
 *
 */

import java.util.*;
public class DatabaseInvoice {
    private static ArrayList<Invoice> INVOICE_DATABASE = new ArrayList<Invoice>();
    private static int lastId = 0;

    //Accessor
    public static ArrayList<Invoice> getDatabaseInvoice() {
        return INVOICE_DATABASE;
    }
    public static int getLastId() {
        return lastId;
    }
    public static Invoice getInvoiceById(int id) throws InvoiceNotFoundException{
        //Looping for checking the input
        for (Invoice listInvoice : INVOICE_DATABASE) {
            if (listInvoice.getId() == id) {
                return listInvoice;
            }
        }
        throw new InvoiceNotFoundException(id);
    }
    public static ArrayList<Invoice> getInvoiceByCustomer(int customerId) {
        ArrayList<Invoice> invoiceListByCustomer = new ArrayList<Invoice>();
        //Looping fo checking the input
        for (Invoice listInvoice : INVOICE_DATABASE) {
            if(listInvoice.getCustomer().getId() == customerId) {
                invoiceListByCustomer.add(listInvoice);
            } else {
                invoiceListByCustomer = null;
            }
        }
        return invoiceListByCustomer;
    }

    //Mutator
    public static boolean addInvoice(Invoice invoice) throws OngoingInvoiceAlreadyExistsException{
        //Loop for Checking the input
        for (Invoice listInvoice : INVOICE_DATABASE) {
            if(invoice.getCustomer().equals(listInvoice.getCustomer()) && listInvoice.getInvoiceStatus().equals(InvoiceStatus.Ongoing)) {
                throw new OngoingInvoiceAlreadyExistsException(listInvoice);
            }
        }
        INVOICE_DATABASE.add(invoice);
        lastId = invoice.getId();
        return true;
    }

    public static boolean changeInvoiceStatus(int id, InvoiceStatus invoiceStatus) {
        //Looping for Checking the input
        for (Invoice invoice : INVOICE_DATABASE) {
            if(invoice.getId() == id && invoice.getInvoiceStatus().equals(InvoiceStatus.Ongoing)) {
                invoice.setInvoiceStatus(invoiceStatus);
                return true;
            }
        }
        return false;
    }

    //Finish Invoice
    public static boolean finishInvoiceStatus(int id) throws InvoiceNotFoundException{
        //Looping for Checking the input
        for (Invoice invoice : INVOICE_DATABASE) {
            if(invoice.getId() == id) {
                invoice.setInvoiceStatus(InvoiceStatus.Finished);
                return true;
            }
        }
        return false;
    }

    //Finish Invoice
    public static boolean cancelInvoiceStatus(int id) throws InvoiceNotFoundException{
        //Looping for Checking the input
        for (Invoice invoice : INVOICE_DATABASE) {
            if(invoice.getId() == id) {
                invoice.setInvoiceStatus(InvoiceStatus.Cancelled);
                return true;
            }
        }
        return false;
    }


    public static boolean removeInvoice(int id) throws InvoiceNotFoundException{
        for (Invoice listInvoice : INVOICE_DATABASE){
            if(listInvoice.getId() == id) {
                INVOICE_DATABASE.remove(listInvoice);
                return true;
            }
        }
        throw new InvoiceNotFoundException(id);
    }

}
