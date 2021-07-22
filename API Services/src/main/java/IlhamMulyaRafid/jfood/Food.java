package IlhamMulyaRafid.jfood;
/**
 * Food Class.
 * Description : This class is used for getting and setting the Food information.
 *
 * @author Ilham Mulya Rafid
 * @version 06.03.2020
 * 
 * @param for every mutator and every variables that called in Constructor
 * @param id = The unique number of the Food for identifying
 * @param name = The name of the food
 * @param price = The value price of the food
 * @param category = The category of the food
 * @param seller = The name of the seller
 * 
 * @return for every class with it's parameter
 * @return id = Returning the Unique ID of the Food
 * @return name = Returning the name of the food
 * @return price = Returning the value price of the Food
 * @return category = Returning the category name of the Food
 * @return seller = Returning the information of the seller
 * 
 */

public class Food{
    // instance variables - replace the example below with your own
    private int id;
    private String name;
    private int price;
    private FoodCategory category;
    private Seller seller;
    
    //Constructor
    public Food(int id, String name , int price, FoodCategory category, Seller seller){
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.seller = seller;
    }
    
    //Accessor
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public Seller getSeller(){
        return seller;
    }
    public int getPrice(){ 
        return price;
    }
    public FoodCategory getCategory(){
        return category;
    }
    
    //Mutator
    public void setId(int id){
        this.id = id;
    }
    public void setSeller(Seller seller){
        this.seller = seller;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setPrice(int price){
        this.price = price;
    }
    public void setCategory(FoodCategory category){
        this.category = category;
    }

    //To String
    public String toString(){ 
        return "\nId = " + id +
               "\nName = " + name +
               "\nSeller = " + getSeller().getName() +
               "\nCity = " + getSeller().getLocation().getCity() +
               "\nPrice = " + price +
               "\nCategory = " + category;
    }

}
