package IlhamMulyaRafid.jfood.controller;

import IlhamMulyaRafid.jfood.*;
import IlhamMulyaRafid.jfood.Database.DatabasePromoPostgre;
import org.springframework.web.bind.annotation.*;
import java.util.*;

/**
 * Promo Controller REST Class
 * Description : This Class used for injectioning promo data using spring boot
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
public class PromoController {

    @RequestMapping(value = "/promo", method = RequestMethod.GET)
    public ArrayList<Promo> getAllPromo(){
        ArrayList<Promo> promoList;
        try{
            promoList = DatabasePromoPostgre.getPromoDatabase();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        return promoList;
    }

    @RequestMapping(value = "/promo/{code}", method = RequestMethod.GET)
    public Promo getPromoByCode(@PathVariable String code){
        Promo promoCode = null;
        try{
            promoCode = DatabasePromoPostgre.getPromoByCode(code);
        } catch (PromoNotFoundException e){
            System.out.println(e.getMessage());
            return null;
        }
        return promoCode;
    }

    @RequestMapping(value = "/promo", method = RequestMethod.POST)
    public Promo addPromo(@RequestParam(value="code") String code,
                          @RequestParam(value="discount") int discount,
                          @RequestParam(value="minPrice") int minPrice,
                          @RequestParam(value="active") boolean active)
    {
        Promo newPromo = new Promo(DatabasePromoPostgre.getLastPromoId()+1, code, discount, minPrice, active);
        try {
            DatabasePromoPostgre.insertPromo(newPromo);
        }catch (PromoCodeAlreadyExistsException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return newPromo;
    }

}
