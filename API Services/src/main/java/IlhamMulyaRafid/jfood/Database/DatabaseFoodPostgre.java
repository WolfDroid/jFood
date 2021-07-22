package IlhamMulyaRafid.jfood.Database;

import IlhamMulyaRafid.jfood.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Database Food Postgre Class
 * Description : This Class used for migrating Food Data to the Postgresql
 *
 * @author Ilham Mulya Rafid
 * @version 28.05.2020
 *
 * @param for every mutator and every variables that called in Constructor
 * @param food in insertFood = this paramater is used to get the food object and use them in the method
 * @param id in getFoodByID = this paramater is used to get the food ID and use them in the method
 * @param id in getFoodBySellerId = this paramater is used to get the seller ID and use them in the method
 * @param category in getFoodByCategory = this paramater is used to get the food category and use them in the method
 *
 *
 * @return for every class with it's parameter
 * @return foods in getFoodBySellerID = returning the Arraylist of the Value that meets requirements in the method
 * @return foods in getFoodByCategory = returning the Arraylist of the Value that meets requirements in the method
 * @return foods in getFoodDatabase = returning the Arraylist of the Value that meets requirements in the method
 *
 */

public class DatabaseFoodPostgre {
    //Insert Food Method
    public static boolean insertFood(Food food){
        //Connecting to Database
        Connection c = DatabaseConnectionPostgre.connection();

        //Useful Variables
        boolean success = true;
        Statement stmt = null;

        //Inserting the value
        try {
            stmt = c.createStatement();
            String sql = "INSERT INTO food_data(food_id, food_name, food_price, food_category, food_seller) VALUES('"+food.getId()+"','"+food.getName()+"','"+food.getPrice()+"','"+food.getCategory()+"','"+food.getSeller().getId()+"');";
            stmt.executeUpdate(sql);
            stmt.close();
            //c.commit();
            c.close();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return success;
    }

    //Get Last Food ID
    public static int getLastFoodId(){
        int value=0;
        Connection c = DatabaseConnectionPostgre.connection();
        Statement stmt = null;

        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT MAX (food_id) AS id FROM food_data;");
            while (rs.next()){
                value = rs.getInt("id");
            }
            rs.close();
            stmt.close();
            c.close();
        }catch (Exception e){
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        return value;
    }

    //Get Food By Id
    public static Food getFoodById(int id) throws FoodNotFoundException, SQLException, SellerNotFoundException {
        Food value = null;
        Connection c = DatabaseConnectionPostgre.connection();
        Statement stmt = null;

        try {
            stmt = c.createStatement();
            FoodCategory foodCategory = null;
            ResultSet rs = stmt.executeQuery("SELECT * FROM food_data WHERE food_id = "+ id +";");
            while (rs.next()){
                int foodId = rs.getInt("food_id");
                String name = rs.getString("food_name");
                int price = rs.getInt("food_price");
                String category = rs.getString("food_category");
                if (category.equals("Western")){
                    foodCategory = FoodCategory.Western;
                } else if (category.equals("Snacks")){
                    foodCategory = FoodCategory.Snacks;
                } else if (category.equals("Rice")){
                    foodCategory = FoodCategory.Rice;
                } else if (category.equals("Noodles")){
                    foodCategory = FoodCategory.Noodles;
                } else if (category.equals("Bakery")){
                    foodCategory = FoodCategory.Bakery;
                } else if (category.equals("Japanese")){
                    foodCategory = FoodCategory.Japanese;
                }
                Seller seller = DatabaseSellerPostgre.getSellerById(rs.getInt("food_seller"));
                value = new Food(foodId, name, price, foodCategory, seller);
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e){
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        return value;
    }

    //Get Food By Seller ID
    public static ArrayList<Food> getFoodBySellerId(int id) throws FoodNotFoundException {
        Food value = null;
        Connection c = DatabaseConnectionPostgre.connection();
        Statement stmt = null;
        ArrayList<Food> foods = new ArrayList<>();

        try {
            stmt = c.createStatement();
            FoodCategory foodCategory = null;
            ResultSet rs = stmt.executeQuery("SELECT * FROM food_data WHERE food_seller = "+ id +";");
            while (rs.next()){
                int foodId = rs.getInt("food_id");
                String name = rs.getString("food_name");
                int price = rs.getInt("food_price");
                String category = rs.getString("food_category");
                if (category.equals("Western")){
                    foodCategory = FoodCategory.Western;
                } else if (category.equals("Snacks")){
                    foodCategory = FoodCategory.Snacks;
                } else if (category.equals("Rice")){
                    foodCategory = FoodCategory.Rice;
                } else if (category.equals("Noodles")){
                    foodCategory = FoodCategory.Noodles;
                } else if (category.equals("Bakery")){
                    foodCategory = FoodCategory.Bakery;
                } else if (category.equals("Japanese")){
                    foodCategory = FoodCategory.Japanese;
                }
                Seller seller = DatabaseSellerPostgre.getSellerById(rs.getInt("food_seller"));
                value = new Food(foodId, name, price, foodCategory, seller);
                foods.add(value);
            }
            rs.close();
            stmt.close();
            c.close();
        }catch (Exception e){
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        return foods;
    }

    //Get Food By Category
    public static ArrayList<Food> getFoodByCategory(FoodCategory searchCategory) throws FoodNotFoundException {
        Food value = null;
        Connection c = DatabaseConnectionPostgre.connection();
        Statement stmt = null;
        ArrayList<Food> foods = new ArrayList<>();

        try {
            stmt = c.createStatement();
            FoodCategory foodCategory = null;
            ResultSet rs = stmt.executeQuery("SELECT * FROM food_data WHERE food_category = '"+ searchCategory +"';");
            while (rs.next()){
                int foodId = rs.getInt("food_id");
                String name = rs.getString("food_name");
                int price = rs.getInt("food_price");
                String category = rs.getString("food_category");
                if (category.equals("Western")){
                    foodCategory = FoodCategory.Western;
                } else if (category.equals("Snacks")){
                    foodCategory = FoodCategory.Snacks;
                } else if (category.equals("Rice")){
                    foodCategory = FoodCategory.Rice;
                } else if (category.equals("Noodles")){
                    foodCategory = FoodCategory.Noodles;
                } else if (category.equals("Bakery")){
                    foodCategory = FoodCategory.Bakery;
                } else if (category.equals("Japanese")){
                    foodCategory = FoodCategory.Japanese;
                }
                Seller seller = DatabaseSellerPostgre.getSellerById(rs.getInt("food_seller"));
                value = new Food(foodId, name, price, foodCategory, seller);
                foods.add(value);
            }
            rs.close();
            stmt.close();
            c.close();
        }catch (Exception e){
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        return foods;
    }

    //Get Food Database List Method
    public static ArrayList<Food> getFoodDatabase() {
        ArrayList<Food> foods = new ArrayList<>();
        Food value = null;
        Connection c = DatabaseConnectionPostgre.connection();
        Statement stmt = null;

        try {
            stmt = c.createStatement();
            FoodCategory foodCategory = null;
            ResultSet rs = stmt.executeQuery("SELECT * FROM food_data;");
            while (rs.next()){
                int foodId = rs.getInt("food_id");
                String name = rs.getString("food_name");
                int price = rs.getInt("food_price");
                String category = rs.getString("food_category");
                if (category.equals("Western")){
                    foodCategory = FoodCategory.Western;
                } else if (category.equals("Snacks")){
                    foodCategory = FoodCategory.Snacks;
                } else if (category.equals("Rice")){
                    foodCategory = FoodCategory.Rice;
                } else if (category.equals("Noodles")){
                    foodCategory = FoodCategory.Noodles;
                } else if (category.equals("Bakery")){
                    foodCategory = FoodCategory.Bakery;
                } else if (category.equals("Japanese")){
                    foodCategory = FoodCategory.Japanese;
                }
                Seller seller = DatabaseSellerPostgre.getSellerById(rs.getInt("food_seller"));
                value = new Food(foodId, name, price, foodCategory, seller);
                foods.add(value);
            }
            rs.close();
            stmt.close();
            c.close();
        }catch (Exception e){
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        return foods;
    }

}
