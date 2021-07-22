package IlhamMulyaRafid.jfood.Database;

import IlhamMulyaRafid.jfood.Customer;
import IlhamMulyaRafid.jfood.CustomerNotFoundException;
import IlhamMulyaRafid.jfood.DatabaseConnectionPostgre;
import IlhamMulyaRafid.jfood.EmailAlreadyExistsException;

import javax.validation.constraints.Email;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Database Customer Postgre Class
 * Description : This Class used for migrating Customer Data to the Postgresql
 *
 * @author Ilham Mulya Rafid
 * @version 28.05.2020
 *
 * @param for every mutator and every variables that called in Constructor
 * @param customer in insertCustomer = this paramater is used to get the customer object and use them in the method
 * @param id in getCustomer = this paramater is used to get the customer ID and use them in the method
 * @param id in removeCustomer = this paramater is used to get the customer ID and use them in the method
 *
 *
 * @return for every class with it's parameter
 * @return foods in getCustomerDatabase = returning the Arraylist of the Value that meets requirements in the method
 *
 */

public class DatabaseCustomerPostgre {

    //Insert Customer Method
    public static boolean insertCustomer(Customer customer) throws EmailAlreadyExistsException {
        //Useful Variables
        boolean success = true;
        Statement stmt = null;


        //Connecting to Database
        Connection c = DatabaseConnectionPostgre.connection();

        //Inserting the value
        try {
            stmt = c.createStatement();
            String sql = "INSERT INTO customer_data(customer_id, customer_name, customer_email, password) VALUES("+customer.getId()+",'"+customer.getName()+"','"+customer.getEmail()+"','"+customer.getPassword()+"');";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        return success;
    }

    //Get Last Customer ID
    public static int getLastCustomerId(){
        int value=0;
        Connection c = DatabaseConnectionPostgre.connection();
        Statement stmt = null;

        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT MAX (customer_id) AS id FROM customer_data;");
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

    //Get Customer
    public static Customer getCustomer(int id)throws CustomerNotFoundException {
        Customer value = null;
        Connection c = DatabaseConnectionPostgre.connection();
        Statement stmt = null;

        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM customer_data WHERE customer_id = "+id+";");
            while (rs.next()){
                id = rs.getInt("customer_id");
                String name = rs.getString("customer_name");
                String email = rs.getString("customer_email");
                Date joinDate = rs.getDate("customer_joinDate");
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(joinDate);
                String password = rs.getString("password");
                value = new Customer(id, name, email, password, calendar);
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

    //Remove Customer
    public static boolean removeCustomer(int id) {
        Connection c = DatabaseConnectionPostgre.connection();
        boolean success = true;
        Statement stmt = null;

        try {
            stmt = c.createStatement();
            String sql = "DELETE FROM customer_data WHERE customer_id = " + id + ";";
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

    //Get Customer Database
    public static ArrayList<Customer> getCustomerDatabase(){
        ArrayList<Customer> customers = new ArrayList<>();
        Customer value = null;
        Connection c = DatabaseConnectionPostgre.connection();
        Statement stmt = null;

        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM customer_data;");
            while (rs.next()){
                int id = rs.getInt("customer_id");
                String name = rs.getString("customer_name");
                String email = rs.getString("customer_email");
                Date joinDate = rs.getDate("customer_joinDate");
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(joinDate);
                String password = rs.getString("password");
                value = new Customer(id, name, email, password, calendar);
                customers.add(value);
            }
            rs.close();
            stmt.close();
            c.close();
        }catch (Exception e){
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }

        return customers;
    }

}
