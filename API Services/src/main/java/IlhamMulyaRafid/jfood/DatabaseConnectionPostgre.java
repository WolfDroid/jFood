package IlhamMulyaRafid.jfood;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Database Connection Postgre Class
 * Description : This class is used for bridging the database into postgresql
 *
 * @author Ilham Mulya Rafid
 * @version 28.05.2020
 *
 * @param for every mutator and every variables that called in Constructor
 *
 * @return for every class with it's parameter
 *
 */

public class DatabaseConnectionPostgre {
    public static Connection connection() {
        Connection c = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jFood", "JFood_Admin", "password123");
            System.out.println("Opened database successfully");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        return c;
    }
}




