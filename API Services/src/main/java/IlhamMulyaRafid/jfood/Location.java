package IlhamMulyaRafid.jfood;
/**
 * Location class.
 * Description : This class is used for getting and setting the Location information.
 *
 * @author Ilham Mulya Rafid
 * @version 27.02.2020
 * 
 * @param for every mutator and every variables that called in Constructor
 * @param province = the name of the Province
 * @param description = the description of the Location
 * @param city = the name of the City
 * 
 * @return for every class with it's parameter
 * @return province = Returning the name value of the Province
 * @return description = Returining the description value of the Location
 * @return city = Returning the name value of the City
 * 
 */
public class Location {
    // instance variables - replace the example below with your own
    private String province;
    private String description;
    private String city;
    
    //Constructor
    public Location( String province, String city, String descrition){
        this.province = province;
        this.city = city;
        this.description = description;
    }

    //Accessor
    public String getProvince(){
        return province;
    }
    public String getCity(){
        return city;
    }
    public String getDescription(){
        return description;
    }
    
    //Mutator
    public void setProvince(String province){
        this.province = province;
    }
    public void setCity(String city){
        this.city = city;
    }
    public void setDescription(String description){
        this.description = description;
    }

    //To String
    public String toString(){ 
        return "\nProvince = " + province +
               "\nCity = " + city +
               "\nDescription = " + description;
    }
}
