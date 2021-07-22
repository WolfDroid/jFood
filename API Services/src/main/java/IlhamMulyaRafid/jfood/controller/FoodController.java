package IlhamMulyaRafid.jfood.controller;

import IlhamMulyaRafid.jfood.*;
import IlhamMulyaRafid.jfood.Database.DatabaseFoodPostgre;
import IlhamMulyaRafid.jfood.Database.DatabaseSellerPostgre;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.*;

/**
 * Food Controller REST Class
 * Description : This Class used for injectioning food data using spring boot
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
public class FoodController {

    @RequestMapping(value = "/food", method= RequestMethod.GET)
    public ArrayList<Food> getAllFood() {
        ArrayList<Food> foodsList;
        try {
            foodsList = DatabaseFoodPostgre.getFoodDatabase();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return foodsList;
    }

    @RequestMapping(value = "/food/{id}", method= RequestMethod.GET)
    public Food getFoodById(@PathVariable int id) {
        Food foodSelected = null;
        try {
            foodSelected = DatabaseFoodPostgre.getFoodById(id);
        } catch (FoodNotFoundException | SQLException | SellerNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return foodSelected;
    }

    @RequestMapping(value = "/food/seller/{sellerId}", method= RequestMethod.GET)
    public ArrayList<Food> getFoodByID(@PathVariable int sellerId) {
        ArrayList<Food> foodSelectedBySeller = null;
        try {
            foodSelectedBySeller = DatabaseFoodPostgre.getFoodBySellerId(sellerId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return foodSelectedBySeller;
    }

    @RequestMapping(value = "/food/category/{category}", method = RequestMethod.GET)
    public ArrayList<Food> getFoodByCategory(@PathVariable FoodCategory category){
        ArrayList<Food> food = null;
        try
        {
            food = DatabaseFoodPostgre.getFoodByCategory(category);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return food;
    }

    @RequestMapping(value = "/food", method = RequestMethod.POST)
    public Food addFood(    @RequestParam(value="name") String name,
                            @RequestParam(value="price") int price,
                            @RequestParam(value="category") FoodCategory category,
                            @RequestParam(value="sellerId") int sellerId
    ) throws SellerNotFoundException
    {
        Food newFood = new Food(DatabaseFoodPostgre.getLastFoodId()+1, name,  price, category, DatabaseSellerPostgre.getSellerById(sellerId));
        try {
            DatabaseFoodPostgre.insertFood(newFood);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return newFood;
    }

}
