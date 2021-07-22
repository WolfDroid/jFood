package IlhamMulyaRafid.jfood.controller;

import IlhamMulyaRafid.jfood.*;
import IlhamMulyaRafid.jfood.Database.DatabaseCustomerPostgre;
import IlhamMulyaRafid.jfood.Database.DatabaseFoodPostgre;
import IlhamMulyaRafid.jfood.Database.DatabasePromoPostgre;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.*;

/**
 * Invoice Controller REST Class
 * Description : This Class used for injectioning invoice data using spring boot
 *
 * @author Ilham Mulya Rafid
 * @version 28.05.2020
 *
 * @param for every mutator and every variables that called in Constructor
 *
 * @return for every class with it's parameter
 *
 */

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class InvoiceController {

    //Get All Invoice
    @RequestMapping(value = "/invoice", method= RequestMethod.GET)
    public ArrayList<Invoice> getAllInvoice() {
        ArrayList<Invoice> invoiceList;
        try {
            invoiceList = DatabaseInvoice.getDatabaseInvoice();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return invoiceList;
    }

    //Get Invoice By Id
    @RequestMapping(value = "/invoice/{id}", method= RequestMethod.GET)
    public Invoice getInvoiceById(@PathVariable int id) {
        Invoice invoiceSelected = null;
        try {
            invoiceSelected = DatabaseInvoice.getInvoiceById(id);
        } catch (InvoiceNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return invoiceSelected;
    }

    //Get Invoice By Customer Id
    @RequestMapping(value = "/invoice/customer/{customerId}", method = RequestMethod.GET)
    public ArrayList<Invoice> getInvoiceByCustomerID(@PathVariable int customerId) {
        ArrayList<Invoice> invoiceSelected = null;
        try {
            invoiceSelected = DatabaseInvoice.getInvoiceByCustomer(customerId);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return invoiceSelected;
    }

    //Change Invoice Status
    @RequestMapping(value = "/invoice/invoiceStatus/{id}", method = RequestMethod.PUT)
    public Invoice changeInvoiceStatus(@RequestParam(value = "id") int id,
                                       @RequestParam(value = "status") InvoiceStatus status) throws InvoiceNotFoundException
    {
        Invoice invoice = null;
        DatabaseInvoice.changeInvoiceStatus(id, status);
        try
        {
            invoice = DatabaseInvoice.getInvoiceById(id);
        }
        catch (Exception error)
        {
            error.getMessage();
            return null;
        }
        return invoice;
    }

    //Finish Invoice
    @RequestMapping(value = "/invoice/finishInvoice/{id}", method = RequestMethod.PUT)
    public Invoice finisihInvoiceStatus(@RequestParam(value = "id") int id) throws InvoiceNotFoundException
    {
        Invoice invoice = null;
        DatabaseInvoice.finishInvoiceStatus(id);
        try
        {
            invoice = DatabaseInvoice.getInvoiceById(id);
        }
        catch (Exception error)
        {
            error.getMessage();
            return null;
        }
        return invoice;
    }

    //Cancel Invoice
    @RequestMapping(value = "/invoice/cancelInvoice/{id}", method = RequestMethod.PUT)
    public Invoice cancelInvoiceStatus(@RequestParam(value = "id") int id) throws InvoiceNotFoundException
    {
        Invoice invoice = null;
        DatabaseInvoice.cancelInvoiceStatus(id);
        try
        {
            invoice = DatabaseInvoice.getInvoiceById(id);
        }
        catch (Exception error)
        {
            error.getMessage();
            return null;
        }
        return invoice;
    }

    //Remove Invoice by ID
    @RequestMapping(value = "/invoice/{id}", method = RequestMethod.DELETE)
    public Boolean removeInvoice(@RequestParam(value = "id") int id) throws InvoiceNotFoundException {
        try {
            DatabaseInvoice.removeInvoice(id);
        }
        catch (InvoiceNotFoundException e)
        {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    //Add Cash Invoice
    @RequestMapping(value = "invoice/addCashInvoice", method = RequestMethod.POST)
    public Invoice addCashInvoice(
            @RequestParam(value="foodIdList") ArrayList<Integer> foodIdList,
            @RequestParam(value="customerId") int customerId,
            @RequestParam(value="deliveryFee", defaultValue = "0") int deliveryFee)
    {
        ArrayList<Food> foods = new ArrayList<>();
        for (int food : foodIdList) {
            try {
                foods.add(DatabaseFoodPostgre.getFoodById(food));
            } catch (FoodNotFoundException | SQLException | SellerNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
        try {
            Invoice invoice = new CashInvoice(DatabaseInvoice.getLastId()+1, foods,
                    DatabaseCustomerPostgre.getCustomer(customerId), deliveryFee);
            DatabaseInvoice.addInvoice(invoice);
            invoice.setTotalPrice();
            return invoice;
        } catch (CustomerNotFoundException | OngoingInvoiceAlreadyExistsException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    //Add Cashless Invoice
    @RequestMapping(value = "invoice/addCashlessInvoice", method = RequestMethod.POST)
    public Invoice addCashlessInvoice(
            @RequestParam(value="foodIdList") ArrayList<Integer> foodIdList,
            @RequestParam(value="customerId") int customerId,
            @RequestParam(value="promoCode", defaultValue = " ") String promoCode) throws PromoNotFoundException {
        ArrayList<Food> foods = new ArrayList<>();
        for (int food : foodIdList) {
            try {
                foods.add(DatabaseFoodPostgre.getFoodById(food));
            } catch (FoodNotFoundException | SQLException | SellerNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
        try {
            Invoice invoice = new CashlessInvoice(DatabaseInvoice.getLastId() + 1, foods,
                    DatabaseCustomerPostgre.getCustomer(customerId), DatabasePromoPostgre.getPromoByCode(promoCode));
            DatabaseInvoice.addInvoice(invoice);
            invoice.setTotalPrice();
            return invoice;
        } catch (CustomerNotFoundException | OngoingInvoiceAlreadyExistsException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
