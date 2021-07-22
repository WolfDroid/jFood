package IlhamMulyaRafid.jfood.controller;

import IlhamMulyaRafid.jfood.*;
import IlhamMulyaRafid.jfood.Database.DatabaseLocationPostgre;
import IlhamMulyaRafid.jfood.Database.DatabaseSellerPostgre;
import org.springframework.web.bind.annotation.*;
import java.util.*;

/**
 * Seller Controller REST Class
 * Description : This Class used for injectioning seller data using spring boot
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
public class SellerController {
    @RequestMapping(value = "/seller", method = RequestMethod.GET)
    public ArrayList<Seller> getAllSeller(){
        ArrayList<Seller> sellerList;

        try{
            sellerList = DatabaseSellerPostgre.getSellerDatabase();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        return sellerList;
    }

    @RequestMapping(value = "/seller/{id}", method = RequestMethod.GET)
    public Seller getSellerById(@PathVariable int id){
        Seller sellerId = null;
        try{
            sellerId = DatabaseSellerPostgre.getSellerById(id);
        }catch (SellerNotFoundException e){
            System.out.println(e.getMessage());
            return null;
        }
        return sellerId;
    }

    @RequestMapping(value = "/seller", method = RequestMethod.POST)
    public Seller addSeller(    @RequestParam(value="name") String name,
                                @RequestParam(value="email") String email,
                                @RequestParam(value="phoneNumber") String phoneNumber,
                                @RequestParam(value = "Province") String province,
                                @RequestParam(value= "description") String description,
                                @RequestParam(value = "City") String city
    )
    {
        Seller newSeller = new Seller(DatabaseSellerPostgre.getLastSellerId()+1, name, email, phoneNumber, DatabaseLocationPostgre.getLocationByCity(city));
        try {
            DatabaseSellerPostgre.insertSeller(newSeller);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return newSeller;
    }
}
