package IlhamMulyaRafid.jfood;
/**
 * Food Category Class
 * Description : This class is used for getting and setting Category from the Food information.
 *
 * @author Ilham Mulya Rafid
 * @version 06.03.2020
 * 
 * @param for every mutator and every variables that called in accessor
 * @param foodCategory : A list of Food Category in Enumerated String ( Beverages Cofee, Wwester, Snacks, Rice, Noodles, Backery, Japanese )
 * 
 */

public enum FoodCategory {
    Beverages ("Beverages"),
    Coffee ("Coffee"),
    Western ("Western"),
    Snacks ("Snacks"),
    Rice ("Rice"),
    Noodles ("Noodles"),
    Bakery ("Bakery"),
    Japanese ("Japanese");
    
    private String foodCategory;
    
    //Converting into String Type
    FoodCategory( String foodCategory ){
        this.foodCategory = foodCategory;
    }
    
    //Returning the String type
    public String toString(){
        return foodCategory;
    }
    
}
