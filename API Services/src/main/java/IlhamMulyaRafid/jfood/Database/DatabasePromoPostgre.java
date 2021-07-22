package IlhamMulyaRafid.jfood.Database;

import IlhamMulyaRafid.jfood.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Database Promo Postgre Class
 * Description : This Class used for migrating Promo data to the Postgresql
 *
 * @author Ilham Mulya Rafid
 * @version 28.05.2020
 *
 * @param for every mutator and every variables that called in Constructor
 * @param promo in InsertPromo = used to getting the promo object that being used in method
 * @param id in getPromoByID = used to getting the promo id that being used in method
 * @param Code in getPromoByCode = used to getting the promo code that being used in method
 * @param id in activatePromoByID = used to getting the promo id that being used in method
 * @param id in deactivatePromoByID = used to getting the promo id that being used in method
 * @param id in removePromo = used to getting the promo id that being used in method
 *
 *
 * @return for every class with it's parameter
 * @return promos in getPromoDatabase = returning the Arraylist Value of the promo that meet the requirements
 *
 */

public class DatabasePromoPostgre {
    //Insert Promo Method
    public static boolean insertPromo(Promo promo) throws PromoCodeAlreadyExistsException{
        //Useful Variables
        boolean success = true;
        Statement stmt = null;

        //Connecting to Database
        Connection c = DatabaseConnectionPostgre.connection();

        //Inserting the value
        try {
            stmt = c.createStatement();
            String sql = "INSERT INTO promo_data(promo_id, promo_code, promo_discount, promo_minprice, promo_activation) VALUES('"+promo.getId()+"','"+promo.getCode()+"',"+promo.getDiscount()+",'"+promo.getMinPrice()+"','"+promo.getActive()+"');";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        return success;
    }

    //Get Last Promo ID
    public static int getLastPromoId(){
        int value=0;
        Connection c = DatabaseConnectionPostgre.connection();
        Statement stmt = null;

        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT MAX (promo_id) AS id FROM promo_data;");
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

    //Get Promo By ID
    public static Promo getPromoById(int id)throws PromoNotFoundException {
        Promo value = null;
        Connection c = DatabaseConnectionPostgre.connection();
        Statement stmt = null;

        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM promo_data WHERE promo_id = "+id+";");
            while (rs.next()){
                id = rs.getInt("promo_id");
                String code = rs.getString("promo_code");
                int discount_price = rs.getInt("promo_discount");
                boolean activation_stat = rs.getBoolean("promo_activation");
                int min_price = rs.getInt("promo_minprice");
                value = new Promo(id, code, discount_price, min_price, activation_stat);
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

    //Get Promo By Code
    public static Promo getPromoByCode(String code) throws PromoNotFoundException{
        Promo value = null;
        Connection c = DatabaseConnectionPostgre.connection();
        Statement stmt = null;

        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM promo_data WHERE promo_code = "+"'"+code+"'"+";");
            while (rs.next()){
                int id = rs.getInt("promo_id");
                code = rs.getString("promo_code");
                int discount_price = rs.getInt("promo_discount");
                int min_price = rs.getInt("promo_minprice");
                boolean activation_stat = rs.getBoolean("activation_stat");
                value = new Promo(id, code, discount_price, min_price, activation_stat);
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

    //Set Promo to Active
    public static boolean activatePromoById(int id)throws PromoNotFoundException {
        //Useful Variables
        boolean success = true;
        Connection c = DatabaseConnectionPostgre.connection();
        Statement stmt = null;

        //Update the Value
        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("UPDATE promo_data SET promo_activation = true WHERE promo_id = "+id+";");
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        return success;
    }

    //Deactive Promo
    public static boolean deactivePromoById(int id)throws PromoNotFoundException {
        //Useful Variables
        boolean success = true;
        Connection c = DatabaseConnectionPostgre.connection();
        Statement stmt = null;

        //Update the Value
        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("UPDATE promo_data SET promo_activation = false WHERE promo_id = "+id+";");
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        return success;
    }

    //Remove Promo
    public static boolean removePromo(int id) throws PromoNotFoundException{
        Connection c = DatabaseConnectionPostgre.connection();
        boolean success = true;
        Statement stmt = null;

        try {
            stmt = c.createStatement();
            String sql = "DELETE FROM promo_data WHERE promo_id = " + id + ";";
            stmt.executeUpdate(sql);
//            c.commit();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        return success;
    }

    //Get All Promo in Database
    public static ArrayList<Promo> getPromoDatabase(){
        ArrayList<Promo> promos = new ArrayList<>();
        Promo value = null;
        Connection c = DatabaseConnectionPostgre.connection();
        Statement stmt = null;

        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM promo_data;");
            while (rs.next()){
                int id = rs.getInt("promo_id");
                String code = rs.getString("promo_code");
                int discount_price = rs.getInt("promo_discount");
                int min_price = rs.getInt("promo_minprice");
                boolean activation_stat = rs.getBoolean("activation_stat");
                value = new Promo(id, code, discount_price, min_price, activation_stat);
                promos.add(value);
            }
            rs.close();
            stmt.close();
            c.close();
        }catch (Exception e){
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }

        return promos;
    }

}
