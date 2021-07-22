package IlhamMulyaRafid.jfood;

/**
 * jFood Main Class
 * Description : This class is containing the mainfunction of the jFood
 *
 * @author Ilham Mulya Rafid
 * @version 28.05.2020
 *
 * @param for every mutator and every variables that called in Constructor
 *
 *
 * @return for every class with it's parameter
 *
 */

import IlhamMulyaRafid.jfood.Database.DatabaseFoodPostgre;
import IlhamMulyaRafid.jfood.Database.DatabaseLocationPostgre;
import IlhamMulyaRafid.jfood.Database.DatabaseSellerPostgre;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class jFood {
    public static void main(String[] args) {
        SpringApplication.run(jFood.class, args);
    }

}

