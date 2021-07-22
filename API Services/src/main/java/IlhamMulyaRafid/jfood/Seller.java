package IlhamMulyaRafid.jfood;
/**
 * Seller Class.
 * Description : This class is used for getting and setting the Seller information.
 *
 * @author Ilham Mulya Rafid
 * @version 27.02.2020
 * 
 * @param for every mutator and every variables that called in Constructor
 * @param id = The unique number of the Seller for identifying.
 * @param name = The name of the Seller
 * @param email = The email of the Seller
 * @param phoneNumber = The phone number of the Seller
 * @param Location = The information of the Location
 * 
 * @return for every class with it's parameter
 * @return id = Returning the ID value of the Seller
 * @return name = Returning the name value of the Seller
 * @return email = Returning the email value of the Seller
 * @return phoneNumber = Returning the phone number value of the Seller
 * @return Location = Returning the information of the Location
 * 
 */
public class Seller{
    // instance variables
    private int id;
    private String name;
    private String email;
    private String phoneNumber;
    private Location location;
    
    //Constructor
    public Seller(int id, String name, String email, String phoneNumber, Location location){
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.location = location;
    }
    
    //Accessor
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getEmail(){ 
        return email;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }
    public Location getLocation(){
        return location;
    }

    //Mutator
    public void setId(int id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    public void setLocation(Location location){
        this.location = location;
    }

    //To String
    public String toString(){ 
        return "\nId = " + id +
               "\nNama = " + name +
               "\nEmail = " + email +
               "\nPhoneNumber = " + phoneNumber +
               "\nLocation = " + location;
    }
}
