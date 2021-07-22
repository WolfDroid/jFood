package IlhamMulyaRafid.jfood;
/**
 * Food Not Found Exception class.
 *
 * @author Ilham Mulya Rafid
 * @version 09.04.2020
 * @param food_error = food id that generated the error
 *
 */

import java.util.*;
public class FoodNotFoundException extends Exception{
    private int food_error;
    //Constructor
    public FoodNotFoundException(int food_input){
        super("Food ID : ");
        this.food_error = food_input;
    }
    public String getMessage() {
        return super.getMessage() + food_error + " not found";
    }
}
