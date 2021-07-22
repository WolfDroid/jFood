package IlhamMulyaRafid.jfood.Database;

import IlhamMulyaRafid.jfood.DatabaseConnectionPostgre;
import IlhamMulyaRafid.jfood.Location;
import IlhamMulyaRafid.jfood.Promo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Database Location Class
 * Description : This Class used for migrating Location data to the Postgresql
 *
 * @author Ilham Mulya Rafid
 * @version 28.05.2020
 *
 * @param for every mutator and every variables that called in Constructor
 * @param Location in InsertLocation = used to getting the Location object that being used in method
 * @param city in getLocationByCity = used to getting the Location City that being used in method
 *
 *
 * @return for every class with it's parameter
 * @return location in getLocationDatabase = returning the Arraylist Value of the Location that meet the requirements
 *
 */

public class DatabaseLocationPostgre {

    //Get Last Location ID
    public static int getLocationLastId(){
        int value=0;
        Connection c = DatabaseConnectionPostgre.connection();
        Statement stmt = null;

        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT MAX (location_id) AS id FROM location_data;");
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

    //Insert New Location
    public static boolean insertLocation(Location location){

        //Connecting to Database
        Connection c = DatabaseConnectionPostgre.connection();

        //Useful Variables
        boolean success = true;
        Statement stmt = null;

        //Inserting the value
        try {
            stmt = c.createStatement();
            String sql = "INSERT INTO location_data(location_province, location_city, location_description) VALUES('"+location.getProvince()+"','"+location.getCity()+"','"+location.getDescription()+"');";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return success;
    }

    //Get Location By City
    public static Location getLocationByCity(String city){
        Location value = null;
        Connection c = DatabaseConnectionPostgre.connection();
        Statement stmt = null;

        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM location_data WHERE location_city = "+"'"+city+"'"+";");
            while (rs.next()){
                String province = rs.getString("location_province");
                city = rs.getString("location_city");
                String description = rs.getString("location_description");
                value = new Location(province, city, description);
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

    //Get Location Database
    public static ArrayList<Location> getLocationDatabase(){
        ArrayList<Location> location = new ArrayList<>();
        Location value = null;
        Connection c = DatabaseConnectionPostgre.connection();
        Statement stmt = null;

        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM location;");
            while (rs.next()){
                String province = rs.getString("location_province");
                String description = rs.getString("location_description");
                String city = rs.getString("location_city");
                value = new Location(province, city, description);
                location.add(value);
            }
            rs.close();
            stmt.close();
            c.close();
        }catch (Exception e){
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        return location;
    }

}
