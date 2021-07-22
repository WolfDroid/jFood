package IlhamMulyaRafid.jfood.Database;

import IlhamMulyaRafid.jfood.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Database Seller Postgre Class
 * Description : This Class used for migrating seller data to the Postgresql
 *
 * @author Ilham Mulya Rafid
 * @version 28.05.2020
 *
 * @param for every mutator and every variables that called in Constructor
 * @param seller in InsertSeller = used for getting the seller object into the method
 * @param id in getSellerById = used for getting the seller id into the method
 *
 * @return for every class with it's parameter
 * @return foods in getSellerDatabase = returning the Arraylist Value of the food that meet the requirements
 *
 */

public class DatabaseSellerPostgre {
    //Get Last Seller ID
    public static int getLastSellerId(){
        int value=0;
        Connection c = DatabaseConnectionPostgre.connection();
        Statement stmt = null;

        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT MAX (seller_id) AS id FROM seller_data;");
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

    //Insert New Seller
    public static boolean insertSeller(Seller seller) throws EmailAlreadyExistsException {

        //Connecting to Database
        Connection c = DatabaseConnectionPostgre.connection();

        //Useful Variables
        boolean success = true;
        Statement stmt = null;

        //Inserting the value
        try {
            stmt = c.createStatement();
            String sql = "INSERT INTO seller_data(seller_id, seller_name, seller_email, seller_phonenumber, seller_location)VALUES('"+seller.getId()+"','"+seller.getName()+"','"+seller.getEmail()+"','"+seller.getPhoneNumber()+"','"+seller.getLocation().getCity()+"');";
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

    //Get Seller By Id
    public static Seller getSellerById(int id) throws SellerNotFoundException {
        Seller value = null;
        Connection c = DatabaseConnectionPostgre.connection();
        Statement stmt = null;

        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM seller_data WHERE seller_id = "+ id +";");
            while (rs.next()){
                id = rs.getInt("seller_id");
                String name = rs.getString("seller_name");
                String email = rs.getString("seller_email");
                String phoneNumber = rs.getString("seller_phonenumber");
                Location location = DatabaseLocationPostgre.getLocationByCity(rs.getString("seller_location"));
                value = new Seller(id, name, email, phoneNumber, location);
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

    //Get Seller Database
    public static ArrayList<Seller> getSellerDatabase(){
        ArrayList<Seller> sellers = new ArrayList<>();
        Seller value = null;
        Connection c = DatabaseConnectionPostgre.connection();
        Statement stmt = null;

        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM seller_data;");
            while (rs.next()){
                int id = rs.getInt("seller_id");
                String name = rs.getString("seller_name");
                String email = rs.getString("seller_email");
                String phoneNumber = rs.getString("seller_phonenumber");
                Location location = DatabaseLocationPostgre.getLocationByCity(rs.getString("seller_location"));
                value = new Seller(id, name, email, phoneNumber, location);
                sellers.add(value);
            }
            rs.close();
            stmt.close();
            c.close();
        }catch (Exception e){
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        return sellers;
    }
}
