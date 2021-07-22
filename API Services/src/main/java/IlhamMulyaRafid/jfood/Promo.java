package IlhamMulyaRafid.jfood;
/**
 * Promo Class
 * Description : This class is used for getting and setting the Promo Information.
 *
 * @author Ilham Mulya Rafid
 * @version 12.03.2020
 * 
 * @param for every mutator and every variables that called in Constructor
 * @param id = The unique number of the Promo for identifying
 * @param code = The name code of the promo
 * @param discount = The value of the discounted price
 * @param minPrice = The minimum price value for the Food to get the Discount.
 * @param active = the status of the Promo Code
 * 
 * @return for every class with it's parameter
 * @return id = Return the id value of the Promo
 * @return code = Return The name code of the promo
 * @return discount = Return The value of the discounted price
 * @return minPrice = Return The minimum price value for the Food to get the Discount.
 * @return active = Return The status of the Promo Code
 * 
 */

public class Promo{
    private int id;
    private String code;
    private int discount;
    private int minPrice;
    private boolean active;
    
    public Promo(int id, String code, int discount, int minPrice, boolean active){
        this.id = id;
        this.code = code;
        this.discount = discount;
        this.minPrice = minPrice;
        this.active = active;
    }
    
    //Accessor
    public int getId(){
        return id;
    }
    public String getCode(){
        return code;
    }
    public int getDiscount(){
        return discount;
    }
    public int getMinPrice(){ 
        return minPrice;
    }
    public boolean getActive(){
        return active;
    }
    
    //Mutator
    public void setId(int id){
        this.id = id;
    }
    public void setCode(String seller){
        this.code = code;
    }
    public void setDiscount(String name){
        this.discount = discount;
    }
    public void setMinPrice(int price){
        this.minPrice = minPrice;
    }
    public void setActive(boolean active){
        this.active = active;
    }

    //To String
    public String toString(){ 
        return "\nId = " + id +
               "\nCode = " + code +
               "\nDiscount = " + discount +
               "\nMinPrice = " + minPrice +
               "\nActive status = " + active;
    }
    
}
