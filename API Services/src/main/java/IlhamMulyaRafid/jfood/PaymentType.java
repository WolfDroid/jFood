package IlhamMulyaRafid.jfood;
/**
 * Payment Type Class
 * Description : This class is used for getting and setting the Payment Type information.
 *
 * @author Ilham Mulya Rafid
 * @version 06.03.2020
 * 
 * @param for every mutator and every variables that called in accessor
 * @param paymentType : Type of payment that being used ( Cash or Cashless )
 * 
 */
public enum PaymentType
{
    Cashless ("Cashless" ),
    Cash ("Cash");
    
    private String paymentType;
    
    //Converting into String Type
    PaymentType ( String paymentType ){
        this.paymentType = paymentType;
    }
    
    //Returning the String Type
    public String toString(){
        return paymentType;
    }
}
