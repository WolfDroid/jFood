package IlhamMulyaRafid.jfood;
/**
 * Price Calculator Class
 * Description : This class is containing the datas of the food.
 *
 * @author Ilham Mulya Rafid
 * @version 02.04.2020
 *
 * @param for every mutator and every variables that called in Constructor
 *
 *
 * @return for every class with it's parameter
 *
 */

public class PriceCalculator implements Runnable{
    Invoice invoices;

    public PriceCalculator(Invoice invoice){
        this.invoices = invoice;
    }

    @Override
    public void run() {
        System.out.println("Calculating Invoice id : " + invoices.getId());
        invoices.setTotalPrice();
        System.out.println("Finish calcluating invoice id : " + invoices.getId());
    }
}
