package IlhamMulyaRafid.jfood.controller;

import IlhamMulyaRafid.jfood.*;
import IlhamMulyaRafid.jfood.Database.DatabaseCustomerPostgre;
import org.springframework.web.bind.annotation.*;

/**
 * Customer Controller REST Class
 * Description : This Class used for injectioning customer data using spring boot
 *
 * @author Ilham Mulya Rafid
 * @version 28.05.2020
 *
 * @param for every mutator and every variables that called in Constructor
 *
 * @return for every class with it's parameter
 *
 */


@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping("/customer")
@RestController

public class CustomerController {

    @RequestMapping("")
    public String indexPage(@RequestParam(value="name", defaultValue="world") String name) {
        return "Hello " + name;
    }

    @RequestMapping("/{id}")
    public Customer getCustomerById(@PathVariable int id) throws CustomerNotFoundException{
        Customer customer = DatabaseCustomerPostgre.getCustomer(id);
        return customer;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Customer registerCustomer(   @RequestParam(value="name") String name,
                                        @RequestParam(value="email") String email,
                                        @RequestParam(value="password") String password) throws EmailAlreadyExistsException {
        Customer customer = new Customer(DatabaseCustomerPostgre.getLastCustomerId()+1, name, email, password);
        DatabaseCustomerPostgre.insertCustomer(customer);
        return customer;
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Customer loginCust(@RequestParam(value = "email") String email,
                              @RequestParam(value = "password") String password ) {

        for (Customer cust : DatabaseCustomerPostgre.getCustomerDatabase()) {
            if (cust.getEmail().equals(email) && cust.getPassword().equals(password)) {
                return cust;
            }
        }
        return null;
    }
}